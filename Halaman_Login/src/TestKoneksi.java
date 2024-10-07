import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestKoneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_kasir";  
    private static final String USER = "root";  
    private static final String PASSWORD = ""; 

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Mencoba koneksi ke database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Koneksi ke database berhasil!");

                // Menambahkan data baru ke tabel profil
                String insertQuery = "INSERT INTO profil (id_user, alamat, no_telepon, nama_toko) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                
                // Mengisi nilai untuk placeholder
                preparedStatement.setInt(1, 1); // id_user
                preparedStatement.setString(2, "Jl. Durian, Kota Tegal"); // alamat
                preparedStatement.setString(3, "081234565384"); // no_telepon
                preparedStatement.setString(4, "Toko Sembako"); // nama_toko

                // Eksekusi query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " data berhasil ditambahkan!");

            }
        } catch (SQLException e) {
            System.out.println("Gagal terhubung ke database: " + e.getMessage());
        } finally {
            // Menutup koneksi jika terbuka
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Gagal menutup koneksi: " + e.getMessage());
                }
            }
        }
    }
}
