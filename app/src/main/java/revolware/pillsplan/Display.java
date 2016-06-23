package revolware.pillsplan;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.widget.AdapterView.OnItemSelectedListener;




/**
 * Created by Jakub on 25.02.2016.
 */
public class Display extends Activity  {
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
        calendar = Calendar.getInstance();


        //creating intent for alarm receiver class
         my_Intent = new Intent(Display.this,Alarm_Receiver.class);



        TextView text = (TextView) findViewById(R.id.tView1);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        tw1 = (TextView) findViewById(R.id.textView);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hours, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.minutes, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);


        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //intent.putExtra("timeHour",parent.getItemIdAtPosition(position));
                calendar.set(calendar.HOUR_OF_DAY,(int) parent.getItemIdAtPosition(position));
                hour = (int) parent.getItemIdAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                //intent.putExtra("timeHour","0");
                calendar.set(calendar.HOUR_OF_DAY,0);
                hour = 0;
            }
        });

        //Minute spinner
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //intent.putExtra("timeMinute", parent.getItemIdAtPosition(position));
                calendar.set(calendar.MINUTE, (int) parent.getItemIdAtPosition(position));
                minute = (int) parent.getItemIdAtPosition(position);
                sMinute = String.valueOf(minute);
                if (minute < 10)
                {
                    sMinute = "0" + minute;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                //intent.putExtra("timeMinute", "0");
                calendar.set(calendar.MINUTE,0);
                minute = 0;
            }
        });


    }

        public void buttonAdd(View v)
        {
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
              //  i.putExtra("data", message);
                i.putExtra("info", info);
                i.putExtra("info2", info2);
                i.putExtra("info3", info3);
                i.putExtra("info4", info4);
                i.putExtra("info5", info5);
                i.putExtra("key", "false");


                alarm_manager = (AlarmManager) this.getSystemService(ALARM_SERVICE);



                PendingIntent pending_Intent;
                // Restore preferences
                SharedPreferences settings = getSharedPreferences("numOfAlarms", 0);
                int num = settings.getInt("value", 0);


                my_Intent.putExtra("medName",info);
                my_Intent.putExtra("numPills",info2);
                my_Intent.putExtra("freq",info4);
                my_Intent.putExtra("docName",info5);
                my_Intent.putExtra("alarmNum",num);
                my_Intent.putExtra("alarmHour", calendar.getInstance().getTime().getHours());
                my_Intent.putExtra("alarmMinutes", calendar.getInstance().getTime().getMinutes());


                pending_Intent = PendingIntent.getBroadcast(Display.this, num, my_Intent, 0);

                // We need an Editor object to make preference changes.
                // All objects are from android.context.Context
                settings = getSharedPreferences("numOfAlarms", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("value", num + 1);

                // Commit the edits!
                editor.commit();






                //set the alarm manager
                calendar.set(Calendar.SECOND, 0);
                alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*3600 * Integer.parseInt(editText4.getText().toString()), pending_Intent);



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





}


