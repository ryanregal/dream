//�����࣬ʵ���漴�����ӵĹ���
package game;
 
public class Dice {
	public int[] random=new int[7];  //�������һ���ӵ��������ӵ���
	public Dice(){
		for(int i = 1;i<7;i++)
			random[i] =0;	
	}
	
//���������
	public void rollDice(){
		for(int i = 1;i<7;i++) {
			random[i] = 1 + ( int )(Math.random() * 6);//��������һ��������, α������ڷ�Χ��0��С��1
		}
	}	
 
//���Խӿڣ������������ɵ���������
	public void printDice(){
		System.out.println(" �����������Ϊ��");
		for(int i = 1;i<7;i++){
			System.out.printf("%d  ",random[i]);
		}
		System.out.print("\n");
	}
}