package urun;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

public class Urun {
	private int barkod;
	private String isim;
	private double brutFiyat;
	private double alisFiyat;
	private HashMap<LocalDate, Double>  fiyatGecmisi;
	private LocalDate skTarihi;
	private Kampanya kampanya;
	private Marka marka;
	
	
	public Urun(int barkod, String isim, double brutFiyat, double alisFiyat, LocalDate skTarihi, Marka marka) {
		super();
		this.barkod = barkod;
		this.isim = isim;
		this.brutFiyat = brutFiyat;
		this.alisFiyat = alisFiyat;
		this.skTarihi = skTarihi;
		this.kampanya = null;
		this.marka = marka;
		fiyatGecmisi = new HashMap<>();
	}

	public int getBarkod() {return barkod;}
	public String getIsim() {return isim;}
	public double getBrutFiyat() {return brutFiyat;}
	public double getAlisFiyat() {return alisFiyat;}
	public HashMap<LocalDate, Double> getFiyatGecmisi() {return fiyatGecmisi;}
	public LocalDate getSkTarihi() {return skTarihi;}

	public void setIsim(String isim) {this.isim = isim;}
	public void setBrutFiyat(double brutFiyat) {
		fiyatGecmisi.put(LocalDate.now(), this.brutFiyat);
		this.brutFiyat = brutFiyat;
	}
	
	public void setKampanya(Kampanya kampanya) {
		this.kampanya = kampanya;
	}

	public double netFiyat() {
		double netFiyat = this.brutFiyat;
		
		if(kampanya != null) {
			if(kampanya.getSonTarih().isAfter(LocalDate.now())) { // Kapmpanya geçerli
				netFiyat = netFiyat*(1-kampanya.getIndirim()/100.0); // İndirimli Satış Fiyatı
			}
		}
		return netFiyat; 
	}
	public double karHesapla() {
		double kar;
		double netAlisFiyat = this.alisFiyat;
		if(marka.getMarkaAnlasmasi() != null) {
			if(marka.getMarkaAnlasmasi().getSonTarih().isAfter(LocalDate.now())) { // Kapmpanya geçerli
				netAlisFiyat = this.alisFiyat*(1-marka.getMarkaAnlasmasi().getIndirim()/100.0); // Marka Anlaşması İndirimli Satış Fiyatı
			}
		}
		kar = netFiyat() - netAlisFiyat; 
		if(kampanya.getEkUrun() != null) {
			kar -= kampanya.getEkUrun().getAlisFiyat();
		}
		if(marka.getMarkaAnlasmasi().getEkUrun() != null) {
			kar += marka.getMarkaAnlasmasi().getEkUrun().getAlisFiyat();
		}
		return kar; // birim başına kar miktarı
	}
	public boolean satisaUygun() {
		return this.skTarihi.isAfter(LocalDate.now());
	}
	public void zam(int yuzde) {
		fiyatGecmisi.put(LocalDate.now(), this.brutFiyat);
		this.brutFiyat *= (1 + yuzde/100.0);
	}
	public double getFiyatAtDate(LocalDate date) {
		Iterator<LocalDate> iterator = this.fiyatGecmisi.keySet().iterator();
		boolean flag = true;
		double fiyatRes = -1;

		LocalDate nextDate = iterator.next();

		while (iterator.hasNext() && flag) {
			LocalDate currDate = nextDate;
			nextDate = iterator.next();

			if(nextDate.isBefore(date) || nextDate.isEqual(date)) {
				fiyatRes = this.fiyatGecmisi.get(currDate);
				flag = false;
			}
			
		}
		return fiyatRes;
	}
	
}
