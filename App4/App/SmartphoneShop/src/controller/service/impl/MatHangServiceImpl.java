package controller.service.impl;

import controller.dao.ChiNhanhMatHangDAO;
import controller.dao.MatHangDAO;
import controller.dao.impl.ChiNhanhMatHangImpl;
import controller.dao.impl.MatHangDAOImpl;
import controller.service.MatHangService;
import java.util.ArrayList;
import model.ChiNhanh;
import model.MatHang;

public class MatHangServiceImpl implements MatHangService {

    private MatHangDAO matHangDAO = new MatHangDAOImpl();
    private ChiNhanhMatHangDAO chiNhanhMatHangDAO = new ChiNhanhMatHangImpl();

    @Override
    public void addMatHang(MatHang matHang) {
        matHangDAO.addMatHang(matHang);
        for (ChiNhanh c : matHang.getListCN()) {
            chiNhanhMatHangDAO.add(c.getMaCN(), matHang.getMaMH(), matHang.getSoLuong());
        }
    }

    @Override
    public void editMatHang(MatHang matHang) {
        matHangDAO.editMatHang(matHang);
        for (ChiNhanh c : matHang.getListCN()) {
            chiNhanhMatHangDAO.edit(c.getMaCN(), matHang.getMaMH(), matHang.getSoLuong());
        }
    }

    @Override
    public void delMatHang(int maMH) {
        chiNhanhMatHangDAO.deleteMatHang(maMH);
        matHangDAO.delMatHang(maMH);
    }

    @Override
    public MatHang getMatHang(int maMH) {
        return chiNhanhMatHangDAO.getMatHang(maMH);
    }

    @Override
    public ArrayList<MatHang> getMatHang(String name) {
        ArrayList<MatHang> list = matHangDAO.getMatHang(name);
        for (MatHang l : list) {
            //l.setListCN(chiNhanhMatHangDAO.getChiNhanh(l.getMaMH()));
            l.setSoLuong(chiNhanhMatHangDAO.getMatHang(l.getMaMH()).getSoLuong());
        }
        return list;
    }

    @Override
    public ArrayList<MatHang> getALL() {
        ArrayList<MatHang> list = matHangDAO.getALL();
        for (MatHang l : list) {
            //l.setListCN(chiNhanhMatHangDAO.getChiNhanh(l.getMaMH()));
            l.setSoLuong(chiNhanhMatHangDAO.getMatHang(l.getMaMH()).getSoLuong());
        }
        return list;
    }

    @Override
    public ArrayList<MatHang> getMatHangByCN(int maCN) {
        return chiNhanhMatHangDAO.getDSMatHangByCN(maCN);
    }

    @Override
    public void deleteMatHangByCN(int maMH, int maCN) {
        chiNhanhMatHangDAO.delete(maCN, maMH);
    }

    @Override
    public MatHang getMatHang(int maMH, int maCN) {
        MatHang matHang = matHangDAO.getMatHang(maMH);
        //matHang.setListCN(chiNhanhMatHangDAO.getChiNhanh(maMH));
        matHang.setSoLuong(chiNhanhMatHangDAO.getSoLuongMatHang(maMH, maCN));
        return matHang;
    }

}
