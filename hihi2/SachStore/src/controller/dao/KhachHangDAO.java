package controller.dao;

import java.util.ArrayList;
import model.KhachHang;

public interface KhachHangDAO {

    void addKhachHang(KhachHang khachHang);

    void editKhachHang(KhachHang khachHang);

    void delKhachHang(int maKH);

    KhachHang getKhachHang(int maKH);

    ArrayList<KhachHang> getKhachHang(String name);
    
    ArrayList<KhachHang> getKhachHangByCN(int maCN);

    ArrayList<KhachHang> getALl();
    
}
