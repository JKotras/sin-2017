package sin.sin2017.project.domoticz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;


public class ChangeState {

    /*
        json example of turning a switch on/off:
        http://127.0.0.1:8080/json.htm?type=command&param=switchlight&idx=9&switchcmd=Off
        idx = id of the device
        action = switchcmd - can be set 'On' or 'Off' - it's case sensitive
     */
    public void turnSwitch(int idx, String action) throws Exception{


        // TODO : send request to do some action


        // check if the action was successful
        String rUrl = "http://127.0.0.1:8080/json.htm?type=command&param=switchlight&idx=" + idx + "&switchcmd=" + action;
        String checkResult = readUrl(rUrl);

        Gson gson = new Gson();
        Response response = gson.fromJson(checkResult, Response.class);

        System.out.print(response + "\n");

        for (Item i : response.result)
            System.out.println("status: " + i.status + "\n");

    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }


    // what we need from response
    static class Item
    {
        String status;
    }


    static class Response
    {
        String status;
        Item[] result;
    }


}