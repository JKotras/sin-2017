package sin.sin2017.project.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.LightLevelStatus;

import sin.sin2017.project.StatusInformations.LightLevelStatusInformations;


public class LightLevelAgent extends Agent{
    protected LightLevelStatus lightLevelStatus = new LightLevelStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new LightLevelStatusInformations());
    }

    public LightLevelStatus getLightLevelStatus() {
        return lightLevelStatus;
    }

    public void infoOthersLightLevelChange(){
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                try {
                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    request.addReceiver(new AID("LightAgent", AID.ISLOCALNAME));
                    request.addReceiver(new AID("BlindAgent", AID.ISLOCALNAME));
                    request.addReceiver(new AID("ProjectorAgent", AID.ISLOCALNAME));
                    request.setContent(lightLevelStatus.serialize());
                    request.setLanguage(Constants.NON_REPLY);
                    myAgent.send(request);
                }catch (Exception e){
                    System.err.println("send message faild");
                }
            }
        });
    }
}
