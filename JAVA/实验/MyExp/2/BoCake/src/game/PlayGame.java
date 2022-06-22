package game;
import java.util.Scanner;
 
public class PlayGame {
		//������
		public static void main(String[] args) {
			//ָ���û�����
			System.out.println("���������������Χ6-10��:");
			Scanner scanner= new Scanner(System.in);
			int n=scanner.nextInt();
			System.out.printf("��������%d�����\n\n",n);
			System.out.printf("\n----------------��Ϸ��ʼ--------------------\n\n");
			n++;
			
			//������
			Dice dice=new Dice() ;
			BoCake cake = new BoCake();
			Player[] player=new Player[n];//����һ����СΪn������player���������
			for(int i=1;i<n;i++){
				player[i]=new Player();//��ʼ��
				player[i].id=i;//����ÿ����ҵ�id
			}
			
			//��Ϸ����
			while(!cake.isOver()) {
				for(int i=1;i<n;i++){
					System.out.printf("��%d����ң�",i);
					dice.rollDice();//�����ӣ�Ͷ���Ľ������random��
					dice.printDice();//��������ĵ���
					player[i].winRecord(dice);//��¼���i��Ӯ�õĽ���
					cake.Rewardjudge(dice,player[i]);
					System.out.printf("\n");
					if(cake.isOver()) {
						break;
					}
				}
				System.out.printf("\n----------------�˾ֽ���--------------------\n\n");
			}
			
			System.out.printf("\n\n----------------��Ϸ���--------------------\n\n");
			
			//�ҳ�״Ԫ������һ��
			int win=0;
			if(cake.reward1[1]!=0) win=1;
			else if(cake.reward1[2]!=0) win=2;
			else if(cake.reward1[3]!=0) win=3;
			else if(cake.reward1[4]!=0)
			{
				if(cake.reward1[4]==6) win=4;
				else if(cake.reward1[4]==5) win=5;
				else if(cake.reward1[4]==3) win=6;
				else if(cake.reward1[4]==2) win=7;
				else if(cake.reward1[4]==1) win=8;
			}
			else if(cake.reward1[5]!=0)
			{
				if(cake.reward1[5]==6) win=9;
				else if(cake.reward1[5]==5) win=10;
				else if(cake.reward1[5]==3) win=11;
				else if(cake.reward1[5]==2) win=12;
				else if(cake.reward1[5]==1) win=13;
			}
			else if(cake.reward1[6]!=0) win=14;
			
			//���ҳ�״Ԫ����һ����
			for(int i=1;i<n;i++) {
				if(player[i].result[1]==win) {
					player[i].result[1]=-1;
					System.out.printf("��ϲ��%d����һ��״Ԫ��\n",i);
					break;
				}
			}
			//������ս��
			for(int i=1;i<n;i++)
			{
				System.out.printf("��%d����ң������ˣ�",i);
				System.out.printf("״Ԫ%d����",player[i].result[1]==-1?1:0);
				System.out.printf("����%d����",player[i].result[2]);
				System.out.printf("����%d����",player[i].result[3]);
				System.out.printf("�Ľ�%d����",player[i].result[4]);
				System.out.printf("����%d����",player[i].result[5]);
				System.out.printf("һ��%d��\n",player[i].result[6]);	
			}
			scanner.close();
			
		}
}