#include <iostream>
#include<string>
using namespace std;
//��������������Ա�����������ⶨ���Ա����

class Teacher
{
public:
	Teacher() {}
	Teacher(string nam, int a, char s, string tit, string ad, string t);
	void display();
protected:
	//�����������ж��������������䡢�Ա𡢵�ַ���绰�����ݳ�Ա��
	//��Teacher���л��������ݳ�Ա��ְ�ƣ�
	string name;
	int age;
	char sex;
	string title;
	string addr;
	string tel;
};


class Cadre
{
public:
	Cadre() {}
	Cadre(string nam, int a, char s, string p, string ad, string t);
	void display();
protected:
	//�����������ж��������������䡢�Ա𡢵�ַ���绰�����ݳ�Ա��
	//��Cadre���л��������ݳ�Ա��ְ��
	string name;
	int age;
	char sex;
	string post;
	string addr;
	string tel;
};


class Teacher_Cadre :public Teacher, public Cadre
{
private:
	//��Teacher_Cadre���л��������ݳ�Ա������
	int wages;
public:
	Teacher_Cadre() {}
	Teacher_Cadre(string nam, int ag, char se, string tit, string pos, string add, string te, int wag);
	void Show();
};