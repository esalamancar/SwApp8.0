package com.example.SwappDB;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class register extends AppCompatActivity implements View.OnClickListener {

    private EditText textemail;
    private EditText textpassword;
    private Button buttonregister;
    private ProgressDialog progressDialog;

    //declaramos el objeto firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //inicializamos el objeto firebase
        firebaseAuth =FirebaseAuth.getInstance();

        //referenciamos los views
        textemail = (EditText) findViewById(R.id.txtemail);
        textpassword = (EditText) findViewById(R.id.txtpassword);

        buttonregister = (Button) findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);

        buttonregister.setOnClickListener(this);
    }

    private void userRegister (){

        //obtenemos el email y la contraseña de las cajas de texto
        String email =textemail.getText().toString().trim();
        String password =textpassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacias
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor ingresa un email",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        //Barra de progreso
        progressDialog.setMessage("Creando tu cuenta");
        progressDialog.show();

        //Crear nuevo usuaurio
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(register.this, "Se ha registrado exitosamente el usuario con el email: "+ textemail.getText(),Toast.LENGTH_LONG).show();
                            Intent intent = new Intent( getApplicationContext(), home.class); // Envie a otra activity, recibe contexto y activity destino.
                            startActivity ( intent );
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(register.this, "El usuario que deseas registrar ya existe", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(register.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    public void onClick(View view) {userRegister();}
    }