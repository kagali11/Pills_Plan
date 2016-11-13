package revolware.pillsplan.activities.PillsInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import revolware.pillsplan.R;

public class PillsHistory extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pills_history);

        Intent getInfo = getIntent();
        //String GetMedicineHistory zachytava mena recent liekov
        //// TODO: 11/13/16 vytvorit subor pilpHistory ktory bude ukladat nazvy liekov 
        String [] sMedName = getInfo.getStringExtra("getMedicineNamehistory").split("#"); //// TODO: 11/13/16 do tohto pola Stringov by mali ist data zo subora(ktory som este nestihol nakodit :P)


        LinearLayout lL = (LinearLayout) findViewById(R.id.activity_pills_history);
        lL.setOrientation(LinearLayout.VERTICAL);

      for(int i = 0; i < sMedName.length; i++)
      {


        TextView MedName = new TextView(this);

        MedName.setText(sMedName[i]);


        lL.addView(MedName);
        }
    }
}
