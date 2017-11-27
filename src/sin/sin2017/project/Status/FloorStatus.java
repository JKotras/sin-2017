package sin.sin2017.project.Status;

public class FloorStatus {
    protected int floorNumber;
    protected String nameOfAgent;

    protected int directionUp = 0;
    protected int directionDown = 0;

    public FloorStatus(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void insertIntoDirectorUp(int numberOfRequests){
        directionUp+=numberOfRequests;
    }

    public void removeFromDirectionUp(int numbers){
        directionUp-=numbers;
        if(directionUp < 0){
            directionUp = 0;
        }
    }


    public void insertIntoDirectorDown(int numberOfRequests){
        directionDown+=numberOfRequests;
    }

    public void removeFromDirectionDown(int numbers){
        directionDown-=numbers;
        if(directionDown < 0){
            directionDown = 0;
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getDirectionUpQueueSize() {
        return directionUp;
    }

    public int getDirectionDownQueueSize() {
        return directionDown;
    }

    public String getNameOfAgent() {
        return nameOfAgent;
    }

    public void setNameOfAgent(String nameOfAgent) {
        this.nameOfAgent = nameOfAgent;
    }
}
