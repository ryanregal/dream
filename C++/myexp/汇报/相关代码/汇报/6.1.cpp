#include<iostream>
using namespace std;

int Myadd(int x, int y) {
	return x + y;
}
float Myadd(float x, float y) {
	return x + y;
}
double Myadd(double x, double y) {
	return x + y;
}

//ʹ��ģ��
//����T�Ǻ�����ʹ�õ��������͵�ռλ������
template<typename T> T Myadd2(T a, T b) {
	return a + b;
}

int main() {
	cout << Myadd(1, 1) << endl;//�������
	cout << Myadd(1.2f, 1.2f) << endl;//float���
	cout << Myadd(1.5, 1.2) << endl;//double���

	cout << Myadd2(1, 1) << endl;//ʹ��ģ��
	cout << Myadd2(1.2f, 1.2f) << endl;//ʹ��ģ��
	cout << Myadd2(1.5, 1.2) << endl;//ʹ��ģ��
}

