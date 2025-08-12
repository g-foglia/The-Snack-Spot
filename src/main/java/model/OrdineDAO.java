package model;
import com.google.protobuf.Internal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class OrdineDAO {
    // inserimento ordine nel sistema, ricerca ordine per numero che lo identifica

    public void doSaveOrdine(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine (n_ordine, stato, totale, data_ordine, data_consegna, n_conto, id_carrello) VALUES(null,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ordine.getStato());
            ps.setDouble(2, ordine.getTotale());
            ps.setString(3,new SimpleDateFormat("dd/MM/yy").format(ordine.getDataOrdine().getTime()));
            ps.setString(4,new SimpleDateFormat("dd/MM/yy").format(ordine.getDataConsegna().getTime()));
            ps.setString(5,ordine.getnConto());
            ps.setInt(6,ordine.getIdCarrello());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Ordine doRetrieveByN(int nOrdine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT n_ordine, stato, totale, dataOrdine, dataConsegna, nConto, idCarrello FROM Ordine WHERE nOrdine=?");
            ps.setInt(1, nOrdine);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ordine p = new Ordine();
                p.setnOrdine(rs.getInt(1));
                p.setStato(rs.getString(2));
                p.setTotale(rs.getDouble(3));
                p.setDataOrdine(new GregorianCalendar(Integer.parseInt(rs.getString(4).substring(6)),Integer.parseInt(rs.getString(4).substring(3,5)),Integer.parseInt(rs.getString(4).substring(0,2))));
                p.setDataConsegna(new GregorianCalendar(Integer.parseInt(rs.getString(5).substring(6)),Integer.parseInt(rs.getString(5).substring(3,5)),Integer.parseInt(rs.getString(5).substring(0,2))));
                p.setnConto(rs.getString(6));
                p.setIdCarrello(rs.getInt(7));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetrieveByEmail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ordine WHERE id_carrello IN (SELECT c.id_carrello FROM carrello c WHERE email=?)");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            List<Ordine> ordini = new ArrayList<>();
            while(rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setnOrdine(rs.getInt(1));
                ordine.setStato(rs.getString(2));
                ordine.setTotale(rs.getDouble(3));
                ordine.setDataOrdine(new GregorianCalendar(Integer.parseInt(rs.getString(4).substring(6)),Integer.parseInt(rs.getString(4).substring(3,5))-1,Integer.parseInt(rs.getString(4).substring(0,2))));
                ordine.setDataConsegna(new GregorianCalendar(Integer.parseInt(rs.getString(5).substring(6)),Integer.parseInt(rs.getString(5).substring(3,5))-1,Integer.parseInt(rs.getString(5).substring(0,2))));
                ordine.setnConto(rs.getString(6));
                ordine.setIdCarrello(rs.getInt(7));

                ordini.add(ordine);
            }
            return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> returnProdottiByNOrdine(int nOrdine){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT p.id_prodotto, p.nome_prodotto, p.prezzo, p.produttore, p.descrizione, p.qt_deposito FROM prodotto p, composizione c, carrello ca, ordine o WHERE p.id_prodotto=c.id_prodotto AND ca.id_carrello=o.id_carrello AND c.id_carrello=ca.id_carrello AND o.n_ordine=?");
            ps.setInt(1, nOrdine);
            ResultSet rs = ps.executeQuery();

            List<Prodotto> prodotti = new ArrayList<>();
            while(rs.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getString(1));
                prodotto.setNomeProdotto(rs.getString(2));
                prodotto.setPrezzo(rs.getDouble(3));
                prodotto.setProduttore(rs.getString(4));
                prodotto.setDescrizione(rs.getString(5));
                prodotto.setIdProdotto(rs.getString(6));

                prodotti.add(prodotto);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> returnQuantitaProdotti(int nOrdine){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT c.quantità FROM prodotto p, composizione c, carrello ca, ordine o WHERE p.id_prodotto=c.id_prodotto AND ca.id_carrello=o.id_carrello AND c.id_carrello=ca.id_carrello AND o.n_ordine=?");
            ps.setInt(1, nOrdine);
            ResultSet rs = ps.executeQuery();

            List<Integer> quantita = new ArrayList<>();
            while(rs.next()) {
                quantita.add(rs.getInt(1));
            }
            return quantita;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
