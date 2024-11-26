package QuanLyNhanSu;

import java.util.Arrays;
import java.util.Scanner;

public class DanhSachPhongBan {
	private PhongBan[] danhSachPhongBan = new PhongBan[0];

	public void themPhongBan(PhongBan phongBan) {
		danhSachPhongBan = Arrays.copyOf(danhSachPhongBan, danhSachPhongBan.length + 1);
		danhSachPhongBan[danhSachPhongBan.length - 1] = phongBan;
	}

	public void hienThiPhongBan() {
		boolean hienThi = false;
		int soThuTu = 1;

		sapXepPhongBan();

		boolean it = false;
		boolean kt = false;
		boolean tc = false;
		boolean td = false;

		// IT
		for (PhongBan phongBan : danhSachPhongBan) {
			if (!phongBan.isTrangThai()) {
				if (phongBan instanceof PhongIT) {
					if (!it) {
						System.out.println("---------Danh sách phòng IT---------");
						it = true;
					}
					phongBan.xuat(soThuTu);
					soThuTu++;
					hienThi = true;
				}

				// Ki Thuat
				else if (phongBan instanceof PhongKiThuat) {
					if (!kt) {
						System.out.println("------Danh sách phòng Kĩ Thuật------");
						kt = true;
					}
					phongBan.xuat(soThuTu);
					soThuTu++;
					hienThi = true;
				}

				// Tai Chinh
				else if (phongBan instanceof PhongTaiChinh) {
					if (!tc) {
						System.out.println("------Danh sách phòng Tài Chính------");
						tc = true;
					}
					phongBan.xuat(soThuTu);
					soThuTu++;
					hienThi = true;
				}

				// Tuyen Dung
				else if (phongBan instanceof PhongTuyenDung) {
					if (!td) {
						System.out.println("-----Danh sách phòng Tuyển Dụng-----");
						td = true;
					}
					phongBan.xuat(soThuTu);
					soThuTu++;
					hienThi = true;
				}
			}
		}

		if (!hienThi)
			System.out.println("Không có phòng ban nào hiện tại.");

	}

