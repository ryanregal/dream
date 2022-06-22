#include<iostream>
using namespace std;
//���������shape��������˵��һ�����麯��area()��Ϊ�ӿڡ����������ж������ĺ���ʵ�֡�

class Shape // �������
{
public:
	virtual void printArea() = 0; // ���麯��
};
class Circle :public Shape // ���� Circle ��
{
public:
	Circle(float r) :radius(r) {}; // ���幹�캯��
	virtual void printArea() // ���麯���ٶ���
	{
		cout << "Area of Circle��" << endl << 3.14159 * radius * radius << endl;
	}
private:
	float radius;
};

class Rectangle :public Shape // ���� Rectangle ��
{
public:
	Rectangle(float w, float h) :width(w), height(h) {}; // ���幹�캯��
	virtual void printArea() // ���麯���ٶ���
	{
		cout << "Area of Rectangle��" << endl << width * height << endl;
	}
private:
	float width;
	float height;
};

class Square:public Shape // ���� Square ��
{
public:
	Square(float w) :length(w) {}; // ���幹�캯��
	virtual void printArea() // ���麯���ٶ���
	{
		cout << "Area of Square��" << endl << length * length << endl;
	}
private:
	float length;
};

class Triangle :public Shape // ���� Triangle ��
{
public:
	Triangle(float w, float h) :width(w), height(h) {}; // ���幹�캯��
	virtual void printArea() // ���麯���ٶ���
	{
		cout << "Area of Triangle��" << endl << 0.5 * width * height << endl;
	}
private:
	float width;
	float height;
};

int main()
{
	Circle circle(4.5);        // ���� Circle ����� circle
	circle.printArea();        // ��� Circle �����
	Rectangle rectangle(2, 6); // ���� Rectangle ����� rectangle
	rectangle.printArea();     // ��� rectangle �����
	Triangle triangle(3, 5);   // ���� Triangle ����� 
	triangle.printArea();      // ��� triangle �����
	Square square(3);   // ���� Triangle ����� 
	square.printArea();      // ��� triangle �����
	return 0;
}
