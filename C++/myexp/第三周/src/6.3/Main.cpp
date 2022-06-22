#include<iostream>
using namespace std;
int* a = new int[10];

//ѡ������
void SelectionSort(int n) {
	for (int i = 0; i <= n; i++) {
		for (int j = i + 1; j <= n - 1; j++) {
			if (a[i] > a[j]) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
}

//��������
void QuickSort(int left, int right) {
	int i, j, t, temp;
	if (left > right) return;
	temp = a[left]; //temp�д�ľ��ǻ�׼��
	i = left;
	j = right;
	while (i != j) {//˳�����Ҫ��Ҫ�ȴ��ұ߿�ʼ��
		while (a[j] >= temp && j > i)  j--;
		while (a[i] <= temp && j > i)  i++;//������ߵ�
		if (i < j) {//�����������������е�λ��
			t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
	a[left] = a[i];//���ս���׼����λ
	a[i] = temp;
	QuickSort(left, i - 1);//����������ߵģ�������һ���ݹ�Ĺ���
	QuickSort(i + 1, right);//���������ұߵ� ��������һ���ݹ�Ĺ���
}

//ð������
void BubbleSort(int n) {
	for (int i = 1; i <= n - 1; i++) {
		for (int j = 1; j <= n - i; j++) {
			if (a[j - 1] > a[j]) {
				int temp = a[j - 1];
				a[j - 1] = a[j];
				a[j] = temp;
			}
		}
	}
}

int main() {
	int n;
	cout << "Ҫ���������������";
	cin >> n;
	//��������
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	SelectionSort(n);//ѡ������
	QuickSort(0, n - 1);//��������
	BubbleSort(n);//ð������
	//�������
	cout << endl;
	for (int i = 0; i < n; i++) {
		cout << a[i] << " ";
	}
}