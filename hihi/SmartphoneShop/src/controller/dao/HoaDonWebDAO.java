package controller.DAO;

import java.util.ArrayList;
import model.HoaDon;

public interface HoaDonWebDAO {

    void addHoaDonOnline(HoaDon hoaDon);

    void editHoaDonOnline(HoaDon hoaDon);

    void delHoaDonOnline(int maHD);

    HoaDon getHoaDonOnline(int maHD);

    ArrayList<HoaDon> getHoaDonByKH(int maKH);

    ArrayList<HoaDon> getHoaDonByNgayLap(String date);

    ArrayList<HoaDon> sortByNgayMua();

    ArrayList<HoaDon> sortByTotal();

    ArrayList<HoaDon> getAll();
}
