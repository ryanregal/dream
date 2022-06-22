package lab14_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircularBufferTest 
{
	public static void main(String[] args)
	{
		//һ�� Executor���ṩ������ֹ�ķ����Ϳ��Ը���һ�������첽����Ľ��ȵķ�����
		//����һ���̳߳أ�������Ҫ�������̣߳����ڿ���ʱ��������ǰ������̡߳�
		ExecutorService application = Executors.newCachedThreadPool();
		
		CircularBuffer sharedLocation = new CircularBuffer();
		
		sharedLocation.displayState("Initial State");
		
		//�����߳��������1-10�����������һ������10��Ԫ�ص�ѭ��������
		//һ���̴߳�����ȡ��Ԫ�ز������
		application.execute(new Producer(sharedLocation));
		application.execute(new Producer(sharedLocation));
		application.execute(new Producer(sharedLocation));
		application.execute(new Consumer(sharedLocation));
		
		application.shutdown();
	}
}
