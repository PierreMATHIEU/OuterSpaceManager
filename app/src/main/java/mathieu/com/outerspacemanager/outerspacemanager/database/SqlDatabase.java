package mathieu.com.outerspacemanager.outerspacemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by Piou on 20/03/2017.
 */

public class SqlDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDBAttackWithUser.db";
    public static final String ATTACK_TABLE_NAME = "Attack";
    public static final String KEY_TIME = "date";
    public static final String KEY_FLEET = "fleet";
    public static final String KEY_USER = "userVictime";

    private static final String ATTACK_TABLE_CREATE = "CREATE TABLE " + ATTACK_TABLE_NAME + " (" + KEY_TIME + " REAL, " + KEY_FLEET + " TEXT, "+ KEY_USER + " TEXT);";
    public SqlDatabase(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/"+DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ATTACK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {db.execSQL("DROP TABLE IF EXISTS " +
            ATTACK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ATTACK_TABLE_NAME);
        onCreate(db);
    }
}
