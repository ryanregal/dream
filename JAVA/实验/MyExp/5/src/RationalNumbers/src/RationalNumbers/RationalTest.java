package RationalNumbers;
import java.util.Scanner;

public class RationalTest {

	public static void main(String[] args) {
		
		//��a/b����ʽ��ӡ������������aΪ���ӣ�bΪ��ĸ��
		Scanner scanner=new Scanner(System.in);
		System.out.println("�������һ����������������ǰ����ĸ�ں�:");
		int r1=scanner.nextInt();
		int r2=scanner.nextInt();
		Rational test1 = new Rational(r1, r2);
		System.out.println("a/b��ʽ��ӡ����������");
		System.out.println(test1);
		
		//�Ը����ʽ��ӡ������
		System.out.println("������������ľ��ȣ�");	
		int precision=scanner.nextInt();
		System.out.println("�Ը����ʽ��ӡ����������");
		System.out.println(test1.toStringFloat(precision));
		
		//����ڶ���������
		System.out.println("\n������ڶ�����������������ǰ����ĸ�ں󣨲�������0��:");
		int r3=scanner.nextInt();
		int r4=scanner.nextInt();
		Rational test2 = new Rational(r3, r4);
		System.out.println("���������Ϊ��");
		System.out.println(test2);
		
		//�������ӷ�
		System.out.println("\n�ڶ��������ӵ�һ����������");
		Rational test4 = Rational.add(test2, test1);
		System.out.println(test4);
	
		//����������
		System.out.println("�ڶ�����������һ����������");
		Rational test3 = Rational.subtract(test2, test1);
		System.out.println(test3);
		
		//�������˷�
		System.out.println("�ڶ��������˵�һ����������");
		Rational test5 = Rational.multiply(test2, test1);
		System.out.println(test5);
	
		//����������
		System.out.println("�ڶ�����������һ����������");
		Rational test6 = Rational.divide(test2, test1);
		System.out.println(test6);
		
		scanner.close();
	}
}