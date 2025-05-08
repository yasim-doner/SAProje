package personel;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonelManager implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1626902419497724621L;
	private ArrayList<Personel> personelListesi;

    public PersonelManager() {
        this.personelListesi = new ArrayList<>();
    }

    // Personel ekleme
    public void personelEkle(Personel p) {
        personelListesi.add(p);
        System.out.println("Yeni personel eklendi: " + p.getAd() + " " + p.getSoyad());
    }

    // Personel çıkarma
    public void personelCikar(int id) {
        Personel silinecek = null;
        for (Personel p : personelListesi) {
            if (p.getId() == id) {
                silinecek = p;
                break;
            }
        }
        if (silinecek != null) {
            personelListesi.remove(silinecek);
            System.out.println("Personel çıkarıldı: " + silinecek.getAd() + " " + silinecek.getSoyad());
        } else {
            System.out.println("ID ile eşleşen personel bulunamadı.");
        }
    }

    // Terfi ettirme
    public void terfiEt(int id, double yeniMaas, String yeniGorev) {
        for (Personel p : personelListesi) {
            if (p.getId() == id) {
                p.terfiEt(yeniMaas, yeniGorev);
                System.out.println(p.getAd() + " " + p.getSoyad() + " terfi etti!");
                return;
            }
        }
        System.out.println("Personel bulunamadı.");
    }

    // Maaş güncelleme
    public void maasGuncelle(int id, double yeniMaas) {
        for (Personel p : personelListesi) {
            if (p.getId() == id) {
                p.setMaas(yeniMaas);
                System.out.println("Maaş güncellendi.");
                return;
            }
        }
        System.out.println("Personel bulunamadı.");
    }

    // Departman (mağaza/depo) atama
    public void departmanAta(int id, String yeniDepartman) {
        for (Personel p : personelListesi) {
            if (p.getId() == id) {
                p.setDepartman(yeniDepartman);
                System.out.println("Yeni departman atandı: " + yeniDepartman);
                return;
            }
        }
        System.out.println("Personel bulunamadı.");
    }

    // Tüm personelleri listeleme
    public void personelListesiYazdir() {
        for (Personel p : personelListesi) {
            p.bilgileriYazdir();
        }
    }
}
