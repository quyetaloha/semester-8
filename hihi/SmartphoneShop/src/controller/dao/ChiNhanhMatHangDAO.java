package controller.dao;

import java.util.ArrayList;
import model.ChiNhanh;
import model.MatHang;

public interface ChiNhanhMatHangDAO {

    void add(int maCN, int maMH, int soLuong);

    void edit(int maMH, int maCN, int soLuong);

    void delete(int maCN, int maMH);

    void deleteMatHang(int maMH);

    MatHang getMatHang(int maMH);
    
    int getSoLuongMatHang(int maMH, int maCN);

    ArrayList<ChiNhanh> getChiNhanh(int maMH);

    ArrayList<MatHang> getDSMatHangByCN(int maCN);

    boolean checkMaMH(int maCN, int maMH);
}
