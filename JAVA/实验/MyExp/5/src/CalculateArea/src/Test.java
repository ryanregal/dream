import java.util.Scanner;

//�����࣬�ֱ𴴽������������������Ķ���
//�����ø����е� area() ��������ӡ����ͬ��״�ļ���ͼ�ε����
public class Test {
 
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		int n = Integer.parseInt(input.nextLine().trim());//��ʾͼ�θ���
		for (int i=0;i<n;i++) 
		{
			//�������
			String s = input.nextLine().trim();
			String[] t = s.split(" ");
			//�������������
			if (t.length == 3) 
			{
				int a = Integer.parseInt(t[0]);
				int b = Integer.parseInt(t[1]);
				int c = Integer.parseInt(t[2]);
				Triangle tri = new Triangle(a,b,c);
				System.out.println(tri.area());
			}
			//������ǳ�����
			else if (t.length == 2) 
			{
				int a = Integer.parseInt(t[0]);
				int b = Integer.parseInt(t[1]);
				Rectangle rec = new Rectangle(a,b);
				System.out.println(rec.area());
			}
		}
		input.close();
	}
}