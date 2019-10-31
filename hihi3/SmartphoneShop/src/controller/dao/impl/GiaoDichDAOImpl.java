package controller.dao.impl;

import controller.dao.GiaoDichDAO;
import controller.dao.HoaDonDAO;
import controller.dao.MatHangDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Items;

public class GiaoDichDAOImpl extends RootDAO implements GiaoDichDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;
    private HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();
    private MatHangDAO matHangDAO = new MatHangDAOImpl();

    @Override
    public void addGiaoDich(Items items) {
        String sql = "INSERT INTO [Order](maHD, maSach, soLuong) VALUES(?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, items.getMaHD());
            ppst.setInt(2, items.getMaMH());
            ppst.setInt(3, items.getSoLuong());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDichDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editGiaoDich(Items items) {
        String sql = "UPDATE [Order] SET soLuong = ? WHERE maHD = ? AND maSach = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, items.getSoLuong());
            ppst.setInt(2, items.getMaHD());
            ppst.setInt(3, items.getMaMH());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDichDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int maHD, int maMH) {
        String sql = "DELETE FROM [Order] WHERE maHD = ? AND maSach = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            ppst.setInt(2, maMH);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDichDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int maHD) {
        String sql = "DELETE FROM [Order] WHERE maHD = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDichDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Items> getMatHangByHoaDon(int maHD) {
        String sql = "SELECT * FROM [Order] WHERE maHD = ?;";
        ArrayList<Items> list = new ArrayList<>();
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maHD);
            rs = ppst.executeQuery();
            while (rs.next()) {
                Items items = new Items();
                items.setMaHD(maHD);
                items.setMaMH(rs.getInt("maSach"));
                items.setSoLuong(rs.getInt("soLuong"));
                list.add(items);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDichDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
