package cs656.cri;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.LogRecord;

public class carSearch extends AppCompatActivity {

    private Button backButton;
    private Spinner mYearSpinner;
    private Spinner mMakeSpinner;
    private Spinner mModelSpinner;

    private Handler mYearHandler;

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
        defineHandlers();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                launchActivity();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.mYearSpinner = (Spinner) this.findViewById(R.id.spinner);
        //ApiRequest.api();
        new Thread(new ApiRequest(mYearHandler)).start();

    }



    private void launchActivity()
    {
        Intent intent = new Intent(this, SearchResults.class);
        startActivity(intent);
    }




    private void changeSpinner(ArrayList<String> arrayList){
        ArrayAdapter<String> yearAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        this.mYearSpinner.setAdapter(yearAdapter);
    }


    private void defineHandlers(){
        mYearHandler = new Handler() {
            @Override
            public void handleMessage(Message msg){
                //TODO
                super.handleMessage(msg);
                Bundle data = msg.getData();
                ArrayList<String> aList = data.getStringArrayList("result");
                changeSpinner(aList);
                System.out.println("Message is received!");
            }
        };
    }

}