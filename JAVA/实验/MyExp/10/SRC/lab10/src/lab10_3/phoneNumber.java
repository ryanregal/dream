package lab10_3;
import java.util.Scanner;

public class phoneNumber 
{
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args)
	{
		System.out.println("Please enter the number in the format of [area code + phone number]\nFor example: (555)555-5555\n");
		String input = scan.nextLine();
		//����)���ָ����ź͵绰����
		String[] tokens = input.split("\\)");
		//"\D"	��������һ���������ַ�
		//�õ�����
		String[] areaCodeGroup = tokens[0].split("\\D");
		String areaCode = areaCodeGroup[1];
		//�õ��绰����
		String[] phoneNumber = tokens[1].split("\\D");
		//��ӡ���
		System.out.println("Area code��" + areaCode);
		System.out.print("Phone number��");
		for(String str : phoneNumber)
		{
			System.out.print(str);
		}
		System.out.println();
	}
}
