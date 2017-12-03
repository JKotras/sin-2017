package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.Status.TemperatureStatus;
import sin.sin2017.project.agents.BlindAgent;
import sin.sin2017.project.agents.TemperatureAgent;
import sin.sin2017.project.domoticz.readJson;

import java.io.IOException;

public class TemperatureStatusInformation extends CyclicBehaviour {
    @Override
    public void action() {
        TemperatureAgent agent = (TemperatureAgent) myAgent;
        try{
            readJson readJson = new readJson();
            String temp = readJson.getTemperature(Integer.parseInt(Constants.TEMPERATURE_SENSOR_ID),2);
            agent.getTemperatureStatus().temp = Integer.parseInt(temp);
            System.out.println(temp);
        }catch (Exception e){
            System.err.println("Error in read status");
            agent.doDelete();
        }

        //set it into lights statuses

        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {

            if(msg.getLanguage() != Constants.NON_REPLY) {
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                TemperatureStatus status = agent.getTemperatureStatus();
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
