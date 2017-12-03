package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.*;
import sin.sin2017.project.StatusInformations.BlindStatusInformation;
import sin.sin2017.project.StatusInformations.LightStatusInfomations;
import sin.sin2017.project.agents.messages.BlindMessages;

public class BlindAgent extends Agent{
    protected BlindStatus blindStatus = new BlindStatus();
    public ProjectorStatus projectorStatus;
    public LightLevelStatus lightLevelStatus;

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new BlindStatusInformation());
        addBehaviour(new BlindMessages());
    }

    public BlindStatus getBlindStatus() {
        return blindStatus;
    }

    public void setBlindsByStatus(){

    }
}
