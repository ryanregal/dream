package cardShufflingandDealing;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//�������Զ�ս
public class Test3
{
	public static void main(String[] args)
	{
		DeckOfCards cards=new DeckOfCards();//����һ����
		Rule referee=new Rule();//����һ������
		Player player1=new Player("���");//����һ�����
		Player player2=new Player("����");//����һ���������
		
		Scanner scanner=new Scanner(System.in);
		List<Integer> changeIndex=new ArrayList<>();//������׼�������Ƶ����
		
		//��ҳ�ȡ��ʼ����
		player1.five_cardHand=cards.drawCards(5);
		player2.five_cardHand=cards.drawCards(5);
		player1.gradeAndKey=referee.evaluate(player1.five_cardHand);
		player2.gradeAndKey=referee.evaluate(player2.five_cardHand);

		for(int rounds=0;rounds<8;rounds++)
		{
			System.out.println("\n�����:");
			player1.printHand();player1.printGradeAndKey();
			//��ʾ�Ƿ���
			System.out.println("\n������Ҫ�����Ƶ��±�\n"+ "(����-1 ������):");
			String[] arr=scanner.nextLine().split(" ");
			for (int i = 0; i < arr.length; i++)
				changeIndex.add(Integer.parseInt(arr[i])-1);

			if (arr[0].equals("-1")||rounds==7)
			{
				if (rounds==7) System.out.println("�ﵽ���Ļ��ƴ���!");
				System.out.println("\n�����:");
				player1.printHand();player1.printGradeAndKey();
				
				System.out.println("\n���Ե���:");
				player2.printHand();player2.printGradeAndKey();
				System.out.printf("\n%sʤ����\n",referee.compareTwoPlayers(player1, player2)?
				player1.getName():player2.getName());
				break;
			}
			else //��Һ͵���ͬʱ����
			{
				player1.change(changeIndex, cards);
				changeIndex.clear();
				player1.gradeAndKey=referee.evaluate(player1.five_cardHand);
				System.out.printf("���Ը����˵� %d����\n",player2.autoChange(cards));
				player2.gradeAndKey=referee.evaluate(player2.five_cardHand);
			}
		}
		scanner.close();
	}

}
