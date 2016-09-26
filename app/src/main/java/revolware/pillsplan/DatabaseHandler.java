package revolware.pillsplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 9/25/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pillsplan";

    //table name
    public static final String TABLE_DRUGS = "drugs";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_STATE = "state";
    private static final String KEY_INDICATION = "indication";
    private static final String KEY_LIFE = "life";
    private static final String KEY_DISTRIBUTION = "distribution";
    private static final String KEY_ABSORPTION = "absorption";
    private static final String KEY_CLEARENCE = "clearence";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SONGS_TABLE = "CREATE TABLE " + TABLE_DRUGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_STATE + " TEXT," + KEY_INDICATION + " TEXT,"
                + KEY_LIFE + " TEXT," + KEY_DISTRIBUTION + " TEXT,"
                + KEY_ABSORPTION + " TEXT," + KEY_CLEARENCE + " TEXT)";


        sqLiteDatabase.execSQL(CREATE_SONGS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUGS);
        // Create tables again
        onCreate(sqLiteDatabase);
    }



    public void addDrug(Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = setValues(medicine);

        db.insert(TABLE_DRUGS, null, values);
        db.close();
    }



    public Medicine getMedicine(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DRUGS, new String[]{KEY_ID,
                        KEY_NAME, KEY_DESCRIPTION, KEY_STATE, KEY_INDICATION, KEY_LIFE,
                        KEY_DISTRIBUTION, KEY_ABSORPTION, KEY_CLEARENCE}, KEY_NAME + "=?",
                new String[]{name}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Medicine medicine = null;
        if(cursor != null && cursor.isFirst()) {
            medicine = retriveFromCursor(cursor);
        }else{
            medicine = null;
        }
        return medicine;
    }

    public List<Medicine> getAllMedicine(){
        List<Medicine> medicines = new ArrayList<Medicine>();

        String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Medicine medicine = retriveFromCursor(cursor);
                medicines.add(medicine);
            } while (cursor.moveToNext());
        }

        return medicines;
    }

    public List<Medicine> serachMedicineByString(String term){
        List<Medicine> medicines = new ArrayList<Medicine>();

        String select_query = "SELECT * FROM " + TABLE_DRUGS + " WHERE " + KEY_NAME + " LIKE " + "'%" + term + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Medicine medicine = retriveFromCursor(cursor);
                medicines.add(medicine);
            } while (cursor.moveToNext());
        }

        return medicines;
    }

    public int getMedicineCounts(){
        String countQuery = "SELECT  * FROM " + TABLE_DRUGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public int updateMedicine(Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = setValues(medicine);

        return db.update(TABLE_DRUGS, values, KEY_NAME + " = ?",
                new String[]{medicine.getNAME()});
    }

    public void deleteMedicine(Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DRUGS, KEY_NAME + " = ?",
                new String[]{medicine.getNAME()});
        db.close();
    }






    public ContentValues setValues(Medicine medicine){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, medicine.getNAME());
        values.put(KEY_DESCRIPTION, medicine.getDESCRIPTION());
        values.put(KEY_STATE, medicine.getSTATE());
        values.put(KEY_INDICATION, medicine.getINDICATION());
        values.put(KEY_LIFE, medicine.getLIFE());
        values.put(KEY_DISTRIBUTION, medicine.getDISTRIBUTION());
        values.put(KEY_ABSORPTION, medicine.getABSORPTION());
        values.put(KEY_CLEARENCE, medicine.getCLEARENCE());
        return values;
    }


    public Medicine retriveFromCursor(Cursor cursor){
        Medicine medicine = new Medicine();
        medicine.setNAME(cursor.getString(1));
        medicine.setDESCRIPTION(cursor.getString(2));
        medicine.setSTATE(cursor.getString(3));
        medicine.setINDICATION(cursor.getString(4));
        medicine.setLIFE(cursor.getString(5));
        medicine.setDISTRIBUTION(cursor.getString(6));
        medicine.setABSORPTION(cursor.getString(7));
        medicine.setCLEARENCE(cursor.getString(8));
        return medicine;
    }

}
