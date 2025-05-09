package GUI;

import java.io.Serializable;
import java.util.ArrayList;

import urun.*;
import personel.*;
import siparisVeLojistik.*;
import SubeIslemleri.*;


public class Sistem implements Serializable {
	
    private static final long serialVersionUID = 1L;

	private ArrayList<Urun> urunler;
	private ArrayList<Depo> depolar;
	private ArrayList<Marka> markalar;
	private ArrayList<Magaza> magazalar;
	private PersonelManager personelManager;
	
	public Sistem() {
		this.urunler = new ArrayList<>();
		this.depolar = new ArrayList<>();
		this.markalar = new ArrayList<>();
		this.magazalar = new ArrayList<>();
		this.personelManager = new PersonelManager();
	}
	
	public PersonelManager getPersonelManager() {
		return this.personelManager;
	}
	
	public ArrayList<Urun> getUrunler() {return urunler;}
	
	public ArrayList<Depo> getDepolar() {return depolar;}
	
	public ArrayList<Marka> getMarkalar() {return markalar;}
	
	public ArrayList<Magaza> getMagazalar() {
		return magazalar;
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
	
	public ArrayList<Magaza> getMagazaAtKonum(String konum) {
		ArrayList<Magaza> konumMagaza = new ArrayList<>();
		for(Magaza m : magazalar) {
			if(m.getMagazaAdres().equals(konum) || konum.equals("")) {
				konumMagaza.add(m);
			}
		}
		return konumMagaza;
	}
	
	public Depo getDepobyName(String name) {
		for (Depo depo : depolar) {
			if(depo.getDepoAdi().equals(name)) return depo;
		}
		return null;
	}
	public Urun getUrunbyName(String name) {
		for (Urun urun : urunler) {
			if(urun.getIsim().equals(name)) return urun;
		}
		return null;
	}
	public Magaza getMagazabyName(String name) {
		for (Magaza magaza : magazalar) {
			if(magaza.getMagazaAdi().equals(name)) return magaza;
		}
		return null;
	}
	public Personel getPersonelbyName(String name) {
		for (Personel personel : personelManager.getPersonelListesi()) {
			if(personel.getAdSoyad().equals(name)) return personel;
		}
		return null;
	}
	
	
}
