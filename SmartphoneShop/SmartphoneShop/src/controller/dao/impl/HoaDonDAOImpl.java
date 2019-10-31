package controller.dao.impl;

import controller.dao.HoaDonDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HoaDon;

public class HoaDonDAOImpl extends RootDAO implements HoaDonDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private KhachHangDAOImpl khachHangDAO = new KhachHangDAOImpl();

    private Date formatDate(String date) {
        java.util.Date dateU = null;
        try {
            dateU = df.parse(date);
            System.out.println(dateU);
        } catch (ParseException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date dateSQL = new Date(dateU.getTime());
        return dateSQL;
    }

    @Override
    public void addHoaDon(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDon(maNV, maKH, ngayLap, total, loaiHD) VALUES(?,?,?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ppst.setInt(1, hoaDon.getMaNV());
            ppst.setInt(2, hoaDon.getMaKH());
            ppst.setDate(3, formatDate(hoaDon.getNgayLap()));
            ppst.setInt(4, hoaDon.getTotal());
            ppst.setString(5, hoaDon.getLoaiHD());
            ppst.executeUpdate();
            rs = ppst.getGeneratedKeys();
            while(rs.next()){
                hoaDon.setMaHD(rs.getInt(1));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editHoaDon(HoaDon hoaDon) {
        String sql = "UPDATE HoaDon SET maNV = ?, maKH = ?, ngayLap = ?, total = ? , loaiHD = ? WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, hoaDon.getMaNV());
            ppst.setInt(2, hoaDon.getMaKH());
            ppst.setDate(3, formatDate(hoaDon.getNgayLap()));
            ppst.setInt(4, hoaDon.getTotal());
            ppst.setString(5, hoaDon.getLoaiHD());
            ppst.setInt(6, hoaDon.getMaHD());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delHoaDon(int maHD) {
        String sql = "DELETE FROM HoaDon WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HoaDon getHoaDon(int maHD) {
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?;";
        conn = getJDBCConnection();
        HoaDon hoaDon = null;
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            rs = ppst.executeQuery();
            while (rs.next()) {
                hoaDon = new HoaDon();
                hoaDon.setMaHD(maHD);
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hoaDon;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByNV(int maNV) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE maNV = ?;";
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
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByKH(int maKH) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE maKH = ?;";
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
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByNgayLap(String date) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE ngayLap = ?;";
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
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public ArrayList<HoaDon> getHoaDon(String name) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT HoaDon.maHD FROM HoaDon INNER JOIN KhachHang ON HoaDon.maKH = KhachHang.maKH WHERE KhachHang.tenKH LIKE ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "%" + name + "%");
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByLoaiHD(String loaiHD) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE loaiHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, loaiHD);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonByCN(int maCN) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon INNER JOIN KhachHang ON HoaDon.maKH = KhachHang.maKH WHERE maCN = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            rs = ppst.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNV(rs.getInt("maNV"));
                hoaDon.setMaKH(rs.getInt("maKH"));
                hoaDon.setNgayLap(rs.getDate("ngayLap").toString());
                hoaDon.setLoaiHD(rs.getString("loaiHD"));
                hoaDon.setTotal(rs.getInt("total"));
                list.add(hoaDon);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
