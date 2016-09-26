package revolware.pillsplan;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

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
                Intent toy = new Intent(Write_Database.this, Pills_info.class);
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

        DatabaseHandler db = new DatabaseHandler(Write_Database.this);
        SQLiteDatabase database = db.getWritableDatabase();

        Medicine medicine = db.getMedicine("Frovatriptan");
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
