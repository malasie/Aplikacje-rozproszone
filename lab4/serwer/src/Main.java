import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root", "");
            Statement statement = connection.createStatement();


            ResultSet resultset = statement.executeQuery("Select * from dane");
            while (resultset.next()) {
                System.out.println(resultset.getString("nazwisko"));
            }


        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}