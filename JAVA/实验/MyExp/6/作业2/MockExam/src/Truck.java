//������
public class Truck extends Vehicle{
	
	//���캯��
	public Truck(String[] inputs) {
		super(inputs);
	}

	//��д�������
	@Override
	public void printVehicle()
	{
		System.out.printf("%s��Ʒ�ƣ�%s ��ɫ��%s ������ݣ�%d", getCarType(), getTradeMark(),
				getColor(), getFactoryYear());
		System.out.printf(" �ػ�����%.2f��\n", getCapacity());
	}
	
}
