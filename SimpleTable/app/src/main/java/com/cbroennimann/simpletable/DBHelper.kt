package com.cbroennimann.simpletable

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.IOException
import java.lang.Exception


class DBHelper (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + UNIQUE_ID_COL + " INT, " +
                FIRST_NAME_COL + " TEXT," +
                LAST_NAME_COL + " TEXT," +
                REWARDS_COL + " INT" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addMember(id: Int,fstName: String, lstName: String, rewards: Int){
        val values = ContentValues()

        if(id != null && fstName != null && lstName != null && rewards != null) {
            values.put(UNIQUE_ID_COL, id)
            values.put(FIRST_NAME_COL, fstName)
            values.put(LAST_NAME_COL, lstName)
            values.put(REWARDS_COL, rewards)

            val db = this.writableDatabase
            checkMember(id)

            if (checkMember(id)) {
                //db.update(TABLE_NAME, values, "$UNIQUE_ID_COL=1", arrayOf())
                deleteMember(id)

            }
            db.insert(TABLE_NAME, null, values)




            db.close()
        }
    }

    fun getMember(id: Int) : Cursor{
            val db = this.readableDatabase
            return db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + UNIQUE_ID_COL + " = " + id,
                null
            )
    }

    fun deleteMember(id: Int?) {
            if (id != null) {
                val db = this.readableDatabase
                db.delete(TABLE_NAME, UNIQUE_ID_COL + " = " + id, null)
                //db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + UNIQUE_ID_COL + " = " + id, null)
            }
    }

    fun checkMember(id: Int) : Boolean {
        val db = this.readableDatabase
        var bool = false
        val cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + UNIQUE_ID_COL + " = " + id, null)
        var list: MutableList<Int> = arrayListOf()

        if(cursor.columnCount > 1) {
            bool = true
            Log.d("TAG", "Cursor is not null")
        }
        //cursor!!.moveToFirst()
        //list.add(cursor.getInt(cursor.getColumnIndex()))
        return bool
    }


    companion object{
        private val DATABASE_NAME = "SIMPLETABLE"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "simple_table"
        val UNIQUE_ID_COL = "id"
        val FIRST_NAME_COL = "first_name"
        val LAST_NAME_COL = "last_name"
        val REWARDS_COL = "rewards"
    }

}