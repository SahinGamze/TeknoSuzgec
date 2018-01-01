package desktopApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JLabel;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class CumleTestSinif {
	public void NegPosTestEt(String negPostTestArffDosyaAdi) throws Exception{
		//NaiveBayesMultinomial 
		//Eğitici Data okundu.
		BufferedReader reader = new BufferedReader(
		         new FileReader("C:/Users/nigre/workspace/desktopApp/NegPos.arff"));
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
            System.out.println("Değerlendirme: "+className);
            
		}
		File file = new File(negPostTestArffDosyaAdi);
		file.delete();

	}
	
	public void FirmaTestEt(String firmaTestArffDosyaAdi) throws Exception{
		//BayesNet 
		//Eğitici Data okundu.
		BufferedReader reader = new BufferedReader(
		         new FileReader("C:/Users/nigre/workspace/desktopApp/yeniFirma.arff"));
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
	
	public void KonuTestEt(String konuTestArffDosyaAdi) throws Exception{
		//NaiveBayes
		//Eğitici Data okundu.
		BufferedReader reader = new BufferedReader(
		         new FileReader("C:/Users/nigre/workspace/desktopApp/yeniKonu.arff"));
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
			
			System.out.println(i);
            double index = bayes.classifyInstance(test.instance(i));
            String className = train.attribute(0).value((int)index);
            System.out.println("Konu: "+className);
           
            
		}
		File file = new File(konuTestArffDosyaAdi);
		file.delete();

	}

	

	
	public void CumleTestEt(String cumle) throws Exception{
		//String cumle = "";
		String negPosTest, firmaTest, konuTest;
		//Scanner user_input = new Scanner( System.in );
		

		negPosTest = "@relation degerlendirme\n@attribute text  String\n@attribute degerlendirme   {neg,pos,nm}\n@Data\n";

		firmaTest = "@RELATION firmaq\n@ATTRIBUTE text  String\n@ATTRIBUTE firmaxox   {bimeks,istBilisim,mediaMarkt,teknosa,vatanBilgisayar,yok}\n@Data\n";

		konuTest = "@RELATION konuq\n@ATTRIBUTE text  String\n@ATTRIBUTE konuxox   {fiyat,kargo,urun,ssh,personel,magaza,reklam,websitesi,yok}\n@Data\n";

		//System.out.println("Analiz edilecek cümleyi girin: ");
        //cumle = user_input.nextLine();

        // Tek ve çift tırnaklar temizleniyor
		cumle = cumle.replaceAll("\'", " ");
		cumle = cumle.replaceAll("\"", " ");

		negPosTest = negPosTest + "'" + cumle + "', ?\n";
		firmaTest = firmaTest + "'" + cumle + "', ?\n";
		konuTest = konuTest + "'" + cumle + "', ?\n";

		Degerlendirmeler.arffYaz(negPosTest,"negPosTest.arff");
		Degerlendirmeler.arffYaz(firmaTest,"firmaTest.arff");
		Degerlendirmeler.arffYaz(konuTest,"konuTest.arff");


		NegPosTestEt("negPosTest.arff");
		FirmaTestEt("firmaTest.arff");
		KonuTestEt("konuTest.arff");
		
		
		
	}
}
