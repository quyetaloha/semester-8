package controller.dao.impl;

import controller.dao.MatHangDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChiTiet;
import model.MatHang;

public class MatHangDAOImpl extends RootDAO implements MatHangDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;

    private void sortByName(ArrayList<MatHang> list) {
        Collections.sort(list, new Comparator<MatHang>() {

            @Override
            public int compare(MatHang o1, MatHang o2) {
                String s1[] = o1.getTenMH().split(" ");
                String s2[] = o2.getTenMH().split(" ");
                return s1[s1.length - 1].compareToIgnoreCase(s2[s2.length - 1]);
            }
        });
    }

    @Override
    public void addMatHang(MatHang matHang) {
        String sql = "INSERT INTO Sach(masach, mals, tensach, nhasanxuat, img, giaban,tacgia,quocgia, nguoibiendich, kichthuoc, moto, ngayxuatban) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, matHang.getMaMH());
            ppst.setInt(2, matHang.getMaDM());
            ppst.setString(3, matHang.getTenMH());
            ppst.setString(4, matHang.getHangMH());
            ppst.setString(5, matHang.getImg());
            ppst.setString(6, matHang.getGiaMH());
            ppst.setString(7, matHang.getChiTiet().getManHinh());
            ppst.setString(8, matHang.getChiTiet().getHeDieuHanh());
            ppst.setString(9, matHang.getChiTiet().getCamera());
            ppst.setString(10, matHang.getChiTiet().getCpu());
            ppst.setString(11, matHang.getChiTiet().getRam());
            ppst.setString(12, matHang.getChiTiet().getPin());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editMatHang(MatHang matHang) {
        String sql = "UPDATE sach SET mals = ?, tensach = ?, nhasanxuat = ?, img = ?, giaban = ?, tacgia = ?, quocgia = ?, nguoibiendich = ?, kichthuoc = ?, moto = ?, ngayxuatban = ? WHERE maMH = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, matHang.getMaDM());
            ppst.setString(2, matHang.getTenMH());
            ppst.setString(3, matHang.getHangMH());
            ppst.setString(4, matHang.getImg());
            ppst.setString(5, matHang.getGiaMH());
            ppst.setString(6, matHang.getChiTiet().getManHinh());
            ppst.setString(7, matHang.getChiTiet().getHeDieuHanh());
            ppst.setString(8, matHang.getChiTiet().getCamera());
            ppst.setString(9, matHang.getChiTiet().getCpu());
            ppst.setString(10, matHang.getChiTiet().getRam());
            ppst.setString(11, matHang.getChiTiet().getPin());
            ppst.setInt(12, matHang.getMaMH());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delMatHang(int maMH) {
        String sql = "DELETE FROM sach WHERE masach = ?;";
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
        String sql = "SELECT * FROM sach WHERE masach = ?;";
        conn = getJDBCConnection();
        MatHang matHang = new MatHang();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maMH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                matHang.setMaMH(maMH);
                matHang.setMaDM(rs.getInt("mals"));
                matHang.setTenMH(rs.getString("tensach"));
                matHang.setHangMH(rs.getString("nhaxuatban"));
                matHang.setImg(rs.getString("img"));
                matHang.setGiaMH(rs.getString("giaMH"));
                ChiTiet chiTiet = new ChiTiet(rs.getString("manHinh"), rs.getString("heDieuHanh"), rs.getString("camera"), rs.getString("cpu"), rs.getString("ram"), rs.getString("pin"));
                matHang.setChiTiet(chiTiet);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matHang;

    }

    @Override
    public ArrayList<MatHang> getMatHang(String name) {
        ArrayList<MatHang> list = new ArrayList<>();
        String sql = "SELECT * FROM MatHang WHERE tenMH LIKE ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "%" + name + "%");
            rs = ppst.executeQuery();
            while (rs.next()) {
                MatHang matHang = new MatHang();
                ChiTiet chiTiet = new ChiTiet(rs.getString("manHinh"), rs.getString("heDieuHanh"), rs.getString("camera"), rs.getString("cpu"), rs.getString("ram"), rs.getString("pin"));
                matHang.setMaMH(rs.getInt("maMH"));
                matHang.setMaDM(rs.getInt("maDM"));
                matHang.setTenMH(rs.getString("tenMH"));
                matHang.setHangMH(rs.getString("hangMH"));
                matHang.setImg(rs.getString("img"));
                matHang.setGiaMH(rs.getString("giaMH"));
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
            while (rs.next()) {
                MatHang matHang = new MatHang();
                ChiTiet chiTiet = new ChiTiet(rs.getString("manHinh"), rs.getString("heDieuHanh"), rs.getString("camera"), rs.getString("cpu"), rs.getString("ram"), rs.getString("pin"));
                matHang.setMaMH(rs.getInt("maMH"));
                matHang.setMaDM(rs.getInt("maDM"));
                matHang.setTenMH(rs.getString("tenMH"));
                matHang.setHangMH(rs.getString("hangMH"));
                matHang.setImg(rs.getString("img"));
                matHang.setGiaMH(rs.getString("giaMH"));
                matHang.setChiTiet(chiTiet);
                list.add(matHang);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean checkMaMH(int maMH) {
        String sql = "SELECT * FROM MatHang WHERE maMH = ?;";
        conn = getJDBCConnection();
        boolean ktra = false;
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maMH);
            rs = ppst.executeQuery();
            while (rs.next()) {
                ktra = true;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ktra;
    }

    @Override
    public ArrayList<MatHang> getMatHangByDM(int maDM) {
        ArrayList<MatHang> list = new ArrayList<>();
        String sql = "SELECT * FROM MatHang WHERE maDM = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maDM);
            rs = ppst.executeQuery();
            while (rs.next()) {
                MatHang matHang = new MatHang();
                ChiTiet chiTiet = new ChiTiet(rs.getString("manHinh"), rs.getString("heDieuHanh"), rs.getString("camera"), rs.getString("cpu"), rs.getString("ram"), rs.getString("pin"));
                matHang.setMaMH(rs.getInt("maMH"));
                matHang.setMaDM(rs.getInt("maDM"));
                matHang.setTenMH(rs.getString("tenMH"));
                matHang.setHangMH(rs.getString("hangMH"));
                matHang.setImg(rs.getString("img"));
                matHang.setGiaMH(rs.getString("giaMH"));
                matHang.setChiTiet(chiTiet);
                list.add(matHang);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MatHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
