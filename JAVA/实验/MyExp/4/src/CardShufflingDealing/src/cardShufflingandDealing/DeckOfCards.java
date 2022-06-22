package cardShufflingandDealing;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//DeckOfCards ���ʾһ���˿���
public class DeckOfCards {
   private List<Card> list; // ���濨�Ƶ�����
   private int top=0; //ָ���ƶѶ�����ָ�룬���ڳ���
   int rounds=0; //���ڼ�¼�ִΣ�ÿ������ϴһ����
   
   // ����ֽ�Ʋ�ϴ��
   public DeckOfCards() {
      Card[] deck = new Card[52];
      int count = 0; // ������

      // ��Card�������deck����
      for (Card.Suit suit : Card.Suit.values()) {
         for (Card.Face face : Card.Face.values()) {  
            deck[count] = new Card(face, suit);
            ++count;
         } 
      } 

      list = Arrays.asList(deck); // �õ�List    
      Collections.shuffle(list);  // ϴ��
   } 

   // ����������ʾ 52 �ſ�Ƭ
   public void printCards() {
      for (int i = 0; i < list.size(); i++) {
         System.out.printf("%-19s%s", list.get(i),
            ((i + 1) % 4 == 0) ? System.lineSeparator() : "");
      } 
   } 
   
   //���ƣ���Ϸ�������־�Ҫϴһ����
   public List<Card> drawCards(int nums)
   {
	   List<Card> Drawed=new ArrayList<>(); 
	   for (int i = 0; i < nums; i++){
		   Drawed.add(list.get(top+i));
	   }
	   top+=nums;
	   
	   Collections.sort(Drawed, new Comparator<Card>(){
		   public int compare(Card c1,Card c2)
		   {
			   if(c1.getFace().ordinal()==c2.getFace().ordinal()) return 0;
			   if(c1.getFace().ordinal()>c2.getFace().ordinal()) return 1;
			   else return -1;
		   }
	   });
	   return Drawed;
   }
   
   //ϴ�ƣ�top��0
   public void shuffer(){
	   Collections.shuffle(list);
	   top=0;
   }
   
   public static void main(String[] args)
   {
      DeckOfCards cards = new DeckOfCards();
      List<Card> five_cardPokerHand=cards.drawCards(5);
      Rule referee=new Rule();
      
      int[] gradeAndKey=referee.evaluate(five_cardPokerHand);//�õ������grade��key
      for (int i = 0; i < five_cardPokerHand.size(); i++)
      {
    	  five_cardPokerHand.get(i).printCard();
      }
      System.out.println();
      System.out.println("Grade:"+gradeAndKey[0]);
      System.out.println("Key:"+gradeAndKey[1]);
   }   
} 
