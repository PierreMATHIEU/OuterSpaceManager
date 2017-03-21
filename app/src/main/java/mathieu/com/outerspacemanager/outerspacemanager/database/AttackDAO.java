package mathieu.com.outerspacemanager.outerspacemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.AttackResponse;

/**
 * Created by Piou on 20/03/2017.
 */

public class AttackDAO {

    // Database fields
    private SQLiteDatabase database;
    private SqlDatabase dbHelper;
    private String[] allColumns = { SqlDatabase.KEY_TIME, SqlDatabase.KEY_FLEET, SqlDatabase.KEY_USER };
    public AttackDAO(Context context) {
        dbHelper = new SqlDatabase(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public AttackResponse createAttack(Long time, String fleet, String userVictime) {
        ContentValues values = new ContentValues();
        values.put(SqlDatabase.KEY_TIME, time);
        values.put(SqlDatabase.KEY_FLEET, fleet);
        values.put(SqlDatabase.KEY_USER, userVictime);
        database.insert(SqlDatabase.ATTACK_TABLE_NAME, null,
                values);
        Cursor cursor = database.query(SqlDatabase.ATTACK_TABLE_NAME,
                allColumns, SqlDatabase.KEY_TIME + " = \"" + time +"\"", null,
                null, null, null);
        cursor.moveToFirst();
        AttackResponse newAttack = cursorToAttack(cursor);
        cursor.close();
        return newAttack;
    }
    public ArrayList<AttackResponse> getAllAttacks() {
        ArrayList<AttackResponse> lesAttacks = new ArrayList<AttackResponse>();
        Cursor cursor = database.query(SqlDatabase.ATTACK_TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AttackResponse unAttack = cursorToAttack(cursor);
            lesAttacks.add(unAttack);
            cursor.moveToNext();
        }
// make sure to close the cursor
        cursor.close();
        return lesAttacks;
    }
    private AttackResponse cursorToAttack(Cursor cursor) {
        AttackResponse comment = new AttackResponse();
        comment.setAttackTime(cursor.getLong(0));
        comment.setFleetSend(cursor.getString(1));
        comment.setUserVictime(cursor.getString(2));
        return comment;
    }
    public void deleteAttack(AttackResponse unAttack) {
        database.delete(SqlDatabase.ATTACK_TABLE_NAME, SqlDatabase.KEY_TIME
                + " = \"" + String.valueOf(unAttack.getAttackTime())+"\"", null);
    }
}
