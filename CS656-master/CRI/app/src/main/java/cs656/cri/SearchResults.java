package cs656.cri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
    }

    // Launching "api.java"
    private void launchActivity() {
        Intent intent = new Intent(this, api.class);
        startActivity(intent);
    }



}
