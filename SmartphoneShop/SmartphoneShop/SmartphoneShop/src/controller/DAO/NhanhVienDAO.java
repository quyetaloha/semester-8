package controller.DAO;

import java.util.ArrayList;
import model.NhanVien;

public interface NhanhVienDAO {
    void addNhanVien(NhanVien nhanVien);
    void editNhanVien(NhanVien nhanVien);
    void delNhanVien(int maNV);
    NhanVien getNhanVien(int maNV);
    NhanVien getNhanVienByUsername(String username);
    ArrayList<NhanVien> getNhanVien(String name);
    ArrayList<NhanVien> getALL();
    ArrayList<NhanVien> getDSNhanVienByCN(int maCN);
    boolean checkLogin(String user, String pass);
    boolean checkUser(String user);
}
