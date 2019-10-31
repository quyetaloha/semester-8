package controller.DAO.Impl;

import controller.DAO.NhanhVienDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NhanVien;

public class NhanVienDAOImpl extends RootDAO implements NhanhVienDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;

    @Override
    public void addNhanVien(NhanVien nhanVien) {
        String sql = "INSERT INTO NhanVien(userName, passWord, hoTen, diaChi, phone, maCN, chucVu) VALUES(?,?,?,?,?,?,?);";
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
        String sql = "UPDATE NhanVien SET userName = ?, passWord = ?, hoTen = ?, diaChi = ?, phone = ?, maCN = ?, chucVu = ? WHERE maNV = ?;";
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
                NhanVien nhanVien = new NhanVien(maNV, rs.getInt("maCN"), rs.getString("userName"), rs.getString("passWord"),
                        rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("phone"), rs.getString("chucVu"));
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
                NhanVien nhanVien = new NhanVien(rs.getInt("maNV"), rs.getInt("maCN"), rs.getString("userName"),
                        rs.getString("passWord"), rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("phone"), rs.getString("chucVu"));
                list.add(nhanVien);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<NhanVien> getALL() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getInt("maNV"), rs.getInt("maCN"), rs.getString("userName"),
                        rs.getString("passWord"), rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("phone"), rs.getString("chucVu"));
                list.add(nhanVien);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean checkLogin(String user, String pass) {
        String sql = "SELECT * FROM NhanVien WHERE userName = ? AND passWord = ?;";
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
        String sql = "SELECT * FROM NhanVien WHERE userName = ?;";
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
        String sql = "SELECT * FROM NhanVien WHERE userName = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, username);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getInt("maNV"));
                nhanVien.setMaCN(rs.getInt("maCN"));
                nhanVien.setUser(rs.getString("userName"));
                nhanVien.setPass(rs.getString("passWord"));
                nhanVien.setName(rs.getString("hoTen"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setPhone(rs.getString("phone"));
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
        String sql = "SELECT * FROM NhanVien WHERE maCN = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            rs = ppst.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getInt("maNV"));
                nhanVien.setMaCN(rs.getInt("maCN"));
                nhanVien.setUser(rs.getString("userName"));
                nhanVien.setPass(rs.getString("passWord"));
                nhanVien.setName(rs.getString("hoTen"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setPhone(rs.getString("phone"));
                nhanVien.setRole(rs.getString("chucVu"));
                list.add(nhanVien);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
}
