#include "stdio.h"
union
{
	int i;  unsigned int ui; float f;
	short s; unsigned short us;
	char c; unsigned char uc;
}t;

//����ַ���ʮ�����Ʊ��� 
void hex_out(char a){
	const char HEX[]="0123456789ABCDEF";//HEX���� 
	//0f����11110000��f����1111
	printf("%c%c",HEX[(a&0xF0)>>4],HEX[a&0xF]);
}

void out_1byte(char *addr){
	hex_out (*(addr +0));
}

void out_2byte(char *addr){
	hex_out (*(addr +1));
	hex_out (*(addr +0));
}

//���������4�ֽڱ����Ļ����� 
void out_4byte(char *addr){
	hex_out (*(addr +3));//���ָ�������ֵ��ָ�뱾�����ڴ��ַ���޷����� 
	hex_out (*(addr +2));//�������С�˷�ʽ���� 
	hex_out (*(addr +1));
	hex_out (*(addr +0));
}

main(){
	t.i=0xC77FFFFF;

	out_4byte(&t.i);//i��int���͵� 
	printf(" = %d \n",t.i);

	out_4byte(&t.ui);//ui��unsigned int 
	printf(" = %u \n",t.ui);

	out_4byte(&t.f);//f��float 
	printf(" = %f \n",t.f);

	out_2byte(&t.s);//s��short 
	printf(" = %d \n",t.s);

	out_2byte(&t.us);//us��unsigned short 
	printf(" = %u \n",t.us);

	out_1byte(&t.c);//c��char 
	printf(" = %d \n",t.c);

	out_1byte(&t.uc);//uc��unsigned char 
	printf(" = %d \n",t.uc);
}

