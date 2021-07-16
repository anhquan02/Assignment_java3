/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mypc
 */
public class Grade {
    String masv,hoTen;
    float tiengAnh,tinHoc,GDTC;

    public Grade() {
    }

    public Grade(String masv,String hoTen, float tiengAnh, float tinHoc, float GDTC) {
        this.masv = masv;
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.GDTC = GDTC;
        this.hoTen=hoTen;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public float getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(float tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public float getTinHoc() {
        return tinHoc;
    }

    public void setTinHoc(float tinHoc) {
        this.tinHoc = tinHoc;
    }

    public float getGDTC() {
        return GDTC;
    }

    public void setGDTC(float GDTC) {
        this.GDTC = GDTC;
    }
    public float getDiemTB(){
        return (tiengAnh+tinHoc+GDTC)/3;
    }
}
