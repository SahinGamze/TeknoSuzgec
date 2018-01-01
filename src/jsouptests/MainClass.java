package jsouptests;

public class MainClass 
{

    public static void main(String[] args) throws Exception
    {
        //System.out.println(Zemberek2.dosyaKokDonusturme("ULUDAĞ SÖZLÜK BİMEKS 117.txt"));
        //ArffIslemleri.konuKokHalineGetir("yeniKonu.arff", "KÖK yeniKonu.arff");
        //ArffIslemleri.firmaKokHalineGetir("yeniFirma.arff", "KÖK yeniFirma.arff");
        //ArffIslemleri.NegPosKokHalineGetir("yeniNegPos.arff", "KÖK yeniNegPos.arff");
        //VeriCek.hepsiburada("http://www.hepsiburada.com/lg-g3-d855-32gb-ithalatci-garantili-p-TELCEPLGG332-B-yorumlari?filtre=", "pos", "5");
        //TestClass test = new TestClass();
        //test.CumleTestEt();

        //VeriCek.sikayetCom("teknosa");
        //VeriCek.sikayetCom("vatan-bilgisayar");

        //VeriCek.sikayetComSoruIsaretiEkle("bimeks");

        //TestClass test = new TestClass();
        //test.CumleTestEt();

        //TestClass test = new TestClass();
        //test.NegPosTestEt("testlikNegPos.arff");

        //VeriCek.eksiSozlukYeni("zara");

  
    	VeriToplaTestEt tester1 = new VeriToplaTestEt("teknosa",false);
    	VeriToplaTestEt tester2 = new VeriToplaTestEt("istanbul bilişim",false);
    	VeriToplaTestEt tester3 = new VeriToplaTestEt("media markt",false);
    	VeriToplaTestEt tester4 = new VeriToplaTestEt("vatan bilgisayar",false);
    	VeriToplaTestEt tester5 = new VeriToplaTestEt("bimeks",false);

    	
    }
    
   
}

