import java.net.*;
import java.io.*;

public class APIInterface
{
   public static void main(String[] args) throws Exception
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
         
      //Note: In the app, these requests would not occur simultaneously; they would wait for an action from the script
      //preceding them (with the exception of year request, as it does not have any variable input).
   }

}