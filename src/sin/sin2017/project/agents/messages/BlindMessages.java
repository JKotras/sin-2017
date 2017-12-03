package sin.sin2017.project.agents.messages;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.*;
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
            System.out.println("SunBlinds - receive some message from " +msg.getSender().getLocalName());
            if(msg.getSender().getLocalName().compareTo("ProjectorAgent") == 0){
                try {
                    String content = msg.getContent();
                    ProjectorStatus projectorStatus = ProjectorStatus.deserialize(content);
                    agent.projectorStatus = projectorStatus;
                    agent.setBlindsByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
            if(msg.getSender().getLocalName().compareTo("LightLevelAgent") == 0){
                try {
                    String content = msg.getContent();
                    LightLevelStatus lightLevelStatus = LightLevelStatus.deserialize(content);
                    agent.lightLevelStatus = lightLevelStatus;
                    agent.setBlindsByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
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
