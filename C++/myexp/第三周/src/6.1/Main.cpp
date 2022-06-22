#include<iostream>
using namespace std;

typedef struct LNode
{
	int data;
	struct LNode* next;
}LNode, * LinkList;

void CreateList(LinkList& L, int n)
{
	LinkList s, p;
	p = L = new LNode;
	cout << "����������:" << endl;
	for (int i = 0; i < n; i++){
		s = new LNode;
		cin >> s->data;
		p->next = s;
		p = s;
	}
	p->next = NULL;
}

void ListDelete(LinkList& L, int mink, int maxk){
	LNode* p, * q;
	p = new LNode;
	q = new LNode;
	q = L->next;
	p = L;
	while (q && q->data <= mink) {
		p = q;
		q = q->next;
	}
	while (q && q->data < maxk){
		p->next = q->next;
		delete(q);
		q = p->next;
	}
	p = L->next;
	cout << "ɾ����������Ϊ:";
	while (p) {
		cout << p->data << ' ';
		p = p->next;
	}
}

int main(){
	LinkList L;
	int n,mink,maxk;
	cout << "�������������:";
	cin >> n;
	CreateList(L, n);
	cout << "������mink��maxk:";
	int k;
	cin >> mink >> maxk;
	ListDelete(L, mink, maxk);
	return 0;
}
