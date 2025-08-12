package model;

import java.sql.*;

public class PagamentoDAO {
    public void doSave(Pagamento pagamento) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO pagamento (n_conto, intestatario, metodo, scadenza, cvv) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,pagamento.getnConto());
            ps.setString(2,pagamento.getIntestatario());
            ps.setString(3,pagamento.getCircuito());
            ps.setString(4, pagamento.getScadenza());
            ps.setInt(5, pagamento.getCvv());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doCanc(String nConto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM pagamento WHERE n_conto=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,nConto);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pagamento doRetrieveByNConto(String nConto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM pagamento WHERE n_conto=?");
            ps.setString(1,nConto);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Pagamento p = new Pagamento();
                p.setnConto(rs.getString(1));
                p.setIntestatario(rs.getString(2));
                p.setCircuito(rs.getString(3));
                p.setScadenza(rs.getString(4));
                p.setCvv(rs.getInt(5));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
