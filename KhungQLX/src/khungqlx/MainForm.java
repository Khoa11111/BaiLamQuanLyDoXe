/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khungqlx;

//test  import MyDataBase.All_Select_SQL;
import MyDataBase.JDBCConnection;
import java.awt.CardLayout;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import quanlydoxe.DangNhap;

/**
 *
 * @author admin
 */
public class MainForm extends javax.swing.JFrame {
//test  All_Select_SQL data = new All_Select_SQL();
    Connection conn = null;
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDoXe;user=sa;password=12345";
    /*
    <<<<<<< HEAD
    
=======
    >>>>>>> TaiKhoan_QuanLy
    */
    ResultSet rs;
    int q, i;
    
    /**
     * Creates new form MainForm
     */
//<<<<<<< HEAD
    public void GetNhanVienTable(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select * from NhanVien";
            String Find = txtTimtheomaNV_17.getText();
            if(Find.length()>0)
                sql = sql + " where maNV like '%" + Find + "%'";
            Statement pstm = conn.createStatement();
            rs = pstm.executeQuery(sql);
            
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tableNhanVien_17.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString(1));
                    columnData.add(rs.getString(2));
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                    columnData.add(rs.getString(5));
                    columnData.add(rs.getString(6));
                    columnData.add(rs.getString(7));
                }
                model.addRow(columnData);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetVevaXeTable(int i){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select * from QuanLyXe where ngayRaBai is null ";
            String FindB = txtTktheoBien.getText();
            String FindV = txtTKtheoVe.getText();
            if(i == 1)
                sql = sql + " and bienSoXe like '%" + FindB + "%'";
            if(i == 2)
                sql = sql + " and maVe like '%" + FindV + "%'";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            q = stData.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) TableBaiDoXe.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString(1));
                    columnData.add(rs.getString(2));
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                    columnData.add(rs.getString(5));
                    columnData.add(rs.getString(6));
                    columnData.add(rs.getString(7));
                }
                model.addRow(columnData);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetVeTable(int i){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select * from QuanLyVe";
            if(i == 1)
                sql = sql + " where maVe in (select maVe from QuanLyXe where ngayRaBai is null)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            q = stData.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) TableQuanLyVe_57.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString(1));
                    columnData.add(rs.getString(2));
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                    columnData.add(rs.getString(5));
                }
                model.addRow(columnData);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GetTongKeVeTable(){
        try {
            int Tong;
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select Sum(soLuong) as soLuong from QuanLyVe";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();
            TongVe_57.setText(rs.getString(1));
            Tong = rs.getInt(1);
            sql = "select count(bienSoXe) as soLuong from QuanLyXe where ngayRaBai is null";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();
            VeChuaPhat_57.setText(""+(Tong-rs.getInt(1)));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetXeTable(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select bienSoXe,loaiXe,tenXe,mauXe from QuanLyXe where ngayRaBai is null order by loaiXe desc";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            q = stData.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) TableXe_57.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString(1));
                    columnData.add(rs.getString(2));
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                }
                model.addRow(columnData);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetXeTable2(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select bienSoXe,loaiXe,tenXe,mauXe,ngayVaoBai,NgayRaBai from QuanLyXe ";
            String Find = tfTimKiemXe_DSDKVT_24.getText();
            if(Find.length()>0)
                sql = sql + "where bienSoXe like '%" + Find + "%' ";
            sql = sql + "order by loaiXe desc";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            q = stData.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbDSDKVeThang_24.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString(1));
                    columnData.add(rs.getString(2));
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                    columnData.add(rs.getString(5));
                    columnData.add(rs.getString(6));
                }
                model.addRow(columnData);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetTKTLuotable2(){
        try {
            int ngay,thang;
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select loaiVe ,count(bienSoXe) as soVe "
                    + "from QuanLyVe as a, QuanLyXe as b " 
                    + "where a.maVe = b.maVe group by loaiVe";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs = pstm.executeQuery();
            rs.next();
            ngay = rs.getInt(2);
            LuotNgay.setText(rs.getString(2));
            rs.next();
            thang = rs.getInt(2);
            LuotThang.setText(rs.getString(2));
            TongLuotXe.setText("" + (ngay+thang)); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetTKLuongTable2(){
        try {
            int ngay,thang;
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select loaiVe ,giaVe*count(bienSoXe) as Luong "
                    + "from QuanLyVe as a, QuanLyXe as b " 
                    + "where a.maVe = b.maVe group by loaiVe,giaVe";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs = pstm.executeQuery();
            rs.next();
            ngay = rs.getInt(2);
            TongNgay.setText(rs.getInt(2)+" VN??");
            rs.next();
            thang = rs.getInt(2);
            TongThang.setText(rs.getInt(2)+" VN??");
            TongThu.setText((ngay+thang)+" VN??"); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetXeTable3(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select bienSoXe,loaiXe,tenXe,mauXe from QuanLyXe ";
            String Find = tfTimKiemXe_HT_24.getText();
            if(Find.length()>0)
                sql = sql + "where bienSoXe like '%" + Find + "%' ";
            sql = sql + "order by loaiXe desc";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            q = stData.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) tbHienTai_24.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString(1));
                    columnData.add(rs.getString(2));
                    columnData.add(rs.getString(3));
                    columnData.add(rs.getString(4));
                }
                model.addRow(columnData);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetVeXeTable(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select loaiXe, count(loaiXe) as soLuong from QuanLyXe where ngayRaBai is null group by loaiXe order by loaiXe desc";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if(rs.next()){
                 TongSoOto.setText(rs.getString(2));
                if(rs.next())
                    TongSoXeMay.setText(rs.getString(2));
                else
                    TongSoXeMay.setText("0");
            }
            else{
                TongSoOto.setText("0");
                TongSoXeMay.setText("0");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GetTKTongXevaVeTable(){
        try {
            int SoXe;
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select count(bienSoXe) as soLuong from QuanLyXe";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();
            TongXe_57.setText(rs.getString(1));
            SoXe = rs.getInt(1);
            sql = "select Sum(soLuong) as soLuong from QuanLyVe";
             pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();
            ViTriTrong_57.setText(""+(rs.getInt(1)-SoXe));
            sql = "select loaiXe, count(loaiXe) as soLuong from QuanLyXe group by loaiXe order by loaiXe desc";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();
            XeOto.setText(rs.getString(2));
            rs.next();
            XeMay.setText(rs.getString(2));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  
    public MainForm() {
        initComponents();
        GetNhanVienTable();
// test        jLabel3.setText(String.valueOf(jTable14.getRowCount()));
/*=======
    
    public MainForm() {
        initComponents();
        
//>>>>>>> TaiKhoan_QuanLy*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnQuanLy = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        BKColor = new javax.swing.JPanel();
        MainP = new javax.swing.JPanel();
        TaiKhoan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableNhanVien_17 = new javax.swing.JTable();
        txtTimtheomaNV_17 = new javax.swing.JTextField();
        btnTimtheoma_17 = new javax.swing.JButton();
        btnThem_17 = new javax.swing.JButton();
        btnNV_Xoa_17 = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHienDS = new javax.swing.JButton();
        txtLoaiNV = new javax.swing.JTextField();
        QuanLy = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        QuanLyP = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        txtBienSo = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        txtMaSoVe = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        txtLoaiXe = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        txtTenXe = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        txtMauXe = new javax.swing.JTextField();
        txtNgayVaoBen = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TableBaiDoXe = new javax.swing.JTable();
        txtTKtheoVe = new javax.swing.JTextField();
        btnTktheoVe = new javax.swing.JButton();
        btnve_xe_Xoa = new javax.swing.JButton();
        btnThemQuanLy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTktheoBien = new javax.swing.JTextField();
        btnTKtheoBien = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnHienDS2 = new javax.swing.JButton();
        RaBen_57 = new javax.swing.JButton();
        jPanel49 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TableQuanLyVe_57 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TongVe_57 = new javax.swing.JLabel();
        VeChuaPhat_57 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        TableXe_57 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TongSoOto = new javax.swing.JLabel();
        TongSoXeMay = new javax.swing.JLabel();
        ThongKe = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        ThongKeP = new javax.swing.JPanel();
        DSXe_57 = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbDSDKVeThang_24 = new javax.swing.JTable();
        tfTimKiemXe_DSDKVT_24 = new javax.swing.JTextField();
        btTimKiemXe_DSDKVT_24 = new javax.swing.JButton();
        jButton79 = new javax.swing.JButton();
        LuotNgay = new javax.swing.JLabel();
        LuotThang = new javax.swing.JLabel();
        TongLuotXe = new javax.swing.JLabel();
        TongThang = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        TongThu = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        TongNgay = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        HienTai_57 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbHienTai_24 = new javax.swing.JTable();
        tfTimKiemXe_HT_24 = new javax.swing.JTextField();
        btTimKiemXe_HT_24 = new javax.swing.JButton();
        ViTriTrong_57 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        TongXe_57 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        XeOto = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        XeMay = new javax.swing.JLabel();
        TroGiup = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        Thoat_57 = new javax.swing.JButton();
        DangXuat_57 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnQuanLy.setText("Qua??n Ly??");
        btnQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyActionPerformed(evt);
            }
        });

        jButton3.setText("Ta??i Khoa??n");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Ti???n ??ch");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Th????ng k??");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(btnQuanLy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        BKColor.setBackground(new java.awt.Color(255, 102, 0));

        MainP.setLayout(new java.awt.CardLayout());

        TaiKhoan.setBackground(new java.awt.Color(255, 102, 0));

        jPanel4.setBackground(new java.awt.Color(255, 102, 0));

        jButton6.setText("Danh Sa??ch Nh??n Vi??n");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );

        jPanel5.setLayout(new java.awt.CardLayout());

        jLabel70.setText("Ma?? Nh??n Vi??n");

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel71.setText("T??n Nh??n Vi??n");

        jLabel72.setText("Nga??y Sinh");

        jLabel73.setText("Gi????i ti??nh");

        jLabel74.setText("Loa??i Nh??n Vi??n");

        jLabel76.setText("S???? ??i????n thoa??i");

        jLabel77.setText("M????t Kh????u");

        tableNhanVien_17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? NV", "t??n NV", "Ng??y Sinh", "Gi???i T??nh", "Lo???i NV", "SDT", "M???t Kh???u"
            }
        ));
        tableNhanVien_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhanVien_17MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tableNhanVien_17);

        btnTimtheoma_17.setText("Ti??m theo ma??");
        btnTimtheoma_17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimtheoma_17ActionPerformed(evt);
            }
        });

        btnThem_17.setText("Th??m");
        btnThem_17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_17ActionPerformed(evt);
            }
        });

        btnNV_Xoa_17.setText("Xo??a");
        btnNV_Xoa_17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNV_Xoa_17ActionPerformed(evt);
            }
        });

        btnEdit.setText("Thay ??????i th??ng tin");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHienDS.setText("Hi???n Danh S??ch");
        btnHienDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienDSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimtheomaNV_17, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimtheoma_17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnThem_17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNV_Xoa_17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnHienDS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(168, 168, 168))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel72)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGioiTinh)
                            .addComponent(txtNgaySinh)
                            .addComponent(txtMaNV)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDT)
                    .addComponent(txtMatKhau)
                    .addComponent(txtLoaiNV, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                .addGap(90, 90, 90))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel74)
                                .addComponent(txtLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel70)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimtheomaNV_17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimtheoma_17)
                    .addComponent(btnThem_17)
                    .addComponent(btnNV_Xoa_17)
                    .addComponent(btnEdit)
                    .addComponent(btnHienDS))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(jPanel43, "card3");

        javax.swing.GroupLayout TaiKhoanLayout = new javax.swing.GroupLayout(TaiKhoan);
        TaiKhoan.setLayout(TaiKhoanLayout);
        TaiKhoanLayout.setHorizontalGroup(
            TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaiKhoanLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TaiKhoanLayout.setVerticalGroup(
            TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainP.add(TaiKhoan, "TaiKhoan");

        QuanLy.setBackground(new java.awt.Color(255, 102, 0));

        jPanel7.setBackground(new java.awt.Color(255, 102, 0));

        jButton13.setText("Qua??n Ly?? Xe");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Qua??n Ly?? Ve??");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Qua??n Ly??  Ba??i Xe");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        QuanLyP.setLayout(new java.awt.CardLayout());

        jLabel100.setText("Bi????n S????");

        jLabel101.setText("Ma?? S???? Ve??");

        jLabel102.setText("Loa??i Xe");

        jLabel103.setText("T??n Xe");

        jLabel104.setText("Ma??u xe");

        jLabel105.setText("Ng??y V??o B???n");

        TableBaiDoXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bi???n S???", "M?? S??? V??", "Lo???i Xe", "t??n Xe", "M??u Xe", "Ng??y V??o b???n", "Ng??y Ra B???n"
            }
        ));
        TableBaiDoXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableBaiDoXeMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(TableBaiDoXe);

        btnTktheoVe.setText("Ti??m Ki????m");
        btnTktheoVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTktheoVeActionPerformed(evt);
            }
        });

        btnve_xe_Xoa.setText("Xo??a");
        btnve_xe_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnve_xe_XoaActionPerformed(evt);
            }
        });

        btnThemQuanLy.setText("Th??m");
        btnThemQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemQuanLyActionPerformed(evt);
            }
        });

        jLabel1.setText("Ti??m Ki????m Theo Ve??");

        btnTKtheoBien.setText("Ti??m Ki????m");
        btnTKtheoBien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKtheoBienActionPerformed(evt);
            }
        });

        jLabel2.setText("Ti??m Ki????m Theo Bi????n");

        btnHienDS2.setText("Hi???n Danh S??ch");
        btnHienDS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienDS2ActionPerformed(evt);
            }
        });

        RaBen_57.setText("Ra B???n");
        RaBen_57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RaBen_57ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                                        .addComponent(jLabel100)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtBienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                                        .addComponent(jLabel101)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaSoVe, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel2)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(txtTktheoBien, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTKtheoBien)))
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel44Layout.createSequentialGroup()
                                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel104)
                                            .addComponent(jLabel105)
                                            .addComponent(RaBen_57))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgayVaoBen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMauXe, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel1)))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(txtTKtheoVe, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTktheoVe)
                                .addGap(22, 22, 22)
                                .addComponent(btnThemQuanLy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnve_xe_Xoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(btnHienDS2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addGap(28, 28, 28)
                                .addComponent(txtTenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel102)
                                .addGap(28, 28, 28)
                                .addComponent(txtLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(txtBienSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104)
                    .addComponent(txtMauXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(txtMaSoVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105)
                    .addComponent(txtNgayVaoBen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RaBen_57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel102)))
                .addGap(18, 18, 18)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnve_xe_Xoa)
                        .addComponent(txtTKtheoVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTktheoVe)
                        .addComponent(btnThemQuanLy)
                        .addComponent(btnHienDS2))
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTktheoBien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTKtheoBien)))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        QuanLyP.add(jPanel44, "QLBaiXe");

        TableQuanLyVe_57.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "M?? V??", "Lo???i V??", "Gi?? v??", "Tr???ng Th??i V??", "S??? l?????ng"
            }
        ));
        jScrollPane14.setViewportView(TableQuanLyVe_57);

        jButton9.setText("Danh Sa??ch T????ng S???? Ve??");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton57.setText("Danh Sa??ch Ve?? ??ang Du??ng");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Th??ng tin v??"));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("S???? ve?? ch??a pha??t ra : ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("T????ng s???? ve?? :");

        TongVe_57.setText("jLabel3");

        VeChuaPhat_57.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TongVe_57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VeChuaPhat_57)
                .addGap(86, 86, 86))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(TongVe_57)
                    .addComponent(VeChuaPhat_57))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        QuanLyP.add(jPanel49, "QLVe");

        TableXe_57.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Bi??n S???", "Lo???i Xe", "T??n Xe", "M??u"
            }
        ));
        jScrollPane20.setViewportView(TableXe_57);

        jLabel13.setText("Ba??i Xe Oto");

        jLabel15.setText("T????ng s???? ch???? ?????? :");

        jLabel17.setText("Ba??i Xe Ma??y");

        jLabel18.setText("T????ng s???? ch???? ?????? :");

        TongSoOto.setText("jLabel3");

        TongSoXeMay.setText("jLabel4");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addGroup(jPanel51Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel51Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jLabel13))
                        .addGroup(jPanel51Layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TongSoOto)
                    .addComponent(TongSoXeMay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(TongSoOto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TongSoXeMay))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        QuanLyP.add(jPanel51, "QLXe");

        javax.swing.GroupLayout QuanLyLayout = new javax.swing.GroupLayout(QuanLy);
        QuanLy.setLayout(QuanLyLayout);
        QuanLyLayout.setHorizontalGroup(
            QuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuanLyLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QuanLyP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        QuanLyLayout.setVerticalGroup(
            QuanLyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(QuanLyP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainP.add(QuanLy, "QuanLy");

        ThongKe.setBackground(new java.awt.Color(255, 102, 0));

        jPanel10.setBackground(new java.awt.Color(255, 102, 0));

        jButton22.setText("Hi????n Ta??i");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("Danh sa??ch xe ????ng ky?? ve?? tha??ng");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );

        ThongKeP.setLayout(new java.awt.CardLayout());

        jLabel157.setText("T????ng s???? l??????t xe ??a?? g????i :");

        jLabel158.setText("S???? l??????t xe g????i theo l??????t :");

        jLabel159.setText("S???? l??????t xe g????i theo tha??ng :");

        tbDSDKVeThang_24.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Bi???n s??? xe", "Lo???i xe", "T??n xe", "M??u xe", "ng??y V??o B??i", "Ng??y Ra B??i"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(tbDSDKVeThang_24);

        btTimKiemXe_DSDKVT_24.setText("Ti??m Ki????m Xe");
        btTimKiemXe_DSDKVT_24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemXe_DSDKVT_24ActionPerformed(evt);
            }
        });

        jButton79.setText("??????t la??i");
        jButton79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton79ActionPerformed(evt);
            }
        });

        LuotNgay.setText(" ");

        LuotThang.setText(" ");

        TongLuotXe.setText(" ");

        TongThang.setText(" ");

        jLabel193.setText("S???? ti????n ??a?? thu ????????c t???? ve?? l??????t :");

        TongThu.setText(" ");

        jLabel195.setText("S???? ti????n ??a?? thu ????????c t???? ve?? tha??ng :");

        TongNgay.setText(" ");

        jLabel197.setText("T????ng ti????n ??a?? thu ????????c :");

        javax.swing.GroupLayout DSXe_57Layout = new javax.swing.GroupLayout(DSXe_57);
        DSXe_57.setLayout(DSXe_57Layout);
        DSXe_57Layout.setHorizontalGroup(
            DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
            .addGroup(DSXe_57Layout.createSequentialGroup()
                .addGroup(DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DSXe_57Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel159)
                            .addComponent(jLabel158)
                            .addComponent(jLabel157)
                            .addComponent(jLabel195)
                            .addComponent(jLabel193)
                            .addComponent(jLabel197))
                        .addGap(41, 41, 41)
                        .addGroup(DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(LuotThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LuotNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TongLuotXe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TongThu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TongThang, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                            .addComponent(TongNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120)
                        .addComponent(jButton79, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DSXe_57Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tfTimKiemXe_DSDKVT_24, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btTimKiemXe_DSDKVT_24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DSXe_57Layout.setVerticalGroup(
            DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DSXe_57Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DSXe_57Layout.createSequentialGroup()
                        .addComponent(jLabel157)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel158)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel159))
                    .addGroup(DSXe_57Layout.createSequentialGroup()
                        .addComponent(TongLuotXe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LuotNgay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LuotThang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DSXe_57Layout.createSequentialGroup()
                        .addComponent(jLabel197)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel193)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel195))
                    .addGroup(DSXe_57Layout.createSequentialGroup()
                        .addComponent(TongThu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TongNgay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TongThang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DSXe_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimKiemXe_DSDKVT_24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTimKiemXe_DSDKVT_24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton79, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ThongKeP.add(DSXe_57, "DSXe");

        tbHienTai_24.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Bi???n s??? xe", "Lo???i xe", "T??n xe", "M??u Xe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(tbHienTai_24);

        btTimKiemXe_HT_24.setText("Ti??m Ki????m Xe");
        btTimKiemXe_HT_24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemXe_HT_24ActionPerformed(evt);
            }
        });

        ViTriTrong_57.setText(" ");

        jLabel160.setText("T????ng s???? xe :");

        jLabel161.setText("T????ng vi?? tri?? tr????ng:");

        TongXe_57.setText(" ");

        jLabel220.setText("Xe ?? t?? :");

        XeOto.setText(" ");

        jLabel225.setText("Xe ma??y");

        XeMay.setText(" ");

        javax.swing.GroupLayout HienTai_57Layout = new javax.swing.GroupLayout(HienTai_57);
        HienTai_57.setLayout(HienTai_57Layout);
        HienTai_57Layout.setHorizontalGroup(
            HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17)
            .addGroup(HienTai_57Layout.createSequentialGroup()
                .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, HienTai_57Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel161)
                            .addComponent(jLabel160))
                        .addGap(20, 20, 20)
                        .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TongXe_57, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(ViTriTrong_57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jLabel220)
                        .addGap(49, 49, 49)
                        .addComponent(XeOto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, HienTai_57Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tfTimKiemXe_HT_24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btTimKiemXe_HT_24, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 105, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addComponent(jLabel225)
                .addGap(71, 71, 71)
                .addComponent(XeMay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        HienTai_57Layout.setVerticalGroup(
            HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HienTai_57Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HienTai_57Layout.createSequentialGroup()
                                .addComponent(jLabel160)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel161))
                            .addGroup(HienTai_57Layout.createSequentialGroup()
                                .addComponent(TongXe_57)
                                .addGap(18, 18, 18)
                                .addComponent(ViTriTrong_57)))
                        .addGroup(HienTai_57Layout.createSequentialGroup()
                            .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel220)
                                .addComponent(XeOto))
                            .addGap(32, 32, 32)))
                    .addComponent(jLabel225)
                    .addComponent(XeMay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(HienTai_57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTimKiemXe_HT_24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTimKiemXe_HT_24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ThongKeP.add(HienTai_57, "HienTai");

        javax.swing.GroupLayout ThongKeLayout = new javax.swing.GroupLayout(ThongKe);
        ThongKe.setLayout(ThongKeLayout);
        ThongKeLayout.setHorizontalGroup(
            ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ThongKeP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ThongKeLayout.setVerticalGroup(
            ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ThongKeP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainP.add(ThongKe, "ThongKe");

        TroGiup.setBackground(new java.awt.Color(255, 102, 0));

        jPanel13.setBackground(new java.awt.Color(255, 102, 0));

        Thoat_57.setText("Tho??t");
        Thoat_57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Thoat_57ActionPerformed(evt);
            }
        });

        DangXuat_57.setText("????ng Xu???t");
        DangXuat_57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DangXuat_57ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DangXuat_57, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
            .addComponent(Thoat_57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DangXuat_57, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Thoat_57, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        jPanel14.setLayout(new java.awt.CardLayout());

        jPanel60.setBackground(new java.awt.Color(204, 255, 255));

        jLabel181.setFont(new java.awt.Font("Times New Roman", 1, 21)); // NOI18N
        jLabel181.setText("Th??nh Vi??n Nh??m :");

        jLabel182.setFont(new java.awt.Font("Times New Roman", 1, 21)); // NOI18N
        jLabel182.setText("Nh??m :");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Great Guy");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Tr???n ????nh Vi???t");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("?????ng H???i Long");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Nguy???n V??n Khoa");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Nguy???n H???ng Sinh");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Tr???n Qu???c C?????ng");

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel182)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel181)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(190, 190, 190))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel182)
                    .addComponent(jLabel181))
                .addGap(18, 18, 18)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel60, "card3");

        javax.swing.GroupLayout TroGiupLayout = new javax.swing.GroupLayout(TroGiup);
        TroGiup.setLayout(TroGiupLayout);
        TroGiupLayout.setHorizontalGroup(
            TroGiupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TroGiupLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TroGiupLayout.setVerticalGroup(
            TroGiupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainP.add(TroGiup, "TroGiup");

        javax.swing.GroupLayout BKColorLayout = new javax.swing.GroupLayout(BKColor);
        BKColor.setLayout(BKColorLayout);
        BKColorLayout.setHorizontalGroup(
            BKColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        BKColorLayout.setVerticalGroup(
            BKColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BKColorLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(MainP, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BKColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BKColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void MainPLayoutChange(int i){
            CardLayout cardLayout = (CardLayout) MainP.getLayout();  
            switch(i){
            case 1:{ 
                cardLayout.first(MainP);
                GetNhanVienTable();
            }break;
            case 2:{
                cardLayout.show(MainP,"QuanLy");
                GetVevaXeTable(0);
            }break;
            case 3:{
                cardLayout.show(MainP,"ThongKe");
                GetXeTable2();
                GetTKTLuotable2();
                GetTKLuongTable2();
            }break;
            default:{
                cardLayout.show(MainP,"TroGiup");
            }break;
            }
    }
    void QuanLyPLayoutChange(int i){
            CardLayout cardLayout = (CardLayout) QuanLyP.getLayout();  
            switch(i){
            case 1: cardLayout.first(QuanLyP);break;
            case 2: cardLayout.show(QuanLyP,"QLVe");break;
            default: cardLayout.show(QuanLyP,"QLXe");break;
            }
    }
    void ThongKePLayoutChange(int i){
            CardLayout cardLayout = (CardLayout) ThongKeP.getLayout();  
            switch(i){
            case 1: cardLayout.first(ThongKeP);break;
            default: cardLayout.show(ThongKeP,"HienTai");break;
            }
    }
    
    private void btnQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyActionPerformed
        // TODO add your handling code here:
        MainPLayoutChange(2);
    }//GEN-LAST:event_btnQuanLyActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        MainPLayoutChange(1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        MainPLayoutChange(4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        MainPLayoutChange(3);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnve_xe_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnve_xe_XoaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TableBaiDoXe.getModel();
        int selectedRowIndex = TableBaiDoXe.getSelectedRow();
        try {
            // TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "delete from QuanLyXe where bienSoXe = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,model.getValueAt(selectedRowIndex, 0).toString());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ban da xoa thanh cong");
//test            data.GetVevaXeTable(TableBaiDoXe);
        } catch (ClassNotFoundException ex){
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnve_xe_XoaActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        QuanLyPLayoutChange(1);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        QuanLyPLayoutChange(2);
        GetTongKeVeTable();
        GetVeTable(0);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        QuanLyPLayoutChange(3);
        GetXeTable();
        GetVeXeTable();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        ThongKePLayoutChange(1);
        GetXeTable2();
        GetTKTLuotable2();
        GetTKLuongTable2();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        ThongKePLayoutChange(2);
        GetXeTable3();
        GetTKTongXevaVeTable();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void btnTimtheoma_17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimtheoma_17ActionPerformed
//<<<<<<< HEAD
        
        GetNhanVienTable();
        //try {
            /* TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select * from NhanVien where maNV=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtTimtheomaNV_17.getText());
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String ngaysinh = rs.getString("ngaysinh");
                String gioitinh = rs.getString("gioitinh");
                String loaiNV = rs.getString("loaiNV");
                String diachi = rs.getString("diachi");
                String SDT = rs.getString("SDT");
                String matkhau = rs.getString("makhauNV");
                
                String tbData[] = {maNV,tenNV,ngaysinh,gioitinh,loaiNV,diachi,SDT,matkhau};
                
                DefaultTableModel tblmodel = (DefaultTableModel) tableNhanVien_17.getModel();
                int rowtoremove = tableNhanVien_17.getRowCount();
                for (int i = rowtoremove - 1; i >= 0; i--){
                    tblmodel.removeRow(i);
                }
                tblmodel.addRow(tbData); 
            }
            txtTimtheomaNV_17.setText("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }*/
//=======
//test        data.btnTimtheoma_NV(tableNhanVien_17, txtTimtheomaNV_17);
        txtTimtheomaNV_17.setText("");
//>>>>>>> TaiKhoan_QuanLy
    }//GEN-LAST:event_btnTimtheoma_17ActionPerformed

    private void btnThem_17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_17ActionPerformed
        try {
            // TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "insert into NhanVien(maNV,tenNV,ngaysinh,gioitinh,loaiNV,SDT,matkhauNV)" 
                   + " values (?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtMaNV.getText());
            pstm.setString(2, txtTenNV.getText());
            pstm.setString(3, txtNgaySinh.getText());
            pstm.setString(4, txtGioiTinh.getText());
            pstm.setString(5, txtLoaiNV.getText());
            pstm.setString(6, txtSDT.getText());
            pstm.setString(7, txtMatKhau.getText());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ban da them moi thanh cong");
//test            data.GetNhanVienTable(tableNhanVien_17);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThem_17ActionPerformed

    private void tableNhanVien_17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhanVien_17MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableNhanVien_17.getModel();
        int selectedRowIndex = tableNhanVien_17.getSelectedRow();
        txtMaNV.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txtTenNV.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txtNgaySinh.setText(model.getValueAt(selectedRowIndex, 2).toString());
        txtGioiTinh.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txtLoaiNV.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txtSDT.setText(model.getValueAt(selectedRowIndex, 5).toString());
        txtMatKhau.setText(model.getValueAt(selectedRowIndex, 6).toString());
    }//GEN-LAST:event_tableNhanVien_17MouseClicked

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnNV_Xoa_17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNV_Xoa_17ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableNhanVien_17.getModel();
        int selectedRowIndex = tableNhanVien_17.getSelectedRow();
        try {
            // TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "delete from NhanVien where maNV = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,model.getValueAt(selectedRowIndex, 0).toString());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ban da xoa thanh cong");
           // GetNhanVienTable();
        } catch (ClassNotFoundException ex){
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNV_Xoa_17ActionPerformed

    private void TableBaiDoXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableBaiDoXeMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TableBaiDoXe.getModel();
        int selectedRowIndex = TableBaiDoXe.getSelectedRow();
        txtBienSo.setText(model.getValueAt(selectedRowIndex, 0).toString());
        txtMaSoVe.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txtLoaiXe.setText(model.getValueAt(selectedRowIndex, 2).toString());
        txtTenXe.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txtMauXe.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txtNgayVaoBen.setText(model.getValueAt(selectedRowIndex, 5).toString());
    }//GEN-LAST:event_TableBaiDoXeMouseClicked

    private void btnHienDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienDSActionPerformed
        // TODO add your handling code here:
        txtTimtheomaNV_17.setText("");
        GetNhanVienTable();
//test        data.GetNhanVienTable(tableNhanVien_17);
    }//GEN-LAST:event_btnHienDSActionPerformed

    private void btnHienDS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienDS2ActionPerformed
        // TODO add your handling code here:
        txtTktheoBien.setText("");
        txtTKtheoVe.setText("");
        GetVevaXeTable(0);
//test        data.GetVevaXeTable(TableBaiDoXe);
    }//GEN-LAST:event_btnHienDS2ActionPerformed

    private void btnTktheoVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTktheoVeActionPerformed
        // TODO add your handling code here:
        GetVevaXeTable(2);
        /*try {
            // TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select * from QuanLyVe_va_Xe ";
            String Find_57 = txtTimtheomaNV_17.getText();
            if (Find_57.replaceAll(" ", "") != "") 
                  sql = sql + "where masove=?" + Find_57;
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtTKtheoVe.getText());
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String Bienso = rs.getString("bienso");
                String Masoxe = rs.getString("masove");
                String Loaixe = rs.getString("loaixe");
                String Tenxe = rs.getString("tenxe");
                String Mauxe = rs.getString("mauxe");
                String Ngayraben = rs.getString("ngayraben");
                String Ngayveben = rs.getString("nayvaoben");
                
                String tbData[] = {Bienso,Masoxe,Loaixe,Tenxe,Mauxe,Ngayraben,Ngayveben};
                
                DefaultTableModel tblmodel = (DefaultTableModel) TableBaiDoXe.getModel();
                int rowtoremove = TableBaiDoXe.getRowCount();
                for (int i = rowtoremove - 1; i >= 0; i--){
                    tblmodel.removeRow(i);
                }
                tblmodel.addRow(tbData);
            }
            txtTKtheoVe.setText("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnTktheoVeActionPerformed

    private void btnTKtheoBienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKtheoBienActionPerformed
        // TODO add your handling code here:
        GetVevaXeTable(1);
    /*     try {
            // TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "select * from QuanLyVe_va_Xe where bienso=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtTktheoBien.getText());
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String Bienso = rs.getString("bienso");
                String Masoxe = rs.getString("masove");
                String Loaixe = rs.getString("loaixe");
                String Tenxe = rs.getString("tenxe");
                String Mauxe = rs.getString("mauxe");
                String Ngayraben = rs.getString("ngayraben");
                String Ngayveben = rs.getString("nayvaoben");
                
                String tbData[] = {Bienso,Masoxe,Loaixe,Tenxe,Mauxe,Ngayraben,Ngayveben};
                
                DefaultTableModel tblmodel = (DefaultTableModel) TableBaiDoXe.getModel();
                int rowtoremove = TableBaiDoXe.getRowCount();
                for (int i = rowtoremove - 1; i >= 0; i--){
                    tblmodel.removeRow(i);
                }
                tblmodel.addRow(tbData);
            }
            txtTktheoBien.setText("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
    }//GEN-LAST:event_btnTKtheoBienActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "update NhanVien set tenNV=? , ngaysinh=? , gioitinh=? , loaiNV=? ,SDT=? , matkhauNV=? where maNV=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtTenNV.getText());
            pstm.setString(2, txtNgaySinh.getText());
            pstm.setString(3, txtGioiTinh.getText());
            pstm.setString(4, txtLoaiNV.getText());
            pstm.setString(5, txtSDT.getText());
            pstm.setString(6, txtMatKhau.getText());
            pstm.setString(7, txtMaNV.getText());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ban da thay doi thanh cong");
//test            data.GetNhanVienTable(tableNhanVien_17);
        }  catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnThemQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemQuanLyActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "insert into QuanLyXe" 
                   + " values (?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, txtBienSo.getText());
            pstm.setString(2, txtMaSoVe.getText());
            pstm.setString(3, txtLoaiXe.getText());
            pstm.setString(4, txtTenXe.getText());
            pstm.setString(5, txtMauXe.getText());
            pstm.setString(6, txtNgayVaoBen.getText());
            pstm.setString(7, "null");
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ban da them moi thanh cong");
//test            data.GetVevaXeTable(TableBaiDoXe);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemQuanLyActionPerformed

    private void btTimKiemXe_DSDKVT_24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemXe_DSDKVT_24ActionPerformed
        GetXeTable2();
        /*
        try {
            // TODO add your handling code here:
            Connection connection_24 = JDBCConnection.getJDBCConnect();
            String sql_24 = "select QuanLyXe.* from QuanLyBaiXe, QuanLyVe, QuanLyXe where QuanLyBaiXe.bienSoXe = QuanLyXe.bienSoXe and QuanLyBaiXe.maVe = QuanLyVe.maVe and loaiVe = 'Ve thang' and QuanLyXe.bienSoXe = ?";
            PreparedStatement pstm = connection_24.prepareStatement(sql_24);
            pstm.setString(1, tfTimKiemXe_DSDKVT_24.getText());
            ResultSet rs_24 = pstm.executeQuery();
            
            while(rs_24.next()){
                String bienSoXe_24 = rs_24.getString("bienSoXe");
                String loaiXe_24 = rs_24.getString("loaiXe");
                String tenXe_24 = rs_24.getString("tenXe");
                String mauXe_24 = rs_24.getString("mauXe");
                
                String tbData[] = {bienSoXe_24,loaiXe_24,tenXe_24,mauXe_24};
                
                DefaultTableModel tblmodel = (DefaultTableModel) tbDSDKVeThang_24.getModel();
                int rowtoremove = tbDSDKVeThang_24.getRowCount();
                for (int i = rowtoremove - 1; i >= 0; i--){
                    tblmodel.removeRow(i);
                }
                tblmodel.addRow(tbData);
            }
            tfTimKiemXe_DSDKVT_24.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
            
    }//GEN-LAST:event_btTimKiemXe_DSDKVT_24ActionPerformed

    private void btTimKiemXe_HT_24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemXe_HT_24ActionPerformed
        GetXeTable3();
        /*try {
            // TODO add your handling code here:
            Connection connection_24 = JDBCConnection.getJDBCConnect();
            String sql_24 = "select x.* from QuanLyVe as v right join QuanLyBaiXe as bx on v.maVe = bx.maVe right join QuanLyXe as x on bx.bienSoXe = x.bienSoXe where bx.ngayRaBai is NULL and x.bienSoXe = ? group by x.bienSoXe";
            PreparedStatement pstm = connection_24.prepareStatement(sql_24);
            pstm.setString(1, tfTimKiemXe_HT_24.getText());
            ResultSet rs_24 = pstm.executeQuery();
            
            while(rs_24.next()){
                String bienSoXe_24 = rs_24.getString("bienSoXe");
                String loaiXe_24 = rs_24.getString("loaiXe");
                String tenXe_24 = rs_24.getString("tenXe");
                String mauXe_24 = rs_24.getString("mauXe");
                
                String tbData[] = {bienSoXe_24,loaiXe_24,tenXe_24,mauXe_24};
                
                DefaultTableModel tblmodel = (DefaultTableModel) tbHienTai_24.getModel();
                int rowtoremove = tbHienTai_24.getRowCount();
                for (int i = rowtoremove - 1; i >= 0; i--){
                    tblmodel.removeRow(i);
                }
                tblmodel.addRow(tbData);
            }
            tfTimKiemXe_HT_24.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btTimKiemXe_HT_24ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        GetVeTable(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        // TODO add your handling code here:
        GetVeTable(1);
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton79ActionPerformed
        // TODO add your handling code here:
        GetXeTable2();
    }//GEN-LAST:event_jButton79ActionPerformed

    private void RaBen_57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RaBen_57ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TableBaiDoXe.getModel();
        int selectedRowIndex = TableBaiDoXe.getSelectedRow();
        try {
            // TODO add your handling code here:
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String sql = "update QuanLyXe set ngayRaBai = getdate() where bienSoXe = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,model.getValueAt(selectedRowIndex, 0).toString());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ban da Xuat thanh cong");
//test            data.GetVevaXeTable(TableBaiDoXe);
        } catch (ClassNotFoundException ex){
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RaBen_57ActionPerformed

    private void Thoat_57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Thoat_57ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_Thoat_57ActionPerformed

    private void DangXuat_57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DangXuat_57ActionPerformed
        // TODO add your handling code here:
        DangNhap h = new DangNhap();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_DangXuat_57ActionPerformed

    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BKColor;
    private javax.swing.JPanel DSXe_57;
    private javax.swing.JButton DangXuat_57;
    private javax.swing.JPanel HienTai_57;
    private javax.swing.JLabel LuotNgay;
    private javax.swing.JLabel LuotThang;
    private javax.swing.JPanel MainP;
    private javax.swing.JPanel QuanLy;
    private javax.swing.JPanel QuanLyP;
    private javax.swing.JButton RaBen_57;
    private javax.swing.JTable TableBaiDoXe;
    private javax.swing.JTable TableQuanLyVe_57;
    private javax.swing.JTable TableXe_57;
    private javax.swing.JPanel TaiKhoan;
    private javax.swing.JButton Thoat_57;
    private javax.swing.JPanel ThongKe;
    private javax.swing.JPanel ThongKeP;
    private javax.swing.JLabel TongLuotXe;
    private javax.swing.JLabel TongNgay;
    private javax.swing.JLabel TongSoOto;
    private javax.swing.JLabel TongSoXeMay;
    private javax.swing.JLabel TongThang;
    private javax.swing.JLabel TongThu;
    private javax.swing.JLabel TongVe_57;
    private javax.swing.JLabel TongXe_57;
    private javax.swing.JPanel TroGiup;
    private javax.swing.JLabel VeChuaPhat_57;
    private javax.swing.JLabel ViTriTrong_57;
    private javax.swing.JLabel XeMay;
    private javax.swing.JLabel XeOto;
    private javax.swing.JButton btTimKiemXe_DSDKVT_24;
    private javax.swing.JButton btTimKiemXe_HT_24;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHienDS;
    private javax.swing.JButton btnHienDS2;
    private javax.swing.JButton btnNV_Xoa_17;
    private javax.swing.JButton btnQuanLy;
    private javax.swing.JButton btnTKtheoBien;
    private javax.swing.JButton btnThemQuanLy;
    private javax.swing.JButton btnThem_17;
    private javax.swing.JButton btnTimtheoma_17;
    private javax.swing.JButton btnTktheoVe;
    private javax.swing.JButton btnve_xe_Xoa;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tableNhanVien_17;
    private javax.swing.JTable tbDSDKVeThang_24;
    private javax.swing.JTable tbHienTai_24;
    private javax.swing.JTextField tfTimKiemXe_DSDKVT_24;
    private javax.swing.JTextField tfTimKiemXe_HT_24;
    private javax.swing.JTextField txtBienSo;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtLoaiNV;
    private javax.swing.JTextField txtLoaiXe;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSoVe;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtMauXe;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgayVaoBen;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTKtheoVe;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTenXe;
    private javax.swing.JTextField txtTimtheomaNV_17;
    private javax.swing.JTextField txtTktheoBien;
    // End of variables declaration//GEN-END:variables
}
