package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllergeniDao {
    public List<Allergeni> doRetrievAll(){
        try(Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Allergeni");
            ResultSet rs = ps.executeQuery();
            List<Allergeni> allergenis = new ArrayList<>();
            while(rs.next()){
                Allergeni a = new Allergeni();
                a.setNomeAll(rs.getString(1));

                allergenis.add(a);
            }
            return allergenis;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
