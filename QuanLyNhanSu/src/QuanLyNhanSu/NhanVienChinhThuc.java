package QuanLyNhanSu;


public class NhanVienChinhThuc extends NhanVien implements Luong {
    public int heSoLuongNhanVien;
    public double luongNhanVien;

    public NhanVienChinhThuc() {};

    @Override
    public void nhapThongTinNhanVien() {
        System.out.println("=====Nhap thong tin nhan vien chinh thuc=====");
        super.nhapThongTinNhanVien();
        System.out.println("Nhap cap bac nhan vien: ");
        heSoLuongNhanVien = sc.nextInt();
        System.out.println("Nhap luong nhan vien: ");
        luongNhanVien = sc.nextDouble();
    }
    
    @Override
    public void xuatThongTinNhanVien() {
        System.out.println("=====Thong tin nhan vien=====");
        super.xuatThongTinNhanVien();
        System.out.println("Cap bac nhan vien: " + heSoLuongNhanVien);
        System.out.println("Luong nhan vien: " + luongNhanVien);
        System.out.println("Luong: " + tinhLuong());
    }
    
    @Override
    public double tinhLuong() {
        if (this.heSoLuongNhanVien <= 0) return luongNhanVien;
        return luongNhanVien * heSoLuongNhanVien;
    }
}