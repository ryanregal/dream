#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

class Mystring {
private:
	char* text;
public:
	//���캯��
	Mystring(char* ch) {
		cout << "���ù��캯��" << endl;
		text = new char(strlen(ch) + 1);
		if (text) { strcpy(text, ch); }
	};
	//�������캯��
	Mystring(const Mystring& s) {
		cout << "���ÿ�������" << endl;
		text = new char(strlen(s.text) + 1);
		if (text) { strcpy(text, s.text); }
	};
	void print() {
		cout << "��ӡStirng��" << text << endl;
	}
	//��������
	~Mystring(){
		cout << "������������" << endl;
	}
};

int main() {
	char* a = new char[6]{'d','a','t','e','\0','\0'};
	Mystring one(a);
	Mystring two(one);
	return 0;
}