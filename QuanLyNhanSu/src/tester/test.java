package tester;

import java.util.ArrayList;

import QuanLyNhanSu.DanhSachDuAn;
import QuanLyNhanSu.DanhSachNhanVien;
import QuanLyNhanSu.Date;
import QuanLyNhanSu.DuAn;
import QuanLyNhanSu.NhanVien;

public class test {
	
	public static void main(String[] args) {
		DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
		DanhSachDuAn danhSachDuAn = new DanhSachDuAn();
		
		danhSachNhanVien.docFile();
		danhSachDuAn.them();
		danhSachDuAn.themNhanVienVaoDanhSachDuAn(danhSachNhanVien.ds[0]);
		danhSachDuAn.themNhanVienVaoDanhSachDuAn(danhSachNhanVien.ds[1]);

		for(DuAn x : danhSachDuAn.getDanhSachDuAn()) {
			x.getDanhSachNhanVien().hienThiDanhSach();
		}
		
	}
	
	
}
