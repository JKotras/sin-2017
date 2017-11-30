package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Status.DayStatus;
import sin.sin2017.project.agents.DayAgent;

import java.io.IOException;

public class DayStatusInformation extends CyclicBehaviour{

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);

        if (msg != null) {
            DayAgent dayAgent = (DayAgent) myAgent;

            // process message
            ACLMessage reply = msg.createReply();

            // respond with queues length
            reply.setPerformative(ACLMessage.INFORM);

            DayStatus status = dayAgent.getDayStatus();
            String serialized = null;

            try {
                serialized = status.serialize();
            } catch (IOException e) {
                System.err.println("Error: Unable to compose day status");
                e.printStackTrace();
                myAgent.doDelete();
                return;
            }

            reply.setContent(serialized);

            myAgent.send(reply);
        }

        else {
            block();
        }

    }
}
