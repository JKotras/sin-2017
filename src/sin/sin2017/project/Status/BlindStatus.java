package sin.sin2017.project.Status;

import jade.core.behaviours.CyclicBehaviour;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BlindStatus implements Serializable {
    /** 0 - 100 */
    public int percentageOfClose = 0;

    public static BlindStatus deserialize(String s) throws IOException, ClassNotFoundException {
        int percentage = Integer.parseInt(s);
        BlindStatus blindStatus = new BlindStatus();
        blindStatus.percentageOfClose = percentage;
        return blindStatus;
    }

    public String serialize() throws IOException {
        return Integer.toString(this.percentageOfClose);
    }
}
