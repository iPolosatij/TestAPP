package space.digitallab.testapp.SQLiteData;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import space.digitallab.testapp.retrofitData.Datum;

public class DBinterface{

        ContentValues cv = new ContentValues();
        SQLiteDatabase db;

    public DBinterface(DBHelper dbHelper){
        db = dbHelper.getWritableDatabase();
    }

    public String dataSave(ArrayList<Datum> contactList) {

        String S = contactList.toString();

        for (Datum contact : contactList) {
            cv.put("firstname", contact.getFirstName());
            cv.put("lastname", contact.getLastName());
            cv.put("avatar", contact.getAvatar());
            cv.put("email", contact.getEmail());
            // вставляем запись и получаем ее ID
            long rowID = db.insert("mytable", null, cv);
            S = S + ("row inserted, ID = " + rowID + "\n");
        }
        return S;
    }

    public void dataItemSave(Datum contact) {

            cv.put("firstname", contact.getFirstName());
            cv.put("lastname", contact.getLastName());
            cv.put("avatar", contact.getAvatar());
            cv.put("email", contact.getEmail());

            long rowID = db.insert("mytable", null, cv);
    }

    public void dataRead(ArrayList<Datum> listDatum) {

        int i = 0;

        Cursor c = db.query("mytable", null, null, null, null, null, null);

        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id");
            int firstnameColIndex = c.getColumnIndex("firstname");
            int lastnameColIndex = c.getColumnIndex("lastname");
            int avatarColIndex = c.getColumnIndex("avatar");
            int emailColIndex = c.getColumnIndex("email");
            do {
                listDatum.add(new Datum(c.getInt(idColIndex),
                        c.getString(emailColIndex),
                        c.getString(firstnameColIndex),
                        c.getString(lastnameColIndex),
                        c.getString(avatarColIndex)));
            } while (c.moveToNext());
        } else {

            c.close();
            for (int n = 0; n < 5; i++) {
                listDatum.add(new Datum(0,"no data", "no data", "no data", "no data"));
            }
        }
    }

    public String dbClear() {
        int clearCount = db.delete("mytable", null, null);
        String S = ("deleted rows count = " + clearCount);
        return S;
    }
}
