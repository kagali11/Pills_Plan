package revolware.pillsplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Created by Daniel on 23.08.2016.
 */
public class Popup extends Activity{

    public Button button;
    public Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.6), (int)(height*.2));

        button = (Button)findViewById(R.id.print);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String mailto = "info@revolware.com";
                String subject = "PillsPlan feedback";

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{mailto});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client"));
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Popup.this, MainActivity.class);
                startActivity(toy);
            }
        });

    }
}
