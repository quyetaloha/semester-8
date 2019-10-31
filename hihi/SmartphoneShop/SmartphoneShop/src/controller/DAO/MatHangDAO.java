package controller.DAO;

import java.util.ArrayList;
import model.MatHang;

public interface MatHangDAO {
    void addMatHang(MatHang matHang);
    void editMatHang(MatHang matHang);
    void delMatHang(int maMH);
    MatHang getMatHang(int maMH);
    ArrayList<MatHang> getMatHang(String name);
    ArrayList<MatHang> getALL();
}
