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
import android.widget.TextView;
import android.view.View;

import android.widget.EditText;

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
            public void onClick(View view)
            {
                // "spinner2" & "spinner3" are the user inputted text boxes
                EditText input1 = (EditText) findViewById(R.id.spinner2); // "make" user input
                EditText input2 = (EditText) findViewById(R.id.spinner3); // "model" user input

                String make = input1.getText().toString();
                String model = input2.getText().toString();

                // user input validation
                if(make.length()==0)
                {
                    input1.requestFocus();
                    input1.setError("Cannot Leave Empty Fields");
                }
                else if(!make.matches("[a-zA-Z ]+"))
                {
                    input1.requestFocus();
                    input1.setError("Fields Must Contain Alphabetic Characters");
                }
                else if(model.length()==0)
                {
                    input2.requestFocus();
                    input2.setError("Cannot Leave Empty Fields");
                }
                else if(!model.matches("[a-zA-Z ]+"))
                {
                    input2.requestFocus();
                    input2.setError("Fields Must Contain Alphabetic Characters");
                }
                // end user input validation



                launchActivity();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void launchActivity()
    {
        Intent intent = new Intent(this, SearchResults.class);
        startActivity(intent);
    }
}
