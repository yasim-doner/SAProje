package siparisVeLojistik;

public class DepoUrun {
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