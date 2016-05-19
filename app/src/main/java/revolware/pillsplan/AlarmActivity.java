package revolware.pillsplan;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        cancelButton = (Button) findViewById(R.id.CancelButton);

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
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmActivity.this,MainActivity.class);
                String num = getInformation.getStringExtra("alarmNum");
               String hour =  getInformation.getStringExtra("alarmHour");
               String minute = getInformation.getStringExtra("alarmMinutes");
int Num = Integer.parseInt(num);
                Intent service_intent = new Intent(AlarmActivity.this,RingtonePlayingService.class);
                service_intent.putExtra("play", "0");
                //stop this ringtone service
                AlarmActivity.this.startService(service_intent);



                Intent  my_Intent = new Intent(getApplicationContext(),Alarm_Receiver.class);

                PendingIntent pending_Intent = PendingIntent.getBroadcast(getApplicationContext(), Num, my_Intent, 0);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(pending_Intent);



                startActivity(i);
                finish();
            }
        });

    }

}
