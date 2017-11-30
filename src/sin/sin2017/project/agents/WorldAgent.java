package sin.sin2017.project.agents;

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import sin.sin2017.project.domoticz.readJson;

public class WorldAgent extends jade.core.Agent {

    protected readJson xRead;
    public static int TIMESPEED = 1;
    protected AgentContainer agentsContainer;

    @Override
    protected void setup() {
        System.out.println("Hi, my programmer");	// :D
        try {
            xRead = new readJson();
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace().toString());
        }
        Profile p = new ProfileImpl();
        agentsContainer = Runtime.instance().createAgentContainer(p);

        try {
            AgentController agent = agentsContainer.createNewAgent("DayAgent", DayAgent.class.getCanonicalName(), null);
            agent.start();
        }catch (Exception e){
            System.err.println("Day agent start faild");
        }

    }

}