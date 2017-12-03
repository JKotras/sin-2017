package sin.sin2017.project.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import sin.sin2017.project.Status.LightsStatus;
import sin.sin2017.project.StatusInformations.LightStatusInfomations;
import sin.sin2017.project.agents.messages.LightMessages;

public class LightAgent extends Agent {
    protected LightsStatus lightsStatus= new LightsStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new LightStatusInfomations());
        addBehaviour(new LightMessages());
    }

    public LightsStatus getLightsStatus() {
        return lightsStatus;
    }

    public void setLightsByOthers(){
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID("LightAgent", AID.ISLOCALNAME));
        this.send(request);
    }
}
