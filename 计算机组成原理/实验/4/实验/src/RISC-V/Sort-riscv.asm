
main:
#ʮ����13 4 2 1 15 9 7 8 6 11
#�ڴ��е�ֵ��ʼ��
ori s0,zero,13
sw s0,0(zero)
ori s0,zero,4
sw s0,4(zero)
ori s0,zero,2
sw s0,8(zero)
ori s0,zero,1
sw s0,12(zero)
ori s0,zero,15
sw s0,16(zero)
ori s0,zero,9
sw s0,20(zero)
ori s0,zero,7
sw s0,24(zero)
ori s0,zero,8
sw s0,28(zero)
ori s0,zero,6
sw s0,32(zero)
ori s0,zero,11
sw s0,36(zero)

ori s0,zero,1		#s0=1�����ѭ���ж���������
ori s1,zero,10		#s1=10�����ѭ����������	
ori s2,zero,1 		#s2=1,�ڲ�ѭ���ж���������
ori s3,zero,0		#s3������ʾ��ַ
ori a0,zero,1		#a0=1,����������ѭ�������б����ĵ���
ori t0,zero,4		#t0=4������������ѭ�������е�ַ�ĵ���
loopout:
	beq s0,s1,finish	#���ѭ���˳�����s0=s1
	ori s2,zero,1		#ÿ�����ѭ���տ�ʼ������s2=1
	ori s3,zero,0		#ÿ�����ѭ���տ�ʼ������s3=0����һ��Ԫ�ؿ�ʼ�ж�
	loopin:	
		lw s4,0(s3)
		lw s5,4(s3)
		slt s6,s5,s4	#���s5<s4,��ôs6=1,����s6=0,Ϊ1��Ҫ����ֵ
		beq s6,zero,next	#���s6=0������Ҫ������ֱ����ת��next������˳��ִ��
		#����ֵ�Ĳ���
		add a1,s4,zero	#a1=s4
		add s4,s5,zero	#s4=s5
		add s5,a1,zero	#s5=a1
	next:
		#�ͻ��ڴ�
		sw s4,0(s3)		
		sw s5,4(s3)
		add s3,s3,t0		#��ַ��4
		add s2,s2,a0		#������1
		add  s7,s0,s2		#s7=s0+s2    s7=j+i
		#j+i>n,�˳�
		slt t1,s1,s7		#s1<s7,t1=1,����t1=0��t1=1�˳�ѭ��
		beq t1,zero,loopin	#����1�������ڲ�ѭ����������������1���������ѭ��
		add s0,s0,a0
		jal zero,loopout
finish:
	jal zero,finish
