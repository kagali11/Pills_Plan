package revolware.pillsplan.activities.AlarmActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import revolware.pillsplan.R;
import revolware.pillsplan.activities.MainActivity.MainActivity;
import revolware.pillsplan.services.alarm.RingtonePlayingService;

public class AlarmActivity extends Activity {

    TextView TimeT;
    TextView MedName;
    TextView PillLeft;
    TextView Freq;
    TextView DrName;
    Button okButton;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.alarm);

        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        TimeT = (TextView) findViewById(R.id.timetext);
        MedName = (TextView) findViewById(R.id.pilltext);
        PillLeft = (TextView) findViewById(R.id.lefttext);
        Freq = (TextView) findViewById(R.id.freqtext);
        DrName = (TextView) findViewById(R.id.doctortext);
        okButton = (Button) findViewById(R.id.okbtn);


        final Intent getInformation = getIntent();
        Calendar c = Calendar.getInstance();
        TimeT.setText(c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
        MedName.setText(getInformation.getStringExtra("medName"));
        PillLeft.setText("Pills left: " + getInformation.getStringExtra("numPills"));
        Freq.setText("Frequency: " + getInformation.getStringExtra("freq") + "/h");
        DrName.setText("Dr\'s name: " + getInformation.getStringExtra("docName"));


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmActivity.this, MainActivity.class);
                String num = getInformation.getStringExtra("alarmNum");

                Intent service_intent = new Intent(AlarmActivity.this, RingtonePlayingService.class);
                getApplicationContext().stopService(service_intent);

                service_intent.putExtra("play","0");
                getApplicationContext().startService(service_intent);
                startActivity(i);
                finish();
            }
        });


    }

}
