package sin.sin2017.project.Status;

import java.io.IOException;
import java.io.Serializable;

public class TemperatureStatus implements Serializable {
    /** 0 - 100 */
    public int temp = 0;

    public static TemperatureStatus deserialize(String s) throws IOException, ClassNotFoundException {
        int temp = Integer.parseInt(s);
        TemperatureStatus status = new TemperatureStatus();
        status.temp = temp;
        return status;
    }

    public String serialize() throws IOException {
        return Integer.toString(this.temp);
    }
}
