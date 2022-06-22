package RationalNumbers;

//����ʹ�÷���ִ����������������
public class Rational {
	private int numerator;//�������ķ���
	private int denominator;//�������ķ�ĸ

	// ���캯��,����ʽ��������
	public Rational(int numerator, int denominator) {
		int commonDivisor = greatestDivisor(numerator, denominator);
		this.numerator = numerator / commonDivisor;
		this.denominator = denominator / commonDivisor;
	}

	// �޲ι��캯��������һ�����ӷ�ĸ��Ϊ1��������
	public Rational() {
		this(1, 1);
	}

	//GETTERS
	public int getNumerator() {
		return numerator;
	}
	public int getDenominator() {
		return denominator;
	}
	
	//SETTERS
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	// ���������������ĺ�
	public static Rational add(Rational number1, Rational number2) {
		int lcd = (Math.abs(number1.getDenominator() * number2.getDenominator()))
				/ greatestDivisor(number1.getDenominator(), number2.getDenominator());

		int numerator1 = (lcd / number1.getDenominator()) * number1.getNumerator();
		int numerator2 = (lcd / number2.getDenominator()) * number2.getNumerator();
		return new Rational(numerator1 + numerator2, lcd);
	}

	// ��������������һ���������Ĳ�
	public static Rational subtract(Rational number1, Rational number2) {
		int lcd = (Math.abs(number1.getDenominator() * number2.getDenominator()))
				/ greatestDivisor(number1.getDenominator(), number2.getDenominator());

		int numerator1 = (lcd / number1.getDenominator()) * number1.getNumerator();
		int numerator2 = (lcd / number2.getDenominator()) * number2.getNumerator();
		return new Rational(numerator1 - numerator2, lcd);
	}
	
	// ���������������Ļ�
	public static Rational multiply(Rational number1, Rational number2){
		return new Rational(number1.getNumerator() * number2.getNumerator(),
				number1.getDenominator() * number2.getDenominator());
	}
	
	// ��������������һ������������
	public static Rational divide(Rational number1, Rational number2){
		   return new Rational(number1.getNumerator() * number2.getDenominator(),
					number1.getDenominator() * number2.getNumerator());
	}
		
	//��a/b����ʽ��ӡ������������aΪ���ӣ�bΪ��ĸ
	public String toString() {
		if(denominator==1) return String.format("%d", numerator);
		return String.format("%d/%d", numerator, denominator);
	}
	
	//���ظ����ʽ��ӡ��������
	//����û��ܹ�ָ��С�����Ҳ�ľ���λ��
	public String toStringFloat(int precision){
		double number = (double)numerator / denominator;
		return String.format("%." + precision + "f", number);
	}
	
	//�������Լ��
	public static int greatestDivisor(int number1, int number2){
		boolean divisorFound = false;
		while (!divisorFound){
			if(number1 == number2)	{
				divisorFound = true;
				break;
			}
			int tempMax = Math.max(number1, number2);
			int tempMin = Math.min(number1, number2);
			number1 = Math.abs(tempMax);
			number2 = Math.abs(tempMin);
			number1 = number1 - number2;
			if(number1 == number2)	divisorFound = true;
		}
		return number1;
	}
}