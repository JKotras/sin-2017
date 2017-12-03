package sin.sin2017.project.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.*;
import sin.sin2017.project.StatusInformations.BlindStatusInformation;
import sin.sin2017.project.StatusInformations.ProjectorStatusInformation;
import sin.sin2017.project.agents.messages.ProjectorMessages;
import sin.sin2017.project.domoticz.ChangeState;

public class ProjectorAgent extends Agent{
    protected ProjectorStatus projectorStatus = new ProjectorStatus();
    public MotionSensorStatus motionSensorStatus;
    public LightLevelStatus lightLevelStatus;
    public TimeStatus timeStatus;
    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new ProjectorStatusInformation());
        addBehaviour(new ProjectorMessages());
    }

    public ProjectorStatus getProjectorStatus() {
        return projectorStatus;
    }

    public void infoOthersProjectorChange(){
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

    public void setProjectorByStatus(){
        if(timeStatus != null) {
            if (timeStatus.getPartOfDay().compareTo(TimeStatus.NIGHT) == 0) {
                try {
                    ChangeState changeState = new ChangeState();
                    changeState.turnSwitch(Integer.parseInt(Constants.PROJECTOR_ID), Constants.LIGHT_TURN_OFF);
                } catch (Exception e) {
                    System.err.println("turn off projector faild");
                }

            }
        }
    }
}
