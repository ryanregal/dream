/*��дһ�������������Ͳ���F��S��ͨ����ԣ�
 *ÿ�������ֱ��ʾ�Եĵ�һ���͵ڶ���Ԫ�ص����͡�
 *Ϊ���Ԫ���еĵ�һ��Ԫ�غ͵ڶ���Ԫ�����get��set������
 *[��ʾ����ͷӦ���ǹ������<F��S>��]*/

package lab13_2;

public class lab13_2 
{
	public static void main(String[] args)
	{
		Pair<String, Integer> p1 = new Pair<>();//keyΪString��valeΪInteger
		
		p1.set("���������", 1);
		p1.set("��������ԭ��", 2);
		p1.set("ʵ�ò���ϵͳ", 3);
		
		System.out.println(p1.get("���������"));
		System.out.println(p1.get("��������ԭ��"));
		System.out.println(p1.get("ʵ�ò���ϵͳ"));
	}
}
