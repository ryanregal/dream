#include "4.2.h"
Teacher::Teacher(string nam, int a, char s, string tit, string ad, string t) :
	name(nam), age(a), sex(s), title(tit), addr(ad), tel(t) {
	cout << "����Teacher���캯��" << endl;
}
void Teacher::display()
{
	cout << "������" << name << endl;
	cout << "���䣺" << age << endl;
	cout << "�Ա�" << sex << endl;
	cout << "ְ�ƣ�" << title << endl;
	cout << "��ַ��" << addr << endl;
	cout << "�绰��" << tel << endl;
}

Cadre::Cadre(string nam, int a, char s, string p, string ad, string t) :
	name(nam), age(a), sex(s), post(p), addr(ad), tel(t) {
	cout << "����Cadre�Ĺ��캯��" << endl;
}
void Cadre::display()
{
	cout << "������" << name << endl;
	cout << "���䣺" << age << endl;
	cout << "�Ա�" << sex << endl;
	cout << "ְ��" << post << endl;
	cout << "��ַ��" << addr << endl;
	cout << "�绰��" << tel << endl;
}

Teacher_Cadre::Teacher_Cadre(string nam, int ag, char se, string tit, string pos, string add, string te, int wag) :
	Teacher(nam, ag, se, tit, add, te),
	Cadre(nam, ag, se, pos, add, te),
	wages(wag){
	cout << "����Teacher_Cadre�Ĺ��캯��" << endl;
}
void Teacher_Cadre::Show() {
	//��������Teacher_Cadre�ĳ�Ա����show�е���Teacher���е�display����
	//������������䡢�Ա�ְ�ơ���ַ���绰��Ȼ����cout������ְ��͹��ʡ�
	//�����������е����������䡢�Ա𡢵�ַ���绰�����ݳ�Ա����ͬ������
	//��������Щ���ݳ�Աʱ��ָ��������
	Teacher::display();
	cout << "ְ��" << Cadre::post << endl;
	cout << "���ʣ�" << wages << endl;
}

int main()
{
	Teacher_Cadre te_ca("Wangli", 50, 'f', "prof.", "president", "135 Beijing Road,Shanghai", "(021)61234567", 1534.5);
	te_ca.Show();
	return 0;
}