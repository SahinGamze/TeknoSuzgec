package jsouptests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Scanner;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class TestClass 
{
	
	 // Sayaclar
    int neg,pos,nm;
    int vatan,teknosa,bimeks,ist, mediaMarkt,yokFirma;
    int fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yokKonu;


	public void KonuTestEt(String konuTestArffDosyaAdi,boolean testArffSilinsinMi) throws Exception{
		//NaiveBayes
		//Eğitici Data okundu.
		BufferedReader reader = new BufferedReader(
		         new FileReader("yeniKonu.arff"));
		Instances train = new Instances(reader);
		reader.close();
		
		//Test datası okundu.
		BufferedReader testreader = new BufferedReader(
				new FileReader(konuTestArffDosyaAdi));	
		Instances test = new Instances(testreader);
		testreader.close();
		
		// setting class attribute for test data
		int lastIndex = train.numAttributes() - 1;
		test.setClassIndex(lastIndex);
		
		// setting class attribute
		train.setClassIndex(lastIndex);
		
		StringToWordVector stwv = new StringToWordVector();
		stwv.setInputFormat(train);
			
		NaiveBayes bayes = new NaiveBayes();
		
		// Filtre uygulanıyor
		train = weka.filters.Filter.useFilter(train, stwv);
		test = weka.filters.Filter.useFilter(test, stwv);
		
		bayes.buildClassifier(train);	
		
		for(int i = 0; i < test.numInstances(); i++) {
			//System.out.println(i);
            double index = bayes.classifyInstance(test.instance(i));
            String className = train.attribute(0).value((int)index);
            
            // Sayaclar arttiriliyor
            switch (className)
            {
                case "fiyat":
                    setFiyat(getFiyat()+1); break;
                case "kargo":
                    setKargo(getKargo()+1); break;
                case "urun":
                    setUrun(getUrun()+1);break;
                case "ssh":
                    setSsh(getSsh()+1); break;
                case "personel":
                    setPersonel(getPersonel()+1); break;
                case "magaza":
                    setMagaza(getMagaza()+1); break;
                case "reklam":
                    setReklam(getReklam()+1); break;
                case "websitesi":
                    setWebsitesi(getWebsitesi()+1); break;
                case "yok":
                    setYokKonu(getYokKonu()+1); break;
            }
           
		}
		
		if(testArffSilinsinMi)
		{
			File file = new File(konuTestArffDosyaAdi);
			file.delete();
		}	
		
	}

	public void FirmaTestEt(String firmaTestArffDosyaAdi) throws Exception{
		//BayesNet 
		//Eğitici Data okundu.
		BufferedReader reader = new BufferedReader(
		         new FileReader("yeniFirma.arff"));
		Instances train = new Instances(reader);
		reader.close();
		
		//Test datası okundu.
		BufferedReader testreader = new BufferedReader(
		         new FileReader(firmaTestArffDosyaAdi));		
		Instances test = new Instances(testreader);
		testreader.close();
		
		// setting class attribute for test data
		int lastIndex = train.numAttributes() - 1;
		test.setClassIndex(lastIndex);
		
		// setting class attribute
		train.setClassIndex(lastIndex);

		StringToWordVector stwv = new StringToWordVector();
		stwv.setInputFormat(train);
			
		BayesNet bayes = new BayesNet();
		
		// Filtre uygulanıyor
		train = weka.filters.Filter.useFilter(train, stwv);
		test = weka.filters.Filter.useFilter(test, stwv);
		
		bayes.buildClassifier(train);	
		
		for(int i = 0; i < test.numInstances(); i++) {
			//System.out.println(i);
            double index = bayes.classifyInstance(test.instance(i));
            String className = train.attribute(0).value((int)index);
            System.out.println("Firma: "+className);
		}
		File file = new File(firmaTestArffDosyaAdi);
		file.delete();

	}

	public void NegPosTestEt(String negPostTestArffDosyaAdi,boolean testArffSilinsinMi) throws Exception{
		//NaiveBayesMultinomial 
		//Eğitici Data okundu.
		BufferedReader reader = new BufferedReader(
		         new FileReader("NegPos.arff"));
		Instances train = new Instances(reader);
		reader.close();
		
		//Test datası okundu.
		BufferedReader testreader = new BufferedReader(
		         new FileReader(negPostTestArffDosyaAdi));		
		Instances test = new Instances(testreader);
		testreader.close();
		
		// setting class attribute for test data
		int lastIndex = train.numAttributes() - 1;
		test.setClassIndex(lastIndex);
		
		// setting class attribute
		train.setClassIndex(lastIndex);

		StringToWordVector stwv = new StringToWordVector();
		stwv.setInputFormat(train);

		NaiveBayesMultinomial bayes = new NaiveBayesMultinomial();
		
		// Filtre uygulanıyor
		train = weka.filters.Filter.useFilter(train, stwv);
		test = weka.filters.Filter.useFilter(test, stwv);
		
		bayes.buildClassifier(train);	
		
		for(int i = 0; i < test.numInstances(); i++) {
			//System.out.println(i);
            double index = bayes.classifyInstance(test.instance(i));
            String className = train.attribute(0).value((int)index);
            // System.out.println("Değerlendirme: "+className);
            
            //Sayaclar arttiriliyor
            switch (className)
            {
                case "neg":
                    setNeg(getNeg()+1);
                    break;
                case "pos":
                    setPos(getPos()+1);
                    break;
                case "nm":
                    setNm(getNm()+1);
                    break;
            }
            
		}
		
		if(testArffSilinsinMi)
		{
			File file = new File(negPostTestArffDosyaAdi);
			file.delete();
		}
		

	}
	
	public void CumleTestEt() throws Exception{
		String cumle = "";
		String negPosTest, firmaTest, konuTest;
		Scanner user_input = new Scanner( System.in );

		negPosTest = "@relation degerlendirme\n@attribute text  String\n@attribute degerlendirme   {neg,pos,nm}\n@Data\n";

		firmaTest = "@RELATION firma\n@ATTRIBUTE text  String\n@ATTRIBUTE firmaxox   {bimeks,istBilisim,mediaMarkt,teknosa,vatanBilgisayar,yok}\n@Data\n";

		konuTest = "@RELATION konu\n@ATTRIBUTE text  String\n@ATTRIBUTE konuxox   {fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yok}\n@Data\n";

		System.out.println("Analiz edilecek cümleyi girin: ");
        cumle = user_input.nextLine();

        // Tek ve çift tırnaklar temizleniyor
		cumle = cumle.replaceAll("\'", " ");
		cumle = cumle.replaceAll("\"", " ");
		
		user_input.close();

		negPosTest = negPosTest + "'" + cumle + "', ?\n";
		firmaTest = firmaTest + "'" + cumle + "', ?\n";
		konuTest = konuTest + "'" + cumle + "', ?\n";

		Degerlendir.arffYaz(negPosTest,"negPosTest.arff");
		Degerlendir.arffYaz(firmaTest,"firmaTest.arff");
		Degerlendir.arffYaz(konuTest,"konuTest.arff");

		NegPosTestEt("negPosTest.arff",true);
		FirmaTestEt("firmaTest.arff");
		KonuTestEt("konuTest.arff",true);
		
	}

	public int getNeg() {
        return neg;
    }

    public void setNeg(int neg) {
        this.neg = neg;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getNm() {
        return nm;
    }

    public void setNm(int nm) {
        this.nm = nm;
    }

    public int getVatan() {
        return vatan;
    }

    public void setVatan(int vatan) {
        this.vatan = vatan;
    }

    public int getTeknosa() {
        return teknosa;
    }

    public void setTeknosa(int teknosa) {
        this.teknosa = teknosa;
    }

    public int getBimeks() {
        return bimeks;
    }

    public void setBimeks(int bimeks) {
        this.bimeks = bimeks;
    }

    public int getIst() {
        return ist;
    }

    public void setIst(int ist) {
        this.ist = ist;
    }

    public int getMediaMarkt() {
        return mediaMarkt;
    }

    public void setMediaMarkt(int mediaMarkt) {
        this.mediaMarkt = mediaMarkt;
    }

    public int getYokFirma() {
        return yokFirma;
    }

    public void setYokFirma(int yokFirma) {
        this.yokFirma = yokFirma;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getKargo() {
        return kargo;
    }

    public void setKargo(int kargo) {
        this.kargo = kargo;
    }

    public int getUrun() {
        return urun;
    }

    public void setUrun(int urun) {
        this.urun = urun;
    }

    public int getSsh() {
        return ssh;
    }

    public void setSsh(int ssh) {
        this.ssh = ssh;
    }

    public int getPersonel() {
        return personel;
    }

    public void setPersonel(int personel) {
        this.personel = personel;
    }

    public int getMagaza() {
        return magaza;
    }

    public void setMagaza(int magaza) {
        this.magaza = magaza;
    }

    public int getReklam() {
        return reklam;
    }

    public void setReklam(int reklam) {
        this.reklam = reklam;
    }

    public int getWebsitesi() {
        return websitesi;
    }

    public void setWebsitesi(int websitesi) {
        this.websitesi = websitesi;
    }

    public int getYokKonu() {
        return yokKonu;
    }

    public void setYokKonu(int yokKonu) {
        this.yokKonu = yokKonu;
    }
	
}
