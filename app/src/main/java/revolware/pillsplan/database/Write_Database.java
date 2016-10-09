package revolware.pillsplan.database;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import revolware.pillsplan.R;
import revolware.pillsplan.activities.PillsInfo.PillsInfo;
import revolware.pillsplan.models.Medicine;

public class Write_Database extends AppCompatActivity {

    TextView text_name;

    public Button button;

    public void BackPressed(){
        button = (Button)findViewById(R.id.button);
        button.setX(250);
        button.setY(540);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Write_Database.this, PillsInfo.class);
                startActivity(toy);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write__database);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BackPressed();

        text_name = (TextView) findViewById(R.id.textViewDefault);

        Intent get = getIntent();

        DatabaseHandler db = new DatabaseHandler(Write_Database.this);
        SQLiteDatabase database = db.getWritableDatabase();

        if(db.getMedicine(get.getStringExtra("medicine")) == null){
            TextView t1 = new TextView(this);
            t1.setText("Your pill is not in database...");
        }else{
            Medicine medicine = db.getMedicine(get.getStringExtra("medicine"));
            text_name.setText("Name: " + medicine.getNAME() + "\n"
                    + "\nDescription: " + medicine.getDESCRIPTION() + "\n"
                    + "\nState: " + medicine.getSTATE() + "\n"
                    + "\nIndication: " + medicine.getINDICATION() + "\n"
                    + "\nLife: " + medicine.getLIFE() + "\n"
                    + "\nDistribution: " + medicine.getDISTRIBUTION() + "\n"
                    + "\nAbsorption: " + medicine.getABSORPTION() + "\n"
                    + "\nClearence: " + medicine.getCLEARENCE() + "\n" + "\n" + "\n" + "\n" + "\n" );
        }


    }
}
