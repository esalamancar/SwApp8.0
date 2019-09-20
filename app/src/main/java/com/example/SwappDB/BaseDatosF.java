package com.example.SwappDB;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by erick on 02/05/2019.
 */

public class BaseDatosF implements Serializable {

    private String txtvalor;
    private String txtfecha;
    private String txtnumfact;
    private String txtnota;
    private String id;
    private String correo;
    private DataSnapshot dataSnapshot;

    public BaseDatosF() {
    }

    public BaseDatosF(String txtvalor, String txtfecha, String txtnumfact, String txtnota, Boolean  radi, String id) {
        this.txtvalor = txtvalor;
        this.txtfecha = txtfecha;
        this.txtnumfact = txtnumfact;
        this.txtnota = txtnota;
        this.id = id;
    }

    public String getTxtvalor() {
        return txtvalor;
    }

    public void setTxtvalor(String txtvalor) {
        this.txtvalor = txtvalor;
    }

    public String getTxtfecha() {
        return txtfecha;
    }

    public void setTxtfecha(String txtfecha) {
        this.txtfecha = txtfecha;
    }

    public String getTxtnumfact() {
        return txtnumfact;
    }

    public void setTxtnumfact(String txtnumfact) {
        this.txtnumfact = txtnumfact;
    }

    public String getTxtnota() {
        return txtnota;
    }

    public void setTxtnota(String txtnota) {
        this.txtnota = txtnota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void TransaccionEnvio(String tabla, String mail, String numerofactu, String categoria, String fecha, String nota, String valor, String tipo){


        if ((mail.trim().length() == 0) || (mail.isEmpty())){
            mail=correo;
            correo=mail;
        }
        else{
            correo = mail.replace("\\.","");
            correo = mail.replace(".","");
        }


        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();
        BaseDatosF datosenviar = new BaseDatosF();

        Map<String, Object> personaMap = new HashMap<>();

        personaMap.put("Categoria",categoria);
        personaMap.put("Fecha",fecha);
        personaMap.put("Nota",nota);
        personaMap.put("Valor",valor);
        personaMap.put("Tipo",tipo);


        mDatabase.child(tabla).child(correo).child(numerofactu).setValue(personaMap);
    }

    public String[] TransaccionCosult(){

        int index=0;
        final String[] Dato = new String[index];
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Gastos").child("swappseminario@gmailcom").child("S4-33654").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Dato[index] = dataSnapshot.child("Valor").getValue().toString();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String Dato = "ErrorConsulta";
            }
        });

        return Dato;
    }

    public void Consultar(){

    }
}
