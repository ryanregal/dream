//��������ʵ�������Ρ������Ρ������Σ�����һ�нǣ������Σ����µ׺͸ߣ��������
//����ʹ�����غ���area()����ͬ���������غ���area()���ĸ��������в�ͬ�����Ĳ���
//�Ĵε���area�����Ĳ���������ͬ��ϵͳ����ݲ����ĸ����ҵ���֮ƥ��ĺ�������������

#include <iostream>
#include <cmath>
using namespace std;

// ���������α߳��������
inline double area(double length) {
	return length * length;
}
// �����ο�ߣ���������
inline double area(double width, double height) {
	return width * height;
}
// �������ε�����һ�ǣ������������
inline double area(double Degrees, double length1, double length2) {
	Degrees = Degrees * 3.14159 / 180;
	double s = 0.5 * length1 * length2 * sin(Degrees);
	return s;
}

//�������κ�����������Ҫ�Ĳ�������3������Ҳ����double
//�����������������area���������м���һ��int���͵Ĳ���flag�Ա�����
// �����ε����µ׺͸ߣ����������
inline double area(double a, double b, double c, int flag) {
	double s = (a + b) * c / 2;
	return s;
}

int main() {
	double square, width, heigth, triangle1, triangle2, degree, a, b, c;
	cout << "�����������α߳���" << endl;
	cin >> square;
	cout << "�������α߳�Ϊ" << area(square) << endl;
	cout << "�����볤���εĳ��Ϳ�" << endl;
	cin >> width >> heigth;
	cout << "���������Ϊ " << area(width, heigth) << endl;
	cout << "�����������ε�������" << endl;
	cin >> triangle1 >> triangle2;
	cout << "�����������ε����߼нǣ��Ƕȣ�" << endl;
	cin >> degree;
	cout << "���������Ϊ " << area(degree, triangle1, triangle2) << endl;
	cout << "���������ε��ϵס��µס���" << endl;
	cin >> a >> b >> c;
	cout << "�������Ϊ " << area(a, b, c, 1) << endl;
	return 0;
}