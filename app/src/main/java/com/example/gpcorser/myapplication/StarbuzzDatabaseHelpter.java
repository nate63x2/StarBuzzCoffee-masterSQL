package com.example.gpcorser.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ntwhitfi on 10/17/2016.
 */

public class StarbuzzDatabaseHelpter extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;

    StarbuzzDatabaseHelpter (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);

        /*
        ContentValues espresso = new ContentValues();

        espresso.put("NAME", "Espresso");

        ContentValues americano = new ContentValues();
        americano.put("NAME", "Americano");

        ContentValues latte = new ContentValues();
        latte.put("NAME", "Latte");

        ContentValues mochachino = new ContentValues();
        mochachino.put("NAME", "Mochachino");

        ContentValues filter = new ContentValues();
        filter.put("DESCRIPTION", "Filter");

        db.execSQL("CREATE TABLE DRINK ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        db.insert("DRINK", null, espresso);
        db.insert("DRINK", null, americano);
        db.insert("DRINK", null, latte);
        db.insert("DRINK", null, mochachino);
        db.insert("DRINK", null, filter);
        */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE DRINK ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertDrink(db, "Latte", "Espresso and steamed Milk", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed milk foam", R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);
        }

        if(oldVersion < 2) {
            // code to add an extra column to the table
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }

    private void insertDrink(SQLiteDatabase db, String name, String description, int resourceID) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceID);
        db.insert("DRINK", null, drinkValues);
    }


}
