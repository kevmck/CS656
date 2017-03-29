package cs656.cri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin on 3/26/2017.
 */

public class apiYear {

    public static String main(String[] args) throws Exception {
        String apiStream;

        //API request for list of years
        String apiLink = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle?format=json";
        URL yearListLink = new URL(apiLink);
        URLConnection yearList = yearListLink.openConnection();
        BufferedReader yearIn = new BufferedReader(new InputStreamReader(yearList.getInputStream()));

        while ((apiStream = yearIn.readLine()) != null)
            System.out.println(apiStream);
        yearIn.close();
        return apiStream;
    }
}