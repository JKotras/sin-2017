package sin.sin2017.project.agents;

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import sin.sin2017.project.domoticz.readJson;
import sin.sin2017.project.domoticz.ChangeState;

public class WorldAgent extends jade.core.Agent {

    protected readJson xRead;
    protected ChangeState xState;
    //default 1000 - that mean 1 second = 1000 miliseconds;
    public static long TIMESPEED = 1;
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
            AgentController agent;
            //day simulation
            agent = agentsContainer.createNewAgent("DayAgent", DayAgent.class.getCanonicalName(), null);
            agent.start();
            //lights
            agent = agentsContainer.createNewAgent("LightAgent", LightAgent.class.getCanonicalName(), null);
            agent.start();
            //sunBlind
            agent = agentsContainer.createNewAgent("BlindAgent", BlindAgent.class.getCanonicalName(), null);
            agent.start();
            //projector agent
            agent = agentsContainer.createNewAgent("ProjectorAgent", ProjectorAgent.class.getCanonicalName(), null);
            agent.start();
            //motionSensorAgent
            agent = agentsContainer.createNewAgent("MotionSensorAgent", MotionSensorAgent.class.getCanonicalName(), null);
            agent.start();
            //lightlevelAgent
            agent = agentsContainer.createNewAgent("LightLevelAgent", LightLevelAgent.class.getCanonicalName(), null);
            agent.start();
            //temperature - not fully implemented
//            agent = agentsContainer.createNewAgent("TemperatureAgent", TemperatureAgent.class.getCanonicalName(), null);
//            agent.start();


        }catch (Exception e){
            System.err.println("Agents start faild");
        }

    }

}