package revolware.pillsplan.activities.PillsInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import revolware.pillsplan.R;

public class PillHistorySpecific extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_history_specific);

        Intent getName = getIntent();
        String name = getName.getStringExtra("medName");

        LinearLayout lL = (LinearLayout) findViewById(R.id.content_pill_history_specific);

        String[] getDate = readTextFileToString("PilpHistory.txt").split("@FuckThis@");
        lL.addView(createTextview(name,this));



        for(int i=0; i < getDate.length;i++){
            if(getDate[i].startsWith(name))
            {
                String[] help = getDate[i].split("@rofl@");
                lL.addView(createTextviewDate(help[1],this));
            }
        }








    }
    //-------------------------------------------------------------------------------------------------------------------------
    // creating textViews
    //-------------------------------------------------------------------------------------------------------------------------

    public TextView createTextview(String text, Context c) {
        TextView MedName = new TextView(c);
        MedName.setText(text);
        //---------------------------------------------------------------------------------------------------------------
        // Place for FE


        //---------------------------------------------------------------------------------------------------------------
        return MedName;
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // creating textViews for Dates
    //-------------------------------------------------------------------------------------------------------------------------

    public TextView createTextviewDate(String text, Context c) {
        TextView MedName = new TextView(c);
        MedName.setText(text);
        //---------------------------------------------------------------------------------------------------------------
        // Place for FE


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
