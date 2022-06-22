//���ߣ�����Ƽ
import java.util.*;

//������
public class AnimalTest {

	//���涯�������Ϣ��Animal����
	static ArrayList<Animal> animalList = new ArrayList<>();
	//���涯��������Ϣ��Animal����
	static ArrayList<ArrayList<String> > vaccineList=new ArrayList<ArrayList<String> >();
	static Scanner scanner=new Scanner(System.in);
	
	//��ӡ���˵�
	public static void printMenu() {
		System.out.println("\n�������Ӧ���������Ӧ�˵�������");
		System.out.println("1.	��������");
		System.out.println("2.	����Ǽ�");
		System.out.println("3.	չʾ���ж�����Ϣ");
		System.out.println("4.	�г�ĳ����������¼");
		System.out.println("5.	��������");
	}
	
	//��ӡ��Ӳ˵�
	public static void addMenu() {
		System.out.println("\n�����붯����Ϣ��ʵ����\n"
		 +"\t�ϻ� N001 �� 167 ��ɫ 30\n"
		 +"\t���� M001 �� ��ɫ 40\n"
		 +"����������������end");
	}
	
	//��ӡ���������Ϣ
	public static void vaccineMenu() {
		System.out.println("\n�밴�����·�ʽ����������Ϣ��\n"
		 +"\tN001 2022��4��15�� Ȯ����\n"
		 +"����������������end");
	}
	
	//��ӡ��ѯ�˵�
	public static void searchMenu() {
	}
	
	//��Ӷ��ﵽ������
	public static void addAnimal() throws Exception{
		addMenu();//��ӡ��Ӳ˵�
		String input=scanner.nextLine();//���������ַ���
		
		while(!input.equals("end")) {
			//��һ���ַ���ͨ���ո�ָ�
			String[]inputArray=input.split("[ ]+");
			
			if(inputArray[0].equals("�ϻ�")) {
				//���Class���󡢻�ù��졢ͨ�����������ʵ��������
				Tiger addTiger=(Tiger) Class.forName("Tiger").getConstructor(String[].class).newInstance((Object)inputArray);
				animalList.add(addTiger);
				System.out.println("�����ɹ�");
			}
			
			else if(inputArray[0].equals("����"))
			{
				Ostrich addOstrich = (Ostrich) Class.forName("Ostrich").getConstructor(String[].class).newInstance((Object)inputArray);
				animalList.add(addOstrich);
				System.out.println("�����ɹ�");
			}
			else{
				throw new IllegalTypeException();
			}
			addMenu();//��ӡ��Ӳ˵�
			input=scanner.nextLine();//���������ַ���
		}
	}
	
	//��ʾ���ж���
	public static void printAll()
	{
		if(animalList.size() == 0)
		{
			System.out.println("Ŀǰû�ж��");
			return;
		}
		System.out.println("һ����" + animalList.size() + "ֻ���");
		for(Animal animal : animalList)
		{
			animal.printAnimal();
		}
		System.out.printf("\n");
	}
	
	//�����������Ϣ
	public static void addVaccine() throws IllegalNumberException {
		vaccineMenu();//��ӡ��Ӳ˵�
		String input=scanner.nextLine();//���������ַ���
		//�����άarraylist�У�[0]��ʾ�����ţ�[1]��ʾ����ʱ�䣬[2]��ʾ��������
		
		while(!input.equals("end")) {
			//��һ���ַ���ͨ���ո�ָ�
			String[]inputArray=input.split("[ ]+");
			int flag=0;
			for(Animal animal : animalList) {
				if((animal.getNumber()).equals(inputArray[0])) {
					ArrayList<String> s=new ArrayList<String>();
					s.add(inputArray[0]);
					s.add(inputArray[1]);
					s.add(inputArray[2]);
					vaccineList.add(s);
					flag=1;
				}
			}
			if(flag==0) throw new IllegalNumberException();
			else System.out.println("����ɹ�!");
			vaccineMenu();//��ӡ��Ӳ˵�
			input=scanner.nextLine();//���������ַ���
		}
	}
	
	//���������¼
	public static void searchVaccine() {
		System.out.println("�����붯����\n");
		if(vaccineList.size() == 0)
		{
			System.out.println("��ǰû�������¼��������ӣ�");
			return;
		}
		searchMenu();//��ӡ��ѯ�˵�
		String searchString=scanner.nextLine();//��ȡ�����ַ���
		
		int flagA=0;
		for(ArrayList<String> inform : vaccineList) {
			if(inform.get(0).equals(searchString)) {
				flagA=1;
				System.out.println(inform.get(0)+' '
				+inform.get(1)+' '+inform.get(2));
			}
		}
		if(flagA==0) System.out.println("����ؼ�¼");
	}
	
	//main����
	public static void main(String[] args)
	{
		int choice = 0;
		boolean runflag = true;
		
		while(runflag)
		{
			printMenu();
			try{choice=Integer.parseInt(scanner.nextLine());} 
			//û����������
			catch (Exception e) {
				System.out.println("������1-5����");
				choice=0;
			}
			
			switch(choice)
			{
			case 0:
				break;
			case 1:
				int flagadd=1;
				while(flagadd==1) {
					flagadd=0;
					try { addAnimal();} 
					//���붯�����ʹ���
					catch (IllegalTypeException e) {
						System.out.println("��һ��Ӧ��Ϊ�ϻ���������");
						System.out.println("�������ɹ�");
						flagadd=1;
					}
					//���붯���Ա����Ӧ���Ǵơ��ۻ���˫��
					catch (IllegalSexException e) {
						flagadd=1;
					}
					catch (Exception e) {
						System.out.println("�����ʽ��������������");
						System.out.println("�������ɹ�");
						flagadd=1;
					}
				}
				break;	
			case 2:
				int flagB=1;
				while(flagB==1) {
					flagB=0;
					try{addVaccine();}catch(IllegalNumberException e) {
						System.out.println("�����Ŵ���!");
						flagB=1;
					}
					catch(Exception e) {
						System.out.println("�����ʽ����");
						flagB=1;
					};
				}
				break;
			case 3:
				printAll();
				break;
			case 4:
				searchVaccine();
				break;
			case 5:
				runflag = false;
				break;
			default:
				System.out.println("������1-5����");
				break;
			}
		}
		System.out.println("�˳�����");
		return;
	}

}
