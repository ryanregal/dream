#include "iostream"  //����һ����Ϊ���͵��ɾ�̬����̬�󶨵�Ǩ��
using namespace std;

class Base {
public:
	//�麯������
	virtual void disp() { cout << "Base class" << endl; }
};
class Derive1 :public Base {
public:
	//�麯���ض���
	void disp() { cout << "Derive1 class" << endl; }
};
class Derive2 :public Base {
public:
	//�麯���ض���
	void disp() { cout << "Derive2 class" << endl; }
};
void main() {
	Base obj, * p;
	Derive1 obj1;
	Derive2 obj2;
	p = &obj;
	p->disp();
	p = &obj1;
	p->disp();
	p = &obj2;
	p->disp();
	obj1.disp();
	obj2.disp();
}