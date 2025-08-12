package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AggiuntaDao{
    //aggiunta prodotto con categoria, visualizzazione dei prodotti di una categoria
    public void doSaveAggiunta(Aggiunta aggiunta) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Aggiunta (nome_cat, id_prodotto) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, aggiunta.getNomeCat());
            ps.setString(2, aggiunta.getIdProdotto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> doRetrieveByNomeCat(String nomeCat) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_prodotto FROM Aggiunta WHERE nome_cat=?");
            ps.setString(1, nomeCat);
            ResultSet rs = ps.executeQuery();

            List<String> idProdotti = new ArrayList<>();
            while(rs.next()) {
                idProdotti.add(rs.getString(1));
            }
            return idProdotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String doRetrieveByIdP(String idProdotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT nome_cat FROM Aggiunta WHERE id_prodotto=?");
            ps.setString(1, idProdotto);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getString(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doCancById(String idProdotto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM aggiunta where id_prodotto=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, idProdotto);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Delete error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
