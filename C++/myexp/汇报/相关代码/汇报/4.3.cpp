#include <iostream>
using namespace std;

int main() {
	int add(int x = 5, int y = 6);
	//�к�������ʱ���������ж���Ĭ���β�ֵ
	cout << add(10, 20) << endl;//��ʵ������ʼ���β�
	cout << add(10) << endl;//�β�x����ʵ��10��y����Ĭ��ֵ6
	cout << add() << endl;//x��y������Ĭ��ֵ���ֱ�Ϊ5��6
	return 0;
}

int add(int x, int y) {
	return x + y;
}