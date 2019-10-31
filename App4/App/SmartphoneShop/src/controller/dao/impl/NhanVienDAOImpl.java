package controller.dao.impl;

import controller.dao.NhanhVienDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NhanVien;

public class NhanVienDAOImpl extends RootDAO implements NhanhVienDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;

    @Override
    public void addNhanVien(NhanVien nhanVien) {
        String sql = "INSERT INTO NhanVien(taiKhoan, matKhau, hoTen, diaChi, sdt, maCN, chucVu) VALUES(?,?,?,?,?,?,?);";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, nhanVien.getUser());
            ppst.setString(2, nhanVien.getPass());
            ppst.setString(3, nhanVien.getName());
            ppst.setString(4, nhanVien.getDiaChi());
            ppst.setString(5, nhanVien.getPhone());
            ppst.setInt(6, nhanVien.getMaCN());
            ppst.setString(7, nhanVien.getRole());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editNhanVien(NhanVien nhanVien) {
        String sql = "UPDATE NhanVien SET taiKhoan = ?, matKhau = ?, hoTen = ?, diaChi = ?, sdt = ?, maCN = ?, chucVu = ? WHERE maNV = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, nhanVien.getUser());
            ppst.setString(2, nhanVien.getPass());
            ppst.setString(3, nhanVien.getName());
            ppst.setString(4, nhanVien.getDiaChi());
            ppst.setString(5, nhanVien.getPhone());
            ppst.setInt(6, nhanVien.getMaCN());
            ppst.setString(7, nhanVien.getRole());
            ppst.setInt(8, nhanVien.getMaNV());
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delNhanVien(int maNV) {
        String sql = "DELETE FROM NhanVien WHERE maNV = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maNV);
            ppst.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public NhanVien getNhanVien(int maNV) {
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maNV);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(maNV, rs.getInt("maCN"), rs.getString("taiKhoan"), rs.getString("matKhau"),
                        rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("sdt"), rs.getString("chucVu"));
                return nhanVien;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<NhanVien> getNhanVien(String name) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE hoTen LIKE ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "%" + name + "%");
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getInt("maNV"), rs.getInt("maCN"), rs.getString("taiKhoan"),
                        rs.getString("matKhau"), rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("sdt"), rs.getString("chucVu"));
                list.add(nhanVien);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        sortByName(list);
        return list;
    }

    @Override
    public ArrayList<NhanVien> getALL() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE chucVu = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "Bán hàng");
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getInt("maNV"), rs.getInt("maCN"), rs.getString("taiKhoan"),
                        rs.getString("matKhau"), rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("sdt"), rs.getString("chucVu"));
                list.add(nhanVien);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        sortByName(list);
        return list;
    }

    @Override
    public boolean checkLogin(String user, String pass) {
        String sql = "SELECT * FROM NhanVien WHERE taiKhoan = ? AND matKhau = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, user);
            ppst.setString(2, pass);
            rs = ppst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean checkUser(String user) {
        String sql = "SELECT * FROM NhanVien WHERE taiKhoan = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, user);
            rs = ppst.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public NhanVien getNhanVienByUsername(String username) {
        String sql = "SELECT * FROM NhanVien WHERE taiKhoan = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, username);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getInt("maNV"));
                nhanVien.setMaCN(rs.getInt("maCN"));
                nhanVien.setUser(rs.getString("taiKhoan"));
                nhanVien.setPass(rs.getString("matKhau"));
                nhanVien.setName(rs.getString("hoTen"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setPhone(rs.getString("sdt"));
                nhanVien.setRole(rs.getString("chucVu"));
                return nhanVien;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<NhanVien> getDSNhanVienByCN(int maCN) {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE maCN = ? AND chucVu = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            ppst.setString(2, "Bán hàng");
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getInt("maNV"));
                nhanVien.setMaCN(rs.getInt("maCN"));
                nhanVien.setUser(rs.getString("taiKhoan"));
                nhanVien.setPass(rs.getString("matKhau"));
                nhanVien.setName(rs.getString("hoTen"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setPhone(rs.getString("sdt"));
                nhanVien.setRole(rs.getString("chucVu"));
                list.add(nhanVien);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        sortByName(list);
        return list;

    }

    private void sortByName(ArrayList<NhanVien> list) {
        Collections.sort(list, new Comparator<NhanVien>() {

            @Override
            public int compare(NhanVien o1, NhanVien o2) {
                String s1[] = o1.getName().split(" ");
                String s2[] = o2.getName().split(" ");
                return s1[s1.length - 1].compareToIgnoreCase(s2[s2.length - 1]);
            }
        });
    }

    @Override
    public ArrayList<NhanVien> getALLNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE chucVu = ? OR chucVu = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, "Bán hàng");
            ppst.setString(2, "Quản lý");
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getInt("maNV"), rs.getInt("maCN"), rs.getString("taiKhoan"),
                        rs.getString("matKhau"), rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("sdt"), rs.getString("chucVu"));
                list.add(nhanVien);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
