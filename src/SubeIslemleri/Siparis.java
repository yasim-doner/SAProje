package SubeIslemleri;

import urun.Urun;
import java.util.Map;

public class Siparis {
    private Map<Urun, Integer> siparisler; // Ürün adı - adet
    private String magazaAdi;

    public Siparis(Map<Urun, Integer> siparisler, String magazaAdi) {
        this.siparisler = siparisler;
        this.magazaAdi = magazaAdi;
    }

    // Getter'lar
    public Map<Urun, Integer> getSiparisler() {
        return siparisler;
    }

    public String getMagazaAdi() {
        return magazaAdi;
    }
}
