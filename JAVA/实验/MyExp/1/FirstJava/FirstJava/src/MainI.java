import java.util.*;//Scanner����Ҫ�İ�

class MainI {
	public static void main(String args[]) {
		
		//��һ���������+��һ�����뷽��
		Scanner sc = new Scanner(System.in);//����Scanner�����
		System.out.println("Please enter your name:");
		String name = sc.nextLine();
		System.out.println("Please enter your age:");
		int age = sc.nextInt();
		System.out.println("Your name��" + name + "\n" + "Your age��" + age + "\n" );
		sc.close();
		
		//System.out�������������
		int num = 20;
		double price = 6.66;
		String s = "Welcome!";
		
		System.out.printf("%2$s ��Ʒ�۸�=%1$f�����������=%1$.1f\n", price, s);
		System.out.printf("��Ʒ����Ϊ%1$d\n", num);
		
	}
}