package QuanLyNhanSu;


import java.util.Arrays;
import java.util.Scanner;


public class DanhSachPhongBan {
	private PhongBan[] danhSachPhongBan = new PhongBan[0];


	

	public void themPhongBan(PhongBan phongBan) {
		danhSachPhongBan = Arrays.copyOf(danhSachPhongBan, danhSachPhongBan.length +1);
		danhSachPhongBan[danhSachPhongBan.length -1]= phongBan;
	}
	
	public void hienThiPhongBan() {
		boolean hienThi = false;
			int soThuTu=1;
			
			sapXepPhongBan();
			boolean it = false ;
			boolean kt = false ;
			boolean tc = false;
			boolean td = false ;
			
			//IT
			for(PhongBan phongBan : danhSachPhongBan) {
				if(!phongBan.isTrangThai()) {
					if(phongBan instanceof PhongIT) {
						if(!it) {
							System.out.println("---------Danh sách phòng IT---------");	
							it=true;
						}
				phongBan.xuat(soThuTu);
				soThuTu++;
				hienThi=true;
					}
					
					//Ki Thuat
					else if(phongBan instanceof PhongKiThuat) {
						if(!kt) {
							System.out.println("------Danh sách phòng Kĩ Thuật------");	
							kt=true;
						}
						phongBan.xuat(soThuTu);
						soThuTu++;
						hienThi=true;
					}
					
					//Tai Chinh
					else if(phongBan instanceof PhongTaiChinh) {
							if(!tc) {
								System.out.println("------Danh sách phòng Tài Chính------");	
								tc=true;
							}
					phongBan.xuat(soThuTu);
					soThuTu++;
					hienThi=true;
						}
														
					//Tuyen Dung
					else if(phongBan instanceof PhongTuyenDung) {
						if(!td) {
							System.out.println("-----Danh sách phòng Tuyển Dụng-----");	
							td=true;
							}
						phongBan.xuat(soThuTu);
						soThuTu++;
						hienThi=true;
					}
				} 	
			}			
			
			if(!hienThi)
				System.out.println("Không có phòng ban nào hiện tại.");
				
	}
	
	public void suaPhongBan() {
		boolean timThay = false;
		int luaChon;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=========Danh sách phòng ban========");
		hienThiPhongBan();
		
		System.out.println("Nhập mã phòng ban bạn muốn sửa : ");
		String maPhongBan = sc.nextLine();
		
		for(PhongBan phongBan : danhSachPhongBan) {
			if(!phongBan.isTrangThai()) {
			if(phongBan.getMaPhongBan().equals(maPhongBan)) {
				timThay=true;
				System.out.println("Đã tìm thấy mã phòng ban : "+maPhongBan);
				do { 
					System.out.println("--------Chỉnh sửa thông tin phòng ban-----------");
					System.out.println("1. Sửa mã phòng ban");
					System.out.println("2. Sửa tên phòng ban");
					System.out.println("0. Thoát");
					System.out.println("Nhập lựa chọn : ");
					luaChon = sc.nextInt();
					switch(luaChon) {
					case 1 : 
						sc.nextLine();
						System.out.println("Nhập mã phòng ban mới : ");
						phongBan.setMaPhongBan(sc.nextLine());
						break;
					case 2:
						sc.nextLine();
						System.out.println("Nhập tên phòng ban mới : ");
						phongBan.setTenPhongBan(sc.nextLine());
						break;
					case 0:
						System.out.println("-------------Phòng ban sau khi chỉnh sửa----------");
						phongBan.xuat();
						System.out.println("==================================================");
						System.out.println("                 Đang thoát!!!!!!                 ");
						System.out.println("==================================================");
						break;
					default:
						System.out.println("Lựa chọn không hợp lệ!!!");
					}
				}
				while(luaChon!=0);						
				}
			}
		}
		if(!timThay)
			System.out.println("Không tìm thấy phòng ban có mã "+maPhongBan+"!!!");
	}
	
