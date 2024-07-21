import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    private String dburl = "jdbc:mysql://localhost:3306/member";
    private String dbuname = "root";
    private String dbpassword = "root";
    private String dbdriver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(dbdriver); // Register MySQL JDBC driver
            con = DriverManager.getConnection(dburl, dbuname, dbpassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading database driver: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error establishing connection: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
        }
        return con;
    }

    public String insert(Member member) {
        Connection con = getConnection();
        String result = "Data entered Successfully";
        String sql = "INSERT INTO member (email,password) VALUES (?, ?)";
        if (con != null)
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getPassword());
            
            

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Data is not entered";
        } finally {
            // Close the connection in finally block to ensure it always gets closed
             {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
