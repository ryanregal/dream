//С������
public class Car extends Vehicle{

	private String carriagesNumber;//������ 
	
	//���캯��
	public Car(String[] inputs) {
		super(inputs);
		this.carriagesNumber = inputs[5];
	}

	//Getters Setters
	public String getCarriagesNumber() {
		return carriagesNumber;
	}
	public void setCarriagesNumber(String carriagesNumber) {
		this.carriagesNumber = carriagesNumber;
	}
	
	//��дС�������
	@Override
	public void printVehicle()
	{
		System.out.printf("%s��Ʒ�ƣ�%s ��ɫ��%s ������ݣ�%d", getCarType(), getTradeMark(),
				getColor(), getFactoryYear());
		System.out.printf(" �ؿ�����%.0f��  ������%s\n",  
				getCapacity(), carriagesNumber);
	}
	
}
