package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssAllDao{
    //ricerca dei prodotti per un certo allergene, ricerca degli allergeni in un dato prodotto
    public List<String> doRetrieveByNome(String nomeAll) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_prodotto FROM ass_all WHERE nome_all=?");
            ps.setString(1, nomeAll);
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
    public List<String> doRetrieveById(String idProdotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT nome_all FROM ass_all WHERE id_prodotto=?");
            ps.setString(1, idProdotto);
            ResultSet rs = ps.executeQuery();

            List<String> allergeni = new ArrayList<>();
            while(rs.next()) {
                allergeni.add(rs.getString(1));
            }
            return allergeni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(AssAll assAll){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ass_all (nome_all, id_prodotto) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, assAll.getNomeAll());
            ps.setString(2, assAll.getIdProdotto());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doCancById(String idProdotto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM ass_all WHERE id_prodotto=?");
            ps.setString(1, idProdotto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
