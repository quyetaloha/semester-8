package controller.DAO.Impl;

import controller.DAO.ChiNhanhDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChiNhanh;

public class ChiNhanhDAOImpl extends RootDAO implements ChiNhanhDAO {

    private PreparedStatement ppst;
    private ResultSet rs;
    private Connection conn;

    @Override
    public ChiNhanh getChiNhanh(int maCN) {
        String sql = "SELECT * FROM ChiNhanh WHERE maCN = ?;";
        conn = getJDBCConnection();
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, maCN);
            rs = ppst.executeQuery();
            while(rs.next()){
                ChiNhanh chiNhanh = new ChiNhanh();
                chiNhanh.setMaCN(rs.getInt("maCN"));
                chiNhanh.setTenCN(rs.getString("tenChiNhanh"));
                chiNhanh.setDiaChi(rs.getString("diaChi"));
                return chiNhanh;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
