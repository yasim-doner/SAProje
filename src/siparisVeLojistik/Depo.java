package siparisVeLojistik;

import java.util.ArrayList;

public class Depo {
	private String depoKonumu;
	private String depoAdi;
	private ArrayList<DepoUrun> urunler;
	private int depoKapasitesi;
	
	public Depo(String depoKonumu, String depoAdi, int depoKapasitesi) {
		super();
		this.depoKonumu = depoKonumu;
		this.depoAdi = depoAdi;
		this.urunler = new ArrayList<>() ;
		this.depoKapasitesi = depoKapasitesi;
	}

	public String getDepoKonumu() {
		return depoKonumu;
	}

	public void setDepoKonumu(String depoKonumu) {
		this.depoKonumu = depoKonumu;
	}

	public String getDepoAdi() {
		return depoAdi;
	}

	public void setDepoAdi(String depoAdi) {
		this.depoAdi = depoAdi;
	}

	public int getDepoKapasitesi() {
		return depoKapasitesi;
	}

	public void setDepoKapasitesi(int depoKapasitesi) {
		this.depoKapasitesi = depoKapasitesi;
	}
	
	public void addUrun(DepoUrun siparisUrun) {
		for(DepoUrun mevcutUrun : urunler) {
			if(mevcutUrun.getUrun().getBarkod() == siparisUrun.getUrun().getBarkod()) {
				mevcutUrun.setAdet(mevcutUrun.getAdet() + siparisUrun.getAdet());
				System.out.println("Urun adedi guncellendi");
			}
			
			urunler.add(siparisUrun);
			System.out.println("Urun basariyla eklendi.");
		}
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
	
	public void listDepoBilgileri() {
		System.out.println("Depo Adi: " + depoAdi);
		System.out.println("Depo Konumu: " + depoKonumu);
		System.out.println("Depo Kapasitesi: " + depoKapasitesi);
		System.out.println();
		listUrunler();
	}
	
	
	
}
