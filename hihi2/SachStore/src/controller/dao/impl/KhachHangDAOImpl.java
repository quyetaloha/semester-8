package controller.dao.impl;

import controller.dao.KhachHangDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KhachHang;

public class KhachHangDAOImpl extends RootDAO implements KhachHangDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;

    @Override
    public void addKhachHang(KhachHang khachHang) {
        String sql = "INSERT INTO KhachHang(maKH, tenkhachhang, maCN, diaChi, sdt) VALUES(?,?,?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, khachHang.getMaKH());
            ppst.setString(2, khachHang.getTenKH());
            ppst.setInt(3, khachHang.getMaCN());
            ppst.setString(4, khachHang.getDiaChi());
            ppst.setString(5, khachHang.getPhone());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void editKhachHang(KhachHang khachHang) {
        String sql = "UPDATE KhachHang SET tenkhachhang = ?, maCN = ?, diaChi = ?, sdt = ? WHERE maKH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, khachHang.getTenKH());
            ppst.setInt(2, khachHang.getMaCN());
            ppst.setString(3, khachHang.getDiaChi());
            ppst.setString(4, khachHang.getDiaChi());
            ppst.setInt(5, khachHang.getMaKH());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delKhachHang(int maKH) {
        String sql = "DELETE FROM KhachHang WHERE maKH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maKH);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public KhachHang getKhachHang(int maKH) {
        String sql = "SELECT * FROM KhachHang WHERE maKH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maKH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(maKH);
                khachHang.setMaCN(rs.getInt("maCN"));
                khachHang.setTenKH(rs.getString("tenkhachhang"));
                khachHang.setDiaChi(rs.getString("diaChi"));
                khachHang.setPhone(rs.getString("sdt"));
                return khachHang;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    @Override
    public ArrayList<KhachHang> getKhachHang(String name) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE tenkhachhang LIKE ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "%" + name + "%");
            rs = ppst.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getInt("maKH"));
                khachHang.setTenKH(rs.getString("tenkhachhang"));
                khachHang.setMaCN(rs.getInt("maCN"));
                khachHang.setDiaChi(rs.getString("diaChi"));
                khachHang.setPhone(rs.getString("sdt"));
                list.add(khachHang);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    @Override
    public ArrayList<KhachHang> getALl() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getInt("maKH"));
                khachHang.setTenKH(rs.getString("tenkhachhang"));
                khachHang.setMaCN(rs.getInt("maCN"));
                khachHang.setDiaChi(rs.getString("diaChi"));
                khachHang.setPhone(rs.getString("sdt"));
                list.add(khachHang);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public ArrayList<KhachHang> getKhachHangByCN(int maCN) {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE maCN = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            rs = ppst.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKH(rs.getInt("maKH"));
                khachHang.setTenKH(rs.getString("tenkhachhang"));
                khachHang.setMaCN(rs.getInt("maCN"));
                khachHang.setDiaChi(rs.getString("diaChi"));
                khachHang.setPhone(rs.getString("sdt"));
                list.add(khachHang);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
}
