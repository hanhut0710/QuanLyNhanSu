package QuanLyNhanSu;


public class NhanVienChinhThuc extends NhanVien implements Luong {
    public double heSoLuongNhanVien;
    public double luongNhanVien;

    public NhanVienChinhThuc() {};
    
    public void setHeSoLuongNhanVien(double heSoLuongNhanVien) {
    	while(true) {
			if(heSoLuongNhanVien < 0 || heSoLuongNhanVien >= 8) {
					System.out.println("He so luong phai lon hon 0 va nho hon 8.");
					heSoLuongNhanVien = sc.nextDouble();
			}else {
					this.heSoLuongNhanVien = heSoLuongNhanVien;
					break;
			}
		}
    }
    public void setLuongNhanVien(double luongNhanVien) {
    	while(true) {
			if(luongNhanVien < 0) {
					System.out.println("Luong phai lon hon 0.");
					luongNhanVien = sc.nextDouble();
			}else {
					this.luongNhanVien = luongNhanVien;
					break;
			}
		}
    }
    @Override
    public void nhapThongTinNhanVien() {
        System.out.println("=====Nhap thong tin nhan vien chinh thuc=====");
        super.nhapThongTinNhanVien();
        System.out.println("Nhap he so luong nhan vien: ");
        setHeSoLuongNhanVien(sc.nextDouble());
        System.out.println("Nhap luong nhan vien: ");
        setLuongNhanVien(sc.nextDouble());
    }
    
    @Override
    public void xuatThongTinNhanVien() {
        System.out.println("=====Thong tin nhan vien=====");
        super.xuatThongTinNhanVien();
        System.out.println("he so luong nhan vien: " + heSoLuongNhanVien);
        System.out.println("Luong nhan vien: " + luongNhanVien);
        System.out.println("Luong: " + tinhLuong());
    }
    
    @Override
    public double tinhLuong() {
        if (this.heSoLuongNhanVien <= 0) return luongNhanVien;
        return luongNhanVien * heSoLuongNhanVien;
    }
}