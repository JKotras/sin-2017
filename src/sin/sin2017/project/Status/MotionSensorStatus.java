package sin.sin2017.project.Status;

import java.io.IOException;
import java.io.Serializable;

public class MotionSensorStatus implements Serializable {
    public boolean isThereAnyone = false;

    public static MotionSensorStatus deserialize(String s) throws IOException, ClassNotFoundException {
        boolean bool = Boolean.parseBoolean(s);
        MotionSensorStatus status = new MotionSensorStatus();
        status.isThereAnyone = bool;
        return status;
    }

    public String serialize() throws IOException {
        return Boolean.toString(this.isThereAnyone);
    }
}
