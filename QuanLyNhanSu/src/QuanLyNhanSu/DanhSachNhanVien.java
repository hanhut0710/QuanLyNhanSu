package QuanLyNhanSu;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class DanhSachNhanVien implements QuanLiDanhSach{
	public static NhanVien[] dsnvct=new NhanVien[0];  // su ly phong ban
	public NhanVien ds [] = new NhanVien [0];
	static Scanner sc = new Scanner(System.in);
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	static void showMenu()
	{	
		System.out.println("===============QUAN LY NHAN VIEN===============");
		System.out.println("1. Nhap danh sach");
		System.out.println("2. Xuat danh sach");
		System.out.println("3. Them nhan vien");
		System.out.println("4. Sua thong tin nhan vien");
		System.out.println("5. Xoa nhan vien");
		System.out.println("6. Tim kiem nhan vien");
		System.out.println("7. Thong ke");
		System.out.println("8. Thoat");
		System.out.println("================================================");
	}
	
	public void docFile()
	{	/*
	 	Bước 1. Khởi tạo file
	 	Bước 2. Kiểm tra đủ 7 trường cơ bản của NhanVien hay không?
	 	Bước 3. Tùy vào trường loaiNhanVien mà khởi tạo đối tượng tương ứng
	 	Bước 4. Gọi các hàm set để cập nhật thông tin 7 trường cơ bản
	 	Bước 5. Kiểm tra nv có là instance của các lớp ? => Set các trường tương ứng
	 	Bước 6. Thêm nv vào mảng
	 	*/
		try 
		{
			FileReader fr = new FileReader("NhanVien.txt");
			BufferedReader br = new BufferedReader(fr);
			String st;
			
			while(true)
			{
				st=br.readLine();
				if(st == null)
					break;
				String s[] = st.split("\t");
				if(s.length >= 7)
				{
					String maNhanVien = s[0].trim();
					String hoTen = s[1].trim();
					String gioiTinh = s[2].trim();
					String ngaySinh = s[3].trim();
					String soDienThoai = s[4].trim();
					boolean isDeleted = Boolean.parseBoolean(s[5].trim());
					String loaiNhanVien = s[6].trim();
					
					if(kiemTraMaNhanVien(maNhanVien))
					{
						System.out.printf("Ma nhan vien %s da ton tai. Bo qua nhan vien \n", maNhanVien);
						continue;
					}
					NhanVien nv = null;
					switch(loaiNhanVien)
					{
						case "NhanVienChinhThuc":
							nv = new NhanVienChinhThuc();
							break;
						case "NhanVienHopDong":
							nv = new NhanVienHopDong();
							break;
						case "ThucTapSinh":
							nv = new ThucTapSinh();
							break;
						case "QuanLi":
							nv = new QuanLi();
							break;
						default:
							nv = new NhanVien();
							break;
					}
					
					/*Cap nhat thong tin cho cac nhan vien*/
					nv.setMaNhanVien(maNhanVien);
					nv.setHoTen(hoTen);
					nv.setGioiTinh(gioiTinh);
					nv.setNgaySinh(ngaySinh);
					nv.setSoDienThoai(soDienThoai);
					nv.setDeleted(isDeleted);
					
					if(nv instanceof NhanVienChinhThuc)
					{
						NhanVienChinhThuc nvChinhThuc = (NhanVienChinhThuc) nv;
						nvChinhThuc.heSoLuongNhanVien = Double.parseDouble(s[7].trim());
						nvChinhThuc.luongNhanVien = Double.parseDouble(s[8].trim());
					}
					else if(nv instanceof NhanVienHopDong)
					{
						NhanVienHopDong nvHopDong = (NhanVienHopDong) nv;
						nvHopDong.ngayBatDau = LocalDate.parse(s[7].trim(), formatter);
						nvHopDong.ngayKetThuc = LocalDate.parse(s[8].trim(), formatter);
						nvHopDong.luongHopDong = Double.parseDouble(s[9].trim());
					}
					else if(nv instanceof ThucTapSinh)
					{
						ThucTapSinh thucTapSinh = (ThucTapSinh) nv;
						thucTapSinh.heSoLuongThucTap = Double.parseDouble(s[7].trim());
						thucTapSinh.tienPhuCap = Double.parseDouble(s[8].trim());	
					}
					else if(nv instanceof QuanLi)
					{
						QuanLi quanLi = (QuanLi) nv;
						quanLi.heSoLuongQuanLi = Double.parseDouble(s[7].trim());
						quanLi.luongQuanLi = Double.parseDouble(s[8].trim());
					}
					ds=Arrays.copyOf(ds, ds.length+1);
					ds[ds.length - 1]= nv;
				}
				else {
					System.out.println("Du lieu khong hop le" +st);
					break;
				}
				
			}
			
			br.close();
			fr.close();
		} catch(Exception e) {	
			System.out.println("Loi khi doc file: " +e.getMessage());
		}
	}
	
	public NhanVien check(String maNhanVien) {
		for(NhanVien x : this.ds) {
			if(maNhanVien.equals(x.getMaNhanVien()))
				return x;
		}
		return null;
	}

	public void ghiFile()
	{
		try {
			FileWriter fw = new FileWriter("NhanVien.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i=0; i < ds.length; i++)
			{
				String st ="";
				st += ds[i].getMaNhanVien() + "\t";
				st += ds[i].getHoTen() + "\t";
				st += ds[i].getGioiTinh() + "\t";
				st += ds[i].getNgaySinh().format(formatter) + "\t";
				st += ds[i].getSoDienThoai() + "\t";
				st += ds[i].isDeleted() + "\t";
				
				if(ds[i] instanceof NhanVienChinhThuc)
				{
					NhanVienChinhThuc nvChinhThuc = (NhanVienChinhThuc) ds[i];
					st += "NhanVienChinhThuc" + "\t" + nvChinhThuc.heSoLuongNhanVien + "\t" + nvChinhThuc.luongNhanVien;
				}
				else if(ds[i] instanceof NhanVienHopDong)
				{
					NhanVienHopDong nvHopDong = (NhanVienHopDong) ds[i];
					st += "NhanVienHopDong" + "\t" + nvHopDong.ngayBatDau.format(formatter) + "\t"
							+ nvHopDong.ngayKetThuc.format(formatter) + "\t" + nvHopDong.luongHopDong;
				}
				else if(ds[i] instanceof ThucTapSinh)
				{
					ThucTapSinh thucTapSinh = (ThucTapSinh) ds[i];
					st += "ThucTapSinh" + "\t" + thucTapSinh.tienPhuCap + "\t" + thucTapSinh.heSoLuongThucTap;
				}
				else if(ds[i] instanceof QuanLi)
				{
					QuanLi quanLi = (QuanLi) ds[i];
					st += "QuanLi" + "\t" + quanLi.heSoLuongQuanLi + "\t" + quanLi.luongQuanLi;
				}
				bw.write(st);
				bw.newLine();
			}
			bw.close();
			fw.close();
			
		} catch(Exception e) {
			System.out.println("Loi ghi file" +e.getMessage());
		}
	}
	
	public boolean kiemTraMaNhanVien(String maNhanVien) 
	{
	    for (NhanVien nv : ds) {
	        if (nv.getMaNhanVien().equals(maNhanVien)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean kiemTraSoDienThoai(String soDienThoai) 
	{
	    for (NhanVien nv : ds) {
	        if (nv.getMaNhanVien().equals(soDienThoai)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	@Override
	public void hienThiDanhSach()
	{	/*Nếu isDeleted = true thì mới xuất ra, false thì không in nữa*/
		System.out.println(ds.length);
		for(int i=0; i < ds.length; i++)
		{	
			if(ds[i].isDeleted() == false)
			{
				ds[i].xuatThongTinNhanVien();
			}
			
		}
	}
	
	public void them()
	{	
		while(true)
		{
			int n=0;
			System.out.println("Moi nhap so luong nhan vien muon them: ");
			
			try {
				n = Integer.parseInt(sc.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Nhap sai, vui long nhap so luong la mot con so");
				continue; //Quay lai vong lap de bat nhap lai
			}
			
			
			int originLength = ds.length;
			
			for(int i = 0; i < n; i++)
			{	
				NhanVien nv;
				System.out.printf("Moi nhap loai nhan vien thu %d\n", i+1);
				System.out.println("1. Quan ly");
				System.out.println("2. Nhan vien chinh thuc");
				System.out.println("3. Nhan vien hop dong");
				System.out.println("4. Thuc tap sinh");
				int choose = 0;
				try 
				{
					choose = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Nhap sai, vui long nhap lua chon la mot con so tu 1-4");
					i--;
					continue;
				}
				switch(choose)
				{
					case 1:
						nv = new QuanLi();
						break;
					case 2:
						nv = new NhanVienChinhThuc();
						break;
					case 3:
						nv = new NhanVienHopDong();
						break;
					default:
						nv = new ThucTapSinh();
						break;
				}

				nv.nhapThongTinNhanVien();
				
				while(true)
				{	
					boolean checkedMaNhanVien = true;
					boolean checkedSoDienThoai = true;
					if(kiemTraMaNhanVien(nv.getMaNhanVien()))
					{
						System.out.println("Ma nhan vien da ton tai. Vui long nhap lai");
						nv.setMaNhanVien(sc.nextLine());
						checkedMaNhanVien = false;
					}
					if(kiemTraSoDienThoai(nv.getSoDienThoai()))
					{
						System.out.println("So dien thoai da ton tai. Vui long nhap lai");
						nv.nhapThongTinNhanVien();
						checkedSoDienThoai = false;
					}
					if(checkedMaNhanVien && checkedSoDienThoai)
						break;
				}
				
				ds = Arrays.copyOf(ds, ds.length+1);
				ds[ds.length-1]= nv;
			} 
			
			if(ds.length > originLength)
			{
				System.out.printf("Them thanh cong %d nhan vien \n", ds.length-originLength);
				ghiFile();
			}
			else System.out.println("Them that bai");
			break;
		}
		
	}
	
	@Override
	public void sua()
	{
		
		System.out.println("Moi nhap ma nhan vien can sua");
		String maNhanVienCanSua = sc.nextLine();
		NhanVien nvCanSua = null;
		
		for(int i=0; i < ds.length; i++)
		{
			if(ds[i].getMaNhanVien().equalsIgnoreCase(maNhanVienCanSua))
			{
				nvCanSua = ds[i];
				break;
			}
		}
		
		if(nvCanSua != null)
		{
			System.out.println("Nhan vien can sua: " +nvCanSua.maNhanVien);
			System.out.println("Moi nhap thong tin muon sua");
			System.out.println("0. Sua toan bo thong tin");
			System.out.println("1. Sua ma nhan vien");
			System.out.println("2. Sua ho ten");
			System.out.println("3. Sua gioi tinh");
			System.out.println("4. Sua ngay sinh");
			System.out.println("5. Sua so dien thoai");
			
			if(nvCanSua instanceof NhanVienChinhThuc)
			{
				System.out.println("6. Sua he so luong");
				System.out.println("7. Sua luong");
			} 
			else if(nvCanSua instanceof NhanVienHopDong)
			{
				System.out.println("6. Sua ngay bat dau hop dong");
				System.out.println("7. Sua ngay ket thuc hop dong");
				System.out.println("8. Sua luong hop dong");
			}
			else if(nvCanSua instanceof ThucTapSinh)
			{
				System.out.println("6. Sua he so luong thuc ta");
				System.out.println("7. Sua tien phu cap");
			}
			else if(nvCanSua instanceof QuanLi)
			{
				System.out.println("6. Sua he so luong");
				System.out.println("7. Sua luong");
			}
			System.out.println("Vui long nhap lua chon");
			
			
			int choose = Integer.parseInt(sc.nextLine());
			switch(choose)
			{
				case 0:
					System.out.println("Nhap lai toan bo thong tin nhan vien");
					nvCanSua.nhapThongTinNhanVien();
					break;
				case 1:
					System.out.println("Moi nhap ma nhan vien moi: ");
					nvCanSua.setMaNhanVien(sc.nextLine());
					break;
				case 2:
					System.out.println("Moi nhap ho ten moi: ");
					nvCanSua.setHoTen(sc.nextLine());
					break;
				case 3:
					System.out.println("Moi nhap gioi tinh(Nam/Nu) moi: ");
					nvCanSua.setGioiTinh(sc.nextLine());
					break;
				case 4:
					System.out.println("Moi nhap ngay sinh moi (dd/MM/yyyy): ");
					nvCanSua.setNgaySinh(sc.nextLine());
					break;
				case 5:
					System.out.println("Moi nhap so dien thoai moi: ");
					nvCanSua.setSoDienThoai(sc.nextLine());
					break;
				
				//Cac truong hop khac tuy thuoc vao loai nhan vien
				case 6:
					if(nvCanSua instanceof NhanVienChinhThuc)
					{
						NhanVienChinhThuc nvChinhThuc = (NhanVienChinhThuc) nvCanSua;
						System.out.println("Moi nhap he so luong moi");
						nvChinhThuc.heSoLuongNhanVien = Integer.parseInt(sc.nextLine());
					}
					else if(nvCanSua instanceof NhanVienHopDong)
					{
						NhanVienHopDong nvHopDong = (NhanVienHopDong) nvCanSua;
						System.out.println("Moi nhap ngay bat dau moi");
						String ngayBatDau = sc.nextLine();
						nvHopDong.ngayBatDau = LocalDate.parse(ngayBatDau, formatter);
					}
					else if(nvCanSua instanceof ThucTapSinh)
					{
						ThucTapSinh thucTapSinh = (ThucTapSinh) nvCanSua;
						System.out.println("Moi nhap he so luong moi");
						thucTapSinh.heSoLuongThucTap = Integer.parseInt(sc.nextLine());
					}
					else if(nvCanSua instanceof QuanLi)
					{
						QuanLi quanLi = (QuanLi) nvCanSua;
						System.out.println("Moi nhap he so luong moi");
						quanLi.heSoLuongQuanLi = Integer.parseInt(sc.nextLine());
					}
					break;
				case 7:
					if(nvCanSua instanceof NhanVienChinhThuc)
					{
						NhanVienChinhThuc nvChinhThuc = (NhanVienChinhThuc) nvCanSua;
						System.out.println("Moi nhap luong moi");
						nvChinhThuc.luongNhanVien = Double.parseDouble(sc.nextLine());
					}
					else if(nvCanSua instanceof NhanVienHopDong)
					{
						NhanVienHopDong nvHopDong = (NhanVienHopDong) nvCanSua;
						System.out.println("Moi nhap ngay ket thuc moi");
						String ngayKetThuc = sc.nextLine();
						nvHopDong.ngayKetThuc = LocalDate.parse(ngayKetThuc, formatter);
					}
					else if(nvCanSua instanceof ThucTapSinh)
					{
						ThucTapSinh thucTapSinh = (ThucTapSinh) nvCanSua;
						System.out.println("Moi tien phu cap moi");
						thucTapSinh.tienPhuCap = Double.parseDouble(sc.nextLine());
					}
					else if(nvCanSua instanceof QuanLi)
					{
						QuanLi quanLi = (QuanLi) nvCanSua;
						System.out.println("Moi nhap luong moi");
						quanLi.luongQuanLi= Double.parseDouble(sc.nextLine());
					}
					break;
				case 8:
					if(nvCanSua instanceof NhanVienHopDong) {
	                    NhanVienHopDong nvHopDong = (NhanVienHopDong) nvCanSua;
	                    System.out.println("Moi nhap luong hop dong moi: ");
	                    nvHopDong.luongHopDong = sc.nextDouble();
	                }
	                break;
	            default:
	            	System.out.println("Lua chon khong hop le, vui long nhap lai");
	            	return;
			}
			ghiFile();
		}
		else System.out.printf("Khong tim nhan nhan vien voi ma %s \n", maNhanVienCanSua);
	}
	
	@Override
	public void xoa()
	{
		System.out.println("Moi nhap ma nhan vien can xoa");
		String maNhanVienCanXoa = sc.nextLine();
		NhanVien nvCanXoa = null;
		
		for(int i=0; i < ds.length; i++)
		{
			if(ds[i].getMaNhanVien().equalsIgnoreCase(maNhanVienCanXoa))
			{
				nvCanXoa = ds[i];
				break;
			}
		}
		
		if(nvCanXoa != null)
		{
			System.out.printf("Ban co chac chan muon xoa nhan vien co ma %s khong? (Co/Khong)\n", maNhanVienCanXoa);
			String confirm = sc.nextLine();
			if(confirm.equalsIgnoreCase("Co"))
			{
				nvCanXoa.setDeleted(true);
				if(nvCanXoa.isDeleted() == true)
				{
					System.out.printf("Xoa thanh cong nhan vien co ma %s \n", maNhanVienCanXoa);
					ghiFile();
				}
				else System.out.println("Loi xay ra, xoa khong thanh cong");
			}
			else System.out.println("Huy xoa nhan vien");
		}
		else System.out.printf("Khong tim thay nhan vien voi ma %s \n", maNhanVienCanXoa);
	}
	
	@Override
	public void timKiem()
	{	/*
	 	Bước 1. Nhập thông tin cần tìm (nhớ cắt bỏ khoảng trắng String.trim() );
	 	Bước 2. Kiểm tra đã nhập ít nhất 1 thông tin ?=> Ko có return
	 	Bước 3. Xử lý ngày sinh try-catch
	 	Bước 4. Khởi tạo boolean found=false. Duyệt vòng lặp kiểm tra
	 			4.1 Kiểm tra
	 			+ Thông tin có rỗng hay không
	 			+ 'thông tin cần tìm' có được tìm thấy hay không
	 			4.2 Kiểm tra 1 trong 5 tiêu chí và isDeleted = false => found=true -> xuất ra.
	 	*/
		/*  Tìm kiếm gần đúng: Tên
			Tìm kiếm chính xác: MãNV, SĐT, Giới tính, Ngày sinh */
		System.out.println("Nhap thong tin nhan vien can tim");
		
		System.out.println("Moi nhap ma nhan vien (de trong neu khong tim theo ma)");
		String maNhanVienTimKiem = sc.nextLine().trim();
		System.out.println("Moi nhap ten nhan vien (de trong neu khong tim theo ten)");
		String tenNhanVienTimKiem = sc.nextLine().trim();
		System.out.println("Moi nhap so dien thoai (de trong neu khong tim theo sdt)");
		String soDienThoaiTimKiem = sc.nextLine().trim();
		System.out.println("Moi nhap gioi tinh (Nam/Nu, de trong neu khong tim theo gioi tinh)");
		String gioiTinhTimKiem = sc.nextLine().trim().toLowerCase();
		System.out.println("Moi nhap ngay sinh (dang dd/MM/yyyy, de trong neu khong tim theo ngay sinh)");
		String ngaySinhTimKiem = sc.nextLine().trim();
		
		
		if(maNhanVienTimKiem.isEmpty() && tenNhanVienTimKiem.isEmpty() && soDienThoaiTimKiem.isEmpty() &&
				gioiTinhTimKiem.isEmpty() && ngaySinhTimKiem.isEmpty())
		{
			System.out.println("Vui long nhap it nhat 1 thong tin tim kiem");
			return;
		}
		
		
		LocalDate ngaySinhNhap = null;
		try 
		{
			if(ngaySinhTimKiem.isEmpty() == false)
				ngaySinhNhap = LocalDate.parse(ngaySinhTimKiem, formatter);
		} catch(DateTimeParseException e)
		{
			System.out.println("Dinh dang khong hop le");
			return;
		}
		
		boolean found= false;
		for(NhanVien nv : ds)
		{
			boolean checkedMa = maNhanVienTimKiem.isEmpty() || nv.getMaNhanVien().equalsIgnoreCase(maNhanVienTimKiem);
			boolean checkedTen = tenNhanVienTimKiem.isEmpty() || nv.getHoTen().toLowerCase().contains(tenNhanVienTimKiem.toLowerCase());
			boolean checkedSDT = soDienThoaiTimKiem.isEmpty() || nv.getSoDienThoai().equalsIgnoreCase(soDienThoaiTimKiem);
			boolean checkedGioiTinh = gioiTinhTimKiem.isEmpty() || nv.getGioiTinh().equalsIgnoreCase(gioiTinhTimKiem);
			boolean checkedNgaySinh = ngaySinhTimKiem.isEmpty() || nv.getNgaySinh().equals(ngaySinhNhap);
			
			//Nếu có bất kỳ tiêu chí nào khớp và QUAN TRỌNG: nhân viên chưa bị xóa
			if((checkedMa && checkedTen && checkedSDT && checkedGioiTinh && checkedNgaySinh) && nv.isDeleted() == false)
			{
				nv.xuatThongTinNhanVien();
				found = true;
			}
		}
		if(found == false)
			System.out.println("Khong tim thay nhan vien nao phu hop voi tieu chi");
	}
	public void thongKeNhanVienNam() {
            int count = 0;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv.getGioiTinh().equalsIgnoreCase("Nam")) {
                    System.out.println(nv.toString());
                    count++;
                }
            }
            System.out.println("So luong nhan vien nam: " + count);
        }

        public void thongKeNhanVienNu() {
            int count = 0;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv.getGioiTinh().equalsIgnoreCase("Nu")) {
                    System.out.println(nv.toString());
                    count++;
                }
            }
            System.out.println("So luong nhan vien nu: " + count);
        }

        public void thongKeLuongCaoNhat() {
            double maxSalary = Double.NEGATIVE_INFINITY;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv instanceof NhanVienChinhThuc) {
                    NhanVienChinhThuc nvChinhThuc = (NhanVienChinhThuc) nv;
                    if (nvChinhThuc.luongNhanVien > maxSalary) {
                        maxSalary = nvChinhThuc.luongNhanVien;
                    }
                } else if (!nv.isDeleted() && nv instanceof NhanVienHopDong) {
                    NhanVienHopDong nvHopDong = (NhanVienHopDong) nv;
                    if (nvHopDong.luongHopDong > maxSalary) {
                        maxSalary = nvHopDong.luongHopDong;
                    }
                }else if (!nv.isDeleted() && nv instanceof QuanLi) {
                    QuanLi nvQuanLi = (QuanLi) nv;
                    if (nvQuanLi.luongQuanLi > maxSalary) {
                        maxSalary = nvQuanLi.luongQuanLi;
                    }
                }
            }
            if (maxSalary == Double.NEGATIVE_INFINITY) {
                System.out.println("Khong co nhan vien nao co luong.");
            } else {
                System.out.println("Luong cao nhat: " + maxSalary + " VND");
            }
        }

        public void thongKeLuongThapNhat() {
            double minSalary = Double.POSITIVE_INFINITY;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv instanceof NhanVienChinhThuc) {
                    NhanVienChinhThuc nvChinhThuc = (NhanVienChinhThuc) nv;
                    if (nvChinhThuc.luongNhanVien < minSalary) {
                        minSalary = nvChinhThuc.luongNhanVien;
                    }
                } else if (!nv.isDeleted() && nv instanceof NhanVienHopDong) {
                    NhanVienHopDong nvHopDong = (NhanVienHopDong) nv;
                    if (nvHopDong.luongHopDong < minSalary) {
                        minSalary = nvHopDong.luongHopDong;
                    }
                } else if (!nv.isDeleted() && nv instanceof QuanLi) {
                    QuanLi nvQuanLi = (QuanLi) nv;
                    if (nvQuanLi.luongQuanLi < minSalary) {
                        minSalary = nvQuanLi.luongQuanLi;
                    }
                }
            }
            if (minSalary == Double.POSITIVE_INFINITY) {
                System.out.println("Khong co nhan vien nao co luong.");
            } else {
                System.out.println("Luong thap nhat: " + minSalary + " VND");
            }
        }

        public void thongKeNhanVienChinhThuc() {
            int count = 0;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv instanceof NhanVienChinhThuc) {
                    System.out.println(nv.toString());
                    count++;
                }
            }
            System.out.println("So luong nhan vien chinh thuc: " + count);
        }

        public void thongKeNhanVienHopDong() {
            int count = 0;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv instanceof NhanVienHopDong) {
                    System.out.println(nv.toString());
                    count++;
                }
            }
            System.out.println("So luong nhan vien hop dong: " + count);
        }

        public void thongKeThucTapSinh() {
            int count = 0;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv instanceof ThucTapSinh) {
                    System.out.println(nv.toString());
                    count++;
                }
            }
            System.out.println("So luong thuc tap sinh: " + count);
        }

        public void thongKeQuanLi() {
            int count = 0;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted() && nv instanceof QuanLi) {
                    System.out.println(nv.toString());
                    count++;
                }
            }
            System.out.println("So luong quan li: " + count);
        }

        public void thongKeTongSoNhanVien() {
            int count = 0;
            for (NhanVien nv : ds) {
                if (!nv.isDeleted()) {
                    System.out.println(nv.toString());
                    count++;
                }
            }
            System.out.println("Tong so nhan vien: " + count);
        }

        public void thongKeLuongTrungBinh() {
            double totalSalary = 0;
            int count = 0;

            for (NhanVien nv : ds) {
                if (!nv.isDeleted()) {
                    if (nv instanceof NhanVienChinhThuc) {
                        totalSalary += ((NhanVienChinhThuc) nv).tinhLuong();
                        count++;
                    } else if (nv instanceof NhanVienHopDong) {
                        totalSalary += ((NhanVienHopDong) nv).tinhLuong();
                        count++;
                    } else if (nv instanceof QuanLi) {
                        totalSalary += ((QuanLi) nv).tinhLuong();
                        count++;
                    }
                }
            }

            if (count == 0) {
                System.out.println("Khong co nhan vien nao co luong.");
            } else {
                double averageSalary = totalSalary / count;
                System.out.println("Luong trung binh: " + averageSalary + " VND");
            }
        }


        public void thongKeTheoGioiTinh() {
            int maleCount = 0;
            int femaleCount = 0;

            for (NhanVien nv : ds) {
                if (!nv.isDeleted()) {
                    if (nv.getGioiTinh().equalsIgnoreCase("Nam")) {
                        maleCount++;
                    } else if (nv.getGioiTinh().equalsIgnoreCase("Nu")) {
                        femaleCount++;
                    }
                }
            }

            System.out.println("So luong nhan vien nam: " + maleCount);
            System.out.println("So luong nhan vien nu: " + femaleCount);
        }
		@Override
		public void thongKe() {
            int choose ; 
            do{
                System.out.println("==========Chon thong ke: ==========");
                System.out.println("1. Nhan vien nam");
                System.out.println("2. Nhan vien nu");
                System.out.println("3. Luong cao nhat");
                System.out.println("4. Luong thap nhat");
                System.out.println("5. Nhan vien chinh thuc");
                System.out.println("6. Nhan vien hop dong");
                System.out.println("7. Thuc tap sinh");
                System.out.println("8. Quan ly");
                System.out.println("9. Tong so nhan vien");
                System.out.println("10. Luong trung binh");
                System.out.println("11. Theo gioi tinh");
                System.out.println("12. Thoat");
                System.out.println("===================================");
                
                choose = Integer.parseInt(sc.nextLine());
                switch (choose) {
                case 1:
                    thongKeNhanVienNam();
                    break;
                case 2:
                    thongKeNhanVienNu();
                    break;
                case 3:
                    thongKeLuongCaoNhat();
                    break;
                case 4:
                    thongKeLuongThapNhat();
                    break;
                case 5:
                    thongKeNhanVienChinhThuc();
                    break;
                case 6:
                    thongKeNhanVienHopDong();
                    break;
                case 7:
                    thongKeThucTapSinh();
                    break;
                case 8:
                    thongKeQuanLi();
                    break;
                case 9:
                    thongKeTongSoNhanVien();
                    break;
                case 10:
                    thongKeLuongTrungBinh();
                    break;
                case 11:
                    thongKeTheoGioiTinh();
                    break;
                case 12:
                    System.out.println("Thoat thong ke.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
            }while(choose != 12);
          
        }
	/*Thuc thi chuong trinh*/
		public static void main(String[] args) {
		    DanhSachNhanVien dsnv = new DanhSachNhanVien();
		    DuAn duAn = new DuAn();
		    int choose= 0;
		    do {
		        showMenu();
		        System.out.println("Vui long nhap thao tac");
		        try {
		            choose = Integer.parseInt(sc.nextLine());
		            switch (choose) {
		                case 1:
		                    dsnv.docFile();
		                    break;
		                case 2:
		                    dsnv.hienThiDanhSach();
		                    break;
		                case 3:
		                    dsnv.them();
		                    break;
		                case 4:
		                    dsnv.sua();
		                    break;
		                case 5:
		                    dsnv.xoa();
		                    break;
		                case 6:
		                    dsnv.timKiem();
		                    break;
		                case 7:
		                    dsnv.thongKe();
		                    break;
		                case 8:
		                	DanhSachNhanVien.dsnvct = dsnv.ds;
		                	System.out.println("mã nhân viên: ");
		                	System.out.println(dsnv.check(sc.nextLine()));
		                    System.out.println("Thoat chuong trinh thanh cong!");
		                    break;
		                default:
		                    System.out.println("Lua chon khong hop le, vui long nhap lai");
		                    break;
		            }
		        } catch (NumberFormatException e) {
		            System.out.println("Thao tac khong hop le. Vui long nhap thao tac (chon 1-8)");
		        }
		    } while (choose != 8);
		    
		    		 
		}

	
}
