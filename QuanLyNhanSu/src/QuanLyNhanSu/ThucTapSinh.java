package QLNhanSu;

public class ThucTapSinh extends NhanVien implements Luong {
    public double tienPhuCap;
    public int capBacThucTap;

    public ThucTapSinh() {};

    @Override
    public void nhapThongTinNhanVien() {
        System.out.println("======Nhap thong tin thuc tap sinh=====");
        super.nhapThongTinNhanVien();
        System.out.println("Nhap cap bac nguoi quan li: ");
        tienPhuCap = sc.nextDouble();
        System.out.println("Nhap luong nguoi quan li: ");
        capBacThucTap = sc.nextInt();
    }

    @Override
    public void xuatThongTinNhanVien() {
        System.out.println("=====Thong tin thuc tap sinh=====");
           
        super.xuatThongTinNhanVien();
        System.out.println("Tien phu cap: " + tienPhuCap);
        System.out.println("Cap bac thuc tap: " + capBacThucTap);
        
         System.out.println("Luong: "+tinhLuong());
    }

    @Override
    public double tinhLuong() {
        return tienPhuCap * capBacThucTap;
    }
}