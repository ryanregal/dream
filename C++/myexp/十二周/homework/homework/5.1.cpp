#include <iostream>
using namespace std;

class root1 {
public:
	root1() { cout << "ִ��root1��Ĺ��캯��" << endl; }
};

class root2 {
public:
	root2() { cout << "ִ��root2��Ĺ��캯��" << endl; }
};

class mid :public root1, virtual public root2 {
public:
	mid() { cout << "ִ��mid��Ĺ��캯��" << endl; }
};

class top:public mid {
public:
	top() { cout << "ִ��top��Ĺ��캯��" << endl; }
};
void main() {
	top t;
}
