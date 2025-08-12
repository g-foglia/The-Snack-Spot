package model;
public class Effettua {
    public String getnConto() {
        return nConto;
    }

    public void setnConto(String nConto) {
        this.nConto = nConto;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    private String nConto;
    private String email;

}