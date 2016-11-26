package revolware.pillsplan.activities.PillsInfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import revolware.pillsplan.R;

import static revolware.pillsplan.R.id.tabHost;

public class PillsHistory extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_history);
        getSupportActionBar().setTitle("History");


        //TabHost ... PivotBar
        TabHost host = (TabHost)findViewById(tabHost);
        host.setup();
        host.getTabWidget().setStripEnabled(false);
        host.setPadding(0, 0, 0, 0);


        //
        //###########################################
        //     Nastavenie prveho TABU(TAB1)
        //###########################################
        //

        TabHost.TabSpec spec = host.newTabSpec("tab1");


        String[] getsMedName = readTextFileToString("PilpHistory.txt").split("@FuckThis@");
        String MedName = "";
        String []help;

        for(int i = 0; i<getsMedName.length;i++)
        {
            help = getsMedName[i].split("@rofl@");
            MedName = MedName + help[0] + "@rozdel@";
        }
        String[] sMedName = MedName.split("@rozdel@");
        sMedName = new HashSet<String>(Arrays.asList(sMedName)).toArray(new String[0]);


        LinearLayout lL = (LinearLayout) findViewById(R.id.tab1);
        lL.setOrientation(LinearLayout.VERTICAL);


        for (int i = 0; i < sMedName.length; i++) {
            if(sMedName[i].isEmpty()){
                //Pre istotu, niekedy sa objavil prazdny liek ?
                continue;
            }

            LinearLayout linlay = new LinearLayout(this);
            linlay.setOrientation(LinearLayout.VERTICAL);


            //  String [] getString = sMedName[i].split("@rofl@");
            final String name = sMedName[i];


            //#########################
            //  Nastavenie TextView

            Typeface face = Typeface.DEFAULT;

            TextView text = (TextView)createTextview(name, this);
            text.setTextSize(28);
            text.setTypeface(face);
            text.setTextColor(Color.WHITE);
            text.setMinHeight(180);
            text.setMinWidth(600);
            text.setPadding(50, 50, 0, 20);

            GradientDrawable shape =  new GradientDrawable();
            shape.setCornerRadius(16);
            shape.setColor(randomColor());

            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            llp.setMargins(50, 80, 0, 0);

            text.setLayoutParams(llp);
            text.setBackgroundDrawable(shape);

            //
            //#########################

            linlay.addView(text);

            linlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startAct = new Intent(PillsHistory.this, PillHistorySpecific.class);
                    startAct.putExtra("medName", name);
                    startActivity(startAct);
                }
            });


            lL.addView(linlay);
        }

        spec.setContent(R.id.tab1);
        spec.setIndicator("MEDICINES");
        host.addTab(spec);

        //
        //###########################################
        //###########################################
        //



        //Tab 2
        spec = host.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("DAYS");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("tab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("TIMES");
        host.addTab(spec);

        for(int i=0;i<host.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#ffffff"));
        }


    }

    public int randomColor(){
        Random rnd = new Random();
        int color = Color.argb(250, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        return color;
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // creating textViews
    //-------------------------------------------------------------------------------------------------------------------------

    public TextView createTextview(String text, Context c) {
        TextView MedName = new TextView(c);
        MedName.setText(text);
        //---------------------------------------------------------------------------------------------------------------
        // Place for FE
        MedName.setTextSize(40);
        MedName.setTypeface(null, Typeface.BOLD);
        MedName.setTextColor(0xFF0BC273);

        //---------------------------------------------------------------------------------------------------------------
        return MedName;
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // File existance
    //-------------------------------------------------------------------------------------------------------------------------

    public boolean fileExistance(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // compares 2 arrays and returns the fusion of them
    //-------------------------------------------------------------------------------------------------------------------------

    public String[] ArrayFusion(String[] volunteerToBeFused1, String[] volunteerToBeFused2)
    {

        int counter = 0;

        Set<String> set = new HashSet<String>();
        List<String> list = Arrays.asList(volunteerToBeFused1);
        List<String> list2 = Arrays.asList(volunteerToBeFused2);

        set.addAll(list);
        set.addAll(list2);

        // Call addAll as many times as you like

        String Fusion[] = set.toArray(new String[set.size()]);

        return Fusion;
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // Reads data from TextFile into one string array
    //-------------------------------------------------------------------------------------------------------------------------
    public String readTextFileToString(String s) {
        String getNames = "";
        if (fileExistance(s)) {
            try {
                FileInputStream fis1 = openFileInput(s); //opens file
                InputStreamReader isr1 = new InputStreamReader(fis1);
                BufferedReader bufferedReader1 = new BufferedReader(isr1);
                String data;

                while ((data = bufferedReader1.readLine()) != null)     //Initializing String Objects for data - AlarmInfo
                {
                    getNames = getNames + data + "@FuckThis@";
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return getNames;
    }


}