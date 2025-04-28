package siparisVeLojistik;

import java.time.LocalDate;

public class Kampanya {
	private int indirim;
	private Urun ekUrun;
	private LocalDate sonTarih;
	
	public Kampanya(int indirim, Urun ekUrun, LocalDate sonTarih) {
		super();
		this.indirim = indirim;
		this.ekUrun = ekUrun;
		this.sonTarih = sonTarih;
	}

	public int getIndirim() {return indirim;}
	public void setIndirim(int indirim) {this.indirim = indirim;}
	public Urun getEkUrun() {return ekUrun;}
	public void setEkUrun(Urun ekUrun) {this.ekUrun = ekUrun;}
	public LocalDate getSonTarih() {return sonTarih;}
	
	public void gunUzat(int gun) {
		this.sonTarih = this.sonTarih.plusDays(gun);
	}
	
}
