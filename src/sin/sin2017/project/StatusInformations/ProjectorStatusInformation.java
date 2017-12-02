package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.Status.ProjectorStatus;
import sin.sin2017.project.agents.BlindAgent;
import sin.sin2017.project.agents.ProjectorAgent;

import java.io.IOException;

public class ProjectorStatusInformation extends CyclicBehaviour {
    @Override
    public void action() {
        ProjectorAgent agent = (ProjectorAgent) myAgent;
        //TODO get status from domoticz
        //set it into lights statuses

        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {

            ACLMessage reply = msg.createReply();

            reply.setPerformative(ACLMessage.INFORM);

            ProjectorStatus status = agent.getProjectorStatus();
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
