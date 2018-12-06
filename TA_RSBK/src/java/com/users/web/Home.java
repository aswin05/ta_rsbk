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
public class Home{

    
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
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
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
    
public ArrayList getGet_all_mobil() throws Exception{
        ArrayList list_of_mobil=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from mobil where penyewaId IS NULL");
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

    public String Tambah_PeminjamanT(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("update mobil set penyewaId = '"+penyewaId+"' where mobilId = '"+mobilId+"'");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/home.xhtml?faces-redirect=true";
    }
    public String Tambah_PeminjamanBT(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into penyewa values('"+penyewaId+"','"+nama+"', '"+alamat+"', '"+notlp+"', '"+noktp+"')");
            ps.executeUpdate();
            PreparedStatement pss=connection.prepareStatement("update mobil set penyewaId = '"+penyewaId+"' where mobilId = '"+mobilId+"'");
            pss.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/home.xhtml?faces-redirect=true";
    }
    
    public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select mobil.*, penyewa.nama, penyewa.alamat, penyewa.noktp, penyewa.notlp from mobil inner join penyewa on mobil.penyewaId = penyewa.penyewaId");
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
    
    public String Delete_Pinjam(){
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String Field_mobilId = params.get("action");
        try {
           Koneksi obj_koneksi = new Koneksi();
           Connection connection = obj_koneksi.get_connection();
           PreparedStatement ps = connection.prepareStatement("update mobil set penyewaId = NULL  where mobilId = ?");
           ps.setString(1, Field_mobilId);
           System.out.println(ps);
           ps.executeUpdate();
          } catch (Exception e) {
           System.out.println(e);
          }
         return "/data_peminjaman.xhtml?faces-redirect=true";   
    }
    
    public String Penyewa_Pinjam(){
        ArrayList list_of_join=new ArrayList();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
        String Field_penyewaId = params.get("action");
        try {
             Koneksi obj_koneksi = new Koneksi();
             Connection connection = obj_koneksi.get_connection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select * from penyewa where penyewaId="+Field_penyewaId);
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
            sessionMap.put("EditPenyewa", list_of_join);  
         } catch (Exception e) {
               System.out.println(e);
         }
        return "/edit_penyewa.xhtml?faces-redirect=true";   
    }
}
