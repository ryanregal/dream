//����һ���������࣬�̳���Graph����������д��area����
public class Triangle extends Graph{
	double a;
	double b;
	double c;
	Triangle(int a,int b,int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	//��д������󷽷�
	public int area() {
		double p = (a+b+c)/2.0;
		return (int) Math.floor(Math.sqrt(p*(p-a)*(p-b)*(p-c)));
	}
}
