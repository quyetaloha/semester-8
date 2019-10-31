package view;

import controller.dao.ChiNhanhDAO;
import controller.dao.ChiNhanhMatHangDAO;
import controller.dao.DanhMucDAO;
import controller.dao.GiaoDichDAO;
import controller.dao.HoaDonDAO;
import controller.dao.KhachHangDAO;
import controller.dao.MatHangDAO;
import controller.dao.NhanhVienDAO;
import controller.dao.impl.ChiNhanhDAOImpl;
import controller.dao.impl.ChiNhanhMatHangImpl;
import controller.dao.impl.DanhMucDAOImpl;
import controller.dao.impl.GiaoDichDAOImpl;
import controller.dao.impl.HoaDonDAOImpl;
import controller.dao.impl.KhachHangDAOImpl;
import controller.dao.impl.MatHangDAOImpl;
import controller.dao.impl.NhanVienDAOImpl;
import controller.service.MatHangService;
import controller.service.impl.MatHangServiceImpl;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.Items;
import model.KhachHang;
import model.MatHang;
import model.NhanVien;

public class FormBanHang extends javax.swing.JFrame {

    private DefaultTableModel modelHoaDon, modelKhachHang, modelMatHang, modelItems;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private HoaDonDAO hoaDonDAO;
    private KhachHangDAO khachHangDAO;
    private ChiNhanhDAO chiNhanhDAO;
    private NhanhVienDAO nhanhVienDAO;
    private GiaoDichDAO giaoDichDAO;
    private DanhMucDAO danhMucDAO;
    private MatHangDAO matHangDAO;
    private MatHangService matHangService;
    private ChiNhanhMatHangDAO chiNhanhMatHangDAO;
    private NhanVien nhanVien;

