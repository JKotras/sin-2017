package sin.sin2017.project.agents.messages;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.LightsStatus;
import sin.sin2017.project.agents.LightAgent;

import java.io.IOException;

public class LightMessages extends CyclicBehaviour{

    @Override
    public void action() {
        LightAgent lightAgent = (LightAgent) myAgent;
        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            if(msg.getLanguage() != Constants.NON_REPLY) {
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                LightsStatus status = lightAgent.getLightsStatus();
                String serialized = null;
                try {
                    serialized = status.serialize();
                } catch (IOException e) {
                    System.err.println("Error in replay");
                }
                reply.setContent(serialized);
                myAgent.send(reply);
            }
        }
    }
}
