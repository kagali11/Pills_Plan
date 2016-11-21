package revolware.pillsplan.activities.loginActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import revolware.pillsplan.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RelativeLayout lL = (RelativeLayout) findViewById(R.id.activity_login);
        lL.addView(createLoginLayout(this,"Name","Password","Login"));


    }

    //-----------------------------------------------------------------------------------------------------
    //creates EditText
    //-----------------------------------------------------------------------------------------------------
    public EditText createEditText(Context c,String hint)
    {
        EditText edit = new EditText(c);
        //--------------------------------------
        //// TODO: 11/21/16 Fe to be done
        edit.setHint(hint);
        return edit;
    }

    //-----------------------------------------------------------------------------------------------------
    //creates Button
    //-----------------------------------------------------------------------------------------------------
    public Button button(Context c,String name)
    {
        Button but = new Button(c);
        //--------------------------------------
        //// TODO: 11/21/16 Fe to be done
        but.setHint("Password");
        return but;
    }
    //-----------------------------------------------------------------------------------------------------
    //creates layout
    //-----------------------------------------------------------------------------------------------------
    public LinearLayout createLoginLayout(Context c,String hint1,String hint2,String butName)
    {
        LinearLayout lL = new LinearLayout(this);
        lL.setOrientation(LinearLayout.VERTICAL);

        lL.addView(createEditText(c,hint1));
        lL.addView(createEditText(c,hint2));
        Button but = button(c,butName);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lL.addView(but);

        return lL;
    }
}
