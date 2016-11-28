package revolware.pillsplan.activities.AlarmActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import revolware.pillsplan.R;
import revolware.pillsplan.activities.MainActivity.MainActivity;
import revolware.pillsplan.services.alarm.RingtonePlayingService;

public class AlarmActivity extends Activity {

    TextView MedName;
    TextView MedInfo;
    Button okButton;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        MedName = (TextView) findViewById(R.id.textView4);
        MedInfo = (TextView) findViewById(R.id.textView5);
        okButton = (Button) findViewById(R.id.OkButton);


        final Intent getInformation = getIntent();
        MedName.setText(getInformation.getStringExtra("medName"));
        MedInfo.setText("Number of pills left: " + getInformation.getStringExtra("numPills") + "\n" +
                        "frequency: " + getInformation.getStringExtra("freq") + "\n" +
                        "Doctor\'s name: " + getInformation.getStringExtra("docName"));


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmActivity.this, MainActivity.class);
                String num = getInformation.getStringExtra("alarmNum");

                Intent service_intent = new Intent(AlarmActivity.this, RingtonePlayingService.class);
                service_intent.putExtra("play", "0");
                //stop this ringtone service
                AlarmActivity.this.startService(service_intent);


                startActivity(i);
                finish();
            }
        });


    }

}
