#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

class Person {
private:
	char* pName;
public:
	Person(const char* pN = "noName") {
		cout << "������  " << pN << "\n";
		pName = new char[strlen(pN) + 1];
		if (pName) strcpy(pName, pN);
	}
	Person(const Person& p) {
		pName = new char[strlen(p.pName) + 1];
		if (p.pName) strcpy(pName, p.pName);
		cout << "���ø��ƹ��캯��" << endl;
	}
	void print() {
		cout << pName << endl;
	}
};

int main() {
	Person p1("John"); //ʹ�ù��캯��������p1
	p1.print();
	Person p2(p1);
	p2.print();
}