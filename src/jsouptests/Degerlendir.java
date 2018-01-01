package jsouptests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Verilen data dosyasını değerlendirmek üzere ekranda yazdırır.
 * Kullanıcıdan değer alır.
 * X'e basıp çıkana kadar bu işlemi yapar.
 * X değeri girildikten sonra verileri arff dosya formatı şeklinde dosyalar.
 */
public class Degerlendir 
{
	
	// dosyaAdi = Yorumlanacak verilerin oldugu dosya (örnek: eksi vatan.txt)
	//arffDosyaAdi = Yaratılacak olan arff dosyasının ismi (örnek: eksi vatan verildiğinde oluşan dosya: ekşi vatan.arff)
	
	public static void NegPosDegerlendir(String dosyaAdi,String arffDosyaAdi) throws IOException
	{
		
		String yeniSatir = "",secim,str,
			   hepsi="@RELATION degerlendirme\n@ATTRIBUTE text  String\n@ATTRIBUTE degerlendirme   {neg,pos,nm}\n@Data\n";
		
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			int sayac=1;
			Scanner user_input = new Scanner( System.in );
			
			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));
			
			while ((str = in.readLine()) != null) 
			{
			    // Okunan satırı ekrana yazdır.
				System.out.println(sayac+". "+str);
			    
				// Seçim giriliyor: neg, pos veya nm girilmeli
				
			    secim = user_input.nextLine();
			    
			    while(!secim.equals("neg") & !secim.equals("pos") & !secim.equals("nm") & !secim.equals("x"))
			    {
			    	System.out.println("Hatalı giriş. ");
			    	secim = user_input.nextLine();
			    }
			    
			    // x değeri girilince döngüden çık
			    if(secim.equals("x"))
			    	break;
			    
			    // Tek tırnak ve çift tırnak yerine boşluk atanıyor
			    str = str.replaceAll("\'", " ");
			    str = str.replaceAll("\"", " ");
			    
			    yeniSatir = "'"+str+"'"+","+secim;
			    hepsi = hepsi+"\n"+yeniSatir;
			    
			    
			    sayac+=1;
			}
			
					user_input.close();
	                in.close();
	                arffYaz(hepsi,arffDosyaAdi+".arff");
	                
		    }
		    catch (UnsupportedEncodingException e)
		    {
				System.out.println(e.getMessage());
		    }
		
	}

	public static void FirmaDegerlendir(String dosyaAdi,String arffDosyaAdi) throws IOException
	{
		
		String yeniSatir = "",str,firma = "",
			   hepsi="@RELATION firma\n@ATTRIBUTE text  String\n@ATTRIBUTE Bimeks  {0,1}\n@ATTRIBUTE istBilisim  {0,1}\n@ATTRIBUTE MediaMarkt  {0,1}\n@ATTRIBUTE Teknosa  {0,1}\n@ATTRIBUTE VatanBilgisayar  {0,1}\n@Data\n";
		
		List<String> firmalar = new ArrayList<String>();; // ***********************************************************************************
		
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			int sayac=1;
			int i=0;
			Scanner user_input = new Scanner( System.in );
			
			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));
			
			while ((str = in.readLine()) != null) 
			{
			    // Okunan satırı ekrana yazdır.
				System.out.println(sayac+". "+str);
			    
				// 5 tane 0 veya 1 değeri girilmeli.
			
				while(i<5)
				{
					if(i==0){System.out.print("Bimeks? ");}
					if(i==1)System.out.print("İstanbul Bilişim? ");
					if(i==2)System.out.print("Media Markt? ");
					if(i==3)System.out.print("Teknosa? ");
					if(i==4)System.out.print("Vatan Bilgisayar? ");
					
					firma=user_input.nextLine();
					
					if(!firma.equals("0") && !firma.equals("1") && !firma.equals("x")){System.out.println("Hatalı giriş yaptınız !");}
					if(firma.equals("x")) break;
					
					if(firma.equals("0") || firma.equals("1"))
					{
						firmalar.add(i, firma);
						i++;
					}
				}
				
			    // x değeri girilince döngüden çık
			    if(firma.equals("x"))
			    	break;
			    
			    // Tek tırnak ve çift tırnak yerine boşluk atanıyor
			    str = str.replaceAll("\'", " ");
			    str = str.replaceAll("\"", " ");
			    
			    yeniSatir = "'"+str+"'"+","+firmalar.get(0)+","+firmalar.get(1)+","+firmalar.get(2)+","+firmalar.get(3)+","+firmalar.get(4);
			    hepsi = hepsi+"\n"+yeniSatir;
			   
			    firmalar.clear();
			    sayac+=1;
			    i=0;
			}
			
					user_input.close();
	                in.close();
	                arffYaz(hepsi,arffDosyaAdi+".arff");
	                
		    }
		    catch (UnsupportedEncodingException e)
		    {
				System.out.println(e.getMessage());
		    }
		
	}

	public static void KonuDegerlendir(String dosyaAdi,String arffDosyaAdi) throws IOException
	{
		/*
		 * Fiyat,ödeme seçeneği
		 * Kargo
		 * Ürün
		 * Personel
		 * Mağaza
		 * SSH Satış Sonrası Hizmet(Teknik servis, müşteri hizmetleri)
		 * Reklam
		 * WebSitesi
		 */
		
		String yeniSatir = "",str,konuVarmi = "",
			   hepsi="@RELATION konu\n@ATTRIBUTE text  String\n@ATTRIBUTE Fiyat  {0,1}\n@ATTRIBUTE Kargo  {0,1}\n@ATTRIBUTE Urun  {0,1}\n@ATTRIBUTE SSH  {0,1}\n@ATTRIBUTE Personel  {0,1}\n@ATTRIBUTE Magaza  {0,1}\n@ATTRIBUTE Reklam  {0,1}\n@ATTRIBUTE WebSitesi  {0,1} \n@Data\n";
		
		List<String> konular = new ArrayList<String>();; // ***********************************************************************************
		
		try {
			// Okunacak dosya
			File fileDir = new File(dosyaAdi);
			int sayac=1;
			int i=0;
			Scanner user_input = new Scanner( System.in );
			
			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));
			
			while ((str = in.readLine()) != null) 
			{
			    // Okunan satırı ekrana yazdır.
				System.out.println(sayac+". "+str);
			    
				// 8 tane 0 veya 1 değeri girilmeli.
			
				while(i<8)
				{
					if(i==0){System.out.print("Fiyat? ");}
					if(i==1)System.out.print("Kargo? ");
					if(i==2)System.out.print("Ürün ? ");
					if(i==3)System.out.print("Satış sonrası hizmet ? ");
					if(i==4)System.out.print("Personel ? ");
					if(i==5)System.out.print("Mağaza ? ");
					if(i==6)System.out.print("Reklam ? ");
					if(i==7)System.out.print("Web Sitesi ? ");


					konuVarmi=user_input.nextLine();
					
					if(!konuVarmi.equals("0") && !konuVarmi.equals("1") && !konuVarmi.equals("x")){System.out.println("Hatalı giriş yaptınız !");}
					if(konuVarmi.equals("x")) break;
					
					if(konuVarmi.equals("0") || konuVarmi.equals("1"))
					{
						konular.add(i, konuVarmi);
						i++;
					}
				}
				
			    // x değeri girilince döngüden çık
			    if(konuVarmi.equals("x"))
			    	break;
			    
			    // Tek tırnak ve çift tırnak yerine boşluk atanıyor
			    str = str.replaceAll("\'", " ");
			    str = str.replaceAll("\"", " ");
			    
			    yeniSatir = "'"+str+"'"+","+konular.get(0)+","+konular.get(1)+","+konular.get(2)+","+konular.get(3)+","+konular.get(4)+","+konular.get(5)+","+konular.get(6)+","+konular.get(7);
			    hepsi = hepsi+"\n"+yeniSatir;
			   
			    konular.clear();
			    sayac+=1;
			    i=0;
			}
			
					user_input.close();
	                in.close();
	                arffYaz(hepsi,arffDosyaAdi+".arff");
	                
		    }
		    catch (UnsupportedEncodingException e)
		    {
				System.out.println(e.getMessage());
		    }
		
	}

	public static void arffYaz(String veriler,String dosyaAdi) 
	{
		try
		{
			File file = new File(dosyaAdi);
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        
	        FileWriter fileWriter = new FileWriter(file,true);
	        BufferedWriter bWriter = new BufferedWriter(fileWriter);
	        bWriter.write(veriler);
	        bWriter.close();
	        System.out.println(dosyaAdi+" oluşturuldu !");
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	


}
