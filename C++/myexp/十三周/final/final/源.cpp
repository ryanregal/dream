#define _CRT_SECURE_NO_WARNINGS 
#include <stdio.h>
#define N 20 //����N��󲻳���20

int main() {
	int col, row,k;
	int n;
	printf("�������ά���������/������\n");
	scanf("%d", &n);
	int a[N][N];

	//�����ά����
	printf("\n�������ά���飺\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf_s("%d", &k);
			a[i][j] = k;
		}
	}

	//���������ά����
	printf("\n���������ά���飺\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			printf("%4d", a[i][j]);
		}
		printf("\n");
	}

	//ÿһ�����ֵ
	for (int i = 0; i < n; i++) {
		int rowmax = a[i][0];
		for (int j = 0; j < n; j++) {
			if (a[i][j] > rowmax)
				rowmax = a[i][j];
		}
		printf("\n��%d�����ֵΪ:%d\n", i, rowmax);
	}


	//ÿһ�����ֵ
	for (int i = 0; i < n; i++) {
		int colmax = a[0][i];
		for (int j = 0; j < n; j++) {
			if (a[j][i] > colmax)
				colmax = a[j][i];
		}
		printf("\n��%d�����ֵΪ:%d\n", i, colmax);
	}

	//ʵ�����������
	printf("\nʵ�������������\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			printf("%4d", a[i][j]);
		}
		printf("\n");
	}

	//ʵ�����������
	printf("\nʵ�������������\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			printf("    ");
		}
		for (int j = i; j < n; j++) {
			printf("%4d", a[i][j]);
		}
		printf("\n");
	}
	return 0;
}
