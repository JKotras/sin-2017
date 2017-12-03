package sin.sin2017.project.StatusInformations;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.BlindStatus;
import sin.sin2017.project.Status.ProjectorStatus;
import sin.sin2017.project.agents.BlindAgent;
import sin.sin2017.project.agents.ProjectorAgent;
import sin.sin2017.project.domoticz.readJson;

import java.io.IOException;

public class ProjectorStatusInformation extends CyclicBehaviour {
    @Override
    public void action() {
        ProjectorAgent agent = (ProjectorAgent) myAgent;
        String tempStatus = agent.getProjectorStatus().status;
        try{
            readJson readJson = new readJson();
            String status = readJson.getSwitchState(Integer.parseInt(Constants.PROJECTOR_ID));
            agent.getProjectorStatus().status = status;
            if(status.compareTo(Constants.LIGHT_TURN_OFF) == 0) {
                agent.getProjectorStatus().status = Constants.LIGHT_TURN_OFF;
            }else{
                agent.getProjectorStatus().status = Constants.LIGHT_TURN_ON;
            }
        }catch (Exception e){
            System.err.println("Error in read status");
        }
        if(agent.getProjectorStatus().status.compareTo(tempStatus) != 0){
            agent.infoOthersProjectorChange();
        }
    }
}
