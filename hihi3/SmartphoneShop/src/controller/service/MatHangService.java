package controller.service;

import java.util.ArrayList;
import model.MatHang;

public interface MatHangService {

    void addMatHang(MatHang matHang);

    void editMatHang(MatHang matHang);

    void delMatHang(int maMH);
    
    void deleteMatHangByCN(int maMH, int maCN);

    MatHang getMatHang(int maMH);

    MatHang getMatHang(int maMH, int maCN);
    
    ArrayList<MatHang> getMatHang(String name);

    ArrayList<MatHang> getMatHangByCN(int maCN);

    ArrayList<MatHang> getALL();
}
