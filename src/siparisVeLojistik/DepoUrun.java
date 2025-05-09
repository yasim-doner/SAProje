package siparisVeLojistik;

import java.io.Serializable;

import urun.*;

public class DepoUrun implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1480642082128640048L;
	private Urun urun;
	private int adet;
	
	public DepoUrun(Urun urun, int adet) {
		this.urun = urun;
		this.adet = adet;
	}
	
	public Urun getUrun() { return urun; }
	
	public int getAdet() { return adet; }
	
	public void setAdet(int adet) { this.adet = adet; }
	
}