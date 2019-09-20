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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText textemail;
    private EditText textpassword;
    private ProgressDialog progressDialog;
    private Button buttonlogin, buttoncreate;
    private FirebaseAuth firebaseAuth;
    public String idd;

    public MainActivity(EditText textemail) {
        this.textemail = textemail;
    }

    public MainActivity() {

    }

    public EditText getTextemail() {
        return textemail;
    }

    public void setTextemail(EditText textemail) {
        this.textemail = textemail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        textemail =(EditText) findViewById(R.id.txtemail);


        textpassword =(EditText) findViewById(R.id.txtpassword);
        progressDialog =new ProgressDialog(this);

        buttonlogin =(Button) findViewById(R.id.login);
        buttoncreate = (Button)findViewById(R.id.create);
        buttonlogin.setOnClickListener(this);
        buttoncreate.setOnClickListener(this);

    }

    private void loginuser (){
        //Obtenemos el email y la contraseña de las cajas de texto
        String email = textemail.getText().toString().trim();
        String password = textpassword.getText().toString().trim();

        //Verificar que las cajas de texto no esten vacias
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor ingresa un email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        //Barra de progreso
        progressDialog.setMessage("Iniciando sesión");
        progressDialog.show();


        //loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){


                            Toast.makeText(MainActivity.this, "Bienvenido: "+ textemail.getText(),Toast.LENGTH_LONG).show();
                            Intent intent= new Intent( getApplicationContext() , home.class);
                            intent.putExtra("dato",textemail.getText().toString());
                            startActivity( intent );






                        }
                        else {
                            Toast.makeText(MainActivity.this, "Email o contraseña incorrecta ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.create:
                Intent intent = new Intent( getApplicationContext(), register.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity ( intent );
                break;

            case R.id.login:
                loginuser();
        }
    }
}
