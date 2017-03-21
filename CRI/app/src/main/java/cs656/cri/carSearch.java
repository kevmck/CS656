package cs656.cri;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.net.*;
import java.io.*;
import android.widget.TextView;
import android.view.View;

public class carSearch extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String string = new String("succes");
        final TextView textView5 = (TextView)findViewById(R.id.textView5);

       /* backButton = (Button) findViewById(R.id.buttonNewSearch);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                APIInterface api = new APIInterface();
                textView5.setText("Success");
                Log.i("Success", string);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

class APIInterface {
    public void main(String[] args) throws Exception
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
           // return apiStream;


    }
}
