#include "5.h"

//�ڴ����ʧ�ܣ�����exit()��ֹ
void MyString::memError() {
	cout << "�ڴ�������" << endl;
	exit(0);
}
//���캯��
MyString::MyString(const char* sptr) {
	len = strlen(sptr);
	str = new char[len + 1];
	if (str==NULL){
		memError();
	}
	strcpy(str, sptr);
}
//�������캯��
MyString::MyString(MyString& right) {
	str = new char[right.length() + 1];
	if (str == NULL) memError();
	strcpy(str, right.getValue());
	len = right.length();
}

//��ʹ�� += ��������������ַ�������������
//����ֵ��������õĶ���
MyString MyString::operator+=(MyString& right) {
	char* temp = str;
	str = new char[strlen(str) + right.length() + 1];
	if (str == NULL)memError();
	strcpy(str, temp);
	strcat(str, right.getValue());
	if (len != 0)delete[]temp;
	len = strlen(str);
	return *this;
}
char* MyString::operator +=(const char* right)
{
	char* temp = str;
	str = new char[strlen(str) + strlen(right) + 1];
	if (str == NULL)  memError();
	strcpy(str, temp);
	strcat(str, right);
	if (len != 0) delete[] temp;
	return str;
}

//��ʹ�� + ��������������ַ����ĸ���
//����ֵ�ǵ��ö���
MyString MyString::operator +(MyString& right) {
	MyString temp(*this);
	temp += right;
	return temp;
};
char* MyString::operator+(const char* a){
	MyString temp(* this);
	temp += (const char*)a;
	char* ans = temp.str;
	return ans;
};

//��ʹ�� == ������������ַ����ж��Ƿ����
// ������ö���Ͳ�������� str ������ͬ������true�����򷵻� false
bool MyString::operator ==(MyString& right)
{
	return strcmp(str, right.getValue()) == 0 ? true : false;
}
bool MyString::operator ==(const char* right)
{
	return strcmp(str, right) == 0 ? true : false;
}

//��ʹ�� = ��������������ַ����ĸ�ֵ����
//����ֵ�ǵ��ö�������
char* MyString::operator=(const char* right) {
	if (len != 0)  delete[] str;
	len = strlen(right);
	str = new char[len + 1];
	if (str == NULL) memError();
	strcpy(str, right);
	return str;
} 
MyString MyString::operator =(MyString& right)
{
	if (len != 0)  delete[] str;
	str = new char[right.length() + 1];
	if (str == NULL)  memError();
	strcpy(str, right.getValue());
	len = right.length();
	return *this; // ���ص��ö�����
}

//��ʹ��[]����������ַ���Ϣ��������Խ���ж�
char& MyString::operator[](int idx) {
	if (idx < len) return str[idx];
	else {
		cout << "����Խ��" << endl;
		char zero[] = "\0";
		*this += (const char*)zero;
		return str[len];
	}
};
// �����������<<������һ������
ostream& operator <<(ostream& strm, MyString& obj)
{
	strm << obj.str;
	return strm; // ����ǰ�����󷵻�
}

int main()
{
	MyString obj1("I "), obj2("love "),obj3("China");
	MyString obj4 = obj1; // ���ÿ������캯��
	cout << obj1[3] << endl;
	char str[] = "!";
	cout << "���� 1: " << obj1 << endl;
	cout << "���� 2: " << obj2 << endl;
	cout << "���� 3: " << obj3 << endl;
	cout << "���� 4: " << obj4 << endl;
	cout << "�ַ�����: " << str << endl;
	// ��ʾ���� += ����
	obj1 += obj2;
	obj1 += obj3;
	obj1 += str;
	cout << "���� 1: " << obj1 << "\n\n";
	// ��ʾ��ϵ����
	if (obj1 == str)  cout << obj1 << " �����ַ����� " << str << endl;
	else  cout << obj1 << " �������ַ����� " << str << endl;
	if (obj3 == "China") cout << obj3 << " ���� China\n";
	else cout << obj3 << " ������ China\n";
	return 0;
}