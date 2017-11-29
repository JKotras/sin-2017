package sin.sin2017.project.agents;

import sin.sin2017.project.domoticz.readJson;

public class WorldAgent extends jade.core.Agent {

    @Override
    protected void setup() {
        System.out.println("Hi, my programmer");	// :D

        readJson xRead = new readJson();

    }
}