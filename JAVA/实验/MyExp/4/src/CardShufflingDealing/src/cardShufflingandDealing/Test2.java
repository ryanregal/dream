package cardShufflingandDealing;

//����������Ҷ�ս
public class Test2
{
	public static void main(String[] args)
	{
		DeckOfCards cards=new DeckOfCards();
		Rule referee=new Rule();
		
		Player player1=new Player("\n���һ");
		player1.five_cardHand=cards.drawCards(5);
		player1.gradeAndKey=referee.evaluate(player1.five_cardHand);
		
		Player player2=new Player("��Ҷ�");
		player2.five_cardHand=cards.drawCards(5);
		player2.gradeAndKey=referee.evaluate(player2.five_cardHand);
		
		System.out.println(player1.getName()+"����:");
		player1.printHand();player1.printGradeAndKey();
		
		System.out.println("\n"+player2.getName()+"����:");
		player2.printHand();player2.printGradeAndKey();
		
		System.out.printf("\n%sʤ����\n",
		(referee.compareTwoPlayers(player1, player2)?
				player1.getName():player2.getName()));
	}

}
