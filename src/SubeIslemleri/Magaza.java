
package SubeIslemleri;

import personel.Personel;
import urun.Urun;
import java.util.ArrayList;
import java.util.Map;
import siparisVeLojistik.Depo;

public class Magaza{
    private String magazaAdi;
    private String magazaAdres;
    private int gelenMusteriSayisi;
    private double ciro;
    private Map<Urun, Integer> urunler; // ürün adı - adet
    private ArrayList<Personel> personelListesi;
    private Depo depo;

    public Magaza(String magazaAdi, String magazaAdres, Depo depo) {
        this.magazaAdi = magazaAdi;
        this.magazaAdres = magazaAdres;
        this.urunler = null;
        this.personelListesi = null;
        this.gelenMusteriSayisi = 0;
        this.ciro = 0.0;
        this.depo = depo;
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

    public Depo getDepo() {
        return depo;
    }
    
}
