package personel;

public class Personel {
    private int id;
    private String ad;
    private String soyad;
    private String gorev;
    private double maas;
    private String departman;
    private int terfiSayisi;

    public Personel(int id, String ad, String soyad, String gorev, double maas, String departman) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.gorev = gorev;
        this.maas = maas;
        this.departman = departman;
        this.terfiSayisi = 0;
    }
    public Personel(int id, String ad, String soyad) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.terfiSayisi = 0;
    }

    // Getter ve  Setter metodları
    public int getId() { return id; }
    public String getAd() { return ad; }
    public String getSoyad() { return soyad; }
    public String getGorev() { return gorev; }
    public double getMaas() { return maas; }
    public String getDepartman() { return departman; }
    public int getTerfiSayisi() { return terfiSayisi; }

    public void setMaas(double maas) { this.maas = maas; }
    public void setDepartman(String departman) { this.departman = departman; }

    // İşlemler
    public void terfiEt(double yeniMaas, String yeniGorev) {
        this.maas = yeniMaas;
        this.gorev = yeniGorev;
        this.terfiSayisi++;
    }

    public void bilgileriYazdir() {
        System.out.println("Personel: " + ad + " " + soyad + ", Görev: " + gorev + ", Maaş: " + maas);
    }
}
