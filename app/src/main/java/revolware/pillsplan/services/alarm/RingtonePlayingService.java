package revolware.pillsplan.services.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
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

        // Toto stale nefunguje, treba opravit, pada aplikacia...

        if(intent.getStringExtra("play").equals("1")){
            //media_song = MediaPlayer.create(this,R.raw.dear_god);
            if(media_song != null)
                media_song.start();
        }
        else if(intent.getStringExtra("play").equals("0")){
            if(media_song != null)
                media_song.setLooping(false);


            if(media_song != null)
                media_song.stop();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_LONG).show();
    }

}
