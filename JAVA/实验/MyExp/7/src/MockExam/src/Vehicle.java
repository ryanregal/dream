//�ؾ���
public abstract class Vehicle {
	
	private String tradeMark;//�̱�  
	private String color;//��ɫ  
	private int factoryYear;//������
	private String carType;//С�������ǿ���
	private double capacity;	//С�����ؿ����򿨳�������

	//���캯��
	public Vehicle(String[] inputs) throws IllegalYearException
	{
		this.carType = inputs[0];
		this.tradeMark = inputs[1];
		this.color = inputs[2];
		setCapacity(Double.parseDouble(inputs[3]));
		setFactoryYear(Integer.parseInt(inputs[4]));
	}
	
	//Getters Setters
	public String getTradeMark() {
		return tradeMark;
	}
	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getFactoryYear() {
		return factoryYear;
	}
	public void setFactoryYear(int factoryYear) throws IllegalYearException {
		if(factoryYear<=0||factoryYear>2022) {
			System.out.println("���Ӧ�ô���0С�ڵ���2022");
			throw new IllegalYearException("Year must be> 0 and <=2022!");	
		}
		else this.factoryYear = factoryYear;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		
		if(capacity<=0) {
			System.out.println("�ؾ�����Ӧ�ô���0");
			throw new IllegalArgumentException("Capacity must be >= 0.0f");
		}
		else this.capacity = capacity;
	}

	//��ӡ�ؾ�
	public abstract void printVehicle();
	
}
