package dao;

import dto.PriceDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.jtds.jdbc.*;
import net.sourceforge.jtds.jdbcx.*;
/**
 *
 * @author Minh Tran
 */
public class PriceTrackerDAO {

    //<editor-fold defaultstate="collapsed" desc="GET CONNECTION">
    public static Connection getConnection() {
        Connection connect = null;
        Properties prop = new Properties();
        try {
            String host = "localhost";
            String database = "PriceTracker";
            String username = "sa";
            String password = "sa";
            String dbURL = "jdbc:jtds:sqlserver://" + host + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection(dbURL);

        } catch (Exception e) {
        }
        return connect;
    }

    //</editor-fold>
    public PriceDTO getPriceProduct(String link) {
        Connection con = getConnection();
        PriceDTO priceDTO = new PriceDTO();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "EXEC getPriceProduct ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, link);
            rs = ps.executeQuery();
            while (rs.next()) {
                priceDTO.setLowestPrice(rs.getInt("LOWEST_PRICE"));
                priceDTO.setAvgPrice(rs.getInt("AVG_PRICE"));
                priceDTO.setHighestPrice(rs.getInt("HIGHEST_PRICE"));
            }
            con.close();
        } catch (Exception ex) {
            ex.toString();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return priceDTO;

    }

    //<editor-fold defaultstate="collapsed" desc="INSERT DATA TO SQL SERVER ">
    public void insertDataToDB() {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            SimpleDateFormat dateHourFormat = new SimpleDateFormat("HH_dd_MM_yyyy");
            SimpleDateFormat dateHourFormat2 = new SimpleDateFormat("YYYY-MM-dd");
            Date currentDate = new Date();
            String dateUpdate = dateHourFormat2.format(currentDate);
            String csvFile = "C:\\Users\\Administrator\\Downloads/" + dateHourFormat.format(currentDate) + "-data.csv";
            String line = "";
            String cvsSplitBy = ";";
            String sql = "INSERT INTO PRODUCTS_PRICE(PRODUCT_LINK,PRODUCT_PRICE,DATE_UPDATED) VALUES(?,?,?);";
            ps = (PreparedStatement) con.prepareStatement(sql);
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                ps.setString(1, data[0]);
                ps.setInt(2, Integer.parseInt(data[1]));
                ps.setString(3,dateUpdate);
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
        } catch (IOException | SQLException e) {
            e.getMessage().toString();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    //</editor-fold>
}
