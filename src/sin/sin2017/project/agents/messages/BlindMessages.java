package sin.sin2017.project.agents.messages;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.agents.BlindAgent;

import java.io.IOException;

public class BlindMessages extends CyclicBehaviour{
    @Override
    public void action() {
        BlindAgent agent = (BlindAgent) myAgent;
        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {

            if(msg.getLanguage() != Constants.NON_REPLY) {
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                BlindStatus status = agent.getBlindStatus();
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
