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

public class home extends AppCompatActivity implements View.OnClickListener {

    private CardView hogar; //Nombre de la tarjeta de hogar
    private CardView factura; //Nombre de la tarjeta de factura
    private CardView transporte;
    private String idd;
    private TextView valorTotal;
    private TextView valorCardHome;
    private TextView valorCardFact;
    private TextView valorCardTrans;
    private Button ingresos;
    private Button estadisticas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        hogar = (CardView) findViewById(R.id.salario);
        valorTotal = (TextView) findViewById(R.id.totalCosts);
        hogar.setOnClickListener(this);
        factura = (CardView) findViewById(R.id.factura);
        factura.setOnClickListener(this);
        transporte = (CardView) findViewById(R.id.gastos4);
        transporte.setOnClickListener(this);
        ingresos = (Button) findViewById(R.id.ingresos);
        ingresos.setOnClickListener(this);
        estadisticas = (Button) findViewById(R.id.estadistica);
        estadisticas.setOnClickListener(this);
        String dato1 = getIntent().getStringExtra("dato");
        idd=dato1;
        BaseDatosF consult = new BaseDatosF();
        valorCardHome = (TextView) findViewById(R.id.costCasa);
        valorCardFact = (TextView) findViewById(R.id.costF);
        valorCardTrans = (TextView) findViewById(R.id.costT);
        factura.setOnClickListener(this);



        //Instancia a la base de datos
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("Gastos").child("swappseminario@gmailcom");

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
        DatabaseReference myRef1 = fbdb.getReference("Gastos").child("swappseminario@gmailcom");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Long count = dataSnapshot.getChildrenCount();
                //leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);


                String buscandoValoresDe="VIVIENDA";
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
                valorCardHome.setText("$ "+formateador.format(result));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });











        //Instancia a la base de datos
        FirebaseDatabase fbdb1 = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef2 = fbdb1.getReference("Gastos").child("swappseminario@gmailcom");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Long count = dataSnapshot.getChildrenCount();
                //leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);


                String buscandoValoresDe="FACTURAS";
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
                valorCardFact.setText("$ "+formateador.format(result));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });





        //Instancia a la base de datos
        FirebaseDatabase fbdb2 = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef3 = fbdb2.getReference("Gastos").child("swappseminario@gmailcom");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Long count = dataSnapshot.getChildrenCount();
                //leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);


                String buscandoValoresDe="TRANSPORTE";
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
                valorCardTrans.setText("$ "+formateador.format(result));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

























    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ingresos:
                Intent ingresos = new Intent(getApplicationContext(), ingresos.class); // Envie a otra activity, recibe contexto y activity destino.
                ingresos.putExtra("dato1", idd);
                startActivity(ingresos);
                break;

            case R.id.estadistica:
                Intent estadisticas = new Intent(getApplicationContext(), estadisticas.class); // Envie a otra activity, recibe contexto y activity destino.
                estadisticas.putExtra("dato1", idd);
                startActivity(estadisticas);
                break;

            case R.id.salario:
                Intent hogar = new Intent(getApplicationContext(), form_home.class); // Envie a otra activity, recibe contexto y activity destino.
                hogar.putExtra("dato1", idd);
                startActivity(hogar);
                break;

            case R.id.factura:
                Intent factura = new Intent(getApplicationContext(), form_facturas.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(factura);
                break;

            case R.id.gastos4:
                Intent transporte = new Intent(getApplicationContext(), form_transporte.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(transporte);
                break;
        }
    }
}