package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Status.LightsStatus;
import sin.sin2017.project.agents.LightAgent;

import java.io.IOException;

public class LightStatusInfomations extends CyclicBehaviour {

    @Override
    public void action() {
        LightAgent lightAgent = (LightAgent) myAgent;
        //TODO get status from domoticz
        //set it into lights statuses

        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {

            ACLMessage reply = msg.createReply();

            reply.setPerformative(ACLMessage.INFORM);

            LightsStatus status = lightAgent.getLightsStatus();
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
