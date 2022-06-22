#include <iostream>
using namespace std;

class Parent {
private:     
	int m_nPrt;
protected:     
	int m_nPtd;
public:     
	int m_nPub;
public:
	Parent(int var = -1) {
		m_nPub = var;
		m_nPtd = var;
		m_nPrt = var;
	}
};

class Child1 : public Parent {
public:
	int getPub() { return m_nPub; }
	int getPtd() { return m_nPtd; }
	//int getPrt() { return m_nPrt; }   //private��Ա�����಻�ɷ���
};

class Child2 : protected Parent {
public:
	int getPub() { return m_nPub; }
	int getPtd() { return m_nPtd; }
	//int getPrt() { return m_nPrt; }   //private��Ա�����಻�ɷ���
};

class Child3 : private Parent {
public:
	int getPub() { return m_nPub; }
	int getPtd() { return m_nPtd; }
	//int getPrt() { return m_nPrt; }   //private��Ա�����಻�ɷ���
};

int main() {
	Child1 cd1;
	Child2 cd2;
	Child3 cd3;
	int nVar = 0;

	Parent p;
	p.m_nPub;
	cd1.m_nPub = nVar;  // public �̳У����Է���
	//cd1.m_nPtd = nVar;   //�����protected��Ա������������ܷ���
	nVar = cd1.getPtd();   //����ͨ������Child1�ĳ�Ա��������

	//cd2.m_nPub = nVar;  // protected �̳У�����������ܷ���
	nVar = cd2.getPtd();   //����ͨ������Child2�ĳ�Ա��������

	//cd3.m_nPub = nVar;  // private �̳У�����������ܷ��� 
	nVar = cd3.getPtd();   //����ͨ������Child3�ĳ�Ա��������

	return 0;
}