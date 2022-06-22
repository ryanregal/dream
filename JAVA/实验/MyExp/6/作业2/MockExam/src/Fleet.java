import java.util.*;


//������
public class Fleet {
	
	//���泵�ӵ�Vehicle������
	static ArrayList<Vehicle> carList = new ArrayList<>();
	static Scanner scanner=new Scanner(System.in);
	
	//��ӡ���˵�
	public static void printMenu() {
		System.out.println("\n�������Ӧ���������Ӧ�˵�������");
		System.out.println("1.	��������");
		System.out.println("2.	��ѯ����");
		System.out.println("3.	�г����г���");
		System.out.println("4.	��������");
	}
	
	//��ӡ��Ӳ˵�
	public static void addMenu() {
		System.out.println("\n�����복����Ϣ��ʵ����\n"
		 +"\tС���� ���� ��ɫ 4 2007 2��\n"
		 +"\t���� ��ŵ ��ɫ 3.5 2008\n"
		 +"����������������end");
	}
	
	//��ӡ��ѯ�˵�
	public static void searchMenu() {
		System.out.println("\n�밴�ա����� �̱� ��ɫ �����ꡱ��˳������������������Ϊ���á�null������,����ʾ��:");
		System.out.println("\tС���� ���� ��ɫ 2007");
		System.out.println("\tnull ���� null null");
		System.out.println("����end�����ϼ��˵�");
	}
	
	//��ӳ�����������
	public static void addVehicle() throws Exception{
		addMenu();//��ӡ��Ӳ˵�
		String input=scanner.nextLine();//���������ַ���
		
		while(!input.equals("end")) {
			//��һ���ַ���ͨ���ո�ָ�
			String[]inputArray=input.split("[ ]+");
			
			if(inputArray[0].equals("С����")) {
				//���Class���󡢻�ù��졢ͨ�����������ʵ��������
				Car addCar=(Car) Class.forName("Car").getConstructor(String[].class).newInstance((Object)inputArray);
				carList.add(addCar);
				System.out.println("�����ɹ�");
			}
			
			else if(inputArray[0].equals("����"))
			{
				Truck addTruck = (Truck) Class.forName("Truck").getConstructor(String[].class).newInstance((Object)inputArray);
				carList.add(addTruck);
				System.out.println("�����ɹ�");
			}
			else{
				throw new IllegalTypeException();
			}
			addMenu();//��ӡ��Ӳ˵�
			input=scanner.nextLine();//���������ַ���
		}
	}
	
	//��ѯ�����еĳ���
	public static void searchVehicle() {
		
		if(carList.size() == 0)
		{
			System.out.println("��ǰ����Ϊ�գ��޷����в�ѯ��������ӳ�����");
			return;
		}
		
		ArrayList<Vehicle> output=new ArrayList<>();//�洢��ӡ�б�
		searchMenu();//��ӡ��ѯ�˵�
		String searchString=scanner.nextLine();//��ȡ�����ַ���
		
		while(!searchString.equals("end")) {
			String[] searchArray = searchString.split("[ ]+");
			
			if((searchArray[0].equals("С����") || searchArray[0].equals("����") || searchArray[0].equals("null")))
			{
				for(Vehicle vehicles : carList){
					if (	(searchArray[0].equals("null") || vehicles.getCarType().equals(searchArray[0]))
							&& (searchArray[1].equals("null") || vehicles.getTradeMark().equals(searchArray[1]))
							&& (searchArray[2].equals("null") || vehicles.getColor().equals(searchArray[2]))
							&& (searchArray[3].equals("null") || vehicles.getFactoryYear() == Integer.parseInt(searchArray[3]))
						)
						output.add(vehicles);
				}
				//��ӡ���
				System.out.printf("������%d����,��Ϣ���£�\n",output.size());
				for(Vehicle vehicles : output)
				{
					vehicles.printVehicle();
				}
				System.out.print("\n");
			}
			else System.out.println("û����������Ӧ�Ľ�ͨ����");
			output.clear();//�������б�
			searchMenu();//�ٴδ�ӡ�鵥�˵�
			searchString = scanner.nextLine();//��ȡ�µ��ַ���	
		}
	}
	
	//��ʾ���г���
	public static void printAll()
	{
		if(carList.size() == 0)
		{
			System.out.println("Ŀǰû�г�����");
			return;
		}
		
		System.out.println("Ŀǰ��" + carList.size() + "��������Ϣ���£�");
		for(Vehicle vehicles : carList)
		{
			vehicles.printVehicle();
		}
		System.out.printf("\n");
	}
	
	public static void main(String[] args) 
	{
		int choice = 0;
		boolean runflag = true;
		
		while(runflag)
		{
			printMenu();
			choice=Integer.parseInt(scanner.nextLine());
			
			switch(choice)
			{
			case 1:
				try { addVehicle();}
				catch(IllegalTypeException e) 
				{
					System.out.println("��һ��ӦΪС�������߿���\n");
					System.out.println("�������ɹ�");
				} 
				catch (Exception e) {
					System.out.println("��������\n");
					System.out.println("�������ɹ�");
				}
				break;	
			case 2:
				searchVehicle();
				break;
			case 3:
				printAll();
				break;
			case 4:
				runflag = false;
				break;
			default:
				System.out.println("������1-4������");
				break;
			}
		}
		System.out.println("�˳�����");
		return;
	}	
	
}