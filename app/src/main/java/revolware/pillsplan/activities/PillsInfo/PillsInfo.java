package revolware.pillsplan.activities.PillsInfo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import revolware.pillsplan.R;
import revolware.pillsplan.database.Write_Database;

import static revolware.pillsplan.R.id.tabHost;

/**
 * Created By Dano on 25.6.2016
 */

public class PillsInfo extends AppCompatActivity {

    public Button print;
    public String sMedName;

    public void init2(){

        print = new Button(this);//findViewById(R.id.print);
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

        Intent getInfo = getIntent();
        sMedName = getInfo.getStringExtra("pills_info_data1");

        new getAllMedicine().execute(sMedName);

        init2();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        SpannableString abTitle = new SpannableString(sMedName);
        abTitle.setSpan(new TypefaceSpan("Roboto-Medium.ttf"), 0, abTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setTitle(abTitle);
        actionBar.setElevation(0);

        TabHost host = (TabHost)findViewById(tabHost);

        host.setup();
        host.getTabWidget().setStripEnabled(false);
        host.setPadding(0, 0, 0, 0);

        TabHost.TabSpec tb1 = host.newTabSpec("tab1");

        LinearLayout lL = (LinearLayout) findViewById(R.id.tab1);
        lL.setOrientation(LinearLayout.VERTICAL);

        LinearLayout numPillsL = new LinearLayout(this);
        numPillsL.setOrientation(LinearLayout.HORIZONTAL);
        numPillsL.setGravity(Gravity.CENTER_HORIZONTAL);
        numPillsL.setBackgroundColor(Color.rgb(233,233,233));


        TextView MedName;
        TextView NumOfPills;
        TextView NumOfPillsS;
        TextView BegDate;
        TextView Freq;
        TextView DocName;

        MedName = new TextView(this);
        NumOfPills = new TextView(this);
        NumOfPillsS = new TextView(this);
        BegDate = new TextView(this);
        Freq = new TextView(this);
        DocName = new TextView(this);

        String sNumOfPills;
        String sBegDate;
        String sFreq;
        String sDocName;

        sNumOfPills =  getInfo.getStringExtra("pills_info_data2");
        sBegDate =  getInfo.getStringExtra("pills_info_data3");
        sFreq =  getInfo.getStringExtra("pills_info_data4");
        sDocName =  getInfo.getStringExtra("pills_info_data5");

        MedName.setText(sMedName);
        NumOfPills.setText(sNumOfPills);
        NumOfPillsS.setText("pills remaining in package");
        BegDate.setText("Beginning date: " + sBegDate);
        Freq.setText("Frequency: " + sFreq);
        DocName.setText(sDocName);

        NumOfPills.setBackgroundColor(Color.rgb(233,233,233));
        NumOfPills.setTextSize(44);
        NumOfPills.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
        NumOfPills.setPadding(0,10,20,20);
        NumOfPillsS.setBackgroundColor(Color.rgb(233,233,233));
        NumOfPillsS.setPadding(20,10,20,20);
        NumOfPillsS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf") );;
        NumOfPillsS.setTextSize(19);

        numPillsL.addView(NumOfPills);
        numPillsL.addView(NumOfPillsS);

        lL.addView(numPillsL);
        lL.addView(BegDate);
        lL.addView(Freq);
        lL.addView(DocName);
        lL.addView(print);

        tb1.setIndicator("OVERVIEW");
        tb1.setContent(R.id.tab1);
        host.addTab(tb1);



        TabHost.TabSpec tb2 = host.newTabSpec("tab2");

        tb2.setIndicator("DETAILS");
        tb2.setContent(R.id.tab2);
        host.addTab(tb2);

        final TabWidget tw = (TabWidget)host.findViewById(android.R.id.tabs);
        for (int i = 0; i < tw.getChildCount(); ++i)
        {
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = (TextView)tabView.findViewById(android.R.id.title);
            tv.setTextSize(17);
            tv.setTextColor(Color.WHITE);
        }

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
