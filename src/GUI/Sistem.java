package GUI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import urun.*;
import personal.*;
import siparisVeLojistik.*;
import SubeIslemleri.*;


public class Sistem {
	private ArrayList<Urun> urunler;
	private ArrayList<Depo> depolar;
	
	public Sistem() {
		urunler = new ArrayList<>();
		depolar = new ArrayList<>();
	}
	
	public ArrayList<Urun> getUrunler() {return urunler;}
	
	public ArrayList<Depo> getDepolar() {return depolar;}

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
	
}
