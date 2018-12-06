/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mno
 */
@ManagedBean
@RequestScoped
public class Penyewa {

    
    private String penyewaId;
    public void setPenyewaId(String penyewaId) {
        this.penyewaId = penyewaId;
    }
    public String getPenyewaId() {
        return penyewaId;
    }

    private String nama;
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNama() {
        return nama;
    }
    
    private String alamat;
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getAlamat() {
        return alamat;
    }
   
    private String notlp;
    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }
    public String getNotlp() {
        return notlp;
    }
    
    private String noktp;
    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }
    public String getNoktp() {
        return noktp;
    }
    
     private String merk;
    public void setMerk(String merk) {
        this.merk = merk;
    }
    public String getMerk() {
        return merk;
    }
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
  public String Edit_Penyewa(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_penyewaId = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from penyewa where penyewaId="+Field_penyewaId);
          Penyewa obj_penyewa = new Penyewa();
          rs.next();
          obj_penyewa.setPenyewaId(rs.getString("penyewaId"));
          obj_penyewa.setNama(rs.getString("nama"));
          obj_penyewa.setAlamat(rs.getString("alamat"));
          obj_penyewa.setNotlp(rs.getString("notlp"));
          obj_penyewa.setNoktp(rs.getString("noktp"));
          sessionMap.put("EditPenyewa", obj_penyewa);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit_penyewa.xhtml?faces-redirect=true";   
}
  
    public String Detail_Penyewa(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_penyewaId = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from penyewa where penyewaId="+Field_penyewaId);
          Penyewa obj_penyewa = new Penyewa();
          rs.next();
          
          obj_penyewa.setPenyewaId(rs.getString("penyewaId"));
          obj_penyewa.setNama(rs.getString("nama"));
          obj_penyewa.setAlamat(rs.getString("alamat"));
          obj_penyewa.setNotlp(rs.getString("notlp"));
          obj_penyewa.setNoktp(rs.getString("noktp"));
          obj_penyewa.setMerk(rs.getString("merk"));

          sessionMap.put("EditPenyewa", obj_penyewa);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/detail_penyewa.xhtml?faces-redirect=true";   
}
    
   
    
   public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
            
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select mobil.*, penyewa.nama, penyewa.alamat, penyewa.noktp, penyewa.notlp from mobil inner join penyewa on mobil.penyewaId = penyewa.penyewaId where mobil.penyewaId");
            while(rs.next()){
                Home obj_Join = new Home();
                obj_Join.setPenyewaId(rs.getString("penyewaId"));
                obj_Join.setMobilId(rs.getString("mobilId"));
                obj_Join.setTipe(rs.getString("tipe"));
                obj_Join.setMerk(rs.getString("merk"));
                obj_Join.setNoplat(rs.getString("noplat"));
                obj_Join.setWarna(rs.getString("warna"));
                obj_Join.setTahun(rs.getString("tahun"));
                obj_Join.setNomesin(rs.getString("nomesin"));
                obj_Join.setNama(rs.getString("nama"));
                obj_Join.setAlamat(rs.getString("alamat"));
                obj_Join.setNotlp(rs.getString("notlp"));
                obj_Join.setNoktp(rs.getString("noktp"));
                list_of_join.add(obj_Join);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_join;
    }

public String Delete_Penyewa(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_penyewaId = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from penyewa where penyewaId=?");
         ps.setString(1, Field_penyewaId);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/penyewa.xhtml?faces-redirect=true";   
}



public String Update_Penyewa(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_penyewaId = params.get("Update_penyewaId");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update penyewa set nama=?, alamat=?, notlp=?, noktp=? where penyewaId=?");
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, notlp);
            ps.setString(4, noktp);
            ps.setString(5, Update_penyewaId);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/penyewa.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_penyewa() throws Exception{
        ArrayList list_of_penyewa = new ArrayList();
             Connection connection = null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from penyewa");
            while(rs.next()){
                Penyewa obj_penyewa = new Penyewa();
                obj_penyewa.setPenyewaId(rs.getString("penyewaId"));
                obj_penyewa.setNama(rs.getString("nama"));
                obj_penyewa.setAlamat(rs.getString("alamat"));
                obj_penyewa.setNotlp(rs.getString("notlp"));
                obj_penyewa.setNoktp(rs.getString("noktp"));
                list_of_penyewa.add(obj_penyewa);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_penyewa;
}
    


}
