package cs656.cri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javax.json.*;
import java.io.StringReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class ApiRequest implements Runnable {

    URL apiLink = null;
    Handler h;
    String requestType = "ModelYear";
    ArrayList<String> dataArrayList = new ArrayList<String>();


    public ApiRequest (String url, Handler h, String requestType){
        this.h = h;
        try {
            this.apiLink = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.requestType = requestType;
    }


    public ApiRequest (Handler h){
        this.h = h;
        try {
            apiLink = new URL("https://one.nhtsa.gov/webapi/api/Recalls/vehicle?format=json");
        } catch (MalformedURLException e) {
            System.out.println('0');
        }
    }


    public void api()
    {

        URLConnection apiRequest = null;
        try
        {
            apiRequest = apiLink.openConnection();
        }
        catch (IOException e1)
        {
            System.out.println("1");
        }

        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(apiRequest.getInputStream()));
        }
        catch (IOException e2)
        {
            System.out.println("2");
        }

        String inputLine;

        try
        {
            while ((inputLine = in.readLine()) != null)
            {
                JsonReader jsonReader = Json.createReader(new StringReader(inputLine));
                JsonObject jsonObj = jsonReader.readObject();
                JsonArray yearList = jsonObj.getJsonArray("Results");

                for (JsonObject year : yearList.getValuesAs(JsonObject.class))
                {
                    if (!year.getString(requestType).equals("9999"))
                        dataArrayList.add(year.getString(requestType));
                }

            }
        }
        catch(IOException e)
        {
            System.out.println("IOException");
        }

        try
        {
            in.close();
        }
        catch (IOException e2)
        {
            System.out.println("4");
        }

    }


    public void run()
    {
        this.api();
        Message msg = new Message();
        Bundle data = new Bundle();
        data.putString("result","year");
        data.putStringArrayList("result", dataArrayList);
        msg.setData(data);
        if(h != null)
            h.sendMessage(msg);
    }
}