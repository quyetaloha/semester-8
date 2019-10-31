package controller.DAO.Impl;

import controller.DAO.MatHangDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChiTiet;
import model.MatHang;

public class MatHangDAOImpl extends RootDAO implements MatHangDAO{
    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;
    
    @Override
    public void addMatHang(MatHang matHang) {
        String sql = "INSERT INTO MatHang(maCN, maDM, tenMH, hangMH, img, giaMH, soLuong, manHinh, heDieuHanh, camera, cpu, ram, pin) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, matHang.getMaCN());
            ppst.setInt(2, matHang.getMaDM());
            ppst.setString(3, matHang.getTenMH());
            ppst.setString(4, matHang.getHangMH());
            ppst.setString(5, matHang.getImg());
            ppst.setString(6, matHang.getGiaMH());
            ppst.setInt(7, matHang.getSoLuong());
            ppst.setString(8, matHang.getChiTiet().getManHinh());
            ppst.setString(9, matHang.getChiTiet().getHeDieuHanh());
            ppst.setString(10, matHang.getChiTiet().getCamera());
            ppst.setString(11, matHang.getChiTiet().getCpu());
            ppst.setString(12, matHang.getChiTiet().getRam());
            ppst.setString(13, matHang.getChiTiet().getPin());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editMatHang(MatHang matHang) {
        String sql = "UPDATE MatHang SET maCN = ?, maDM = ?, tenMH = ?, hangMH = ?, img = ?, giaMH = ?, soLuong = ?, manHinh = ?, heDieuHanh = ?, camera = ?, cpu = ?, ram = ?, pin = ? WHERE maMH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, matHang.getMaCN());
            ppst.setInt(2, matHang.getMaDM());
            ppst.setString(3, matHang.getTenMH());
            ppst.setString(4, matHang.getHangMH());
            ppst.setString(5, matHang.getImg());
            ppst.setString(6, matHang.getGiaMH());
            ppst.setInt(7, matHang.getSoLuong());
            ppst.setString(8, matHang.getChiTiet().getManHinh());
            ppst.setString(9, matHang.getChiTiet().getHeDieuHanh());
            ppst.setString(10, matHang.getChiTiet().getCamera());
            ppst.setString(11, matHang.getChiTiet().getCpu());
            ppst.setString(12, matHang.getChiTiet().getRam());
            ppst.setString(13, matHang.getChiTiet().getPin());
            ppst.setInt(14, matHang.getMaMH());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delMatHang(int maMH) {
        String sql = "DELETE FROM MatHang WHERE maMH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maMH);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public MatHang getMatHang(int maMH) {
        String sql = "SELECT * FROM MatHang WHERE maMH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maMH);
            rs = ppst.executeQuery();
            while(rs.next()){
                MatHang matHang = new MatHang();
                ChiTiet chiTiet = new ChiTiet(rs.getString("manHinh"), rs.getString("heDieuHanh"), rs.getString("camera"), rs.getString("cpu"), rs.getString("ram"), rs.getString("pin"));
                matHang.setMaMH(maMH);
                matHang.setMaCN(rs.getInt("maCN"));
                matHang.setMaDM(rs.getInt("maDM"));
                matHang.setTenMH(rs.getString("tenMH"));
                matHang.setHangMH(rs.getString("hangMH"));
                matHang.setImg(rs.getString("img"));
                matHang.setGiaMH(rs.getString("giaMH"));
                matHang.setSoLuong(rs.getInt("soLuong"));
                matHang.setChiTiet(chiTiet);
                return matHang;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public ArrayList<MatHang> getMatHang(String name) {
        ArrayList<MatHang> list = new ArrayList<>();
        String sql = "SELECT * FROM MatHang WHERE tenMH LIKE ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "%"+name+"%");
            rs = ppst.executeQuery();
            while(rs.next()){
                MatHang matHang = new MatHang();
                ChiTiet chiTiet = new ChiTiet(rs.getString("manHinh"), rs.getString("heDieuHanh"), rs.getString("camera"), rs.getString("cpu"), rs.getString("ram"), rs.getString("pin"));
                matHang.setMaMH(rs.getInt("maMH"));
                matHang.setMaCN(rs.getInt("maCN"));
                matHang.setMaDM(rs.getInt("maDM"));
                matHang.setTenMH(rs.getString("tenMH"));
                matHang.setHangMH(rs.getString("hangMH"));
                matHang.setImg(rs.getString("img"));
                matHang.setGiaMH(rs.getString("giaMH"));
                matHang.setSoLuong(rs.getInt("soLuong"));
                matHang.setChiTiet(chiTiet);
                list.add(matHang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<MatHang> getALL() {
        ArrayList<MatHang> list = new ArrayList<>();
        String sql = "SELECT * FROM MatHang;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while(rs.next()){
                MatHang matHang = new MatHang();
                ChiTiet chiTiet = new ChiTiet(rs.getString("manHinh"), rs.getString("heDieuHanh"), rs.getString("camera"), rs.getString("cpu"), rs.getString("ram"), rs.getString("pin"));
                matHang.setMaMH(rs.getInt("maMH"));
                matHang.setMaCN(rs.getInt("maCN"));
                matHang.setMaDM(rs.getInt("maDM"));
                matHang.setTenMH(rs.getString("tenMH"));
                matHang.setHangMH(rs.getString("hangMH"));
                matHang.setImg(rs.getString("img"));
                matHang.setGiaMH(rs.getString("giaMH"));
                matHang.setSoLuong(rs.getInt("soLuong"));
                matHang.setChiTiet(chiTiet);
                list.add(matHang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}