/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;


import java.io.IOException; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@RequestScoped
public class Login {
 
private String username;
private String password;
private String dbusername;
private String dbpassword;

public String getUsername() {
 return username;
 }
 
 public void setUsername(String username) {
 this.username = username;
 }
 
 public String getPassword() {
 return password;
 }
 
 public void setPassword(String password) {
 this.password = password;
 }
 
 public String getDBusername() {
 return dbusername;
 }
 
 public void setDBusername(String dbusername) {
 this.dbusername = dbusername;
 }
 
 public String getDBpassword() {
 return dbpassword;
 }
 
 public void setDBpassword(String dbpassword) {
 this.dbpassword = dbpassword;
 }

public void dbData(String username){
    try
    {
        Connection connection=null;
        Koneksi obj_koneksi = new Koneksi();
        connection = obj_koneksi.get_connection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("Select * from login where username='"+username+"'");
        rs.next();
        dbusername = rs.getString(2);
        dbpassword = rs.getString(3);
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
        System.out.println("Exception Occured in the process :" + ex);
    }
}
public void login() {
    
    dbData(username);
    FacesContext context = FacesContext.getCurrentInstance();

    if(this.username.equals(dbusername) && this.password.equals(dbpassword)){
        context.getExternalContext().getSessionMap().put("user", username);
        try {
           context.getExternalContext().redirect("home.xhtml");
           } 
        catch (IOException e) {
           e.printStackTrace();
           }
    }
    else  {
        context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));

    } 
}
 
public void logout() {
    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().invalidateSession();
    try {
       context.getExternalContext().redirect("login.xhtml");
       } catch (IOException e) {
       e.printStackTrace();
       }
}
     
     
 
}
 