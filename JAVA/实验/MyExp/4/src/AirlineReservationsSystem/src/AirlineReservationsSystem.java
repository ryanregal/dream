import java.util.Scanner;

public class AirlineReservationsSystem {
	private static final int TOTAL_SEATS = 11;//�ܹ���λ��
	private static boolean[] seats = new boolean[TOTAL_SEATS];//ƽ����λͼ

	private enum Class {
		FIRST, ECONOMIC
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int option = 0;
		int seatNumber = 0;
		while (option != 9) {
			System.out.print("������\n" + "һ����(1)  \n" + "������(2) \n" + "�˳�(0) \n"
					+ "ѡ��: ");
			option = input.nextInt();
			switch (option) {
			case 1:
				seatNumber = checkAvailable(Class.FIRST);
				if (seatNumber != -1) {
					seats[seatNumber] = true;
					displayMessage(seatNumber, Class.FIRST);
				} 
				else { // ���Ծ�����
					seatNumber = checkAvailable(Class.ECONOMIC);
					if (seatNumber != -1) {
						System.out.print(
								"\nһ��������!\n����Ҫ�����ɾ�������\n1 - Yes\n2 - Not\n����ѡ��: ");
						option = input.nextInt();

						if (option == 1) {//ѡ�񾭼���
							seats[seatNumber] = true;
							displayMessage(seatNumber, Class.ECONOMIC);
						} 
						else System.out.println("\n��һ���໹����Сʱ\n");
					} 
					else System.out.println("\nû�и�����λ�ˣ���������\n");
				}
				break;
		
			 case 2:
				seatNumber = checkAvailable(Class.ECONOMIC);

				if (seatNumber != -1) {
					seats[seatNumber] = true;
					displayMessage(seatNumber, Class.ECONOMIC);
				} 
				else { // try in another section
					seatNumber = checkAvailable(Class.FIRST);

					if (seatNumber != -1) {
						System.out.print(
								"\n����������!\\n����Ҫ������һ������\n1 - Yes\n2 - Not\n����ѡ��: ");
						option = input.nextInt();

						if (option == 1) {//ѡ��һ����
							seats[seatNumber] = true;
							displayMessage(seatNumber, Class.FIRST);
						} else
							System.out.println("\n��һ���໹����Сʱ\n");
					} else
						System.out.println("\nû�и�����λ�ˣ���������\n");
				}
				break;
				
			case 0:	break;
			default:
				System.out.println("\n��������ȷ����\n");
				break;
		  }
		}
		input.close();
	}

	//�����λ��Ϣ
	public static void displayMessage(int seatNumber, Class section) {
		System.out.println("\n�ǻ���");
		System.out.printf("��λ��: %d%n���: %s%n%n", seatNumber, section);
	}

	//������ʹ�õ���λ
	public static int checkAvailable(Class section) {
		int seatNumber = -1;//���������-1���ʾû�п���ʹ�õ���λ

		switch (section) {
		case FIRST:
			for (int i = 1; i <= TOTAL_SEATS / 2; i++) {
				if (seats[i] == false) {//��λ��û�б�ռ��
					seatNumber = i;
					break;
				}
			}
			break;
		case ECONOMIC:
			for (int i = TOTAL_SEATS / 2 + 1; i < TOTAL_SEATS; i++) {
				if (seats[i] == false) {//��λ��û�б�ռ��
					seatNumber = i;
					break;
				}
			}
			break;
		}
		return seatNumber;
	}
}