package view;

import controller.dao.ChiNhanhDAO;
import controller.dao.ChiNhanhMatHangDAO;
import controller.dao.GiaoDichDAO;
import controller.dao.HoaDonDAO;
import controller.dao.KhachHangDAO;
import controller.dao.impl.ChiNhanhDAOImpl;
import controller.dao.impl.MatHangDAOImpl;
import controller.dao.impl.NhanVienDAOImpl;
import controller.dao.MatHangDAO;
import controller.dao.NhanhVienDAO;
import controller.dao.impl.ChiNhanhMatHangImpl;
import controller.dao.impl.GiaoDichDAOImpl;
import controller.dao.impl.HoaDonDAOImpl;
import controller.dao.impl.KhachHangDAOImpl;
import controller.service.MatHangService;
import controller.service.impl.MatHangServiceImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import model.ChiNhanh;
import model.ChiTiet;
import model.HoaDon;
import model.Items;
import model.KhachHang;
import model.MatHang;
import model.NhanVien;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.data.general.DefaultPieDataset;

public class FormQuanLy extends javax.swing.JFrame {

    private DefaultTableModel modelTableNV;
    private DefaultTableModel modelTableMH;
    private DefaultTableModel modelTableHD;
    private DefaultTableModel modelTableItems;
    private DefaultTableModel modelTableKH;

    private NhanhVienDAO nhanVienDAO;
    private MatHangDAO matHangDAO;
    private ChiNhanhDAO chiNhanhDAO;
    private ChiNhanhMatHangDAO chiNhanhMatHangDAO;
    private KhachHangDAO khachHangDAO;
    private HoaDonDAO hoaDonDAO;
    private GiaoDichDAO giaoDichDAO;

    private MatHangService matHangService;

    private static String imgName;
    private static String imgPath;
    private final String IMG_PATH = "C:\\Users\\LuongChinh\\Documents\\NetBeansProjects\\SmartphoneShop\\src\\view\\img\\";

    public FormQuanLy() {
        initComponents();
    }

    public FormQuanLy(NhanVien nhanVien) {
        initComponents();

        nhanVienDAO = new NhanVienDAOImpl();
        matHangDAO = new MatHangDAOImpl();
        chiNhanhDAO = new ChiNhanhDAOImpl();
        chiNhanhMatHangDAO = new ChiNhanhMatHangImpl();
        khachHangDAO = new KhachHangDAOImpl();
        hoaDonDAO = new HoaDonDAOImpl();
        matHangService = new MatHangServiceImpl();
        giaoDichDAO = new GiaoDichDAOImpl();

        nvql1.setText("Tên NVQL : " + nhanVien.getName());
        nvql2.setText("Tên NVQL : " + nhanVien.getName());
        nvql3.setText("Tên NVQL : " + nhanVien.getName());
        nvql4.setText("Tên NVQL : " + nhanVien.getName());
        nvql5.setText("Tên NVQL : " + nhanVien.getName());

        modelTableNV = new DefaultTableModel();
        modelTableHD = new DefaultTableModel();
        modelTableMH = new DefaultTableModel();
        modelTableItems = new DefaultTableModel();
        modelTableKH = new DefaultTableModel();
        modelTableNV.setColumnIdentifiers(new Object[]{"MaNV", "Tên NV", "Username", "Password", "Địa chỉ", "SĐT", "Chi nhánh", "Chức vụ"});
        modelTableMH.setColumnIdentifiers(new Object[]{"MaMH", "Tên MH", "Hãng", "Giá MH", "Số lượng"});
        modelTableHD.setColumnIdentifiers(new Object[]{"MaHD", "Tên KH", "Tên NV", "Ngày lập", "Tổng tiền", "Loại hóa đơn"});
        modelTableItems.setColumnIdentifiers(new Object[]{"MaMH", "Tên MH", "Đơn giá", "Số lượng"});
        modelTableKH.setColumnIdentifiers(new Object[]{"MaKH", "Tên KH", "Địa chỉ", "SĐT", "Chi nhánh"});

        nhanVienTable.setModel(modelTableNV);
        getDSNhanVien(nhanVienDAO.getALLNV());
        matHangTable.setModel(modelTableMH);
        getDSMatHang(matHangService.getALL());
        hoaDonTable.setModel(modelTableHD);
        getDSHoaDon(hoaDonDAO.getAll());
        listItems.setModel(modelTableItems);
        khachHangTable.setModel(modelTableKH);
        getDSKhachHang(khachHangDAO.getALl());

        nhanVienTable.setComponentPopupMenu(menuNhanVien);
        matHangTable.setComponentPopupMenu(menuMatHang);
        hoaDonTable.setComponentPopupMenu(menuHoaDon);
        khachHangTable.setComponentPopupMenu(menuKhachHang);
    }

    private void resetDataTableNV() {
        if (checkNV.isSelected()) {
            locNhanVienLabel.setText("Tất cả");
            modelTableNV.setRowCount(0);
            getDSNhanVien(nhanVienDAO.getALL());
        } else if (checkNVHN.isSelected()) {
            locNhanVienLabel.setText("Hà Nội");
            modelTableNV.setRowCount(0);
            getDSNhanVien(nhanVienDAO.getDSNhanVienByCN(1));
        } else {
            locNhanVienLabel.setText("Hồ Chí Minh");
            modelTableNV.setRowCount(0);
            getDSNhanVien(nhanVienDAO.getDSNhanVienByCN(2));
        }
    }

    private void setDataTableKH() {
        if (checkKH.isSelected()) {
            locKHLabel.setText("Tất cả");
            modelTableKH.setRowCount(0);
            getDSKhachHang(khachHangDAO.getALl());
        } else if (checkHaNoi.isSelected()) {
            locKHLabel.setText("Hà Nội");
            modelTableKH.setRowCount(0);
            getDSKhachHang(khachHangDAO.getKhachHangByCN(1));
        } else if (checkHCM.isSelected()) {
            locKHLabel.setText("Hồ Chí Minh");
            modelTableKH.setRowCount(0);
            getDSKhachHang(khachHangDAO.getKhachHangByCN(2));
        }
    }

