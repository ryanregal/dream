#include "Cuboid.h"

inline void Cuboid::setdata(){
	cout << "������ĳ������߷ֱ�Ϊ��";
	cin >> length >> width >> height;
	if (length<=0|| width <= 0|| height <= 0){
		throw "��Ч����";
	}
}

inline void Cuboid::volumevalue(){
	volume = length * width * height;
}

inline void Cuboid::printvalue(){
	cout << endl<<"������ĳ�������Ϊ��";
	cout << length << ' ' << width << ' ' << height << endl ;
	cout << "����������Ϊ��";
	cout << volume << endl << endl;
}

int main()
{
	Cuboid cub;
	while (true){
		try{
			cub.setdata();
			break;
		}
		catch (const char*msg){
			cerr << msg << endl;
		}
	}
	cub.volumevalue();
	cub.printvalue();
	return 0;
}