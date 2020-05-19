package com.proyecto.asturiasenruta.ui.rutas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.asturiasenruta.FirebaseReferences;
import com.proyecto.asturiasenruta.R;
import com.proyecto.asturiasenruta.Ruta;
import com.proyecto.asturiasenruta.RutasAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RutasFragment extends Fragment {

    //Declaramos variables
    RecyclerView recyclerRutas; //Variable para el recycler view
    List<Ruta> listaRutas;  //Lista donde se almacenarán las rutas
    RutasAdapter adapter;   //Adaptador de rutas


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View vista = inflater.inflate(R.layout.fragment_rutas, container, false);   //Vista que devuelve el layout fragment_rutas

        listaRutas = new ArrayList<>(); //lista de rutas
        recyclerRutas = vista.findViewById(R.id.recycler_view); //recogemos la ID del recycler view
        recyclerRutas.setLayoutManager(new LinearLayoutManager(getContext())); //adaptamos el recyclerview al layout con LayoutManager

        llenarLista();  //Método para llenar el array

        RutasAdapter adapter = new RutasAdapter(listaRutas);    //Objeto de tipo adaptador y le pasamos la listarutas (constructor)
        recyclerRutas.setAdapter(adapter);
        return vista;   //Devolvemos la vista

    }

    //Método para rellenar la lista con los datos de Firebase
    private void llenarLista() {

        //Declaramos la base de datos de Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance(); //Base de datos de Firebase
        DatabaseReference miRef = database.getReference(FirebaseReferences.RUTAS_REFERENCE); //Obtenemos la referencia
        //RUTAS_REFERENCE Y NRUTA_REFERENCE se encuentra en la clase FirebaseReferences

        adapter = new RutasAdapter(listaRutas);
        recyclerRutas.setAdapter(adapter);


       miRef.child(FirebaseReferences.NRUTA_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaRutas.removeAll(listaRutas);   //Cada vez que se carga borra los datos anteriores y carga de nuevo los que hay en Firebase
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {

                    Ruta ruta = new Ruta();
                    ruta.setNombre(snapshot.getValue().toString());
                    //ruta =  snapshot.getValue(Ruta.class);  //Recogemos el valor devuelto en un Objeto //En esta linea da el error
                    //Ruta ruta = new Ruta(snapshot.getValue(String.class));
                    listaRutas.add(ruta);   //Añadimos la ruta a la lista de rutas

                }
                adapter.notifyDataSetChanged(); //notifica que ha habido cambios
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("ERROR", databaseError.getMessage()); //Mostramos el error en un log  //Mostramos un error y capturamos su mensaje
            }
        });

        //miRef.addValueEventListener(valueEventListener);

        //Otra menera de hacerlo recogiendo los datos de Firebase directamente desde el ROOT y no desde el hijo como arriba

        /*
        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaRutas.removeAll(listaRutas);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {

                    Ruta ruta = snapshot.getValue(Ruta.class);
                    listaRutas.add(ruta);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        //listaRutas.add(new Ruta("Ruta del Cares","Cabrales",R.drawable.common_google_signin_btn_icon_dark));
    }

    @Override
    public void onStart() {
        super.onStart();



    }

    public interface onFragmentInteractionListener {
    }
}