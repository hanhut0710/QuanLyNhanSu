package QuanLyNhanSu;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class NhanVienHopDong extends NhanVien implements Luong {
    public LocalDate ngayBatDau;
    public LocalDate ngayKetThuc;
    public double luongHopDong;
    Scanner sc = new Scanner(System.in);

    public NhanVienHopDong() {}

    @Override
    public void nhapThongTinNhanVien() {
        System.out.println("======NHAP THONG TIN NHAN VIEN HOP DONG=====");
        super.nhapThongTinNhanVien(); 

        while (true) {
            try {
                System.out.println("Nhap ngay bat dau (dd/MM/yyyy): ");
                String ngayBDString = sc.next();
                ngayBatDau = LocalDate.parse(ngayBDString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch (Exception e) {
                System.out.println("Dinh dang ngay khong hop le. Vui long nhap lai.");
            }
        }

        while (true) {
            try {
                System.out.println("Nhap ngay ket thuc (dd/MM/yyyy): ");
                String ngayKTString = sc.next();
                ngayKetThuc = LocalDate.parse(ngayKTString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (!ngayKetThuc.isBefore(ngayBatDau)) {
                    break;
                } else {
                    System.out.println("Ngay ket thuc phai lon hon hoac bang ngay bat dau.");
                }
            } catch (Exception e) {
                System.out.println("Dinh dang ngay khong hop le. Vui long nhap lai.");
            }
        }

        System.out.println("Nhap luong hop dong: ");
        luongHopDong = sc.nextDouble();
    }

    @Override
    public void xuatThongTinNhanVien() {
        System.out.println("=====THONG TIN NHAN VIEN HOP DONG=====");
        super.xuatThongTinNhanVien();
        System.out.println("Ngay bat dau: " + ngayBatDau);
        System.out.println("Ngay ket thuc: " + ngayKetThuc);
        System.out.println("Luong hop dong: " + luongHopDong);
    }

    @Override
    public double tinhLuong() {
        long soNgayLamViec = ChronoUnit.DAYS.between(ngayBatDau, ngayKetThuc);
        return luongHopDong * soNgayLamViec;
    }
}
