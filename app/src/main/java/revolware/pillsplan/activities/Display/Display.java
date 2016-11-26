package revolware.pillsplan.activities.Display;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import revolware.pillsplan.R;
import revolware.pillsplan.activities.MainActivity.MainActivity;

/**
 * Created by Jakub on 25.02.2016.
 */
public class Display extends AppCompatActivity  {
    public EditText editText,editText2,editText3,editText4,editText5;
    TextView tw1;
    AlarmManager alarm_manager;
    TimePicker alarm_time_picker;
    Context context;
    int hour,minute;
    String sMinute;

    public TextView textView;
    public Button save, load;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ aTutorial";
    public final static String MESSAGE_KEY = "revolware.pillsplan.message_key";
    public final static String MESSAGE_KEYS = "revolware.pillsplans.message_key";
    Intent my_Intent;
    Intent intent;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        //this.context = context;
        intent = new Intent(Display.this, MainActivity.class);
        //intialize our alarmManager

        //create an instance of calendar
      //  calendar = Calendar.getInstance();


        //creating intent for alarm receiver class
         //my_Intent = new Intent(Display.this,Alarm_Receiver.class);


        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        tw1 = (TextView) findViewById(R.id.textView);


    }


    public void buttonAdd(View v) {
            String message ="Medicine: \t\t\t" + editText.getText().toString() + "\n" +
                    "Number of Pills: \t" + editText2.getText().toString()+ " Pill/s" + "\n" +
                    "Beginning Date: \t" + editText3.getText().toString() + "\n" +
                    "Frequency: \t\t\t"+ editText4.getText().toString() + " hours" + "\n" +
                    "Doctors Name: \t\t" +"Dr. " + editText5.getText().toString() + "\n" ;

            String info = editText.getText().toString();
            String info2 = editText2.getText().toString();
            String info3 = editText3.getText().toString();
            String info4 = editText4.getText().toString();
            String info5 = editText5.getText().toString();


            if((editText.getText().toString()).equals("")  ||
                    (editText2.getText().toString()).equals("") ||
                    (editText3.getText().toString()).equals("") ||
                    (editText4.getText().toString()).equals("") ||
                    (editText5.getText().toString()).equals(""))
            {
                Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "alarm is set to : " + hour + ":" + sMinute, Toast.LENGTH_LONG).show();
                Intent i = new Intent(Display.this, MainActivity.class);
                // i.putExtra("data", message);
                i.putExtra("info", info);
                i.putExtra("info2", info2);
                i.putExtra("info3", info3);
                i.putExtra("info4", info4);
                i.putExtra("info5", info5);
                i.putExtra("key", "false");

                startActivity(i);
                finish();
            }
        }
        public void onBackPressed(){
        Intent intent = new Intent(Display.this, MainActivity.class);
            intent.putExtra("key", "true");
            startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


