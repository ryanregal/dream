//���ߣ�����Ƽ
//������

public abstract class Animal {
	private String animalType;//����
	private String number;//���
	private String sex;//�Ա�
	private String color;//ëɫ
	
	//Setters Getters
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) throws IllegalSexException {
		if(sex.equals("��")||sex.equals("��")||sex.equals("˫��")){
			this.sex = sex;
		}
		else {
			System.out.println("�Ա�Ӧ�����ۣ��ƣ���˫��");
			throw new IllegalSexException();	
		}
		
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	
	//���캯��
	public Animal(String[] inputs) throws IllegalSexException 
	{
		this.animalType=inputs[0];
		this.number=inputs[1];
		setSex(inputs[2]);
	}
	
	//��ӡ����
	public abstract void printAnimal();
	
}
