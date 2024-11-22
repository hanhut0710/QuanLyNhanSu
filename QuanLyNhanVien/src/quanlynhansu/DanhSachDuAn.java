package quanlynhansu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.NotSerializableException;
import java.util.Arrays;
import java.util.Scanner;

import javax.net.ssl.SSLEngine;

import java.lang.ClassNotFoundException;

public class DanhSachDuAn implements QuanLiDanhSach, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DuAn[] danhSachDuAn= new DuAn[0];
	public static int stt = 0;
	public DanhSachDuAn() {
		this.danhSachDuAn = new DuAn[0];
	}
	
	public boolean checkMaDuAn(String maDuAn) {
		for(DuAn x : danhSachDuAn) {
			if(x.getMaDuAn().equals(maDuAn))
				return false;
		}
		return true;
	}
	
	public void them() {
		DuAn duAn = new DuAn();
		do {
			duAn.nhapThongTinDuAn();
			if(!checkMaDuAn(duAn.getMaDuAn()))
				System.out.println("Mã dự án đã tồn tại, vui lòng nhập lại mã dự án !!!");
		} while (!checkMaDuAn(duAn.getMaDuAn()));
		danhSachDuAn = Arrays.copyOf(danhSachDuAn, danhSachDuAn.length + 1);
		danhSachDuAn[danhSachDuAn.length - 1] = duAn;
		stt++;
	}
	
	public void hienThiDanhSach() {
		int soThuTu = 1;
		for(DuAn x : danhSachDuAn) {
			if(!x.isTrangThaiXoa()) {
				x.xuatThongTinDuAn(soThuTu);
				soThuTu++;
			}
		}
	}
	
	public void sua() {
		System.out.println("----Danh sách dự án hiện tại----");
		hienThiDanhSach();
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---------------------------");
			System.out.println("Bạn muốn sửa dự án số mấy: ");
			choice = sc.nextInt();
			if(choice < 0 || choice > stt) {
				System.out.println("Dự án không tồn tại vui lòng nhập lại số thứ tự !!");
			}
		}while(choice < 0 || choice > stt);
		while(true) {
			System.out.println("----Chỉnh sửa thông tin dự án----");
			System.out.println("1. Sửa mã dự án");
			System.out.println("2. Sửa tên dự án");
			System.out.println("3. Sửa ngày bắt đầu dự án");
			System.out.println("4. Sửa ngày dự kiến kết thúc");
			System.out.println("0.Thoát");
			System.out.print("Nhập lựa chọn: ");
			int luaChon = sc.nextInt();
			sc.nextLine();
			switch(luaChon) {
				case 1:
					System.out.print("Nhập mã dự án: ");
					this.danhSachDuAn[choice - 1].setMaDuAn(sc.nextLine());
					break;
				case 2:
					System.out.print("Nhập tên dự án: ");
					this.danhSachDuAn[choice - 1].setTenDuAn(sc.nextLine());
					break;
				case 3:
					System.out.print("Nhập ngày bắt đầu dự án: ");
					this.danhSachDuAn[choice - 1].setNgayBatDau(sc.nextLine());
					break;
				case 4:
					System.out.print("Nhập ngày dự kiến kết thúc: ");
					this.danhSachDuAn[choice -1].setDuKienKetThuc(sc.nextLine());
				case 0:
					System.out.println("Thoát !!");
					System.out.println("----Danh sách dự án sau khi chỉnh sửa----");
					hienThiDanhSach();
					return;
				default:
					System.out.println("Lựa chọn không hợp lệ");
					break;
			}
		}
		
	}
	
	
	public int checkDanhSach() {
		for(DuAn x : danhSachDuAn) {
			if(!x.isTrangThaiXoa())
				return 0;
		}
		return 1;
	}
	
	public int checkDanhSach(String maDuAn) {
		for(DuAn x : danhSachDuAn ) {
			if(maDuAn.equals(x.getMaDuAn()))
				return 0;
		}
		return 1;
	}
	
	public void history() {
		for(DuAn x : danhSachDuAn) {
			x.xuatThongTinDuAn();
		}
	}
	
	public int checkTrue(String maDuAn) {
		for(DuAn x : danhSachDuAn)
			if(x.getMaDuAn().equals(maDuAn)) {
				if(x.isTrangThaiXoa())
					return 0;
			}
		return 1;
	}
	
	public void xoa() {
		if(checkDanhSach() == 1) {
			System.out.println("Danh sách đang rỗng");
			return ;
		}
		System.out.println("----Danh sách dự án hiện tại----");
		this.hienThiDanhSach();
		Scanner sc = new Scanner(System.in);
		int choice;
		System.out.println("----Xóa theo khóa----");
		System.out.println("1. Xóa theo số thứ tự");
		System.out.println("2. Xóa theo mã dự án");
		System.out.print("Nhập lựa chọn: ");
		choice = sc.nextInt();
		if(choice == 1) {
			int soThuTu;
			do {
				System.out.println("-------------------------");
				System.out.print("Bạn muốn xóa dự án số mấy: ");
				soThuTu = sc.nextInt();
				if(soThuTu < 0 || soThuTu > stt) {
					System.out.println("Dự án không tồn tại vui lòng nhập lại số thứ tự !!");
				}
			} while (soThuTu < 0 || soThuTu > stt );
			int index = 1;
			for(DuAn x : danhSachDuAn) {
				if(index != soThuTu) {
					index++;
					continue;
				}
				else {
					if(x.isTrangThaiXoa())
						continue;
					else {
						x.setTrangThaiXoaTrue();
						stt--;
						break;
					}
				}
			}
		}
		else if(choice == 2) {
			String maDuAn;
			do {
				System.out.println("-------------------------");
				System.out.print("Nhập mã dự án muốn xóa: ");
				sc.nextLine();
				maDuAn = sc.nextLine();
			
				if(checkDanhSach(maDuAn) == 1 )
					System.out.println("Dự án không tồn tại, vui lòng nhập lại mã dự án !!");
				System.out.println(checkTrue(maDuAn));
				if(checkTrue(maDuAn) == 0) {
					System.out.println("Dự án đã được xóa trước đó");
					return ;
				}
			} while (checkDanhSach(maDuAn) == 1);
			for(DuAn x : danhSachDuAn) {
				if(x.getMaDuAn().equals(maDuAn)) {
					x.setTrangThaiXoaTrue();
					stt--;
					break;
				}
			}
		}
		
	}
	
	
	public void timKiem() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----Tìm kiếm dự án----");
		System.out.println("1. Tìm kiếm theo mã");
		System.out.println("2. Tìm kiếm theo tên");
		System.out.println("3. Tìm kiếm theo ngày bắt đầu dự án");
		System.out.println("4. Tìm kiếm theo ngày dự kiến kết thúc");
		System.out.print("Nhập lựa chọn: ");
		int luaChon = sc.nextInt();
		switch(luaChon) {
			case 1:
				timKiemTheoMa();
				break;
			case 2:
				timKiemTheoTen();
				break;
			case 3:
				
			default:
				System.out.println("Lụa chọn không tồn tại");
				break;
		}
	}
	
	public void timKiemTheoTen() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----Tìm kiếm theo mã----");
		System.out.print("Nhập tên dự án: ");
		String maDuAn = sc.nextLine();
		int k = 0;
		for(DuAn x : danhSachDuAn) {
			if(maDuAn.equals(x.getTenDuAn())) {
				x.xuatThongTinDuAn();
				k++;
				break;
			}
		}
		if(k == 0) {
			System.out.println("Dự án không tồn tại !!!");
		}
	}
	
	public void timKiemTheoMa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----Tìm kiếm theo mã----");
		System.out.print("Nhập mã dự án: ");
		String maDuAn = sc.nextLine();
		int k = 0;
		for(DuAn x : danhSachDuAn) {
			if(maDuAn.equals(x.getMaDuAn())) {
				x.xuatThongTinDuAn();
				k++;
				break;
			}
		}
		if(k == 0) {
			System.out.println("Dự án không tồn tại !!!");
		}
	}
	
	public void timKiemTheoNgayBatDau() {
		boolean success = false;
		Scanner sc = new Scanner(System.in);
		Date date = null ;
		do {
			try {
				System.out.println("------Tìm kiếm theo ngày bắt đầu------");
				System.out.println("Nhập ngày bắt đầu dự án(dd/MM/yyyy): ");
				date = new Date(sc.nextLine());
				success = true;
			} 
			catch(NumberFormatException e) {
				System.out.println("Vui lòng nhập ngày đúng định dạng(dd/MM/yyyy) !!!");
			}
		}while(!success);
		int k = 0;
		for(DuAn x : danhSachDuAn) {
			if(x.getNgayBatDau().getDate().equals(date.getDate())) {
				x.nhapThongTinDuAn();
				k++;
			}
				
		}
		
		if(k == 0) {
			System.out.println("Dự án không tồn tại !!!");
		}
		
	}
	
	public void checkStatus() {
		for(DuAn x: danhSachDuAn)
		{
			System.out.println(x.isTrangThaiXoa());
		}
	}

	@Override
	public void thongKe() {
		
		
	}

	@Override
	public void docFile() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fileIn = new FileInputStream("DuAn.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			while(fileIn.available() > 0) {
				DuAn duAn = (DuAn) in.readObject();
				danhSachDuAn = Arrays.copyOf(danhSachDuAn, danhSachDuAn.length + 1);
				danhSachDuAn[danhSachDuAn.length - 1] = duAn;
				stt++;
			}
			
			in.close();
			fileIn.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void ghiFile() {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fileOut = new FileOutputStream("DuAn.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			for(DuAn x : danhSachDuAn) {
				out.writeObject(x);
			}
			
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotSerializableException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

	
}
