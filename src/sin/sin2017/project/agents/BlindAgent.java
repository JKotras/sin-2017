package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.Status.LightsStatus;
import sin.sin2017.project.StatusInformations.BlindStatusInformation;
import sin.sin2017.project.StatusInformations.LightStatusInfomations;

public class BlindAgent extends Agent{
    protected BlindStatus blindStatus = new BlindStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new BlindStatusInformation());
    }

    public BlindStatus getBlindStatus() {
        return blindStatus;
    }
}