package cs656.cri;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button searchLaunch;
    private Button historyLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        searchLaunch = (Button) findViewById(R.id.buttonNewSearch);
        historyLaunch = (Button) findViewById(R.id.buttonHistory);

        searchLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                launchActivity();
            }
        });

        historyLaunch.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view)
           {
               launchActivity2();
           }
        });


        if (ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_CODE = 100;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE);
            System.out.println("No Internet permission");
        } else {
            System.out.println("Has Internet permission");
        }

    }

    private void launchActivity()
    {
        Intent intent = new Intent(this, carSearch.class);
        startActivity(intent);
    }

    private void launchActivity2()
    {
        Intent intent = new Intent(this, search_history.class);
        startActivity(intent);
    }

    private void launchActivity3()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void launchActivity4()
    {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                launchActivity();
                return true;
            case R.id.action_settings1:
                launchActivity2();
                return true;
            case R.id.action_settings2:
                launchActivity3();
                return true;
            case R.id.action_settings3:
                launchActivity4();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void launchCarSearch()
    {
        Intent intent = new Intent(this,carSearch.class);
        startActivity(intent);
    }
}
