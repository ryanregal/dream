//�ж�ÿ��������ʱӦ��õĽ������ж��Ƿ��ѷ������еĽ���
package game;
 
public class BoCake
{
	//��¼ÿ����������������Ϸֱ�ָ״Ԫ�����á����졢�Ľ������١�һ��
	public static final int numA = 1,numB = 2,numC = 4,numD = 8,numE= 16,numF = 32;
	public int[] reward1=new int[7]; //��¼״Ԫ
	public int reward2=0,reward3=0,reward4=0,reward5=0,reward6=0;//��¼����
	
	//��ʼ��
	public BoCake(){
		for(int i=1;i<7;i++) {
			reward1[i]=0;
		}
		reward2=reward3=reward4=reward5=reward6=0;
	}
	
	
	//�жϽ���
	public void Rewardjudge(Dice dice,Player player)
	{
		//a,b,c,d,e,f�������һ���ӵ��������ӵ���������4֮�⣩
		int four=0,a=0,b=0,c=0,d=0,e=0,f=0; 
		for(int i=1;i<7;i++) {
			if(dice.random[i]==4) four++;//��¼����Ϊ4��������Ŀ
			else if(a==0) a=dice.random[i];
			else if(b==0) b=dice.random[i];
			else if(c==0) c=dice.random[i];
			else if(d==0) d=dice.random[i];
			else if(e==0) e=dice.random[i];
			else if(f==0) f=dice.random[i];
		}
		
		//�жϽ��� ״Ԫ�ȼ�
		
		//״Ԫ���>������>������>����>���ӵǿ�>״Ԫ
		//������:444444������reward�ĵ�2λ
		if(four==6) {
			reward1[2]=1;  
			System.out.println("�����죬�������״Ԫ��ѡ��");
		}
		//�����ڣ�XXXXXX(û��4����)
		else if(four==0 && a==b && b==c && c==d && d==e && e==f) {
			reward1[3]=1;
			System.out.println("�����ڣ��������״Ԫ��ѡ��");
		}
		//������44444X
		else if(four==5) {//��ͬ�������Ƚϣ�6>5>3>2>1
			if(a==6) reward1[4]=6;
			else if(a==5) reward1[4]=5;
			else if(a==3) reward1[4]=3;
			else if(a==2) reward1[4]=2;
			else if(a==1) reward1[4]=1;
			System.out.println("�������������״Ԫ��ѡ��");
		}
		//���ӵǿ�:XXXXXA
		else if(a==b && b==c && c==d && d==e){
			if(four==1 && reward6<numF) {//��������һ������ʱ�������Ҳͬʱ���һ��
				reward6++; 
				player.result[6]++;
			}
			//��ͬ�����ӵǿƱȽϴ�С6>5>3>2>1
			if(a==6) reward1[5]=6;
			else if(a==5) reward1[5]=5;
			else if(a==3) reward1[5]=3;
			else if(a==2) reward1[5]=2;
			else if(a==1) reward1[5]=1;
			System.out.println("���ӵǿƣ��������״Ԫ��ѡ��");
		}
		//״Ԫ��𻨣�444411
		else if(four==4){
			if(a==1 && b==1) {
				reward1[1]=1;
				System.out.println("״Ԫ��𻨣���ϲ���״Ԫ��");
			}
			else {
				reward1[6]=1;//��ͨ״Ԫ
				System.out.println("״Ԫ��������˺�ѡ��");
			}
		}
		
		//���ۣ�123456��ȫ����������ȣ�����һ��four��
		else if(four==1 && a!=b && b!=c && c!=d && d!=e && a!=c && a!=d && a!=e && b!=d && b!=e && c!=e)
		{
			if(reward2<numB) {//�������ж�������ʱ
				reward2++; 
				player.result[2]++; 
				System.out.println("��ϲ��ö��ã�");
			}
			else{ 
				System.out.println("��Ǹ�����������Ѿ�û������");
			}
		}
			
		//̽����444XXX
		else if(four==3) 
		{
			if(reward3<numC){//����������������ʱ
				reward3++; player.result[3]++; 
				System.out.println("��ϲ������죡");
			}
			else  { 
				System.out.println("��Ǹ�����������Ѿ�û������");
			}
		}
		//��ʿ��AAAAXX
		else if(a==b && b==c && c==d )
		{
			if(four==2)//��������4���������
			{
				if(reward5<numE) {
					reward5++; player.result[5]++; 
					System.out.println("��ϲ��ö��٣�");
				}
				else  { 
					System.out.println("��Ǹ�������Ѿ�û������");
				}
			}
			else if(four==1)//�������������Ի��һ��
			{
				if(reward6<numF) {
					reward6++; player.result[6]++; 
					System.out.println("��ϲ���һ�㣡");
				}
				else  { 
					System.out.println("��Ǹ��һ���Ѿ�û������");
				}
			}
			if(reward4<numD) {
				reward4++; player.result[4]++; 
				System.out.println("��ϲ����Ľ���");
				}
			else  { 
				System.out.println("��Ǹ���Ľ��Ѿ�û������");
			}
		}
		//���ˣ�44XXXX
		else if(four==2) 
		{
			if(reward5<numE) {
				reward5++; player.result[5]++; 
				System.out.println("��ϲ��ö��٣�");
		}
			else { 
				System.out.println("��Ǹ�������Ѿ�û������");
			}
		}
		//���:4XXXXX
		else if(four==1) 
		{
			if(reward6<numF) {
				reward6++; player.result[6]++;
				System.out.println("��ϲ���һ�㣡");
			}
			else  { 
				System.out.println("��Ǹ��һ���Ѿ�û������");
			}
		}
	}
 
//�ж���Ϸ����
	public boolean isOver()
	{
		if(!(reward2==2 && reward3==4 && reward4==8 && reward5==16 && reward6==32) ) {
			return false;
		}
  	    for(int i=1;i<7;i++) {
		   if(reward1[i]!=0) {
			   return true;//����״Ԫ�����н��ȷ����
		   }
	    }
	    return false;	
	}
}