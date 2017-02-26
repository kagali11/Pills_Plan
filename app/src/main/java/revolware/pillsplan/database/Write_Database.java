package revolware.pillsplan.database;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import revolware.pillsplan.R;

public class Write_Database extends AppCompatActivity {

    TextView info, contradictions, loading;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write__database);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        info = (TextView) findViewById(R.id.textview_moreinfo_info);
        contradictions = (TextView) findViewById(R.id.textview_moreinfo_contradictions);
        loading = (TextView) findViewById(R.id.textview_moreinfo_loading);

        Intent get_info = getIntent();

        new getAllMedicine().execute(get_info.getStringExtra("medicine"));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class getAllMedicine extends AsyncTask<String, JSONArray, JSONArray> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONArray doInBackground(String... strings) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://hrabovec.hopto.org:7070/api/database/search?q=" + strings[0]);

            Log.i("inside", strings[0]);

            get.setHeader("Content-type", "application/json");
            get.setHeader("Authorization", "Bearer " + strings[0]);

            HttpResponse response;
            JSONArray responseObject = null;

            try {
                response = httpClient.execute(get);
                StatusLine statusline = response.getStatusLine();
                Log.i("Spotify", "" + statusline.getStatusCode());
                if (statusline.getStatusCode() == HttpStatus.SC_OK) {
                    String jsonResponseString = EntityUtils.toString(response.getEntity());
                    Log.i("spotify returned", jsonResponseString);
                    responseObject = new JSONArray(jsonResponseString);
                    Log.i("inside", responseObject.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return responseObject;
        }


        @Override
        protected void onPostExecute(JSONArray result) {
            if (result != null && result.length() != 0) {
                try {
                    JSONObject medicine = result.getJSONObject(0);
                    //TODO set contradictions and info textivies to values from medicine, also set their visibility and hide visibility of Loading



                } catch (JSONException e) {
                    e.printStackTrace();
                    loading.setText("Could not find your medicine");
                }
            }else{
                loading.setText("Could not find your medicine");
            }
        }
    }
}
