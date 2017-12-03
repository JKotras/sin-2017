package sin.sin2017.project.agents.messages;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.LightLevelStatus;
import sin.sin2017.project.Status.MotionSensorStatus;
import sin.sin2017.project.Status.ProjectorStatus;
import sin.sin2017.project.Status.TimeStatus;
import sin.sin2017.project.agents.ProjectorAgent;

import java.io.IOException;

public class ProjectorMessages extends CyclicBehaviour{

    @Override
    public void action() {
        ProjectorAgent agent = (ProjectorAgent) myAgent;
        //replay for requests
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            System.out.println("Projector - receive some message from " +msg.getSender().getLocalName());
            if(msg.getSender().getLocalName().compareTo("MotionSensorAgent") == 0){
                try {
                    String content = msg.getContent();
                    MotionSensorStatus motionSensorStatus = MotionSensorStatus.deserialize(content);
                    agent.motionSensorStatus = motionSensorStatus;
                    agent.setProjectorByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
            if(msg.getSender().getLocalName().compareTo("LightLevelAgent") == 0){
                try {
                    String content = msg.getContent();
                    LightLevelStatus lightLevelStatus = LightLevelStatus.deserialize(content);
                    agent.lightLevelStatus = lightLevelStatus;
                    agent.setProjectorByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
            if(msg.getSender().getLocalName().compareTo("DayAgent") == 0){
                try {
                    String content = msg.getContent();
                    TimeStatus timeStatus = TimeStatus.deserialize(content);
                    agent.timeStatus = timeStatus;
                    agent.setProjectorByStatus();
                }catch (Exception e){
                    System.err.println("parse message faild");
                }
            }
            if(msg.getLanguage() != Constants.NON_REPLY) {
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
}
