#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>         
using namespace std;

class Student {
public:
	void Register(const char* n, char s = 'M', int a = 19, float sc = 90);
	void showme();
private:
	char name[20];
	char sex;
	int age;
	float score;
};
void  Student::Register(const char* n, char s, int a, float sc) {
	strcpy(name, n);
	sex = s;
	age = a;
	score = sc;
}
inline  void   Student::showme() { //����
	cout << name << '\t' << sex << '\t' << age << '\t' << score << endl;
}
int main() {
	Student Stu1, Stu2;
	//ע��nameΪTom��ͬѧ��sex��age��score����defaultֵM��19��90
	Stu1.Register("Tom");
	//ע��nameΪLucy,sexΪF��ageΪ18��scoreΪ98��ͬѧ
	Stu2.Register("Lucy", 'F', 18, 98);
	cout << "���" << endl;
	Stu1.showme();//���TOM����Ϣ
	Stu2.showme();//���LUCY����Ϣ
	return 0;
}