package lab11_4;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class channelTest 
{
	public static void main(String[] args) throws IOException
	{
		RandomAccessFile File1 = new RandomAccessFile("src/lab11_4/file1.txt", "rw");
		RandomAccessFile File2 = new RandomAccessFile("src/lab11_4/file2.txt", "rw");
		FileChannel channelFirst = File1.getChannel();
		FileChannel channelSecond = File2.getChannel();
		
		String newData = "Magic always comes with a price.";
		String newData2 = "Love is the most powerful magic.";
		
		//����д,�ļ�1
//		ByteBuffer buf = ByteBuffer.allocate(50);
//		buf.clear();
//		buf.put(newData.getBytes());
//		//��ת�˻������� ����������Ϊ��ǰλ�ã�Ȼ��λ������Ϊ�㡣 �������Ѷ��壬���䶪��
//		//��һϵ��ͨ����ȡ����ò���֮�󣬵��ô˷�����׼��һϵ��ͨ��д�����ػ�ȡ����
//		buf.flip();
//		while(buf.hasRemaining()) channelFirst.write(buf);
		
		//�Ǹ���д��ֱ����д��
		//����һ���µ��ֽڻ�����
		//�»�������λ�ý�Ϊ�㣬�����ƽ��������������ǽ�δ���壬������ÿ��Ԫ�ؽ���ʼ��Ϊ�㡣 ������һ�������飬������ƫ����Ϊ�㡣
		ByteBuffer buf = ByteBuffer.allocate(50);
		//�Ե�һ���ļ�����д
		if(channelFirst.size() == 0)
		{
			channelFirst.position(channelFirst.size());
			//���ֽ������װ���������С�
			//�»��������ɸ������ֽ�����֧�֣�Ҳ����˵���Ի��������޸Ľ��������鱻�޸ģ���֮��Ȼ��
			//�»����������������ƽ�Ϊarray.length����λ��Ϊ�㣬����Ϊδ���塣 ���ĺ����齫�Ǹ��������飬������������ƫ����Ϊ�㡣
			channelFirst.write(ByteBuffer.wrap(newData.getBytes()));
		}
		else
		{
			//��ӻس�
			channelFirst.position(channelFirst.size());
			channelFirst.write(ByteBuffer.wrap("\n".getBytes()));
			//�ڻس����������Ҫ����ľ���
			channelFirst.position(channelFirst.size());
			channelFirst.write(ByteBuffer.wrap(newData.getBytes()));
		}
		//�Եڶ����ļ�����д
		if(channelSecond.size() == 0)
		{
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap(newData2.getBytes()));
		}
		else
		{
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap("\n".getBytes()));
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap(newData2.getBytes()));
		}
		
		//�ر��ļ�
		File1.close();
		File2.close();
		
		//��ȡ
		File1 = new RandomAccessFile("src/lab11_4/file1.txt", "rw");
		File2 = new RandomAccessFile("src/lab11_4/file2.txt", "rw");
		channelFirst = File1.getChannel();
		channelSecond = File2.getChannel();
		buf = ByteBuffer.allocate(50);
		//����ļ�1������
		System.out.println("File1����");
		while(channelFirst.read(buf) != -1) //��û�н���ʱ����ӡÿ��buf
		{
			buf.flip();
			while(buf.hasRemaining()) System.out.print((char)buf.get());
			buf.clear();
		}
		System.out.println();
		//����ļ�2������
		System.out.println("\nFile2����");
		while(channelSecond.read(buf) != -1)
		{
			buf.flip();
			while(buf.hasRemaining())  System.out.print((char)buf.get());
			buf.clear();
		}
		
		File1.close();
		File2.close();
	}
}
