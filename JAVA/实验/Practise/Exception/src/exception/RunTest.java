package exception;

public class RunTest {
	public static void main(String[] args) {
		 
        String str = null;
 
        try {
            str.toString();
        } catch (NullPointerException e) {
            System.out.println("��׽������ʱ�쳣: NullPointerException ");
        }
 
        StringBuffer sb = new StringBuffer("1234567890");
        try {
            for (int i = 0; i < 100; i++) {
                sb.append(sb.toString());
            }
        } catch (OutOfMemoryError e) {
            System.out.println("��׽���ڴ��ù����:  OutOfMemoryError");
        }
 
    }
}
