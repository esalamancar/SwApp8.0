package com.example.SwappDB;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.SwappDB.modelo.Gastos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class ingresos extends AppCompatActivity implements View.OnClickListener {

    private Button gastos;
    private Button estadisticas;
    private String idd;
    private CardView salario; //Nombre de la tarjeta de hogar
    private CardView prestamo; //Nombre de la tarjeta de factura
    private CardView extra;
    private TextView valorTotal;
    private TextView valorCardSal;
    private TextView valorCardPrest;
    private TextView valorCardExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

        gastos = (Button) findViewById(R.id.gastos);
        gastos.setOnClickListener(this);
        estadisticas = (Button) findViewById(R.id.estadistica);
        estadisticas.setOnClickListener(this);
        salario = (CardView) findViewById(R.id.salario);
        salario.setOnClickListener(this);
        prestamo = (CardView) findViewById(R.id.prestamo);
        prestamo.setOnClickListener(this);
        valorTotal = (TextView) findViewById(R.id.totalCosts);
        valorCardSal = (TextView) findViewById(R.id.costCasa);
        valorCardPrest = (TextView) findViewById(R.id.costF);
        valorCardExtra = (TextView) findViewById(R.id.costT);
        extra = (CardView) findViewById(R.id.extra);
        extra.setOnClickListener(this);


        //Instancia a la base de datos
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("Ingresos").child("swappseminario@gmailcom");

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

                DecimalFormat formateador = new DecimalFormat("###,###.###");


                valorTotal.setText("$ "+formateador.format(result));
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });












        //Instancia a la base de datos
        FirebaseDatabase fbdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef1 = fbdb.getReference("Ingresos").child("swappseminario@gmailcom");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Long count = dataSnapshot.getChildrenCount();
                //leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);


                String buscandoValoresDe="SALARIO";
                String Tablas="";
                String valor;
                String valores="";
                long result = 0;



                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    Tablas = Tablas + "\n" + child.getKey();

                    valor = child.child("Valor").getValue().toString();
                    if (child.child("Categoria").getValue().toString().equals(buscandoValoresDe)){

                        valores = valores +"\n"+valor;
                        result = result + Long.parseLong(valor);
                    }


                }

                DecimalFormat formateador = new DecimalFormat("###,###.###");
                valorCardSal.setText("$ "+formateador.format(result));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });











        //Instancia a la base de datos
        FirebaseDatabase fbdb1 = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef2 = fbdb1.getReference("Ingresos").child("swappseminario@gmailcom");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Long count = dataSnapshot.getChildrenCount();
                //leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);


                String buscandoValoresDe="PRESTAMO";
                String Tablas="";
                String valor;
                String valores="";
                long result = 0;



                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    Tablas = Tablas + "\n" + child.getKey();

                    valor = child.child("Valor").getValue().toString();
                    if (child.child("Categoria").getValue().toString().equals(buscandoValoresDe)){

                        valores = valores +"\n"+valor;
                        result = result + Long.parseLong(valor);
                    }


                }

                DecimalFormat formateador = new DecimalFormat("###,###.###");
                valorCardPrest.setText("$ "+formateador.format(result));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });





        //Instancia a la base de datos
        FirebaseDatabase fbdb2 = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef3 = fbdb2.getReference("Ingresos").child("swappseminario@gmailcom");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Long count = dataSnapshot.getChildrenCount();
                //leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);


                String buscandoValoresDe="EXTRA";
                String Tablas="";
                String valor;
                String valores="";
                long result = 0;



                for (DataSnapshot child : dataSnapshot.getChildren()) {


                    Tablas = Tablas + "\n" + child.getKey();

                    valor = child.child("Valor").getValue().toString();
                    if (child.child("Categoria").getValue().toString().equals(buscandoValoresDe)){

                        valores = valores +"\n"+valor;
                        result = result + Long.parseLong(valor);
                    }


                }

                DecimalFormat formateador = new DecimalFormat("###,###.###");
                valorCardExtra.setText("$ "+formateador.format(result));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


















    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.gastos:
                Intent gastos = new Intent(getApplicationContext(), home.class); // Envie a otra activity, recibe contexto y activity destino.
                gastos.putExtra("dato1", idd);
                startActivity(gastos);
                break;

            case R.id.estadistica:
                Intent estadisticas = new Intent(getApplicationContext(), estadisticas.class); // Envie a otra activity, recibe contexto y activity destino.
                estadisticas.putExtra("dato1", idd);
                startActivity(estadisticas);
                break;

            case R.id.salario:
                Intent salario = new Intent(getApplicationContext(), form_salario.class); // Envie a otra activity, recibe contexto y activity destino.
                salario.putExtra("dato1", idd);
                startActivity(salario);
                break;

            case R.id.prestamo:
                Intent prestamo = new Intent(getApplicationContext(), form_prestamo.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(prestamo);
                break;

            case R.id.extra:
                Intent extra = new Intent(getApplicationContext(), form_extra.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(extra);
                break;
        }
    }
}
