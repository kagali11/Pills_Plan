package revolware.pillsplan.activities.PillsInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import revolware.pillsplan.R;

public class PillsHistory extends AppCompatActivity {

    public String sMedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_history);
/*
        Intent getInfo = getIntent();
        sMedName = getInfo.getStringExtra("pills_info_data1");

        TextView MedName;
        MedName = (TextView) findViewById(R.id.MedName);

        MedName.setText(sMedName);
*/
    }
}
