package lab14_1;

//��һ��������Ԫ�ص�buffer������ʵ��ͬ��
//ѭ�����������Լ��������
public class CircularBuffer implements Buffer
{
	private final int[] buffer = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	
	private int occupiedCells = 0;	//�������м���buffers��ռ��
	private int writeIndex = 0;		//��һ��Ҫд��Ԫ�ص��±�
	private int readIndex = 0;		//��һ��Ҫ����Ԫ�ص��±�
	
	public void displayState(String operation) 
	{
		System.out.printf("%s%s%d)\n%s", operation,
				" (buffer cells occupied: ", occupiedCells, "buffer Cells:  ");
		
		for(int value : buffer)
		{
			System.out.printf(" %2d  ", value);		//չʾbuffer���value
		}
		
		System.out.print("\n               ");
		
		for(int i = 0; i < buffer.length; i++)
		{
			System.out.print("---- ");
		}
		
		System.out.print("\n               ");
		
		for(int i = 0; i < buffer.length; i++)
		{
			if(i == writeIndex && i == readIndex)
				System.out.print(" WR");	//ͬʱ��д
			else if(i == writeIndex)
				System.out.print(" W   ");  //ֻд
			else if(i == readIndex)
				System.out.print("  R  ");  //ֻ��
			else
				System.out.print("     ");	//ʲô��û��
		}
		
		System.out.println("\n");
	}
	
	//synchronized����һ�������������εķ�����Ϊͬ�������������õķ�Χ����������
	//���õĶ����ǵ�����������Ķ���
	public synchronized void blockingPut(int value) throws InterruptedException
	{
		//һֱ�ȵ�buffer�п�λ��Ȼ����д������
		//��û�п�λ��ʱ�򣬾Ͱ��̷߳�������״̬
		while(occupiedCells == buffer.length)
		{
			System.out.printf("Buffer is full. Producer waits\n");
			wait();	//�ȵ�buffer cell�п�λ
		}
		
		buffer[writeIndex] = value;	//������ֵ
		
		//��һ��Ҫд��λ�ã�ѭ������
		writeIndex = (writeIndex + 1) % buffer.length;
		
		occupiedCells++;
		displayState("Producer writes " + value);
	    notifyAll(); // notify threads waiting to read from buffer
	}
	
	public synchronized int blockingGet() throws InterruptedException
	{
		//�ȵ������ݵ�ʱ���ٶ���û���ݾ͵�
		while(occupiedCells == 0)
		{
			System.out.printf("Buffer is empty. Consumer watis.\n");
			wait();
		}
		
		int readValue = buffer[readIndex];
		
		readIndex = (readIndex + 1) % buffer.length;//ѭ����ȡ
		
		occupiedCells--;
		displayState("Consumer reads " + readValue);
		notifyAll();
		
		return readValue;
	}
}
