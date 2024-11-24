package QuanLyNhanSu;

public class ThucTapSinh extends NhanVien implements Luong {
    public double tienPhuCap;
    public int heSoLuongThucTap;

    public ThucTapSinh() {};

    @Override
    public void nhapThongTinNhanVien() {
        System.out.println("======Nhap thong tin thuc tap sinh=====");
        super.nhapThongTinNhanVien();
        System.out.println("Nhap he so luong nguoi quan li: ");
        tienPhuCap = sc.nextDouble();
        System.out.println("Nhap luong nguoi quan li: ");
        heSoLuongThucTap = sc.nextInt();
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