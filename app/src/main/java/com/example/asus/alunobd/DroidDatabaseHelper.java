package com.example.asus.alunobd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Asus on 31/10/2017.
 */

public class DroidDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "aluno.db";
    public static final String TABLE_NAME = "alunos";
    public static final String ALUNOS_COLUMN_ID = "id";
    public static final String ALUNOS_COLUMN_NOME = "a_nome";
    public static final String ALUNOS_COLUMN_RA = "a_ra";

    public DroidDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

      String sql = "create table "+ TABLE_NAME + " ( " + ALUNOS_COLUMN_ID + " INTEGER primary key AUTOINCREMENT," + ALUNOS_COLUMN_NOME+ " CHAR(50),"+ ALUNOS_COLUMN_RA+ " CHAR(9))";

      Log.e("[MEUDB]", "Create table: " + sql);

      db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
    db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
    onCreate(db);
    }

    public boolean insertAluno(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("a_nome", aluno.getNome());
        contentValues.put("a_ra", aluno.getRa());

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Aluno> getData() {
        ArrayList<Aluno> aluno = new ArrayList<Aluno>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("select * from "+ TABLE_NAME , null);

        while(result.moveToNext()){
            aluno.add(new Aluno(result.getString(result.getColumnIndex(ALUNOS_COLUMN_ID)),result.getString(result.getColumnIndex(ALUNOS_COLUMN_NOME)),
                    result.getString( result.getColumnIndex(ALUNOS_COLUMN_RA))));
        }
        return aluno;

    }

    public boolean updateAluno(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("a_nome", aluno.getNome());
        contentValues.put("a_ra", aluno.getRa());

        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{aluno.getId()});
        return true;
    }

    public Integer deleteAluno(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[]{id});
    }

    public Aluno buscarAluno(String id){

        Aluno aluno = new Aluno(null , null, null);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from "+ TABLE_NAME + " where id= '"+id+"'" , null);
        while(result.moveToNext()){

            aluno.setRa(result.getString(result.getColumnIndex(ALUNOS_COLUMN_ID)));
            aluno.setNome(result.getString(result.getColumnIndex(ALUNOS_COLUMN_NOME)));
            aluno.setRa(result.getString( result.getColumnIndex(ALUNOS_COLUMN_RA)));
        }
        return aluno;
    }

}
