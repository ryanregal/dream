public class MonthTest {
	public static void main(String[] args)
	{
		//.values()��ö����ת��Ϊһ��ö�����͵�����,�������
		for(Month.monthenmu i : Month.monthenmu.values())
		{
			System.out.println(i.getAbbr());
			System.out.println(i.getFullName());
		}
	}
}
