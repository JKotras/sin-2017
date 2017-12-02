package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.TimeStatus;
import sin.sin2017.project.agents.behaviours.TimeBehaviour;
import sin.sin2017.project.agents.behaviours.TimeInfoBehaviour;

public class DayAgent extends Agent{

    protected TimeStatus timeStatus = new TimeStatus();

    public String dayPart;

    @Override
    protected void setup() {
        addBehaviour(new TimeBehaviour());
        addBehaviour(new TimeInfoBehaviour());
    }

    public TimeStatus getTimeStatus() {
        return timeStatus;
    }
}
