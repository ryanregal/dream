#include <iostream>
using namespace std;

//��forѭ���з���ִ������������ʹǶ���Ч����Խϸߡ�
inline int is_digit(char a) {
	if (a >= '0' && a <= '9') return 1;
	else return 0;
}

int main()
{
	int num = 0;
	cout << "�������ַ������ַ�����:" << endl;
	scanf_s("%d", &num);
	cout << "�������ַ���(�ַ�����Ϊ" << num << ")" << endl;
	char clear = getchar();
	for (int i = 0; i < num; i++)
	{
		char a = getchar();
		if (is_digit(a))
		{//������������
			cout << a << "������" << endl;
		}
		else cout << a << "��������" << endl;
	}
	return 0;
}