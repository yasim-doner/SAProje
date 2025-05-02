package siparisVeLojistik;

import java.util.ArrayList;
import SubeIslemleri.*;
import urun.*;

public class Depo {
	private String depoKonumu;
	private String depoAdi;
	private ArrayList<DepoUrun> urunler;
	private ArrayList<Magaza> magazalar;
	private int depoKapasitesi;
	private int urunMiktari;
	
	
	public Depo(String depoKonumu, String depoAdi, int depoKapasitesi) {
		super();
		this.depoKonumu = depoKonumu;
		this.depoAdi = depoAdi;
		this.urunler = new ArrayList<>();
		this.magazalar = new ArrayList<>();
		this.depoKapasitesi = depoKapasitesi;
		this.urunMiktari = 0;
	}

	public String getDepoKonumu() {return depoKonumu;}

	public void setDepoKonumu(String depoKonumu) {this.depoKonumu = depoKonumu;}

	public String getDepoAdi() {return depoAdi;}

	public void setDepoAdi(String depoAdi) {this.depoAdi = depoAdi;}

	public int getDepoKapasitesi() {return depoKapasitesi;}

	public void setDepoKapasitesi(int depoKapasitesi) {this.depoKapasitesi = depoKapasitesi;}
	
	public int getUrunMiktari() {return urunMiktari;}
	
	public void setUrunMiktari(int urunMiktari) {this.urunMiktari = urunMiktari;}
	
	public void addUrun(DepoUrun siparisUrun) {
		for(DepoUrun mevcutUrun : urunler) {
			if(mevcutUrun.getUrun().getBarkod() == siparisUrun.getUrun().getBarkod()) {
				mevcutUrun.setAdet(mevcutUrun.getAdet() + siparisUrun.getAdet());
				System.out.println("Urun adedi guncellendi");
			}else {
				urunler.add(siparisUrun);
				System.out.println("Urun basariyla eklendi.");
			}
			urunMiktari = urunMiktari + siparisUrun.getAdet();
		}
	}
	
	public void removeUrun(DepoUrun depoUrun) {
		urunler.remove(depoUrun);
	}
	
	public void addMagaza(Magaza magaza) {
		if(magazalar.isEmpty()) {
			magazalar.add(magaza);
		}else {
			for(Magaza mevcutMagaza : magazalar) {
				if(magaza.equals(mevcutMagaza)) {
					System.out.println("Bu magaza bu depoyu zaten kullanmakta.");
					return;
				}
			}
			magazalar.add(magaza);
		}
	}
	
	public void listUrunler() {
		if (urunler.isEmpty()) {
            System.out.println("Bu depoda urun bulunmamaktadır.");
        } else {
            System.out.println(depoAdi +" isimli depodaki urunler: ");
            for (DepoUrun urun : urunler) {
                System.out.println(urun.getUrun().getBarkod() + " - " + urun.getUrun().getIsim() + " - Depoda " + urun.getAdet() + " adet bulunmaktadir.");
            }
        }
	}
	
	public boolean magazayaTedarikEt(Magaza magaza, Urun urun, int adet) {
		for(DepoUrun depoUrun : urunler) {
			if(urun.equals(depoUrun.getUrun())) {
				if(depoUrun.getAdet() >= adet) {
					depoUrun.setAdet(depoUrun.getAdet() - adet);
					urunMiktari = urunMiktari - adet;
					System.out.println("Siparis basariyla yola cikti.");
					return true;
				}else {
					System.out.println("Depoda istenilen sayida urun bulunmamakta!");
					return false;
				}
			}
		}
		System.out.println("Istenilen urun depoda bulunmamakta!");
		return false;
	}
	
	public void listDepoBilgileri() {
		System.out.println();
		System.out.println("Depo Adi: " + depoAdi);
		System.out.println("Depo Konumu: " + depoKonumu);
		System.out.println("Depo Kapasitesi: " + depoKapasitesi);
		System.out.println("Depodaki Urun Adedi: " + urunMiktari);
		System.out.println();
		listUrunler();
		System.out.println();
	}
	
}
