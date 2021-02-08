package search;
/*
两个有序递增数组A,B,长度分别为m,n，查找这两个数组的中位数
分析：
    1、中位数的位置：不管数组长度为奇数或偶数【有两个，向下取小的】，中位数前面都有 （length-1）/2 个元素小于中位数，中位数之后的都大于中位数
       所以 合并后的两个有序递增数组的中位数位置在 c=（m+n-1)/2，
    2、中位数假设在A中A[p];则B满足 B[c-p-1]<=A[p]<=B[c-p]
        若：B[c-p-1] > A[p],说明A[p]小了，继续向p后面A[p+1] - A[m-1]找
        若：B[c-p] < A[p],说明 A[p]大了，继续向前面A[0]-A[p-1]找
    3、若中位数不在A中，则继续上述操作从B中找

    注意：因为是有序递增序列，所以查找时可用二分查找提升效率
 */
public class FindMedian {
    private static int find_median(int[] A,int[] B, int m, int n, int s, int t){
        int c=(m+n-1)/2; //两个序列合并后的下中位数所在位置，前面有c个元素值比中位数元素值小
        int p=(s+t)/2; //递增有序数组，二分查找更快，单独某个序列或子序列区中

        //中位数不在A中，就从B中开始找
        if(s>t){
            return find_median(B,A,n,m,0,n-1);
        }

        //A[p]是中位数，按照合并序列中位数前有c个元素小于A[p]，A中占了前p个,则B中应刚好占了 c-p个，即递增B中第[c-p-1]为小于A[p]
         // B中下一位[c-p]要大于A[p]
        if(B[c-p-1]<= A[p] && A[p] <= B[c-p]){
            return A[p];
        }

        // 若递增B中前 c-p的第c-p-1位元素值 大于 A[p],说明A[p]小了，二分查找 p+1  - t
        if(B[c-p-1] > A[p]){
            return find_median(A,B,m,n,p+1,t);
        }

        // 若递增B中前 c-p的第c-p-1位元素值小于A[p],后一位需要大于 A[p]，否则A[p]大了，往p前进行二分查找，s  -  p-1
       /* if(B[c-p] > A[p]){
        }*/

            return find_median(A,B,m,n,s,p-1);

    }

    public static void main(String[] args) {
        int m, n;
        int A[]={1,3,5,7,8,9,10,12,24,45,65};
        int B[]={2,4,6,10,11,12,13,14,17,19,20,34,44,45,66,99};

        m = A.length;
        n = B.length;
        System.out.println(find_median(A, B, m, n, 0, m - 1));


    }
}
