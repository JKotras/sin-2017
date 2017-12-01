package sin.sin2017.project.Status;

import sin.sin2017.project.Constants;

import java.io.*;

public class LightsStatus implements Serializable{
    public String lightBlackboardStatus = Constants.LIGHT_TURN_OFF;
    public String lightMiddleStatus = Constants.LIGHT_TURN_OFF;
    public String lightWindowsStatus = Constants.LIGHT_TURN_OFF;


    public static LightsStatus deserialize(String s) throws IOException, ClassNotFoundException {
        byte[] b = s.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        ObjectInputStream in = new ObjectInputStream(bais);
        LightsStatus lightsStatus = new LightsStatus();
        lightsStatus.lightBlackboardStatus = in.readUTF();
        lightsStatus.lightMiddleStatus = in.readUTF();
        lightsStatus.lightWindowsStatus = in.readUTF();
        in.close();
        return lightsStatus;
    }

    public String serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeUTF(this.lightBlackboardStatus);
        out.writeUTF(this.lightMiddleStatus);
        out.writeUTF(this.lightWindowsStatus);
        out.close();
        return new String(baos.toByteArray());
    }
}
