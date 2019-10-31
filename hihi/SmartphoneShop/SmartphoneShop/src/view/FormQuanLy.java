package view;

import controller.DAO.ChiNhanhDAO;
import controller.DAO.Impl.ChiNhanhDAOImpl;
import controller.DAO.Impl.MatHangDAOImpl;
import controller.DAO.Impl.NhanVienDAOImpl;
import controller.DAO.MatHangDAO;
import controller.DAO.NhanhVienDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChiNhanh;
import model.ChiTiet;
import model.MatHang;
import model.NhanVien;

public class FormQuanLy extends javax.swing.JFrame {

    private DefaultTableModel modelTableNV;
    private DefaultTableModel modelTableMH;
    private DefaultTableModel modelTableHD;
    private NhanhVienDAO nhanVienDAO;
    private MatHangDAO matHangDAO;
    private ChiNhanhDAO chiNhanhDAO;

    public FormQuanLy() {
        initComponents();
    }

    public FormQuanLy(NhanVien nhanVien) {
        initComponents();

        nhanVienDAO = new NhanVienDAOImpl();
        matHangDAO = new MatHangDAOImpl();
        chiNhanhDAO = new ChiNhanhDAOImpl();

        nvql1.setText("Tên NVQL : " + nhanVien.getName());
        nvql2.setText("Tên NVQL : " + nhanVien.getName());

        modelTableNV = new DefaultTableModel();
        modelTableHD = new DefaultTableModel();
        modelTableMH = new DefaultTableModel();
        modelTableNV.setColumnIdentifiers(new Object[]{"MaNV", "Tên NV", "Username", "Password", "Địa chỉ", "SĐT", "Chi nhánh", "Chức vụ"});
        modelTableMH.setColumnIdentifiers(new Object[]{"MaMH", "Tên MH", "Hãng", "Giá MH", "Số lượng",});
        modelTableHD.setColumnIdentifiers(new Object[]{});
        nhanVienTable.setModel(modelTableNV);
        matHangTable.setModel(modelTableMH);
        getDSNhanVien(nhanVienDAO.getALL());
        getDSMatHang(matHangDAO.getALL());
        nhanVienTable.setComponentPopupMenu(menuNhanVien);
        matHangTable.setComponentPopupMenu(menuMatHang);

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

    private void setPicture(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\LuongChinh\\Documents\\NetBeansProjects\\SmartphoneShop\\src\\view\\img\\" + filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            int ix = image.getWidth();
            int iy = image.getHeight();
            System.out.println(ix + "  " + x);
            System.out.println(iy + "  " + y);
            int dx = 0;
            int dy = 0;
            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuNhanVien = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        menuMatHang = new javax.swing.JPopupMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
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
        nvql1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        chiNhanhCBB = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        tenHangTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        hangSXTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        giaTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        soLuongTF = new javax.swing.JTextField();
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
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        matHangTable = new javax.swing.JTable();
        nvql2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

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

        jMenu1.setText("Danh sách theo :");

        jMenuItem7.setText("Tất cả");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setText("Chi nhanh Hà Nội");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setText("Chi nhanh HCM");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        menuNhanVien.add(jMenu1);

        jMenuItem13.setText("Xem chi tiết");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem13);

        jMenuItem4.setText("Update");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuMatHang.add(jMenuItem4);

        jMenuItem5.setText("Delete");
        menuMatHang.add(jMenuItem5);

        jMenuItem6.setText("Refresh");
        menuMatHang.add(jMenuItem6);

        jMenu2.setText("Danh sách theo :");
        jMenu2.setToolTipText("");

        jMenuItem10.setText("Tất cả");
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Chi nhánh Hà Nội");
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Chi nhanh HCM");
        jMenu2.add(jMenuItem12);

        menuMatHang.add(jMenu2);

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
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
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
                    .addGroup(chiTietSPLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(0, 126, Short.MAX_VALUE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ManagerShop");

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 500));

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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(jButton2)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel22))
                .addContainerGap(219, Short.MAX_VALUE))
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
        jScrollPane1.setViewportView(nhanVienTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(0, 747, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(nvql1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản lý nhân viên", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("GroupBox1"));

        jLabel10.setText("Chi nhánh:");

        chiNhanhCBB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hà Nội", "Hồ Chí Minh" }));

        jLabel11.setText("Tên hàng:");

        jLabel12.setText("Hãng SX:");

        jLabel13.setText("Đơn giá:");

        jLabel14.setText("Số lượng:");

        jLabel15.setText("Danh mục:");

        danhMucCBBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Smartphone", "Phụ kiện", "Sim" }));

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

        jButton5.setText("Thêm");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chiNhanhCBB, 0, 120, Short.MAX_VALUE)
                    .addComponent(soLuongTF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(giaTF)
                    .addComponent(hangSXTF)
                    .addComponent(tenHangTF))
                .addGap(68, 68, 68)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manHinhTF)
                    .addComponent(danhMucCBBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hdhTF)
                    .addComponent(cameraTF)
                    .addComponent(cpuTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(imgLable, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ramTF)
                            .addComponent(pinTF, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                .addGap(34, 34, 34)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(chiNhanhCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tenHangTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(hangSXTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(giaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(soLuongTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(danhMucCBBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(ramTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(manHinhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(pinTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton5)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(hdhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17))
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(cameraTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(cpuTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 27, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton4)
                                    .addComponent(jButton3))
                                .addGap(26, 26, 26)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("GroupBox2"));

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
        jScrollPane2.setViewportView(matHangTable);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        nvql2.setText("Tên NVQL :");

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
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(nvql2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Quản lý mặt hàng", jPanel2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quản lý hóa đơn", jPanel4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser fileDialog = new JFileChooser();
        int returnValue = fileDialog.showOpenDialog(null);
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
                modelTableNV.setRowCount(0);
                getDSNhanVien(nhanVienDAO.getALL());
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int row = nhanVienTable.getSelectedRow();
        int maNV = Integer.valueOf(String.valueOf(nhanVienTable.getValueAt(row, 0)));
        if (row == -1) {
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
                modelTableNV.setRowCount(0);
                getDSNhanVien(nhanVienDAO.getALL());
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        modelTableNV.setRowCount(0);
        getDSNhanVien(nhanVienDAO.getALL());
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        modelTableNV.setRowCount(0);
        getDSNhanVien(nhanVienDAO.getALL());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        modelTableNV.setRowCount(0);
        getDSNhanVien(nhanVienDAO.getDSNhanVienByCN(1));
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        modelTableNV.setRowCount(0);
        getDSNhanVien(nhanVienDAO.getDSNhanVienByCN(2));
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        int row = matHangTable.getSelectedRow();
        int maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            MatHang matHang = matHangDAO.getMatHang(maMH);
            tenSPLable.setText("Sản phẩm : " + matHang.getTenMH());
            hangLable.setText("Hãng : " + matHang.getHangMH());
            manHinhLable.setText("Màn hình : " + matHang.getChiTiet().getManHinh());
            hdhLable.setText("Hệ điều hành : " + matHang.getChiTiet().getHeDieuHanh());
            cameraLable.setText("Camera : " + matHang.getChiTiet().getCamera());
            cpuLable.setText("CPU : " + matHang.getChiTiet().getCpu());
            ramLable.setText("RAM : " + matHang.getChiTiet().getRam());
            pinLable.setText("Pin : " + matHang.getChiTiet().getPin());
            jLabel23.setSize(120, 140);
            setPicture(jLabel23, matHang.getImg());
            
            chiTietSP.setVisible(true);
        }

    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        int row = matHangTable.getSelectedRow();
        int maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên nào.", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            MatHang matHang = matHangDAO.getMatHang(maMH);
            // matHang.setMa
            if (matHang.getMaCN() == 1) {
                chiNhanhCBB.setSelectedIndex(0);
            } else {
                chiNhanhCBB.setSelectedIndex(1);
            }
            if (matHang.getMaDM() == 1) {
                danhMucCBBox.setSelectedIndex(0);
            } else if (matHang.getMaDM() == 2) {
                danhMucCBBox.setSelectedIndex(1);
            } else {
                danhMucCBBox.setSelectedIndex(3);
            }
            tenHangTF.setText(matHang.getTenMH());
            hangSXTF.setText(matHang.getHangMH());
            giaTF.setText(matHang.getGiaMH());
            soLuongTF.setText(String.valueOf(matHang.getSoLuong()));
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
                modelTableNV.setRowCount(0);
                getDSNhanVien(nhanVienDAO.getALL());
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = matHangTable.getSelectedRow();
        int maMH = Integer.valueOf(String.valueOf(matHangTable.getValueAt(row, 0)));
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn mặt hàng nào!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            MatHang matHang = matHangDAO.getMatHang(maMH);
            String chiNhanh = String.valueOf(chiNhanhCBB.getSelectedItem());
            String ten = tenHangTF.getText().trim();
            String hang = hangSXTF.getText().trim();
            String gia = giaTF.getText().trim();
            int soLuong = Integer.valueOf(soLuongTF.getText().trim());
            String danhMuc = String.valueOf(danhMucCBBox.getSelectedItem());
            String manHinh = manHinhTF.getText().trim();
            String hdh = hdhTF.getText().trim();
            String camera = cameraTF.getText().trim();
            String cpu = cpuTF.getText().trim();
            String ram = ramTF.getText().trim();
            String pin = pinTF.getText().trim();
            int dm = 1, cn = 1;
            if (chiNhanh.equals("Hồ Chí Minh")) {
                cn = 2;
            }
            if (danhMuc.equals("Phụ kiện")) {
                dm = 2;
            } else if (danhMuc.equals("Sim")) {
                dm = 3;
            }
            if (ten.length() == 0 || hang.length() == 0 || soLuong == 0 || gia.length() == 0) {
                JOptionPane.showMessageDialog(this, "Chưa nhập liệu đủ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                matHang.setTenMH(ten);
                matHang.setHangMH(hang);
                matHang.setGiaMH(gia);
                matHang.setSoLuong(soLuong);
                matHang.setMaDM(dm);
                matHang.setMaCN(cn);
                System.out.println(matHang.getMaDM());
                System.out.println(matHang.getMaCN());
                matHang.setChiTiet(new ChiTiet(manHinh, hdh, camera, cpu, ram, pin));
                matHangDAO.editMatHang(matHang);
                JOptionPane.showMessageDialog(this, "Thêm mặt hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
        tenHangTF.setText(null);
        hangSXTF.setText(null);
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
        String chiNhanh = String.valueOf(chiNhanhCBB.getSelectedItem());
        String ten = tenHangTF.getText().trim();
        String hang = hangSXTF.getText().trim();
        String gia = giaTF.getText().trim();
        int soLuong = Integer.valueOf(soLuongTF.getText().trim());
        String danhMuc = String.valueOf(danhMucCBBox.getSelectedItem());
        String manHinh = manHinhTF.getText().trim();
        String hdh = hdhTF.getText().trim();
        String camera = cameraTF.getText().trim();
        String cpu = cpuTF.getText().trim();
        String ram = ramTF.getText().trim();
        String pin = pinTF.getText().trim();
        int dm = 1, cn = 1;
        if (chiNhanh.equals("Hồ Chí Minh")) {
            cn = 2;
        }
        if (danhMuc.equals("Phụ kiện")) {
            dm = 2;
        } else if (danhMuc.equals("Sim")) {
            dm = 3;
        }
        if (ten.length() == 0 || hang.length() == 0 || soLuong == 0 || gia.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa nhập liệu đủ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            MatHang matHang = new MatHang();
            matHang.setTenMH(ten);
            matHang.setHangMH(hang);
            matHang.setGiaMH(gia);
            matHang.setSoLuong(soLuong);
            matHang.setMaDM(dm);
            matHang.setMaCN(cn);
            matHang.setChiTiet(new ChiTiet(manHinh, hdh, camera, cpu, ram, pin));
            matHangDAO.addMatHang(matHang);
            JOptionPane.showMessageDialog(this, "Thêm mặt hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            modelTableMH.setRowCount(0);
            getDSMatHang(matHangDAO.getALL());
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cameraLable;
    private javax.swing.JTextField cameraTF;
    private javax.swing.JComboBox chiNhanhCBB;
    private javax.swing.JComboBox chiNhanhCBBox;
    private javax.swing.JFrame chiTietSP;
    private javax.swing.JComboBox chucVuCBBox;
    private javax.swing.JLabel cpuLable;
    private javax.swing.JTextField cpuTF;
    private javax.swing.JComboBox danhMucCBBox;
    private javax.swing.JTextField diaChiTF;
    private javax.swing.JTextField giaTF;
    private javax.swing.JLabel hangLable;
    private javax.swing.JTextField hangSXTF;
    private javax.swing.JLabel hdhLable;
    private javax.swing.JTextField hdhTF;
    private javax.swing.JLabel imgLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel manHinhLable;
    private javax.swing.JTextField manHinhTF;
    private javax.swing.JTable matHangTable;
    private javax.swing.JPopupMenu menuMatHang;
    private javax.swing.JPopupMenu menuNhanVien;
    private javax.swing.JTextField nameTF;
    private javax.swing.JTable nhanVienTable;
    private javax.swing.JLabel nvql1;
    private javax.swing.JLabel nvql2;
    private javax.swing.JTextField passTF;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JLabel pinLable;
    private javax.swing.JTextField pinTF;
    private javax.swing.JLabel ramLable;
    private javax.swing.JTextField ramTF;
    private javax.swing.JTextField soLuongTF;
    private javax.swing.JTextField tenHangTF;
    private javax.swing.JLabel tenSPLable;
    private javax.swing.JTextField userTF;
    // End of variables declaration//GEN-END:variables
}
