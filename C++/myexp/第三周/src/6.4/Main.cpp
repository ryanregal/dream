#include <iostream>
#include <string>
#include<vector>
using namespace std;

int main() {

	//ʹ�ñ�׼������string
	string str1("Hello!"), str2("My friend!");
	str1 += str2;
	cout <<str1 << endl;

	//ʹ�ñ�׼������vector
	vector <char> string1{'H','e','l','l','o','!',0};//��̬����
	vector <char> string2{ 'M','y',' ','f','r','i','e','n','d','!',0 };
	string1.pop_back();
	for (char i:string2) {
		string1.push_back(i);//�ַ�������
	}
	for (char j:string1){
		cout << j ;//��������ַ�
	}	

	//�Լ���д
	char* mystr1 = new char[15]{ 'H','e','l','l','o','!',0 };//�ڶ��Ϸ���15�������ڴ�
	char* mystr2 = new char[15]{ 'M','y',' ','f','r','i','e','n','d','!',0 };//�ڶ��Ϸ���15�������ڴ�
	//��̬�����ڴ治��
	char* newstr1 = new char[30];//�ڶ��Ϸ���30�������ڴ�
	for (int i = 0; mystr1[i - 1] != 0; i++) {
		newstr1[i] = mystr1[i];
	}
	delete[]mystr1;
	for (int i = 0; mystr2[i - 1] != 0; i++) {
		newstr1[i+6] = mystr2[i];
	}
	delete[]mystr2;
	cout << endl<<newstr1;
}