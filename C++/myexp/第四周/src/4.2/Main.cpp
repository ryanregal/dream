//����string�洢�ַ��������ַ�����ȥ��������
#include <iostream>
#include <string>
using namespace std;

void remove(string& s) {
	for (int i = 0, len = s.size(); i < len; i++) {
		if (ispunct(s[i])) {
			s.erase(i--, 1);
			len = s.size();
		}
	}
}

int main() {
	string text;
	getline(cin, text);//"Lorem ipsum, dolor sit! amet, consectetur? adipiscing elit. Ut porttitor."
	remove(text);
	cout << text << endl;
	return 0;
}