#include <iostream>
using namespace std;

class Sample {
	int x;
public:
	Sample() { cout << "�����޲ι��캯��" << endl; };
	Sample(int a) { 
		cout << "���ô��ι��캯��" << endl;
		x = a; 
	}
	Sample(Sample& a) { 
		cout << "���ÿ������캯��" << endl;
		x = a.x + 1; 
	}
	void disp() { cout << "x =  " << x << endl; }
};

void main() {
	Sample s1(2), s2(s1);
	s2.disp();
}