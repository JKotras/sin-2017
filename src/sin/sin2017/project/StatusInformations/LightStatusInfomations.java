package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.LightsStatus;
import sin.sin2017.project.agents.LightAgent;
import sin.sin2017.project.domoticz.readJson;

import java.io.IOException;

public class LightStatusInfomations extends CyclicBehaviour {

    @Override
    public void action() {
        LightAgent lightAgent = (LightAgent) myAgent;
        try{
            readJson readJson = new readJson();
            LightsStatus lightsStatus = lightAgent.getLightsStatus();
            String status;
            status = readJson.getSwitchState(Integer.parseInt(Constants.LIGHT_BLACKBOARD_ID));
            lightsStatus.lightBlackboardStatus = status;
            status = readJson.getSwitchState(Integer.parseInt(Constants.LIGHT_MIDDLE_ID));
            lightsStatus.lightMiddleStatus = status;
            status = readJson.getSwitchState(Integer.parseInt(Constants.LIGHT_WINDOW_ID));
            lightsStatus.lightWindowsStatus = status;
        }catch (Exception e){
            System.err.println("Error in read status");
        }

        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            System.out.println("tady" + msg.getContent());


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
