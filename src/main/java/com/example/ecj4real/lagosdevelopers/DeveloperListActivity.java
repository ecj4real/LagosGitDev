package com.example.ecj4real.lagosdevelopers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class DeveloperListActivity extends AppCompatActivity {
    private DeveloperClient client;
    private ListView lvDevelopers;
    private DeveloperAdapter developerAdapter;
    private ProgressBar progress;
    public static final String DEVELOPER_DETAIL_KEY = "book";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_list);
        lvDevelopers = (ListView) findViewById(R.id.lvDevelopers);
        ArrayList<Developer> developersList = new ArrayList<Developer>();
        developerAdapter = new DeveloperAdapter(this, developersList);
        lvDevelopers.setAdapter(developerAdapter);
        progress = (ProgressBar) findViewById(R.id.progress);

        fetchDevelopers();
        setupDeveloperSelectedListener();
    }
    private void fetchDevelopers() {
        progress.setVisibility(ProgressBar.VISIBLE);
        client = new DeveloperClient();
        client.getDevelopers(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    progress.setVisibility(ProgressBar.GONE);
                    JSONArray devs = null;
                    if(response != null) {
                        devs = response.getJSONArray("items");
                        Log.i("response", response.toString());
                        final ArrayList<Developer> developers = Developer.fromJson(devs);
                        developerAdapter.clear();
                        for (Developer developer : developers) {
                            developerAdapter.add(developer);
                        }
                        developerAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progress.setVisibility(ProgressBar.GONE);
                Log.d("Failed: ", ""+statusCode);
                Log.d("Error : ", "" + throwable);
                log.d("failure:", "" + responseString);
            }
        });
    }
    public void setupDeveloperSelectedListener() {
        lvDevelopers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DeveloperListActivity.this, DeveloperDetailActivity.class);
                intent.putExtra(DEVELOPER_DETAIL_KEY, developerAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
