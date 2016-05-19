package revolware.pillsplan;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.security.Provider;
import java.util.List;
import java.util.Map;

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
            media_song = MediaPlayer.create(this,R.raw.dear_god);

            media_song.start();}
        else if(intent.getStringExtra("play").equals("0")){
            //media_song.setLooping(false);



            media_song.stop();}
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_LONG).show();
    }

}
