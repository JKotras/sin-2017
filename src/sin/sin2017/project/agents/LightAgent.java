package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.LightsStatus;
import sin.sin2017.project.StatusInformations.LightStatusInfomations;

public class LightAgent extends Agent {
    protected LightsStatus lightsStatus= new LightsStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new LightStatusInfomations());
    }

    public LightsStatus getLightsStatus() {
        return lightsStatus;
    }
}