    private void setDataTableMH() {
        if (checkBox.isSelected()) {
            locMatHangLabel.setText("Tất cả");
            modelTableMH.setRowCount(0);
            getDSMatHang(matHangService.getALL());
        } else if (checkBoxHN.isSelected()) {
            locMatHangLabel.setText("Hà Nội");
            modelTableMH.setRowCount(0);
            getDSMatHang(matHangService.getMatHangByCN(1));
        } else if (checkBoxHCM.isSelected()) {
            locMatHangLabel.setText("Hồ Chí Minh");
            modelTableMH.setRowCount(0);
            getDSMatHang(matHangService.getMatHangByCN(2));
        }

    }

    private void setDataTableHD() {
        ArrayList<HoaDon> list = null;
        if (checkBoxHD.isSelected()) {
            locHoaDonLabel.setText("Tẩt cả");
            modelTableHD.setRowCount(0);
            list = hoaDonDAO.getAll();
            getDSHoaDon(list);
        } else if (checkBoxHDHN.isSelected()) {
            locHoaDonLabel.setText("Hà Nội");
            modelTableHD.setRowCount(0);
            list = hoaDonDAO.getHoaDonByCN(1);
            getDSHoaDon(list);
        } else if (checkBoxHDHCM.isSelected()) {
            locHoaDonLabel.setText("Hồ Chí Minh");
            modelTableHD.setRowCount(0);
            list = hoaDonDAO.getHoaDonByCN(2);
            getDSHoaDon(list);
        }
    }

    private void getDSKhachHang(ArrayList<KhachHang> list) {
        for (KhachHang k : list) {
            ChiNhanh c = chiNhanhDAO.getChiNhanh(k.getMaCN());
            modelTableKH.addRow(new Object[]{k.getMaKH(), k.getTenKH(), k.getDiaChi(), k.getPhone(), c.getTenCN()});
        }
    }

    private void getDSNhanVien(ArrayList<NhanVien> list) {
        for (NhanVien n : list) {
            ChiNhanh chiNhanh = chiNhanhDAO.getChiNhanh(n.getMaCN());
            modelTableNV.addRow(new Object[]{n.getMaNV(), n.getName(), n.getUser(), n.getPass(), n.getDiaChi(), n.getPhone(), chiNhanh.getTenCN(), n.getRole()});
        }
    }

    private void getDSMatHang(ArrayList<MatHang> list) {
        for (MatHang m : list) {
            modelTableMH.addRow(new Object[]{m.getMaMH(), m.getTenMH(), m.getHangMH(), m.getGiaMH(), m.getSoLuong()});
        }
    }

    private void getDSHoaDon(ArrayList<HoaDon> list) {
        for (HoaDon h : list) {
            KhachHang khachHang = khachHangDAO.getKhachHang(h.getMaKH());
            NhanVien nhanVien = nhanVienDAO.getNhanVien(h.getMaNV());
            modelTableHD.addRow(new Object[]{h.getMaHD(), khachHang.getTenKH(), nhanVien.getName(), h.getNgayLap(), h.getTotal(), h.getLoaiHD()});
        }
    }

    private void getDSItems(ArrayList<Items> list) {
        for (Items i : list) {
            MatHang matHang = matHangDAO.getMatHang(i.getMaMH());
            modelTableItems.addRow(new Object[]{matHang.getMaMH(), matHang.getTenMH(), matHang.getGiaMH(), i.getSoLuong()});
        }
    }

