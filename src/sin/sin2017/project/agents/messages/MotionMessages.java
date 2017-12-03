package sin.sin2017.project.agents.messages;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.MotionSensorStatus;
import sin.sin2017.project.agents.MotionSensorAgent;

import java.io.IOException;

public class MotionMessages extends CyclicBehaviour{

    @Override
    public void action() {
        MotionSensorAgent agent = (MotionSensorAgent) myAgent;
        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {

            if(msg.getLanguage() != Constants.NON_REPLY) {
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
}
