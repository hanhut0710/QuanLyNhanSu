package QuanLyNhanSu;

import java.util.Scanner;

public class Display {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int luaChon = 0;
		DanhSachNhanVien danhSachNhanVien = new DanhSachNhanVien();
		DanhSachDuAn danhSachDuAn = new DanhSachDuAn();
		danhSachNhanVien.docFile();
		danhSachDuAn.docFile(danhSachNhanVien);
		DanhSachPhongBan danhSachPhongBan = FileManager.docFile();
		DanhSachNhanVien.dsnvct = danhSachNhanVien.ds;//Phong Ban
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
				FileManager.ghiFile(danhSachPhongBan);
				break;
			}
			else if(luaChon == 1) {
				while(true) {
					System.out.println("================ MENU ================");
					System.out.println("1. Thêm nhân viên mới");
					System.out.println("2. Sửa nhân viên");
					System.out.println("3. Xóa nhân viên");
					System.out.println("4. Hiển thị danh sách nhân viên");
					System.out.println("5. Tìm kiếm nhân viên");
					System.out.println("6. Thống kê danh sách nhân viên");
					System.out.println("0. Thoát và lưu file");
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
						danhSachNhanVien.timKiem();
					else if(luaChon == 6)
						danhSachNhanVien.thongKe();
					else if(luaChon == 0){
						DanhSachNhanVien.dsnvct = danhSachNhanVien.ds;//Phong ban		
						danhSachNhanVien.ghiFile();		
						System.out.println("========================================");
						System.out.println("             	Thoát!!!!!!                 ");
						System.out.println("========================================");
						break;
					}
					else 
						System.out.println("Vui lòng nhập số từ (1-6) để chọn");
				}
			}
			else if(luaChon == 2) {									
					do {
							System.out.println("================ MENU ================");
							System.out.println("1. Thêm phòng ban");
				            System.out.println("2. Xóa phòng ban");
				            System.out.println("3. Tìm kiếm phòng ban");
				            System.out.println("4. Sửa thông tin phòng ban");
				            System.out.println("5. Hiển thị thông tin phòng ban");	 
				            System.out.println("6. Phục hồi phòng ban");	
				            System.out.println("7. Thống kê phòng ban");
				            System.out.println("8. Tìm kiếm nhân viên");
				            System.out.println("0. Thoát và lưu file");
				            System.out.print("Nhập lựa chọn: ");
				            luaChon = sc.nextInt();
				            sc.nextLine();
				
				            switch(luaChon) {
				            case 1 : 
				            	System.out.println("____________________________________");
				            	System.out.println("Chọn loại phòng ban : ");
				            	System.out.println("1. Phòng IT");
				                System.out.println("2. Phòng kĩ thuật");
				                System.out.println("3. Phòng tài chính");
				                System.out.println("4. Phòng tuyển dụng");
				                System.out.println("____________________________________");
				                int loaiPhong = sc.nextInt();
				                sc.nextLine();
				                PhongBan phongBan = null;
				                if(loaiPhong == 1)
				                	phongBan = new PhongIT();
				                else if(loaiPhong == 2)
				                	phongBan = new PhongKiThuat();
				                else if(loaiPhong == 3)
				                	phongBan = new PhongTaiChinh();
				                else if(loaiPhong == 4)
				                	phongBan = new PhongTuyenDung();                 
				                if(phongBan != null) {
				                	phongBan.nhapThongTinPhongBan(danhSachPhongBan);
				                	danhSachPhongBan.themPhongBan(phongBan);
				                }
				                else System.out.println("Không tồn tại phòng ban đó!!");
				                break;
				            case 2:	
				            	danhSachPhongBan.xoa();
				            	break;
				            case 3:
				            	danhSachPhongBan.timKiem();
				            	break;
				            case 4:
				            	danhSachPhongBan.sua();
				            	break;
				            case 5:
				            	System.out.println("=========Danh sách phòng ban========");
				            	danhSachPhongBan.hienThiDanhSach();
				            	break;
				            case 6:
				            	danhSachPhongBan.phucHoiPhongBan();
				            	break;
				            case 7:
				            	danhSachPhongBan.thongKe();
				            	break;
				            case 8:
				            	danhSachPhongBan.timKiemNV();
				            	break;
				            case 0:
				            	FileManager.ghiFile(danhSachPhongBan);
				            	System.out.println("========================================");
								System.out.println("             	Thoát!!!!!!                 ");
								System.out.println("========================================");
				            	break;
				            default:
				            	System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!!!");
				            }			
					}while(luaChon!=0);										
			}
			
			else if(luaChon == 3) {
				while(true) {
					System.out.println("================ MENU ================");
					System.out.println("1. Thêm dự án mới");
					System.out.println("2. Sửa dự án");
					System.out.println("3. Xóa dự án");
					System.out.println("4. Hiển thị danh sách dự án");
					System.out.println("5. Tìm kiếm dự án");
					System.out.println("6. Thống kê dự án");
					System.out.println("7. Thêm nhân viên vào dự án");
					System.out.println("8. Xóa nhân viên khỏi dự án");
					System.out.println("9. Dữ liệu dự án");
					System.out.println("0. Thoát và lưu file.");
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
						danhSachDuAn.history();
					else if(luaChon == 0) {
						danhSachDuAn.ghiFile();
						System.out.println("========================================");
						System.out.println("             	Thoát!!!!!!                 ");
						System.out.println("========================================");
						break;
					}
					else 
						System.out.println("Vui lòng nhập số từ (1-10) để chọn");
					
				}
			}
			
		}
	}
}
