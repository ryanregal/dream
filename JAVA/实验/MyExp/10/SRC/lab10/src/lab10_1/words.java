package lab10_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class words 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String line;
		//���ļ�report
		File file = new File("src/lab10_1/report.txt");
		if(!file.exists()) 
		{
			System.err.println("No such file!");
			return;
		}
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
		//����һ��String��Integer��ֵ�Ĺ�ϣ��
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//ʹ��Pattern.compile������ʵ�ֶ�ָ���ַ����Ľ�ȡ
		//����Pattern����p���ұ���������ʽ
		Pattern p=Pattern.compile("[.,\"\\?!:������(){}]");
		while(scan.hasNextLine())
		{
			line = scan.nextLine();
			//��һ�а��տո�ָ���,+��ʾ1�λ���
			String[] lineWords = line.split("[ ]+");
			for(int i = 0; i < lineWords.length; i++)
			{
				////��Pattern���matcher()��������һ��Matcher����
				Matcher m= p.matcher(lineWords[i]);
				//����Щ�����ַ���ɾ��
				lineWords[i] = m.replaceAll("");
				//System.out.println(lineWords[i]);
				if(!lineWords[i].isEmpty())
				{
					//�����Ϊ�գ��򽫸õ��ʼ��뵽��ϣ���key�У���ӦΪ1
					if(!hashMap.containsKey(lineWords[i]))
					{
						hashMap.put(lineWords[i], 1);
					}
					else
					{
						int times = hashMap.get(lineWords[i]);
						times++;//��¼���ʳ����˼���
						hashMap.put(lineWords[i], times);
					}
				}
				
			}
		}
		System.out.println("The article contains the following words��\n");
		//ʹ��keySet()������ȡ���е�keyֵ
		Iterator<String> it = hashMap.keySet().iterator();
		while(it.hasNext())
		{
			String word = it.next();
			//����õ��ʳ��ִ�������1���ӡ
			if(hashMap.get(word) >= 1)
				System.out.println(word);
		}
	}
}
