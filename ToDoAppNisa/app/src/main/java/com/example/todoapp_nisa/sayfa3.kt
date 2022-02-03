package com.example.todoapp_nisa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.sayfa2.*
import kotlinx.android.synthetic.main.sayfa3.*

class sayfa3 : AppCompatActivity() {

    private lateinit var gorev:Gorevler
    private lateinit var vt:VeriTabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sayfa3)

        toolbarDetay.title = "Yapılacak İş Detay"
        setSupportActionBar(toolbarDetay)

        vt = VeriTabaniYardimcisi(this@sayfa3)

        gorev = intent.getSerializableExtra("nesne") as Gorevler

        textguncelle.setText(gorev.yapilacak_is)

        guncelleButon.setOnClickListener {
            val is_guncelle = textguncelle.text.toString()

            guncelle(gorev.yapilacak_id, is_guncelle)
        }
    }

    fun guncelle(yapilacak_id:Int, yapilacak_is:String){
        Log.e("İş Kayıt", "$yapilacak_id - $yapilacak_is")

        Gorevlerdao().gorevGuncelle(vt,yapilacak_id,yapilacak_is)
        startActivity(Intent(this@sayfa3,MainActivity::class.java))
    }
}