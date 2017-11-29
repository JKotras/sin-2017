package sin.sin2017.project.agents;

import sin.sin2017.project.domoticz.readJson;

public class WorldAgent extends jade.core.Agent {

    protected readJson xRead;

    @Override
    protected void setup() {
        System.out.println("Hi, my programmer");	// :D
        try {
            xRead = new readJson();
        }catch (Exception e){
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace().toString());
        }

    }
}