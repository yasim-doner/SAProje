package GUI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;

import urun.*;
import personel.*;
import siparisVeLojistik.*;
import SubeIslemleri.*;


public class Sistem {
	private ArrayList<Urun> urunler;
	private ArrayList<Depo> depolar;
	private ArrayList<Marka> markalar;
	
	public Sistem() {
		urunler = new ArrayList<>();
		depolar = new ArrayList<>();
		markalar = new ArrayList<>();
	}
	
	public ArrayList<Urun> getUrunler() {return urunler;}
	
	public ArrayList<Depo> getDepolar() {return depolar;}
	
	public ArrayList<Marka> getMarkalar() {return markalar;}

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
	
}
