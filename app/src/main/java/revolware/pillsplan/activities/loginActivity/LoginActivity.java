package revolware.pillsplan.activities.loginActivity;

import android.app.Activity;
import android.os.Bundle;

import revolware.pillsplan.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_login);


    }
}
