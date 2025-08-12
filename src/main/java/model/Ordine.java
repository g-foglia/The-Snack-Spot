package model;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Ordine {
    public int getnOrdine() {
        return nOrdine;
    }

    public void setnOrdine(int nOrdine) {
        this.nOrdine = nOrdine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }





    public String getnConto() {
        return nConto;
    }

    public void setnConto(String nConto) {
        this.nConto = nConto;
    }


    private int nOrdine;
    private String stato;
    private double totale;

    public GregorianCalendar getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(GregorianCalendar dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public GregorianCalendar getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(GregorianCalendar dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    private GregorianCalendar dataOrdine;
    private GregorianCalendar dataConsegna;

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    private int idCarrello;
    private String nConto;
}