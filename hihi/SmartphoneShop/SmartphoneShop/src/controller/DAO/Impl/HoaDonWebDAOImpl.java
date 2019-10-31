package controller.DAO.Impl;

import controller.DAO.HoaDonWebDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HoaDon;

public class HoaDonWebDAOImpl extends RootDAO implements HoaDonWebDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;
    private SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MI:SS");

    private Date formatDate(String date) {
        java.util.Date dateU = null;
        try {
            dateU = df.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date dateSQL = new Date(dateU.getTime());
        return dateSQL;
    }

    @Override
    public void addHoaDonOnline(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDonWeb(maKH, ngayLap, total) VALUES(?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, hoaDon.getMaKH());
            ppst.setDate(2, formatDate(hoaDon.getNgayLap()));
            ppst.setInt(3, hoaDon.getTotal());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editHoaDonOnline(HoaDon hoaDon) {
        String sql = "UPDATE HoaDonWeb SET maKH = ?, ngayLap = ?, total = ? WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, hoaDon.getMaKH());
            ppst.setDate(2, formatDate(hoaDon.getNgayLap()));
            ppst.setInt(3, hoaDon.getTotal());
            ppst.setInt(4, hoaDon.getMaHD());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delHoaDonOnline(int maHD) {
        String sql = "DELETE FROM HoaDonWeb WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public HoaDon getHoaDonOnline(int maHD) {
        String sql = "SELECT * FROM HoaDonWeb WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(maHD);
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(df.format(rs.getDate("ngayLap")));
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                return hoaDon;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByKH(int maKH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByNgayLap(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HoaDon> sortByNgayMua() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HoaDon> sortByTotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HoaDon> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
