package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.DayStatus;
import sin.sin2017.project.StatusInformations.DayStatusInformation;
import sin.sin2017.project.agents.behaviours.DaysBehaviour;

public class DayAgent extends Agent{

    protected DayStatus dayStatus = new DayStatus();

    @Override
    protected void setup() {
        System.out.println("Day agent start");
        addBehaviour(new DaysBehaviour());
        addBehaviour(new DayStatusInformation());
    }

    public DayStatus getDayStatus() {
        return dayStatus;
    }
}
