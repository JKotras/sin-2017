package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.*;
import sin.sin2017.project.StatusInformations.BlindStatusInformation;
import sin.sin2017.project.StatusInformations.LightStatusInfomations;
import sin.sin2017.project.agents.messages.BlindMessages;
import sin.sin2017.project.domoticz.ChangeState;

public class BlindAgent extends Agent{
    protected BlindStatus blindStatus = new BlindStatus();
    protected int blindStep = 10;
    protected int blindMax = 100;
    protected int blindMin = 0;
    public ProjectorStatus projectorStatus;
    public LightLevelStatus lightLevelStatus;

    @Override
    protected void setup() {
        super.setup();
        setBlindsByStatus();
        addBehaviour(new BlindStatusInformation());
        addBehaviour(new BlindMessages());
    }

    public BlindStatus getBlindStatus() {
        return blindStatus;
    }

    public void setBlindsByStatus(){
        int actual = blindStatus.percentageOfClose;
        if(lightLevelStatus != null){
            int percetageofLights = lightLevelStatus.percentageOfSun;
            if(percetageofLights >= Constants.LIGHT_LEVEL_TO_MUCH_SUN){
                int dif = percetageofLights - Constants.LIGHT_LEVEL_TO_MUCH_SUN;
                int scale = this.blindMax - Constants.LIGHT_LEVEL_TO_MUCH_SUN;
                float ratio = ((float)dif/(float)scale);
                int percentaceOfClose = ((int) (ratio*100));
                actual = percentaceOfClose;
            }else{
                actual = 0;
            }
        }

        if(projectorStatus != null){
            if(projectorStatus.status.compareTo(Constants.LIGHT_TURN_ON) == 0){
                actual = actual + 20;
            }
        }
        if(actual < blindMin){
            actual = blindMin;
        }
        if(actual > blindMax){
            actual = blindMax;
        }
        //change to ten times
        actual = ((int)(actual/this.blindStep))*blindStep;

        if(actual != blindStatus.percentageOfClose){
            try {
                ChangeState changeState = new ChangeState();
                changeState.setSwitch(Integer.parseInt(Constants.BLIND_ID), actual);
            }catch (Exception e){
                System.err.println("SunBlind - change state faild");
            }
        }

    }
}