    static ArrayList<Items> listItems = new ArrayList<Items>();
    private final String IMG_PATH = "C:\\Users\\Trinh Nam\\Downloads\\App\\SmartphoneShop\\src\\view\\img";

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public FormBanHang(NhanVien nhanVien) {
        initComponents();

        setNhanVien(nhanVien);

        hoaDonDAO = new HoaDonDAOImpl();
        khachHangDAO = new KhachHangDAOImpl();
        chiNhanhDAO = new ChiNhanhDAOImpl();
        nhanhVienDAO = new NhanVienDAOImpl();
        giaoDichDAO = new GiaoDichDAOImpl();
        danhMucDAO = new DanhMucDAOImpl();
        matHangService = new MatHangServiceImpl();
        matHangDAO = new MatHangDAOImpl();
        chiNhanhMatHangDAO = new ChiNhanhMatHangImpl();

        maNVLabel.setText("Mã NV : " + nhanVien.getMaNV());
        maNV3Label.setText("Mã NV : " + nhanVien.getMaNV());
        tenNVLabel.setText("Tên NV : " + nhanVien.getName());
        tenNV3Label.setText("Tên NV : " + nhanVien.getName());
        cnLabel.setText("Chi nhanh : " + chiNhanhDAO.getChiNhanh(nhanVien.getMaCN()).getTenCN());
        cn3Label.setText("Chi nhanh : " + chiNhanhDAO.getChiNhanh(nhanVien.getMaCN()).getTenCN());

        modelHoaDon = new DefaultTableModel();
        modelKhachHang = new DefaultTableModel();
        modelMatHang = new DefaultTableModel();
        modelItems = new DefaultTableModel();

        modelHoaDon.setColumnIdentifiers(new Object[]{"Mã HD", "Tên khách hàng", "Tên nhân viên", "Ngày lập", "Tổng tiền"});
        modelKhachHang.setColumnIdentifiers(new Object[]{"Mã KH", "Tên khách hàng", "Địa chỉ", "Số ĐT"});
        modelMatHang.setColumnIdentifiers(new Object[]{"Mã MH", "Tên mặt hàng", "Hãng", "Danh mục", "Đơn giá"});
        modelItems.setColumnIdentifiers(new Object[]{"STT", "Mã MH", "Tên MH", "Số lượng"});

        hoaDonTable.setModel(modelHoaDon);
        khachHangTable2.setModel(modelKhachHang);
        matHangTable.setModel(modelMatHang);
        listItemsTable.setModel(modelItems);
        setDSHoaDon(hoaDonDAO.getHoaDonByCN(nhanVien.getMaCN()));
        setDSKhachHang(khachHangDAO.getKhachHangByCN(nhanVien.getMaCN()));
        setDSMatHang(matHangService.getMatHangByCN(nhanVien.getMaCN()));

        hoaDonTable.setComponentPopupMenu(menuHoaDon);
        khachHangTable2.setComponentPopupMenu(menuKhachHang);
        matHangTable.setComponentPopupMenu(menuMatHang);

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

    private void setDSHoaDon(ArrayList<HoaDon> list) {
        for (HoaDon h : list) {
            KhachHang khachHang = khachHangDAO.getKhachHang(h.getMaKH());
            if (h.getMaNV() == 0) {
                modelHoaDon.addRow(new Object[]{h.getMaHD(), khachHang.getTenKH(), "NULL", h.getNgayLap(), h.getTotal(), h.getLoaiHD()});
            } else {
                NhanVien nhanVien = nhanhVienDAO.getNhanVien(h.getMaNV());
                modelHoaDon.addRow(new Object[]{h.getMaHD(), khachHang.getTenKH(), nhanVien.getName(), h.getNgayLap(), h.getTotal(), h.getLoaiHD()});
            }
        }
    }

    private void setDSKhachHang(ArrayList<KhachHang> list) {
        for (KhachHang k : list) {
            modelKhachHang.addRow(new Object[]{k.getMaKH(), k.getTenKH(), k.getDiaChi(), k.getPhone()});
        }
    }

    private void setDSMatHang(ArrayList<MatHang> list) {
        for (MatHang m : list) {
            modelMatHang.addRow(new Object[]{m.getMaMH(), m.getTenMH(), m.getHangMH(), m.getMaDM(), m.getGiaMH()});
        }
    }

    private void setDSItems(ArrayList<Items> list) {
        int stt = 0;
        for (Items i : list) {
            stt++;
            MatHang m = matHangService.getMatHang(i.getMaMH());
            modelItems.addRow(new Object[]{stt, i.getMaMH(), m.getTenMH(), i.getSoLuong()});

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuHoaDon = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuKhachHang = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        menuMatHang = new javax.swing.JPopupMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        menuItems = new javax.swing.JPopupMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tenNVLabel = new javax.swing.JLabel();
        maNVLabel = new javax.swing.JLabel();
        cnLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hoaDonTable = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        maMHTF = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listItemsTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tenKHTF = new javax.swing.JTextField();
        diaChiTF = new javax.swing.JTextField();
        phoneTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        maKHTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        khachHangTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tenNV3Label = new javax.swing.JLabel();
        maNV3Label = new javax.swing.JLabel();
        cn3Label = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        matHangTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        maMHLabel = new javax.swing.JLabel();
        tenMHLabel = new javax.swing.JLabel();
        danhMucLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        imgLabel = new javax.swing.JLabel();
        hangLabel = new javax.swing.JLabel();
        donGiaLabel = new javax.swing.JLabel();
        manHinhLabel = new javax.swing.JLabel();
        hdhLabel = new javax.swing.JLabel();
        cameraLable = new javax.swing.JLabel();
        cpuLabel = new javax.swing.JLabel();
        ramLabel = new javax.swing.JLabel();
        pinLabel = new javax.swing.JLabel();

        jMenuItem1.setText("Xem chi tiết");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuHoaDon.add(jMenuItem1);

        jMenuItem2.setText("Delete");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuHoaDon.add(jMenuItem2);

        jMenuItem3.setText("Refresh");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuHoaDon.add(jMenuItem3);

        jMenuItem5.setText("Refresh");
        menuKhachHang.add(jMenuItem5);

        jMenuItem6.setText("Xem chi tiết");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem6);

        jMenuItem7.setText("Refresh");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem7);

        jMenuItem8.setText("Delete");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuItems.add(jMenuItem8);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ManagerShop");
        setMaximumSize(new java.awt.Dimension(700, 450));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        tenNVLabel.setText("Tên NV :");

        maNVLabel.setText("Mã NV : ");

        cnLabel.setText("Chi nhánh :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maNVLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tenNVLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maNVLabel)
                .addGap(18, 18, 18)
                .addComponent(tenNVLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(cnLabel)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 51, 0))); // NOI18N

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
        jScrollPane1.setViewportView(hoaDonTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("GroupBox"));

