package lab13_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test 
{
	public static void main(String[] args)
	{		
		Printer writer1 = new Printer(1);
		Printer writer2 = new Printer(11);
		Printer writer3 = new Printer(21);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(writer1);
		executorService.execute(writer2);
		executorService.execute(writer3);		
		executorService.shutdown();
		
		try{
			boolean tasksEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);
			
			if(tasksEnded)
			{
				System.out.println("\n��ӡ������");
			}
			else
				System.out.println("�̳߳�ʱ��");
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
}

