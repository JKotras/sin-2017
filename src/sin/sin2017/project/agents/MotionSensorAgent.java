package sin.sin2017.project.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.MotionSensorStatus;
import sin.sin2017.project.StatusInformations.MotionSensorStatusInformation;
import sin.sin2017.project.agents.messages.MotionMessages;

public class MotionSensorAgent extends Agent{
    protected MotionSensorStatus motionSensorStatus = new MotionSensorStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new MotionSensorStatusInformation());
        addBehaviour(new MotionMessages());
    }

    public MotionSensorStatus getMotionSensorStatus() {
        return motionSensorStatus;
    }

    public void infoOthersMotionChange(){
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                try {
                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    request.addReceiver(new AID("LightAgent", AID.ISLOCALNAME));
                    request.setContent(motionSensorStatus.serialize());
                    request.setLanguage(Constants.NON_REPLY);
                    myAgent.send(request);

                }catch (Exception e){
                    System.err.println("send message faild");
                }
            }
        });
    }
}
