package com.example.SwappDB;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class estadisticas_ingresos extends AppCompatActivity implements View.OnClickListener {
    private Button gastos;
    private Button ingresos;
    private TextView g_gastos;
    private CardView graf_salario;
    private CardView graf_prest;
    private CardView graf_extra;
    private TextView enviar;
    private TextView enviarf;
    private TextView enviart;
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_ingresos);

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

        yValues.add(new PieEntry(100f, "SALARIO"));
        yValues.add(new PieEntry(50f, "PRESTAMO"));
        yValues.add(new PieEntry(120f, "INGRESOS EXTRA"));


        dataset.setSliceSpace(8f);
        dataset.setSelectionShift(15f);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataset));
        data.setValueTextSize(25f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);




        gastos = (Button) findViewById(R.id.gastos);
        gastos.setOnClickListener(this);
        ingresos = (Button) findViewById(R.id.ingresos);
        ingresos.setOnClickListener(this);
        graf_salario = (CardView) findViewById(R.id.graf_salario);
        graf_salario.setOnClickListener(this);
        graf_prest = (CardView) findViewById(R.id.graf_prest);
        graf_prest.setOnClickListener(this);
        graf_extra = (CardView) findViewById(R.id.graf_extra);
        graf_extra.setOnClickListener(this);
        g_gastos = (TextView) findViewById(R.id.g_gastos);
        g_gastos.setOnClickListener(this);
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

            case R.id.g_gastos:
                Intent g_gastos = new Intent(getApplicationContext(), estadisticas.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(g_gastos);
                break;

            case R.id.graf_salario:
                Intent graf_salario = new Intent(getApplicationContext(), graf_salario.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(graf_salario);
                break;

            case R.id.graf_prest:
                Intent graf_prest = new Intent(getApplicationContext(), graf_prestamo.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(graf_prest);
                break;

            case R.id.graf_extra:
                Intent graf_extra = new Intent(getApplicationContext(), graf_extra.class); // Envie a otra activity, recibe contexto y activity destino.
                startActivity(graf_extra);
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
