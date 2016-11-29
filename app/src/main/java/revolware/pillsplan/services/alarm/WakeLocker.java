package revolware.pillsplan.services.alarm;

import android.content.Context;
import android.os.PowerManager;

import revolware.pillsplan.activities.MainActivity.MainActivity;

/**
 * Created by admin on 11/29/16.
 */

public abstract class WakeLocker {

    private static PowerManager.WakeLock wakeLock;

    public static void acquire(Context ctx) {
        PowerManager pm = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, MainActivity.LAUNCHER_APPS_SERVICE);
        wakeLock.acquire();
    }
    public static void release(){
        if(wakeLock != null) wakeLock.release();wakeLock = null;
    }



}