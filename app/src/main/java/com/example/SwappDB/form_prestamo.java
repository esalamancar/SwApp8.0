package com.example.SwappDB;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class form_prestamo extends AppCompatActivity implements View.OnClickListener {

    private EditText banco; //variable Campo de texto valor
    private EditText valor; //variable Campo de texto valor
    private EditText nota; //variable campo de texto nota
    private EditText fecha; //variable campo de texto fecha
    private Button prestamo_guardar; //variable boton de guardar
    private ImageView back;
    private EditText prestamo;
    private TextView categ;
    private DatabaseReference mDatabase;
    private String idd;
    private RadioButton tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_prestamo);
        valor = (EditText) findViewById(R.id.valor); // instanciar variable valor
        nota = (EditText) findViewById(R.id.nota); // instanciar variable nota
        fecha = (EditText) findViewById(R.id.txtfecha); // instanciar variable fecha
        banco = (EditText) findViewById(R.id.banco);
        categ = (TextView) findViewById(R.id.salario);
        tipo = (RadioButton) findViewById(R.id.radioButton);
        prestamo_guardar = (Button) findViewById(R.id.prestamo_guardar);
        prestamo_guardar.setOnClickListener(this);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        String dato1 = getIntent().getStringExtra("dato1");
        idd = dato1;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.prestamo_guardar: //caso para devolver al home

                if (!validate()) {
                    return;
                }

                String sbanco = banco.getText().toString();
                String svalor = valor.getText().toString();
                String snota = nota.getText().toString();
                String sfecha = fecha.getText().toString();
                String scategoria = categ.getText().toString();
                String stipo = "variable";
                if (tipo.isSelected() == true) {
                    stipo = "variable";
                } else {
                    stipo = "fijo";
                }
                BaseDatosF enviar = new BaseDatosF();

                Intent guardar = new Intent(getApplicationContext(), ingresos.class); // Envie a otra activity, recibe contexto y activity destino.

                enviar.TransaccionEnvio("Ingresos", "nsforero@samateoeduco", sbanco, scategoria, sfecha, snota, svalor, stipo);
                startActivity(guardar);
                break;

            case R.id.back: //caso para devolver al home
                Intent back = new Intent(getApplicationContext(), ingresos.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(back);
                break;
        }
    }

    private boolean validate() {
        // validaciones para que los campos no se dejen vacios

        if (banco.getText().toString().equals("")) {
            Toast.makeText(this, "Ingresa el nombre del Banco", Toast.LENGTH_SHORT).show();
            // Focus
            banco.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(form_prestamo.INPUT_METHOD_SERVICE); imm.showSoftInput(banco, 0); //Activar teclado
            return false;
        } else {
            if (valor.getText().toString().equals("")) {
                Toast.makeText(this, "Ingresa un valor valido", Toast.LENGTH_SHORT).show();
                // Focus
                valor.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(form_prestamo.INPUT_METHOD_SERVICE); imm.showSoftInput(valor, 0); //Activar teclado
                return false;
            } else {
                if (nota.getText().toString().equals("")) {
                    Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    // Focus
                    nota.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(form_prestamo.INPUT_METHOD_SERVICE); imm.showSoftInput(nota, 0); //Activar teclado
                    return false;

                } else {
                    if (fecha.getText().toString().equals("")) {
                        Toast.makeText(this, "Ingresa una fecha valida", Toast.LENGTH_SHORT).show();
                        // Focus
                        fecha.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(form_prestamo.INPUT_METHOD_SERVICE); imm.showSoftInput(fecha, 0); //Activar teclado
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }
}
