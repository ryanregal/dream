#include<iostream>
using namespace std;
//��̳У�������Ӷ����������
class A1 {	// �������A1 
	int a1;
public:
	A1(int i) { a1 = i; cout << "constructor A1." << a1 << endl; }
	void print() { cout << a1 << endl; }
};
class A2 {	// �������A2 
	int a2;
public:
	A2(int i) { a2 = i; cout << "constructor A2." << a2 << endl; }
	void print() { cout << a2 << endl; }
};
class B :public virtual A2, public virtual A1 { // ���������� B������ΪA1��A2 
	int b;
public:
	B(int i, int j, int k, int l) : A1(i), A2(j) {//�����๹�캯�� 
		b = l;
		cout << "constructor B." << b << endl;
	}
	void print(){
		A1::print();
		A2::print();
	}
};
void main(){
	B bb(1, 2, 3, 4);
	bb.print();
}
