#include <iostream>
using namespace std;


//�ں�������ͺ��������ĺ���ͷǰ��ӹؼ���inline
inline int cube(int);
int main() {
	for (int i = 1; i <= 10; i++)
	{
		int p = cube(i);
		cout << i << '*' << i << '*' << i << '=' << p << endl;
	}
}

//�����г��ֵ����������ĵ��ý��øú����ĺ�������棬������תȥ���øú�����
inline int cube(int n) {
	return n * n * n;
}