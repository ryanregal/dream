#include <iostream>
using namespace std;
template<typename T1>   //ģ��������T1Ϊ���Ͳ���
T1 max(T1* p1, T1 n)    //����ģ�庯��max�������ֵ
{
	int j = 0;
	for (int i = 1; i < n; i++)
		if (p1[i] > p1[j])  j = i;
	return p1[j];
}
template<typename T2>
T2 min(T2* p2, T2 m)   //����ģ�庯��min,����Сֵ
{
	int j = 1;
	for (int i = 0; i < m; i++)
		if (p2[i] < p2[j]) j = i;
	return p2[j];
}
int main()
{
	int a[] = { 5,6,4,58,2 };
	double b[] = { 2.3,1.1,2.2,3.3,88.01 };
	cout << "����aΪ��" << endl;
	for (int i = 0; i < 5; i++)  cout << a[i] << "  ";  cout << endl;
	cout << "����a�����ֵΪ��" << max(a, 5) << endl;
	cout << "����a����СֵΪ:" << min(a, 5) << endl;
	cout << "����bΪ:" << endl;
	for (int j = 0; j < 5; j++)  cout << b[j] << "  ";  cout << endl;
	cout << "����b�����ֵΪ��" << max(b, 5.0) << endl;
	cout << "����b����СֵΪ:" << min(b, 5.0) << endl;
	return 0;
}