package revolware.pillsplan;

import android.app.Activity;
import android.content.Context;
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
    public EditText editText;
    public TextView textView;
    public Button save, load;

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ aTutorial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        TextView text = (TextView) findViewById(R.id.tView1);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        save = (Button) findViewById(R.id.Save);
        load = (Button) findViewById(R.id.load);

        File dir = new File(path);
        try {
            dir.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dir.mkdirs();

    }

    public void buttonSave(View view,Context context) {
        File file = new File(path + "/savedFile.txt");
        String[] saveText = String.valueOf(editText.getText()).split(System.getProperty("line.separator"));

        editText.setText("");

        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

        Save(file, saveText);
    }

    public void buttonLoad(View view, Context context) {
        File file = new File(path + "/savedFile.txt");
        String[] loadText = Load(file);

        String finalString = "";

        for (int i = 0; i < loadText.length; i++) {
            finalString += loadText[i] + System.getProperty("line.separator");
        }

        textView.setText(finalString);

    }

    public static void Save(File file, String[] data) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                for (int i = 0; i < data.length; i++) {
                    fos.write(data[i].getBytes());
                    if (i < data.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String[] Load(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl = 0;
        try {
            while ((test = br.readLine()) != null) {
                anzahl++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                array[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }


}


