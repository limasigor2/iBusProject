package team.com.ibus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by Alysson on 17/11/2015.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    private static final String BD_NAME = "bd_ibus_interno";
    private static final int BD_VERSION = 1;


    public DatabaseOpenHelper(Context ctx) {
        super(ctx, BD_NAME, null, BD_VERSION);
    }

    //Vai dar certo para glória do Senhor Jesus.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    /**
     *
     * @param db db of application
     * this method update the wrong data of table "tblleitura_1tri"
     */

}