/* Bilal Özcan
 * 18120205035 */
import java.util.*;
public class DivideAndConquer {
    static void merge(int array[], int l, int m, int r, int F, int k, final ArrayList<Integer> mList, final ArrayList<Integer> pList){
        int mesafe = 0;
        for(int i = l; i <= m; ++i){
            if(array[i] != -1){
                for(int j = r; j >= m+1; --j){
                    mesafe = array[j] - array[i];
                    if(k <= mesafe && mesafe <= F){
                        for(int a = i+1 ; a < j; ++a)
                            array[a] = -1;
                        break;
                    }
                }
            }
        }
    }
    
    static void divide(int array[], int l, int r, int F, int k, final ArrayList<Integer> mList, final ArrayList<Integer> pList) {
        if (l < r) {
            int m = (l + r) / 2;
            divide(array, l, m, F, k, mList, pList);
            divide(array, m + 1, r, F, k, mList, pList);
            merge(array, l, m, r, F, k, mList, pList);
        }
    }

    public static void main(final String[] args) {
        final Integer[] mArray = 
            //{0, 5, 7, 9, 11, 13, 15}; //Yol 15 f =7 k = 2
            //{0, 6, 15, 17, 20, 23, 25, 32, 40, 49, 56, 59}; // Yol 59 f = 10 k = 5
            //{0, 4, 6, 10, 12, 14, 18, 21, 26, 32, 36, 42, 47, 51, 55, 59, 62, 66, 70, 72, 78}; //YOL 78 f = 10 k = 5
            //{0,   23,   51,   80,   97,  150,  171,  207,  251,  274,  310,  355,  393,  418,  458,  496,  537,  555,  585,  644,  694,  715,  735,  753,  810, 835};//YOL  835 f = 100 k = 50
            {0,   27,   75,  130,  142,  161,  183,  198,  241,  253,  307,  361,  397,  453,  487,  544,  557,  608,  620,  638,  698,  737,  752,  779,  
            813,  866,  893,  910,  940,  973,  997, 1028, 1052, 1078, 1112, 1158, 1210, 1263, 1316, 1365, 1410, 1463, 1482, 1515, 1549, 1610, 1622, 1642, 1664, 1721, 1746};//YOL 1746 f = 100 k = 50
        final ArrayList<Integer> mList = new ArrayList<Integer>(Arrays.asList(mArray));

        final Integer[] pArray = 
                //{0, 6, 8, 7, 3, 4, 0};
                //{0, 3,  4,  5,  7,  1,  1,  2,  3,  7,  5, 0};
                //{0, 3, 5, 5, 1, 3, 1, 1, 4, 4, 2, 4, 5, 5, 2, 1, 1, 2, 1, 1, 0};
                //{0,   17,   18,   15,   19,   11,   17,   14,   12,   11,   13,   12,   19,    8,   14,   12,    7,   12,    9,   12,   15,   17,   17,   12,   19, 0};
                {0,    5,   18,   19,   11,    8,   15,    5,   16,    5,   18,   10,   18,   10,   18,   15,   13,   18,   12,   14,   10,   11,   13,   10,   17,   18,   
                11,   18,   17,   16,    9,    5,   13,   18,   14,   10,   19,   14,    5,    9,    9,   17,    6,   18,   17,    9,   14,   19,   10,    7, 0};  
        final ArrayList<Integer> pList = new ArrayList<Integer>(Arrays.asList(pArray));

        final int array[] = new int[mList.size()];
        for(int i = 0; i < mList.size(); ++i)
            array[i] = mList.get(i);
        final int F = 100;
        final int Limit = 50;
        final int finish = 1746;
        final long startTime = System.currentTimeMillis();

        System.out.print("F: " + F + "\tLimit: " + Limit + "\tBitiş Noktasi: " + finish +"\nmList: ");
        for(int i = 0; i < mList.size(); ++i)
            System.out.print(mList.get(i) + ", ");

        System.out.print("\npList: ");
        for(int i = 0; i < pList.size(); ++i)
            System.out.print(pList.get(i) + ", ");
        divide(array, 0, mList.size() - 1, F, Limit, mList, pList);
        final long endTime = System.currentTimeMillis();
        System.out.println("\nTotal execution time: " + (endTime - startTime));
        int toplam = 0;
        for(int i = 0; i < mList.size();++i){
            if(array[i] != -1){
                System.out.print(array[i] + ", ");
                toplam += pList.get(i);
            }
        }
        System.out.println("\nToplam Ücret: " + toplam);
    }
}