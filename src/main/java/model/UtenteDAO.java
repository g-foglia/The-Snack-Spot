package model;

import java.sql.*;

public class UtenteDAO {
    public Utente doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT email, password_, nome, cognome, via, città, cap, nc, cellulare, tipo, id_guest FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setEmailCliente(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setVia(rs.getString(5));
                u.setCitta(rs.getString(6));
                u.setCap(rs.getInt(7));
                u.setNc(rs.getString(8));
                u.setCellulare(rs.getString(9));
                u.setTipo(rs.getBoolean(10));
                u.setIdGuest(rs.getString(11));

                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (email, password_, nome, cognome, via, città, cap, nc, cellulare, tipo, id_guest) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getEmailCliente());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getVia());
            ps.setString(6, utente.getCitta());
            ps.setInt(7, utente.getCap());
            ps.setString(8, utente.getNc());
            ps.setString(9, utente.getCellulare());
            ps.setBoolean(10, utente.getTipo());
            ps.setString(11, utente.getIdGuest());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Utente utente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente SET password=?, nome=?, cognome=?, via=?, città=?, cap=?, nc=?, cellulare=?, tipo=?, id_guest=? WHERE email=?");

            ps.setString(1, utente.getPassword());
            ps.setString(2, utente.getNome());
            ps.setString(3, utente.getCognome());
            ps.setString(4, utente.getVia());
            ps.setString(5, utente.getCitta());
            ps.setInt(6, utente.getCap());
            ps.setString(7, utente.getNc());
            ps.setString(8, utente.getCellulare());
            ps.setBoolean(9, utente.getTipo());
            ps.setString(10, utente.getIdGuest());
            ps.setString(11, utente.getEmailCliente());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Update error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doCheckEmail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