	public void suaPhongBan() {
		boolean timThay = false;
		int luaChon;
		int position = 0;
		Scanner sc = new Scanner(System.in);

		System.out.println("=========Danh sách phòng ban========");
		hienThiPhongBan();

		System.out.println("Nhập mã phòng ban bạn muốn sửa : ");
		String maPhongBan = sc.nextLine();

		for (PhongBan phongBan : danhSachPhongBan) {
			if (!phongBan.isTrangThai()) {
				if (phongBan.getMaPhongBan().equals(maPhongBan)) {
					timThay = true;
					System.out.println("Đã tìm thấy mã phòng ban : " + maPhongBan);
					do {
						System.out.println("--------Chỉnh sửa thông tin phòng ban-----------");
						System.out.println("1. Sửa mã phòng ban");
						System.out.println("2. Sửa tên phòng ban");
						System.out.println("3. Sửa tên quản lí");
						System.out.println("4. Thêm nhân viên vào phòng");
						System.out.println("5. Xóa nhân viên trong phòng");
						System.out.println("0. Thoát");
						System.out.println("Nhập lựa chọn : ");
						luaChon = sc.nextInt();
						switch (luaChon) {
						case 1:
							sc.nextLine();
							System.out.println("Nhập mã phòng ban mới : ");
							phongBan.setMaPhongBan(sc.nextLine());
							break;
						case 2:
							sc.nextLine();
							System.out.println("Nhập tên phòng ban mới : ");
							phongBan.setTenPhongBan(sc.nextLine());
							break;
						case 3:
							sc.nextLine();
							boolean check = false;

							for (int i = 0; i < phongBan.danhSachNhanVien.length; i++)
								if (phongBan.getQuanLi().equals(phongBan.danhSachNhanVien[i]))
									position = i;

							while (!check) {
								boolean found = false;
								System.out.println("Nhập mã quản lí mới : ");
								String quanLi = sc.nextLine();
								for (NhanVien nv : DanhSachNhanVien.dsnvct) {
									if (quanLi.equals(nv.maNhanVien)) {
										found = true;
										if (nv instanceof QuanLi) {
											System.out.println("Đã sửa quản lí cho phòng !");
											check = true;
											phongBan.setQuanLi(quanLi);
											phongBan.danhSachNhanVien[position] = quanLi;
											break;
										} else
											System.out.println("Nhân viên đó không phải là quản lí !!!");
									}
								}
								if (!found) {
									System.out.println("Không tìm thấy mã nhân viên, Vui lòng nhập lại !!!");
								} else if (!check) {
									System.out.println("Vui lòng nhập lại !!!");
								}
							}

							break;

						case 4:
							sc.nextLine();
							boolean Valid = false;
							boolean Exist = false;
							String maNV;
							do {
								 Valid = false;
								System.out.println("Nhập mã nhân viên thêm vào phòng : ");
								maNV = sc.nextLine();
								for (NhanVien nv : DanhSachNhanVien.dsnvct) {
									if (maNV.equals(nv.maNhanVien)) {
										Exist = true;
										if (!checkMaNV(maNV, phongBan)) {
											Valid = true;
											phongBan.danhSachNhanVien = Arrays.copyOf(phongBan.danhSachNhanVien,
													phongBan.danhSachNhanVien.length + 1);
											phongBan.danhSachNhanVien[phongBan.danhSachNhanVien.length - 1] = maNV;
											System.out.println("Đã thêm nhân viên vào phòng !!!");
											break;
										}
									}
								}

								if (!Exist) {
									System.out.println("Không tìm thấy nhân viên trong danh sách công ty!!!");
								} else if (!Valid)
									System.out.println("Nhân viên bị trùng (đã ở trong phòng)");
							} while (!Valid);
							break;
						
						case 5:
							sc.nextLine();
							boolean kiemTra = false;
							do {
								System.out.println("Nhập mã nhân viên muốn xóa : ");
								String maNhanVien = sc.nextLine();
								
								if (checkMaNV2(maNhanVien, phongBan) != -1) {
									for(int i = checkMaNV2(maNhanVien, phongBan);i<phongBan.danhSachNhanVien.length-1;i++)
										phongBan.danhSachNhanVien[i]=phongBan.danhSachNhanVien[i+1];
									phongBan.danhSachNhanVien = Arrays.copyOf(phongBan.danhSachNhanVien,phongBan.danhSachNhanVien.length - 1);
									kiemTra=true;
									System.out.println("Đã xóa nhân viên thành công !!!");
								}
								else {
									System.out.println("Không tìm thấy nhân viên này");
									break;
								
								}
								
//								if (!kiemTra)
//									System.out.println("Không tìm nhân viên trong danh sách !!!");
							} while (!kiemTra);
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
					} while (luaChon != 0);
				}
			}
		}

		if (!timThay)
			System.out.println("Không tìm thấy phòng ban có mã " + maPhongBan + "!!!");
	}

	public void timKiem() {
		int index = 0;
		sapXepPhongBan();
		Scanner sc = new Scanner(System.in);

		System.out.println("____________________________________");
		System.out.println("Nhập mã phòng muốn tìm : ");
		String maPhongBan = sc.nextLine();

		for (PhongBan phongBan : danhSachPhongBan) {
			if (!phongBan.isTrangThai()) {
				index++;
				if (phongBan.getMaPhongBan().equals(maPhongBan)) {
					System.out.println("Đã tìm thấy mã phòng ban : " + maPhongBan);
					System.out.println("-----------Phòng Ban số " + index + "-----------");
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

		for (PhongBan phongBan : danhSachPhongBan) {
			if (!phongBan.isTrangThai()) {
				if (phongBan.getMaPhongBan().equals(maPhongBan)) {
					System.out.println("____________________________________");
					System.out.println("Đã tìm thấy mã phòng ban : " + maPhongBan);
					System.out.println("Đang tiến hành xóa!!!");
					phongBan.setTrangThai(true);
					System.out.println("Phòng ban " + maPhongBan + " đã được xóa.");
					return;
				}
			}
		}

		System.out.println("Không tìm thấy phòng ban có mã " + maPhongBan + " !!!");
	}

	public void phucHoiPhongBan() {
		Scanner sc = new Scanner(System.in);
		boolean check = false;
		// hien thi phong ban an
		System.out.println("===Danh sách các phòng ban bị ẩn===");
		int soThuTu = 1;
		for (PhongBan phongBan : danhSachPhongBan)
			if (phongBan.trangThai) {
				phongBan.xuat(soThuTu);
				soThuTu++;
				check = true;
			}
		if (!check) {
			System.out.println("Hiện không có phòng ban nào bị ẩn đi!!!");
			return;
		}

		System.out.println("Nhập mã phòng ban muốn phục hồi : ");
		String maPhongBan = sc.nextLine();
		for (PhongBan phongBan : danhSachPhongBan) {
			if (phongBan.getMaPhongBan().equals(maPhongBan)) {
				if (phongBan.isTrangThai()) {
					phongBan.setTrangThai(false);
					System.out.println("Phòng Ban " + maPhongBan + " đã được phục hồi.");
					return;
				}
			}
		}
		System.out.println("Không tìm thấy phòng ban có mã: " + maPhongBan);
	}

	public void sapXepPhongBan() {
		for (int i = 0; i < danhSachPhongBan.length - 1; i++)
			for (int j = i + 1; j < danhSachPhongBan.length; j++)
				if (danhSachPhongBan[i].getMaPhongBan().compareTo(danhSachPhongBan[j].getMaPhongBan()) > 0) // doi neu i
																											// > j
				{
					PhongBan temp = danhSachPhongBan[i];
					danhSachPhongBan[i] = danhSachPhongBan[j];
					danhSachPhongBan[j] = temp;
				}
	}

	public void thongKePhongBan() {
		if (danhSachPhongBan.length == 0) {
			System.out.println("Danh sách phòng ban hiện tại đang trống.");
			return;
		}
		int maxSoNhanVien = -1;
		int minSoNhanVien = Integer.MAX_VALUE;

		for (PhongBan pb : danhSachPhongBan) {
			int soNhanVien = pb.danhSachNhanVien.length;
			if (soNhanVien > maxSoNhanVien) {
				maxSoNhanVien = soNhanVien;
			}
			if (soNhanVien < minSoNhanVien) {
				minSoNhanVien = soNhanVien;
			}
		}
		System.out.println("Phòng ban có số lượng nhân viên nhiều nhất (" + maxSoNhanVien + " nhân viên):");
		for (PhongBan pb : danhSachPhongBan) {
			if( pb.danhSachNhanVien.length == maxSoNhanVien) {
				System.out.println("Mã phòng ban: " + pb.getMaPhongBan() + ", Tên phòng ban: " + pb.getTenPhongBan());
			}
		}
		System.out.println("Phòng ban có số lượng nhân viên ít nhất (" + minSoNhanVien + " nhân viên):");
		for (PhongBan pb : danhSachPhongBan) {
			if (pb.danhSachNhanVien.length == minSoNhanVien) {
				System.out.println("Mã phòng ban: " + pb.getMaPhongBan() + ", Tên phòng ban: " + pb.getTenPhongBan());
			}
		}
	}
	
	public void timKiemNV() {
		Scanner sc = new Scanner(System.in);
		System.out.println("____________________________________");
		boolean found = false;
		do {
			System.out.println("Nhập mã NHÂN VIÊN muốn tìm : ");
			String maNhanVien = sc.nextLine();
			for (PhongBan pb : danhSachPhongBan) {
			for(String NhanVien : pb.danhSachNhanVien) {
				if (maNhanVien.equals(NhanVien)) {
					System.out.println("Đã tìm thấy mã nhân viên " + maNhanVien + " tại phòng ban : " + pb.getMaPhongBan()+ ", Tên phòng ban: " + pb.getTenPhongBan());
					found = true;
					break;
					}
				}
				if (found) break;
			}
			 if (!found) {
		            System.out.println("Không tìm thấy mã nhân viên đó. Vui lòng nhập lại!");
		        }
		} while (!found);
	}
	
	public PhongBan[] getDanhSachPhongBan() {
		return danhSachPhongBan;
	}

	public boolean checkMaNV(String maNV, PhongBan phongBan) {
		for (String nv : phongBan.danhSachNhanVien)
			if (maNV.equals(nv))
				return true;
		return false;
	}
	public int checkMaNV2(String maNV, PhongBan phongBan) {
		for (int i = 0;i<phongBan.danhSachNhanVien.length;i++)
			if (maNV.equals(phongBan.danhSachNhanVien[i]))
				return i;
		return -1;
	}

	// run
//	public static void main(String[] args) {
////			DanhSachPhongBan danhSachPhongBan=new DanhSachPhongBan();
//		
//		DanhSachPhongBan danhSachPhongBan = FileManager.docFile();
//
////		Scanner sc = new Scanner(System.in);
//		int luaChon;
//		
//		do {
//				System.out.println("================MENU================");
//			 	System.out.println("1. Thêm phòng ban");
//	            System.out.println("2. Xóa phòng ban");
//	            System.out.println("3. Tìm kiếm phòng ban");
//	            System.out.println("4. Sửa thông tin phòng ban");
//	            System.out.println("5. Hiển thị thông tin phòng ban");	 
//	            System.out.println("6. Phục hồi phòng ban");	
//	            System.out.println("7. Thống kê phòng ban");
//	            System.out.println("8. Tìm kiếm nhân viên");
//	            System.out.println("0. Thoát và lưu file");
//	            System.out.print("Nhập lựa chọn: ");
//	            luaChon = sc.nextInt();
//
//	            switch(luaChon) {
//	            case 1 : 
//	            	System.out.println("____________________________________");
//	            	System.out.println("Chọn loại phòng ban : ");
//	            	System.out.println("1. Phòng IT");
//                    System.out.println("2. Phòng kĩ thuật");
//                    System.out.println("3. Phòng tài chính");
//                    System.out.println("4. Phòng tuyển dụng");
//                    System.out.println("____________________________________");
//                    int loaiPhong = sc.nextInt();
//                 
//                    PhongBan phongBan = null;
//                    if(loaiPhong == 1)
//                    	phongBan = new PhongIT();
//                    else if(loaiPhong == 2)
//                    	phongBan = new PhongKiThuat();
//                    else if(loaiPhong == 3)
//                    	phongBan = new PhongTaiChinh();
//                    else if(loaiPhong == 4)
//                    	phongBan = new PhongTuyenDung();                 
//                    if(phongBan != null) {
//                    	phongBan.nhapThongTinPhongBan();
//                    	danhSachPhongBan.themPhongBan(phongBan);
//                    }
//                    else System.out.println("Không tồn tại phòng ban đó!!");
//                    break;
//	            case 2:	
//	            	danhSachPhongBan.xoa();
//	            	break;
//	            case 3:
//	            	danhSachPhongBan.timKiem();
//	            	break;
//	            case 4:
//	            	danhSachPhongBan.suaPhongBan();
//	            	break;
//	            case 5:
//	            	System.out.println("=========Danh sách phòng ban========");
//	            	danhSachPhongBan.hienThiPhongBan();
//	            	break;
//	            case 6:
//	            	danhSachPhongBan.phucHoiPhongBan();
//	            	break;
//	            case 7:
//	            	danhSachPhongBan.thongKePhongBan();
//	            	break;
//	            case 8:
//	            	danhSachPhongBan.timKiemNV();
//	            	break;
//	            case 0:
//	            	FileManager.ghiFile(danhSachPhongBan);
//	            	System.out.println("========================================");
//					System.out.println("             	Thoát!!!!!!                 ");
//					System.out.println("========================================");
//	            	break;
//	            default:
//	            	System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại!!!");
//	            }
//	            
//		}while(luaChon!=0);	
}
