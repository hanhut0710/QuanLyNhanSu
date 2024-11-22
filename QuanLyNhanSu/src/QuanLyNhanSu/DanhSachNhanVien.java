package QLNhanSu;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class DanhSachNhanVien {
	NhanVien ds [] = new NhanVien [0];
	static Scanner sc = new Scanner(System.in);
	static void showMenu()
	{	
		System.out.println("QUAN LY NHAN VIEN");
		System.out.println("1. Nhap danh sach");
		System.out.println("2. Xuat danh sach");
		System.out.println("3. Them nhan vien");
		System.out.println("4. Sua thong tin nhan vien");
		System.out.println("5. Xoa nhan vien");
		System.out.println("6. Tim kiem nhan vien");
		System.out.println("7. Thong ke");
		System.out.println("8. Thoat");
		System.out.println();
	}
	
	public void nhapDanhSach()
	{	
		try 
		{
			FileReader fr = new FileReader("data.txt");
			BufferedReader br = new BufferedReader(fr);
			String st;
			
			while(true)
			{
				st=br.readLine();
				if(st == null)
					break;
				String s[] = st.split("\t");
				if(s.length == 6)
				{
					String maNhanVien = s[0].trim();
					String hoTen = s[1].trim();
					String gioiTinh = s[2].trim();
					String ngaySinh = s[3].trim();
					String soDienThoai = s[4].trim();
					boolean isDeleted = Boolean.parseBoolean(s[5].trim());
			
					NhanVien nv = new NhanVien(maNhanVien, hoTen, gioiTinh, ngaySinh, soDienThoai, isDeleted);
					nv.setDeleted(isDeleted);
					
					ds=Arrays.copyOf(ds, ds.length+1);
					ds[ds.length - 1]= nv;
					System.out.println("Nhap thanh cong nhan vien " +hoTen);
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
	
	
	public void xuatDanhSach()
	{	/*Nếu isDeleted = true thì mới xuất ra, false thì không in nữa*/
		for(int i=0; i < ds.length; i++)
		{	
			if(ds[i].isDeleted() == false)
			{
				System.out.printf("Thong tin nhan vien thu %d \n", i+1);
				ds[i].xuatThongTinNhanVien();
			}
			
		}
	}
	
	public void them()
	{	
		while(true)
		{
			int n;
			System.out.println("Moi nhap so luong nhan vien muon them: ");
			n = Integer.parseInt(sc.nextLine());
			
			int originLength = ds.length;
			
			for(int i = 0; i < n; i++)
			{	
				NhanVien nv;
				System.out.printf("Moi nhap thong tin nhan vien thu %d \n", i+1);
				System.out.println("Moi nhap loai nhan vien");
				System.out.println("1. Quan ly");
				System.out.println("2. Nhan vien chinh thuc");
				System.out.println("3. Nhan vien hop dong");
				System.out.println("4. Thuc tap sinh");
				int choose = Integer.parseInt(sc.nextLine());
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
				ds = Arrays.copyOf(ds, ds.length+1);
				ds[ds.length-1]= nv;
			} 
			if(ds.length > originLength)
				System.out.printf("Them thanh cong %d nhan vien \n", ds.length-originLength);
			else System.out.println("Them that bai");
			break;
		}
		
	}
	
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
			System.out.println("Nhan vien can sua:");
			nvCanSua.xuatThongTinNhanVien();
			/*Nhập lại 1 thông tin hay nhập lại hết????*/
			System.out.println("Moi nhap thong tin moi cho nhan vien");
			nvCanSua.nhapThongTinNhanVien();
		}
		else System.out.printf("Khong tim nhan nhan vien voi ma %s \n", maNhanVienCanSua);
	}
	
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
					System.out.printf("Xoa thanh cong nhan vien co ma %s \n", maNhanVienCanXoa);
				else System.out.println("Loi xay ra, xoa khong thanh cong");
			}
			else System.out.println("Huy xoa nhan vien");
		}
		else System.out.printf("Khong tim thay nhan vien voi ma %s \n", maNhanVienCanXoa);
	}
	
	public void timKiem()
	{
		
	}
	
	/*Thuc thi chuong trinh*/
	public static void main (String [] args)
	{	
		DanhSachNhanVien dsnv = new DanhSachNhanVien();
		
		int choose;
		do {
			showMenu();
			System.out.println("Vui long nhap thao tac");
			choose = Integer.parseInt(sc.nextLine());
			
			switch(choose)
			{
				case 1:
					dsnv.nhapDanhSach();
					break;
				case 2:
					dsnv.xuatDanhSach();
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
					break;
				case 8:
					System.out.println("Thoat chuong trinh thanh cong!");
					break;
				default:
					System.out.println("Lua chon khong hop le, vui long nhap lai");
					break;
			}
		} while(choose != 8);
	}
	
	
	
}
