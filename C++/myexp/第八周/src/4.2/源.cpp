#include <iostream>
using namespace std;

//ĳ��������const��Ա
class A {
public:
	A(int size) : SIZE(size) { }
private:
	
	const int SIZE;
}; 
//ĳ���������������ͳ�Ա
class B{
public:
	B(int& v) : i(v), p(v), j(v) { }
	void print_val() {
		cout << "hello:" << i << " " << j << endl;
	}
private:
	const int i;
	int p;
	int& j; 
};

int main() { 
	A a(100); 
	int r = 45; B b(r); b.print_val();
}


