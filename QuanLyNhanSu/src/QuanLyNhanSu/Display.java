package QuanLyNhanSu;

import java.util.Scanner;

public class Display {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int luaChon = 0;
		DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
		DanhSachDuAn danhSachDuAn = new DanhSachDuAn();
		DanhSachPhongBan danhSachPhongBan = new DanhSachPhongBan();
		danhSachNhanVien.docFile();
		danhSachDuAn.docFile(danhSachNhanVien);
		while(true) {
			System.out.println("======= Hệ thống quản lí nhân sự ======");
			System.out.println("1. Nhân sự ");
			System.out.println("2. Phòng ban ");
			System.out.println("3. Dự án ");
			System.out.println("0. Thoát");
			System.out.print("Hãy chọn tab muốn vào: ");
			luaChon = Integer.parseInt(sc.nextLine());
			if(luaChon == 0) {
				System.out.println("Thoát chương trình !!");
				danhSachNhanVien.ghiFile();
				danhSachDuAn.ghiFile();
				break;
			}
			else if(luaChon == 1) {
				while(true) {
					System.out.println("========= Menu =========");
					System.out.println("1. Thêm nhân viên mới");
					System.out.println("2. Sửa nhân viên");
					System.out.println("3. Xóa nhân viên");
					System.out.println("4. Hiển thị danh sách nhân viên");
					System.out.println("5. Tìm kiếm nhân viên");
					System.out.println("6. Thống kê danh sách nhân viên");
					System.out.println("0. Thoát");
					System.out.print("Hãy nhập tab tab muốn vào: ");
					luaChon = Integer.parseInt(sc.nextLine());
					if(luaChon == 1)
						danhSachNhanVien.them();
					else if(luaChon == 2)
						danhSachNhanVien.sua();
					else if(luaChon == 3)
						danhSachNhanVien.xoa();
					else if(luaChon == 4)
						danhSachNhanVien.hienThiDanhSach();
					else if(luaChon == 5)
						danhSachDuAn.timKiem();
					else if(luaChon == 6)
						danhSachDuAn.thongKe();
					else if(luaChon == 0)
						break;
					else 
						System.out.println("Vui lòng nhập số từ (1-6) để chọn");
				}
			}
			else if(luaChon == 2) {
				while(true) {
					
				}
				
			}
			else if(luaChon == 3) {
				while(true) {
					System.out.println("========= Menu =========");
					System.out.println("1. Thêm dự án mới");
					System.out.println("2. Sửa dự án");
					System.out.println("3. Xóa dự án");
					System.out.println("4. Hiển thị danh sách dự án");
					System.out.println("5. Tìm kiếm dự án");
					System.out.println("6. Thống kê dự án");
					System.out.println("7. Thêm nhân viên vào dự án");
					System.out.println("8. Xóa nhân viên khỏi dự án");
					System.out.println("9. Lưu dự án hiện có");
					System.out.println("10. Dữ liệu dự án");
					System.out.println("0. Thoát");
					System.out.print("Hãy nhập tab tab muốn vào: ");
					luaChon = Integer.parseInt(sc.nextLine());
					if(luaChon == 1)
						danhSachDuAn.them();
					else if(luaChon==2)
						danhSachDuAn.sua();
					else if(luaChon==3)
						danhSachDuAn.xoa();
					else if(luaChon==4)
						danhSachDuAn.hienThiDanhSach();
					else if(luaChon==5)
						danhSachDuAn.timKiem();
					else if(luaChon == 6)
						danhSachDuAn.thongKe();
					else if(luaChon == 7) 
						danhSachDuAn.themNhanVienVaoDanhSachDuAn(danhSachNhanVien);
					else if(luaChon == 8)
						danhSachDuAn.xoaNhanVienKhoiDanhSach(danhSachNhanVien);
					else if(luaChon == 9)
						danhSachDuAn.ghiFile();
					else if(luaChon == 10)
						danhSachDuAn.history();
					else if(luaChon == 0)
						break;
					else 
						System.out.println("Vui lòng nhập số từ (1-10) để chọn");
					
				}
			}
			
		}
	}
}
