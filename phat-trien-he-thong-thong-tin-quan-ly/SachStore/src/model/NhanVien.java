package model;

public class NhanVien {
    private int maNV;
    private int maCN;
    private String user;
    private String pass;
    private String name;
    private String diaChi;
    private String phone;
    private String role;

    public NhanVien() {
    }

    public NhanVien(int maNV, int maCN, String user, String pass, String name, String diaChi, String phone, String role) {
        this.maNV = maNV;
        this.maCN = maCN;
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.diaChi = diaChi;
        this.phone = phone;
        this.role = role;
    }
    
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaCN() {
        return maCN;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
