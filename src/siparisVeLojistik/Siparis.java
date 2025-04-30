package siparisVeLojistik;

import java.time.LocalDate;
import java.util.ArrayList;

public class Siparis {
	private int siparisNo;
	private double siparisTutari;
	private String tedarikciAdi;
	private ArrayList<DepoUrun> siparisUrunleri;
	private LocalDate siparisTarihi;
	private LocalDate teslimTarihi;
	private boolean teslimEdildi;
	private boolean siparisIptal;
	private Depo hedefDepo;
	
	public Siparis(int siparisNo, String tedarikciAdi, ArrayList<DepoUrun> siparisUrunleri,LocalDate teslimTarihi, Depo hedefDepo) {
		super();
		this.siparisNo = siparisNo;
		this.siparisTutari = 0;
		this.tedarikciAdi = tedarikciAdi;
		this.siparisUrunleri = siparisUrunleri;
		this.siparisTarihi = LocalDate.now();
		this.teslimTarihi = teslimTarihi;
		this.teslimEdildi = false;
		this.siparisIptal = false;
		this.hedefDepo = hedefDepo;
	}

	public ArrayList<DepoUrun> getSiparisUrunleri() {return siparisUrunleri;}

	public void setSiparisUrunleri(ArrayList<DepoUrun> siparisUrunleri) {this.siparisUrunleri = siparisUrunleri;}
	
	public int getSiparisNo() {return siparisNo;}

	public String getTedarikciAdi() {return tedarikciAdi;}

	public LocalDate getSiparisTarihi() {return siparisTarihi;}

	public LocalDate getTeslimTarihi() {return teslimTarihi;}

	public boolean isTeslimEdildi() {return teslimEdildi;}
	
	public boolean isSiparisIptal() {return siparisIptal;}

	public Depo getHedefDepo() {return hedefDepo;}
	
	public void teslimEt() {
		if(siparisIptal) {
			System.out.println("Bu sipariş iptal edilmis.");
		}else if(teslimEdildi) {
			System.out.println("Bu sipariş zaten teslim edildi.");
		}else if (teslimTarihi.isBefore(LocalDate.now())) {
			System.out.println("Teslim tarihi henüz gelmedi! (" + teslimTarihi + ")");
		}else {
			for(DepoUrun depoUrun : siparisUrunleri) {
				if(!depoUrun.getUrun().satisaUygun()) {
					System.out.println("Siparis edilen urunler arasında SKT'si gecen urun var! Siparis iptal edildi!");
					siparisIptal = true;
					return;
				}			
			}
			for(DepoUrun depoUrun : siparisUrunleri) {
				hedefDepo.addUrun(depoUrun);
				siparisTutari = siparisTutari + depoUrun.getUrun().netFiyat()*depoUrun.getAdet();
			}
			teslimEdildi = true;
			System.out.println("Sipariş " + siparisNo + " teslim edildi ve depoya eklendi.");
		}
	}
}
