package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarrelloDao{

    public void doUpdate(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Carrello SET sub_totale=?, id_guest=?, email=? WHERE id_carrello=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, carrello.getSubTotale());
            ps.setString(2, carrello.getIdGuest());
            ps.setString(3, carrello.getEmail());
            ps.setInt(4, carrello.getIdCarrello());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello returnActiveChartByEmail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM carrello WHERE email=? and id_carrello NOT IN (SELECT id_carrello FROM ordine)");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setIdCarrello(rs.getInt(1));
                c.setSubTotale(rs.getDouble(2));
                c.setIdGuest(rs.getString(3));
                c.setEmail(rs.getString(4));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello returnActiveChartByIdGuest(String idGuest){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM carrello WHERE id_guest=? and id_carrello NOT IN (SELECT id_carrello FROM ordine)");
            ps.setString(1,idGuest);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setIdCarrello(rs.getInt(1));
                c.setSubTotale(rs.getDouble(2));
                c.setIdGuest(rs.getString(3));
                c.setEmail(rs.getString(4));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Carrello doRetrieveById(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_carrello, sub_totale, id_guest, email FROM Carrello WHERE id_carrello=?");
            ps.setInt(1, idCarrello);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setIdCarrello(rs.getInt(1));
                c.setSubTotale(rs.getDouble(2));
                c.setIdGuest(rs.getString(3));
                c.setEmail(rs.getString(4));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO carrello (id_carrello, sub_totale, id_guest, email) VALUES(null,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, carrello.getSubTotale());
            ps.setString(2, carrello.getIdGuest());
            ps.setString(3, carrello.getEmail());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int idCarrello = rs.getInt(1);
            return idCarrello;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_carrello, sub_totale, id_guest, email FROM Carrello WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setIdCarrello(rs.getInt(1));
                c.setSubTotale(rs.getFloat(2));
                c.setIdGuest(rs.getString(3));
                c.setEmail(rs.getString(4));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetrieveByIdGuest(String idGuest) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_carrello, sub_totale, id_guest, email FROM Carrello WHERE id_guest=?");
            ps.setString(1, idGuest);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setIdCarrello(rs.getInt(1));
                c.setSubTotale(rs.getFloat(2));
                c.setIdGuest(rs.getString(3));
                c.setEmail(rs.getString(4));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doCanc(int idCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM carrello WHERE id_carrello=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,idCarrello);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
