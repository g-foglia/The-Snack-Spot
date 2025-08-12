package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO{
    public List<Categorie> doRetrievAll(){
        try(Connection connection = ConPool.getConnection()){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categorie");
            ResultSet rs = ps.executeQuery();
            List<Categorie> categories = new ArrayList<>();
            while(rs.next()){
                Categorie c = new Categorie();
                c.setNomeCat(rs.getString(1));


                categories.add(c);
            }
            return categories;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
