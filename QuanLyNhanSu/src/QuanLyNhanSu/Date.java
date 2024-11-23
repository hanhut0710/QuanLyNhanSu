package QuanLyNhanSu;	

import java.io.Serializable;

public class Date implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	
	public Date(String date) {
		String[] parts = date.split("/");
		 
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);
		if((day>=1 && day <=31) && (month>= 1 && month <=12) && (year >= 100)) {
			if((day == 31) && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11) ){
				System.out.println("Lỗi: Ngày nhập không hợp lệ");
			}
			else if(day == 29 && month == 2 && !namNhuan(year)) {
				System.out.println("Lỗi: Ngày nhập không hợp lệ");
			}
			else
				this.date = date;
		}
		else
			System.out.println("Lỗi: Ngày nhập sai định dạng");
	}
	
	public boolean namNhuan(int year) {
		if(year % 4 == 0) {
			if(year % 100 == 0) {
				if(year % 400 == 0)
					return true;
				else 
					return false;
			}
			else
				return true;
		}
		else 
			return false;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}