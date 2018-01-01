package jsouptests;

import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VeriToplaTestEt 
{
	private String tumYorumlar;
	private String negPosArffBaslik = "@RELATION degerlendirme\n@ATTRIBUTE text  String\n@ATTRIBUTE degerlendirmexox   {neg,pos,nm}\n@Data\n";
	private String konuArffBaslik = "@RELATION konu\n@ATTRIBUTE text  String\n@ATTRIBUTE konuxox   {fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yok}\n@Data\n";

	
	public VeriToplaTestEt(String firmaAdi,boolean testArffleriSaklansinMi)
	{
		tumYorumlar = "";
		try 
		{
			// istisna -> ist bilisim icin..
			if(firmaAdi.equals("istanbul bilişim"))
			{
				tumYorumlar = eksiSozluk("istanbul bilişim a.ş.");
			}
			else
			{
				tumYorumlar = eksiSozluk(firmaAdi);
			}
			
			// uludag sozluk linkinde bosluk yerine "-" olmali 
			if(firmaAdi.contains(" "))
			{
				tumYorumlar += "\n" + uludag(firmaAdi.replace(" ","-"));
			}
			else
			{
				tumYorumlar +="\n" + uludag(firmaAdi);
			}
			
			negPosArffBaslik+=tumYorumlar;
			konuArffBaslik+=tumYorumlar;
			
			// Tum yorumlar etiketlendi, test arff dosyasi haline getiriliyor. -> NegPos icin.
			dosyayaYaz(negPosArffBaslik,firmaAdi+".arff");
			
			// Tum yorumlar etiketlendi, test arff dosyasi haline getiriliyor. -> Konu icin.
		    dosyayaYaz(konuArffBaslik,firmaAdi+"Konu.arff");
			
			TestClass a = new TestClass();
			try 
			{
				// Toplanan veriler negpos testine sokuluyor
				a.NegPosTestEt(firmaAdi+".arff",testArffleriSaklansinMi);
				System.out.println("Negatif: "+a.getNeg());
				System.out.println("Pozitif: "+a.getPos());
				System.out.println("Nm: "+a.getNm());
				
				// Toplanan veriler konu testine sokuluyor
				a.KonuTestEt(firmaAdi+"Konu.arff", testArffleriSaklansinMi);
				
				
				// Veritabanina kayit.
				sonuclariKaydet(firmaAdi,
						String.valueOf(a.getNeg()),
						String.valueOf(a.getPos()) ,
						String.valueOf(a.getNm()),
						String.valueOf(a.getFiyat()) ,
						String.valueOf(a.getKargo()) ,
						String.valueOf(a.getUrun()),
						String.valueOf(a.getSsh()),
						String.valueOf(a.getPersonel()),
						String.valueOf(a.getMagaza()),
						String.valueOf(a.getReklam()),
						String.valueOf(a.getWebsitesi()),
						String.valueOf(a.getYokKonu()));
				
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String eksiSozluk(String firmaAdi) throws IOException
	{
		//Arff icin ? etiketi ekliyor.
		String sonSayfa="";
		String toplananlar = "";
		// ?p=2 ekle
		//firmaLink ='e kadar, = dahil.
		Document doc3= Jsoup.connect("https://eksisozluk.com/"+firmaAdi).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").get();
		String firmaLink = doc3.location();
		
		sonSayfa = doc3.getElementsByClass("pager").first().attr("data-pagecount");
		firmaLink = firmaLink + "?p=";
		String gelen;
		System.out.println("Ekşisözlük !\n"+firmaLink);
		int i = 1;
		try
		{
			Elements elements;
			do
			{
				Document doc = Jsoup.connect(firmaLink+i).timeout(0).userAgent("Chrome").get();
			    elements = doc.getElementsByClass("content");
				if(elements != null)
					for(Element element : elements)
					{
						gelen = element.text();
						//Yorum temizleniyor
						gelen = gelen.replaceAll("\'", " ");
						gelen = gelen.replaceAll("\"", " ");
						
						//Etiketlendi.
						gelen = "'"+gelen+"',?";
						
						toplananlar = toplananlar + gelen +"\n";
						
						//System.out.println(element.text());
					}
				//Diğer sayfa için i arttırıldı.
				i++;
				
			}while(i <= Integer.valueOf(sonSayfa));
			
			return toplananlar;
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			
			return toplananlar;
		}
	}

	public static String uludag(String firmaAdi)
	{
		// Arff icin ? ile etiketliyor.
		String toplananlar = "";
		// Boşluk için - kullan
		System.out.println("Uludağ Sözlük "+firmaAdi+" !");
		
		int i = 1;
		try
		{		
			Elements elementsSayfa,veriler;
			Document doc;
			String gelen;
			String sonrakiSayfa="»";
			
				do 
				{
					doc = Jsoup.connect("http://www.uludagsozluk.com/k/"+firmaAdi+"/"+i).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").get();
				    elementsSayfa = doc.select("#main > div.entry-block.clearfix > div.pagination > ul > li");
				    veriler = doc.getElementsByClass("entry-p");
				    //System.out.println(doc);
				    
				    
				    if(veriler == null)
				    	break;
				    Element element;
				    
					for(int j = 0;j<veriler.size();j++)
					{
						element = veriler.get(j);
						gelen = element.text();
												
						//Yorum temizleniyor
						gelen = gelen.replaceAll("\'", " ");
						gelen = gelen.replaceAll("\"", " ");
						
						//Etiketlendi.
						gelen = "'"+gelen+"',?";
						
						toplananlar = toplananlar + gelen +"\n";

					}
					i++;		
				}while(elementsSayfa.last().text().equals(sonrakiSayfa));
				
				return toplananlar;
				
		}
		catch(Exception ex)
		{
			//System.out.println(ex.toString());
			return toplananlar;
		}
	}
	
	public static void dosyayaYaz(String veriler,String dosyaTamAdi)
	{
		try
		{
		    PrintWriter writer = new PrintWriter(dosyaTamAdi, "UTF-8");
		    writer.println(veriler);
		    writer.close();
		    //System.out.println("Dosyaya yazıldı");
		}
		catch (Exception e) 
		{
		   // do something
		}
		
	}

	public void sonuclariKaydet(String firmaAdi,String negatif,String pozitif,String notmention,
			String fiyat,String kargo,String urun,String ssh, String personel,String magaza,String reklam,String websitesi,String konu_yok)
	{
		// Retrofit kullanilacak, php web servis ile veritabanina kayit yapilacak!
		// Retrofit'e gerek kalmadi...
		try 
		{
			Jsoup.connect("http://www.burakkacar.com/webservisler/proje/firmaistatistikekle.php")
			.header("Content-Type", "application/x-www-form-urlencoded")
			.ignoreContentType(true)
			.data("firma_adi",firmaAdi)
			.data("negatif",negatif)
			.data("pozitif",pozitif)
			.data("notmention",notmention)
			.data("fiyat",fiyat)
			.data("kargo",kargo)
			.data("urun",urun)
			.data("ssh",ssh)
			.data("personel",personel)
			.data("magaza",magaza)
			.data("reklam",reklam)
			.data("websitesi",websitesi)
			.data("konu_yok",konu_yok)
			.post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
