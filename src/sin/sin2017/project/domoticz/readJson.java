package sin.sin2017.project.domoticz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;

//import sun.management.Agent;

public class readJson{

    public readJson() throws Exception{

        // only names + idx
        // String flusha = readUrl("http://127.0.0.1:8080/json.htm?type=command&param=getlightswitches");

        // full data
        String flusha = readUrl("http://127.0.0.1:8080/json.htm?type=devices&filter=light&used=true&order=Name");

        Gson gson = new Gson();
        Response response = gson.fromJson(flusha, Response.class);

        for (Item i : response.result)
            System.out.println("result: " + i.Name + " + " + i.idx + "\n");

        System.out.println("--------------- \n");

        System.out.println("status, title: " + response.status + " + " + response.title + "\n");
        System.out.println("sunset, sunrise: " + response.Sunset + " + " + response.Sunrise + "\n");

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


    // data from array result: [
    static class Item
    {
        int idx;        // this is necessary for change the sensor status
        String Name;
        String Type;
        String Data;
    }


    static class Response
    {
        int totalCount;

        String status;
        String title;
        String Sunset;
        String Sunrise;

        Item[] result;
    }

}