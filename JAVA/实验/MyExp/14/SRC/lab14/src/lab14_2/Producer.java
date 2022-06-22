package lab14_2;


import java.security.SecureRandom;

public class Producer implements Runnable
{
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation;	//Ҫ����Ķ��������
	
	public Producer(Buffer sharedLocation)
	{
		this.sharedLocation = sharedLocation;
	}
	
	//��sharedLocation�д洢1-10�������
	public void run()
	{
		//int sum = 0;
		
		for(int i = 1; i <= 10; i++)
		{
			try	//��˯0-3�룬Ȼ���value�Ž�Buffer��
			{
				Thread.sleep(generator.nextInt(3000));	//���˯��
				sharedLocation.blockingPut(i);			//��value�Ž�Buffer��
				//sum += i;	//��value�ۼ�
				//System.out.printf("\t%2d\n", sum);
			}
			catch(InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
		
		System.out.println("\nProducer done producing\nTerminating Producer");
	}
}
