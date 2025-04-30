
package SubeIslemleri;

import personel.Personel;
import urun.Urun;
import java.util.ArrayList;
import java.util.Map;

public class Magaza{
    private String magazaAdi;
    private String magazaAdres;
    private int gelenMusteriSayisi;
    private double ciro;
    private Map<Urun, Integer> urunler; // ürün adı - adet
    private ArrayList<Personel> personelListesi;

    public Magaza(String magazaAdi, String magazaAdres, Map<Urun, Integer> urunler, ArrayList<Personel> personelListesi) {
        this.magazaAdi = magazaAdi;
        this.magazaAdres = magazaAdres;
        this.urunler = urunler;
        this.personelListesi = personelListesi;
        this.gelenMusteriSayisi = 0;
        this.ciro = 0.0;
    }

    public int getGelenMusteriSayisi() {
        return gelenMusteriSayisi;
    }

    public void setGelenMusteriSayisi(int gelenMusteriSayisi) {
        this.gelenMusteriSayisi = gelenMusteriSayisi;
    }

    public double getCiro() {
        return ciro;
    }

    public void setCiro(double ciro) {
        this.ciro = ciro;
    }

    public Map<Urun, Integer> getUrunler(){
        return urunler;
    }

    public ArrayList<Personel> getPersonelListesi() {
        return personelListesi;
    }

    public String getMagazaAdi() {
        return magazaAdi;
    }

    public String getMagazaAdres() {
        return magazaAdres;
    }    
}
