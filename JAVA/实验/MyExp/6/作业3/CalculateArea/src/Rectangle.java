//����һ���������࣬�̳���Graph����������д��area����
public class Rectangle extends Graph{
	double a;
	double b;
	Rectangle(double a,double b)
	{
		this.a = a;
		this.b = b;
	}
	//��д������󷽷�
	public int area() 
	{
		return (int) Math.floor(a*b);
	}
}