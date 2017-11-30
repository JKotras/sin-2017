package sin.sin2017.project.Status;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sin.sin2017.project.agents.WorldAgent;
import sin.sin2017.project.agents.behaviours.DaysBehaviour;

import java.io.*;

public class DayStatus implements Serializable {
    private static final long serialVersionUID = 1229926952785698818L;

    public String dayPart = DaysBehaviour.STATE_MORNING;

    /**
     * Deserializace
     */
    public static DayStatus deserialize(String s) throws IOException, ClassNotFoundException {
        DayStatus dayStatus = new DayStatus();
        dayStatus.dayPart = s;
        return dayStatus;
    }

    /**
     * Serializace
     */
    public String serialize() throws IOException {
        return new String(this.dayPart);
    }
}
