package com.example.todoapp_nisa

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class GorevlerAdapter(var mContext:Context, var gorevler_liste:ArrayList<Gorevler>, var vt:VeriTabaniYardimcisi)
    :RecyclerView.Adapter<GorevlerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim:View):RecyclerView.ViewHolder(tasarim){
        var gorev_kart:CardView
        var gorev_text:TextView
        var gorev_sil:ImageView

        init {
            gorev_kart = tasarim.findViewById(R.id.gorev_kart)
            gorev_text = tasarim.findViewById(R.id.gorev_text)
            gorev_sil = tasarim.findViewById(R.id.gorev_sil)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.satir_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return gorevler_liste.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var gorev = gorevler_liste.get(position)

        holder.gorev_text.text = "${gorev.yapilacak_is}"

        holder.gorev_sil.setOnClickListener{
            Toast.makeText(mContext,"${gorev.yapilacak_is} silindi",Toast.LENGTH_SHORT).show()
            Gorevlerdao().gorevSil(vt,gorev.yapilacak_id)
            gorevler_liste = Gorevlerdao().gorevler(vt)
            notifyDataSetChanged()
        }

        holder.gorev_kart.setOnClickListener {
            val intent = Intent(mContext, sayfa3::class.java)
            intent.putExtra("nesne", gorev)
            mContext.startActivity(intent)

        }
    }
}