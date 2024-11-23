package QuanLyNhanSu;

public class QuanLi extends NhanVien implements Luong {
    public int capBacQuanLi;
    public double luongQuanLi;

    public QuanLi() {}

    @Override
    public void nhapThongTinNhanVien() {
        System.out.println("=====Nhap thong tin quan li=====");
        super.nhapThongTinNhanVien(); 
        System.out.print("Nhap cap bac nguoi quan li: ");
        capBacQuanLi = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Nhap luong nguoi quan li: ");
        luongQuanLi = sc.nextDouble();
        sc.nextLine(); 
    }
    
    @Override
    public void xuatThongTinNhanVien() {
        System.out.println("=====Thong tin quan li=====");
        super.xuatThongTinNhanVien(); 
        System.out.println("Cap bac quan li: " + capBacQuanLi);
        System.out.println("Luong quan li: " + luongQuanLi);
        
        System.out.println("Luong: " + tinhLuong());
    }

    @Override
    public double tinhLuong() {
        return luongQuanLi * capBacQuanLi;
    }
}