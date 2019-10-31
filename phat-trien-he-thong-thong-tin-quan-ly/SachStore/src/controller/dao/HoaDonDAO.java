package controller.dao;

import java.util.ArrayList;
import model.HoaDon;

public interface HoaDonDAO {

    void addHoaDon(HoaDon hoaDon);

    void editHoaDon(HoaDon hoaDon);

    void delHoaDon(int maHD);

    HoaDon getHoaDon(int maHD);

    ArrayList<HoaDon> getHoaDon(String name);

    ArrayList<HoaDon> getHoaDonByNV(int maNV);

    ArrayList<HoaDon> getHoaDonByKH(int maKH);

    ArrayList<HoaDon> getHoaDonByLoaiHD(String loaiHD);

    ArrayList<HoaDon> getHoaDonByCN(int maCN);

    ArrayList<HoaDon> getHoaDonByNgayLap(String date);

    ArrayList<HoaDon> getAll();

}
