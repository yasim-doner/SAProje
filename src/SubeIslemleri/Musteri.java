package SubeIslemleri;
import urun.Urun;
import java.util.HashMap;
import java.util.Map;

public class Musteri {
    private Map<Urun, Integer> sepet;

    public Musteri() {
        this.sepet = new HashMap<>();
    }

    // Sepete ürün ekle
    public void sepeteUrunEkle(Urun urun, int adet) {
        sepet.put(urun, sepet.getOrDefault(urun, 0) + adet);
    }

    public Map<Urun, Integer> getSepet() {
        return sepet;
    }

}
