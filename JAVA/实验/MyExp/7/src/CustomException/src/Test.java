//�Զ����쳣�Ķ��塢�����Ͳ�������
//�Զ��������쳣��Ƿ������쳣IllegaNameException�ͷǷ���ַ�쳣IllegalAddressException��
//����Student�����Name��Address���ԣ���setName��setAddress����
//����������С��1���ߴ���5�׳�IllegaNameExceptio
//����ַ�в����С�ʡ�����ߡ��С��ؼ����׳�IllegalAddressException��
//��main�����н��в������顣

public class Test{

        public static void main(String args[]) throws IllegaNameException, IllegalAddressException {
            Student s=new Student();
            try {
                s.setName("��ɪ����˹��");
            }
           catch(IllegaNameException e)
           {
               System.out.println("\n�����������");
               e.printStackTrace();
           }
           
            try {
                s.setAddress("�����޳�");
            }
            catch(IllegalAddressException e)
            {
                System.out.println("\n��ַ�������");
                e.printStackTrace();
            }
        }
}
