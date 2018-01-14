package com.example.examen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examen.FBObjects.FBNoticia;
import com.example.examen.R;

import java.util.ArrayList;

/**
 * Created by juan.jusue on 19/12/2017.
 */

public class ListaNoticiasAdapter extends RecyclerView.Adapter<NoticiasViewHolder> {
    //arraylist de objetos noticias
    private ArrayList<FBNoticia> noticias;
    private Context nContext;

    public ListaNoticiasAdapter(ArrayList<FBNoticia> noticias,Context nContext){
        this.noticias = noticias;
        this.nContext = nContext;
    }

    @Override
    public NoticiasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflamos el xml de la celda
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_noticia_layout,null);
        //inicializamos la viewHolder de las noticias
        NoticiasViewHolder cochesViewHolder = new NoticiasViewHolder(view);
        return cochesViewHolder;
    }

    @Override
    //metodo para setear todos los datos de la noticia dentro de la cerda
    public void onBindViewHolder(NoticiasViewHolder holder, int position) {
        holder.tvtitulo.setText(noticias.get(position).titulo+"");
        holder.tvperiodico.setText(noticias.get(position).periodico);
        Glide.with(nContext).load(noticias.get(position).imgurl).into(holder.imgnoticia);

    }


    @Override
    public int getItemCount() {
        return noticias.size();
    }
}



//clase viewholder de las noticias, esta clase se repetira por cada noticia que haya en la bbdd
class NoticiasViewHolder extends RecyclerView.ViewHolder {

    public TextView tvtitulo;
    public TextView tvperiodico;
    public ImageView imgnoticia;

    public NoticiasViewHolder(View itemView) {
        super(itemView);
        tvtitulo=itemView.findViewById(R.id.tvtitulo);
        tvperiodico=itemView.findViewById(R.id.tvperiodico);
        imgnoticia=itemView.findViewById(R.id.imgnoticia);

    }
}
