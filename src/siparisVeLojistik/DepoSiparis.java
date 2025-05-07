package siparisVeLojistik;

import java.time.LocalDate;
import java.util.ArrayList;

public class DepoSiparis {
	private int siparisNo;
	private int siparisAdedi;
	private double siparisTutari;
	private String tedarikciAdi;
	private ArrayList<DepoUrun> siparisUrunleri;
	private LocalDate siparisTarihi;
	private LocalDate teslimTarihi;
	private boolean teslimEdildi;
	private boolean siparisIptal;
	private Depo hedefDepo;
	
	public DepoSiparis(int siparisNo, String tedarikciAdi, ArrayList<DepoUrun> siparisUrunleri,LocalDate teslimTarihi, Depo hedefDepo) {
		super();
		this.siparisNo = siparisNo;
		this.siparisTutari = 0;
		this.siparisAdedi = 0;
		this.tedarikciAdi = tedarikciAdi;
		this.siparisUrunleri = siparisUrunleri;
		this.siparisTarihi = LocalDate.now();
		this.teslimTarihi = teslimTarihi;
		this.teslimEdildi = false;
		this.siparisIptal = false;
		this.hedefDepo = hedefDepo;
		setSiparisAdedi();
	}

	public ArrayList<DepoUrun> getSiparisUrunleri() {return siparisUrunleri;}

	public void setSiparisUrunleri(ArrayList<DepoUrun> siparisUrunleri) {this.siparisUrunleri = siparisUrunleri;}
	
	public int getSiparisNo() {return siparisNo;}
	
	public int getSiparisAdedi() {return siparisAdedi;}
	
	public void setSiparisAdedi() {
		if(siparisUrunleri.isEmpty()) {
			siparisIptal = true;
			return;
		}
		for(DepoUrun depoUrun : siparisUrunleri) {
			siparisAdedi = siparisAdedi + depoUrun.getAdet();
		}
	}

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
		}else if(teslimTarihi.isBefore(LocalDate.now())) {
			System.out.println("Teslim tarihi henüz gelmedi! (" + teslimTarihi + ")");
		}else if(siparisAdedi > (hedefDepo.getDepoKapasitesi()-hedefDepo.getToplamUrunMiktari())) {
			System.out.println("Deponun kapasitesi siparis icin yeterli degil! Lutfen baska bir depo seciniz veya siparis adedinin azaltiniz!");
			siparisIptal = true;
		}else {
			for(DepoUrun depoUrun : siparisUrunleri) {
				if(!depoUrun.getUrun().satisaUygun()) {
					System.out.println("Siparis edilen urunler arasında SKT'si gecen urun var! Siparis iptal edildi!");
					siparisIptal = true;
					return;
				}			
			}
			for(DepoUrun depoUrun : siparisUrunleri) {
				if(hedefDepo.addUrun(depoUrun)) {
					siparisTutari = siparisTutari + depoUrun.getUrun().netFiyat()*depoUrun.getAdet();
					teslimEdildi = true;
					System.out.println("Sipariş " + siparisNo + " teslim edildi ve depoya eklendi.");
				}else {
					siparisIptal = true;
				}
			}	
		}
	}
	
	public void listSiparisUrunleri() {
		if (siparisUrunleri.isEmpty()) {
            System.out.println("Bu sipariste urun bulunmamaktadır.");
        } else {
            System.out.println(siparisNo +" no'lu siparisteki urunler: ");
            for (DepoUrun urun : siparisUrunleri) {
                System.out.println(urun.getUrun().getBarkod() + " - " + urun.getUrun().getIsim() + " - Sipariste " + urun.getAdet() + " adet bulunmaktadir.");
            }
        }
	}
	
	public void listSiparisBilgileri() {
		System.out.println();
		System.out.println("Siparis No: " + siparisNo);
		System.out.println("Tedarikci Adi: " + tedarikciAdi);
		System.out.println("Siparis Tarihi: " + siparisTarihi);
		System.out.println("Teslim Tarihi: " + teslimTarihi);
		System.out.println("Teslim Edildi mi: " + (teslimEdildi ? "Evet" : "Hayır"));
		System.out.println("Siparis Iptal Edildi mi: " + (siparisIptal ? "Evet" : "Hayır"));
		System.out.println("Siparis Tutari: " + siparisTutari);
		System.out.println("Siparis Edilen Toplam Urun Adedi: " + siparisAdedi);
		System.out.println();
		listSiparisUrunleri();
		System.out.println();
	}
}
