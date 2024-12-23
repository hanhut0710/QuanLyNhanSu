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
		while(true)
		{
			if(maNhanVien.matches("[A-Z]{2}\\d{3,}"))
			{
				this.maNhanVien = maNhanVien;
				break;
			}
			else
			{
				System.out.println("Ma nhan vien bao gom 2 ki tu in hoa va 3 chu so. VD 'XX001'.");
				maNhanVien = sc.nextLine();
			}
		}
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		while(true)
		{
			if(hoTen.isEmpty() == true)
			{
				System.out.println("Ho ten khong duoc de trong, vui long nhap lai");
				hoTen = sc.nextLine();
				continue;
			}
			
			 // Kiểm tra tên có chứa số hay ký tự đặc biệt không
	        if (hoTen.matches(".*\\d.*")) {
	            System.out.println("Ho ten khong duoc chua so, vui long nhap lai");
	            hoTen = sc.nextLine();
	            continue;
	        }

	        // Kiểm tra xem tên có chứa ký tự đặc biệt không (chỉ cho phép chữ cái và khoảng trắng)
	        if (!hoTen.matches("[a-zA-Z\\s]+")) {
	            System.out.println("Ho ten khong duoc chua ky tu dac biet, vui long nhap lai");
	            hoTen = sc.nextLine();
	            continue;
	        }
			String words [] = hoTen.split(" ");
			String result ="";
			
			for(String word : words)
				if(word.isEmpty() == false)
					result += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase()+ " ";
			this.hoTen = result.trim();
			break; //break neu hop le
		}
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
			if(soDienThoai.length() == 10	&& soDienThoai.matches("[0-9]+"))
			{
				this.soDienThoai = soDienThoai;
				break;
			}
			else 
			{	
				System.out.println("Vui long nhap so dien thoai gom 10 chu so");
				soDienThoai = sc.nextLine();
			}
		}
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
	
}
