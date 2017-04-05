package cs656.cri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResults extends AppCompatActivity {

    private TextView mSearchResults;
    private String results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        results = intent.getStringExtra("results");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        mSearchResults = (TextView) this.findViewById(R.id.searchResults);
        mSearchResults.setText(results);
    }
}
