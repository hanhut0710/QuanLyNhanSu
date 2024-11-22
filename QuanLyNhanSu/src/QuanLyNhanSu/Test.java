package QuanLyNhanSu;

import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
		DanhSachDuAn danhSachDuAn = new DanhSachDuAn();
		while(true) {
			System.out.println("1.thêm ");
			System.out.println("2. SỬA");
			System.out.println("3.xóa");
			System.out.println("4 hiển thị danh sách");
			System.out.println("5. tìm kiếm");
			System.out.println("6. đọc file");
			System.out.println("7. ghi file");
			System.out.println("lựa chọn : ");
			Scanner sc = new Scanner(System.in);
			int luaChon = sc.nextInt();
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
				danhSachDuAn.docFile();
			else if(luaChon == 7)
				danhSachDuAn.ghiFile();
			else if(luaChon == 8)
				danhSachDuAn.history();
			else 
				continue;
		}
	}
	
}
