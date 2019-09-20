package com.example.SwappDB.modelo;

public class Gastos {

    //estas variables deben ir exactamente como enstan en los child del nodo a consultar
    private String Categoria;
    private String Fecha;
    private String Nota;
    private String Tipo;
    private Long Valor;
    private String Tablas;

    public Gastos(){}

    public String getTablas() {
        return Tablas;
    }

    public void setTablas(String tablas) {
        Tablas = tablas;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        this.Categoria = categoria;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public Long getValor() {
        return Valor;
    }

    public void setValor(Long valor) {
        Valor = valor;
    }

    @Override
    public String toString() {
        return "Gasto: \n" +
                "  Categoria=" + Categoria + "\n" +
                "  Fecha=" + Fecha + "\n" +
                "  Nota=" + Nota + "\n" +
                "  Tipo=" + Tipo + "\n" +
                "  Valor=" + Valor + "\n";
    }
}
