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
        addBehaviour(new LightStatusInfomations());
        addBehaviour(new LightMessages());
    }

    public LightsStatus getLightsStatus() {
        return lightsStatus;
    }

    public void setLightsByStatus(){
        try {
            ChangeState changeState = new ChangeState();
            this.lightsStatus.lightWindowsStatus = Constants.LIGHT_TURN_ON;
            changeState.turnSwitch(Integer.parseInt(Constants.LIGHT_MIDDLE_ID), Constants.LIGHT_TURN_ON);
        }catch (Exception e){
            System.err.println("Set state faild");
        }
    }
}
