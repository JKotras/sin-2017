package sin.sin2017.project.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.TimeStatus;
import sin.sin2017.project.agents.behaviours.TimeBehaviour;
import sin.sin2017.project.agents.behaviours.TimeInfoBehaviour;

public class DayAgent extends Agent{

    protected TimeStatus timeStatus = new TimeStatus();

    public String dayPart;

    @Override
    protected void setup() {
        addBehaviour(new TimeBehaviour(this, WorldAgent.TIMESPEED));
        addBehaviour(new TimeInfoBehaviour());
    }

    public TimeStatus getTimeStatus() {
        return timeStatus;
    }

    public void infoOthersProjectorChange(){
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                try {
                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    request.addReceiver(new AID("LightAgent", AID.ISLOCALNAME));
                    request.addReceiver(new AID("BlindAgent", AID.ISLOCALNAME));
                    request.addReceiver(new AID("ProjectorAgent", AID.ISLOCALNAME));
                    request.setContent(timeStatus.serialize());
                    request.setLanguage(Constants.NON_REPLY);
                    myAgent.send(request);
                }catch (Exception e){
                    System.err.println("send message faild");
                }
            }
        });
    }
}
