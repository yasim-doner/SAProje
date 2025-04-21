package personel;

public class test {
	 public static void main(String[] args) {
	        PersonelManager manager = new PersonelManager();

	        Personel p1 = new Personel(1, "Ali", "Kaya", "Kasiyer", 8500, "Magaza1");
	        Personel p2 = new Personel(2, "Ayşe", "Demir", "Müdür", 15000, "Depo1");

	        manager.personelEkle(p1);
	        manager.personelEkle(p2);
	        manager.terfiEt(1, 9500, "Kasa Sorumlusu");
	        manager.departmanAta(2, "Magaza2");

	        manager.personelListesiYazdir();
	 }
}