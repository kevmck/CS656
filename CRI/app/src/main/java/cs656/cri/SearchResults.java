package cs656.cri;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.StringReader;
import java.lang.String;
import java.util.ArrayList;
import javax.json.*;
import static cs656.cri.R.id.listView;

public class SearchResults extends AppCompatActivity
{
    private ListView mListView;
    private String results;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent intent = this.getIntent();
        results = intent.getStringExtra("results");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mListView = (ListView) this.findViewById(listView);
        this.registerListener();
        this.setListView();
    }


    private void setListView()
    {
        JsonReader jsonReader = Json.createReader(new StringReader(results));
        JsonArray data = jsonReader.readArray();
        jsonReader.close();
        ArrayList<String> recalls = new ArrayList<>();
        String[] keys = {"Manufacturer", "NHTSACampaignNumber", "ReportReceivedDate", "Component", "Summary", "Conequence", "Remedy", "Notes", "ModelYear", "Make", "Model"};

        for (JsonObject item : data.getValuesAs(JsonObject.class))
        {
            String str = "";
            for (int i = 0; i < keys.length; i++)
            {
                String value = item.getString(keys[i]);
                str += keys[i] + ": " + value + "\r\n\r\n";
            }
            recalls.add(str + "\r\n");
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, recalls);
        mListView.setAdapter(listAdapter);
    }


    private void registerListener(){
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                String o = (String) mListView.getItemAtPosition(position);
                AlertDialog recallInfo = new AlertDialog.Builder(SearchResults.this).create();
                recallInfo.setTitle("Details");
                recallInfo.setMessage(o);

                recallInfo.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface msgDetail, int identify)
                    {
                        msgDetail.dismiss();
                    }
                });

                recallInfo.show();

            }
        });

    }

}