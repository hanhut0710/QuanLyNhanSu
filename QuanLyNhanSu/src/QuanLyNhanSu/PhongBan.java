package QuanLyNhanSu;

import java.util.Scanner;

public abstract class PhongBan {
	protected String maPhongBan;
	protected String tenPhongBan;
//	protected QuanLi quanLi;
//	protected DanhSachNhanVien danhSachNhanVien;
	protected boolean trangThai = false;
	
	public PhongBan() {
	}
	
	
	
	public String getMaPhongBan() {
		return maPhongBan;
	}



	public void setMaPhongBan(String maPhongBan) {
		this.maPhongBan = maPhongBan;
	}



	public String getTenPhongBan() {
		return tenPhongBan;
	}



	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}

	public abstract void nhapThongTinPhongBan();
	
	public void xuat(int stt) {
		System.out.println("      -----Phòng Ban số "+stt+"-----");
		System.out.println("Mã Phòng Ban : " + maPhongBan);
		System.out.println("Tên Phòng Ban : " + tenPhongBan);
		System.out.println("____________________________________");
	}
	
	public void xuat() {
		System.out.println("Mã Phòng Ban : " + maPhongBan);
		System.out.println("Tên Phòng Ban : " + tenPhongBan);
	}


	public boolean isTrangThai() {
		return trangThai;
	}


	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	
}

