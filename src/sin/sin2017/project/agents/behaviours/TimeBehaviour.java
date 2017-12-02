package sin.sin2017.project.agents.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import sin.sin2017.project.Status.TimeStatus;
import sin.sin2017.project.agents.DayAgent;
import sin.sin2017.project.agents.WorldAgent;


import java.util.concurrent.TimeUnit;


public class TimeBehaviour extends CyclicBehaviour{

    @Override
    public void action() {
        try {
            TimeUnit.MICROSECONDS.sleep(WorldAgent.TIMESPEED);
        }catch (Exception e){

        }
        DayAgent dayAgent = (DayAgent) myAgent;
        TimeStatus timeStatus = dayAgent.getTimeStatus();
        timeStatus.addSecond();
    }
}

