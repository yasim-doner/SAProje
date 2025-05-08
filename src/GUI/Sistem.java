package GUI;

import java.util.ArrayList;

import urun.*;
import personel.*;
import siparisVeLojistik.*;
import SubeIslemleri.*;


public class Sistem {
	private ArrayList<Urun> urunler;
	private ArrayList<Depo> depolar;
	private ArrayList<Marka> markalar;
	private ArrayList<Magaza> magazalar;
	private ArrayList<Personel> personeller;
	
	public Sistem() {
		urunler = new ArrayList<>();
		depolar = new ArrayList<>();
		markalar = new ArrayList<>();
		magazalar = new ArrayList<>();
		personeller = new ArrayList<>();
	}
	
	public ArrayList<Urun> getUrunler() {return urunler;}
	
	public ArrayList<Depo> getDepolar() {return depolar;}
	
	public ArrayList<Marka> getMarkalar() {return markalar;}
	
	public ArrayList<Magaza> getMagazalar() {
		return magazalar;
	}
	public ArrayList<Personel> getPersoneller() {
		return personeller;
	}

	public void urunEkle(Urun urun) throws DuplicateInfoException {
		for (Urun u : urunler) {
			if(u.equals(urun))
				throw new DuplicateInfoException("İşlem başarısız. Girdiğiniz ürün zaten sistemimizde bulunmakta.");
		}
		urunler.add(urun);
	}
	
	public void depoEkle(Depo depo) throws DuplicateInfoException {
		for (Depo d : depolar) {
			if(d.equals(depo))
				throw new DuplicateInfoException("İşlem başarısız. Girdiğiniz depo zaten sistemimizde bulunmakta.");
		}
		depolar.add(depo);
	}
	
	public void markaEkle(Marka marka) throws DuplicateInfoException {
		for (Marka m : markalar) {
			if(m.getName().equalsIgnoreCase(marka.getName()))
				throw new DuplicateInfoException("İşlem başarısız. Girdiğiniz marka zaten sistemimizde bulunmakta.");
		}
		markalar.add(marka);
	}
	public void magazaEkle(Magaza magaza) throws DuplicateInfoException {
		for (Magaza m : magazalar) {
			if(m.getMagazaAdi().equalsIgnoreCase(magaza.getMagazaAdi()))
				throw new DuplicateInfoException("İşlem başarısız. Girdiğiniz mağaza zaten sistemimizde bulunmakta.");
		}
		magazalar.add(magaza);
	}
	public void personelEkle(Personel personel) throws DuplicateInfoException {
		for (Personel p : personeller) {
			if(p.getId() == personel.getId())
				throw new DuplicateInfoException("İşlem başarısız. Girdiğiniz personel zaten sistemimizde bulunmakta.");
		}
		personeller.add(personel);
	}
	
}
