package sin.sin2017.project.Status;

import java.io.IOException;
import java.io.Serializable;

public class LightLevelStatus implements Serializable {
    /** 0 - 100 */
    public int percentageOfSun = 0;

    public static LightLevelStatus deserialize(String s) throws IOException, ClassNotFoundException {
        int percentage = Integer.parseInt(s);
        LightLevelStatus status = new LightLevelStatus();
        status.percentageOfSun = percentage;
        return status;
    }

    public String serialize() throws IOException {
        return Integer.toString(this.percentageOfSun);
    }
}
