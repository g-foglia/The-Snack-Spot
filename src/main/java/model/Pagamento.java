package model;


public class Pagamento{
    public String getnConto() {
        return nConto;
    }
    public void setnConto(String nConto) {
        this.nConto = nConto;
    }

    public String getIntestatario() {
        return intestatario;
    }

    public void setIntestatario(String intestatario) {
        this.intestatario = intestatario;
    }

    public String getCircuito() {
        return metodo;
    }

    public void setCircuito(String metodo) {
        this.metodo = metodo;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public int getCvv() {
        return cvv;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    private String nConto;
    private String intestatario;
    private String metodo;
    private String scadenza;
    private int cvv;
}