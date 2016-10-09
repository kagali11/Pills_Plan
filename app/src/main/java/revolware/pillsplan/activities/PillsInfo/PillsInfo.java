package revolware.pillsplan.activities.PillsInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import revolware.pillsplan.R;
import revolware.pillsplan.activities.MainActivity.MainActivity;
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
}
