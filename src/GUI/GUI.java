package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import urun.*;
import personal.*;
import siparisVeLojistik.*;
import SubeIslemleri.*;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;



public class GUI {
	
	private static boolean yetkiAlindi = false;
	
	public static void main(String[] args) {
		
		Sistem sistem = new Sistem();
		// Ana pencere
		JFrame frame = new JFrame("Uygulama Ana Penceresi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        // Menü çubuğu
        JMenuBar menuBar = new JMenuBar();

        // "Manage" ana menüsü
        JMenu manageMenu = new JMenu("Manage");

        // === "Ürün" Alt Menüsü ===
        JMenu urunMenu = new JMenu("Urun Islemleri");
        JMenu subeMenu = new JMenu("Sube Islemleri");
        JMenu personelMenu = new JMenu("Personel Islemleri");
        JMenu depoMenu = new JMenu("Depo Islemleri");

        // "Ürün"ün alt seçenekleri
        JMenuItem urunEkleItem = new JMenuItem("Urun Ekle");
        JMenuItem kampanyaEkleItem = new JMenuItem("Urune Kampanya Yap");
        
        // "Depo"nun alt seçenekleri
        JMenuItem depoEkleItem = new JMenuItem("Depo Ekle");
        JMenuItem depoSiparisItem = new JMenuItem("Depoya Urun Siparis Et");
        

        // "Ürün" menüsüne alt seçenekleri ekle
        urunMenu.add(urunEkleItem);
        urunMenu.add(kampanyaEkleItem);
        
        // "Depo" menüsüne alt seçenekleri ekle
        depoMenu.add(depoEkleItem);
        depoMenu.add(depoSiparisItem);
        
        // Başlangıçta hepsi devre dışı
        urunMenu.setEnabled(false);
        subeMenu.setEnabled(false);
        personelMenu.setEnabled(false);
        depoMenu.setEnabled(false);

        // "Manage" menüsüne "Ürün" (alt menülü) ve "Şube"yi ekle
        manageMenu.add(urunMenu);
        manageMenu.add(subeMenu);
        manageMenu.add(personelMenu);
        manageMenu.add(depoMenu);

        // Menü çubuğuna "Manage"i ekle
        menuBar.add(manageMenu);

        // Menü çubuğunu çerçeveye ekle
        frame.setJMenuBar(menuBar);

     // === Şifre kontrolü ===
        manageMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                if (!yetkiAlindi) {
                    String girilenSifre = JOptionPane.showInputDialog(frame, "Şifreyi giriniz:");
                    if ("1234".equals(girilenSifre)) { // Şifre buradan kontrol edilir
                        yetkiAlindi = true;
                        urunMenu.setEnabled(true);
                        subeMenu.setEnabled(true);
                        personelMenu.setEnabled(true);
                        depoMenu.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Hatalı şifre!");
                        // Menüyü kapatS
                        manageMenu.setPopupMenuVisible(false);
                    }
                }
            }

			@Override
			public void menuDeselected(MenuEvent e) {}

			@Override
			public void menuCanceled(MenuEvent e) {}
        });
        
        urunEkleItem.addActionListener(e -> {
            JFrame urunEklePenceresi = new JFrame("Ürün Ekle");
            urunEklePenceresi.setSize(300, 250);
            urunEklePenceresi.setResizable(false);
            urunEklePenceresi.setLocationRelativeTo(frame);
            urunEklePenceresi.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel barkotLabel = new JLabel("Barkot:");
            JTextField barkotField = new JTextField(12);

            JLabel isimLabel = new JLabel("İsim:");
            JTextField isimField = new JTextField(12);

            JLabel brutFiyatLabel = new JLabel("Brüt Fiyat:");
            JTextField brutFiyatField = new JTextField(12);

            JLabel alisFiyatLabel = new JLabel("Alış Fiyatı:");
            JTextField alisFiyatField = new JTextField(12);

            JLabel sktLabel = new JLabel("SK Tarihi:");

            // === JDatePicker bileşeni oluştur ===
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Bugün");
            p.put("text.month", "Ay");
            p.put("text.year", "Yıl");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            JButton kaydetButton = new JButton("Kaydet");

            int row = 0;

            gbc.gridx = 0; gbc.gridy = row;
            urunEklePenceresi.add(barkotLabel, gbc);
            gbc.gridx = 1;
            urunEklePenceresi.add(barkotField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            urunEklePenceresi.add(isimLabel, gbc);
            gbc.gridx = 1;
            urunEklePenceresi.add(isimField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            urunEklePenceresi.add(brutFiyatLabel, gbc);
            gbc.gridx = 1;
            urunEklePenceresi.add(brutFiyatField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            urunEklePenceresi.add(alisFiyatLabel, gbc);
            gbc.gridx = 1;
            urunEklePenceresi.add(alisFiyatField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            urunEklePenceresi.add(sktLabel, gbc);
            gbc.gridx = 1;
            urunEklePenceresi.add(datePicker, gbc);

            gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
            urunEklePenceresi.add(kaydetButton, gbc);

            kaydetButton.addActionListener(evt -> {
                try {
                	Integer barkot = Integer.parseInt(barkotField.getText());
                	String isim = isimField.getText();
                	double brut = Double.parseDouble(brutFiyatField.getText());
                	double alis = Double.parseDouble(alisFiyatField.getText());
                	Object selectedDate = datePicker.getModel().getValue();
                
                	if (isim.isEmpty() || selectedDate == null) {
                		JOptionPane.showMessageDialog(urunEklePenceresi, "Lütfen tüm alanları doldurun!");
                	} else {
                		LocalDate skTarihi = ((Date) selectedDate).toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
//                		Urun yeni_urun = new Urun(barkot,isim,brut,alis,skTarihi);
//                		sistem.urunEkle(yeni_urun);
                		JOptionPane.showMessageDialog(urunEklePenceresi, "Ürün kaydedildi:\n" +
                				"Barkot: " + barkot + "\nİsim: " + isim + "\nBrüt Fiyat: " + brut +
                				"\nAlış Fiyatı: " + alis + "\nSKT: " + skTarihi.toString());
                	
                		urunEklePenceresi.dispose();
                	}
                } catch (DuplicateInfoException ex) {
                	JOptionPane.showMessageDialog(urunEklePenceresi, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                	JOptionPane.showMessageDialog(urunEklePenceresi, "Lütfen geçerli bir sayısal değer giriniz!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            	
            });
            urunEklePenceresi.setVisible(true);
        });
        
        kampanyaEkleItem.addActionListener(e -> {
            JFrame kampanyaEklePenceresi = new JFrame("Kampanya Ekle");
            kampanyaEklePenceresi.setSize(350, 250);
            kampanyaEklePenceresi.setResizable(false);
            kampanyaEklePenceresi.setLocationRelativeTo(frame);
            kampanyaEklePenceresi.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Mevcut ürünler listesi (örnek)
            JLabel urunSecLabel = new JLabel("Ürün Seç:");
            JComboBox<String> urunComboBox = new JComboBox<>();
            for (Urun urun : sistem.getUrunler()) {
				urunComboBox.addItem(urun.getIsim());
			}
            
            JLabel indirimLabel = new JLabel("İndirim (%):");
            JTextField indirimField = new JTextField(10);

            JLabel tarihLabel = new JLabel("Son Tarih:");
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Bugün");
            p.put("text.month", "Ay");
            p.put("text.year", "Yıl");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            JButton kaydetButton = new JButton("Kaydet");

            int row = 0;

            gbc.gridx = 0; gbc.gridy = row;
            kampanyaEklePenceresi.add(urunSecLabel, gbc);
            gbc.gridx = 1;
            kampanyaEklePenceresi.add(urunComboBox, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            kampanyaEklePenceresi.add(indirimLabel, gbc);
            gbc.gridx = 1;
            kampanyaEklePenceresi.add(indirimField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            kampanyaEklePenceresi.add(tarihLabel, gbc);
            gbc.gridx = 1;
            kampanyaEklePenceresi.add(datePicker, gbc);

            gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
            kampanyaEklePenceresi.add(kaydetButton, gbc);

            kaydetButton.addActionListener(evt -> {
                try {
                	boolean flag = false;
                	Urun selectedUrun = null;
                    String secilenUrun = (String) urunComboBox.getSelectedItem();
                    int indirim = Integer.parseInt(indirimField.getText());
                    Object selectedDate = datePicker.getModel().getValue();

                    if (selectedDate == null) {
                        JOptionPane.showMessageDialog(kampanyaEklePenceresi, "Lütfen tarih seçin!");
                    } else {
                        LocalDate sonTarih = ((java.util.Date) selectedDate).toInstant()
                                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                        for (Urun urun : sistem.getUrunler()) {
							if(urun.getIsim().contains(secilenUrun)) {
								flag = true;
								selectedUrun = urun;
							}
						}
                        if(flag) {
                        	Kampanya kampanya = new Kampanya(indirim, selectedUrun, sonTarih);
                        	selectedUrun.setKampanya(kampanya);
                        	System.out.println(selectedUrun.getIsim());
                        	JOptionPane.showMessageDialog(kampanyaEklePenceresi,
                                "Kampanya Kaydedildi!\nÜrün: " + secilenUrun +
                                "\nİndirim: " + indirim + "%\nSon Tarih: " + kampanya.getSonTarih() + "\nNet Fiyat: " + selectedUrun.netFiyat());
                        	kampanyaEklePenceresi.dispose();
                        }
                        
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(kampanyaEklePenceresi, "Lütfen geçerli bir indirim oranı girin.");
                }
            });

            kampanyaEklePenceresi.setVisible(true);
        });
        
        depoEkleItem.addActionListener(e -> {
            JFrame depoEklePenceresi = new JFrame("Depo Ekle");
            depoEklePenceresi.setSize(350, 250);
            depoEklePenceresi.setResizable(false);
            depoEklePenceresi.setLocationRelativeTo(null);
            depoEklePenceresi.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel depoAdiLabel = new JLabel("Depo Adı:");
            JTextField depoAdiField = new JTextField(12);

            JLabel konumLabel = new JLabel("Depo Konumu:");
            JTextField konumField = new JTextField(12);

            JLabel kapasiteLabel = new JLabel("Depo Kapasitesi:");
            JTextField kapasiteField = new JTextField(12);

            JLabel urunKapasiteLabel = new JLabel("Ürün Başına Kapasite:");
            JTextField urunKapasiteField = new JTextField(12);

            JButton kaydetButton = new JButton("Kaydet");

            int row = 0;

            gbc.gridx = 0; gbc.gridy = row;
            depoEklePenceresi.add(depoAdiLabel, gbc);
            gbc.gridx = 1;
            depoEklePenceresi.add(depoAdiField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            depoEklePenceresi.add(konumLabel, gbc);
            gbc.gridx = 1;
            depoEklePenceresi.add(konumField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            depoEklePenceresi.add(kapasiteLabel, gbc);
            gbc.gridx = 1;
            depoEklePenceresi.add(kapasiteField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            depoEklePenceresi.add(urunKapasiteLabel, gbc);
            gbc.gridx = 1;
            depoEklePenceresi.add(urunKapasiteField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
            depoEklePenceresi.add(kaydetButton, gbc);

            kaydetButton.addActionListener(evt -> {
                String depoAdi = depoAdiField.getText().trim();
                String konum = konumField.getText().trim();
                String kapasiteStr = kapasiteField.getText().trim();
                String urunKapasiteStr = urunKapasiteField.getText().trim();

                if (depoAdi.isEmpty() || konum.isEmpty() || kapasiteStr.isEmpty() || urunKapasiteStr.isEmpty()) {
                    JOptionPane.showMessageDialog(depoEklePenceresi, "Lütfen tüm alanları doldurun!");
                    return;
                }

                try {
                    int kapasite = Integer.parseInt(kapasiteStr);
                    int urunKapasite = Integer.parseInt(urunKapasiteStr);
                    
                    Depo depo = new Depo(konum, depoAdi, kapasite, urunKapasite);
                    sistem.depoEkle(depo);
                    
                    JOptionPane.showMessageDialog(depoEklePenceresi,
                            "Depo kaydedildi:\nAd: " + depoAdi + "\nKonum: " + konum +
                                    "\nKapasite: " + kapasite + "\nÜrün Başına: " + urunKapasite);

                    depoEklePenceresi.dispose();

                }catch (DuplicateInfoException ex) {
                	JOptionPane.showMessageDialog(depoEklePenceresi, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(depoEklePenceresi, "Kapasite alanlarına sadece sayı girin!");
                }
            });

            depoEklePenceresi.setVisible(true);
        });
        
        depoSiparisItem.addActionListener(e -> {
            JFrame siparisPenceresi = new JFrame("Depo Siparişi");
            siparisPenceresi.setSize(500, 500);
            siparisPenceresi.setResizable(false);
            siparisPenceresi.setLocationRelativeTo(null);
            siparisPenceresi.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // === Giriş Bileşenleri ===
            JLabel siparisNoLabel = new JLabel("Sipariş No:");
            JTextField siparisNoField = new JTextField(10);

            JLabel tedarikciLabel = new JLabel("Tedarikçi Adı:");
            JTextField tedarikciField = new JTextField(12);

            JLabel urunlerLabel = new JLabel("Sipariş Ürünleri:");

            // Örnek ürün listesi ve adet tablosu
            String[] columnNames = {"Ürün", "Adet"};
            Object[][] data = new Object[sistem.getUrunler().size()][2];
            for (int i=0; i<sistem.getUrunler().size(); i++) {
				data[i][0] = sistem.getUrunler().get(i);
				data[i][1] = 0;
			}

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1; // Sadece 'Adet' sütunu düzenlenebilir
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return (columnIndex == 1) ? Integer.class : String.class;
                }
            };

            JTable urunTable = new JTable(tableModel);
            JScrollPane urunScroll = new JScrollPane(urunTable);
            urunScroll.setPreferredSize(new Dimension(250, 100));

            JLabel tarihLabel = new JLabel("Teslim Tarihi:");
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Bugün");
            p.put("text.month", "Ay");
            p.put("text.year", "Yıl");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            JLabel hedefDepoLabel = new JLabel("Hedef Depo:");
            JComboBox<String> depoComboBox = new JComboBox<>();
            for (Depo depo : sistem.getDepolar()) {
				depoComboBox.addItem(depo.getDepoAdi());
			}
            

            JButton kaydetButton = new JButton("Kaydet");

            // === Bileşenleri Yerleştir ===
            int row = 0;
            gbc.gridx = 0; gbc.gridy = row;
            siparisPenceresi.add(siparisNoLabel, gbc);
            gbc.gridx = 1;
            siparisPenceresi.add(siparisNoField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            siparisPenceresi.add(tedarikciLabel, gbc);
            gbc.gridx = 1;
            siparisPenceresi.add(tedarikciField, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            siparisPenceresi.add(urunlerLabel, gbc);
            gbc.gridx = 1;
            siparisPenceresi.add(urunScroll, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            siparisPenceresi.add(tarihLabel, gbc);
            gbc.gridx = 1;
            siparisPenceresi.add(datePicker, gbc);

            gbc.gridx = 0; gbc.gridy = ++row;
            siparisPenceresi.add(hedefDepoLabel, gbc);
            gbc.gridx = 1;
            siparisPenceresi.add(depoComboBox, gbc);

            gbc.gridx = 0; gbc.gridy = ++row; gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            siparisPenceresi.add(kaydetButton, gbc);

            // === Kaydet Butonu ===
            kaydetButton.addActionListener(evt -> {
                try {
                    int siparisNo = Integer.parseInt(siparisNoField.getText().trim());
                    String tedarikci = tedarikciField.getText().trim();
                    Object selectedDate = datePicker.getModel().getValue();
                    String hedefDepo = (String) depoComboBox.getSelectedItem();

                    if (tedarikci.isEmpty() || selectedDate == null || hedefDepo == null) {
                        JOptionPane.showMessageDialog(siparisPenceresi, "Lütfen tüm alanları doldurun!");
                        return;
                    }

                    LocalDate teslimTarihi = ((java.util.Date) selectedDate)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    StringBuilder urunBilgileri = new StringBuilder();
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String urun = (String) tableModel.getValueAt(i, 0);
                        int adet = (int) tableModel.getValueAt(i, 1);
                        if (adet > 0) {
                            urunBilgileri.append(urun).append(" (").append(adet).append("), ");
                        }
                    }

                    if (urunBilgileri.length() == 0) {
                        JOptionPane.showMessageDialog(siparisPenceresi, "Lütfen en az bir ürün ve adet girin.");
                        return;
                    }

                    JOptionPane.showMessageDialog(siparisPenceresi,
                        "Sipariş Kaydedildi!\nSipariş No: " + siparisNo +
                        "\nTedarikçi: " + tedarikci +
                        "\nÜrünler: " + urunBilgileri +
                        "\nTeslim Tarihi: " + teslimTarihi +
                        "\nHedef Depo: " + hedefDepo
                    );

                    siparisPenceresi.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(siparisPenceresi, "Sipariş No sadece sayı olmalıdır.");
                }
            });

            siparisPenceresi.setVisible(true);
        });


        

        
        frame.setVisible(true);
    }
}
