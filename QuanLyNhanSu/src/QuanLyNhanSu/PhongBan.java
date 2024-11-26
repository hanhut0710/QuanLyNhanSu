package QuanLyNhanSu;

//import java.util.Scanner;

public abstract class PhongBan {
	protected String maPhongBan;
	protected String tenPhongBan;
	protected String quanLi;
	protected String[] danhSachNhanVien = new String[0];
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

	public String getQuanLi() {
		return quanLi;
	}

	public void setQuanLi(String quanLi) {
		this.quanLi = quanLi;
	}

	public abstract void nhapThongTinPhongBan();

	public void xuat(int stt) {
		System.out.println("      -----Phòng Ban số " + stt + "-----");
		System.out.println("Mã Phòng Ban : " + maPhongBan);
		System.out.println("Tên Phòng Ban : " + tenPhongBan);
		System.out.println("Quản lí phòng ban : " + quanLi);
		System.out.println("Danh Sách nhân viên : ");
		for (NhanVien nvCT : DanhSachNhanVien.dsnvct)
			for (String nv : danhSachNhanVien) {
				if (nv.equals(nvCT.maNhanVien))
					System.out.println("	- " + nv + " 	: " + nvCT.hoTen);
			}
		System.out.println("____________________________________");
	}

	public void xuat() {
		System.out.println("Mã Phòng Ban : " + maPhongBan);
		System.out.println("Tên Phòng Ban : " + tenPhongBan);
		System.out.println("Quản lí phòng ban : " + quanLi);
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

}
