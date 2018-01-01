package jsouptests;

import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VeriCek 
{
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
	
	
	public static void donanimHaber(String id,String firmaAdi)
	{
		// https://forum.donanimhaber.com/m_118127418/mpage_499/tm.htm
		System.out.println("DonanımHaber !");
		int i = 1;
		String toplananlar = "";
		int mesajSayaci = 0;
		try
		{
			Elements elements;
			do
			{
				Document doc = Jsoup.connect("https://forum.donanimhaber.com/"+id+"/mpage_"+i+"/tm.htm").timeout(10000).userAgent("Chrome").get();
			    elements = doc.getElementsByClass("msg");
			    if(elements.size()<=0)
			    	break;
			    
					for(Element element : elements)
					{
						toplananlar = toplananlar + element.text()+"\n";
						mesajSayaci++;
						System.out.println(element.text());
					}
				//Diğer sayfa için i arttırıldı.
				i++;
				
			}while(!elements.equals(null));
			
			//Yorumlar temizleniyor
			toplananlar = toplananlar.replaceAll("\'", " ");
			toplananlar = toplananlar.replaceAll("\"", " ");
			
			System.out.println("Başarıyla sonlandı!");
			dosyayaYaz(toplananlar,firmaAdi.toUpperCase(),"DONANIMHABER",mesajSayaci);

		}
		catch(Exception ex)
		{
			System.out.println(ex.toString()+"\n"+"Hata ile sonuçlandı ! ");
			dosyayaYaz(toplananlar,firmaAdi.toUpperCase(),"DONANIMHABER",mesajSayaci);
		}
		
	}
	
	public static void eksiSozlukYeni(String firmaAdi) throws IOException
	{
		String toplananlar = "";
		// ?p=2 ekle
		//firmaLink ='e kadar, = dahil.
		Document doc3= Jsoup.connect("https://eksisozluk.com/"+firmaAdi).timeout(0).userAgent("Chrome").get();
		String firmaLink = doc3.location();
		firmaLink = firmaLink + "?p=";
		
		System.out.println("Ekşisözlük !\n"+firmaLink);
		int i = 1;
		int mesajSayaci = 0;
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
						toplananlar = toplananlar + element.text()+"\n";
						mesajSayaci++;
						System.out.println(element.text());
					}
				//Diğer sayfa için i arttırıldı.
				i++;
				
			}while(elements != null);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			//Yorumlar temizleniyor
			toplananlar = toplananlar.replaceAll("\'", " ");
			toplananlar = toplananlar.replaceAll("\"", " ");
			dosyayaYaz(toplananlar,firmaAdi.toUpperCase(),"EKŞİ SÖZLÜK",mesajSayaci);
		}
	}
	
	public static void hepsiburada(String link,String etiket,String yildiz,int kacSayfa)
	{
		// Gerekli link
		// http://www.hepsiburada.com/samsung-galaxy-ace-s5830i-2-gb-hafiza-karti-hediye-p-TELCEPSAMS5830-B-yorumlari?filtre=
		System.out.println("Hepsiburada.com !");
		int i = 1;
		String toplananlar = "",temp;
		int mesajSayaci = 0;
		try
		{
			Elements elements;
			do
			{
				if(i == kacSayfa+1)
					break;
				
				Document doc = Jsoup.connect(link+yildiz+"&sayfa="+i).timeout(0).userAgent("Chrome").get();
			    elements = doc.getElementsByClass("review-text");
				if(elements != null)
					for(Element element : elements)
					{	
						temp = element.text();
						
						//Tırnaklar temizleniyor.
						temp = temp.replaceAll("\'", " ");
						temp = temp.replaceAll("\"", " ");
						
						temp = "'"+ temp + "',"+etiket+"\n";
						toplananlar = toplananlar + temp;
						mesajSayaci++;
						System.out.println(mesajSayaci+temp);
					}
				//Diğer sayfa için i arttırıldı.
				i++;
				
			}while(elements != null);
			
			dosyayaYaz(toplananlar,"Hepsiburada",yildiz+" yıldız",mesajSayaci);

		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			dosyayaYaz(toplananlar,"Hepsiburada",yildiz +" yıldız",mesajSayaci);
		}
	}
	
	public static void sikayetCom(String firma)
	{
		int i = 1,numberOfPage=9;
		String temp,toplananlar = "";
		Elements gelenler;
		try {
			Document doc = Jsoup.connect("http://www.sikayet.com/firma/"+firma).get();

			Element maxSayfa = doc.select(".page-text > strong:nth-child(1)").first();
			numberOfPage = Integer.valueOf(maxSayfa.text());
		    System.out.println(numberOfPage);

			for(i=1;i<=numberOfPage;i++)
			{
				Document doc1 = Jsoup.connect("http://www.sikayet.com/firma/"+firma+"/"+String.valueOf(i))
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:54.0) Gecko/20100101 Firefox/54.0")
						.get();
				
				gelenler = doc1.select("a.title");
				
				for (Element gelen : gelenler)
				{
					String detayUrl;
					detayUrl=gelen.attr("href");
					
					Document docDetaySayfasi = Jsoup.connect(detayUrl).get();
					
					String sikayet = "";
				    sikayet= docDetaySayfasi.select(".has-text > p").first().text();
				    
				    temp = sikayet;
					
					//Tırnaklar temizleniyor.
					temp = temp.replaceAll("\'", " ");
					temp = temp.replaceAll("\"", " ");
					
					temp = "'"+ temp + "',"+"neg"+"\n";
					toplananlar = toplananlar + temp;
				    
				    System.out.println(sikayet);
				}
				
				
			}
			
			dosyayaYaz(toplananlar, firma, "sikayet.com", 1);
			System.out.println("Bitti");
			
		} catch (Exception ex) {
			System.out.println(ex.toString());
			dosyayaYaz(toplananlar, firma, "sikayet.com", 1);

		}

	}
	

	public static void sikayetComSoruIsaretiEkle(String firma)
	{
		int i = 1,numberOfPage=9;
		String temp,toplananlar = "";
		Elements gelenler;
		try {
			Document doc = Jsoup.connect("http://www.sikayet.com/firma/"+firma).get();

			Element maxSayfa = doc.select(".page-text > strong:nth-child(1)").first();
			numberOfPage = Integer.valueOf(maxSayfa.text());
		    System.out.println(numberOfPage);

			for(i=1;i<=numberOfPage;i++)
			{
				Document doc1 = Jsoup.connect("http://www.sikayet.com/firma/"+firma+"/"+String.valueOf(i))
						.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:54.0) Gecko/20100101 Firefox/54.0")
						.get();
				
				gelenler = doc1.select("a.title");
				
				for (Element gelen : gelenler)
				{
					String detayUrl;
					detayUrl=gelen.attr("href");
					
					Document docDetaySayfasi = Jsoup.connect(detayUrl).get();
					
					String sikayet = "";
				    sikayet= docDetaySayfasi.select(".has-text > p").first().text();
				    
				    temp = sikayet;
					
					//Tırnaklar temizleniyor.
					temp = temp.replaceAll("\'", " ");
					temp = temp.replaceAll("\"", " ");
					
					temp = "'"+ temp + "',"+"?"+"\n";
					toplananlar = toplananlar + temp;
				    
				    System.out.println(sikayet);
				}
				
			}
			
			dosyayaYaz(toplananlar, firma, "sikayet.com", 1);
			System.out.println("Bitti");
			
		} 
		catch (Exception ex) 
		{
			System.out.println(ex.toString());
			dosyayaYaz(toplananlar, firma, "sikayet.com", 1);
		}

	}
	
	
	public static void dosyayaYaz(String veriler,String firma, String kaynak,int adet)
	{
		try
		{
		    PrintWriter writer = new PrintWriter(kaynak+" "+firma+" "+adet+".txt", "UTF-8");
		    writer.println(veriler);
		    writer.close();
		    System.out.println("Dosyaya yazıldı. !");
		} catch (Exception e) {
		   // do something
		}
		
	}
	
	
}
