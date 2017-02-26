package revolware.pillsplan.activities.PillsInfo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.Switch;
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
        print.setText("More info...");
        print.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
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
        numPillsL.setGravity(Gravity.LEFT);
        numPillsL.setPadding(20,10,0,10);
        numPillsL.setBackgroundColor(Color.rgb(233,233,233));


        LinearLayout lastTaken = new LinearLayout(this);
        lastTaken.setOrientation(LinearLayout.HORIZONTAL);
        lastTaken.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        lastTaken.setPadding(20,10,0,10);

        LinearLayout interval = new LinearLayout(this);
        interval.setOrientation(LinearLayout.VERTICAL);
        interval.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.LEFT);
        interval.setPadding(20,10,0,10);

        LinearLayout doctor = new LinearLayout(this);
        doctor.setOrientation(LinearLayout.HORIZONTAL);
        doctor.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        doctor.setPadding(20,10,0,10);

        LinearLayout color = new LinearLayout(this);
        color.setOrientation(LinearLayout.HORIZONTAL);
        color.setGravity(Gravity.CENTER_VERTICAL);
        color.setPadding(20,10,0,10);

        LinearLayout txtNot = new LinearLayout(this);
        txtNot.setOrientation(LinearLayout.HORIZONTAL);
        txtNot.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        txtNot.setPadding(20,10,0,10);

        LinearLayout phoneNum = new LinearLayout(this);
        phoneNum.setOrientation(LinearLayout.HORIZONTAL);
        phoneNum.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        phoneNum.setPadding(20,10,0,10);

        TextView MedName;
        TextView NumOfPills;
        TextView NumOfPillsS;
        TextView lastTakedS;
        TextView lastTaked;
        TextView Freq;
        TextView FreqS;
        TextView DocName;
        TextView DocNameS;
        TextView ColorS;
        Button ColorB;
        TextView textNotS;
        Switch textNot;

        MedName = new TextView(this);
        NumOfPills = new TextView(this);
        NumOfPillsS = new TextView(this);
        lastTakedS = new TextView(this);
        lastTaked = new TextView(this);
        Freq = new TextView(this);
        FreqS = new TextView(this);
        DocName = new TextView(this);
        DocNameS = new TextView(this);
        ColorS = new TextView(this);
        ColorB = new Button(this);
        textNotS = new TextView(this);
        textNot = new Switch(this);

        String sNumOfPills;
        String slastTaken;
        String sFreq;
        String sDocName;
        String clr;

        sNumOfPills =  getInfo.getStringExtra("pills_info_data2");
        slastTaken =  "Today at 8:15";
        sFreq =  getInfo.getStringExtra("pills_info_data4");
        sDocName =  getInfo.getStringExtra("pills_info_data5");
        clr = getInfo.getStringExtra("pills_info_data6");

        MedName.setText(sMedName);
        NumOfPills.setText(sNumOfPills);
        NumOfPillsS.setText("pills remaining in package");
        lastTaked.setText(slastTaken);
        lastTakedS.setText("Last taken:");
        Freq.setText("Every " + sFreq + " hours");
        FreqS.setText("Interval:");
        DocName.setText(sDocName);
        DocNameS.setText("Doctor:");
        ColorS.setText("Color:");
        textNotS.setText("Text notification:");

        NumOfPills.setBackgroundColor(Color.rgb(233,233,233));
        NumOfPills.setTextSize(44);
        NumOfPills.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
        NumOfPills.setPadding(0,10,20,20);
        NumOfPillsS.setBackgroundColor(Color.rgb(233,233,233));
        NumOfPillsS.setPadding(20,10,20,20);
        NumOfPillsS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf") );
        NumOfPillsS.setTextSize(19);

        lastTaked.setTextSize(19);
        lastTaked.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf") );
        lastTaked.setPadding(0,10,20,20);
        lastTakedS.setPadding(20,10,20,20);
        lastTakedS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
        lastTakedS.setTextSize(21);

        Freq.setTextSize(19);
        Freq.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf") );
        Freq.setPadding(20,10,20,20);
        FreqS.setPadding(20,10,20,20);
        FreqS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
        FreqS.setTextSize(21);

        DocName.setTextSize(19);
        DocName.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf") );
        DocName.setPadding(20,10,20,20);
        DocNameS.setPadding(20,10,20,20);
        DocNameS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
        DocNameS.setTextSize(21);

        ColorS.setPadding(20,10,20,20);
        ColorS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
        ColorS.setTextSize(21);
        ColorS.setGravity(Gravity.LEFT);

        ColorB.setPadding(0,10,10,10);
        ColorB.setClickable(false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END;
        ColorB.setLayoutParams(params);
        ColorB.setBackgroundResource(R.drawable.color_button_rounded);
        GradientDrawable drawable = (GradientDrawable) ColorB.getBackground();
        drawable.setColor(Integer.valueOf(clr));

        textNotS.setPadding(20,10,20,20);
        textNotS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf") );
        textNotS.setTextSize(21);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END;
        textNot.setLayoutParams(params);

        lastTaken.addView(lastTakedS);
        lastTaken.addView(lastTaked);

        numPillsL.addView(NumOfPills);
        numPillsL.addView(NumOfPillsS);

        interval.addView(FreqS);
        interval.addView(Freq);

        doctor.addView(DocNameS);
        doctor.addView(DocName);

        color.addView(ColorS);
        color.addView(ColorB);

        txtNot.addView(textNotS);
        txtNot.addView(textNot);

        lL.addView(numPillsL);
        lL.addView(lastTaken);
        lL.addView(interval);
        lL.addView(doctor);
        lL.addView(color);
        lL.addView(txtNot);
        lL.addView(print);

        tb1.setIndicator("OVERVIEW");
        tb1.setContent(R.id.tab1);
        host.addTab(tb1);


        //DETAILS TAB
        //
        //TabHost.TabSpec tb2 = host.newTabSpec("tab2");

        //tb2.setIndicator("DETAILS");
        //tb2.setContent(R.id.tab2);
        //host.addTab(tb2);

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
