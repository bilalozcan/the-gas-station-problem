/* Bir kümenin alt kümelerini bulan algoritma için geeksforgeeks'den faydalanılmıştır
 * https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/ 
 * Bilal Özcan
 * 18120205035*/
import java.util.*;
public class bruteForce {

    public static void bruteForceAlgortihm(final ArrayList<Integer> mList, final ArrayList<Integer> pList, final int f, final int Limit, final int finish){
        ArrayList<Integer> subset = new ArrayList<Integer>(); //Geçerli alt kümeyi tutar
        ArrayList<Integer> stations= new ArrayList<Integer>(); //En az maliyeti veren alt kümeyi tutar
        int n = mList.size(); 
        int maliyet = Integer.MAX_VALUE;
        int mesafe = 0;
        int lastStation = 0;
        int geciciMaliyet = 0;

        //Alt kümeleri bulup kontrol eden ana döngü
        for (int i = 0; i < (1<<n); i++){
            subset.clear();
            geciciMaliyet = 0;
            lastStation = 0;

            //Bir alt kümenin subset array listinde tutulması
            for (int j = 0; j < n; j++){
                if ((i & (1 << j)) > 0){
                    subset.add(j);
                }
            }
            
            //Alt kümenin bizim uğrayabileceğimiz istasyonlar mı oldugunun kontrol edilmesi
            for(int k = 0; k < subset.size(); ++k){
                mesafe = mList.get(subset.get(k)) - mList.get(lastStation);
                if(Limit <= mesafe && mesafe <= f){
                    lastStation = subset.get(k);
                }else{
                    subset.clear();
                    break;
                }
                if(k == (subset.size() - 1)){
                    mesafe = finish - mList.get(subset.get(k));
                    if(Limit <= mesafe && mesafe <= f){
                    }else{
                        subset.clear();
                        break;
                    }
                }
            }
            //Her uygun alt küme için en az maliyet veren alt kümenin bir yerde tutulması ve maliyetin güncellenmesi
            if(subset.size() != 0){
                System.out.print("Kontrol Sonrası: ");
                for(int m = 0; m < subset.size(); ++m){
                    System.out.print(mList.get(subset.get(m)) + " ");
                    geciciMaliyet += pList.get(subset.get(m));
                }
                    
                System.out.println();
                if(geciciMaliyet < maliyet){
                    maliyet = geciciMaliyet;
                    stations.clear();
                    for(int k = 0; k < subset.size(); ++k)
                        stations.add(subset.get(k));
                }
            }
            
        }
        //En uygun bulanan alt kümenin elemanlarının ve maliyetin ekrana yazdırılması
        System.out.print("Toplam Maliyet: " + maliyet + "\nDurulan İstasyonlar: " + mList.get(0) + " ");
        for(int i = 0; i < stations.size(); ++i){
            System.out.print(mList.get(stations.get(i)) + " ");
        }
        System.out.println(finish);
      
    }
    public static void main(final String[] args) {
        final Integer[] mArray =
            //{0, 5, 7, 9, 11, 13}; // Yol 15 f= 7 k = 2
            //{0,   23,   51,   80,   97,  150,  171,  207,  251,  274,  310,  355,  393,  418,  458,  496,  537,  555,  585,  644,  694,  715,  735,  753,  810};//YOL  835 f= 100 k = 50
            /*{0,   27,   75,  130,  142,  161,  183,  198,  241,  253,  307,  361,  397,  453,  487,  544,  557,  608,  620,  638,  698,  737,  752,  779,  
            813,  866,  893,  910,  940,  973,  997, 1028, 1052, 1078, 1112, 1158, 1210, 1263, 1316, 1365, 1410, 1463, 1482, 1515, 1549, 1610, 1622, 1642, 1664, 1721};//YOL 1746 f= 100 k = 50*/
            //{0, 4, 6, 11, 16}; //YOL 20
            //{0, 3, 4, 9, 16, 20, 22, 26, 31}; //YOL 31 f= 10 k = 5
            //{0, 4, 6, 10, 12, 14, 18, 21, 26, 32, 36, 42, 47, 51, 55, 59, 62, 66, 70, 72}; //YOL 78 f= 10 k = 5
            {0, 6, 15, 17, 20, 23, 25, 32, 40, 49, 56}; // Yol 59 f= 10 k = 5
        final ArrayList<Integer> mList = new ArrayList<Integer>(Arrays.asList(mArray));
        final Integer[] pArray =
            //{0, 6, 8, 7, 3, 4};
            //{0,   17,   18,   15,   19,   11,   17,   14,   12,   11,   13,   12,   19,    8,   14,   12,    7,   12,    9,   12,   15,   17,   17,   12,   19};
            /*{0,    5,   18,   19,   11,    8,   15,    5,   16,    5,   18,   10,   18,   10,   18,   15,   13,   18,   12,   14,   10,   11,   13,   10,   17,   
            18, 11,   18,   17,   16,    9,    5,   13,   18,   14,   10,   19,   14,    5,    9,    9,   17,    6,   18,   17,    9,   14,   19,   10,    7};*/
            //{0, 1, 2, 5, 4};
            //{0, 1, 2,  3,  1,  2,  1,  3, 0};
            //{0, 3, 5, 5, 1, 3, 1, 1, 4, 4, 2, 4, 5, 5, 2, 1, 1, 2, 1, 1};
            {0, 3,  4,  5,  7,  1,  1,  2,  3,  7,  5};
            
        final ArrayList<Integer> pList = new ArrayList<Integer>(Arrays.asList(pArray));
        final int F = 10;
        final int Limit = 5;
        final int finish = 59;
        final long startTime = System.currentTimeMillis();
        System.out.print("F: " + F + "   Limit: " + Limit + "\nmList: ");
        for(int i = 0; i < mList.size(); ++i)
            System.out.print(mList.get(i) + ", ");
        System.out.print("\npList: ");
        for(int i = 0; i < pList.size(); ++i)
            System.out.print(pList.get(i) + ", ");
        System.out.println();

        bruteForceAlgortihm(mList, pList, F, Limit, finish);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
    }
    
}