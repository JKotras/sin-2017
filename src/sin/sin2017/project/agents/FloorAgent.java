package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Constants;
import sin.sin2017.project.Status.FloorStatus;

public class FloorAgent extends Agent {
    protected int numberOfFloor;
    protected FloorStatus floorStatus;

    @Override
    protected void setup() {
        Object args[] = getArguments();
        if (args.length != 2) {
            System.err.println("Unexpected arguments for AGENT");
            doDelete();
        }else{
            this.numberOfFloor = Integer.valueOf(args[1].toString());
        }
        floorStatus = new FloorStatus(numberOfFloor);
        floorStatus.setNameOfAgent(getAID().getLocalName());

        //and behaviors
        //TODO
        //prichozi lidi do fronty
        //odchozi lidi to vytahu
        //generovani pozadavku na vytahy
    }

    public void enqueue(int direction){
        if(direction == Constants.DOWN){
            this.floorStatus.insertIntoDirectorDown(1);
        }else{
            this.floorStatus.insertIntoDirectorUp(1);
        }

        //TODO make request for elevator
    }

    public void unqueue(int direction){
        if(direction == Constants.DOWN){
            this.floorStatus.insertIntoDirectorDown(1);
        }else{
            this.floorStatus.insertIntoDirectorUp(1);
        }

        //SHOULD INFORM ELVEVQATOR ???
    }
}
