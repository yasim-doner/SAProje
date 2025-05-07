package siparisVeLojistik;

import java.util.ArrayList;
import urun.*;

public class Depo {
	private String depoKonumu;
	private String depoAdi;
	private ArrayList<DepoUrun> urunler;
	private int depoKapasitesi;
	private int urunBasinaDepoKapasitesi;
	private int toplamUrunMiktari;
	
	
	public Depo(String depoKonumu, String depoAdi, int depoKapasitesi, int urunBasinaDepoKapasitesi) {
		super();
		this.depoKonumu = depoKonumu;
		this.depoAdi = depoAdi;
		this.urunler = new ArrayList<>();
		this.depoKapasitesi = depoKapasitesi;
		this.urunBasinaDepoKapasitesi = urunBasinaDepoKapasitesi;
		this.toplamUrunMiktari = 0;
	}

	public String getDepoKonumu() {return depoKonumu;}

	public void setDepoKonumu(String depoKonumu) {this.depoKonumu = depoKonumu;}

	public String getDepoAdi() {return depoAdi;}

	public void setDepoAdi(String depoAdi) {this.depoAdi = depoAdi;}

	public int getDepoKapasitesi() {return depoKapasitesi;}

	public void setDepoKapasitesi(int depoKapasitesi) {this.depoKapasitesi = depoKapasitesi;}
	
	public int getToplamUrunMiktari() {return toplamUrunMiktari;}
	
	public void setToplamUrunMiktari(int urunMiktari) {this.toplamUrunMiktari = urunMiktari;}
	
	public boolean addUrun(DepoUrun siparisUrun) {
		if(siparisUrun.getAdet()+toplamUrunMiktari<=depoKapasitesi) {
			for(DepoUrun mevcutUrun : urunler) {
				if(mevcutUrun.getUrun().getBarkod() == siparisUrun.getUrun().getBarkod()){
					if(siparisUrun.getAdet()+mevcutUrun.getAdet()<=urunBasinaDepoKapasitesi) {
						mevcutUrun.setAdet(mevcutUrun.getAdet() + siparisUrun.getAdet());
						System.out.println("Urun adedi guncellendi");
						toplamUrunMiktari = toplamUrunMiktari + siparisUrun.getAdet();
						return true;
					}else
						return false;
				}
			} 
			urunler.add(siparisUrun);
			System.out.println("Urun basariyla eklendi.");
			toplamUrunMiktari = toplamUrunMiktari + siparisUrun.getAdet();
			return true;		
		}else
			return false;
	}
	
	public void removeUrun(DepoUrun depoUrun) {
		urunler.remove(depoUrun);
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
	
	public boolean magazayaTedarikEt(Urun urun, int adet) {
		for(DepoUrun depoUrun : urunler) {
			if(urun.equals(depoUrun.getUrun())) {
				if(depoUrun.getAdet() >= adet) {
					depoUrun.setAdet(depoUrun.getAdet() - adet);
					toplamUrunMiktari = toplamUrunMiktari - adet;
					return true;
				}else {
					System.out.println("Depoda istenilen sayida urun bulunmamakta!" + urun.getIsim());
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
		System.out.println("Depodaki Urun Adedi: " + toplamUrunMiktari);
		System.out.println();
		listUrunler();
		System.out.println();
	}
	
}
