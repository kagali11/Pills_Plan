package revolware.pillsplan.services.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import revolware.pillsplan.activities.AlarmActivity.AlarmActivity;

/**
 * Created by Jakub on 01.04.2016.
 */
public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();


        //create an Intent to the Ringtone Service
        Intent service_intent = new Intent(context,RingtonePlayingService.class);
        //start this ringtone service
        service_intent.putExtra("play","1");
        context.startService(service_intent);

        Intent showAlarmActivity = new Intent(context.getApplicationContext(),AlarmActivity.class);
        showAlarmActivity.putExtra("medName",intent.getStringExtra("medName"));
        showAlarmActivity.putExtra("numPills", intent.getStringExtra("numPills"));
        showAlarmActivity.putExtra("freq",intent.getStringExtra("freq"));
        showAlarmActivity.putExtra("docName",intent.getStringExtra("docName"));
        showAlarmActivity.putExtra("alarmNum",intent.getStringExtra("alarmNum"));
        showAlarmActivity.putExtra("alarmHour",intent.getStringExtra("alarmHour"));
        showAlarmActivity.putExtra("alarmMinutes",intent.getStringExtra("alarmMinutes"));

        showAlarmActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(showAlarmActivity);

    }
}
