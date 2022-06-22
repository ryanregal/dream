package game;
//����࣬��¼��һ�õ�ÿ�ֽ����ĸ���
public class Player 
{
	//��¼ÿ����������������Ϸֱ�ָ״Ԫ�����á����졢�Ľ������١�һ��
	public static final int A = 1,B = 2,C = 4,D = 8,E= 16,F = 32;
	public int id; //��Һ���
	public int[] result=new int[7]; //��¼��ҽ��
	
	public Player()
	{
		id=0;
		for(int i=1;i<7;i++) {
			result[i]=0;
		}
	}
	
//�ж����������
	public void winRecord(Dice dice)
	{
		int a=0,b=0,c=0,d=0,e=0,f=0,four=0; 
		//a,b,c,d,e,f�������һ���ӵ��������ӵ���������4֮�⣩
		for(int i=1;i<7;i++) 
		{
			if(dice.random[i]==4) four++; //��¼����Ϊ4��������Ŀ
			else if(a==0) a=dice.random[i];
			else if(b==0) b=dice.random[i];
			else if(c==0) c=dice.random[i];
			else if(d==0) d=dice.random[i];
			else if(e==0) e=dice.random[i];
			else if(f==0) f=dice.random[i];
		}
		
		//�жϽ��� ״Ԫ�ȼ�
		
		//״Ԫ��𻨣�444411
		if(four==4 && a==1 && b==1 && (result[1]>1||result[1]==0)) result[1]=1;
		//�����죺444444
		if(four==6 && (result[1]>2||result[1]==0)) result[1]=2;  
		//�����ڣ�XXXXXX(û��4����)
		else if(four==0 && a==b && b==c && c==d && d==e && e==f && (result[1]>3||result[1]==0)) 
			result[1]=3;
		//������44444X
		else if(four==5) 
		{//��ͬ�������Ƚϣ�6>5>3>2>1
			if(a==6 && (result[1]>4||result[1]==0)) result[1]=4;
			else if(a==5 && (result[1]>5||result[1]==0)) result[1]=5;
			else if(a==3 && (result[1]>6||result[1]==0)) result[1]=6;
			else if(a==2 && (result[1]>7||result[1]==0)) result[1]=7;
			else if(a==1 && (result[1]>8||result[1]==0)) result[1]=8;
		}
		//���ӵǿ�:XXXXXA
		else if(a==b && b==c && c==d && d==e)
		{//��ͬ�����ӵǿƱȽϴ�С6>5>3>2>1
			if(a==6 && (result[1]>9||result[1]==0)) result[1]=9;
			else if(a==5 && (result[1]>10||result[1]==0)) result[1]=10;
			else if(a==3 && (result[1]>11||result[1]==0)) result[1]=11;
			else if(a==2 && (result[1]>12||result[1]==0)) result[1]=12;
			else if(a==1 && (result[1]>13||result[1]==0)) result[1]=13;
		}
		//״Ԫ4444XX
		else if(four==4 && result[1]==0)
		{
			result[1]=14;
		}
	}
}