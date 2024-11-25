package QuanLyNhanSu;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class DuAn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maDuAn;
	private String tenDuAn;
	private Date ngayBatDau;
	private Date duKienKetThuc;
	private boolean trangThaiXoa; // xóa là true, false là chưa xóa
	private DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();

	public DuAn(String maDuAn, String tenDuAn, Date ngayBatDau, Date duKienKetThuc, boolean trangThaiXothí) {
		this.maDuAn = maDuAn;
		this.tenDuAn = tenDuAn;
		this.ngayBatDau = ngayBatDau;
		this.duKienKetThuc = duKienKetThuc;
		this.trangThaiXoa = false;
		this.danhSachNhanVien = new DanhSachNhanVien();
	}
	public DuAn() {
		this.trangThaiXoa = false;
		this.danhSachNhanVien = new DanhSachNhanVien();
	}
	
	
	public void themNhanVienVaoDuAn(NhanVien x) {
		this.danhSachNhanVien.ds = Arrays.copyOf(this.danhSachNhanVien.ds, this.danhSachNhanVien.ds.length + 1);
		this.danhSachNhanVien.ds[this.danhSachNhanVien.ds.length -1] = x;
	}
	
	
	public void nhapThongTinDuAn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----Nhập thông tin dự án----");
		System.out.print("Nhập mã dự án: ");
		this.maDuAn = sc.nextLine();
		System.out.print("Nhập tên dự án: ");
		this.tenDuAn = sc.nextLine();
		boolean success = false;
		do {
			try {
				do {
					System.out.print("Nhập ngày bắt đầu dự án(dd/MM/yyyy): ");
					this.ngayBatDau = new Date(sc.nextLine());
					if(this.ngayBatDau.getDate() == null) {
						System.out.println("Vui lòng nhập lại ngày bắt đầu !!");
					}
				}while(this.ngayBatDau.getDate() == null);
				do {
					System.out.print("Nhập ngày dự kiến hoàn thành dự án(dd/MM/yyyy): ");
					this.duKienKetThuc = new Date(sc.nextLine());
					if(this.duKienKetThuc.getDate() == null) {
						System.out.println("Vui lòng nhập lại ngày dự kiến kết thúc !!");
					}
				}while(this.duKienKetThuc.getDate() == null);
				success = true;
				
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("Vui lòng nhập ngày đúng định dạng(dd/MM/yyyy) !!!");
			}
		}while(!success);
	}
	
	
	public void xuatThongTinDuAn() {
		System.out.println("----Thông tin dự án----");
		System.out.println("Mã dự án: "+this.maDuAn);
		System.out.println("Tên dự án: "+this.tenDuAn);
		System.out.println("Ngày bắt đầu: "+this.ngayBatDau.getDate());
		System.out.println("Ngày dự kiến kết thúc: "+this.duKienKetThuc.getDate());
	}
	public void xuatThongTinDuAn(int stt) {
		System.out.printf("%-5d %-10s %-20s %-15s %-15s \n", stt, this.maDuAn, this.tenDuAn, this.ngayBatDau.getDate(), this.duKienKetThuc.getDate());
	}
	
	public void xuatThongTinDanhSachNhanVien() {
		for(NhanVien x : danhSachNhanVien.ds) {
			System.out.printf("");
		}
	}
	
	public String getMaDuAn() {
		return maDuAn;
	}
	public void setMaDuAn(String maDuAn) {
		this.maDuAn = maDuAn;
	}
	public String getTenDuAn() {
		return tenDuAn;
	}
	public void setTenDuAn(String tenDuAn) {
		this.tenDuAn = tenDuAn;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(String ngayBatDau) {
		boolean success = false;
		do {
			try {
				do {
					this.ngayBatDau = new Date(ngayBatDau);
					if(this.ngayBatDau.getDate() == null) 
						System.out.println("Lỗi: Ngày nhập không hợp lệ !!!");
				} while (this.ngayBatDau.getDate() == null);
				success = true;
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Ngày nhập đúng định dạng(dd/MM/yyyy) !!!");			
			}
		} while (!success);
	}
	public Date getDuKienKetThuc() {
		return duKienKetThuc;
	}
	public void setDuKienKetThuc(String duKienKetThuc) {
		boolean success = false;
		do {
			try {
				do {
					this.duKienKetThuc = new Date(duKienKetThuc);
					if(this.duKienKetThuc.getDate() == null) 
						System.out.println("Lỗi: Ngày nhập không hợp lệ !!!");
				} while (this.duKienKetThuc.getDate() == null);
				success = true;
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Ngày nhập đúng định dạng(dd/MM/yyyy) !!!");			
			}
		} while (!success);
	}
	public boolean isTrangThaiXoa() {
		return trangThaiXoa;
	}
	public void setTrangThaiXoaTrue() {
		this.trangThaiXoa = true;
	}
	public void setTrangThaiXoaFalse() {
		this.trangThaiXoa = false;
	}
	
	
	public DanhSachNhanVien getDanhSachNhanVien() {
		return danhSachNhanVien;
	}
	public void setDanhSachNhanVien(DanhSachNhanVien danhSachNhanVien) {
		this.danhSachNhanVien = danhSachNhanVien;
	}
	
	
	
}