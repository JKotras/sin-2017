package sin.sin2017.project.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.*;
import sin.sin2017.project.StatusInformations.LightStatusInfomations;
import sin.sin2017.project.agents.messages.LightMessages;
import sin.sin2017.project.domoticz.ChangeState;

public class LightAgent extends Agent {
    protected LightsStatus lightsStatus= new LightsStatus();
    public ProjectorStatus projectorStatus;
    public MotionSensorStatus motionSensorStatus;
    public TimeStatus timeStatus;
    public LightLevelStatus lightLevelStatus;

    @Override
    protected void setup() {
        super.setup();
        setLightsByStatus();
        addBehaviour(new LightStatusInfomations());
        addBehaviour(new LightMessages());
    }

    public LightsStatus getLightsStatus() {
        return lightsStatus;
    }

    public void setLightsByStatus(){
        if(motionSensorStatus != null){
            if(motionSensorStatus.isThereAnyone){
                lightsStatus.lightBlackboardStatus = Constants.LIGHT_TURN_ON;
                lightsStatus.lightMiddleStatus = Constants.LIGHT_TURN_ON;
                lightsStatus.lightWindowsStatus = Constants.LIGHT_TURN_ON;
            }else{
                lightsStatus.lightBlackboardStatus = Constants.LIGHT_TURN_OFF;
                lightsStatus.lightMiddleStatus = Constants.LIGHT_TURN_OFF;
                lightsStatus.lightWindowsStatus = Constants.LIGHT_TURN_OFF;
            }
        }
        if(projectorStatus != null){
            if(projectorStatus.status.compareTo(Constants.LIGHT_TURN_ON) == 0){
                lightsStatus.lightBlackboardStatus = Constants.LIGHT_TURN_OFF;
            }
        }
        if(lightLevelStatus != null){
            if(lightLevelStatus.percentageOfSun <= Constants.LIGHT_LEVEL_TO_LOW_SUN){
                lightsStatus.lightBlackboardStatus = Constants.LIGHT_TURN_ON;
                lightsStatus.lightMiddleStatus = Constants.LIGHT_TURN_ON;
                lightsStatus.lightWindowsStatus = Constants.LIGHT_TURN_ON;
            }
        }

        try {
            ChangeState changeState = new ChangeState();
            changeState.turnSwitch(Integer.parseInt(Constants.LIGHT_WINDOW_ID), lightsStatus.lightWindowsStatus);
            changeState.turnSwitch(Integer.parseInt(Constants.LIGHT_MIDDLE_ID), lightsStatus.lightMiddleStatus);
            changeState.turnSwitch(Integer.parseInt(Constants.LIGHT_BLACKBOARD_ID), lightsStatus.lightBlackboardStatus);
        }catch (Exception e){
            System.err.println("Lights - set state faild");
        }
    }
}
