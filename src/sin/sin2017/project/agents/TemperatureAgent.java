package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.ProjectorStatus;
import sin.sin2017.project.Status.TemperatureStatus;
import sin.sin2017.project.StatusInformations.ProjectorStatusInformation;
import sin.sin2017.project.StatusInformations.TemperatureStatusInformation;

public class TemperatureAgent extends Agent{
    protected TemperatureStatus temperatureStatus = new TemperatureStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new TemperatureStatusInformation());
    }

    public TemperatureStatus getTemperatureStatus() {
        return temperatureStatus;
    }
}
