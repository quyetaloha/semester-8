package controller.DAO;

import java.util.ArrayList;
import model.DanhMuc;

public interface DanhMucDAO {
    void addDanhMuc(DanhMuc danhMuc);
    void editDanhMuc(DanhMuc danhMuc);
    void delDanhMuc(int maDM);
    DanhMuc getDanhMuc(int maDM);
    ArrayList<DanhMuc> searchByDanhMuc(String str);
    ArrayList<DanhMuc> getALl();
}
