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


/*
    LIST OF USED SENSORS/SWITCHES
*   idx     name
*   2       PC lab 1 - lights
*   13      sunblind
*   20      light - blackboard
*   21      light - middle
*   22      light - window
*   36      projector
*   24      outside temperature
*   32      motion sensor
*   27      text field of simulation time
**/



public class readJson{

    String httpPort = "http://127.0.0.1:8080";

    // only for testing purposes
    public readJson() throws Exception{

        // only names + idx
        // String flusha = readUrl("http://127.0.0.1:8080/json.htm?type=command&param=getlightswitches");

        // full data
        String flusha = readUrl("http://127.0.0.1:8080/json.htm?type=devices&filter=light&used=true&order=Name");

        Gson gson = new Gson();
        Response response = gson.fromJson(flusha, Response.class);

        //System.out.print("==1== RESPONSE: " + response + "\n");

//        for (Item i : response.result) {
//            System.out.println("result: " + i.Name + " + " + i.idx + "\n");
//
//            if(i.idx == 20){
//                //return i.Status;
//                //System.out.println("STATUS: " + i.Status + "\n");
//            }
//
//        }

       /* System.out.println("--------------- \n");

        System.out.println("status, title: " + response.status + " + " + response.title + "\n");
        System.out.println("sunset, sunrise: " + response.Sunset + " + " + response.Sunrise + "\n");*/

	}


	/*
        targetIDX = idx of the device, whose state we want to know
        Can be used for: lights, projector, motion sensor
	 */
	public String getSwitchState(int targetIDX) throws Exception{

	    String state = null;
        // full data
        String flusha = readUrl(httpPort + "/json.htm?type=devices&filter=light&used=true&order=Name");

        Gson gson = new Gson();
        Response response = gson.fromJson(flusha, Response.class);

        for (Item i : response.result) {

            if(i.idx == targetIDX){
                state = i.Status;
            }
        }

        return state;
    }


    /*
        Can be used for: sunblind, light level
        target IDX = idx of the device, whose state we want to know
        return: current percentage (integer)
                0 = Off
                +10 = each additional entry
     */
    public int getPercentage(int targetIDX) throws Exception{
        String str = null;
        int percentage = 0;

        String flusha = readUrl(httpPort + "/json.htm?type=devices&filter=light&used=true&order=Name");

        Gson gson = new Gson();
        Response response = gson.fromJson(flusha, Response.class);

        for (Item i : response.result) {

            if(i.idx == targetIDX){
                str = i.Status;
            }
        }

        str = str.replaceAll("\\D+","");
        percentage = Integer.parseInt(str);

        return percentage;


    }




    /*
        targetIDX = idx of a device, whose state we want to know
        whichStatus = which data do you need:   1 - temperature
                                                2 - weather
        Can be used for: outside temperature
     */
    public String getTemperature(int targetIDX, int whichStatus) throws Exception{
        String state = null;

        String flusha = readUrl(httpPort + "/json.htm?type=devices&filter=light&used=true&order=Name");

        Gson gson = new Gson();
        Response response = gson.fromJson(flusha, Response.class);

        for (Item i : response.result) {

            if(i.idx == targetIDX){

                if(whichStatus == 1)
                    state = i.Data;
                if(whichStatus == 2)
                    state = i.ForecastStr;
            }

        }

        return state;
    }



    /*
        target IDX = idx of a group, whose state we want to know
        Can be used for: PC lab 1 - lights
     */
    public String getGroupState(int targetIDX) throws Exception {

        String state = null;

        String flusha = readUrl(httpPort + "/json.htm?type=scenes");

        Gson gson = new Gson();
        Response response = gson.fromJson(flusha, Response.class);

        for (Item i : response.result) {

            if(i.idx == targetIDX){
                state = i.Status;
            }
        }

        return state;
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
        String Status;
        String ForecastStr;
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