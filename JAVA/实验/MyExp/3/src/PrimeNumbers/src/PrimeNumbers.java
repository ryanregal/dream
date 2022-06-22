public class PrimeNumbers{
    public static void main(String[] args){
        int count = 0;
        System.out.printf("С��10000�������У�\n");
        for(int i=2; i<10000; i++){
            if(isPrimeRefined(i)){//��i������
                System.out.printf("%d ", i);
                count++;
            }
            //ÿʮ�����һ��
            if(count == 10){
                count = 0;
                System.out.println();
            }
        }
        System.out.println();
    }
   //����һ
    public static boolean isPrime(int n){
    	if(n<2) return false; //��С��������2
        for(int i=2; i<n/2+1; i++){
            if(n % i == 0)  return false;
        }
        return true;
    }
    //������
    public static boolean isPrimeRefined(int n){
    	if(n<2) return false; //��С��������2
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i == 0)  return false;
        }
        return true;
    }
}
