package com.proyecto.asturiasenruta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Variable para Firebase
        final FirebaseAuth firebaseAuth;
        final DatabaseReference mDatabase;
        firebaseAuth = FirebaseAuth.getInstance();  //Instanciamos FirebaseAuth
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Recogemos ID de los EditText
        //Edit Text email y contraseña
        final EditText cajaEmail = findViewById(R.id.cajaEmail);
        final EditText cajaPass = findViewById(R.id.cajaPass);

        Button objetoBotonRegistrar = findViewById(R.id.botonRegistrar);    //Obtenemos id del boton
        objetoBotonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //al hacer clicnk
                //Pasan a String lo que se escribio ahi
                final String emailID = cajaEmail.getText().toString();
                final String pass = cajaPass.getText().toString();

                //Condicion
                if(emailID.isEmpty()){ //Si email esta vacio muestra un toast
                    Toast toast = Toast.makeText(getApplicationContext(), "Debes introducir" +
                            " un email para continuar", Toast.LENGTH_LONG);
                    toast.show();
                }else if (pass.isEmpty()){  //Si la contraseña esta vacia muestra un toast
                    Toast toast = Toast.makeText(getApplicationContext(), "Debes introducir " +
                            "una contraseña para continuar", Toast.LENGTH_LONG);
                    toast.show();
                }else{  //Si introduce email y cotraseña...
                    firebaseAuth.createUserWithEmailAndPassword(emailID,pass).addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){    //...y son correctos, se guarda en Firebase

                                Map<String,Object> map = new HashMap<>();
                                map.put("email", emailID);
                                map.put("contra", pass);

                                String id = firebaseAuth.getCurrentUser().getUid(); //Obtenemos el id de usuario

                                //Almacena el nuevo usuario regitsrado en la tabla usuarios
                                mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task1) {
                                        if (task1.isSuccessful()) {
                                            startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                                            Toast toast = Toast.makeText(getApplicationContext(), "Usuario registrado, por favor inicie sesion", Toast.LENGTH_SHORT);
                                            toast.show();
                                            finish();
                                        }
                                    }

                                });

                            }else{ //si no muestra un toast con error

                                Toast toast = Toast.makeText(RegistroActivity.this.getApplicationContext(), "No se ha podido finalizar el registro" + task.getException().getMessage(), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });
                }

            }
        });
    }
}
