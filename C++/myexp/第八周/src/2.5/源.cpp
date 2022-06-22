#include <iostream>
using namespace std;

class Cube {
private:
	double side;
public:
	Cube(double side);  //���캯��
	double volume();    //�����
	bool compareVolume(Cube aCube);
};

Cube::Cube(double length) :side(length) { }
double Cube::volume() { return side * side * side; }
bool Cube::compareVolume(Cube aCube) { return volume() > aCube.volume(); }

int main() {
	Cube box1(5.0);  //���캯����������
	Cube box2(3.0);
	if (box1.compareVolume(box2))
		cout << "box1 is larger\n";
	else
		cout << "box1 is not larger\n";
	if (box1.compareVolume(Cube(50.0)))
		cout << "Volume of box1 is greater than 50\n";
	else
		cout << "Volume of box1 is not greater than 50\n";
	return 0;
}