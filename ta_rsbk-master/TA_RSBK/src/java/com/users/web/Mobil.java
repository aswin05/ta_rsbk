/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author mno
 */
@ManagedBean
@RequestScoped
public class Mobil {

    
    private String mobilId;
    public void setMobilId(String mobilId) {
        this.mobilId = mobilId;
    }
    public String getmobilId() {
        return mobilId;
    }

    private String tipe;
    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    public String getTipe() {
        return tipe;
    }
    
    private String merk;
    public void setMerk(String merk) {
        this.merk = merk;
    }
    public String getMerk() {
        return merk;
    }
   
    private String noplat;
    public void setNoplat(String noplat) {
        this.noplat = noplat;
    }
    public String getNoplat() {
        return noplat;
    }
    
    private String warna;
    public void setWarna(String warna) {
        this.warna = warna;
    }
    public String getWarna() {
        return warna;
    }
    
    private String tahun;
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
    public String getTahun() {
        return tahun;
    }
    
    private String nomesin;
    public void setNomesin(String nomesin) {
        this.nomesin = nomesin;
    }
    public String getNomesin() {
        return nomesin;
    }
    
    private String foto;
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getFoto() {
        return foto;
    }
    
    private String penyewaId;
    public void setPenyewaId(String penyewaId) {
        this.penyewaId = penyewaId;
    }
    public String getPenyewaId() {
        return penyewaId;
    }
    private Part uploadedFile;
    private String folder = "D:/kuliah (DESKTOP-JOR976L)/akademik/prak/rsbl/TA_RSBK";

    public Part getUploadedFile() {
    return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
    this.uploadedFile = uploadedFile;
    }
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
  public String Edit_Mobil(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_mobilId = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from mobil where mobilId="+Field_mobilId);
          Mobil obj_mobil = new Mobil();
          rs.next();
          obj_mobil.setMobilId(rs.getString("mobilId"));
          obj_mobil.setTipe(rs.getString("tipe"));
          obj_mobil.setMerk(rs.getString("merk"));
          obj_mobil.setNoplat(rs.getString("noplat"));
          obj_mobil.setWarna(rs.getString("warna"));
          obj_mobil.setTahun(rs.getString("tahun"));
          obj_mobil.setNomesin(rs.getString("nomesin"));
          obj_mobil.setFoto(rs.getString("foto"));
          obj_mobil.setPenyewaId(rs.getString("penyewaId"));
          sessionMap.put("EditMobil", obj_mobil);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit_mobil.xhtml?faces-redirect=true";   
}
  public String Detail_Mobil(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_mobilId = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from mobil where mobilId="+Field_mobilId);
          Mobil obj_mobil = new Mobil();
          rs.next();
          obj_mobil.setMobilId(rs.getString("mobilId"));
          obj_mobil.setTipe(rs.getString("tipe"));
          obj_mobil.setMerk(rs.getString("merk"));
          obj_mobil.setNoplat(rs.getString("noplat"));
          obj_mobil.setWarna(rs.getString("warna"));
          obj_mobil.setTahun(rs.getString("tahun"));
          obj_mobil.setNomesin(rs.getString("nomesin"));
          obj_mobil.setFoto(rs.getString("foto"));
          sessionMap.put("EditMobil", obj_mobil);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/detail_mobil.xhtml?faces-redirect=true";   
}

public String Delete_Mobil(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_mobilId = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from mobil where mobilId=?");
         ps.setString(1, Field_mobilId);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/mobil.xhtml?faces-redirect=true";   
}

public String Update_Mobil(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_mobilId= params.get("Update_mobilId");
        try{
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            if(uploadedFile == null){
                PreparedStatement ps = connection.prepareStatement("update mobil set tipe=?, merk=?, noplat=?, warna=?, tahun=?, nomesin=? where mobilId=?");
                ps.setString(1, tipe);
                ps.setString(2, merk);
                ps.setString(3, noplat.toUpperCase());
                ps.setString(4, warna);
                ps.setString(5, tahun);
                ps.setString(6, nomesin.toUpperCase());
                ps.setString(7, Update_mobilId);
                System.out.println(ps);
                ps.executeUpdate();
            }else{
                try (InputStream input = uploadedFile.getInputStream()){
                    Random rand = new Random();
                    int n = rand.nextInt(500)+rand.nextInt(9999);
                    String fileName = Integer.toString(n)+".jpg";
                    String fileNamedb = "/resources/images"+"/"+Integer.toString(n)+".jpg";
                    Files.copy(input, new File(folder, fileName).toPath());
                    PreparedStatement ps = connection.prepareStatement("update mobil set tipe=?, merk=?, noplat=?, warna=?, tahun=?, nomesin=?, foto=? where mobilId=?");
                    ps.setString(1, tipe);
                    ps.setString(2, merk);
                    ps.setString(3, noplat.toUpperCase());
                    ps.setString(4, warna);
                    ps.setString(5, tahun);
                    ps.setString(6, nomesin.toUpperCase());
                    ps.setString(7, fileNamedb);
                    ps.setString(8, Update_mobilId);
                    System.out.println(ps);
                    ps.executeUpdate();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/mobil.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_mobil() throws Exception{
        ArrayList list_of_mobil=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from mobil");
            while(rs.next()){
                Mobil obj_mobil = new Mobil();
                obj_mobil.setMobilId(rs.getString("mobilId"));
                obj_mobil.setTipe(rs.getString("tipe"));
                obj_mobil.setMerk(rs.getString("merk"));
                obj_mobil.setNoplat(rs.getString("noplat"));
                obj_mobil.setWarna(rs.getString("warna"));
                obj_mobil.setTahun(rs.getString("tahun"));
                obj_mobil.setNomesin(rs.getString("nomesin"));
                obj_mobil.setFoto(rs.getString("foto"));
                obj_mobil.setPenyewaId(rs.getString("penyewaId"));
                list_of_mobil.add(obj_mobil);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_mobil;
}
    
public String Tambah_Mobil(){
            Connection connection=null;
                    Koneksi obj_koneksi = new Koneksi();
                    connection = obj_koneksi.get_connection();
        try {
            if(uploadedFile != null){
                try(InputStream input = uploadedFile.getInputStream()){
                    Random rand = new Random();
                    int n = rand.nextInt(500)+rand.nextInt(9999);
                    String fileName = Integer.toString(n)+".jpg";
                    String fileNamedb = "/resources/images"+"/"+Integer.toString(n)+".jpg";
                    Files.copy(input, new File(folder, fileName).toPath());
                    
                    PreparedStatement ps=connection.prepareStatement("insert into mobil(tipe, merk, noplat, warna, tahun, nomesin, foto) value('"+tipe+"','"+merk+"','"+noplat.toUpperCase()+"','"+warna+"','"+tahun+"','"+nomesin.toUpperCase()+"','"+fileNamedb+"')");
                    ps.executeUpdate();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                connection = obj_koneksi.get_connection();
                PreparedStatement ps=connection.prepareStatement("insert into mobil(tipe, merk, noplat, warna, tahun, nomesin) value('"+tipe+"','"+merk+"','"+noplat.toUpperCase()+"','"+warna+"','"+tahun+"','"+nomesin.toUpperCase()+"')");
                ps.executeUpdate();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/mobil.xhtml?faces-redirect=true";
    }

}
