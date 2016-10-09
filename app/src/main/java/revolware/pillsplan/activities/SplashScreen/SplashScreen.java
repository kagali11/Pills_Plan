package revolware.pillsplan.activities.SplashScreen;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import revolware.pillsplan.R;
import revolware.pillsplan.activities.Tutorial.Tutorial;
import revolware.pillsplan.database.DatabaseHandler;
import revolware.pillsplan.models.Medicine;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getAllDrugsScript();

        startActivity(new Intent(SplashScreen.this, Tutorial.class));

    }

    public class loadingDatabase extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute(){
            //sem daj loading
        }

        @Override
        protected String doInBackground(String... strings) {
            getAllDrugsScript();
            return "done";
        }

        @Override
        protected void onPostExecute(String result){
            startActivity(new Intent(SplashScreen.this, Tutorial.class));
        }

    }

    public void getAllDrugsFromFile(String file_name){
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(file_name);
            Log.i("tag", inputStream.toString());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource source = new InputSource(inputStream);
            Document document = db.parse(source);
            document.getDocumentElement().normalize();

            Node drugbank = document.getFirstChild();
            Node first_drug = drugbank.getFirstChild();

            while( first_drug.getNextSibling()!=null ){
                first_drug = first_drug.getNextSibling();
                if (first_drug.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) first_drug;
                    NodeList list = childElement.getElementsByTagName("name");
                    Log.i("tag", list.item(0).getTextContent());
                    Medicine m = getMedicineFromXML(childElement);

                    DatabaseHandler datab = new DatabaseHandler(SplashScreen.this);
                    SQLiteDatabase database = datab.getWritableDatabase();

                    datab.addDrug(m);
                }
            }


            int i = 0;

        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Medicine getMedicineFromXML(Element element){
        Medicine medicine = new Medicine();

        NodeList list_name = element.getElementsByTagName("name");
        medicine.setNAME(list_name.item(0).getTextContent());

        NodeList list_description = element.getElementsByTagName("description");
        if (list_description.getLength() > 0) {
            medicine.setDESCRIPTION(list_description.item(0).getTextContent());
        }else{
            medicine.setDESCRIPTION("");
        }

        NodeList list_state =   element.getElementsByTagName("state");
        if (list_state.getLength() > 0) {
            medicine.setSTATE(list_state.item(0).getTextContent());
        }else{
            medicine.setSTATE("");
        }

        NodeList list_indication = element.getElementsByTagName("indication");
        if (list_indication.getLength() > 0) {
            medicine.setINDICATION(list_indication.item(0).getTextContent());
        }else{
            medicine.setINDICATION("");
        }

        NodeList list_life = element.getElementsByTagName("half-life");
        if (list_life.getLength() > 0){
            medicine.setLIFE(list_life.item(0).getTextContent());
        }else{
            medicine.setLIFE("");
        }

        NodeList list_distribution = element.getElementsByTagName("volume-of-distribution");
        if(list_distribution.getLength()>0){
            medicine.setDISTRIBUTION(list_distribution.item(0).getTextContent());
        }else{
            medicine.setDISTRIBUTION("");
        }

        NodeList list_absorption = element.getElementsByTagName("absorption");
        if(list_absorption.getLength()>0){
            medicine.setABSORPTION(list_absorption.item(0).getTextContent());
        }else{
            medicine.setABSORPTION("");
        }

        NodeList list_clearence = element.getElementsByTagName("clearance");
        if(list_clearence.getLength()>0){
            medicine.setCLEARENCE(list_clearence.item(0).getTextContent());
        }else{
            medicine.setCLEARENCE("");
        }




        return medicine;
    }


    public void getAllDrugsScript(){

        getAllDrugsFromFile("part01.xml");
        Log.i("progress", "part 1 done");
        getAllDrugsFromFile("part02.xml");
        Log.i("progress", "part 2 done");
        getAllDrugsFromFile("part03.xml");
        Log.i("progress", "part 3 done");
        getAllDrugsFromFile("part04.xml");
        Log.i("progress", "part 4 done");
        getAllDrugsFromFile("part05.xml");
        Log.i("progress", "part 5 done");
        getAllDrugsFromFile("part06.xml");
        Log.i("progress", "part 6 done");
        getAllDrugsFromFile("part07.xml");
        Log.i("progress", "part 7 done");
        getAllDrugsFromFile("part08.xml");
        Log.i("progress", "part 8 done");
        getAllDrugsFromFile("part09.xml");
        Log.i("progress", "part 9 done");
        getAllDrugsFromFile("part10.xml");
        Log.i("progress", "part 10 done");
        getAllDrugsFromFile("part11.xml");
        Log.i("progress", "part 11 done");
        getAllDrugsFromFile("part12.xml");
        Log.i("progress", "part 12 done");
        getAllDrugsFromFile("part13.xml");
        Log.i("progress", "part 13 done");
        getAllDrugsFromFile("part14.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part15.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part16.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part17.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part18.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part19.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part20.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part21.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part22.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part23.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part24.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part25.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part26.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part27.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part28.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part29.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part30.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part31.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part32.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part33.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part34.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part35.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part36.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part37.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part38.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part39.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part40.xml");
        Log.i("progress", "part done");
        getAllDrugsFromFile("part41.xml");
        Log.i("progress", "part done");

    }
}
