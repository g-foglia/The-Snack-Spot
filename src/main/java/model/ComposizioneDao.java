package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComposizioneDao{
    public List<Composizione> doRetrieveChartById(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_carrello, id_prodotto, quantità FROM Composizione WHERE id_carrello=?");
            ps.setInt(1, idCarrello);
            ResultSet rs = ps.executeQuery();

            List<Composizione> composizioni = new ArrayList<>();
            while(rs.next()) {
                Composizione c = new Composizione();
                c.setIdCarrello(rs.getInt(1));
                c.setIdProdotto(rs.getString(2));
                c.setQuantita(rs.getInt(3));
                composizioni.add(c);
            }
            return composizioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Composizione doRetrieveById(int idCarrello, String idProdotto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_carrello, id_prodotto, quantità FROM Composizione WHERE id_carrello=? and id_prodotto=?");
            ps.setInt(1, idCarrello);
            ps.setString(2,idProdotto);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Composizione c = new Composizione();
                c.setIdCarrello(rs.getInt(1));
                c.setIdProdotto(rs.getString(2));
                c.setQuantita(rs.getInt(3));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doUpdate(Composizione composizione){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE composizione SET quantità=? WHERE id_carrello=? AND id_prodotto=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, composizione.getQuantita());
            ps.setInt(2, composizione.getIdCarrello());
            ps.setString(3, composizione.getIdProdotto());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doCanc(String idProdotto, int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Composizione WHERE id_prodotto=? AND id_carrello=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,idProdotto);
            ps.setInt(2,idCarrello);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Composizione composizione){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO composizione (id_carrello, id_prodotto, quantità) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,composizione.getIdCarrello());
            ps.setString(2,composizione.getIdProdotto());
            ps.setInt(3,composizione.getQuantita());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public double returnSubTotal(int idCarrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT subtotale FROM ( SELECT subquery.id_carrello, SUM(totale) AS subtotale FROM ( SELECT c.id_carrello, (c.quantità * p.prezzo) AS totale FROM composizione c, prodotto p WHERE c.id_prodotto = p.id_prodotto) AS subquery GROUP BY subquery.id_carrello) AS risultato WHERE risultato.id_carrello =?");
            ps.setInt(1,idCarrello);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                double subtotale = rs.getDouble(1);
                return subtotale;
            }
            return -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
