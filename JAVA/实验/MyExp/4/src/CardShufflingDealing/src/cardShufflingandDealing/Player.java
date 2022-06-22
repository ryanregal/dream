package cardShufflingandDealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cardShufflingandDealing.Card.Face;
import cardShufflingandDealing.Card.Suit;

//player���ʾ���
public class Player
{
	public String name;
	List<Card> five_cardHand;
	public int[] gradeAndKey;
	
	public Player(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void printHand(){
		for (int i = 0; i <five_cardHand.size(); i++){
			five_cardHand.get(i).printCard();
		}
		System.out.println();
	}
	
	public void printGradeAndKey(){
		System.out.println("�ȼ�: "+gradeAndKey[0]
				+"  ��С: "+gradeAndKey[1]);
	}
	
	//���л���
	public void change(List<Integer> indexs,DeckOfCards cards)
	{
		for (int i = 0; i < indexs.size(); i++){
			Collections.replaceAll(five_cardHand, five_cardHand.get(indexs.get(i)),cards.drawCards(1).get(0));
		}
		//������
	    Collections.sort(five_cardHand, new Comparator<Card>(){
		    @Override
		    public int compare(Card c1,Card c2)
		    {
			    if(c1.getFace().ordinal()==c2.getFace().ordinal()) return 0;
			    if(c1.getFace().ordinal()>c2.getFace().ordinal()) return 1;
			    else  return -1;
		    }
	    });
	}
	
	//�Զ�����
	public int autoChange(DeckOfCards cards)
	{
		int[] intFaces=new int[5];	//��¼��������(1-13)
		Map<Face, Integer> faceCount=new HashMap<Face,Integer>();//��¼������ֵ�����
		Map<Suit, Integer> suiCount=new HashMap<>();
		Suit[] suits=new Suit[5]; //��¼��ɫ
		List<Integer> changeIndex=new ArrayList<>();
		
		//ͳ�������Լ���ɫ,���滨ɫͨ����������һһ��Ӧ
		for (int i = 0; i < five_cardHand.size(); i++)
		{
		   Face face=five_cardHand.get(i).getFace();
		   Suit suit=five_cardHand.get(i).getSuit();
		   if(faceCount.containsKey(face))  faceCount.put(face, faceCount.get(face)+1);
		   else faceCount.put(face, 1);
		   if(suiCount.containsKey(suit))	suiCount.put(suit, suiCount.get(suit)+1);
		   else  suiCount.put(suit, 1);
		   
		   intFaces[i]=face.ordinal()+1;
		   suits[i]=suit;
		}
		switch (gradeAndKey[0])
		{
		case 1:{
			//���ƣ�
			if (suiCount.containsValue(4)){
				//����ͬ��ɫ����һ��
				int index=0;
				Suit singleSuit=returnKey_suits(suiCount, 1).get(0);
				for (Card card : five_cardHand){
					if (singleSuit==card.getSuit()){
						changeIndex.add(index);
						break;
					}
					index++;
				}
				change(changeIndex, cards);
				break;
			}
			//�����ų�˳�ӣ�������
			else if(!twoToFormAStraight(intFaces).isEmpty())
			{
				change(twoToFormAStraight(intFaces), cards);
				break;
			}
			else {
				//������������,������
				changeIndex.add(0);
				changeIndex.add(1);
				changeIndex.add(2);
				change(changeIndex, cards);
			}
			break;
		}
		case 2://һ��
		{
			//�����ӣ�������
			Face pairFace=returnKey_faces(faceCount, 2).get(0);
			int index=0;
			for (Card card:five_cardHand){
				if (card.getFace()!=pairFace){
					changeIndex.add(index);
				}
				index++;
			}
			change(changeIndex, cards);
			break;
		}
		case 3://����
		{
			//�����ӣ���һ��
			List<Face> pairFaces=returnKey_faces(faceCount, 2);
			int index=0;
			for (Card card : five_cardHand){
				if (card.getFace()!=pairFaces.get(1) &&card.getFace()!=pairFaces.get(0)){
					changeIndex.add(index);
				}
				index++;
			}
			change(changeIndex, cards);
			break;
		}
		case 4://����
		{
			//��������������
			Face tripleFace=returnKey_faces(faceCount, 3).get(0);
			int index=0;
			for (Card card:five_cardHand){
				if (card.getFace()!=tripleFace){
					changeIndex.add(index);
				}
				index++;
			}
			change(changeIndex, cards);
			break;
		}
		default:
			break;//�����Ѿ����ò��ش���
		}
		return changeIndex.size();
	}
	
   //����key��ɫ
   private List<Suit> returnKey_suits(Map<Suit, Integer> faceCount,int value){
	   List<Suit> list=new ArrayList<>();
	   for(Map.Entry<Suit,Integer> entry:faceCount.entrySet())
	   {
		   if (entry.getValue().equals(value))
		   {
			   list.add(entry.getKey());
		   }
	   }
	   return list;
   }
   
   //����key��ֵ
   private List<Face> returnKey_faces(Map<Face, Integer> faceCount,int value){
	   List<Face> list=new ArrayList<>();
	   for(Map.Entry<Face,Integer> entry:faceCount.entrySet())
	   	{
		   if (entry.getValue().equals(value))
		   {
			   list.add(entry.getKey());
		   }
	   }
		   return list;
   }

   //�����������Ż����л�������˳�ӣ����ػ����������ŵ����
   private List<Integer> twoToFormAStraight(int[] intFaces)
   {
	   List<Integer> changeList=new ArrayList<>();
	   for (int i = 0; i < five_cardHand.size(); i++){
		   for (int j = 0; j < five_cardHand.size(); j++){
			
			   int gap=0;
			   int[] intThreeCardFace=new int[3];
			   int tempIndex=0;
			   for (int k = 0; k < intFaces.length; k++){
				   //���������е�����ȡ��������ʱ����
				   if(k==j||k==i) continue;
				   else{
					   intThreeCardFace[tempIndex]=intFaces[k];
					   tempIndex++;
				   }
			   }
			   for (int k = 0; k < intThreeCardFace.length-1; k++){
				   gap+=(intThreeCardFace[j+1]-intThreeCardFace[i]-1);
			   }
			   if(gap<=2){
				   changeList.add(i);
				   changeList.add(j);
				   return changeList;
			   }
			   changeList.clear();
		   }
	   }
	   return changeList;
   }
}
