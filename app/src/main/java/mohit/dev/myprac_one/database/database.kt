package mohit.dev.myprac_one.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.ColorSpace.Model
import mohit.dev.myprac_one.model.my_listModel

class database(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

    companion object {

        private val DATABASE_NAME = "dbname"
        private val DB_VERSION = 1

        private const val tableName = "atmdatatable"
        private const val keyId = "db_id"
        private const val keyName = "db_name"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE =
            (
                    "CREATE TABLE " + tableName.toString() + " "
                            + " ( " + keyId.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                            + keyName.toString() + " TEXT)"
                    )

        db?.execSQL(CREATE_TABLE)

    }


    fun insert(atmModelclass: my_listModel): Long {

        var atmDatabase = this.writableDatabase

        var atm_cv = ContentValues()

        atm_cv.put(keyName, atmModelclass.name)

        var atm_insert = atmDatabase.insert(tableName, null, atm_cv)
        return atm_insert

    }


    @SuppressLint("Range")
    fun retrive(): ArrayList<my_listModel> {

        var userdatalist: MutableList<my_listModel> = ArrayList<my_listModel>()

        var query = "select * from $tableName order by $keyId desc"

        var cursor: Cursor?
        var atmdatabase = this.writableDatabase


        try {

            cursor = atmdatabase.rawQuery(query, null)
        } catch (Exp: SQLException) {
            atmdatabase.execSQL(query)
            return ArrayList()
        }

        var dbid: Int
        var name: String

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {
                    dbid = cursor.getInt(cursor.getColumnIndex(keyId))
                    name = cursor.getString(cursor.getColumnIndex(keyName))

                    var userDatas = my_listModel(dbid, name)
                    userdatalist.add(userDatas)
                } while (cursor.moveToNext())
            }
        }

        return userdatalist as ArrayList<my_listModel>

    }



    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}