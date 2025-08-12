package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EffettuaDao {

    // inserimento, visualizzazione dei metodi di pagamento di un utente , cancellazione un metodo di pagamento per un dato utente

    public void doSaveEff(Effettua effettua) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO effettua (n_conto, email) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, effettua.getnConto());
            ps.setString(2, effettua.getEmail());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> doRetrieveById(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT n_conto FROM effettua WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            List<String> conti = new ArrayList<>();
            while(rs.next()) {
                conti.add(rs.getString(1));
            }
            return conti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doCanc(String email,String nConto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM effettua WHERE email=? and n_conto=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,email);
            ps.setString(2,nConto);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Effettua doRetrieve(String nConto, String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM effettua WHERE email=? and n_conto=?");
            ps.setString(1, email);
            ps.setString(2,nConto);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Effettua e = new Effettua();
                e.setEmail(rs.getString(1));
                e.setnConto(rs.getString(2));
                return e;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
