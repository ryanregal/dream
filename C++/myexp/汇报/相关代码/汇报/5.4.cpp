/*�궨������Ԥ��������ڱ�������е�Ԥ����׶δ����궨��ֻ�ǵ������滻
���Զ�a��b���бȽϣ������Max����1����aȡ����ֵ�������ABS����1����a��b���н��������a=0��b=1��*/

#include <iostream>
using namespace std;
#define Max(a,b) ((a)>(b)? (a):(b))
#define ABS(a) ((a)>= 0)? (a):(0-(a))
#define Swap(t,x,y) t=x;x=y;y=t;

int main() {
	int a = 1, b = 0, t = 0;
	cout << "Max=" << Max(a, b) << endl;
	cout << "ABS=" << ABS(a);
	Swap(t, a, b)
		cout << endl << "a=" << a << endl << "b=" << b << endl;

	/*	Max(a++,b)��ֵΪ2��ͬʱa����ֵ��Max(a++,b+10)��ֵΪ10��ͬʱa��ֵ����ֵһ�Ρ�
	��Ϊ�Ľ�������ͨ��һ�����������õ����к���滻Ч�ܣ������п�Ԥ����״̬���Լ����溯�������ͼ�顣*/

	Swap(t, a, b)
		cout << endl << "a=" << a << endl << "b=" << b << endl;
	cout << "max=" << Max(a++, b) << "  a=" << a << endl;
	//a����ֵ����
	cout << "max=" << Max(a++, b + 10) << "  a=" << a << endl;
	//a����ֵһ��

}