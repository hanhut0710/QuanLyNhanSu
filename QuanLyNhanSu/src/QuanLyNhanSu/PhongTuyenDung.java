package QuanLyNhanSu;

import java.util.Arrays;
import java.util.Scanner;

public class PhongTuyenDung extends PhongBan {

	@Override
	public void nhapThongTinPhongBan(DanhSachPhongBan danhSachPhongBan) {
		Scanner sc = new Scanner(System.in);
		boolean isDuplicate;
		do {
			isDuplicate = false;
			System.out.println("Nhập mã phòng ban: ");
			this.maPhongBan = sc.nextLine();

			for (PhongBan pb : danhSachPhongBan.getDanhSachPhongBan()) {
				if (pb.getMaPhongBan().equals(this.maPhongBan)) {
					System.out.println("Mã phòng ban đã tồn tại. Vui lòng nhập lại.");
					isDuplicate = true;
					break;
				}
			}
		} while (isDuplicate);

		System.out.println("Nhập tên phòng ban : ");
		this.tenPhongBan = sc.nextLine();

		boolean check = false;
		while (!check) {
			boolean found = false;
			System.out.println("Nhập mã quản lý phòng ban: ");
			this.quanLi = sc.nextLine();

			for (NhanVien nv : DanhSachNhanVien.dsnvct) {
				if (quanLi.equals(nv.maNhanVien)) {
					found = true;
					if (nv instanceof QuanLi) {

						if (kiemTraNhanVienTrongPhongBanKhac(quanLi, danhSachPhongBan)) {
							System.out.println("Nhân viên này đã thuộc một phòng ban khác!!!");
							break;
						}

						System.out.println("Đã nhập quản lí cho phòng !");
						check = true;
						danhSachNhanVien = Arrays.copyOf(danhSachNhanVien, danhSachNhanVien.length + 1);
						danhSachNhanVien[danhSachNhanVien.length - 1] = quanLi;
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

		int soLuongNV = 0;
		boolean isValid = false;

		do {
			try {
				System.out.print("Nhập số lượng nhân viên thêm vào phòng: ");
				soLuongNV = sc.nextInt();
				sc.nextLine();

				if (soLuongNV >= 0) {
					isValid = true;					
				} else {
					System.out.println("Số lượng nhân viên phải là số nguyên dương. Vui lòng nhập lại!");
				}
			} catch (Exception e) {
				System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
				sc.nextLine();
			}
		} while (!isValid);

		for (int i = 0; i < soLuongNV; i++) {
			String maNV;
			boolean kt = false;
			/* do while */ do {

				System.out.println("Nhập mã viên thứ " + (i + 1) + " : ");
				maNV = sc.nextLine();
				// check manv
				if (!kiemTraMaNhanVien(maNV)) { // check danh sach toan cong ty
					System.out.println("Không tìm thấy nhân viên này trong danh sách công ty, vui lòng nhập lại !!!");
					continue;
				}

				if (kiemTraNhanVienTrongPhongBanKhac(maNV, danhSachPhongBan)) {
					System.out.println("Nhân viên này đã thuộc một phòng ban khác, vui lòng nhập mã nhân viên khác!");
					continue;
				}

				boolean trungLap = false;
				for (String nvDanhSach : this.danhSachNhanVien) { // trong danh sach phong
					if (maNV.equals(nvDanhSach)) {
						trungLap = true;
						break;
					}
				}
				if (trungLap) {
					System.out.println("Mã nhân viên này đã có trong phòng , vui lòng nhập mã nhân viên khác !!!");

				} else {
					kt = true;
					danhSachNhanVien = Arrays.copyOf(danhSachNhanVien, danhSachNhanVien.length + 1);
					danhSachNhanVien[danhSachNhanVien.length - 1] = maNV;
				}
			} while (!kt);
		}

	}

	private boolean kiemTraMaNhanVien(String maNV) {
		for (NhanVien nv : DanhSachNhanVien.dsnvct) {
			if (maNV.equals(nv.maNhanVien)) {
				return true;
			}
		}
		return false;
	}

	private boolean kiemTraNhanVienTrongPhongBanKhac(String maNV, DanhSachPhongBan danhSachPhongBan) {
		for (PhongBan phongBan : danhSachPhongBan.getDanhSachPhongBan())
			for (String nv : phongBan.danhSachNhanVien)
				if (maNV.equals(nv))
					return true;
		return false;
	}

}
