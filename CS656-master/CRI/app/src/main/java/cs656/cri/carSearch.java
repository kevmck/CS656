package cs656.cri;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.*;

public class carSearch extends AppCompatActivity {

    private Button backButton;

    EditText spinner2;
    TextView responseText;
    ProgressBar progressBar;
    static final String API_URL = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle/modelyear/2000?format=json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final String string = new String("succes");
        //final TextView textView5 = (TextView)findViewById(R.id.textView5);

       /* backButton = (Button) findViewById(R.id.buttonNewSearch);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/

        responseText = (TextView) findViewById(R.id.responseText);
        spinner2 = (EditText) findViewById(R.id.spinner2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String make = spinner2.getText().toString();
                new api().execute();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    class api extends AsyncTask<Void, Void, String> {

        private Exception exception;

        //@Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseText.setText("");
        }
        //@Override
        protected String doInBackground(Void... urls) {
            //String make = spinner2.getText().toString();

            try {
                URL url = new URL(API_URL + "make="); // + make
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

    private void launchActivity()
    {
        Intent intent = new Intent(this, SearchResults.class);
        startActivity(intent);
    }
}