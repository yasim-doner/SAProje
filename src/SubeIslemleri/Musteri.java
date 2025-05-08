package SubeIslemleri;
import urun.Urun;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Musteri implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5660656872626941504L;
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
