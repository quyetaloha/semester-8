package controller.DAO;

import java.util.ArrayList;
import model.HoaDon;

public interface HoaDonOfflineDAO {

    void addHoaDonOffline(HoaDon hoaDon);

    void editHoaDonOffline(HoaDon hoaDon);

    void delHoaDonOffline(int maHD);

    HoaDon getHoaDonOffline(int maHD);
    
    HoaDon getHoaDonOffline(String name);

    ArrayList<HoaDon> getHoaDonByNV(int maNV);

    ArrayList<HoaDon> getHoaDonByKH(int maKH);

    ArrayList<HoaDon> getHoaDonByNgayLap(String date);

    ArrayList<HoaDon> sortByNgayMua();

    ArrayList<HoaDon> sortByTotal();

    ArrayList<HoaDon> getAll();

}
