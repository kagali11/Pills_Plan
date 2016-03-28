package revolware.pillsplan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    TextView tw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // tw1 = (TextView) findViewById(R.id.textView2);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textViewTutorHeader = (TextView)findViewById(R.id.textViewTutorHeader);
        textViewTutorHeader.setVisibility(View.INVISIBLE);
        TextView textViewTutorText = (TextView)findViewById(R.id.textViewTutorText);
        textViewTutorText.setVisibility(View.INVISIBLE);

    }


    @Override
     protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        String message = "";
        String info,info2,info3,info4,info5;

        ArrayList<String> list = new ArrayList<String>();
        ArrayList<AlarmInfo> map = new ArrayList<AlarmInfo>();

        if(intent.getStringExtra("info") != null)
        {       // getting Input from second Activity
            info = intent.getStringExtra("info");
            info2 = intent.getStringExtra("info2");
            info3 = intent.getStringExtra("info3");
            info4 = intent.getStringExtra("info4");
            info5 = intent.getStringExtra("info5");
            AlarmInfo inf = new AlarmInfo(info,info2,info3,info4,info5);

            map.add(inf);
        }

        if(intent.getStringExtra("data") != null)
        {       // getting Input from second Activity
           message = intent.getStringExtra("data");
           list.add(message);
        }

            if( fileExistance("file.txt") )
            {
                try {
                    FileInputStream fis = openFileInput ("file.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    String dt;

                    FileInputStream fis1 = openFileInput ("data.txt");
                    InputStreamReader isr1 = new InputStreamReader(fis1);
                    BufferedReader bufferedReader1 = new BufferedReader(isr1);
                    String data,data2,data3,data4,data5;


                    while((dt = bufferedReader.readLine()) != null)     //Initializing String Objects for TextViews
                    {
                        dt =    dt + "\n" +
                                bufferedReader.readLine()   + "\n" +
                                bufferedReader.readLine()   + "\n" +
                                bufferedReader.readLine()  + "\n" +
                                bufferedReader.readLine()  + "\n";
                        list.add(dt);
                    }

                    while((data = bufferedReader1.readLine()) != null)     //Initializing String Objects for data - AlarmInfo
                    {

                        data2 =        bufferedReader1.readLine();
                         data3 =        bufferedReader1.readLine();
                         data4 =       bufferedReader1.readLine();
                         data5 =        bufferedReader1.readLine();

                        map.add(new AlarmInfo(data,data2,data3,data4,data5));
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else {
                TextView textViewTutorHeader = (TextView)findViewById(R.id.textViewTutorHeader);
                textViewTutorHeader.setVisibility(View.VISIBLE);
                TextView textViewTutorText = (TextView)findViewById(R.id.textViewTutorText);
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

            try {
                 FileOutputStream fos = openFileOutput("file.txt", MODE_PRIVATE);
                FileOutputStream fos1 = openFileOutput("data.txt", MODE_PRIVATE);

                 for(int i = 0; i < list.size(); i++)
                    fos.write(list.get(i).getBytes());
                for(int i = 0; i < map.size(); i++)
                    fos1.write(map.get(i).toString().getBytes());

                 fos.close();
            } catch (IOException e) {
                 e.printStackTrace();
            }





        // Create a linear layout to add new object as vertical
        LinearLayout lL = (LinearLayout) findViewById(R.id.AlarmView);
        lL.setOrientation(LinearLayout.VERTICAL);

        for (int i=0;i < list.size();i++){

            // Every time create new object of text view
            TextView objDonateTextView = new TextView(this);

            objDonateTextView.setText(list.get(i));
            objDonateTextView.setId(i);
            objDonateTextView.setTypeface(null, Typeface.BOLD); /*TODO MH: urobit krajsie zobrazenie*/
            objDonateTextView.setTextColor(0xFF000000);

            lL.addView(objDonateTextView);
        }

    }

    public boolean fileExistance(String fname){
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

        if (id == R.id.Bdisplay) {
            Intent i = new Intent(MainActivity.this, Display.class);
            startActivity(i);
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
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ mailto});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
