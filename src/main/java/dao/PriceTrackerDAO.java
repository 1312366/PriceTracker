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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    //<editor-fold defaultstate="collapsed" desc="GET PRICE PRODUCT">
    public List<PriceDTO> getPriceProduct(String link) {
        Connection con = getConnection();
        List<PriceDTO> resultList =new ArrayList<>();
       
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "EXEC getPriceProduct ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, link);
            rs = ps.executeQuery();
            while (rs.next()) {
                 PriceDTO priceDTO = new PriceDTO();
                priceDTO.setPrice(rs.getInt("PRODUCT_PRICE"));
                priceDTO.setDateUpdated(rs.getString("DATE_UPDATED"));
                resultList.add(priceDTO);
            }
            con.close();
            return resultList;
        } catch (Exception ex) {
            ex.toString();
            return resultList;
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
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GET PRICE HISTORY">
    public List<PriceDTO> getPriceHistory(String link) {
        Connection con = getConnection();
        List<PriceDTO> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM PRODUCTS_PRICE WHERE PRODUCT_LINK= ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, link);
            rs = ps.executeQuery();
            while (rs.next()) {
                PriceDTO priceDTO = new PriceDTO();
                priceDTO.setDateUpdated(rs.getString("DATE_UPDATED"));
                priceDTO.setPrice(rs.getInt("PRODUCT_PRICE"));
                list.add(priceDTO);
            }
            con.close();
            return list;
        } catch (Exception ex) {
            ex.toString();
            return list;
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

    }
    //</editor-fold>
    
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
                ps.setString(3, dateUpdate);
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
    
    //<editor-fold defaultstate="collapsed" desc="SAVE USER REQUEST">
    public Integer saveUserRequest(String url, Integer sessionId) {
        Connection con = getConnection();
        Integer result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "EXEC saveUserRequest ?, ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, sessionId);
            ps.setString(2, url);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("RESULT");
            }
            con.close();
            return result;
        } catch (Exception ex) {
            ex.toString();
            return result;
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
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getSuggestUrl">
    public List<PriceDTO> getSuggestUrl(String link) {
        Connection con = getConnection();
        List<PriceDTO> resultList =new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "EXEC GET_SUGGEST_URLS ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, link);
            rs = ps.executeQuery();
            while (rs.next()) {
                 PriceDTO priceDTO = new PriceDTO();
                priceDTO.setUrl(rs.getString("PRODUCT_LINK"));
                resultList.add(priceDTO);
            }
            con.close();
            return resultList;
        } catch (Exception ex) {
            ex.toString();
            return resultList;
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
        
    }
    //</editor-fold>
}

