package com.example.SwappDB;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.SwappDB.modelo.Gastos;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class estadisticas extends AppCompatActivity implements View.OnClickListener {

    private Button gastos;
    private Button ingresos;
    private TextView g_ingresos;
    private CardView hogar;
    private CardView graf_fact;
    private CardView graf_trans;
    private TextView enviar;
    private TextView enviarf;
    private TextView enviart;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);




        pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);


        ArrayList<PieEntry> yValues = new ArrayList<>();

        PieDataSet dataset = new PieDataSet(yValues, "BALANCE DE GASTOS");





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
                float result = 0;


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Tablas = Tablas + "\n" + child.getKey();


                    valor = child.child("Valor").getValue().toString();
                    valores = valores +"\n"+valor;
                    result = result + Long.parseLong(valor);



                }


                yValues.add(new PieEntry(result, "Total Gastos:"));
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });


        //Instancia a la base de datos
        FirebaseDatabase fdb1 = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef1 = fdb1.getReference("Gastos").child("swappseminario@gmailcom");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                Long count = dataSnapshot.getChildrenCount();
                //leeremos un objeto de tipo Gastos
                GenericTypeIndicator<Gastos> t = new GenericTypeIndicator<Gastos>() {};
                Gastos gastos = dataSnapshot.getValue(t);



                String Tablas="";
                String valor;
                String valores="";
                float result = 0;


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Tablas = Tablas + "\n" + child.getKey();


                    valor = child.child("Valor").getValue().toString();
                    valores = valores +"\n"+valor;
                    result = result + Long.parseLong(valor);



                }


                yValues.add(new PieEntry(result, "Total Ingresos:"));
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });





        dataset.setSliceSpace(8f);
        dataset.setSelectionShift(15f);
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData((dataset));
        data.setValueTextSize(25f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);

        gastos = (Button) findViewById(R.id.gastos);
        gastos.setOnClickListener(this);
        ingresos = (Button) findViewById(R.id.ingresos);
        ingresos.setOnClickListener(this);
        hogar = (CardView) findViewById(R.id.hogar);
        hogar.setOnClickListener(this);
        graf_fact = (CardView) findViewById(R.id.graf_fact);
        graf_fact.setOnClickListener(this);
        graf_trans = (CardView) findViewById(R.id.graf_trans);
        graf_trans.setOnClickListener(this);
        g_ingresos = (TextView) findViewById(R.id.g_ingresos);
        g_ingresos.setOnClickListener(this);
        enviar = (TextView) findViewById(R.id.enviar);
        enviar.setOnClickListener(this);
        enviarf = (TextView) findViewById(R.id.enviarf);
        enviarf.setOnClickListener(this);
        enviart = (TextView) findViewById(R.id.enviart);
        enviart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.gastos:
                Intent gastos = new Intent(getApplicationContext(), home.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(gastos);
                break;

            case R.id.ingresos:
                Intent ingresos = new Intent(getApplicationContext(), ingresos.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(ingresos);
                break;

            case R.id.g_ingresos:
                Intent g_ingresos = new Intent(getApplicationContext(), estadisticas_ingresos.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(g_ingresos);
                break;

            case R.id.hogar:
                Intent hogar = new Intent(getApplicationContext(), graf_hogar.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(hogar);
                break;

            case R.id.graf_fact:
                Intent graf_fact = new Intent(getApplicationContext(), graf_facturas.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(graf_fact);
                break;


            case R.id.enviar:
                Intent enviar = new Intent(getApplicationContext(), grafica_hogar.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(enviar);
                break;

            case R.id.enviarf:
                Intent enviarf = new Intent(getApplicationContext(), graf_facturas.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(enviarf);
                break;

            case R.id.enviart:
                Intent enviart = new Intent(getApplicationContext(), grafica_transporte.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(enviart);
                break;
        }
    }
}
