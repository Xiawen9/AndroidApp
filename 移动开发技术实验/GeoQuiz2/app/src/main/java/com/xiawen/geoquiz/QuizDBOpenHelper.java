package com.xiawen.geoquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDBOpenHelper extends SQLiteOpenHelper {
    //create database(quiz.db)
    public QuizDBOpenHelper(Context context){
        super(context,"quiz.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info (TextResId integer primary key autoincrement, TextQuestion varchar(200), answer varchar(20), isAnswerd integer)");
        db.execSQL("insert into info (TextResId, TextQuestion, answer, isAnswerd) " +
                "values(?,?,?,?)", new Object[]{0, "Canberra is the capital of Australia.", "true", 0});
        db.execSQL("insert into info (TextResId, TextQuestion, answer,isAnswerd) " +
                "values(?,?,?,?)", new Object[]{1, "The Pacific Ocean is larger than the Atlantic Ocean.", "true", 0});
        db.execSQL("insert into info (TextResId, TextQuestion, answer,isAnswerd) " +
                "values(?,?,?,?)", new Object[]{2, "The Suez Canal connects the Red Sea and the Indian Ocean.", "false", 0});
        db.execSQL("insert into info (TextResId, TextQuestion, answer,isAnswerd) " +
                "values(?,?,?,?)", new Object[]{3, "The source of the Nile River is in Egypt.", "false", 0});
        db.execSQL("insert into info (TextResId, TextQuestion, answer,isAnswerd) " +
                "values(?,?,?,?)", new Object[]{4, "The Amazon River is the longest river in the Americas.", "true", 0});
        db.execSQL("insert into info (TextResId, TextQuestion, answer,isAnswerd) " +
                "values(?,?,?,?)", new Object[]{5, "Lake Baikal is the world\\'s oldest and deepest freshwater lake.", "true", 0});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
