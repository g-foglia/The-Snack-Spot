package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDao {

    public Prodotto doRetrieveById(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_prodotto, nome_prodotto, prezzo, produttore, descrizione, qt_deposito FROM prodotto WHERE id_prodotto=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Prodotto p = new Prodotto();
                p.setIdProdotto(rs.getString(1));
                p.setNomeProdotto(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setProduttore(rs.getString(4));
                p.setDescrizione(rs.getString(5));
                p.setQtDeposito(rs.getInt(6));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrievAll(){
        try(Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodotto");
            ResultSet rs = ps.executeQuery();
            List<Prodotto> prodotti = new ArrayList<>();
            while(rs.next()){
                Prodotto p = new Prodotto();
                p.setIdProdotto(rs.getString(1));
                p.setNomeProdotto(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setProduttore(rs.getString(4));
                p.setDescrizione(rs.getString(5));
                p.setQtDeposito(rs.getInt(6));

                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doSearch(String key){
        try(Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodotto WHERE INSTR(nome_prodotto,?)");
            ps.setString(1,key);
            ResultSet rs = ps.executeQuery();

            List<Prodotto> prodotti = new ArrayList<>();
            while(rs.next()){
                Prodotto p = new Prodotto();
                p.setIdProdotto(rs.getString(1));
                p.setNomeProdotto(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setProduttore(rs.getString(4));
                p.setDescrizione(rs.getString(5));
                p.setQtDeposito(rs.getInt(6));

                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto (id_prodotto, nome_prodotto, prezzo, produttore, descrizione, qt_deposito) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,prodotto.getIdProdotto());
            ps.setString(2,prodotto.getNomeProdotto());
            ps.setDouble(3,prodotto.getPrezzo());
            ps.setString(4,prodotto.getProduttore());
            ps.setString(5,prodotto.getDescrizione());
            ps.setInt(6,prodotto.getQtDeposito());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE prodotto SET nome_prodotto=?, prezzo=?, produttore=?, descrizione=?, qt_deposito=? WHERE id_prodotto=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,prodotto.getNomeProdotto());
            ps.setDouble(2,prodotto.getPrezzo());
            ps.setString(3,prodotto.getProduttore());
            ps.setString(4,prodotto.getDescrizione());
            ps.setInt(5,prodotto.getQtDeposito());
            ps.setString(6,prodotto.getIdProdotto());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doCanc(String idProdotto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE prodotto SET qt_deposito=0 WHERE id_prodotto=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,idProdotto);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
