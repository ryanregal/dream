package cardShufflingandDealing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cardShufflingandDealing.Card.Face;
import cardShufflingandDealing.Card.Suit;

//��������൱�ڲ�����
public class Rule
{
	//�Ƚ���������
   public boolean compareTwoPlayers(Player p1,Player p2)
   {
	   boolean p1Better=false;//true��p1����
		if(p1.gradeAndKey[0]==p2.gradeAndKey[0]){ //�ȼ���ͬʱ���Ƚ���ֵ
			p1Better=p1.gradeAndKey[1]>p2.gradeAndKey[1]? true:false;
		}
		else {	//�ȼ���ͬʱ��ֱ�ӱȽϵȼ�
			p1Better=p1.gradeAndKey[0]>p2.gradeAndKey[0]? true:false;
		}
		return p1Better;
   }
   
   //ͳ�Ʒ���
   public int[] evaluate(List<Card> five_cardPokerHand)
   {
	   int[] gradeAndKey=new int[2]; //�ȼ�+����
	   int[] intFaces=new int[5]; //�������
	   Map<Face, Integer> faceCount=new HashMap<Face,Integer>(); //������ֵ�����
	   Suit[] suits=new Suit[5]; //��¼��ɫ
	   
	   //ͳ�������Լ���ɫ,���滨ɫͨ����������һһ��Ӧ
	   for (int i = 0; i < five_cardPokerHand.size(); i++)
	   {
		   Face face=five_cardPokerHand.get(i).getFace();
		   Suit suit=five_cardPokerHand.get(i).getSuit();
		   if(faceCount.containsKey(face))
		   {
			   faceCount.put(face, faceCount.get(face)+1);
		   }
		   else 
		   {
			   faceCount.put(face, 1);
		   }
		   
		   intFaces[i]=face.ordinal()+1;
		   suits[i]=suit;
	   }
	  
	   //�ʼ�ͬ��˳
	   if(isStraight(intFaces)&&isFlush(suits) &&intFaces[0]==Face.Ace.ordinal()+1){
		   gradeAndKey[0]=10;
		   gradeAndKey[1]=suits[0].ordinal();
	   }
	   //ͬ��˳
	   else if(isFlush(suits)&&isStraight(intFaces)){
		   gradeAndKey[0]=9;
		   gradeAndKey[1]=intFaces[4];
	   }
	   //����
	   else if(isFOAK(faceCount)){   
		   gradeAndKey[0]=8;
		   gradeAndKey[1]=returnKey(faceCount,4).get(0).ordinal()+1;
	   }
	   //��«
	   else if (isFullHouse(faceCount)) {
		   gradeAndKey[0]=7;
		   gradeAndKey[1]=returnKey(faceCount,3).get(0).ordinal()+1;
	   }
	   //ͬ��
	   else if(isFlush(suits)){
		   gradeAndKey[0]=6;
		   gradeAndKey[1]=intFaces[4];
	   }
	   //˳��
	   else if(isStraight(intFaces)){
		   gradeAndKey[0]=5;
		   gradeAndKey[1]=intFaces[4];
	   }
	   //����
	   else if(isTOAK(faceCount)){
		   gradeAndKey[0]=4;
		   gradeAndKey[1]=returnKey(faceCount,3).get(0).ordinal()+1;
	   }
	   //����
	   else if(isTwoPair(faceCount)){
		   gradeAndKey[0]=3;
		   List<Face> tempFaces=returnKey(faceCount, 2);
		   gradeAndKey[1]=tempFaces.get(0).ordinal()>tempFaces.get(1).ordinal()?
		   tempFaces.get(0).ordinal()+1:tempFaces.get(1).ordinal()+1;
		   
	   }
	   //һ��
	   else if(isPair(faceCount)){
		   gradeAndKey[0]=2;
		   gradeAndKey[1]=returnKey(faceCount, 2).get(0).ordinal()+1;
	   }
	   // ����
	   else {
		   gradeAndKey[0]=1;
		   gradeAndKey[1]=intFaces[4];
	   }
	   return gradeAndKey;
   }
   
   //���صȼ��µ�����ֵ
   private List<Face> returnKey(Map<Face, Integer> faceCount,int value)
   {
	   List<Face> list=new ArrayList<>();
	   for(Map.Entry<Face,Integer> entry:faceCount.entrySet()){
		   if (entry.getValue().equals(value)){
			   list.add(entry.getKey());
		   }
	   }
		   return list;
   }
   
	//�ж��Ƿ���˳�ӣ�������������
   private boolean isStraight(int[] intFaces)
   {
	   boolean isstraight=false;
	   for (int i = 0; i < intFaces.length-1; i++)
	   {
		   if(intFaces[i+1]-intFaces[i]-1!=0)
		   {
			   isstraight=false;
			   break;
		   }
	   }
	 //�������A K Q J 10
	   if(intFaces[0]==1&&intFaces[1]==10&&intFaces[2]==11 &&intFaces[3]==12&&intFaces[4]==13)
		   isstraight=true;		
	   return isstraight;
   }
   
	//�ж��Ƿ���ͬ�������Ż�ɫһ��
   private boolean isFlush(Suit[] suits)
   {
	   boolean isflush=false;
	   if(suits[0]==suits[1]&&suits[1]==suits[2] &&suits[2]==suits[3]&&suits[3]==suits[4] &&suits[4]==suits[1]){
		  isflush=true; 
	   }
	   return isflush;
   }
   
	//�ж��Ƿ�������������һ��
   private boolean isFOAK(Map<Face, Integer> faceCount)
   {
	   boolean isfoak=false;
	   if(faceCount.containsValue(4))  isfoak=true;
	   return isfoak;
   }
   
	//�ж��Ƿ��Ǻ�«����������һ����������������һ��
   private boolean isFullHouse(Map<Face, Integer> faceCount)
   {
	   boolean isfullhouse=false;
	   if(faceCount.containsValue(3)&&faceCount.containsValue(2))
		   isfullhouse=true;
	   return isfullhouse;
   }
   
	//�ж��Ƿ���������5����3�ŵ�����һ��
   private boolean isTOAK(Map<Face, Integer> faceCount)
   {
	   boolean istoak=false;
	   if(faceCount.containsValue(3)&&faceCount.size()==3)
		   istoak=true;
	   return istoak;
   }
   
	//�ж��Ƿ������ԣ�4������������һ��������1�Ų�ͬ   
   private boolean isTwoPair(Map<Face, Integer> faceCount)
   {
	   boolean istwopair=false;
	   if(faceCount.containsValue(2)&&faceCount.size()==3)
		   istwopair=true;
	   return istwopair;
   }
   
	//�ж��Ƿ���һ�ԣ� 5����2�ŵ�����һ��������3�Ų���ͬ
   private boolean isPair(Map<Face, Integer> faceCount)
   {
	   boolean ispair=false;
	   if(faceCount.containsValue(2)&&faceCount.size()==4)
		   ispair=true;
	   return ispair;
   }

}