        jButton1.setText("Thêm HĐ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã mặt hàng");

        jButton3.setText("Thêm MH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        listItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã MH", "Tên MH", "Số lượng"
            }
        ));
        listItemsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(listItemsTable);

        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("***");

        jLabel8.setText("Tên khách hàng :");

        jLabel9.setText("Địa chỉ :");

        jLabel10.setText("Phone :");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("***");

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("***");

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("***");

        jLabel15.setText("SL :");

        jLabel1.setText("Mã khách hàng :");

        jLabel16.setForeground(new java.awt.Color(255, 0, 51));
        jLabel16.setText("***");

        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        totalLabel.setText("Tổng tiền :");

        jButton2.setText("Check");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(diaChiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tenKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel12))
                                        .addGap(19, 19, 19))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addGap(20, 20, 20)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(25, 25, 25)
                                        .addComponent(maKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(95, 95, 95)
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(maMHTF, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton1)
                        .addComponent(jButton4))
                    .addComponent(totalLabel))
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tenKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaChiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maMHTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        khachHangTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        khachHangTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(khachHangTable2);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel5.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Hóa đơn", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        tenNV3Label.setText("Tên NV :");

        maNV3Label.setText("Mã NV : ");

        cn3Label.setText("Chi nhánh :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maNV3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tenNV3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cn3Label, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maNV3Label)
                .addGap(18, 18, 18)
                .addComponent(tenNV3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(cn3Label)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng danh sách mặt hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 51, 255))); // NOI18N

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
        jScrollPane3.setViewportView(matHangTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("GroupBox"));

        maMHLabel.setText("Mã mặt hàng : ");

        tenMHLabel.setText("Tên mặt hàng :");

        danhMucLabel.setText("Danh mục : ");

        jLabel11.setText("Image :");

        imgLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        hangLabel.setText("Hãng : ");

        donGiaLabel.setText("Đơn giá : ");

        manHinhLabel.setText("Màn hình :");

        hdhLabel.setText("Hệ điều hành :");

        cameraLable.setText("Camera :");

        cpuLabel.setText("CPU :");

        ramLabel.setText("RAM :");

        pinLabel.setText("PIN :");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tenMHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(danhMucLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maMHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cameraLable, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(donGiaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hangLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(manHinhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hdhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(cpuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ramLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cpuLabel)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maMHLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hangLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tenMHLabel)
                        .addComponent(donGiaLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(ramLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manHinhLabel)
                    .addComponent(danhMucLabel)
                    .addComponent(pinLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(hdhLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cameraLable))
                    .addComponent(jLabel11)
                    .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mặt hàng", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int row = hoaDonTable.getSelectedRow();
        int maHD = 0;
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hàng nào", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    maHD = Integer.valueOf(String.valueOf(hoaDonTable.getValueAt(row, 0)));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Sai đinh dạng mã HD", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
                if (maHD != 0) {
                    giaoDichDAO.delete(maHD);
                    hoaDonDAO.delHoaDon(maHD);
                    JOptionPane.showMessageDialog(this, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    modelHoaDon.setRowCount(0);
                    setDSHoaDon(hoaDonDAO.getAll());
                }
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        modelHoaDon.setRowCount(0);
        setDSHoaDon(hoaDonDAO.getAll());
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        int row = listItemsTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Ban chua chon hang nao", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                int stt = Integer.valueOf(String.valueOf(listItemsTable.getValueAt(row, 0)));
                for (Items i : listItems) {
                    listItems.remove(stt);
                    break;
                }

            }
        }

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        maKHTF.setText(null);
        tenKHTF.setText(null);
        diaChiTF.setText(null);
        phoneTF.setText(null);
        maMHTF.setText(null);
        jSpinner1.setValue(0);
        listItems = new ArrayList<>();
        modelItems.setRowCount(0);
        totalLabel.setText("Tổng tiền : ");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int maMH = 0, soLuong = 0;
        soLuong = Integer.valueOf(String.valueOf(jSpinner1.getValue()));
        boolean ktra = true;
        try {
            maMH = Integer.valueOf(maMHTF.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sai định dang mã mặt hàng", "Thông báo", JOptionPane.ERROR_MESSAGE);
            System.err.println("Lỗi maMH");
        }
        if (maMH != 0) {
            if (matHangService.getMatHang(maMH) != null) {
                for (Items i : listItems) {
                    if (i.getMaMH() == maMH) {
                        i.setSoLuong(i.getSoLuong() + soLuong);
                        ktra = false;
                        break;
                    }
                }
                if (ktra) {
                    Items items = new Items();
                    items.setMaMH(maMH);
                    items.setSoLuong(soLuong);
                    listItems.add(items);
                }
                int total = 0;
                for (Items i : listItems) {
                    total += i.getSoLuong() * Integer.valueOf(matHangService.getMatHang(i.getMaMH()).getGiaMH());
                }
                totalLabel.setText("Tổng tiền : " + total);
                modelItems.setRowCount(0);
                setDSItems(listItems);
            } else {
                System.err.println("null mat hang");
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void setSoLuongMatHang(ArrayList<Items> list) {
        for (Items i : list) {
            int maMH = i.getMaHD(), soLuong = i.getSoLuong(), maCN = this.getNhanVien().getMaCN();
            MatHang matHang = matHangService.getMatHang(maMH, maCN);
            matHang.setSoLuong(matHang.getSoLuong() - soLuong);
            chiNhanhMatHangDAO.edit(maMH, maCN, soLuong);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int maKH = 0;
        try {
            maKH = Integer.valueOf(maKHTF.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng mã khách hàng ", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        KhachHang khachHang = khachHangDAO.getKhachHang(maKH);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaKH(maKH);
        hoaDon.setLoaiHD("Offline");
        hoaDon.setMaNV(getNhanVien().getMaNV());
        int total = 0;
        for (Items list : listItems) {
            total += list.getSoLuong() * Integer.valueOf(matHangService.getMatHang(list.getMaMH()).getGiaMH());
        }
        hoaDon.setTotal(total);
        hoaDon.setNgayLap(df.format(new Date()));
        if (khachHang != null) {
            //luu thong tin hoa don vao database
            hoaDonDAO.addHoaDon(hoaDon);
            for (Items i : listItems) {
                i.setMaHD(hoaDon.getMaHD());
                giaoDichDAO.addGiaoDich(i);
            }
            //update soluong mat hang vao bang mat hang theo CN
            setSoLuongMatHang(listItems);

            JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            modelHoaDon.setRowCount(0);
            setDSHoaDon(hoaDonDAO.getAll());
        } else {
            khachHang = new KhachHang();
            khachHang.setMaKH(Integer.valueOf(maKHTF.getText().trim()));
            khachHang.setMaCN(getNhanVien().getMaCN());
            khachHang.setTenKH(tenKHTF.getText().trim());
            khachHang.setDiaChi(diaChiTF.getText().trim());
            khachHang.setPhone(phoneTF.getText().trim());
            khachHangDAO.addKhachHang(khachHang);
            hoaDon.setMaKH(Integer.valueOf(maKHTF.getText().trim()));
            hoaDonDAO.addHoaDon(hoaDon);
            for (Items i : listItems) {
                i.setMaHD(hoaDon.getMaHD());
                giaoDichDAO.addGiaoDich(i);
            }
            setSoLuongMatHang(listItems);
            JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            modelHoaDon.setRowCount(0);
            setDSHoaDon(hoaDonDAO.getAll());
            modelKhachHang.setRowCount(0);
            setDSKhachHang(khachHangDAO.getKhachHangByCN(this.getNhanVien().getMaCN()));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        int row = matHangTable.getSelectedRow();
        int maMH = 0;
        if (row != -1) {
            try {
                maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
            } catch (Exception e) {
                System.err.println("Loi dinh dang");
            }
            if (row > 0) {
                MatHang matHang = matHangService.getMatHang(maMH);
                maMHLabel.setText("Mã mặt hàng : " + matHang.getMaMH());
                tenMHLabel.setText("Tên mặt hàng : " + matHang.getTenMH());
                danhMucLabel.setText("Danh mục : " + danhMucDAO.getDanhMuc(matHang.getMaDM()).getTenDM());
                hangLabel.setText("Hãng : " + matHang.getHangMH());
                donGiaLabel.setText("Đơn giá : " + matHang.getGiaMH());
                manHinhLabel.setText("Màn hình : " + matHang.getChiTiet().getManHinh());
                hdhLabel.setText("Hệ điều hành : " + matHang.getChiTiet().getHeDieuHanh());
                cameraLable.setText("Camera : " + matHang.getChiTiet().getCamera());
                cpuLabel.setText("CPU : " + matHang.getChiTiet().getCpu());
                ramLabel.setText("RAM : " + matHang.getChiTiet().getRam());
                pinLabel.setText("PIN : " + matHang.getChiTiet().getPin());
                setPicture(imgLabel, matHang.getImg());
            }
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        modelMatHang.setRowCount(0);
        setDSMatHang(matHangService.getALL());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int row = hoaDonTable.getSelectedRow();
        int maHD = 0;
        if (row != -1) {
            maHD = Integer.valueOf(String.valueOf(hoaDonTable.getValueAt(row, 0)));
            HoaDon hoaDon = hoaDonDAO.getHoaDon(maHD);
            KhachHang khachHang = khachHangDAO.getKhachHang(hoaDon.getMaKH());
            maKHTF.setText(String.valueOf(khachHang.getMaKH()));
            tenKHTF.setText(khachHang.getTenKH());
            diaChiTF.setText(khachHang.getDiaChi());
            phoneTF.setText(khachHang.getPhone());
            totalLabel.setText("Tổng tiền : " + String.valueOf(hoaDon.getTotal()));
            listItems = giaoDichDAO.getMatHangByHoaDon(maHD);
            modelItems.setRowCount(0);
            setDSItems(listItems);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int maKH = 0;
        try {
            maKH = Integer.valueOf(maKHTF.getText());
        } catch (Exception e) {
            System.err.println("Lỗi định dạng mã hóa đơn");
            JOptionPane.showMessageDialog(this, "Sai định dạng mã hóa đơn.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        if (khachHangDAO.checkMaKH(maKH)) {
            KhachHang khachHang = khachHangDAO.getKhachHang(maKH);
            tenKHTF.setText(khachHang.getTenKH());
            diaChiTF.setText(khachHang.getDiaChi());
            phoneTF.setText(khachHang.getPhone());
        } else {
            JOptionPane.showMessageDialog(this, "Không có thông tin khách hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel cameraLable;
    private javax.swing.JLabel cn3Label;
    private javax.swing.JLabel cnLabel;
    private javax.swing.JLabel cpuLabel;
    private javax.swing.JLabel danhMucLabel;
    private javax.swing.JTextField diaChiTF;
    private javax.swing.JLabel donGiaLabel;
    private javax.swing.JLabel hangLabel;
    private javax.swing.JLabel hdhLabel;
    private javax.swing.JTable hoaDonTable;
    private javax.swing.JLabel imgLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable khachHangTable2;
    private javax.swing.JTable listItemsTable;
    private javax.swing.JTextField maKHTF;
    private javax.swing.JLabel maMHLabel;
    private javax.swing.JTextField maMHTF;
    private javax.swing.JLabel maNV3Label;
    private javax.swing.JLabel maNVLabel;
    private javax.swing.JLabel manHinhLabel;
    private javax.swing.JTable matHangTable;
    private javax.swing.JPopupMenu menuHoaDon;
    private javax.swing.JPopupMenu menuItems;
    private javax.swing.JPopupMenu menuKhachHang;
    private javax.swing.JPopupMenu menuMatHang;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JLabel pinLabel;
    private javax.swing.JLabel ramLabel;
    private javax.swing.JTextField tenKHTF;
    private javax.swing.JLabel tenMHLabel;
    private javax.swing.JLabel tenNV3Label;
    private javax.swing.JLabel tenNVLabel;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
