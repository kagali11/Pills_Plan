package revolware.pillsplan.activities.PillsInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import revolware.pillsplan.R;

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


        final String[] sMedName = readTextFileToString("PilpHistory.txt").split("@FuckThis@");


        LinearLayout lL = (LinearLayout) findViewById(R.id.activity_pills_history);
        lL.setOrientation(LinearLayout.VERTICAL);


        for (int i = 0; i < sMedName.length; i++)
        {

            LinearLayout linlay = new LinearLayout(this);
            linlay.setOrientation(LinearLayout.VERTICAL);


            String [] getString = sMedName[i].split("@rofl@");
            final String name =  getString[0];

            linlay.addView(createTextview(name, this));

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

    }

    //-------------------------------------------------------------------------------------------------------------------------
    // creating textViews
    //-------------------------------------------------------------------------------------------------------------------------

    public TextView createTextview(String text, Context c) {
        TextView MedName = new TextView(c);
        MedName.setText(text);
        //---------------------------------------------------------------------------------------------------------------------
        // Place for FE


        //---------------------------------------------------------------------------------------------------------------------
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