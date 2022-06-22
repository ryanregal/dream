//�ؾ���
public abstract class Vehicle {
	
	private String tradeMark;//�̱�  
	private String color;//��ɫ  
	private int factoryYear;//������
	private String carType;//С�������ǿ���
	private double capacity;	//С�����ؿ����򿨳�������

	//���캯��
	public Vehicle(String[] inputs)
	{
		this.carType = inputs[0];
		this.tradeMark = inputs[1];
		this.color = inputs[2];
		this.capacity = Double.parseDouble(inputs[3]);
		this.factoryYear=Integer.parseInt(inputs[4]);
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
	public void setFactoryYear(int factoryYear) {
		this.factoryYear = factoryYear;
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
		this.capacity = capacity;
	}

	//��ӡ�ؾ�
	public abstract void printVehicle();
	
}
