package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.Status.MotionSensorStatus;
import sin.sin2017.project.agents.BlindAgent;
import sin.sin2017.project.agents.MotionSensorAgent;
import sin.sin2017.project.domoticz.readJson;

import java.io.IOException;

public class MotionSensorStatusInformation extends CyclicBehaviour {
    @Override
    public void action() {
        MotionSensorAgent agent = (MotionSensorAgent) myAgent;
        boolean inState = agent.getMotionSensorStatus().isThereAnyone;
        try{
            readJson readJson = new readJson();
            String status = readJson.getSwitchState(Integer.parseInt(Constants.MOTION_SENSOR_ID));
            if(status.compareTo(Constants.LIGHT_TURN_OFF) == 0) {
                agent.getMotionSensorStatus().isThereAnyone = false;
            }else{
                agent.getMotionSensorStatus().isThereAnyone = true;
            }
        }catch (Exception e){
            System.err.println("Error in read status");
        }
        if(inState != agent.getMotionSensorStatus().isThereAnyone){
            System.out.println("change");
            agent.infoOthersMotionChange();
        }
    }
}
