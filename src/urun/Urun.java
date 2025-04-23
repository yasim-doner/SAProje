package urun;

import java.time.LocalDate;
import java.util.HashMap;

public class Urun {
	private int barkod;
	private String isim;
	private double brutFiyat;
	private double alisFiyat;
	private HashMap<Double, LocalDate> fiyatGecmisi;
	private LocalDate skTarihi;
	private Kampanya kampanya;
	
	
	public Urun(int barkod, String isim, double brutFiyat, double alisFiyat, LocalDate skTarihi, Kampanya kampanya) {
		super();
		this.barkod = barkod;
		this.isim = isim;
		this.brutFiyat = brutFiyat;
		this.alisFiyat = alisFiyat;
		this.skTarihi = skTarihi;
		this.kampanya = kampanya;
		fiyatGecmisi = new HashMap<>();
	}

	public int getBarkod() {return barkod;}
	public String getIsim() {return isim;}
	public double getBrutFiyat() {return brutFiyat;}
	public double getAlisFiyat() {return alisFiyat;}
	public HashMap<Double, LocalDate> getFiyatGecmisi() {return fiyatGecmisi;}
	public LocalDate getSkTarihi() {return skTarihi;}

	public void setIsim(String isim) {this.isim = isim;}
	public void setBrutFiyat(double brutFiyat) {
		fiyatGecmisi.put(this.brutFiyat, LocalDate.now());
		this.brutFiyat = brutFiyat;
	}
	
	public double netFiyat() {
		if(kampanya.getSonTarih().isAfter(LocalDate.now())) { // Kapmpanya geçerli
			return this.brutFiyat*(1-kampanya.getIndirim()/100); // İndirimli Satış Fiyatı
		}
		else {
			return this.brutFiyat; // İndirimsiz Satış Fiyatı
		}
	}
	public double karHesapla() {
		double kar;
		kar = alisFiyat - netFiyat();
		if(kampanya.getEkUrun() != null) {
			kar -= kampanya.getEkUrun().getAlisFiyat();
		}
		return kar;
	}
	public boolean satisaUygun() {
		return this.skTarihi.isAfter(LocalDate.now());
	}
	public void zam(int yuzde) {
		fiyatGecmisi.put(this.brutFiyat, LocalDate.now());
		this.brutFiyat *= (1 + yuzde/100);
	}
	
}
