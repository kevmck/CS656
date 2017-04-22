package cs656.cri;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.util.ArrayList;

public class carSearch extends AppCompatActivity
{

    private ImageButton fab;
    private Spinner mYearSpinner;
    private Spinner mMakeSpinner;
    private Spinner mModelSpinner;
    private Handler mYearHandler;
    private Handler mMakeHandler;
    private Handler mModelHandler;
    private Handler mResultsHandler;

    private String results = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mYearSpinner = (Spinner) this.findViewById(R.id.spinner);
        mYearSpinner.setSelection(0, false);
        this.mMakeSpinner = (Spinner) this.findViewById(R.id.spinner2);
        mMakeSpinner.setSelection(0, false);
        this.mModelSpinner = (Spinner) this.findViewById(R.id.spinner3);
        mModelSpinner.setSelection(0, false);

        fab = (ImageButton) findViewById(R.id.fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        defineHandlers();
        registerListeners();

        //This is where the new thread for ApiRequest is started; a new thread is necessary for networking activities.
        new Thread(new ApiRequest(mYearHandler)).start();

    }



    private void launchActivity()
    {
        Intent intent = new Intent(this, SearchResults.class);
        intent.putExtra("results", results);
        if(mMakeSpinner.getSelectedItem()!=null && mYearSpinner.getSelectedItem()!=null && mModelSpinner.getSelectedItem()!=null)
            startActivity(intent);
    }


    private void changeSpinner(Spinner spinner, ArrayList<String> arrayList)
    {
        ArrayAdapter<String> yearAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(yearAdapter);
    }

    //This is where all the spinners' data is fetched, and is used to build the link to fetch the correct recall information
    //that all three parameters (Year, Make, Model) have been satisfied.
    //A new thread is also started here; this thread uses the link that was just made to get the correct recall information.
    private void registerListeners(){
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String selected3 = mModelSpinner.getSelectedItem().toString();
                String year = mYearSpinner.getSelectedItem().toString();
                String make = mMakeSpinner.getSelectedItem().toString();
                String modelSelected = "https://web.njit.edu/~klm25/cs656/cache.php?year=" + year + "&make=" + make + "&model=" + selected3;
                new Thread(new ApiRequest(modelSelected,mResultsHandler,"Results")).start();
            }
        });

        //This is where the Year spinner's data is fetched, and is used to build the link to populate the Make spinner.
        //A new thread is also started here; this thread uses the link that was just made to populate the next spinner.
        mYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selected = mYearSpinner.getSelectedItem().toString();
                String yearSelected = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle/modelyear/" + selected + "?format=json";
                new Thread(new ApiRequest(yearSelected,mMakeHandler,"Make")).start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //This is where the Make spinner's data is fetched, and is used to build the link to populate the Model spinner.
        //A new thread is also started here; this thread uses the link that was just made to populate the next spinner.
        mMakeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selected2 = mMakeSpinner.getSelectedItem().toString();
                String year = mYearSpinner.getSelectedItem().toString();
                String makeSelected = "https://one.nhtsa.gov/webapi/api/Recalls/vehicle/modelyear/" + year + "/make/" + selected2 + "?format=json";
                new Thread(new ApiRequest(makeSelected,mModelHandler,"Model")).start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    //Below are four handlers that serve similar functions, but vary based on the context (Year, Make, Model or Recall requests):
    //Based on the data recieved from the ApiRequest class, they process the Message and Bundle, and use the data (in the form of an array)
    //to populate the corresponding spinner.
    //The only exception to this is the mResultHandler, which simply stores the data received; it also launches the search results page.
    //We later display that in a ListView, and also in an Alert when an item from the ListView is selected.
    private void defineHandlers()
    {
        mYearHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //TODO
                super.handleMessage(msg);
                Bundle data = msg.getData();
                ArrayList<String> aList = data.getStringArrayList("result");
                changeSpinner(mYearSpinner, aList);
                System.out.println("Message is received!");
            }
        };

        mMakeHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //TODO
                super.handleMessage(msg);
                Bundle data = msg.getData();
                ArrayList<String> aList = data.getStringArrayList("result");
                changeSpinner(mMakeSpinner, aList);
                System.out.println("Message is received!");
            }
        };

        mModelHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //TODO
                super.handleMessage(msg);
                Bundle data = msg.getData();
                ArrayList<String> aList = data.getStringArrayList("result");
                changeSpinner(mModelSpinner, aList);
                System.out.println("Message is received!");
            }
        };

        mResultsHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //TODO
                super.handleMessage(msg);
                Bundle data = msg.getData();
                results = data.getString("result");
                System.out.println(results);
                launchActivity();
            }
        };

    }

}
