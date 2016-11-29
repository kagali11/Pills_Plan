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

    MediaPlayer media_song;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId){


        //create an instance of mediaplayer


        if(intent.getStringExtra("play").equals("1")){
            //media_song = MediaPlayer.create(this,R.raw.dear_god);
Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(),notification);
            mp.start();
        //    media_song.start();
            }
        else if(intent.getStringExtra("play").equals("0")){
            //media_song.setLooping(false);



            //media_song.stop();
            }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_LONG).show();
    }

}
