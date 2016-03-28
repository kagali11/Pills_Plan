package revolware.pillsplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;




/**
 * Created by Jakub on 25.02.2016.
 */
public class Display extends Activity {
    public EditText editText,editText2,editText3,editText4,editText5;
    TextView tw1;
    public TextView textView;
    public Button save, load;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ aTutorial";
    public final static String MESSAGE_KEY = "revolware.pillsplan.message_key";
    public final static String MESSAGE_KEYS = "revolware.pillsplans.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        TextView text = (TextView) findViewById(R.id.tView1);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        tw1 = (TextView) findViewById(R.id.textView);


    }

        public void buttonAdd(View v)
        {
            String message ="Medicine: \t\t\t\t\t" + editText.getText().toString() + "\n" +
                    "Number of Pills: \t" + editText2.getText().toString()+ " Pill/s" + "\n" +
                    "Beginning Date: \t" + editText3.getText().toString() + "\n" +
                    "Frequency: \t\t\t\t"+ editText4.getText().toString() + " hours" + "\n" +
                    "Doctors Name: \t\t" +"Dr. " + editText5.getText().toString() + "\n" ;

            String info = editText.getText().toString();
            String info2 = editText2.getText().toString();
            String info3 = editText.getText().toString();
            String info4 = editText2.getText().toString();
            String info5 = editText.getText().toString();


            if((editText.getText().toString()).equals("")  ||
                    (editText2.getText().toString()).equals("") ||
                    (editText3.getText().toString()).equals("") ||
                    (editText4.getText().toString()).equals("") ||
                    (editText5.getText().toString()).equals(""))
            {
                Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Display.this, MainActivity.class);
                i.putExtra("data", message);
                i.putExtra("info", info);
                i.putExtra("info2", info2);
                i.putExtra("info3", info3);
                i.putExtra("info4", info4);
                i.putExtra("info5", info5);
                startActivity(i);
                finish();
            }
        }

}


