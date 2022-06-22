package lab12_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileWords 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String line;
		File file = new File("src/lab12_4/TheGreatDictator.txt");
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
		Pattern p=Pattern.compile("[;.,\"\\?!:������(){}']");//
		while(scan.hasNextLine())
		{
			line = scan.nextLine();
			//��һ�а��տո��-�ָ���,+��ʾ1�λ���
			String[] lineWords = line.split("[ -]+");
			for(int i = 0; i < lineWords.length; i++)
			{
			    //��Pattern���matcher()��������һ��Matcher����
				Matcher m= p.matcher(lineWords[i]);
				//����Щ�����ַ���ɾ��
				lineWords[i] = m.replaceAll("");
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
		System.out.println("�����а����ĵ������£�");
		//���ճ��ִ����������
		//entrySet����ӳ���а�����ӳ��ļ�����ͼ��������ӳ��֧�֣���˶�ӳ��ĸ��Ļᷴӳ�ڼ����У���֮��Ȼ��
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>()
		{
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
			{
				return o2.getValue().compareTo(o1.getValue());//��д����ϣ���ǱȽ�ֵ
			}
		});
		
		int wordsCount = 0;
		int prevalue=0;
		for(Map.Entry<String, Integer> entry : list)
		{
			//ʹ��keySet()������ȡ���е�keyֵ
			if(wordsCount==0) {
				prevalue=entry.getValue();
				System.out.println(entry.getValue()+":\t"+entry.getKey() );
			}
			else if(entry.getValue()!=prevalue) {
				System.out.println(entry.getValue()+":\t"+entry.getKey() );
			}
			else System.out.println("\t"+entry.getKey() );
			wordsCount += entry.getValue();
			prevalue=entry.getValue();
		}
		System.out.println("\n�ܵ�������" + wordsCount);
	}
}
