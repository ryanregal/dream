#include <iostream>
#include <string>
using namespace std;

void Encode(string& input) {
	for (auto &ch:input){
		ch += 8;
	}
}

void Decode(string& output) {
	for (auto &ch : output) {
		ch -= 8;
	}
}

int main() {
	int order;
	cout << "��������������루1�� ���루2�� �˳���0��" << endl;
	cin >> order;
	while(order!=0){
		if (order == 1) {
			string input;
			cout << "������Ҫ������ַ���" << endl;
			cin>>input;
			Encode(input);//����
			cout << "����Ϊ" <<input<< endl<<endl;
		}
		else if (order == 2) {
			string output;
			cout << "������Ҫ������ַ���" << endl;
			cin>>output;
			Decode(output);//����
			cout << "����Ϊ" << output << endl << endl;
		}
		cout << "��������������루1�� ���루2�� �˳���0��" << endl;
		cin >>order;
	}
}



