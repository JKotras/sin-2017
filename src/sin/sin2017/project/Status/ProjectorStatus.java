package sin.sin2017.project.Status;

import sin.sin2017.project.Constants;

import java.io.IOException;
import java.io.Serializable;

public class ProjectorStatus implements Serializable {
    /** 0 - 100 */
    public String status = Constants.LIGHT_TURN_OFF;

    public static ProjectorStatus deserialize(String s) throws IOException, ClassNotFoundException {
        ProjectorStatus projectorStatus = new ProjectorStatus();
        projectorStatus.status = s;
        return projectorStatus;
    }

    public String serialize() throws IOException {
        return this.status;
    }
}