	public void timKiem() {
		int index = 0 ;
		sapXepPhongBan();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("____________________________________");
		System.out.println("Nhập mã phòng muốn tìm : ");
		String maPhongBan = sc.nextLine();
		
		for(PhongBan phongBan : danhSachPhongBan) {
			if(!phongBan.isTrangThai()) {
			index++;
			if(phongBan.getMaPhongBan().equals(maPhongBan)) {
				System.out.println("Đã tìm thấy mã phòng ban : "+maPhongBan);
				System.out.println("-----------Phòng Ban số "+index+"-----------");
				phongBan.xuat();
				return;
				}		
			}
		}
		System.out.println("Không tìm thấy phòng ban đó !!!");
	}
	
	
	public void xoa() {
		Scanner sc = new Scanner(System.in);

		
		System.out.println("============Danh sách phòng ban hiện tại=============");
		hienThiPhongBan();
		
		System.out.println("Nhập mã phòng muốn xóa : ");
		String maPhongBan = sc.nextLine();
		
		for(PhongBan phongBan : danhSachPhongBan) {
			if(!phongBan.isTrangThai()) {
			if(phongBan.getMaPhongBan().equals(maPhongBan)) {
				System.out.println("____________________________________");
				System.out.println("Đã tìm thấy mã phòng ban : " + maPhongBan);
				System.out.println("Đang tiến hành xóa!!!");
				phongBan.setTrangThai(true);
				System.out.println("Phòng ban "+ maPhongBan + " đã được xóa.");
				return;
				}
			}
		}

			System.out.println("Không tìm thấy phòng ban có mã " + maPhongBan+" !!!");
	}
	


	public void phucHoiPhongBan() {
		Scanner sc = new Scanner(System.in);
		boolean check = false;
		//hien thi phong ban an
		System.out.println("===Danh sách các phòng ban bị ẩn===");
		int soThuTu=1;
		for(PhongBan phongBan : danhSachPhongBan) 
			if(phongBan.trangThai)
			{
				phongBan.xuat(soThuTu);
				soThuTu++;
				check =true;
			}
		if(!check)
		{
			System.out.println("Hiện không có phòng ban nào bị ẩn đi!!!");
			return;
		}
		
		System.out.println("Nhập mã phòng ban muốn phục hồi : ");
		String maPhongBan = sc.nextLine();
		for(PhongBan phongBan : danhSachPhongBan) {
			if(phongBan.getMaPhongBan().equals(maPhongBan)) {
				if(phongBan.isTrangThai()) {
					phongBan.setTrangThai(false);
					System.out.println("Phòng Ban "+maPhongBan+" đã được phục hồi.");
					return;
				}
			}
		}
		System.out.println("Không tìm thấy phòng ban có mã: "+maPhongBan);
	}
	
	public void sapXepPhongBan() {
		for (int i =0 ; i < danhSachPhongBan.length-1;i++)
			for(int j = i+1;j<danhSachPhongBan.length;j++)
				if(danhSachPhongBan[i].getMaPhongBan().compareTo(danhSachPhongBan[j].getMaPhongBan())>0) // doi neu i > j
				{
					PhongBan temp = danhSachPhongBan[i];
					danhSachPhongBan[i] = danhSachPhongBan[j];
					danhSachPhongBan[j]= temp;
				}
	}
	
		
	public PhongBan[] getDanhSachPhongBan() {
		return danhSachPhongBan;
	}
	
	
	//run 
	public static void main(String[] args) {
		DanhSachPhongBan danhSachPhongBan = FileManager.docFile();

		Scanner sc = new Scanner(System.in);
		int luaChon;
		
		do {
				System.out.println("================MENU================");
			 	System.out.println("1. Thêm phòng ban");
	            System.out.println("2. Xóa phòng ban");
	            System.out.println("3. Tìm kiếm phòng ban");
	            System.out.println("4. Sửa thông tin phòng ban");
	            System.out.println("5. Hiển thị thông tin phòng ban");	 
	            System.out.println("6. Phục hồi phòng ban");	
	            System.out.println("0. Thoát và lưu file");
	            System.out.print("Nhập lựa chọn: ");
	            luaChon = sc.nextInt();

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
                    	phongBan.nhapThongTinPhongBan();
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
	            	danhSachPhongBan.suaPhongBan();
	            	break;
	            case 5:
	            	System.out.println("=========Danh sách phòng ban========");
	            	danhSachPhongBan.hienThiPhongBan();
	            	break;
	            case 6:
	            	danhSachPhongBan.phucHoiPhongBan();
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
	
}

	
