import java.util.*;

//ͼ�����
public class Library {
	
	//�����鼮��Item������
	static ArrayList<Item> itemList = new ArrayList<>();
	static Scanner scanner=new Scanner(System.in);

	//��ӡ���˵�
	public static void printMenu() {
		System.out.println("\n�����������ţ�");
		System.out.println("a.	¼��");
		System.out.println("b.	��ѯ");
		System.out.println("c.	�鿴���б�");
		System.out.println("d.	�˳�");
	}
	
	//��ӡ��Ӳ˵�
	public static void addMenu() {
		System.out.println("\n�밴�����¸�ʽ���룺\n"
		 +"\t�� ISBN ���� ���� ������\n"
		 +"\t��־ ISBN ��־�� ������ �ں� ������\n"
		 +"�磺\n"
		 +"\t�� 9787121340932 Java������� Լ��������˹ ���ӹ�ҵ������\n"
		 +"\t��־ 9771009633001 �й����ҵ��� 2021 1 �й����ҵ��������\n"
		 +"���г�������ںű���Ϊ����");
	}
	
	//��ӡ��ѯ�˵�
	public static void searchMenu() {
		System.out.println("\n�밴�����¸�ʽ�����ѯ��Ϣ:");
		System.out.println("\t�� ISBN ���� ���� ������");
		System.out.println("\t��־ ISBN ��־�� ������ �ں� ������\n��:");
		System.out.println("\t�� 9787121340932 Java������� Լ��������˹ ���ӹ�ҵ������");
		System.out.println("\t��־ 9771009633001 �й����ҵ��� 2021 1 �й����ҵ��������");
		System.out.println("���У��յ��ֶξ����� null ����");
	}
	
	//����鼮��ͼ���
	public static void addItem() throws Exception{
		addMenu();//��ӡ��Ӳ˵�
		String input=scanner.nextLine();//���������ַ���
		int flag=0;//�ж��Ƿ�������
		
		
		while(flag!=1) {
			//��һ���ַ���ͨ���ո�ָ�
			String[]inputArray=input.split("[ ]+");
			
			if(inputArray[0].equals("��")) {
				//���Class���󡢻�ù��졢ͨ�����������ʵ��������
				Book addBook=(Book) Class.forName("Book").getConstructor(String[].class).newInstance((Object)inputArray);
				itemList.add(addBook);
				String booknumber=String.format("%05d", itemList.size());
				addBook.setNumber(booknumber);
				System.out.println("�����ɹ�,�Ƿ����¼�룿(��/��)");
				input=scanner.nextLine();//���������ַ���
				if(!input.equals("��")) flag=1;
			}
			
			else if(inputArray[0].equals("��־"))
			{
				Magazine addMagazine = (Magazine) Class.forName("Magazine").getConstructor(String[].class).newInstance((Object)inputArray);
				itemList.add(addMagazine);
				String booknumber=String.format("%05d", itemList.size());
				addMagazine.setNumber(booknumber);
				System.out.println("�����ɹ����Ƿ����¼�룿(��/��)");
				input=scanner.nextLine();//���������ַ���
				if(!input.equals("��")) flag=1;
			}
			else{
				throw new IllegalTypeException();
			}
			if(flag==1) break;
			else{
				addMenu();//��ӡ��Ӳ˵�
				input=scanner.nextLine();//���������ַ���
			}
		}
	}
	
	//��ѯͼ����е��鼮
	public static void searchItem() {
		
		if(itemList.size() == 0)
		{
			System.out.println("��ǰ���Ϊ�գ��޷����в�ѯ����������鱾��");
			return;
		}
		
		ArrayList<Item> output=new ArrayList<>();//�洢��ӡ�б�
		searchMenu();//��ӡ��ѯ�˵�
		String searchString=scanner.nextLine();//��ȡ�����ַ���
		String[] searchArray = searchString.split("[ ]+");
		int hasFlag=0;
		
		if( searchArray[0].equals("��") || ( searchArray[0].equals("null") && searchArray.length==5))
		{
			for(Item item : itemList){
				if (	(searchArray[0].equals("null") || item.getType().equals(searchArray[0]))
						&& (searchArray[1].equals("null") || item.getIsbn().equals(searchArray[1]))
						&& (searchArray[2].equals("null") || item.getName().equals(searchArray[2]))
						&& (searchArray[3].equals("null") || ((Book)item).getAuthor().equals(searchArray[3]))
						&& (searchArray[4].equals("null") || (item).getPublisher().equals(searchArray[4]))
					) {
					output.add(item);
					hasFlag=1;
				}
			}
		}
		
		else if(searchArray[0].equals("��־")  || ( searchArray[0].equals("null") && searchArray.length==6))
		{
			for(Item item : itemList){
				if (	(searchArray[0].equals("null") || item.getType().equals(searchArray[0]))
						&& (searchArray[1].equals("null") || item.getIsbn().equals(searchArray[1]))
						&& (searchArray[2].equals("null") || item.getName().equals(searchArray[2]))
						&& (searchArray[3].equals("null") || ((Magazine)item).getYear() == Integer.parseInt(searchArray[3]))
						&& (searchArray[4].equals("null") || ((Magazine)item).getIssueNumber().equals(searchArray[4]))
						&& (searchArray[5].equals("null") || (item).getPublisher().equals(searchArray[5]))
					) {
					System.out.println("�ҵ�һ����־��");
					output.add(item);
					hasFlag=1;
				}
			}
		}
		
		if(hasFlag==1) {//��ӡ���
			System.out.printf("������%d����,��Ϣ���£�\n",output.size());
			for(Item item : output){
				item.printItem();
			}
			System.out.print("\n");
		}
		else System.out.println("û����������Ӧ�鼮");
		output.clear();//�������б�
	}
	
	//��ʾ�����鼮
	public static void printAll()
	{
		if(itemList.size() == 0)
		{
			System.out.println("Ŀǰû���鱾��");
			return;
		}
		
		System.out.println("Ŀǰ��" + itemList.size() + "���飬��Ϣ���£�");
		for(Item item : itemList)
		{
			item.printItem();
		}
		System.out.printf("\n");
	}
	
	//main����
	public static void main(String[] args) 
	{
		String choice;
		boolean runflag = true;
		
		while(runflag)
		{
			printMenu();
			choice=scanner.nextLine();
			
			switch(choice)
			{
			case "a":
				try { addItem();}
				catch(IllegalTypeException e) 
				{
					System.out.println("�鼮����ֻ�ܴ洢�鿯����־\n");
					System.out.println("�������ɹ�");
				} 
				catch (Exception e) {
					System.out.println("��������\n");
					System.out.println("�������ɹ�");
				}
				break;	
			case "b":
				searchItem();
				break;
			case "c":
				printAll();
				break;
			case "d":
				runflag = false;
				break;
			default:
				System.out.println("�������������a-d����ĸ");
				break;
			}
		}
		System.out.println("�˳�����");
		return;
	}	

}
