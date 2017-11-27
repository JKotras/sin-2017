package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.ElevatorStatus;

public class ElevatorAgent extends Agent {

    protected ElevatorStatus elevatorStatus = new ElevatorStatus();

    @Override
    protected void setup() {
        super.setup();
    }
}
