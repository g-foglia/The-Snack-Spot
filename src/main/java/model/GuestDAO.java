package model;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
    //visualizzazione tutti gli id

    public List<Guest> doRetrievAll(){
        try(Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Guest");
            ResultSet rs = ps.executeQuery();
            List<Guest> guests = new ArrayList<>();
            while(rs.next()){
                Guest guest = new Guest();
                guest.setIdGuest(rs.getString(1));

                guests.add(guest);
            }
            return guests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Guest doRetrieveBiId(String idGuest){
        try(Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Guest WHERE id_guest=?");
            ps.setString(1,idGuest);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Guest guest = new Guest();
                guest.setIdGuest(rs.getString(1));

                return guest;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Guest guest){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO guest (id_guest) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, guest.getIdGuest());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
