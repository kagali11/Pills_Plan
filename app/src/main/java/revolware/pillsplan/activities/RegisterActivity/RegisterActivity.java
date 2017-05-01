package revolware.pillsplan.activities.RegisterActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import revolware.pillsplan.R;

/**
 * Created by andrej on 1.5.2017.
 */

public class RegisterActivity extends Activity {

    private Button doneBtn;

    private ToggleButton male;
    private ToggleButton female;
    private boolean femaleClicked;
    private boolean maleClicked;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String password2;
    private String birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_register);

        doneBtn = (Button) findViewById(R.id.registerdone_btn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = ((EditText)findViewById(R.id.firstname_text)).getText().toString();
                lastName = ((EditText)findViewById(R.id.lastname_text)).getText().toString();
                email = ((EditText)findViewById(R.id.email_text)).getText().toString();
                password  = ((EditText)findViewById(R.id.pass_text)).getText().toString();
                password2  = ((EditText)findViewById(R.id.pass2_text)).getText().toString();
                birthday  = ((EditText)findViewById(R.id.birthday_date)).getText().toString();
            }
        });


        maleClicked = false;
        male = (ToggleButton) findViewById(R.id.male_tglbtn);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maleClicked == false){
                    maleClicked = true;
                    male.setBackgroundColor( getResources().getColor(R.color.pickedColor));
                    femaleClicked = false;
                    female.setBackgroundColor( getResources().getColor(R.color.textColorPrimary));
                }else{
                    maleClicked = false;
                    male.setBackgroundColor( getResources().getColor(R.color.textColorPrimary));
                }
            }
        });


        femaleClicked = false;
        female = (ToggleButton) findViewById(R.id.female_tglbtn);
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(femaleClicked == false){
                    femaleClicked = true;
                    female.setBackgroundColor( getResources().getColor(R.color.pickedColor));
                    maleClicked = false;
                    male.setBackgroundColor( getResources().getColor(R.color.textColorPrimary));
                }else{
                    femaleClicked = false;
                    female.setBackgroundColor( getResources().getColor(R.color.textColorPrimary));
                }
            }
        });




    }

}
