package com.proyecto.asturiasenruta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.PasswordAuthentication;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Intencion para ir a Registrarse cuando pinches el boton
        Button botonRegistro = findViewById(R.id.botonRegistro);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();     //Instanciamos la BD de Firebase
        email = findViewById(R.id.idUsuario);           //Recogemos la ID del TextView de email
        pass = findViewById(R.id.idContra);            //Recogemos la ID del TextView de contraseña

        Button objetoBotonInicioSesion = findViewById(R.id.botonLogin); //Recogemos ID del boton de inicio de sesion
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();  //Recogemos el usuario
                if(user != null){   //Si es disitinto de null se logea y pasa a otra activity
                    Toast.makeText(LoginActivity.this, "Usuario logeado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{ //si no muetra un toast
                    Toast.makeText(LoginActivity.this, "Inicia sesion para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        };

        objetoBotonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Uemail = email.getText().toString();
                String Upass = pass.getText().toString();

                if(Uemail.isEmpty()){   //si no introduce email o es incorrecto muestra un error
                    email.setError("Introduce email correcto");
                    email.requestFocus();
                }else if (Upass.isEmpty()){ //si no introduce contraseña o es incorrecta muestra un error
                    pass.setError("Introduce contraseña correcta");
                    pass.requestFocus();
                }else if (!(Uemail.isEmpty() && Upass.isEmpty())){  //Si ambos estan vacios o no coinciden muestra error
                    firebaseAuth.signInWithEmailAndPassword(Uemail,Upass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "NO SUCCESS", Toast.LENGTH_SHORT).show();
                            }else{  //Si coinciden pasa a  login activity para iniciar sesion
                                startActivity(new Intent(LoginActivity.this, MainRutas.class));
                            }
                        }
                    });
                }
            }
        });




    }

    protected void  onStart(){
        super.onStart();

        if(firebaseAuth != null){   //esto quiere decir que si ya se ha iniciado sesion...
            startActivity(new Intent(LoginActivity.this, MainRutas.class)); //pasa directamente al Main
            finish();
        }
    }

}
