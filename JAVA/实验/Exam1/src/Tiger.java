//���ߣ�����Ƽ
//�ϻ���,�̳г�����Animal

public class Tiger extends Animal{
	private double weight;//����
	private int teeth;//����
	
	//Setters Getters
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getTeeth() {
		return teeth;
	}
	public void setTeeth(int teeth) {
		this.teeth = teeth;
	}
	
	//���캯��
	public Tiger(String[] inputs) throws IllegalSexException 
	{
		super(inputs);
		this.weight=Double.parseDouble(inputs[3]);
		setColor(inputs[4]);
		this.teeth=Integer.parseInt(inputs[5]);
	}
	
	//��д���
	@Override
	public void printAnimal()
	{
		System.out.printf("%s %s %s %.0f %s %d\n", getAnimalType(), getNumber(),
				getSex(),getWeight(),getColor(), getTeeth());
	}
}
