#RISC-Vð�ݷ����������                ��n�����ݽ������򣨴�С�����������У�        
#                                                       n����ڵ�ַΪ0�����ݴ洢����            
#                                                       n�����ݴ���ڵ�ַΪ4��8��12��4*n�����ݴ洢����             
#                                                       ���кõ�������Ȼ���ڵ�ַΪ4��8��12��4*n�����ݴ洢����     

main:
	lw	a1,0(zero)  		#����������ָ���n����0�Ŵ洢��Ԫ��
	add	a2,a1,zero  		#i=n
	ori  	a4,zero,1  		#x4=1
	ori  	a5,zero,4  		#x5=4
	ori  	a6,zero,-1  		#x6=-1
loop1:
	beq  	a2,a4,finish  		#if i=1 �����
	ori  	a3,zero,1  		#j=1
	ori  	a7,zero,4                                 
	ori  	s8,zero,8
loop2:
	sltu  	s11,a3,a2 		# if j<i then ��ȡ����Ԫ�رȽ�
	beq  	s11,zero,loop3
	lw 	s9,0(a7)     		#��ȡ��j��Ԫ��
	lw 	s10,0(s8)    		#��ȡ��j+1��Ԫ��
	sltu 	s11,s9,s10 		#�������У���С����
	beq 	s11,a4,loop4                           
	sw 	s10,0(a7)  		#�����洢
	sw 	s9,0(s8)   			#�����洢
	jal 	zero,loop4
loop3:
   	add 	a2,a2,a6
   	jal 	zero,loop1
loop4:
   	add 	a3,a3,a4 			# j=j+1
   	add 	a7,a7,a5
   	add 	s8,s8,a5
   	jal 	zero,loop2
finish:
	jal 	zero,finish

