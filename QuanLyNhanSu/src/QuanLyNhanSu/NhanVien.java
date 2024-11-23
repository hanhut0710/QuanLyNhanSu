package QuanLyNhanSu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class NhanVien {
	protected String maNhanVien;
	protected String hoTen;
	protected String gioiTinh;
	protected LocalDate ngaySinh;
	protected String soDienThoai;
	protected boolean isDeleted;
	
	static Scanner sc = new Scanner(System.in);
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public NhanVien () {
		this.isDeleted = false;
	}

	public NhanVien(String maNhanVien, String hoTen, String gioiTinh, LocalDate ngaySinh, String soDienThoai, boolean isDeleted) {
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
				+ ngaySinh.format(formatter) + ", So dien thoai: " + soDienThoai;
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
		System.out.println("Moi nhap ngay sinh (dd/MM/yyyy): ");
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

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinhStr) {
		LocalDate ngaySinhNhap = null;
		try 
		{
			ngaySinhNhap = LocalDate.parse(ngaySinhStr, formatter);
			
			if(ngaySinhNhap.isAfter(LocalDate.now()))
			{
				System.out.println("Khong the nhap ngay sinh la mot ngay o tuong lai. Vui long nhap lai");
				setNgaySinh(sc.nextLine());
			}
			else this.ngaySinh = ngaySinhNhap;
		} catch(DateTimeParseException e) {
			System.out.println("Dinh dang ngay khong hop le. Vui long nhap theo dinh dang (dd/MM/yyyy)");
			setNgaySinh(sc.nextLine());
		}
		
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
