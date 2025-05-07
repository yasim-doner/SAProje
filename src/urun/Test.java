package urun;

import java.time.LocalDate;

public class Test {

	public static void main(String[] args) {

		
		LocalDate skDate = LocalDate.now().plusDays(7);

		Marka etiMarka = new Marka("ETİ");
		etiMarka.anlasmaYap(40, null, 5);

		
		
		LocalDate kampanyaDate = LocalDate.now().plusDays(10);

		Kampanya kampanya = new Kampanya(20, null, kampanyaDate);
		
		
		Urun kalem = new Urun(123, "kalem", 15, 10, skDate, etiMarka);
		
		kalem.setKampanya(kampanya);
		
		
		System.out.println(kalem.karHesapla());

		
	}

}
