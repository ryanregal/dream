#include <iostream>
using namespace std;

//C++�������������������ʱ��Ϊ���������趨Ĭ��ֵ��
//�����淶�����һ������������ʱΪ���������趨Ĭ��ֵ��
//int add(int x = 1, int y = 2, int z = 3)
int add(int x, int y, int z)
{
	int sum;
	sum = x + y + z;
	return sum;
}

double add(double x, int y)
{
	double sum;
	sum = x + y;
	return sum;
}

int main() {
	int add(int = 1, int = 2, int = 3);
	double add(double x, int y);
	int sum; double sum2;

	sum = add(10, 20);
	//����������ʵ�ε������ж��ǵ���int add(int x,int y,int z)
	//x��y��10��20��ʼ����������zû�и�����ʵ�Σ������Ԥ�ȵ�Ĭ���β�ֵ
	//���õ��Ľ��10+20+3=33���ظ�sum����������sunΪ33��

	sum2 = add(1.0, 2);
	//����������ʵ�ε������ж��ǵ���double add��double x��int y��
	//ʵ��1.0��2�����뺯���У�������õ��Ľ��3���ظ�sum2��������sum2Ϊ3��

	cout << sum << endl;
	cout << sum2 << endl;
	return 1;
}