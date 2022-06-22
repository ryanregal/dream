package lab14_1;


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
		for(int i = 1; i <= 10; i++)
		{
			try	//��˯0-3�룬Ȼ���value�Ž�Buffer��
			{
				Thread.sleep(generator.nextInt(3000));	//���˯��
				sharedLocation.blockingPut(1 + generator.nextInt(10));//��value�Ž�Buffer��
			}
			catch(InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("\nProducer done producing\nTerminating Producer\n");
	}
}
