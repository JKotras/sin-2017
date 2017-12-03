package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import sin.sin2017.project.Constants;
import sin.sin2017.project.agents.BlindAgent;
import sin.sin2017.project.agents.LightAgent;
import sin.sin2017.project.agents.LightLevelAgent;
import sin.sin2017.project.domoticz.readJson;

public class LightLevelStatusInformations extends CyclicBehaviour {
    @Override
    public void action() {
        LightLevelAgent agent = (LightLevelAgent) myAgent;
        int tempPerc = agent.getLightLevelStatus().percentageOfSun;
        try{
            readJson readJson = new readJson();
            int percentage = readJson.getPercentage(Integer.parseInt(Constants.LIGHT_LEVEL_SENSOR_ID));
            agent.getLightLevelStatus().percentageOfSun = percentage;
        }catch (Exception e){
            agent.getLightLevelStatus().percentageOfSun = 0;
            //System.err.println("Error in read status");
        }
        if(tempPerc != agent.getLightLevelStatus().percentageOfSun){
            agent.infoOthersLightLevelChange();
        }

    }
}
