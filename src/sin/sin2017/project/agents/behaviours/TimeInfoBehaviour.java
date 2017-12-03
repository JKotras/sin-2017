package sin.sin2017.project.agents.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.TimeStatus;
import sin.sin2017.project.agents.DayAgent;
import sin.sin2017.project.agents.WorldAgent;
import sin.sin2017.project.domoticz.ChangeState;

import java.util.concurrent.TimeUnit;

public class TimeInfoBehaviour extends CyclicBehaviour{

    @Override
    public void action() {
        DayAgent dayAgent = (DayAgent) myAgent;
        TimeStatus timeStatus = dayAgent.getTimeStatus();
        String time = timeStatus.getTimeLikeString();
        try {
            ChangeState changeState = new ChangeState();
            changeState.setText(Integer.parseInt(Constants.TIME_ELEMENT_ID), time);
        }catch (Exception $e){
            dayAgent.doDelete();
            System.err.println("Fail to write into domoticz");
        }
    }
}
