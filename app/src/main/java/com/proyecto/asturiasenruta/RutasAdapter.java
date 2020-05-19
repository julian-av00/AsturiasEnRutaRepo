package com.proyecto.asturiasenruta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RutasAdapter extends RecyclerView.Adapter<RutasAdapter.RutasViewHolder> {  //La clase extiende de RecyclerView que es el componente que vamos a usar

    //Lista de Rutas (por eso le pasamos el objeto)
    List<Ruta> listaRutas;

    public RutasAdapter(List<Ruta> listaRutas) {
        this.listaRutas = listaRutas;   //Constructor al que le pasamos la lista
    }

    @NonNull
    @Override
    public RutasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Enlaza el adaptador con el ItemList(layout con la estructura para mostrar despues la base)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, null, false);

        return new RutasViewHolder(view);   //Devuelve la vista
    }

    @Override
    public void onBindViewHolder(@NonNull RutasViewHolder holder, int position) {

        //Establece la comunicacion entre le adpatador y la clase ViewHolderDatos
        holder.nombre.setText(listaRutas.get(position).getNombre());
        holder.concejo.setText(listaRutas.get(position).getConcejo());
    }

    @Override
    public int getItemCount() {
        return listaRutas.size(); //devuelve el tamaño de la lista de rutas
    }

    public class RutasViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, concejo;
        ImageView imagenId;
        public RutasViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.idNombre);   //Recogemos la id del 1er TextView
            concejo = (TextView) itemView.findViewById(R.id.idInfo);     //Recogemos la id del 2º TextView

        }
    }


}
