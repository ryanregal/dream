.text
main:
	li $t0,7 #쳲�������n=7
	li $t1,0 #��ʼ��f(0)=0
	li $t2,1 #��ʼ��f(1)=1
	li $t4,1 #��ʼ��f(2)=1
	li $t3,2 #int i=2	
        sw $t1,0($zero)  #��f(0)���ڻ�����
  	blt $t0,2,out    #�������n<2��ֱ����תout
  	sw $t2,4($zero)  #��f(1)���ڻ�����
 	blt $t0,3,out    #�������n<3��ֱ����תout
	sw $t2,8($zero)  #��f(2)���ڻ�����
  	blt $t0,4,out    #�������n<4��ֱ����תout
loop:
 	bgt $t3,7,out    #���i>7��ֱ����תout
	move $t1,$t2       #f(n-2)=f(n-1)
	move $t2,$t4       #f(n-1)=f(n)
	add $t4,$t1,$t2    #f(n)=f(n-1)+f(n-2),n��3��ʼ
 	addi $t3,$t3,1     #i++,i��һ�α��3
	sll $t0, $t3, 2    #ƫ���� $t0 =i*4 sll������
	add $t0,$zero,$t0  #ʵ�ʵ�ַ = ƫ����$t0 + ��׼��ַ
	sw $t4,0($t0)      #��f(n)д�뵽ʵ�ʵ�ַ
	j loop
out:     #=========���==========#
	li $v0,10 #��������
	syscall  #ִ��

