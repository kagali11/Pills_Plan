package revolware.pillsplan;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.TreeMap;


public class MainActivity extends AppCompatActivity {
    int hour, minute;
    String sMinute;
    TextView tw1;
    Intent intent;
    Intent my_Intent;

    SharedPreferences prefs = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // tw1 = (TextView) findViewById(R.id.textView2);

        prefs = getSharedPreferences("com.revolware.pillsplan", MODE_PRIVATE);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textViewTutorHeader = (TextView) findViewById(R.id.textViewTutorHeader); // HEADER FILE
        textViewTutorHeader.setVisibility(View.INVISIBLE);
        TextView textViewTutorText = (TextView) findViewById(R.id.textViewTutorText);  //TUTOR TEXT
        textViewTutorText.setVisibility(View.INVISIBLE);

        intent = getIntent();  // APP INTENT
        String message = "";
        String info, info2, info3, info4, info5;

        if (intent.getStringExtra("key") == null) {
            intent.putExtra("key", "true");
        }


        final TreeMap<Integer, String> list = new TreeMap<Integer, String>();
        final TreeMap<Integer, AlarmInfo> map = new TreeMap<Integer, AlarmInfo>();

        int iCounter = 0;

        if (intent.getStringExtra("key").equals("false")) {
            if (intent.getStringExtra("info") != null) // This signals if there is a mssg if Intent,
            {       // getting Input from second Activity
                info = intent.getStringExtra("info");
                info2 = intent.getStringExtra("info2");
                info3 = intent.getStringExtra("info3");
                info4 = intent.getStringExtra("info4");
                info5 = intent.getStringExtra("info5");
                map.put(iCounter, new AlarmInfo(info, info2, info3, info4, info5)); //adds info into alarm object to map List

                iCounter++;
                String s = null;
                intent.putExtra("info", s);//-----------------------------------------------////////////////

            }
        }
/* }
            if(intent.getStringExtra("data") != null) //signals if theres a msg in intent from second activity
            {       // getting Input from second Activity
                message = intent.getStringExtra("data");
                list.put(iCounter,message); // adds String to list object
                iCounter ++;
                intent.putExtra("data", "" );

            }
*/
        //-------------------------------------------------------------------------------------------------------------------------------
        //!Reading Text File!
        //-------------------------------------------------------------------------------------------------------------------------------
        // if( fileExistance("dfile.txt") )
        if (fileExistance("data1.txt"))//----------------------------------------------------////////////////
        {
            try {
/*                    FileInputStream fis = openFileInput ("dfile.txt");  //opens file
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    String dt;
*/
                FileInputStream fis1 = openFileInput("data1.txt"); //opens file
                InputStreamReader isr1 = new InputStreamReader(fis1);
                BufferedReader bufferedReader1 = new BufferedReader(isr1);
                String data, data2, data3, data4, data5;


  /*                  while((dt = bufferedReader.readLine()) != null)     //Initializing String Objects for TextViews
                    {
                        dt =    dt + "\n" +
                                bufferedReader.readLine()   + "\n" +
                                bufferedReader.readLine()   + "\n" +
                                bufferedReader.readLine()  + "\n" +
                                bufferedReader.readLine()  + "\n";
                        list.put(iCounter,dt);
                        iCounter++;
                    }
*/
                while ((data = bufferedReader1.readLine()) != null)     //Initializing String Objects for data - AlarmInfo
                {

                    data2 = bufferedReader1.readLine();
                    data3 = bufferedReader1.readLine();
                    data4 = bufferedReader1.readLine();
                    data5 = bufferedReader1.readLine();

                    map.put(iCounter, new AlarmInfo(data, data2, data3, data4, data5));//-----------------------------------------/////////
                    iCounter++;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //    TextView textViewTutorHeader = (TextView)findViewById(R.id.textViewTutorHeader);
            textViewTutorHeader.setVisibility(View.VISIBLE);
            //  TextView textViewTutorText = (TextView)findViewById(R.id.textViewTutorText);
            textViewTutorText.setVisibility(View.VISIBLE);

               /* new AlertDialog.Builder(MainActivity.this) /* KEBY SME POTREBOVALI ALERT *
                .setTitle("Tutorial")
                .setMessage("relly?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                     // continue with delete
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show(); */

        }

        //-------------------------------------------------------------------------------------------------------------------------------
        // !Writing into File!
        //-------------------------------------------------------------------------------------------------------------------------------
        try {
//                FileOutputStream fos = openFileOutput("dfile.txt", MODE_PRIVATE);
            FileOutputStream fos1 = openFileOutput("data1.txt", MODE_PRIVATE);

//                for(int i = 0; i < list.size(); i++)
//                   fos.write(list.get(i).getBytes());
//                for(int i = 0; i < map.size(); i++)
            //                   fos1.write(map.get(i).toString().getBytes());
            try {
                FileOutputStream fos = openFileOutput("data1.txt", MODE_PRIVATE);

                for (int i = 0; i < iCounter; i++) {
                    if (map.get(i) != null)
                        fos.write(map.get(i).toString().getBytes());
                }
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//                fos.close();
            fos1.close(); //--------------------------------------------------------------///////////
        } catch (IOException e) {
            e.printStackTrace();
        }

        //-------------------------------------------------------------------------------------------------------------------------------
        // !Createing Layout for actual Pills!
        //-------------------------------------------------------------------------------------------------------------------------------
        // Create a linear layout to add new object as vertical
        final LinearLayout lL = (LinearLayout) findViewById(R.id.AlarmView);
        lL.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < map.size(); i++) {

            final LinearLayout holdLayouts = new LinearLayout(this); //holds horizontally 2 vertical layouts
            holdLayouts.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout linLay = new LinearLayout(this); // layout for text fields
            linLay.setOrientation(LinearLayout.VERTICAL);

            LinearLayout linLay1 = new LinearLayout(this); //layout for textFields
            linLay1.setOrientation(LinearLayout.VERTICAL);

            LinearLayout linLay2 = new LinearLayout(this); //layout for buttons next to textFields
            linLay2.setOrientation(LinearLayout.VERTICAL);

            LinearLayout linLay3 = new LinearLayout(this); //layout for switch
            linLay3.setOrientation(LinearLayout.VERTICAL);

            Switch mySwitch;
            Button myButton;
            myButton = new Button(this);
            myButton.setText("X");

            mySwitch = new Switch(this);
            mySwitch.setChecked(false);

            //-------------------------------------------------------------------------------------------------------------------------------
            // !Createing Layout for actual Pills!
            //-------------------------------------------------------------------------------------------------------------------------------

            // Every time create new object of text view
            TextView medicineView = new TextView(this);
            medicineView.setText("\t" + map.get(i).getMedicine());
            medicineView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
            medicineView.setTextColor(0xFF000000);

            final String data_1 = map.get(i).getMedicine();
            final String data_2 = map.get(i).getNPills();
            final String data_3 = map.get(i).getDate();
            final String data_4 = map.get(i).getFrequency();
            final String data_5 = map.get(i).getName();

            /*TODO*/

//            TextView numPillsView = new TextView(this);
//            numPillsView.setText("\t" + map.get(i).getNPills());
//            numPillsView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            numPillsView.setTextColor(0xFF000000);
//
//            TextView dateView = new TextView(this);
//            dateView.setText("\t" + map.get(i).getDate());
//            dateView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            dateView.setTextColor(0xFF000000);
//
//            TextView freqView = new TextView(this);
//            freqView.setText("\t" + map.get(i).getFrequency());
//            freqView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            freqView.setTextColor(0xFF000000);
//
//            TextView docNameView = new TextView(this);
//            docNameView.setText("\t" + map.get(i).getName());
//            docNameView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            docNameView.setTextColor(0xFF000000);


            //-------------------------------------------------------------------------------------------------------------------------------
            // !Creating Layout for actual Pills!
            //-------------------------------------------------------------------------------------------------------------------------------

            // Every time create new object of text view
            TextView sMedicineView = new TextView(this);
            sMedicineView.setText("Medicine: ");
            sMedicineView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
            sMedicineView.setTextColor(0xFF000000);

            /*TODO*/

//            TextView sNumPillsView = new TextView(this);
//            sNumPillsView.setText("Pills : ");
//            sNumPillsView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            sNumPillsView.setTextColor(0xFF000000);
//
//            TextView sDateView = new TextView(this);
//            sDateView.setText("Date: ");
//            sDateView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            sDateView.setTextColor(0xFF000000);
//
//            TextView sFreqView = new TextView(this);
//            sFreqView.setText("Frequency: ");
//            sFreqView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            sFreqView.setTextColor(0xFF000000);
//
//            TextView sDocNameView = new TextView(this);
//            sDocNameView.setText("DocName: ");
//            sDocNameView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
//            sDocNameView.setTextColor(0xFF000000);

            //-------------------------------------------------------------------------------------------------------------------------------
            // Spinners Actions
            //-------------------------------------------------------------------------------------------------------------------------------


            AlarmManager alarm_manager;
            final Calendar calendar;

            calendar = Calendar.getInstance();


            Spinner spinner = new Spinner(this);
            Spinner spinner1 = new Spinner(this);

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

            //hour spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    //intent.putExtra("timeHour",parent.getItemIdAtPosition(position));
                    calendar.set(calendar.HOUR_OF_DAY, (int) parent.getItemIdAtPosition(position));
                    hour = (int) parent.getItemIdAtPosition(position);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    //intent.putExtra("timeHour","0");
                    calendar.set(calendar.HOUR_OF_DAY, 0);
                    hour = 0;
                }
            });

            //Minute spinner
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    //intent.putExtra("timeMinute", parent.getItemIdAtPosition(position));
                    calendar.set(calendar.MINUTE, (int) parent.getItemIdAtPosition(position));
                    minute = (int) parent.getItemIdAtPosition(position);
                    sMinute = String.valueOf(minute);
                    if (minute < 10) {
                        sMinute = "0" + minute;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    //intent.putExtra("timeMinute", "0");
                    calendar.set(calendar.MINUTE, 0);
                    minute = 0;
                }
            });


            //-------------------------------------------------------------------------------------------------------------------------------
            //
            //-------------------------------------------------------------------------------------------------------------------------------

            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {


                        AlarmManager alarm_manager;
                        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        my_Intent = new Intent(MainActivity.this, Alarm_Receiver.class);

                        PendingIntent pending_Intent;
                        // Restore preferences
                        SharedPreferences settings = getSharedPreferences("numOfAlarms", 0);
                        int num = settings.getInt("value", 0);

                        my_Intent.putExtra("medName", data_1);
                        my_Intent.putExtra("numPills", data_2);
                        my_Intent.putExtra("freq", data_4);
                        my_Intent.putExtra("docName", data_5);
                        my_Intent.putExtra("alarmNum", num);
                        my_Intent.putExtra("alarmHour", calendar.getInstance().getTime().getHours());
                        my_Intent.putExtra("alarmMinutes", calendar.getInstance().getTime().getMinutes());


                        pending_Intent = PendingIntent.getBroadcast(MainActivity.this, num, my_Intent, 0);

                        // We need an Editor object to make preference changes.
                        // All objects are from android.context.Context
                        settings = getSharedPreferences("numOfAlarms", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("value", num + 1);

                        // Commit the edits!
                        editor.commit();


                        //set the alarm manager
                        calendar.set(Calendar.SECOND, 0);
                        alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), calendar.getInstance().getTime().getHours() * 1000 * 60 * 60 + calendar.getInstance().getTime().getMinutes() * 1000 * 60, pending_Intent);
                    }
                }
            });

            //-------------------------------------------------------------------------------------------------------------------------------
            //
            //-------------------------------------------------------------------------------------------------------------------------------


            /*TODO*/
            linLay.addView(medicineView); //right layout
//            linLay.addView(numPillsView);
//            linLay.addView(dateView);
//            linLay.addView(freqView);
//            linLay.addView(docNameView);
            linLay.addView(spinner1);

            linLay1.addView(sMedicineView); //left layout
//            linLay1.addView(sNumPillsView);
//            linLay1.addView(sDateView);
//            linLay1.addView(sFreqView);
//            linLay1.addView(sDocNameView);
            linLay1.addView(spinner);


            linLay2.addView(myButton);
            linLay3.addView(mySwitch);

            holdLayouts.addView(linLay1);
            holdLayouts.addView(linLay);
            holdLayouts.addView(linLay2);
            holdLayouts.addView(linLay3);

            lL.addView(holdLayouts); //TENTO CHCEM ABY BOL KLIKATELNY
            TextView blank = new TextView(this);
            blank.setText("");
            lL.addView(blank);
            lL.setClickable(true); //clickable layout
            lL.setBackgroundColor(Color.LTGRAY);


            /**
             * Pridane Danom 23.8.2016
             */

            /*TODO: treba spravit to, aby sa pouzivatel napriklad 5 krat po sebe dostal na pills_info a raz na Popup!*/

            //ked kliknem na text medicine, co je lL layout, tak ma to hodi z mainActivity na pills_info
        lL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int num = rand.nextInt(6);
                if (v == lL && num == 5) {
                    Intent toy2 = new Intent(MainActivity.this, Popup.class);
                    startActivity(toy2);
                }else{
                    Intent toy = new Intent(MainActivity.this, Pills_info.class);
                    toy.putExtra("pills_info_data1", data_1);
                    toy.putExtra("pills_info_data2", data_2);
                    toy.putExtra("pills_info_data3", data_3);
                    toy.putExtra("pills_info_data4", data_4);
                    toy.putExtra("pills_info_data5", data_5);
                    startActivity(toy);
                }
            }
        });

            //TU KONCI TEN KOD


            //-------------------------------------------------------------------------------------------------------------------------------
            // !Button X Activity!
            //-------------------------------------------------------------------------------------------------------------------------------
            final int removeInd = i;
            final int removeCounter = iCounter;
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lL.removeView(holdLayouts);
                    map.remove(removeInd);

                    try {
                        FileOutputStream fos = openFileOutput("data1.txt", MODE_PRIVATE);

                        for (int i = 0; i < removeCounter; i++) {
                            if (map.get(i) != null)
                                fos.write(map.get(i).toString().getBytes());
                        }
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    } //ending oncreate


    //-------------------------------------------------------------------------------------------------------------------------------
    // Finishes this Activity when Back is pressed
    //-------------------------------------------------------------------------------------------------------------------------------
    public void onBackPressed() {
        finish();
    }


    //-------------------------------------------------------------------------------------------------------------------------------
    // Finds out whether file with "fname" exist, and if exist returns true
    //-------------------------------------------------------------------------------------------------------------------------------
    public boolean fileExistance(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*public void onButtonClick(View v) {
        if(v.getId() == R.id.Bdisplay)
        {
            Intent i = new Intent(MainActivity.this, Display.class);
            startActivity(i);

        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Starts Display Activity and finishes this one
        if (id == R.id.Bdisplay) {
            Intent i = new Intent(MainActivity.this, Display.class);
            //i.putExtra("numOfAlarms", Integer.toString(Integer.parseInt(intent.getStringExtra("numOfAlarms") + 1)));
            startActivity(i);
            finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.revolware.com"));
            startActivity(browserIntent);
            return true;
        }

        if (id == R.id.action_feedback) {
            String mailto = "info@revolware.com";
            String subject = "PillsPlan feedback";

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{mailto});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * onResume spusti tutorial ked sa aplikacia prvy krat zapne, inak nie
     * Dano 22.8.2016
     */

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            Intent in = new Intent(MainActivity.this, SplashScreen.class);
            startActivity(in);
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://revolware.pillsplan/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://revolware.pillsplan/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
