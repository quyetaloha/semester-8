package controller.dao;

import java.util.ArrayList;
import model.Items;

public interface GiaoDichDAO {

    void addGiaoDich(Items items);

    void editGiaoDich(Items items);

    void delete(int maHD, int maMH);

    void delete(int maHD);
    
    ArrayList<Items> getMatHangByHoaDon(int maHD);
    
}
