package com.example.examen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by juan.jusue on 19/12/2017.
 */

public class ListaNoticiasAdapter extends RecyclerView.Adapter<NoticiasViewHolder> {
    //arraylist de objetos coches
    private ArrayList<FBCoche> coches;
    private Context nContext;

    public ListaNoticiasAdapter(ArrayList<FBCoche> coches,Context nContext){
        this.coches = coches;
        this.nContext = nContext;
    }

    @Override
    public NoticiasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflamos el xml de la cerda
      //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_coche_layout,null);
        //inicializamos la viewHolder de los coches
        NoticiasViewHolder cochesViewHolder = new NoticiasViewHolder(view);
        return cochesViewHolder;
    }

    @Override
    //metodo para setear todos los datos del coche dentro de la cerda
    public void onBindViewHolder(NoticiasViewHolder holder, int position) {
        holder.tvfabricado.setText(coches.get(position).Fabricado+"");
        holder.tvnombre.setText(coches.get(position).Nombre);
        holder.tvmarca.setText(coches.get(position).Marca);
        Glide.with(nContext).load(coches.get(position).imgurl).into(holder.imgcoche);

    }


    @Override
    public int getItemCount() {
        return coches.size();
    }
}

}

//clase viewholder de los coches, esta clase se repetira por cada coche que haya en la bbdd
class NoticiasViewHolder extends RecyclerView.ViewHolder {

    public TextView tvfabricado;
    public TextView tvmarca;
    public TextView tvnombre;
    public TextView tvlatitud;
    public TextView tvlongitud;
    public ImageView imgcoche;

    public NoticiasViewHolder(View itemView) {
        super(itemView);
        //tvfabricado=itemView.findViewById(R.id.tvfabricado);
        //tvmarca=itemView.findViewById(R.id.tvmarca);
        //tvnombre=itemView.findViewById(R.id.tvnombre);

    }
}
