package sin.sin2017.project.agents.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import sin.sin2017.project.agents.WorldAgent;
import sin.sin2017.project.domoticz.ChangeState;

import java.util.concurrent.TimeUnit;


public class DaysBehaviour extends FSMBehaviour{

    public static final String STATE_MORNING = "A";
    public static final String STATE_AFTERNOON = "B";
    public static final String STATE_EVENING = "C";
    public static final String STATE_NINGT = "D";

    public DaysBehaviour() {
        super();
        registerFirstState(new PartDayBehaviours(), STATE_MORNING);
        registerState(new PartDayBehaviours(), STATE_AFTERNOON);
        registerState(new PartDayBehaviours(), STATE_EVENING);
        registerState(new PartDayBehaviours(), STATE_NINGT);

        registerDefaultTransition(STATE_MORNING, STATE_AFTERNOON);
        registerDefaultTransition(STATE_AFTERNOON, STATE_EVENING);
        registerDefaultTransition(STATE_EVENING, STATE_NINGT);
        registerDefaultTransition(STATE_NINGT, STATE_MORNING);
    }


}

class PartDayBehaviour extends CustomWakerBehaviour{
    private int lastExitValue = -1;


    public PartDayBehaviour(Agent a) {
        super(a, true);
    }

    @Override
    public void onStart() {
        FSMBehaviour fsm = (FSMBehaviour) parent;
        lastExitValue = fsm.getLastExitValue();
    }

    @Override
    public int onEnd() {
        return lastExitValue;
    }
}

class PartDayBehaviours extends OneShotBehaviour{
    @Override
    public void action() {
        try {
            TimeUnit.SECONDS.sleep(WorldAgent.TIMESPEED*6);
        }catch (Exception e){

        }
        System.out.println("part");
    }
}
