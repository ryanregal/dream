#include<iostream> 
using namespace std;

class A{ //�������
private:
	int a ; 
public:
	A(int x) { 
		a = x ;
		cout<<"A's constructor called."<<endl ; 
	}
	void show( ) { 
		cout<<a<<endl;
	} 
};

class C : public A {//���������� 
private:
	int c;
public:
	// �����๹�캯�� 
	//�����������д��βεĹ��캯��ʱ��������ҲӦ�������βεĹ��캯���������������ݸ����๹�캯����
	C(int x, int y, int z) :A(x){ 
		c = z;
		cout << "C's constructor called." << endl;
	}
	void show() {
		A::show();
		cout << c << endl;
	}
};

//������һ���࣬����Ϊ���Ա 
class B{ 
private:
	int b ; 
public: 
	int get( ) { return b;} 
};

class D :public B {
private:
	int d;
public:
	D(int d1) {
		d = d1;//��������������Ĭ����ʽ�Ĺ��캯����δ�������캯��ʱ�������๹�캯�����Բ�����๹�캯�����ݲ�����
	}
};

class E :public B {
private:
	int e;
	//��������δ�������캯������������Ҳ���Բ�������ȫ����Ĭ����ʽ���캯����
};

