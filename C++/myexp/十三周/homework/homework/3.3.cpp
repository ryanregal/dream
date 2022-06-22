#include <iostream>
#define MAXSIZE 50
using namespace std;

template <class T> 
class stack{
private:
    T s[MAXSIZE];
    int top;
public:
    stack() { top = -1; }
    void push(T newvalue); //��ջ
    T pop();  //��ջ
};

template <class T>
void stack<T>::push(T newvalue) {
    if (top<MAXSIZE-1){
        top = top + 1;
        s[top] = newvalue;
    }
    else  cout << "ջ�����޷���ջ��" << endl;
}

template <class T>
T stack<T>::pop() {
    if (top > -1) {
        cout << s[top] << endl;
        top = top - 1;
    }
    else  cout << "��ջ�ѿ�!�޷���ջ��" << endl;
    return s[top];
}

int main() {
    stack<int> args;
    args.push(10);
    args.push(20);
    args.pop();
    args.pop();
    args.pop();
}