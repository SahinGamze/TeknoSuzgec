package jsouptests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import net.zemberek.erisim.Zemberek;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import net.zemberek.yapi.Kelime;

public class Zemberek2 
{
	public static String dosyaKokDonusturme(String dosyaAdi) throws IOException
	{
		String sonuc="";
		Zemberek zemberek = new Zemberek(new TurkiyeTurkcesi());

		File file = new File(dosyaAdi);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String satir= reader.readLine();

		while (satir!=null) {
			
			String[] kelimeler=null;
			kelimeler=satir.split(" ");
			
			for(int i=0;i<kelimeler.length;i++){
				
				//cözümleme
				Kelime[] cozumler = zemberek.kelimeCozumle(kelimeler[i]);
				if(cozumler.length==0)
				{
					   sonuc+=kelimeler[i];
				}
				else
				{
					Kelime k = cozumler[0];
					sonuc+=k.kok().toString().split(" ")[0];
				}
				
				sonuc+=" ";
			}
			
			sonuc+="\n";
			satir= reader.readLine(); 
			}
		
		reader.close();
		
		dosyayaYaz(sonuc, "KÖK "+dosyaAdi);
		return sonuc;
	}
	
	public static String KokHalineGetir(String cumle)
	{
		String sonuc = "";
		Zemberek zemberek = new Zemberek(new TurkiyeTurkcesi());
		String[] kelimeler = null;
		kelimeler = cumle.split(" ");
		
		for(int i = 0; i<kelimeler.length;i++)
		{
			Kelime[] cozumler = zemberek.kelimeCozumle(kelimeler[i]);
			if(cozumler.length==0)
			{
				sonuc+=kelimeler[i];
			}
			else
			{
				Kelime k = cozumler[0];
				sonuc+=k.kok().toString().split(" ")[0];
			}
			
			// Her kelimeden sonra 1 boşluk bırak.
			sonuc+=" ";
		}
		
		return sonuc;
	}
	
	public static String KokHalineGetirOlumsuzlukEkiyle(String cumle)
	{
		String sonuc = "";
		Zemberek zemberek = new Zemberek(new TurkiyeTurkcesi());
		String[] kelimeler = null;
		kelimeler = cumle.split(" ");
		
		for(int i = 0; i<kelimeler.length;i++)
		{
			Kelime[] cozumler = zemberek.kelimeCozumle(kelimeler[i]);
			if(cozumler.length==0)
			{
				sonuc+=kelimeler[i];
			}
			else
			{
				Kelime k = cozumler[0];
				
				sonuc+=k.kok().toString().split(" ")[0];
				
				if(k.toString().contains("OLUMSUZLUK"))
					sonuc+="ma";
			}
			sonuc+=" ";
		}
		
		return sonuc;
		
	}
	
	public static void dosyayaYaz(String data, String fileName)
	{
		try
		{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.println(data);
		    writer.close();
		    System.out.println(fileName +" yazıldı. !");
		} catch (Exception e) {
		   // do something
			System.out.println("Olmadı.");
		}
	}

}
