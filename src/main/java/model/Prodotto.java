package model;

public class Prodotto {

    private String idProdotto;
    private String nomeProdotto;
    private double prezzo;
    private String produttore;
    private String descrizione;
    private int qtDeposito;

    public String getIdProdotto() {
        return idProdotto;
    }
    public void setIdProdotto(String idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }
    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public double getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    public String getProduttore() {
        return produttore;
    }
    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQtDeposito() {
        return qtDeposito;
    }
    public void setQtDeposito(int qtDeposito) {
        this.qtDeposito = qtDeposito;
    }

}
