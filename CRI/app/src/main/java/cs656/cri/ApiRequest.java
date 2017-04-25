package cs656.cri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javax.json.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class ApiRequest implements Runnable
{
    URL apiLink = null;
    Handler h;

    //The String variable requestType is used here to make the code aware of the type of request that is being handled.
    String requestType = "ModelYear";

    String results = "";
    ArrayList<String> dataArrayList = new ArrayList<String>();


    public ApiRequest (String url, Handler h, String requestType)
    {
        this.h = h;
        try
        {
            this.apiLink = new URL(url);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        this.requestType = requestType;
    }


    public ApiRequest (Handler h){
        this.h = h;
        try
        {
            //This is the only link that is hard-coded; this is because the app will always perform a request for a list of years.
            //All other links (which can be found in the carSearch.java file) are built based on user input.
            apiLink = new URL("https://one.nhtsa.gov/webapi/api/Recalls/vehicle?format=json");
        }
        catch (MalformedURLException e)
        {
            System.out.println('0');
        }
    }


    //Section below processes each request (opens the links, saves the returned contents, and decodes the JSON)
    public void api()
    {
        //The following creates a URLConnection object, which is necessary to complete an HTTP request.
        //A BufferedReader is also created in order to store the data stream received from the HTTP request.
        //Both the  URLConnection & BufferedReader are surrounded by a try/catch block because it is required in Android Studio.
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

        //The following code uses the data stored in the variable "in" above (set by the BufferReader), which in our case
        //is in JSON format, and decodes the JSON using the javax.json library (not included with Android Studio by default).
        //The raw string data is transformed into a JSON object, which the javax.json library can then process by their key/value pairings.
        //As with the previous section, the try/catch block is required only by Android Studio.
        String inputLine;

        try
        {
            while ((inputLine = in.readLine()) != null)
            {
                //Steps to read the JSON, make it into a JsonObject and turn it into an array.
                JsonReader jsonReader = Json.createReader(new StringReader(inputLine));
                JsonObject jsonObj = jsonReader.readObject();
                JsonArray list = jsonObj.getJsonArray("Results");

                if(!requestType.equals("Results"))
                {
                    if(list==null)
                    {
                        System.out.println(requestType);
                    }
                    for (JsonObject year : list.getValuesAs(JsonObject.class))
                    {
                        //The following if statement is used specifically to remove the '9999' option from the Year spinner.
                        if (!year.getString(requestType).equals("9999"))
                            dataArrayList.add(year.getString(requestType));
                    }
                }
                else
                {

                    results = list.toString();
                }

            }
        }
        catch(IOException e)
        {
            System.out.println("IOException");
        }

        //Closes the BufferReader.
        try
        {
            in.close();
        }
        catch (IOException e2)
        {
            System.out.println("4");
        }

    }

    //Since the class apiRequest is set as Runnable, we can start a thread by using the run method.
    //It is necessary to run networking tasks in a separate thread, as Android Studio will not build the project.
    //It is also a good habit to not overload the MainActivity with too many processes (esp. networking).
    public void run()
    {
        this.api();
        Message msg = new Message();

        //Bundle is used here to "send" data (arrays in this case) from the car search activity to this class.
        //The arrays are encapsulated in the Message 'msg' variable, and are placed in a bundle.
        //In the car search activity, the bundle and messages are handled and read, and the data used in the class.
        Bundle data = new Bundle();
        if(!requestType.equals("Results"))
            data.putStringArrayList("result", dataArrayList);
        else
            data.putString("result", results);
        msg.setData(data);
        if(h != null)
            h.sendMessage(msg);
    }
}
