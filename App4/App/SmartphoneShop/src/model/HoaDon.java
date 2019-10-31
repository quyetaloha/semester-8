package model;

import java.util.ArrayList;

public class HoaDon {
    private int maHD;
    private int maNV;
    private int maKH;
    private ArrayList<Items> listItems;
    private String ngayLap;
    private int total;
    private String loaiHD;
    
    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public ArrayList<Items> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Items> listItems) {
        this.listItems = listItems;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getLoaiHD() {
        return loaiHD;
    }

    public void setLoaiHD(String loaiHD) {
        this.loaiHD = loaiHD;
    }
    
}
