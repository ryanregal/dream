import java.security.SecureRandom;
import java.util.Scanner;

public class CoinTossing {
	//�����������
	private static final SecureRandom random = new SecureRandom();
	//����ö�ٱ���������Ӳ������ͷ���
	private enum Coin {
		HEADS, TAILS
	}

	public static void main(String[] args) {
		int headsCount = 0,tailsCount = 0,option = 0;
		Scanner scanner = new Scanner(System.in);
		String message = "\nѡ��˵�\n" + "��Ӳ�� (���� 1) \n" + "��ʾ��� (���� 2) \n" 
		+ "�˳� (���� 3) \n"+ "������ѡ��: ";

		while (option != 3) {
			System.out.print(message);//ÿ��ѭ�����menu
			option = scanner.nextInt();//��ȡ�û������ѡ��

			switch (option) {
			case 1:
				if (toss() == Coin.HEADS) {
					headsCount++;//�ӵ�����
					System.out.printf("���ӵ�������%n");
				}
				else  {
					tailsCount++;//�ӵ�����
					System.out.printf("���ӵ��˷���%n");
				}
				
				break;
			case 2:
				display(headsCount, tailsCount);//չʾ���
				break;
			case 3: break;
			default: System.err.println("\nError!\n");
			}
		}
		scanner.close();//�ر�scanner
	}
	//��ӡ�������
	public static void display(int headsCount, int tailsCount) {
		System.out.println("\nResults:\n");
		System.out.printf("HEADS: %d\n", headsCount);
		System.out.printf("TAILS: %d\n", tailsCount);
	}
	//�����ķ�תӲ�ҷ���
	public static Coin toss() {
		//�������һ��������ֵ�� [0, 2)
		return random.nextInt(2) == 0 ? Coin.HEADS : Coin.TAILS;
	}
}
