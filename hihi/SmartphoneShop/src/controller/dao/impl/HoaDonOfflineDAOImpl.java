package controller.DAO.Impl;

import controller.DAO.HoaDonOfflineDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HoaDon;

public class HoaDonOfflineDAOImpl extends RootDAO implements HoaDonOfflineDAO {

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
    public void addHoaDonOffline(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDonOffline(maNV, maKH, ngayLap, total) VALUES(?,?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, hoaDon.getMaNV());
            ppst.setInt(2, hoaDon.getMaKH());
            ppst.setDate(3, formatDate(hoaDon.getNgayLap()));
            ppst.setInt(4, hoaDon.getTotal());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editHoaDonOffline(HoaDon hoaDon) {
        String sql = "UPDATE HoaDonOffline SET maNV = ?, maKH = ?, ngayLap = ?, total = ? WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, hoaDon.getMaNV());
            ppst.setInt(2, hoaDon.getMaKH());
            ppst.setDate(3, formatDate(hoaDon.getNgayLap()));
            ppst.setInt(4, hoaDon.getTotal());
            ppst.setInt(5, hoaDon.getMaHD());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delHoaDonOffline(int maHD) {
        String sql = "DELETE FROM HoaDonOffline WHERE maHD = ?;";
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
    public HoaDon getHoaDonOffline(int maHD) {
        String sql = "SELECT * FROM HoaDonOffline WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(maHD);
                hoaDon.setMaNV(rs.getInt("maNV"));
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
    public ArrayList<HoaDon> getHoaDonByNV(int maNV) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonOffline WHERE maNV = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maNV);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(df.format(rs.getDate("ngayLap")));
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByKH(int maKH) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonOffline WHERE maKH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maKH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(df.format(rs.getDate("ngayLap")));
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByNgayLap(String date) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonOffline WHERE ngayLap = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setDate(1, formatDate(date));
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(df.format(rs.getDate("ngayLap")));
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonOffline;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(df.format(rs.getDate("ngayLap")));
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonOfflineDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public ArrayList<HoaDon> sortByNgayMua() {
        ArrayList<HoaDon> list = getAll();
        Collections.sort(list, new Comparator<HoaDon>() {

            @Override
            public int compare(HoaDon o1, HoaDon o2) {
                return 0;
                
            }
        });
        return list;

    }

    @Override
    public ArrayList<HoaDon> sortByTotal() {
        ArrayList<HoaDon> list = getAll();
        Collections.sort(list, new Comparator<HoaDon>() {

            @Override
            public int compare(HoaDon o1, HoaDon o2) {
                return 0;
                
            }
        });
        return list;
    }

    @Override
    public HoaDon getHoaDonOffline(String name) {
        return null;
    }

    private Connection getJDBCConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
