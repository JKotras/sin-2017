package sin.sin2017.project.agents;

import jade.core.Agent;
import sin.sin2017.project.Status.MotionSensorStatus;
import sin.sin2017.project.StatusInformations.MotionSensorStatusInformation;

public class MotionSensorAgent extends Agent{
    protected MotionSensorStatus motionSensorStatus = new MotionSensorStatus();

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new MotionSensorStatusInformation());
    }

    public MotionSensorStatus getMotionSensorStatus() {
        return motionSensorStatus;
    }
}
