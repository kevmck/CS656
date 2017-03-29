package cs656.cri;
import java.net.*;
import java.io.*;
import java.lang.*;

import android.view.View;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




/**
 * Created by kevinm on 3/21/17.
 */

/**
public class api {

    public static String main(String[] args) throws Exception
    {
        String apiStream;

        //API request for list of years
        String apiLink = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle?format=json";
        URL yearListLink = new URL(apiLink);
        URLConnection yearList = yearListLink.openConnection();
        BufferedReader yearIn = new BufferedReader(new InputStreamReader(yearList.getInputStream()));

        while ((apiStream = yearIn.readLine()) != null)
            System.out.println(apiStream);
        yearIn.close();

        //API request for list of makes
        apiLink = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle/modelyear/2000?format=json";
        URL makeListLink = new URL(apiLink);
        URLConnection makeList = makeListLink.openConnection();
        BufferedReader makeIn = new BufferedReader(new InputStreamReader(makeList.getInputStream()));

        while ((apiStream = makeIn.readLine()) != null)
            System.out.println(apiStream);
        makeIn.close();

        //API request for list of models
        apiLink = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle/modelyear/2000/make/toyota?format=json";
        URL modelListLink = new URL(apiLink);
        URLConnection modelList = modelListLink.openConnection();
        BufferedReader modelIn = new BufferedReader(new InputStreamReader(modelList.getInputStream()));

        while ((apiStream = modelIn.readLine()) != null)
            System.out.println(apiStream);
        modelIn.close();

        //API request for recalls based on previous three conditions
        apiLink = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle/modelyear/2000/make/toyota/model/corolla?format=json";
        URL recallListLink = new URL(apiLink);
        URLConnection recallList = recallListLink.openConnection();
        BufferedReader recallIn = new BufferedReader(new InputStreamReader(recallList.getInputStream()));

        while ((apiStream = recallIn.readLine()) != null)
            System.out.println(apiStream);
        recallIn.close();

        return apiStream;

        //Note: In the app, these requests would not occur simultaneously; they would wait for an action from the script
        //preceding them (with the exception of year request, as it does not have any variable input).
    }

}
*/






/*
public class api extends AsyncTask<Void, Void, String> {

    private Exception exception;

    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        responseText.setText("");
    }

    protected String doInBackground(Void... urls) {
        String make = spinner2.getText().toString();
        // Do some validation here

        try {
            URL url = new URL(API_URL + "make=" + make);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        responseText.setText(response);
    }
}
*/
