package urun;

import java.io.Serializable;
import java.time.LocalDate;

import SubeIslemleri.Magaza;

public class Marka implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -625087074920827276L;
	private String name;
	private Kampanya markaAnlasmasi;
	
	public Marka(String name) {
		this.name = name;
		this.markaAnlasmasi = null;
	}
	
	public String getName() {return name;}
	public Kampanya getMarkaAnlasmasi() {return markaAnlasmasi;}
	
	public void anlasmaYap(int indirim, Urun ekUrun, long ay) {
		Kampanya anlasma = new Kampanya(indirim, ekUrun, LocalDate.now().plusMonths(ay)); 
		this.markaAnlasmasi = anlasma;
	}
	
	public void urunİade(Magaza magaza, Urun urun, Integer adet) {
        
		if (magaza.getUrunler().get(urun) > adet) {
			magaza.getUrunler().put(urun, magaza.getUrunler().get(urun) - adet);
			magaza.setCiro(magaza.getCiro() + urun.getAlisFiyat()*adet);
		}	
	}
	
}
