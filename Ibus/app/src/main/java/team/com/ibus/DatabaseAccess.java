package team.com.ibus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import team.com.ibus.Dominio.Usuario;

/**
 * Created by Alysson on 22/11/2015.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {

       // this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }


    public String getUserName(){

        String retornoNome = null;

        try {

            Cursor cursor = database.rawQuery("SELECT nome FROM usuario", null);

            if(cursor.moveToFirst())
                retornoNome = cursor.getString(0);


            cursor.close();
        }catch (Exception ex){

            return "";

        }

        return retornoNome;
    }

    public boolean cadastrarUsuario(Usuario usuario) {

        ContentValues cValues = new ContentValues();

        cValues.put("nome",usuario.getNome());
        cValues.put("login", usuario.getLogin());
        cValues.put("senha", usuario.getSenha());
        cValues.put("id",1);

        try {

            database.insert("usuario", null, cValues);

        } catch (Exception ex) {

            return false;
        }

        return true;

    }


    public boolean autenticarLogin(String login, String senha){


        try {

            Cursor cursor = database.rawQuery("SELECT login FROM usuario WHERE login = '"+login+"' AND senha = '"+senha+"'",null);

            if (cursor.moveToFirst())
                return true;

            cursor.close();

        }catch(Exception ex){

            return false;
        }

        return true;
    }


}
