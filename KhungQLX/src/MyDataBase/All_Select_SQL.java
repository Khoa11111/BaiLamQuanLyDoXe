/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import khungqlx.MainForm;

/**
 *
 * @author Administrator
 */
public class All_Select_SQL {
    Connection conn = JDBCConnection.getJDBCConnect();
    ResultSet rs;
    int q, i;
    
    public void GetNhanVienTable(JTable table){
        try {
            String sql = "select NhanVien.maNV,NhanVien.tenNV,NhanVien.ngaysinh,NhanVien.gioitinh,NhanVien.chucVu,DiaChi.QuanHuyen,NhanVien.SDT,TaiKhoan.matKhau" +
                " from NhanVien,TaiKhoan,DiaChi where NhanVien.tenTK=TaiKhoan.tenTK and NhanVien.maNV=DiaChi.maNV";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString("maNV"));
                    columnData.add(rs.getString("tenNV"));
                    columnData.add(rs.getString("ngaysinh"));
                    columnData.add(rs.getString("gioitinh"));
                    columnData.add(rs.getString("chucVu"));
                    columnData.add(rs.getString("QuanHuyen"));
                    columnData.add(rs.getString("SDT"));
                    columnData.add(rs.getString("matKhau"));
                }
                model.addRow(columnData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GetVevaXeTable(JTable table){
        try {
            String sql = "select qlx.bienSoXe,qlv.maVe,qlx.loaiXe,qlx.tenXe,qlx.mauXe,format(qlbx.ngayVaoBai,'dd-MM-yyyy') as ngayRaBai1"
                    + ",format(qlbx.ngayRaBai,'dd-MM-yyyy') as ngayVaoBai1"
                    + " from QuanLyXe as qlx,QuanLyVe as qlv,QuanLyBaiXe as qlbx "
                    + "where qlbx.bienSoXe = qlx.bienSoXe and qlbx.maVe = qlv.maVe";
            PreparedStatement pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector columnData = new Vector();
                for (i=1; i<=q; i++){
                    columnData.add(rs.getString("bienSoXe"));
                    columnData.add(rs.getString("maVe"));
                    columnData.add(rs.getString("loaiXe"));
                    columnData.add(rs.getString("tenXe"));
                    columnData.add(rs.getString("mauXe"));
                    columnData.add(rs.getString("ngayRaBai1"));
                    columnData.add(rs.getString("ngayVaoBai1"));
                }
                model.addRow(columnData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
