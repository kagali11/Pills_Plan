package revolware.pillsplan.activities.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import revolware.pillsplan.R;
import revolware.pillsplan.activities.RegisterActivity.RegisterActivity;

public class LoginActivity extends Activity {

    private Button registerBtn;
    private Button doneBtn;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_login);

        registerBtn = (Button) findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(reg);
            }
        });

        doneBtn = (Button) findViewById(R.id.logindone_btn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((EditText)findViewById(R.id.name_edit)).getText().toString();
                password = ((EditText)findViewById(R.id.pass_edit)).getText().toString();
            }
        });


    }
}
