package revolware.pillsplan.activities.PillsInfo;

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

public class PillsHistory extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_history);


        String[] sMedName = readTextFileToStringArray("PilpHistory.txt").split("@");


        LinearLayout lL = (LinearLayout) findViewById(R.id.activity_pills_history);
        lL.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < sMedName.length; i++) {

            TextView MedName = new TextView(this);
            MedName.setText(sMedName[i]);



            lL.addView(MedName);
        }
    }

    public boolean fileExistance(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // Reads data from TextFile into one string array
    //-------------------------------------------------------------------------------------------------------------------------
    public String readTextFileToStringArray(String s) {
        String getNames = "";
        if (fileExistance(s)) {
            try {
                FileInputStream fis1 = openFileInput(s); //opens file
                InputStreamReader isr1 = new InputStreamReader(fis1);
                BufferedReader bufferedReader1 = new BufferedReader(isr1);
                String data;

                while ((data = bufferedReader1.readLine()) != null)     //Initializing String Objects for data - AlarmInfo
                {
                    getNames = getNames + data + "@";
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