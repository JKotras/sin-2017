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

	 Gson gson;

    public readJson() throws Exception{

		// TODO Auto-generated method stub
        gson = new Gson();

        String json = readUrl("http://127.0.0.1:8080/json.htm?type=command&param=getlightswitches");

        // Page page = gson.fromJson(json, Page.class);
        Result result = gson.fromJson(json, Result.class);

       // System.out.println(result.termsOfService);

        System.out.println(result.Name);


	}

	/*String sURL = "http://127.0.0.1:8080/json.htm?type=command&param=getlightswitches"; //just a string

    // Connect to the URL using java's native library
    URL url = new URL(sURL);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.connect();

    // Convert to a JSON object to print data
    JsonParser jp = new JsonParser(); //from gson
    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
    JsonObject rootobj = root.getAsJsonObject(); 	//May be an array, may be an object. 
    zipcode = rootobj.get("Type").getAsString(); 	//just grab the zipcode*/


    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }



    static class Result{
        String Name;
        //String termsOfService;
    }


}



/*
public class readJson {

	String sURL = "http://127.0.0.1:8080/json.htm?type=command&param=getlightswitches"; //just a string

    // Connect to the URL using java's native library
    URL url = new URL(sURL);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.connect();

    // Convert to a JSON object to print data
    JsonParser jp = new JsonParser(); //from gson
    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
    JsonObject rootobj = root.getAsJsonObject(); 	//May be an array, may be an object. 
    zipcode = rootobj.get("Type").getAsString(); 	//just grab the zipcode


}

// -------------------------------------------------------------
public class gsonexample {
 
    public gsonexample(){}
 
    public void parseJson(String url){
        try{
            Reader reader = new InputStreamReader(new URL("http://127.0.0.1:8080/json.htm?type=command&param=getlightswitches").openStream()); //Read the json output
            Gson gson = new GsonBuilder().create();
            DataObject obj = gson.fromJson(reader, DataObject.class);
            System.out.println(obj);
        }catch(Exception e){
            System.out.println(e);
        }
    }
 
    private class DataObject{ //This class should match your json object structure
        private int status;
        private String error_message;
        private List<Item> item; // This is for the inner array
        @Override
        public String toString() {
            return status + " - " + error_message+ " (" + item + ")";
        }
    }
 
    private class Item{ //This is the inner array class
        public int ID;
        public String Name;
        @Override
        public String toString() {
            return ID + " - " + Name +"\n";
        }
    }
}


// -------------------------------------------------------------
public class JsonSimpleLibraryExample {

	private static final String jURL = "F:\\nikos7\\Desktop\\filesForExamples\\jsonFile.json";

	public static void main(String[] args) {

		JSONParser jsonParser = new JSONParser();

		try {

			FileReader fileReader = new FileReader(jURL);

			JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

			String siteName = (String) jsonObject.get("Site Name");

			System.out.println("Site Name: " + siteName);

			long members = (long) jsonObject.get("Members");

			System.out.println("Members: " + members);

			String url = (String) jsonObject.get("URL");

			System.out.println("URL: " + url);

			JSONArray names = (JSONArray) jsonObject.get("Names");

			Iterator i = names.iterator();

			while (i.hasNext()) {

				System.out.println(" "+i.next());

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}*/