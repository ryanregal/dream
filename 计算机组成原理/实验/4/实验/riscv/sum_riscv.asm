#RISC-V�����ۼӺͳ���                      result=1+2+...+n               n����������ַΪ0�����ݴ洢���У����result����������ַΪ4�����ݴ洢���е�

main:
	lw a3, 0(zero)     		# ��ȡ����0�ŵ�Ԫ����ַΪ0����ֵ��n����a3
	ori a5, zero, 1     		# a5���ݣ�ѭ������i��Ϊ1 
	ori a2, zero, 1     		# a2���ݣ�ѭ��������Ϊ1
                ori a4, zero, 0                          # a4����Ϊ0
loop:
	add a4, a4, a5     		# ��i�ӵ�a4���ۼӺͣ�
	beq a5, a3, finish  		# ��a5=n��������ѭ��
    	add a5, a5, a2     		# a5=a5+1
    	jal zero, loop		# ��������ת��loopִ��
finish:
	sw a4, 4(zero)    		# ���ۼӽ��result���浽����1�ŵ�Ԫ����ַΪ4��
end:
	jal zero, end		# ��������ת��endִ��