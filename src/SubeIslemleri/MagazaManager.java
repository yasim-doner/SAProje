package SubeIslemleri;


import personel.Personel;
import urun.Urun;
import java.util.HashMap;
import java.util.Map;



public class MagazaManager {
    private Magaza magaza;

    public MagazaManager(Magaza magaza) {
        this.magaza = magaza;
    }

    public void gelenMusteriArttir(int musteriSayisi) {
        magaza.setGelenMusteriSayisi(magaza.getGelenMusteriSayisi() + musteriSayisi);
    }

    public void ciroGuncelle(double satisTutari) {
        magaza.setCiro(magaza.getCiro() + satisTutari);
    }

    public void envanterGoruntule() {
        for (Map.Entry<Urun, Integer> entry : magaza.getUrunler().entrySet()) {
            System.out.println("Ürün: " + entry.getKey() + " - Stok: " + entry.getValue());
        }
    }

    public void personelListele() {
        for (Personel personel : magaza.getPersonelListesi()) {
            System.out.println(personel.getAdSoyad() + " - Gorev: " + personel.getGorev());
        }
    }
    public void siparisOlustur(Map<Urun, Integer> istenenUrunler) {
        Map<Urun, Integer> gecerliSiparisler = new HashMap<>();

        for (Map.Entry<Urun, Integer> entry : istenenUrunler.entrySet()) {
            Urun urun = entry.getKey();
            int adet = entry.getValue();

            if (!magaza.getUrunler().containsKey(urun)) {
                System.out.println("Ürün bulunamadı: " + urun.getIsim());
                continue; // Ürün yoksa atla
            }
            
            
            if(magaza.getDepo().magazayaTedarikEt(magaza, urun, adet)){
                gecerliSiparisler.put(urun, adet);
                magaza.getUrunler().put(urun, adet);
            }
        }

        if (gecerliSiparisler.isEmpty()) {
            System.out.println("Hiç geçerli ürün yok, sipariş oluşturulamadı.");
            return;
        }
        
        System.out.println("Sipariş oluşturuldu:");
        for (Map.Entry<Urun, Integer> entry : gecerliSiparisler.entrySet()) {
            System.out.println("- " + entry.getKey().getIsim() + " : " + entry.getValue() + " adet");
        }
    }

    
    public void musteriSepettenSatinAl(Musteri musteri) {
        Map<Urun, Integer> sepet = musteri.getSepet();

        for (Map.Entry<Urun, Integer> entry : sepet.entrySet()) {
            Urun urun = entry.getKey();
            int istenenAdet = entry.getValue();

            // Mağaza stoğunda ürün ismine göre arama yapacağız
            Urun stokUrun = null;
            for (Urun mUrun : magaza.getUrunler().keySet()) {
                if (mUrun.getIsim().equals(urun.getIsim())) {
                    stokUrun = mUrun;
                    break;
                }
            }

            if (stokUrun == null) {
                System.out.println(urun.getIsim() + " mağazada bulunamadı. Satın alma iptal edildi.");
                continue;
            }

            int mevcutAdet = magaza.getUrunler().get(stokUrun);

            if (mevcutAdet < istenenAdet) {
                System.out.println(urun.getIsim() + " için yeterli stok yok. Satın alma iptal edildi.");
                continue;
            }

            // Stoktan düş
            magaza.getUrunler().put(stokUrun, mevcutAdet - istenenAdet);

            // Ciroyu artır (Urun'dan brüt fiyat çekiyoruz)
            magaza.setCiro(magaza.getCiro()+ stokUrun.netFiyat() * istenenAdet);
            magaza.setGelenMusteriSayisi(magaza.getGelenMusteriSayisi()+ 1);

            System.out.println("Musteri" + " " + istenenAdet + " adet " + stokUrun.getIsim() + " satın aldı.");
        }

        System.out.println("Güncel ciro: " + magaza.getCiro() + " TL");
    }
}
