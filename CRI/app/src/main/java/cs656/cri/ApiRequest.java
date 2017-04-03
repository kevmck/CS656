package cs656.cri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javax.json.*;
import java.io.StringReader;

class ApiRequest implements Runnable
{

    public static void api()
    {
        //Test inputs to pull data from the cache/API.
        URL apiLink = null;
        try{
            apiLink= new URL("https://one.nhtsa.gov/webapi/api/Recalls/vehicle?format=json");
        } catch (MalformedURLException e){
            System.out.println('0');
        }
        URLConnection apiRequest = null;
        try {
            apiRequest = apiLink.openConnection();
        } catch (IOException e1){
            System.out.println("1");
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(apiRequest.getInputStream()));
        } catch (IOException e2){
            System.out.println("2");
        }
        String inputLine;
        ArrayList<String> spinnerYear = new ArrayList<String>();

        try {
            while ((inputLine = in.readLine()) != null) {
                JsonReader jsonReader = Json.createReader(new StringReader(inputLine));
                JsonObject jsonObj = jsonReader.readObject();
                JsonArray yearList = jsonObj.getJsonArray("Results");

                for (JsonObject year : yearList.getValuesAs(JsonObject.class)) {
                    spinnerYear.add(year.getString("ModelYear"));
                }

            }
        } catch(IOException e){
            System.out.println("3");
        }

        try {
        in.close();
        } catch (IOException e2){
            System.out.println("4");
        }

        for (int i=1; i < spinnerYear.size(); i++)
        {
            System.out.println(spinnerYear.get(i));
        }

    }

    @Override
    public void run() {
        this.api();
    }
}
