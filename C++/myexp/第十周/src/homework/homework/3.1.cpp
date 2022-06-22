#include <iostream>
using namespace std;

class Complex
{
private:
	double real;
	double im;
public:
	Complex& operator++();//ǰ��++a
	Complex operator++(int);//����a++
	Complex(double r, double i) {
		real = r;
		im = i;
	}
	//�������캯��
	Complex(const Complex& t) {
		real = t.real;
		im = t.im;
		cout << "HAHA    " << endl;
	}
	Complex() {
		real = 0;
		im = 0;
	};
	friend ostream& operator <<(ostream&, Complex&);
	friend istream& operator >>(istream&, Complex&);
	//Ҫ��д������ʵ�ָ����ļӣ��ԡ�+����������
	//����const�������ú������󲻻ᱻ�޸�
	//�����е�const��ʾ�������󲻻ᱻ�޸�
	Complex operator +(const Complex& x)const {
		Complex temp;
		temp.real = real + x.real;
		temp.im = im + x.im;
		return temp;
	}
	double getReal()const {
		return real;
	}
	double getIm()const {
		return im;
	}
	Complex& operator+=(const Complex& c) {
		real += c.real;
		im += c.im;
		return *this;
	}
};

Complex& Complex::operator++() {
	real++;//������
	return *this;//�ٷ��ض���
}
Complex Complex::operator++(int) {
	Complex temp(*this);//���ƹ��캯����������ԭ�ж���ֵ
	real++;
	return temp;
}

//Ҫ��д������ʵ�ֶ�Complex�����>>��<<����
ostream& operator <<(ostream& output, Complex& c) {
	output << "(" << c.real << "+" << c.im << "i)" << endl;
	return output;
};
istream& operator >>(istream& input, Complex& c) {
	cout << "�����븴����ʵ�����ֺ���������" << endl;
	input >> c.real >> c.im;
	return input;
};

int main() {
	Complex c1(2,3), c2(3,4), c3(5,6),c4;
	c4 = c1 + c2 + c3;
	cout << c4 << endl;

	Complex c5, c6;
	cin >> c5 >> c6;
	cout << c5 << c6 << endl;

	c2 = c1++;
	c2 = ++(++c1);
	cout << c2 <<c3<< endl;

	c1 += c2 += c3 += c4;
	cout << c4 << endl;
	return 0;
}
