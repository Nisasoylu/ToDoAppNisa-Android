package com.example.todoapp_nisa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.sayfa2.*

class sayfa2 : AppCompatActivity() {

    private lateinit var vt:VeriTabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sayfa2)

        toolbarKayit.title = "Yapılacak İş Kayıt"
        setSupportActionBar(toolbarKayit)

        vt = VeriTabaniYardimcisi(this@sayfa2)

        kaydetButton.setOnClickListener {
            val yapilacak_is = textis.text.toString()

            kayit(yapilacak_is)
        }
    }

    fun kayit(yapilacak_is:String){
        Log.e("İş Kayıt", "$yapilacak_is")

        Gorevlerdao().gorevEkle(vt, yapilacak_is)
        startActivity(Intent(this@sayfa2,MainActivity::class.java))


    }
}