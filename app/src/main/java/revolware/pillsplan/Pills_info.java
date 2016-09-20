package revolware.pillsplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created By Dano on 25.6.2016
 */

public class Pills_info extends AppCompatActivity {

    public Button back;
    public Button print;

    public void BackPressed(){
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Pills_info.this, MainActivity.class);
                startActivity(toy);
            }
        });
    }

    public void init2(){
        print = (Button)findViewById(R.id.print);
        print.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toy2 = new Intent(Pills_info.this, WriteDatabase.class);
                startActivity(toy2);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_info);
        BackPressed();
        init2();

        Intent getInfo = getIntent();

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

        String sMedName;
        String sNumOfPills;
        String sBegDate;
        String sFreq;
        String sDocName;

        sMedName = getInfo.getStringExtra("pills_info_data1");
        sNumOfPills =  getInfo.getStringExtra("pills_info_data2");
        sBegDate =  getInfo.getStringExtra("pills_info_data3");
        sFreq =  getInfo.getStringExtra("pills_info_data4");
        sDocName =  getInfo.getStringExtra("pills_info_data5");

        MedName.setText(sMedName);
        NumOfPills.setText(sNumOfPills);
        BegDate.setText(sBegDate);
        Freq.setText(sFreq);
        DocName.setText(sDocName);

    }
}
