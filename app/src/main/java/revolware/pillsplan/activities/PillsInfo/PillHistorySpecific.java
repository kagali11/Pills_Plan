package revolware.pillsplan.activities.PillsInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import revolware.pillsplan.R;

public class PillHistorySpecific extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_history_specific);

        Intent getName = getIntent();
        String name = getName.getStringExtra("medName");

        LinearLayout lL = (LinearLayout) findViewById(R.id.content_pill_history_specific);


        lL.addView(createTextview(name,this));



    }
    //-------------------------------------------------------------------------------------------------------------------------
    // creating textViews
    //-------------------------------------------------------------------------------------------------------------------------

    public TextView createTextview(String text, Context c) {
        TextView MedName = new TextView(c);
        MedName.setText(text);
        //---------------------------------------------------------------------------------------------------------------------
        // Place for FE


        //---------------------------------------------------------------------------------------------------------------------
        return MedName;
    }

}
