package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.Status.ProjectorStatus;
import sin.sin2017.project.StatusInformations.BlindStatusInformation;
import sin.sin2017.project.StatusInformations.ProjectorStatusInformation;

public class ProjectorAgent extends Agent{
    protected ProjectorStatus projectorStatus = new ProjectorStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new ProjectorStatusInformation());
    }

    public ProjectorStatus getProjectorStatus() {
        return projectorStatus;
    }
}
