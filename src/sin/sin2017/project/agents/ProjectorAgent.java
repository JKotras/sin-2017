package sin.sin2017.project.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.Status.ProjectorStatus;
import sin.sin2017.project.StatusInformations.BlindStatusInformation;
import sin.sin2017.project.StatusInformations.ProjectorStatusInformation;
import sin.sin2017.project.agents.messages.ProjectorMessages;

public class ProjectorAgent extends Agent{
    protected ProjectorStatus projectorStatus = new ProjectorStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new ProjectorStatusInformation());
        addBehaviour(new ProjectorMessages());
    }

    public ProjectorStatus getProjectorStatus() {
        return projectorStatus;
    }

    public void infoOthersMotionChange(){
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                try {
                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    request.addReceiver(new AID("LightAgent", AID.ISLOCALNAME));
                    request.addReceiver(new AID("BlindAgent", AID.ISLOCALNAME));
                    request.setContent(projectorStatus.serialize());
                    request.setLanguage(Constants.NON_REPLY);
                    myAgent.send(request);
                }catch (Exception e){
                    System.err.println("send message faild");
                }
            }
        });
    }
}
