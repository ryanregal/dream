//���ߣ�����Ƽ
//������ �̳г�����Animal

public class Ostrich extends Animal{
	private int eggsNumber;//������

	//Setters Getters
	public int getEggsNumber() {
		return eggsNumber;
	}
	public void setEggsNumber(int eggsNumber) {
		this.eggsNumber = eggsNumber;
	}
	
	//���캯��
	public Ostrich(String[] inputs) throws IllegalSexException 
	{
		super(inputs);
		setColor(inputs[3]);
		this.eggsNumber=Integer.parseInt(inputs[4]);
	}
	
	//��д���
	@Override
	public void printAnimal()
	{
		System.out.printf("%s %s %s %s %d\n", getAnimalType(), getNumber(),
				getSex(),getColor(), this.eggsNumber);
	}
}
