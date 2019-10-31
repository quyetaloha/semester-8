package controller.dao.impl;

import controller.dao.ChiNhanhDAO;
import controller.dao.ChiNhanhMatHangDAO;
import controller.dao.MatHangDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChiNhanh;
import model.MatHang;

public class ChiNhanhMatHangImpl extends RootDAO implements ChiNhanhMatHangDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn = null;
    private ChiNhanhDAO chiNhanhDAO = new ChiNhanhDAOImpl();
    private MatHangDAO matHangDAO = new MatHangDAOImpl();

    @Override
    public void add(int maCN, int maMH, int soLuong) {
        String sql = "INSERT INTO SachThuocChiNhanh(maCN, maSach, soLuong) VALUES(?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            ppst.setInt(2, maMH);
            ppst.setInt(3, soLuong);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(int maMH, int maCN, int soLuong) {
        String sql = "UPDATE SachThuocChiNhanh SET soLuong = ? WHERE maCN = ? AND maSach = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, soLuong);
            ppst.setInt(2, maCN);
            ppst.setInt(3, maMH);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int maCN, int maMH) {
        String sql = "DELETE FROM SachThuocChiNhanh WHERE maCN = ? AND maSach = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            ppst.setInt(2, maMH);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ChiNhanh> getChiNhanh(int maMH) {
        ArrayList<ChiNhanh> list = new ArrayList<>();
        String slq = "SELECT * FROM SachThuocChiNhanh WHERE maSach = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(slq);
            ppst.setInt(1, maMH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                ChiNhanh chiNhanh = chiNhanhDAO.getChiNhanh(rs.getInt("maCN"));
                list.add(chiNhanh);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    @Override
    public ArrayList<MatHang> getDSMatHangByCN(int maCN) {
        ArrayList<MatHang> list = new ArrayList<>();
        String sql = "SELECT * FROM SachThuocChiNhanh WHERE maCN = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            rs = ppst.executeQuery();
            while (rs.next()) {
                MatHang matHang = matHangDAO.getMatHang(rs.getInt("maSach"));
                matHang.setSoLuong(rs.getInt("soLuong"));
                list.add(matHang);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;

    }

    @Override
    public void deleteMatHang(int maMH) {
        String sql = "DELETE FROM SachThuocChiNhanh WHERE maSach = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maMH);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public MatHang getMatHang(int maMH) {
        String sql = "SELECT * FROM SachThuocChiNhanh WHERE maSach = ?;";
        MatHang matHang = matHangDAO.getMatHang(maMH);
        //matHang.setListCN(getChiNhanh(maMH));
        int soLuong = 0;
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maMH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                soLuong += rs.getInt("soLuong");
            }
            matHang.setSoLuong(soLuong);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matHang;
    }

    @Override
    public boolean checkMaMH(int maCN, int maMH) {
        String sql = "SELECT * FROM SachThuocChiNhanh WHERE maCN = ? AND maSach = ?;";
        try {
            conn = getJDBCConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            ppst.setInt(2, maMH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    @Override
    public int getSoLuongMatHang(int maMH, int maCN) {
        String sql = "SELECT * FROM SachThuocChiNhanh WHERE maCN = ? AND maSach = ?;";
        int soLuong = 0;
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            ppst.setInt(2, maMH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("soLuong");
                conn.close();
                return soLuong;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhMatHangImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
