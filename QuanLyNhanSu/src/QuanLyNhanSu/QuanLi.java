package QuanLyNhanSu;

public class QuanLi extends NhanVien implements Luong {
    public double heSoLuongQuanLi;
    public double luongQuanLi;

    public QuanLi() {}


	public double getHeSoLuongQuanLi() {
		return heSoLuongQuanLi;
	}


	public void setHeSoLuongQuanLi(double heSoLuongQuanLi) {
		while(true) {
			if(heSoLuongQuanLi < 0 || heSoLuongQuanLi >= 8) {
					System.out.println("He so luong phai lon hon 0 va nho hon 8.");
					heSoLuongQuanLi = sc.nextDouble();
			}else {
					this.heSoLuongQuanLi = heSoLuongQuanLi;
					break;
			}
		}
	}


	public double getLuongQuanLi() {
		return luongQuanLi;
	}


	public void setLuongQuanLi(double luongQuanLi) {
		while(true) {
			if(luongQuanLi < 0) {
					System.out.println("Luong phai lon hon 0.");
					luongQuanLi = sc.nextDouble();
			}else {
					this.luongQuanLi = luongQuanLi;
					break;
			}
		}
	}


	@Override
    public void nhapThongTinNhanVien() {
        System.out.println("=====Nhap thong tin quan li=====");
        super.nhapThongTinNhanVien(); 
        System.out.print("Nhap he so luong nguoi quan li: ");
        setHeSoLuongQuanLi(sc.nextDouble());;
        sc.nextLine(); 
        System.out.print("Nhap luong nguoi quan li: ");
        setLuongQuanLi(sc.nextDouble());
        sc.nextLine(); 
    }
    
    @Override
    public void xuatThongTinNhanVien() {
        System.out.println("=====Thong tin quan li=====");
        super.xuatThongTinNhanVien(); 
        System.out.println("he so luong quan li: " + heSoLuongQuanLi);
        System.out.println("Luong quan li: " + luongQuanLi);
        
        System.out.println("Luong: " + tinhLuong());
    }

    @Override
    public double tinhLuong() {
        if (heSoLuongQuanLi <= 0) {
            return luongQuanLi; 
        }
        return luongQuanLi * heSoLuongQuanLi;
    }
}