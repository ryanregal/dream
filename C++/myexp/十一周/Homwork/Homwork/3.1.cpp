#include<iostream>
using namespace std;

//AΪ����
class A { 
protected:
	int x,y; 
public:
	void f(){
		cout << x << endl;//���Ա����ڲ��ĳ�Ա��������
	};
}; 

class B :public A { 
public:
	void h() {
		cout << x << y << endl; //�ܹ���������ĳ�Ա��������
		f();
	}
}; 

int main() {
	A a;
	//cout << a.x << endl;//ERROR:���������main���������ܽ��з���
	B b;
	//cout << b.x << endl;//ERROR
	a.f();
	b.h();
}