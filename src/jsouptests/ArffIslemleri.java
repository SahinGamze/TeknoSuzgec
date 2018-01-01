package jsouptests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ArffIslemleri {

	public static void NegPosKokHalineGetir(String dosyaAdi, String yeniDosyaAdi) throws IOException {
		// Yorumlardaki tırnak işaretleri temizlendi
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			String str, newStr = null;
			int satirSayisi = 1;
			String yeniSatir = null, hepsi;
			hepsi = "@RELATION degerlendirme\n@ATTRIBUTE text  String\n@ATTRIBUTE degerlendirme   {neg,pos,nm}\n@Data\n";
			String sonKarakter, degerlendirme = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));

			while ((str = in.readLine()) != null) {

				if (satirSayisi > 6) {
					System.out.println((satirSayisi - 4) + ". " + str);

					// Satır sonundaki karakter kontrol ediliyor ve ona göre
					// değerlendirme seçiliyor.
					sonKarakter = str.substring(str.length() - 1);
					if (sonKarakter.equals("g")) {
						degerlendirme = "neg";
						// Yorum aradan çıkartılıyor
						newStr = str.substring(1, str.length() - 4);
						// Yorum temizleniyor
						newStr = newStr.replaceAll("\'", " ");
						newStr = newStr.replaceAll("\"", " ");
						newStr = Zemberek2.KokHalineGetirOlumsuzlukEkiyle(newStr);

					} else if (sonKarakter.equals("m")) {
						degerlendirme = "nm";
						// Yorum aradan çıkartılıyor
						newStr = str.substring(1, str.length() - 3);
						// Yorum temizleniyor
						newStr = newStr.replaceAll("\'", " ");
						newStr = newStr.replaceAll("\"", " ");
						newStr = Zemberek2.KokHalineGetirOlumsuzlukEkiyle(newStr);

					} else if (sonKarakter.equals("s")) {
						degerlendirme = "pos";
						// Yorum aradan çıkartılıyor
						newStr = str.substring(1, str.length() - 4);
						// Yorum temizleniyor
						newStr = newStr.replaceAll("\'", " ");
						newStr = newStr.replaceAll("\"", " ");
						newStr = Zemberek2.KokHalineGetirOlumsuzlukEkiyle(newStr);

					}
					yeniSatir = "'" + newStr + "'" + "," + degerlendirme;
				}

				hepsi = hepsi + "\n" + yeniSatir;
				satirSayisi += 1;
			}
			in.close();
			Degerlendir.arffYaz(hepsi, yeniDosyaAdi);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void firmaKokHalineGetir(String dosyaAdi, String yeniDosyaAdi) throws IOException {
		// Yorumlardaki tırnak işaretleri temizlendi
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			String str, newStr = null;
			int satirSayisi = 1;
			String yeniSatir = null, hepsi;
			hepsi = "@RELATION firma\n@ATTRIBUTE text  String\n@ATTRIBUTE firma   {bimeks,istBilisim,mediaMarkt,teknosa,vatanBilgisayar,yok}\n@Data\n";
			String sonKarakter, degerlendirme = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));

			while ((str = in.readLine()) != null) {

				if (satirSayisi > 5) {
					System.out.println((satirSayisi - 4) + ". " + str);

					// Satır sonundaki karakter kontrol ediliyor ve ona göre
					// değerlendirme seçiliyor.
					sonKarakter = str.substring(str.length() - 1);
					if (sonKarakter.equals("s")) {
						degerlendirme = "bimeks";
					} else if (sonKarakter.equals("m")) {
						degerlendirme = "istBilisim";
					} else if (sonKarakter.equals("t")) {
						degerlendirme = "mediaMarkt";
					} else if (sonKarakter.equals("a")) {
						degerlendirme = "teknosa";
					} else if (sonKarakter.equals("r")) {
						degerlendirme = "vatanBilgisayar";
					} else if (sonKarakter.equals("k")) {
						degerlendirme = "yok";
					}
					
					// Yorum aradan çıkartılıyor
					newStr = str.substring(1, str.length() - (degerlendirme.length() + 1));
					// Yorum temizleniyor
					newStr = newStr.replaceAll("\'", " ");
					newStr = newStr.replaceAll("\"", " ");
					//Yorum kök haline dönüştürülüyor.
					newStr = Zemberek2.KokHalineGetir(newStr);
					
					yeniSatir = "'" + newStr + "'" + "," + degerlendirme;
				}

				hepsi = hepsi + "\n" + yeniSatir;
				satirSayisi += 1;
			}
			in.close();
			Degerlendir.arffYaz(hepsi, yeniDosyaAdi);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void konuKokHalineGetir(String dosyaAdi, String yeniDosyaAdi) throws IOException {
		// Yorumlardaki tırnak işaretleri temizlendi
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			String str, newStr = null;
			int satirSayisi = 1;
			String yeniSatir = null, hepsi;
			hepsi = "@RELATION konu\n@ATTRIBUTE text  String\n@ATTRIBUTEkonu   {fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yok}\n@Data\n";
			String sonKarakter, degerlendirme = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));

			while ((str = in.readLine()) != null) {

				if (satirSayisi > 4) {
					System.out.println((satirSayisi - 4) + ". " + str);

					// Satır sonundaki karakter kontrol ediliyor ve ona göre
					// değerlendirme seçiliyor.
					sonKarakter = str.substring(str.length() - 1);
					
					if (sonKarakter.equals("t")) {
						degerlendirme = "fiyat";
					} else if (sonKarakter.equals("o")) {
						degerlendirme = "kargo";

					} else if (sonKarakter.equals("u")) {
						degerlendirme = "urun";

					} else if (sonKarakter.equals("h")) {
						degerlendirme = "ssh";

					} else if (sonKarakter.equals("l")) {
						degerlendirme = "personel";

					} else if (sonKarakter.equals("a")) {
						degerlendirme = "magaza";

					} else if (sonKarakter.equals("m")) {
						degerlendirme = "reklam";

					} else if (sonKarakter.equals("i")) {
						degerlendirme = "websitesi";

					} else {
						degerlendirme = "yok";
					}
			
					// Yorum aradan çıkartılıyor
					newStr = str.substring(1, str.length() - (degerlendirme.length() + 1));
					// Yorum temizleniyor
					newStr = newStr.replaceAll("\'", " ");
					newStr = newStr.replaceAll("\"", " ");
					//Yorum kök haline dönüştürülüyor
					newStr = Zemberek2.KokHalineGetir(newStr);
					
					yeniSatir = "'" + newStr + "'" + "," + degerlendirme;
					System.out.println("YENİ: " + yeniSatir);

				}

				hepsi = hepsi + "\n" + yeniSatir;
				satirSayisi += 1;
			}
			in.close();
			Degerlendir.arffYaz(hepsi, yeniDosyaAdi);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void FirmaDuzelt(String dosyaAdi, String yeniDosyaAdi) throws IOException {
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			String str;
			int satirSayisi = 1;
			String hepsi;
			hepsi = "@RELATION firma\n@ATTRIBUTE text  String\n@ATTRIBUTE firma   {bimeks,istBilisim,mediaMarkt,teknosa,vatanBilgisayar,yok}\n@Data\n";
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			String firmalar, yorumKismi;

			while ((str = in.readLine()) != null) {

				if (satirSayisi > 9) {
					firmalar = str.substring(str.length() - 9);
					yorumKismi = str.substring(0, str.length() - 9);
					System.out.println(yorumKismi);
					System.out.println(firmalar);
					System.out.println("bimeks:" + firmalar.charAt(0));
					System.out.println("istBilisim:" + firmalar.charAt(2));
					System.out.println("MediaMarkt:" + firmalar.charAt(4));
					System.out.println("Teknosa:" + firmalar.charAt(6));
					System.out.println("VatanBilgisayar:" + firmalar.charAt(8));
					System.out.println("Yok:" + !firmalar.contains("1"));

					if (firmalar.charAt(0) == '1')
						firmalar = "bimeks";
					else if (firmalar.charAt(2) == '1')
						firmalar = "istBilisim";
					else if (firmalar.charAt(4) == '1')
						firmalar = "mediaMarkt";
					else if (firmalar.charAt(6) == '1')
						firmalar = "teknosa";
					else if (firmalar.charAt(8) == '1')
						firmalar = "vatanBilgisayar";
					else
						firmalar = "yok";
					System.out.println("Sonuç: " + firmalar);
					str = yorumKismi + firmalar;
				}

				hepsi = hepsi + "\n" + str;
				satirSayisi += 1;
			}
			in.close();
			Degerlendir.arffYaz(hepsi, yeniDosyaAdi);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void KonuDuzelt(String dosyaAdi, String yeniDosyaAdi) throws IOException {
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			String str;
			int satirSayisi = 1;
			String hepsi;
			hepsi = "@RELATION konu\n@ATTRIBUTE text  String\n@ATTRIBUTE konu   {fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yok}\n@Data\n";
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			String konular, yorumKismi;

			while ((str = in.readLine()) != null) {

				if (satirSayisi > 12) {
					konular = str.substring(str.length() - 15);
					yorumKismi = str.substring(0, str.length() - 15);
					System.out.println(yorumKismi);
					System.out.println(konular);

					if (konular.charAt(0) == '1')
						konular = "fiyat";
					else if (konular.charAt(2) == '1')
						konular = "kargo";
					else if (konular.charAt(4) == '1')
						konular = "urun";
					else if (konular.charAt(6) == '1')
						konular = "ssh";
					else if (konular.charAt(8) == '1')
						konular = "personel";
					else if (konular.charAt(10) == '1')
						konular = "magaza";
					else if (konular.charAt(12) == '1')
						konular = "reklam";
					else if (konular.charAt(14) == '1')
						konular = "websitesi";
					else
						konular = "yok";

					System.out.println("Sonuç: " + konular);
					str = yorumKismi + konular;
				}

				hepsi = hepsi + "\n" + str;
				satirSayisi += 1;
			}
			in.close();
			Degerlendir.arffYaz(hepsi, yeniDosyaAdi);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void NegPosEncodingDuzelt(String dosyaAdi, String yeniDosyaAdi) throws IOException {
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			String str;
			int satirSayisi = 1;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));

			while ((str = in.readLine()) != null) {
				if (satirSayisi > 2) {
					System.out.println(str.toString());
				}
				
				satirSayisi += 1;
				if (satirSayisi == 10)
					break;
			}
			in.close();
			// Degerlendir.arffYaz(hepsi,yeniDosyaAdi);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

	}
}