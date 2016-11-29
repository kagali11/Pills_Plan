package revolware.pillsplan.services.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Jakub on 05.04.2016.
 */
public class RingtonePlayingService extends Service {

    MediaPlayer mp;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId){


        //create an instance of mediaplayer

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mp = MediaPlayer.create(getApplicationContext(),notification);

        if(intent.getStringExtra("play").equals("1")){
            mp.start();
            mp.setLooping(true);

            }
        else if(intent.getStringExtra("play").equals("0")){
            mp.setLooping(false);
            mp.stop();
            }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_LONG).show();
    }

}
