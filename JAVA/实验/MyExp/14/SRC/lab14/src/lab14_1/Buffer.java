package lab14_1;

//Buffer�ӿڶ�����Ҫ��Producer��Consumer���õķ���
public interface Buffer 
{
	//��value�ŵ�Buffer��
	public void blockingPut(int value) throws InterruptedException;
	
	//��Buffer�з���ֵ
	public int blockingGet() throws InterruptedException;
	
}