    private void setPicture(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(IMG_PATH + filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void uploadPicture(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, BufferedImage.SCALE_REPLICATE));
            label.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void writeFileImg(String filename) {
        File f = null;
        BufferedImage image = null;
        try {
            f = new File(filename);
            image = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(FormQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            f = new File(IMG_PATH + imgName);
            ImageIO.write(image, "jpg", f);
        } catch (IOException ex) {
            Logger.getLogger(FormQuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteFileImg(String filname) {
        File f = new File(filname);
        if (f.exists()) {
            f.delete();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuNhanVien = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        checkNV = new javax.swing.JCheckBoxMenuItem();
        checkNVHN = new javax.swing.JCheckBoxMenuItem();
        checkHVHCM = new javax.swing.JCheckBoxMenuItem();
        menuMatHang = new javax.swing.JPopupMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        checkBox = new javax.swing.JCheckBoxMenuItem();
        checkBoxHN = new javax.swing.JCheckBoxMenuItem();
        checkBoxHCM = new javax.swing.JCheckBoxMenuItem();
        chiTietSP = new javax.swing.JFrame();
        tenSPLable = new javax.swing.JLabel();
        hangLable = new javax.swing.JLabel();
        manHinhLable = new javax.swing.JLabel();
        hdhLable = new javax.swing.JLabel();
        cameraLable = new javax.swing.JLabel();
        cpuLable = new javax.swing.JLabel();
        ramLable = new javax.swing.JLabel();
        pinLable = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        menuHoaDon = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        checkBoxHD = new javax.swing.JCheckBoxMenuItem();
        checkBoxHDHN = new javax.swing.JCheckBoxMenuItem();
        checkBoxHDHCM = new javax.swing.JCheckBoxMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        menuKhachHang = new javax.swing.JPopupMenu();
        jMenu4 = new javax.swing.JMenu();
        checkKH = new javax.swing.JCheckBoxMenuItem();
        checkHaNoi = new javax.swing.JCheckBoxMenuItem();
        checkHCM = new javax.swing.JCheckBoxMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        userTF = new javax.swing.JTextField();
        passTF = new javax.swing.JTextField();
        nameTF = new javax.swing.JTextField();
        diaChiTF = new javax.swing.JTextField();
        phoneTF = new javax.swing.JTextField();
        chiNhanhCBBox = new javax.swing.JComboBox();
        chucVuCBBox = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nhanVienTable = new javax.swing.JTable();
        locNhanVienLabel = new javax.swing.JLabel();
        nvql1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        maMHTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        hangSXTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        giaTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        danhMucCBBox = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        manHinhTF = new javax.swing.JTextField();
        hdhTF = new javax.swing.JTextField();
        cameraTF = new javax.swing.JTextField();
        cpuTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        ramTF = new javax.swing.JTextField();
        pinTF = new javax.swing.JTextField();
        imgLable = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        tenHangTF = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        matHangTable = new javax.swing.JTable();
        locMatHangLabel = new javax.swing.JLabel();
        nvql2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        soLuongTF = new javax.swing.JTextField();
        chiNhanhCBB = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        nvql5 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        search = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        locMaKhachHang = new javax.swing.JRadioButton();
        locTenKhachHang = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        khachHangTable = new javax.swing.JTable();
        locKHLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        nvql3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        hoaDonTable = new javax.swing.JTable();
        locHoaDonLabel = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        panelChiTiet = new javax.swing.JPanel();
        maHDLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listItems = new javax.swing.JTable();
        maKHLabel = new javax.swing.JLabel();
        tenKHLabel = new javax.swing.JLabel();
        diaChiLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        ngayLapLabel = new javax.swing.JLabel();
        loaiHDLabel = new javax.swing.JLabel();
        nvLabel = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        searchHD = new javax.swing.JTextField();
        maHDRadio = new javax.swing.JRadioButton();
        tenKHRadio = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        nvql4 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();

        jMenuItem1.setText("Update");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuNhanVien.add(jMenuItem1);

        jMenuItem2.setText("Delete");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuNhanVien.add(jMenuItem2);

        jMenuItem3.setText("Refresh");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuNhanVien.add(jMenuItem3);

        jMenu3.setText("Xem danh sách theo");

        buttonGroup5.add(checkNV);
        checkNV.setSelected(true);
        checkNV.setText("Tất cả");
        checkNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNVActionPerformed(evt);
            }
        });
        jMenu3.add(checkNV);

        buttonGroup5.add(checkNVHN);
        checkNVHN.setText("Chi nhánh Hà Nội");
        checkNVHN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNVHNActionPerformed(evt);
            }
        });
        jMenu3.add(checkNVHN);

        buttonGroup5.add(checkHVHCM);
        checkHVHCM.setText("Chi nhánh Hồ Chí Minh");
        checkHVHCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkHVHCMActionPerformed(evt);
            }
        });
        jMenu3.add(checkHVHCM);

        menuNhanVien.add(jMenu3);

        jMenuItem13.setText("Xem chi tiết");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem13);

        jMenuItem4.setText("Update thông tin sản phẩm");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem4);

        jMenuItem5.setText("Delete");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem5);

        jMenuItem6.setText("Refresh");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem6);

        jMenu1.setText("Xem theo :");

        buttonGroup1.add(checkBox);
        checkBox.setSelected(true);
        checkBox.setText("Tất cả");
        checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });
        jMenu1.add(checkBox);

        buttonGroup1.add(checkBoxHN);
        checkBoxHN.setText("Chi nhánh Hà Nội");
        checkBoxHN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxHNActionPerformed(evt);
            }
        });
        jMenu1.add(checkBoxHN);

        buttonGroup1.add(checkBoxHCM);
        checkBoxHCM.setText("Chi nhanh Hồ Chí Minh");
        checkBoxHCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxHCMActionPerformed(evt);
            }
        });
        jMenu1.add(checkBoxHCM);

        menuMatHang.add(jMenu1);

        chiTietSP.setTitle("Chi tiết");
        chiTietSP.setLocation(new java.awt.Point(500, 100));
        chiTietSP.setResizable(false);
        chiTietSP.setSize(new java.awt.Dimension(300, 450));

        tenSPLable.setText("Sản phẩm : ");
        tenSPLable.setPreferredSize(new java.awt.Dimension(250, 15));

        hangLable.setText("Hãng :");
        hangLable.setPreferredSize(new java.awt.Dimension(250, 15));

        manHinhLable.setText("Màn hình :");
        manHinhLable.setPreferredSize(new java.awt.Dimension(250, 15));

        hdhLable.setText("Hệ điều hành :");
        hdhLable.setPreferredSize(new java.awt.Dimension(250, 15));

        cameraLable.setText("Camera :");
        cameraLable.setPreferredSize(new java.awt.Dimension(250, 15));

        cpuLable.setText("CPU :");
        cpuLable.setPreferredSize(new java.awt.Dimension(250, 15));

        ramLable.setText("RAM :");
        ramLable.setPreferredSize(new java.awt.Dimension(250, 15));

        pinLable.setText("PIN :");

        jLabel31.setText("Image :");

        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout chiTietSPLayout = new javax.swing.GroupLayout(chiTietSP.getContentPane());
        chiTietSP.getContentPane().setLayout(chiTietSPLayout);
        chiTietSPLayout.setHorizontalGroup(
            chiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chiTietSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(chiTietSPLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(chiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ramLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tenSPLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hangLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(manHinhLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hdhLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cameraLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cpuLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pinLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        chiTietSPLayout.setVerticalGroup(
            chiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chiTietSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tenSPLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hangLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(manHinhLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hdhLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cameraLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cpuLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ramLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pinLable)
                .addGap(21, 21, 21)
                .addGroup(chiTietSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jMenuItem7.setText("Xem chi tiết");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuHoaDon.add(jMenuItem7);

        jMenu2.setText("Xem danh sách theo");

        buttonGroup2.add(checkBoxHD);
        checkBoxHD.setSelected(true);
        checkBoxHD.setText("Tất cả");
        checkBoxHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxHDActionPerformed(evt);
            }
        });
        jMenu2.add(checkBoxHD);

        buttonGroup2.add(checkBoxHDHN);
        checkBoxHDHN.setText("Chi nhánh Hà Nội");
        checkBoxHDHN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxHDHNActionPerformed(evt);
            }
        });
        jMenu2.add(checkBoxHDHN);

        buttonGroup2.add(checkBoxHDHCM);
        checkBoxHDHCM.setText("Chi nhanh Hồ Chí Minh");
        checkBoxHDHCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxHDHCMActionPerformed(evt);
            }
        });
        jMenu2.add(checkBoxHDHCM);

        menuHoaDon.add(jMenu2);

        jMenuItem8.setText("Refresh");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuHoaDon.add(jMenuItem8);

        jMenu4.setText("Xem theo ");

        buttonGroup7.add(checkKH);
        checkKH.setSelected(true);
        checkKH.setText("Tất cả");
        checkKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkKHActionPerformed(evt);
            }
        });
        jMenu4.add(checkKH);

        buttonGroup7.add(checkHaNoi);
        checkHaNoi.setText("Chi nhánh Hà Nội");
        checkHaNoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkHaNoiActionPerformed(evt);
            }
        });
        jMenu4.add(checkHaNoi);

        buttonGroup7.add(checkHCM);
        checkHCM.setText("Chi nhánh Hồ Chí Minh");
        checkHCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkHCMActionPerformed(evt);
            }
        });
        jMenu4.add(checkHCM);

        menuKhachHang.add(jMenu4);

        jMenuItem9.setText("Refresh");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuKhachHang.add(jMenuItem9);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ManagerShop");

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(995, 1000));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GroupBox1", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Chi nhánh:");
        jLabel1.setPreferredSize(new java.awt.Dimension(60, 15));

        jLabel3.setText("Tài khoản:");
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 15));

        jLabel4.setText("Mật khẩu:");
        jLabel4.setPreferredSize(new java.awt.Dimension(60, 15));

        jLabel5.setText("Họ và tên:");
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 15));

        jLabel6.setText("Địa chỉ:");
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 15));

        jLabel7.setText("SĐT:");
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 15));

        jLabel8.setText("Chức vụ:");
        jLabel8.setPreferredSize(new java.awt.Dimension(60, 15));

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        chiNhanhCBBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hà Nội", "Hồ Chí Minh" }));

        chucVuCBBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bán hàng", "Quản lý" }));

        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("***");

        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("***");

        jLabel22.setForeground(new java.awt.Color(255, 51, 51));
        jLabel22.setText("***");

        jButton2.setText("Lưu sửa đổi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("Refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userTF)
                            .addComponent(chiNhanhCBBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passTF))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(diaChiTF))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chucVuCBBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(98, 98, 98)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel22))
                .addContainerGap(374, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chiNhanhCBBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(passTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(diaChiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chucVuCBBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GroupBox2", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        nhanVienTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        nhanVienTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(nhanVienTable);

        locNhanVienLabel.setText("Tất cả");
        locNhanVienLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(locNhanVienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(locNhanVienLabel)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        nvql1.setText("Tên NVQL :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nvql1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(nvql1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý nhân viên", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thêm sản phẩm mới"));

        jLabel11.setText("Tên hàng:");

        jLabel12.setText("Hãng SX:");

        jLabel13.setText("Đơn giá:");

        jLabel15.setText("Danh mục:");

        danhMucCBBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Smartphone", "Laptop", "Tablet" }));

        jLabel16.setText("Màn hình:");

        jLabel17.setText("HĐH:");

        jLabel18.setText("Camera:");

        jLabel19.setText("CPU:");

        jLabel2.setText("RAM:");

        jLabel9.setText("PIN:");

        jLabel24.setText("Image:");

        pinTF.setToolTipText("");

        imgLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton4.setText("Chọn ảnh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Thêm SP mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("Lưu thay đổi");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setText("Refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel25.setText("Mã MH :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel2)
                                .addComponent(jLabel25))
                            .addGap(26, 26, 26)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ramTF, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(giaTF)
                                .addComponent(hangSXTF)
                                .addComponent(maMHTextField)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                            .addComponent(tenHangTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(danhMucCBBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel9)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16))))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pinTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(manHinhTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hdhTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(28, 28, 28)
                                .addComponent(cameraTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(45, 45, 45)
                                .addComponent(cpuTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(imgLable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imgLable, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(maMHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel16)
                                    .addComponent(manHinhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(hangSXTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hdhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13)
                                            .addComponent(giaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cameraTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tenHangTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel19)
                                    .addComponent(cpuTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(ramTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(pinTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(danhMucCBBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng quản lý mặt hàng"));

        matHangTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        matHangTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(matHangTable);

        locMatHangLabel.setText("Tất cả");
        locMatHangLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(locMatHangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(locMatHangLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        nvql2.setText("Tên NVQL :");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thêm sp vào chi nhánh"));

        jLabel14.setText("Số lượng:");

        chiNhanhCBB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hà Nội", "Hồ Chí Minh" }));

        jLabel10.setText("Chi nhánh:");

        jButton8.setText("Thêm vào chi nhánh");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setText("Refresh");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(chiNhanhCBB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(24, 24, 24)
                                .addComponent(soLuongTF)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(chiNhanhCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(soLuongTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton10))
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(nvql2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(nvql2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản lý mặt hàng", jPanel2);

        nvql5.setText("Tên NVQL :");

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết"));

        jButton12.setText("Tìm kiếm");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        buttonGroup6.add(locMaKhachHang);
        locMaKhachHang.setSelected(true);
        locMaKhachHang.setText("Mã khách hàng");

        buttonGroup6.add(locTenKhachHang);
        locTenKhachHang.setText("Tên khách hàng");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(locTenKhachHang)
                            .addComponent(locMaKhachHang)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton12)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addGap(18, 18, 18)
                .addComponent(locMaKhachHang)
                .addGap(18, 18, 18)
                .addComponent(locTenKhachHang)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng nhân viên"));

        khachHangTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(khachHangTable);

        locKHLabel.setText("Tất cả");
        locKHLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(locKHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(locKHLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nvql5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 630, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(nvql5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản lý khách hàng", jPanel15);

        nvql3.setText("Tên NVQL :");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng hóa đơn mua hàng"));

        hoaDonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        hoaDonTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(hoaDonTable);

        locHoaDonLabel.setText("Tất cả");
        locHoaDonLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(locHoaDonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(locHoaDonLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        jPanel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        maHDLabel.setText("Mã đơn hàng:");

        listItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        listItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(listItems);

        maKHLabel.setText("Mã khách hàng:");

        tenKHLabel.setText("Tên khách hàng :");

        diaChiLabel.setText("Địa chỉ :");

        phoneLabel.setText("Số đt :");

        jLabel32.setText("Danh sách sp :");

        totalLabel.setText("Tổng tiền :");

        ngayLapLabel.setText("Ngày lập : ");

        loaiHDLabel.setText("Loại HD :");

        nvLabel.setText("Nhân viên BH :");

        javax.swing.GroupLayout panelChiTietLayout = new javax.swing.GroupLayout(panelChiTiet);
        panelChiTiet.setLayout(panelChiTietLayout);
        panelChiTietLayout.setHorizontalGroup(
            panelChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChiTietLayout.createSequentialGroup()
                        .addGroup(panelChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(maKHLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(maHDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(ngayLapLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelChiTietLayout.createSequentialGroup()
                        .addGroup(panelChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nvLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(loaiHDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tenKHLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(diaChiLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelChiTietLayout.setVerticalGroup(
            panelChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maHDLabel)
                    .addComponent(ngayLapLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maKHLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tenKHLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(diaChiLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phoneLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loaiHDLabel)
                .addGap(11, 11, 11)
                .addComponent(nvLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalLabel)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jButton9.setText("Tìm kiếm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        buttonGroup3.add(maHDRadio);
        maHDRadio.setSelected(true);
        maHDRadio.setText("Mã hóa đơn");

        buttonGroup3.add(tenKHRadio);
        tenKHRadio.setText("Tên khách hàng");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenKHRadio)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(maHDRadio)
                        .addGap(58, 58, 58)
                        .addComponent(searchHD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(searchHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maHDRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tenKHRadio)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(nvql3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(nvql3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản lý hóa đơn", jPanel4);

        nvql4.setText("Tên NVQL : ");

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Tùy chon"));

        jButton11.setText("Chart");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(308, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11)
                .addContainerGap(446, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Biểu đồ"));

        chartPanel.setBackground(new java.awt.Color(255, 255, 255));
        chartPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(nvql4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(nvql4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thống kê", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getAbsolutePath().endsWith(".jpg");
            }

            @Override
            public String getDescription() {
                return "*.jpg";
            }
        });
        int returnValue = fileDialog.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileDialog.getSelectedFile();
            imgName = file.getName();
            imgPath = file.getPath();
            uploadPicture(imgLable, imgPath);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String user = userTF.getText().trim();
        String chiNhanh = String.valueOf(chiNhanhCBBox.getSelectedItem());
        String pass = passTF.getText().trim();
        String name = nameTF.getText().trim();
        String diaChi = diaChiTF.getText().trim();
        String phone = phoneTF.getText().trim();
        String chucVu = String.valueOf(chucVuCBBox.getSelectedItem());
        int maCN = 1;
        if (chiNhanh.equals("Hồ Chí Minh")) {
            maCN = 2;
        }
        if (user.length() == 0 || pass.length() == 0 || name.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đầy đủ thông tin cần thiết", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            if (nhanVienDAO.checkUser(user)) {
                JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaCN(maCN);
                nhanVien.setUser(user);
                nhanVien.setPass(pass);
                nhanVien.setName(name);
                nhanVien.setDiaChi(diaChi);
                nhanVien.setPhone(phone);
                nhanVien.setRole(chucVu);
                nhanVienDAO.addNhanVien(nhanVien);
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                resetDataTableNV();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int rowNV = nhanVienTable.getSelectedRow();
        int maNV = Integer.valueOf(String.valueOf(nhanVienTable.getValueAt(rowNV, 0)));
        if (rowNV == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            NhanVien nhanVien = nhanVienDAO.getNhanVien(maNV);
            if (nhanVien.getMaCN() == 1) {
                chiNhanhCBBox.setSelectedIndex(0);
            } else {
                chiNhanhCBBox.setSelectedIndex(1);
            }
            if (nhanVien.getRole().equals("Bán hàng")) {
                chucVuCBBox.setSelectedIndex(0);
            } else {
                chucVuCBBox.setSelectedIndex(1);
            }
            userTF.setText(nhanVien.getUser());
            passTF.setText(nhanVien.getPass());
            nameTF.setText(nhanVien.getName());
            diaChiTF.setText(nhanVien.getDiaChi());
            phoneTF.setText(nhanVien.getPhone());
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int row = nhanVienTable.getSelectedRow();
        int maNV = Integer.valueOf(String.valueOf(nhanVienTable.getValueAt(row, 0)));
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chán muốn xóa!", "Thông báo", JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                nhanVienDAO.delNhanVien(maNV);
                JOptionPane.showMessageDialog(this, "Delete thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                resetDataTableNV();
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        resetDataTableNV();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        //xem chi tiet mat hang
        int row = matHangTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            int maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
            MatHang matHang = matHangDAO.getMatHang(maMH);
            tenSPLable.setText("Sản phẩm : " + matHang.getTenMH());
            hangLable.setText("Hãng : " + matHang.getHangMH());
            manHinhLable.setText("Màn hình : " + matHang.getChiTiet().getManHinh());
            hdhLable.setText("Hệ điều hành : " + matHang.getChiTiet().getHeDieuHanh());
            cameraLable.setText("Camera : " + matHang.getChiTiet().getCamera());
            cpuLable.setText("CPU : " + matHang.getChiTiet().getCpu());
            ramLable.setText("RAM : " + matHang.getChiTiet().getRam());
            pinLable.setText("Pin : " + matHang.getChiTiet().getPin());
            jLabel23.setSize(100, 120);
            setPicture(jLabel23, matHang.getImg());

            chiTietSP.setVisible(true);
        }

    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //upload thong tin mat hang len text field de thuc hien update
        int row = matHangTable.getSelectedRow();
        int maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn mặt hàng nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            MatHang matHang = matHangDAO.getMatHang(maMH);
            if (matHang.getMaDM() == 1) {
                danhMucCBBox.setSelectedIndex(0);
            } else if (matHang.getMaDM() == 2) {
                danhMucCBBox.setSelectedIndex(1);
            } else {
                danhMucCBBox.setSelectedIndex(2);
            }
            maMHTextField.setText(String.valueOf(matHang.getMaMH()));
            tenHangTF.setText(matHang.getTenMH());
            hangSXTF.setText(matHang.getHangMH());
            giaTF.setText(matHang.getGiaMH());
            manHinhTF.setText(matHang.getChiTiet().getManHinh());
            hdhTF.setText(matHang.getChiTiet().getHeDieuHanh());
            cameraTF.setText(matHang.getChiTiet().getCamera());
            cpuTF.setText(matHang.getChiTiet().getCpu());
            ramTF.setText(matHang.getChiTiet().getRam());
            pinTF.setText(matHang.getChiTiet().getPin());
            setPicture(imgLable, matHang.getImg());
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //update nhan vien
        int row = nhanVienTable.getSelectedRow();
        int maNV = Integer.valueOf(String.valueOf(nhanVienTable.getValueAt(row, 0)));
        String user = userTF.getText().trim();
        String chiNhanh = String.valueOf(chiNhanhCBBox.getSelectedItem());
        String pass = passTF.getText().trim();
        String name = nameTF.getText().trim();
        String diaChi = diaChiTF.getText().trim();
        String phone = phoneTF.getText().trim();
        String chucVu = String.valueOf(chucVuCBBox.getSelectedItem());
        int maCN = 1;
        if (chiNhanh.equals("Hồ Chí Minh")) {
            maCN = 2;
        }
        if (row != -1) {
            NhanVien nhanVien = nhanVienDAO.getNhanVien(maNV);
            if (user.length() == 0 || pass.length() == 0 || name.length() == 0) {
                JOptionPane.showMessageDialog(this, "Chưa nhập đầy đủ thông tin cần thiết", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                nhanVien.setMaCN(maCN);
                nhanVien.setUser(user);
                nhanVien.setPass(pass);
                nhanVien.setName(name);
                nhanVien.setDiaChi(diaChi);
                nhanVien.setPhone(phone);
                nhanVien.setRole(chucVu);
                nhanVienDAO.editNhanVien(nhanVien);
                JOptionPane.showMessageDialog(this, "Update thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                resetDataTableNV();
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //update mat hang vao CSDL
        int row = matHangTable.getSelectedRow();
        int maMH = 0, giaHang = 0;
        String chiNhanh = String.valueOf(chiNhanhCBB.getSelectedItem());
        String ten = tenHangTF.getText().trim();
        String hang = hangSXTF.getText().trim();
        String gia = giaTF.getText().trim();
        String danhMuc = String.valueOf(danhMucCBBox.getSelectedItem());
        String manHinh = manHinhTF.getText().trim();
        String hdh = hdhTF.getText().trim();
        String camera = cameraTF.getText().trim();
        String cpu = cpuTF.getText().trim();
        String ram = ramTF.getText().trim();
        String pin = pinTF.getText().trim();
        try {
            maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
            giaHang = Integer.valueOf(gia);
        } catch (Exception e) {
            System.err.println("Sai định dạng");
            JOptionPane.showMessageDialog(this, "Nhập sai định dạng", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        int dm = 1;
        if (danhMuc.equals("Laptop")) {
            dm = 2;
        } else if (danhMuc.equals("Tablet")) {
            dm = 3;
        }
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn mặt hàng nào!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            MatHang matHang = matHangDAO.getMatHang(maMH);
            if (ten.length() == 0 || hang.length() == 0 || gia.length() == 0) {
                JOptionPane.showMessageDialog(this, "Chưa nhập đủ thông tin cần thiết!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                matHang.setTenMH(ten);
                matHang.setHangMH(hang);
                matHang.setGiaMH(gia);
                matHang.setMaDM(dm);
                matHang.setChiTiet(new ChiTiet(manHinh, hdh, camera, cpu, ram, pin));
                matHangDAO.editMatHang(matHang);
                JOptionPane.showMessageDialog(this, "Update thông tin mặt hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                modelTableMH.setRowCount(0);
                getDSMatHang(matHangDAO.getALL());
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        chiNhanhCBBox.setSelectedIndex(0);
        userTF.setText(null);
        passTF.setText(null);
        nameTF.setText(null);
        diaChiTF.setText(null);
        phoneTF.setText(null);
        chucVuCBBox.setSelectedIndex(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        chiNhanhCBB.setSelectedIndex(0);
        maMHTextField.setText(null);
        hangSXTF.setText(null);
        tenHangTF.setText(null);
        giaTF.setText(null);
        soLuongTF.setText(null);
        danhMucCBBox.setSelectedIndex(0);
        manHinhTF.setText(null);
        hdhTF.setText(null);
        cameraTF.setText(null);
        cpuTF.setText(null);
        ramTF.setText(null);
        pinTF.setText(null);
        imgLable.setIcon(null);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //them thong tin san pham vao CSDL
        int maMH = 0;
        try {
            maMH = Integer.valueOf(maMHTextField.getText().trim());
        } catch (Exception e) {
        }
        String ten = tenHangTF.getText().trim();
        String hang = hangSXTF.getText().trim();
        String gia = giaTF.getText().trim();
        String danhMuc = String.valueOf(danhMucCBBox.getSelectedItem());
        String manHinh = manHinhTF.getText().trim();
        String hdh = hdhTF.getText().trim();
        String camera = cameraTF.getText().trim();
        String cpu = cpuTF.getText().trim();
        String ram = ramTF.getText().trim();
        String pin = pinTF.getText().trim();
        System.out.println("ok 1");
        int dm = 1;
        if (danhMuc.equals("Laptop")) {
            dm = 2;
        } else if (danhMuc.equals("Tablet")) {
            dm = 3;
        }
        if (ten.length() == 0 || hang.length() == 0 || gia.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa nhập liệu đủ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else if (maMH == 0) {
            JOptionPane.showMessageDialog(this, "Sai đinh dang hoăc nhập thiếu", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else if (matHangDAO.checkMaMH(maMH)) {
            JOptionPane.showMessageDialog(this, "Mã mặt hàng đã trùng. Mời ktra lại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                MatHang matHang = new MatHang();
                matHang.setMaMH(maMH);
                matHang.setTenMH(ten);
                matHang.setHangMH(hang);
                matHang.setGiaMH(gia);
                matHang.setMaDM(dm);
                matHang.setChiTiet(new ChiTiet(manHinh, hdh, camera, cpu, ram, pin));
                matHang.setImg(imgName);
                writeFileImg(imgPath);
                matHangDAO.addMatHang(matHang);
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm mới thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setDataTableMH();
            } catch (Exception e) {
                System.err.println("Lỗi writeFileAnh");
                JOptionPane.showMessageDialog(this, "Lỗi upload file ảnh", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //them san pham vao tung chi nhanh vao CSDL
        int maMH = 0, soLuong = 0, maCN = 1;
        String chiNhanh = String.valueOf(chiNhanhCBB.getSelectedItem());
        try {
            maMH = Integer.valueOf(maMHTextField.getText().trim());
            soLuong = Integer.valueOf(soLuongTF.getText().trim());
        } catch (Exception e) {
            System.err.println("Sai định dạng mã MH, hoăc số lượng");
            JOptionPane.showMessageDialog(this, "Sai định dạng", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        if (chiNhanh.equals("Hồ Chí Minh")) {
            maCN = 2;
        }
        if (maMH != 0 && soLuong != 0) {
            if (chiNhanhMatHangDAO.checkMaMH(maCN, maMH)) {
                soLuong += chiNhanhMatHangDAO.getSoLuongMatHang(maMH, maCN);
                chiNhanhMatHangDAO.edit(maMH, maCN, soLuong);
                setDataTableMH();
                JOptionPane.showMessageDialog(this, "Đã thêm sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                chiNhanhMatHangDAO.add(maCN, maMH, soLuong);
                setDataTableMH();
                JOptionPane.showMessageDialog(this, "Đã thêm sản phẩm mới vào chi nhánh thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        //refresh mat hang trong Bang Mat Hang
        setDataTableMH();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxActionPerformed
        //xem bang Mat Hang void che do chon Tat ca
        setDataTableMH();
    }//GEN-LAST:event_checkBoxActionPerformed

    private void checkBoxHNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxHNActionPerformed
        //xem bang Mat Hang void che do chon Ha Noi
        setDataTableMH();
    }//GEN-LAST:event_checkBoxHNActionPerformed

    private void checkBoxHCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxHCMActionPerformed
        //xem bang Mat Hang void che do chon HCM
        setDataTableMH();
    }//GEN-LAST:event_checkBoxHCMActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // Delete mat hang trong bang mat hang
        int row = matHangTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn mặt hàng nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            int maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                if (checkBox.isSelected()) {
                    matHangService.delMatHang(maMH);
                } else if (checkBoxHN.isSelected()) {
                    matHangService.deleteMatHangByCN(maMH, 1);
                } else if (checkBoxHCM.isSelected()) {
                    matHangService.deleteMatHangByCN(maMH, 2);
                }
                setDataTableMH();
            }
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //refresh button 
        soLuongTF.setText(null);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void checkBoxHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxHDActionPerformed
        setDataTableHD();
    }//GEN-LAST:event_checkBoxHDActionPerformed

    private void checkBoxHDHNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxHDHNActionPerformed
        setDataTableHD();
    }//GEN-LAST:event_checkBoxHDHNActionPerformed

    private void checkBoxHDHCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxHDHCMActionPerformed
        setDataTableHD();
    }//GEN-LAST:event_checkBoxHDHCMActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        setDataTableHD();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // xem chi tiet hoa don
        int row = hoaDonTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            int maHD = 0;
            try {
                maHD = Integer.valueOf(String.valueOf(hoaDonTable.getValueAt(row, 0)));
                HoaDon hoaDon = hoaDonDAO.getHoaDon(maHD);
                KhachHang khachHang = khachHangDAO.getKhachHang(hoaDon.getMaKH());
                //set data
                maHDLabel.setText("Mã đơn hàng : " + hoaDon.getMaHD());
                maKHLabel.setText("Mã khách hàng : " + khachHang.getMaKH());
                tenKHLabel.setText("Tên khách hàng : " + khachHang.getTenKH());
                diaChiLabel.setText("Địa chỉ : " + khachHang.getDiaChi());
                phoneLabel.setText("Số đt : " + khachHang.getPhone());
                nvLabel.setText("Nhân viên BH : " + nhanVienDAO.getNhanVien(hoaDon.getMaNV()).getName());
                ngayLapLabel.setText("Ngày lập : " + hoaDon.getNgayLap());
                totalLabel.setText("Tổng tiền : " + hoaDon.getTotal());
                modelTableItems.setRowCount(0);
                getDSItems(giaoDichDAO.getMatHangByHoaDon(maHD));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi mã hóa đơn", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // Tìm kiem hoa don
        String input = searchHD.getText().trim();
        int maHD = 0;
        if (maHDRadio.isSelected()) {
            try {
                maHD = Integer.valueOf(input);
            } catch (Exception e) {
                System.err.println("Lỗi định dạng mã khách hàng");
                JOptionPane.showMessageDialog(this, "Sai định dạng ma KH", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            if (maHD != 0) {
                modelTableHD.setRowCount(0);
                ArrayList<HoaDon> list = new ArrayList<HoaDon>();
                list.add(hoaDonDAO.getHoaDon(maHD));
                getDSHoaDon(list);
            }
        } else if (tenKHRadio.isSelected()) {
            modelTableKH.setRowCount(0);
            getDSHoaDon(hoaDonDAO.getHoaDon(input));
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // thống kê số lượng sản phẩm bán ra theo danh muc sp theo thang, nam
//        DefaultPieDataset dataset = new DefaultPieDataset();
//        dataset.setValue("Smartphone", 30);
//        dataset.setValue("Laptop", 20);
//        dataset.setValue("Tablet", 10);
//        JFreeChart chart = ChartFactory.createPieChart3D("Thống kê sản phẩm bán ra", dataset, true, true, true);
//        ChartPanel panel = new ChartPanel(chart);
//        chartPanel.removeAll();
//        chartPanel.add(panel, BorderLayout.CENTER);
//        chartPanel.validate();
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void checkNVHNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNVHNActionPerformed
        resetDataTableNV();
    }//GEN-LAST:event_checkNVHNActionPerformed

    private void checkNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNVActionPerformed
        resetDataTableNV();
    }//GEN-LAST:event_checkNVActionPerformed

    private void checkHVHCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkHVHCMActionPerformed
        resetDataTableNV();
    }//GEN-LAST:event_checkHVHCMActionPerformed

    private void checkKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkKHActionPerformed
        setDataTableKH();
    }//GEN-LAST:event_checkKHActionPerformed

    private void checkHaNoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkHaNoiActionPerformed
        setDataTableKH();
    }//GEN-LAST:event_checkHaNoiActionPerformed

    private void checkHCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkHCMActionPerformed
        setDataTableKH();
    }//GEN-LAST:event_checkHCMActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        setDataTableKH();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String input = search.getText().trim();
        int maKH = 0;
        if (locMaKhachHang.isSelected()) {
            try {
                maKH = Integer.valueOf(input);
            } catch (Exception e) {
                System.err.println("Lỗi định dạng mã khách hàng");
                JOptionPane.showMessageDialog(this, "Sai định dạng ma KH", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            if (maKH != 0) {
                modelTableKH.setRowCount(0);
                ArrayList<KhachHang> list = new ArrayList<KhachHang>();
                list.add(khachHangDAO.getKhachHang(maKH));
                getDSKhachHang(list);
            }
        } else if (locTenKhachHang.isSelected()) {
            modelTableKH.setRowCount(0);
            getDSKhachHang(khachHangDAO.getKhachHang(input));
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JLabel cameraLable;
    private javax.swing.JTextField cameraTF;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JCheckBoxMenuItem checkBox;
    private javax.swing.JCheckBoxMenuItem checkBoxHCM;
    private javax.swing.JCheckBoxMenuItem checkBoxHD;
    private javax.swing.JCheckBoxMenuItem checkBoxHDHCM;
    private javax.swing.JCheckBoxMenuItem checkBoxHDHN;
    private javax.swing.JCheckBoxMenuItem checkBoxHN;
    private javax.swing.JCheckBoxMenuItem checkHCM;
    private javax.swing.JCheckBoxMenuItem checkHVHCM;
    private javax.swing.JCheckBoxMenuItem checkHaNoi;
    private javax.swing.JCheckBoxMenuItem checkKH;
    private javax.swing.JCheckBoxMenuItem checkNV;
    private javax.swing.JCheckBoxMenuItem checkNVHN;
    private javax.swing.JComboBox chiNhanhCBB;
    private javax.swing.JComboBox chiNhanhCBBox;
    private javax.swing.JFrame chiTietSP;
    private javax.swing.JComboBox chucVuCBBox;
    private javax.swing.JLabel cpuLable;
    private javax.swing.JTextField cpuTF;
    private javax.swing.JComboBox danhMucCBBox;
    private javax.swing.JLabel diaChiLabel;
    private javax.swing.JTextField diaChiTF;
    private javax.swing.JTextField giaTF;
    private javax.swing.JLabel hangLable;
    private javax.swing.JTextField hangSXTF;
    private javax.swing.JLabel hdhLable;
    private javax.swing.JTextField hdhTF;
    private javax.swing.JTable hoaDonTable;
    private javax.swing.JLabel imgLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable khachHangTable;
    private javax.swing.JTable listItems;
    private javax.swing.JLabel loaiHDLabel;
    private javax.swing.JLabel locHoaDonLabel;
    private javax.swing.JLabel locKHLabel;
    private javax.swing.JRadioButton locMaKhachHang;
    private javax.swing.JLabel locMatHangLabel;
    private javax.swing.JLabel locNhanVienLabel;
    private javax.swing.JRadioButton locTenKhachHang;
    private javax.swing.JLabel maHDLabel;
    private javax.swing.JRadioButton maHDRadio;
    private javax.swing.JLabel maKHLabel;
    private javax.swing.JTextField maMHTextField;
    private javax.swing.JLabel manHinhLable;
    private javax.swing.JTextField manHinhTF;
    private javax.swing.JTable matHangTable;
    private javax.swing.JPopupMenu menuHoaDon;
    private javax.swing.JPopupMenu menuKhachHang;
    private javax.swing.JPopupMenu menuMatHang;
    private javax.swing.JPopupMenu menuNhanVien;
    private javax.swing.JTextField nameTF;
    private javax.swing.JLabel ngayLapLabel;
    private javax.swing.JTable nhanVienTable;
    private javax.swing.JLabel nvLabel;
    private javax.swing.JLabel nvql1;
    private javax.swing.JLabel nvql2;
    private javax.swing.JLabel nvql3;
    private javax.swing.JLabel nvql4;
    private javax.swing.JLabel nvql5;
    private javax.swing.JPanel panelChiTiet;
    private javax.swing.JTextField passTF;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JLabel pinLable;
    private javax.swing.JTextField pinTF;
    private javax.swing.JLabel ramLable;
    private javax.swing.JTextField ramTF;
    private javax.swing.JTextField search;
    private javax.swing.JTextField searchHD;
    private javax.swing.JTextField soLuongTF;
    private javax.swing.JTextField tenHangTF;
    private javax.swing.JLabel tenKHLabel;
    private javax.swing.JRadioButton tenKHRadio;
    private javax.swing.JLabel tenSPLable;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JTextField userTF;
    // End of variables declaration//GEN-END:variables
}
