package com.example.SwappDB;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.SwappDB.modelo.Gastos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

public class MostrarGastos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_gastos);

        final TextView textview = (TextView)findViewById(R.id.textbd);

        //Instancia a la base de datos
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("Gastos").child("esalamanca@sanmateoeduco");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                Long count = dataSnapshot.getChildrenCount();
//leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);



                String Tablas="";
                String valor;
                String valores="";
                long result = 0;


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Tablas = Tablas + "\n" + child.getKey();
                    valor = child.child("Valor").getValue().toString();
                    valores = valores +"\n"+valor;
                    result = result + Long.parseLong(valor);

                }



                //formamos el resultado en un string
                String resultado = "Total Gastos ingresados del usuario: "+count+" Nombre de nodo: "+Tablas+"\n\nValores:\n"+valores+"\nResultado de los ingresos: "+result+"\n";


                textview.setText(resultado);
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });


    }
}
