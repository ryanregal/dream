#include<iostream>
using namespace std;

class Date {
private:
	int month;
	int day;
	int year;
public:
	void cindata();
	void cout1();
	void cout2();
	void cout3();
	string getmonth(int month);
	
	void setdata() {
		year = 2010;
		month = 12;
		day = 25;
	}
};

void Date::cindata() {
	cout << "���������ڣ���2022 3 27��:";
	cin >> year >> month >> day;
	if (month > 12 || month < 1) 	throw "��Ч�·�";
	if (day > 31 || day < 1) 	throw "��Ч����";
}

void Date::cout1() {
	cout << endl << month << "-" << day << "-" << year % 100  << endl;
}

void Date::cout2() {
	cout << endl  << getmonth(month) << " " << day << "," << year  << endl;
}

void Date::cout3() {
	cout << endl  << day << " " << getmonth(month) << " " << year  << endl;
}

string Date::getmonth(int month) {
	switch (month) {
	case 1: return "January";
	case 2: return "February";
	case 3:  return "March";
	case 4:  return "April";
	case 5: return "May";
	case 6: return "June";
	case 7: return "July";
	case 8: return "August";
	case 9: return "September";
	case 10: return "October";
	case 11: return "November";
	case 12: return "December";
	}
}

int main()
{
	Date date;
	int choice;
	while (true) {
		while (true) {
			try {
				date.cindata();
				break;
			}
			catch (const char* msg) {
				cerr << msg << endl;//���throw�Ĵ�����Ϣ
			}
		}
		while (true) {
			cout << "\n1.���12-25-11��ʽ" << endl;
			cout << "2.���December 25,2011��ʽ" << endl;
			cout << "3.���25 December 2011��ʽ" << endl;
			cout << "4.��������" << endl;
			cout << "ѡ��";
			cin >> choice;
			if (choice == 4) {
				cout << endl;
				break;
			}
			switch (choice) {
			case 1:date.cout1(); break;
			case 2:date.cout2(); break;
			case 3:date.cout3(); break;
			default:break;
			}
		}
	}
}
