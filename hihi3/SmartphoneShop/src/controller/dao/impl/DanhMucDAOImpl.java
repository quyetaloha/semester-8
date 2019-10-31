package controller.dao.impl;

import controller.dao.DanhMucDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DanhMuc;

public class DanhMucDAOImpl extends RootDAO implements DanhMucDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;

    @Override
    public void addDanhMuc(DanhMuc danhMuc) {
        String sql = "INSERT INTO LoaiSach(tenLoaiSach) VALUES(?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, danhMuc.getTenDM());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editDanhMuc(DanhMuc danhMuc) {
        String sql = "UPDATE LoaiSach SET tenLoaiSach = ? WHERE maLS = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, danhMuc.getTenDM());
            ppst.setInt(2, danhMuc.getMaDM());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delDanhMuc(int maDM) {
        String sql = "DELETE FROM LoaiSach WHERE maLS = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maDM);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DanhMuc getDanhMuc(int maDM) {
        String sql = "SELECT * FROM LoaiSach WHERE maLS = ?;";
        DanhMuc danhMuc = new DanhMuc();
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maDM);
            rs = ppst.executeQuery();
            while (rs.next()) {
                danhMuc.setMaDM(rs.getInt("maLS"));
                danhMuc.setTenDM(rs.getString("tenLoaiSach"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return danhMuc;
    }

    @Override
    public ArrayList<DanhMuc> getALl() {
        ArrayList<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiSach;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setMaDM(rs.getInt("maLS"));
                danhMuc.setTenDM(rs.getString("tenLoaiSach"));
                list.add(danhMuc);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<DanhMuc> searchByDanhMuc(String str) {
        ArrayList<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiSach WHERE tenLoaiSach LIKE ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "%" + str + "%");
            rs = ppst.executeQuery();
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setMaDM(rs.getInt("maLS"));
                danhMuc.setTenDM(rs.getString("tenLoaiSach"));
                list.add(danhMuc);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
