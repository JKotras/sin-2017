package sin.sin2017.project.domoticz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;


/*
    LIST OF USED SENSORS/SWITCHES
*   idx     name
*   2       PC lab 1 - lights
*   13      sunblind
*   20      light - blackboard
*   21      light - middle
*   22      light - window
*   23      projector
*   24      outside temperature
*   26      motion sensor
*   27      text field of simulation time
**/


public class ChangeState {

    String httpPort = "http://127.0.0.1:8080";



    // just for testing purposes
    public ChangeState() throws Exception{
       // String rUrl = httpPort + "/json.htm?type=command&param=switchlight&idx=" + idx + "&switchcmd=" + action;
        String rUrl = httpPort + "/json.htm?type=command&param=switchlight&idx=20&switchcmd=Off";

        System.out.print("URL: " + rUrl + "\n");

        String checkResult = readUrl(rUrl);

        Gson gson = new Gson();
        Response response = gson.fromJson(checkResult, Response.class);

        System.out.print("RESPONSE: " + response + "\n");
        System.out.println("status: " + response.status + "\n");

        if("OK".equals(response.status))
            System.out.print("It's OK \n");
        else
            System.out.print("Error with on/off occurred \n");
    }




    /*
        Using this command, the lights and projector can be turned on/off:
        json.htm?type=command&param=switchlight&idx=IDX&switchcmd=CMD

        IDX = id of the device
        CMD = can be set 'On' or 'Off' - it's case sensitive
     */
    public int turnSwitch(int idx, String action) throws Exception{

        // send request to do some action
        String rUrl = httpPort + "/json.htm?type=command&param=switchlight&idx=" + idx + "&switchcmd=" + action;
        String checkResult = readUrl(rUrl);

        // check if the action was successful
        Gson gson = new Gson();
        Response response = gson.fromJson(checkResult, Response.class);

        if("OK".equals(response.status))
            return 0;
        else
            return 1;
    }




    /*
        There is one command for outside temperature and weather with 4 param:
        /json.htm?type=command&param=udevice&idx=IDX&nvalue=0&svalue=TEMP;BAR;BAR_FOR;ALTITUDE

        IDX = id of the device
        TEMP = Temperature
        BAR = Barometric pressure
        BAR_FOR = Barometer forecast -> 0 = No Info, 1 = Sunny, 2 = Paryly Cloudy
                                        3 = Cloudy, 4 = Rain
        ALTITUDE = Not used at the moment, default 0

     */
    public int setWeather(int idx, int temp, int bar, int bar_for) throws Exception{

        String rUrl = httpPort + "/json.htm?type=command&param=udevice&idx="+ idx +"&nvalue=0&svalue="+ temp +";"+ bar +";"+ bar_for +";0";
        String checkResult = readUrl(rUrl);

        // check if the action was successful
        Gson gson = new Gson();
        Response response = gson.fromJson(checkResult, Response.class);

        if("OK".equals(response.status))
            return 0;
        else
            return 1;
    }



    /*
        With this command, all the lights in lab can be turned on/off:
        /json.htm?type=command&param=switchscene&idx=IDX&switchcmd=CMD

        IDX = id of the device
        CMD = On or Off - it's case sensitive
     */
    public int setGroup(int idx, String cmd) throws Exception{

        String rUrl = httpPort + "/json.htm?type=command&param=switchscene&idx="+ idx +"&switchcmd=" + cmd;
        String checkResult = readUrl(rUrl);

        // check if the action was successful
        Gson gson = new Gson();
        Response response = gson.fromJson(checkResult, Response.class);

        if("OK".equals(response.status))
            return 0;
        else
            return 1;
    }




    /*
        Using this command, the sunblind can be set:
        /json.htm?type=command&param=switchlight&idx=IDX&switchcmd=Set%20Level&level=LEVEL

        IDX = id of the device
        LEVEL =  10  - open
                 20  - close
     */
    public int setSwitch(int idx, int level) throws Exception{

        String rUrl = httpPort + "/json.htm?type=command&param=switchlight&idx="+ idx +"&switchcmd=Set%20Level&level=" + level;
        String checkResult = readUrl(rUrl);

        // check if the action was successful
        Gson gson = new Gson();
        Response response = gson.fromJson(checkResult, Response.class);

        if("OK".equals(response.status))
            return 0;
        else
            return 1;
    }




    /*
        Using this command, the simulation time can be displayed:
        /json.htm?type=command&param=udevice&idx=IDX&nvalue=0&svalue=TEXT

        IDX = id of the device
        TEXT = time you want to display
     */
    public int setText(int idx, String text) throws Exception{

        String rUrl = httpPort + "/json.htm?type=command&param=udevice&idx="+ idx +"&nvalue=0&svalue=" + text;
        String checkResult = readUrl(rUrl);

        // check if the action was successful
        Gson gson = new Gson();
        Response response = gson.fromJson(checkResult, Response.class);

        if("OK".equals(response.status))
            return 0;
        else
            return 1;
    }



    /*
        urlString = url with json
     */
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


    static class Response
    {
        String status;
    }


}