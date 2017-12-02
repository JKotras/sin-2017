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
        try{
            readJson readJson = new readJson();
            String status = readJson.getSwitchState(Integer.parseInt(Constants.MOTION_SENSOR_ID));
            if(status == Constants.LIGHT_TURN_OFF) {
                agent.getMotionSensorStatus().isThereAnyone = false;
            }else{
                agent.getMotionSensorStatus().isThereAnyone = true;
            }
        }catch (Exception e){
            System.err.println("Error in read status");
        }

        //set it into lights statuses

        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {

            ACLMessage reply = msg.createReply();

            reply.setPerformative(ACLMessage.INFORM);

            MotionSensorStatus status = agent.getMotionSensorStatus();
            String serialized = null;

            try {
                serialized = status.serialize();
            } catch (IOException e) {
                System.err.println("Error");
            }

            reply.setContent(serialized);

            myAgent.send(reply);
        }
    }
}
