#include <iostream>
#include <stdlib.h>
#include <string>
using namespace std;

class vehicle //������
{
protected:
	int wheels; // ������
	double weight; // ����
	bool isrun;//�Ƿ�����
public:
	vehicle(int a, double b); //���캯��
	int GetWheels() { return wheels; }
	double GetWeight() { return weight; }
	void GetStart() { isrun = 1; cout << "��������" << endl; }//����
	void GetStop() { isrun = 0; cout << "����ֹͣ" << endl; }//ֹͣ
	void show();
};
vehicle::vehicle(int a, double b) //���캯��
{
	isrun = 0;//��ʼδ����
	wheels = a;
	weight = b;
}
void vehicle::show()
{
	cout << "��������" << wheels << endl;
	cout << "������" << weight << endl;
}

class car :public vehicle //С������
{
	int passenger;//������
public:
	car(int wheels1, double weight1, int passenger1);//���캯��
	void show();//��ʾ��������Ϣ
};
//���캯��
car::car(int wheels1, double weight1, int passenger1) :vehicle(wheels1, weight1)
{
	passenger = passenger1;
}
//��ʾ��������Ϣ
void car::show()
{
	cout << "С���ࣺ" << endl;
	vehicle::show();
	cout << "��������" << passenger << endl;
}
//��ʾ��������Ϣ
class truck :public vehicle //������
{
	double payload;//������
public:
	truck(int wheels1, double weight1,double payload1);//���캯��
	void show();
};
//���캯��
truck::truck(int wheels1, double weight1, double payload1) :vehicle(wheels1, weight1)
{
	payload = payload1;
}
//��ʾ��������Ϣ
void truck::show()
{
	cout << "�����ࣺ" << endl;
	vehicle::show();
	cout << "��������" << payload << endl;
}

int main()
{
	car a(4, 100, 5);
	truck b(6, 500, 300);
	a.GetStart();
	b.GetStart();
	a.show();
	cout << endl;
	b.show();
	a.GetStop();
	b.GetStop();
	system("PAUSE");
	return 0;
}

