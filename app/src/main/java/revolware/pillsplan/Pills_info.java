package revolware.pillsplan;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.TreeMap;

public class Pills_info extends AppCompatActivity {

    public Button back;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_info);
        BackPressed();

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

    }
}
