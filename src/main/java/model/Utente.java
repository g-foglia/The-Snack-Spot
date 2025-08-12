package model;

public class Utente {
    private String nome;
    private String cognome;
    private String via;
    private String citta;

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }
    private String cellulare;
    private int cap;
    private String nc;
    private String email;
    private String password;
    private boolean tipo;
    public String getIdGuest() {
        return idGuest;
    }
    public void setIdGuest(String idGuest) {
        this.idGuest = idGuest;
    }
    private String idGuest;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }



    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getNc() {
        return nc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getEmailCliente() {
        return email;
    }

    public void setEmailCliente(String email) {
        this.email = email;
    }
}
