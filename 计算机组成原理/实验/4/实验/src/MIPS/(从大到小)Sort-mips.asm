.text                           #�����
.globl main                     #����Ӵ˿�ʼ

main:                             #������
      add  $s1,  $zero ,0         #���ػ�׼�ڴ��ַ
      add  $s0, $zero, 1      # $s0 ���� int i
read: 
      li $t0,10  #n=10
      sw $t0, 0($zero)# ��������
      li $t0,9
      sw $t0, 4($zero)
      li $t0,1
      sw $t0, 8($zero)
       li $t0,2
      sw $t0, 12($zero)
       li $t0,5
      sw $t0, 16($zero)
       li $t0,10
      sw $t0, 20($zero)
       li $t0,8
      sw $t0, 24($zero)
       li $t0,4
      sw $t0, 28($zero)     
       li $t0,7
      sw $t0, 32($zero)
       li $t0,6
      sw $t0, 36($zero)
       li $t0,3
      sw $t0, 40($zero)
      
oLop:
                                #���ѭ����ʼ
      addi $s2, $zero, 10        #$s2 ���� int j = 10
iLop:
                                    #�ڲ�ѭ����ʼ 
          sll $t0, $s2, 2           #ƫ����j*4����ʼ��40
          add $t1, $s1, $t0         #A[j]��ʵ���ڴ��ַ
          addi $t2, $t1, -4         #A[j-1]��ʵ���ڴ��ַ
          lw $t3, 0($t1)            #$t3=A[j]��ֵ
          lw $t4, 0($t2)            #$t4=A[j-1]��ֵ
          slt $t5, $t4, $t3         #��A[j-1] < A[j]
          beq $t5, $zero, afterSwap #Ϊ�潻������������
#swap
          lw $t6, 0($t1)           # tmp=A[j]
          sw $t4, 0($t1)            # A[j] = A[j-1]
          sw $t6, 0($t2)            # A[j-1]=tmp
afterSwap:
          addi $s2, $s2, -1          # j = j - 1
          slt $t0, $s0, $s2         #��i < j
          bne $t0, $zero, iLop      #�����ڲ�ѭ��
          addi $s0, $s0, 1          # i = i + 1
          slti $t0, $s0, 10          #��i<10
          bne  $t0, $zero, oLop     #��������ѭ�� 
                                #���ѭ������
          li $v0, 10                #ͣ
          syscall                   #�� 
