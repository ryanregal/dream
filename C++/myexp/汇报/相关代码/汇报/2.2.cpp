#include <iostream>
using namespace std;

int add(int x, int y, int z)
{
	int sum;
	sum = x + y + z;
	return sum;
}
double add(int x, int y)
{
	double sum;
	sum = x + y;
	return sum;
}
int main() {
	int add(int x = 1, int y = 2, int z = 3);
	double add(int x, int y);
	int sum; double sum2;

	/*����int add��int x = 1��int y = 2��int z = 3�����βζ���int�ͣ�
	  double add��int x��int y�����β�Ҳ����int�ͣ������ú�������10��20ʱ���������޷�ʶ����Ҫ������һ���������ʻᱨ��
	  ���ԣ�һ���������ܼ���Ϊ���غ���������Ϊ��Ĭ�ϲ����ĺ�����
	  �����ú���ʱ�������дһ�������������������޷��ж��ǵ������غ������ǵ��ô�Ĭ�ϲ����ĺ�����*/
	sum = add(10, 20);
	sum = add(10, 20,20);
	cout << sum;
	return 1;
}