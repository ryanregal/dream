#include <iostream>
using namespace std;

int main() {
	using namespace std;
	//��̬�����СΪ3������
	double* p3 = new double[3];
	//�������鸳ֵ
	p3[0] = 0.2;    p3[1] = 0.5;   p3[2] = 0.8;
	cout << "p3[1] is " << p3[1] << ".\n";
	p3 = p3 + 1;
	cout << "Now p3[0] is " << p3[0] << " and ";
	cout << " p3[1] is " << p3[1] << " .\n";
	p3 = p3 - 1;
	delete[] p3;
	return 0;
}