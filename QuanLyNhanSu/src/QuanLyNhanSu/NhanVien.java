package QuanLyNhanSu;

import java.util.Date;
import java.util.Scanner;

public class NhanVien {
	protected String maNhanVien;
	protected String hoTen;
	protected String gioiTinh;
	protected String ngaySinh;
	protected String soDienThoai;
	protected boolean isDeleted;
	
	static Scanner sc = new Scanner(System.in);
	
	public NhanVien () {
		this.isDeleted = false;
	}

	public NhanVien(String maNhanVien, String hoTen, String gioiTinh, String ngaySinh, String soDienThoai, boolean isDeleted) {
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.isDeleted = false;
	}
	
	
	@Override
	public String toString() {
		return "Ma nhan vien: " + maNhanVien + ", Ho ten: " + hoTen + ", Gioi tinh: " + gioiTinh + ", Ngay sinh: "
				+ ngaySinh + ", So dien thoai: " + soDienThoai;
	}
	
	public void xuatThongTinNhanVien()
	{
		System.out.println(toString());
	}
	
	public void nhapThongTinNhanVien()
	{	
		System.out.println("Moi nhap ma nhan vien: ");
		setMaNhanVien(sc.nextLine());
		System.out.println("Moi nhap ho ten: ");
		setHoTen(sc.nextLine());
		System.out.println("Moi nhap gioi tinh: ");
		setGioiTinh(sc.nextLine());
		System.out.println("Moi nhap ngay sinh: ");
		setNgaySinh(sc.nextLine());
		System.out.println("Moi nhap so dien thoai: ");
		setSoDienThoai(sc.nextLine());
	}
	
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		while(true) {
			if(gioiTinh.equalsIgnoreCase("Nam") || gioiTinh.equalsIgnoreCase("Nu"))
			{
				this.gioiTinh = gioiTinh;
				break;
			}
			else 
			{
				System.out.println("Vui long nhap gioi tinh 'Nam' hoac 'Nu'");
				gioiTinh = sc.nextLine();
			}
		}
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		while(true)
		{
			if(soDienThoai.length() != 10)
			{
				System.out.println("Vui long nhap so dien thoai gom 10 chu so");
				soDienThoai = sc.nextLine();
			}
			else 
			{
				this.soDienThoai = soDienThoai;
				break;
			}
		}
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public double tinhLuong() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
