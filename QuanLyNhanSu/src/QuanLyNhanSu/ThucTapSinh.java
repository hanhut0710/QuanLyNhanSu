package QuanLyNhanSu;

public class ThucTapSinh extends NhanVien implements Luong {
    public double tienPhuCap;
    public double heSoLuongThucTap;

    public ThucTapSinh() {};
    
    public void setHeSoLuongThucTap (double heSoLuongThucTap) {
    	while(true) {
			if(heSoLuongThucTap < 0 || heSoLuongThucTap >= 8) {
					System.out.println("He so luong phai lon hon 0 va nho hon 8.");
					heSoLuongThucTap = sc.nextDouble();
			}else {
					this.heSoLuongThucTap = heSoLuongThucTap;
					break;
			}
		}
    }
    public void setTienPhuCap (double tienPhuCap) {
    	while(true) {
			if(tienPhuCap < 0) {
					System.out.println("Luong phai lon hon 0.");
					tienPhuCap = sc.nextDouble();
			}else {
					this.tienPhuCap = tienPhuCap;
					break;
			}
		}
    }
    @Override
    public void nhapThongTinNhanVien() {
        System.out.println("======Nhap thong tin thuc tap sinh=====");
        super.nhapThongTinNhanVien();
        System.out.println("Nhap he so luong thuc tap sinh: ");
        setHeSoLuongThucTap(sc.nextDouble());
        System.out.println("Nhap luong thuc tap sinh: ");
        setTienPhuCap(sc.nextDouble());
    }

    @Override
    public void xuatThongTinNhanVien() {
        System.out.println("=====Thong tin thuc tap sinh=====");
           
        super.xuatThongTinNhanVien();
        System.out.println("Tien phu cap: " + tienPhuCap);
        System.out.println("he so luong thuc tap: " + heSoLuongThucTap);
        
         System.out.println("Luong: "+tinhLuong());
    }

    @Override
    public double tinhLuong() {
        if (this.heSoLuongThucTap <= 0) return this.tienPhuCap;
        return this.tienPhuCap * this.heSoLuongThucTap;
    }
}