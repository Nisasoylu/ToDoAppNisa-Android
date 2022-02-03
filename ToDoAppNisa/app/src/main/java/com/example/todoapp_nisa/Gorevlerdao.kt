package com.example.todoapp_nisa

import android.content.ContentValues

class Gorevlerdao {

    fun gorevler(vt: VeriTabaniYardimcisi): ArrayList<Gorevler> {
        val db = vt.writableDatabase
        val gorevler_liste = ArrayList<Gorevler>()

        val c = db.rawQuery("SELECT * FROM gorevler", null)

        while(c.moveToNext()){
            val gorev = Gorevler(c.getInt(c.getColumnIndex("yapilacak_id"))
                ,c.getString(c.getColumnIndex("yapilacak_is")))

            gorevler_liste.add(gorev)
        }
        return gorevler_liste
    }

    fun gorevAra(vt: VeriTabaniYardimcisi, aramaKelimesi: String): ArrayList<Gorevler> {
        val db = vt.writableDatabase
        val gorevler_liste = ArrayList<Gorevler>()

        val c =
            db.rawQuery("SELECT * FROM gorevler WHERE yapilacak_is like'%$aramaKelimesi%'", null)

        while (c.moveToNext()) {
            val gorev = Gorevler(
                c.getInt(c.getColumnIndex("yapilacak_id"))
                , c.getString(c.getColumnIndex("yapilacak_is"))
            )

            gorevler_liste.add(gorev)
        }
        return gorevler_liste
    }

    fun gorevSil(vt: VeriTabaniYardimcisi, yapilacak_id: Int) {
        val db = vt.writableDatabase
        db.delete("gorevler", "yapilacak_id=?", arrayOf(yapilacak_id.toString()))
        db.close()

    }

    fun gorevEkle(vt: VeriTabaniYardimcisi, yapilacak_is: String) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("yapilacak_is", yapilacak_is)
        db.insertOrThrow("gorevler", null, values)

        db.close()
        MainActivity()
    }

    fun gorevGuncelle(vt: VeriTabaniYardimcisi, yapilacak_id: Int, yapilacak_is: String) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("yapilacak_is", yapilacak_is)
        //gorevSil(vt,yapilacak_id)
        //db.insertOrThrow("gorevler", null, values)
        db.update("gorevler", values, "yapilacak_id=?", arrayOf(yapilacak_id.toString()))

        db.close()

    }
}