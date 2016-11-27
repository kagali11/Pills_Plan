package revolware.pillsplan.activities.PillsInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appdatasearch.GetRecentContextCall;

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
import revolware.pillsplan.activities.MainActivity.MainActivity;
import revolware.pillsplan.activities.Tutorial.Tutorial;
import revolware.pillsplan.database.Write_Database;

/**
 * Created By Dano on 25.6.2016
 */

public class PillsInfo extends AppCompatActivity {

    public Button back;
    public Button print;
    public String sMedName;

    public void BackPressed(){
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(PillsInfo.this, MainActivity.class);
                startActivity(toy);
            }
        });
    }

    public void init2(){

        print = (Button)findViewById(R.id.print);
        print.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toy2 = new Intent(PillsInfo.this, Write_Database.class);
                toy2.putExtra("medicine", sMedName);
                startActivity(toy2);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_info);
        BackPressed();

        Intent getInfo = getIntent();
        sMedName = getInfo.getStringExtra("pills_info_data1");

        new getAllMedicine().execute(sMedName);

        init2();

        TextView MedName;
        TextView NumOfPills;
        TextView BegDate;
        TextView Freq;
        TextView DocName;

        MedName = (TextView) findViewById(R.id.MedName);
        NumOfPills = (TextView) findViewById(R.id.NumOfPills);
        BegDate = (TextView) findViewById(R.id.BegDate);
        Freq = (TextView) findViewById(R.id.Freq);
        DocName = (TextView) findViewById(R.id.DocName);


        String sNumOfPills;
        String sBegDate;
        String sFreq;
        String sDocName;

        sNumOfPills =  getInfo.getStringExtra("pills_info_data2");
        sBegDate =  getInfo.getStringExtra("pills_info_data3");
        sFreq =  getInfo.getStringExtra("pills_info_data4");
        sDocName =  getInfo.getStringExtra("pills_info_data5");

        MedName.setText(sMedName);
        NumOfPills.setText("Remaining pills: " + sNumOfPills);
        BegDate.setText("Beginning date: " + sBegDate);
        Freq.setText("Frequency: " + sFreq);
        DocName.setText(sDocName);
    }

    public class getAllMedicine extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://hrabovec.hopto.org:7070/api/database/search?q=" + strings[0]);

            Log.i("inside", strings[0]);

            get.setHeader("Content-type", "application/json");
            get.setHeader("Authorization", "Bearer " + strings[0]);

            HttpResponse response;
            JSONObject responseObject = null;

            try {
                response = httpClient.execute(get);
                StatusLine statusline = response.getStatusLine();
                Log.i("Spotify", "" + statusline.getStatusCode());
                if (statusline.getStatusCode() == HttpStatus.SC_OK) {
                    String jsonResponseString = EntityUtils.toString(response.getEntity());
                    Log.i("spotify returned", jsonResponseString);
                    responseObject = new JSONObject(jsonResponseString);
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
        protected void onPostExecute(JSONObject result) {
            if (result != null){

            }
        }
    }
}
