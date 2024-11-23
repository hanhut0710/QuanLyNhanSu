package QuanLyNhanSu;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;



public class FileManager {
	
	private static final String File_Path = "C:\\Users\\user\\eclipse-workspace\\QuanLyNhanSu\\src\\input.txt";
	
	public static DanhSachPhongBan docFile() {
		DanhSachPhongBan danhSachPhongBan = new DanhSachPhongBan();
		try{
			Path path = Paths.get(File_Path);
			BufferedReader br  = Files.newBufferedReader(path,StandardCharsets.UTF_8);
			String line;
			while((line = br.readLine()) != null)
			{
				String[] parts = line.split(",");
				String loaiPhong = parts[0];
				String maPhongBan = parts[1];
				String tenPhongBan = parts[2];
				boolean trangThai = Boolean.parseBoolean(parts[3]);
				PhongBan phongBan = null;
				if(loaiPhong.equals("PhongIT")) {
					phongBan = new PhongIT();
				}
				else if(loaiPhong.equals("PhongTaiChinh")) {
					phongBan = new PhongTaiChinh();
				}
				else if(loaiPhong.equals("PhongKiThuat")) {
					phongBan = new PhongKiThuat();
				}
				else if(loaiPhong.equals("PhongTuyenDung")) {
					phongBan = new PhongTuyenDung();
				}
				else continue;				
				
				phongBan.setMaPhongBan(maPhongBan);
				phongBan.setTenPhongBan(tenPhongBan);
				phongBan.setTrangThai(trangThai);
				danhSachPhongBan.themPhongBan(phongBan);
			}						
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return danhSachPhongBan;
	}
	
	
	public static void ghiFile(DanhSachPhongBan danhSachPhongBan) {
		try {
		Path path = Paths.get(File_Path);
		BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
		
		for(PhongBan phongBan : danhSachPhongBan.getDanhSachPhongBan()) {
			String loaiPhong = null ;
			if(phongBan instanceof PhongIT) {
				loaiPhong = "PhongIT";
			}
			else if (phongBan instanceof PhongTaiChinh){
				loaiPhong = "PhongTaiChinh";
			}
			else if (phongBan instanceof PhongKiThuat){
				loaiPhong = "PhongKiThuat";
			}
			else if (phongBan instanceof PhongTuyenDung){
				loaiPhong = "PhongTuyenDung";
			}
			String line = loaiPhong + "," + phongBan.getMaPhongBan() + "," + phongBan.getTenPhongBan() + "," + phongBan.isTrangThai();
			
			bw.write(line);
			bw.newLine();
		}
		 bw.close();
         System.out.println("Đã in danh sách phòng ban ra file.");
         
		}catch(Exception e) {
			System.out.println("Không thể ghi file với lỗi : "+e.getMessage());
		}
	}
}

