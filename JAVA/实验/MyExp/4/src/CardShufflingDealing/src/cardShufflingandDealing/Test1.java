package cardShufflingandDealing;
import java.util.ArrayList;
import java.util.List;

import cardShufflingandDealing.Card.Face;
import cardShufflingandDealing.Card.Suit;

//������ƣ��жϴ�С�ȼ�
public class Test1
{
	public static void main(String[] args)
	{
		String[] title= {" ","High Card","Pair","Two pairs",
				"Three of a Kind","Straight","Flush",
				"Full House","Four of a Kind","Straight Flush",
				"Royal Flush"};
		Rule referee =new Rule();
		List<Card> fiveCards=new ArrayList<>();
		fiveCards.add(new Card(Face.Five, Suit.Spades));
		fiveCards.add(new Card(Face.Seven, Suit.Spades));
		fiveCards.add(new Card(Face.Seven, Suit.Clubs));
		fiveCards.add(new Card(Face.Eight, Suit.Diamonds));
		fiveCards.add(new Card(Face.Nine, Suit.Diamonds));
		int[] gradeAndKey=referee.evaluate(fiveCards);
		System.out.println("�������ȡ���ƣ��ȼ��� "+title[gradeAndKey[0]]+"����С�� "+gradeAndKey[1]);
	}

}
