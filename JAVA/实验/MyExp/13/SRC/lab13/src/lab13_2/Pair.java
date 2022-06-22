package lab13_2;

import java.util.HashMap;
import java.util.Map;

//�����������Ͳ���F��S��ͨ�����
//��ͷ�ǹ������<F��S>
public class Pair <F, S>
{
	private Map<F, S> elements;
	//���캯�������ó�ʼ��HashMap��������С
	public Pair(int capacity)
	{
		int initCapacity = capacity > 0? capacity : 10;
		elements = new HashMap<F, S>(initCapacity);
	}
	
	public Pair()
	{
		this(10);
	}
	
	//Ϊ���Ԫ���еĵ�һ��Ԫ�غ͵ڶ���Ԫ�����get��set������
	public S get(F keyValue)
	{
		return elements.get(keyValue);//����ֵ����S
	}
	public void set(F addKey, S addValue)
	{
		elements.put(addKey, addValue);//����ֵ�Լ���Map��
	}
}
