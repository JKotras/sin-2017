package sin.sin2017.project.agents.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import sin.sin2017.project.Status.TimeStatus;
import sin.sin2017.project.agents.DayAgent;
import sin.sin2017.project.agents.WorldAgent;


import java.util.concurrent.TimeUnit;


public class TimeBehaviour extends TickerBehaviour{

    public TimeBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    public void onTick() {
        DayAgent dayAgent = (DayAgent) myAgent;
        String partOfDay = dayAgent.getTimeStatus().getPartOfDay();
        TimeStatus timeStatus = dayAgent.getTimeStatus();
        timeStatus.addSecond();

        if(timeStatus.getPartOfDay().compareTo(partOfDay) != 0){
            dayAgent.infoOthersProjectorChange();
        }
    }
}

