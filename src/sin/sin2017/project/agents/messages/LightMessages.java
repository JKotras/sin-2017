package sin.sin2017.project.agents.messages;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.*;
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
            System.out.println("Lights - receive some message from " +msg.getSender().getLocalName());
            if(msg.getSender().getLocalName().compareTo("ProjectorAgent") == 0){
                try {
                    String content = msg.getContent();
                    ProjectorStatus projectorStatus = ProjectorStatus.deserialize(content);
                    lightAgent.projectorStatus = projectorStatus;
                    lightAgent.setLightsByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
            if(msg.getSender().getLocalName().compareTo("MotionSensorAgent") == 0){
                try {
                    String content = msg.getContent();
                    MotionSensorStatus motionSensorStatus = MotionSensorStatus.deserialize(content);
                    lightAgent.motionSensorStatus = motionSensorStatus;
                    lightAgent.setLightsByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
            if(msg.getSender().getLocalName().compareTo("DayAgent") == 0){
                try {
                    String content = msg.getContent();
                    TimeStatus timeStatus = TimeStatus.deserialize(content);
                    lightAgent.timeStatus = timeStatus;
                    lightAgent.setLightsByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
            if(msg.getSender().getLocalName().compareTo("LightLevelAgent") == 0){
                try {
                    String content = msg.getContent();
                    LightLevelStatus lightLevelStatus = LightLevelStatus.deserialize(content);
                    lightAgent.lightLevelStatus = lightLevelStatus;
                    lightAgent.setLightsByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
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
