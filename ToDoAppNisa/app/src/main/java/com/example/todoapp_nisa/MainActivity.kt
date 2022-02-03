package com.example.todoapp_nisa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    private lateinit var gorevler_liste: ArrayList<Gorevler>
    private lateinit var adapter: GorevlerAdapter

    private lateinit var vt:VeriTabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        veritabanikopyala()

        // Toolbar'a başlık ekledim:
        toolbarYapilacaklar.title = "Yapılacaklar"
        setSupportActionBar(toolbarYapilacaklar)

        // Arayüzde orantılı durmasını sağlayan kodlama
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this@MainActivity)

        vt = VeriTabaniYardimcisi(this@MainActivity)

        gorevlerAl()

        // fab butonuna tıklanma metodu:

        fab.setOnClickListener {
            //startActivity(Intent(this@MainActivity, sayfa2::class.java))
            startActivity(Intent(this@MainActivity, sayfa2::class.java))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Yarattığımız menu'yu burda tanımlamamız lazım
        menuInflater.inflate(R.menu.toolbar_arama_menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this) // Yukardaki interface'i işaret ediyor bu this
        return super.onCreateOptionsMenu(menu)
    }

    // Main activity'e gelen kullanıcı uygulamadan çıksın istiyoruz:

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Gönderiler arama sonucu", query)
        aramaYap(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Harf girdikçe sonuç", newText)
        aramaYap(newText)
        return true
    }

    fun veritabanikopyala(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)

        try{
            copyHelper.createDataBase()

        }catch (e: IOException){
            e.printStackTrace()
        }

    }

    fun gorevlerAl(){

        gorevler_liste = Gorevlerdao().gorevler(vt)
        adapter = GorevlerAdapter(this@MainActivity, gorevler_liste,vt)

        rv.adapter = adapter
    }

    fun aramaYap(aramaKelimesi:String){

        gorevler_liste = Gorevlerdao().gorevAra(vt, aramaKelimesi)
        adapter = GorevlerAdapter(this@MainActivity, gorevler_liste,vt)

        rv.adapter = adapter
    }

}