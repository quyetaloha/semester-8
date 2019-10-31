package model;

public class ChiTiet {
    private String manHinh;
    private String heDieuHanh;
    private String camera;
    private String cpu;
    private String ram;
    private String pin;

    public ChiTiet() {
    }

    public ChiTiet(String manHinh, String heDieuHanh, String camera, String cpu, String ram, String pin) {
        this.manHinh = manHinh;
        this.heDieuHanh = heDieuHanh;
        this.camera = camera;
        this.cpu = cpu;
        this.ram = ram;
        this.pin = pin;
    }
    
    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
}
