package QuanLyNhanSu;

import java.util.Scanner;

public class PhongKiThuat extends PhongBan {
	
	
	@Override
	public void nhapThongTinPhongBan() {
		Scanner sc =new Scanner(System.in);
		System.out.println("Nhập mã phòng ban : ");
		this.maPhongBan = sc.nextLine();
		System.out.println("Nhập tên phòng ban : ");
		this.tenPhongBan = sc.nextLine();
		
	}
}