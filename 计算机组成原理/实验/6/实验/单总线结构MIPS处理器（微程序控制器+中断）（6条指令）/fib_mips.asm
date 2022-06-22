#MIPS程序    计算费波那契数列                           n=10（可以改变），计算好的n+1个数�?0�?1�?1�?2�?3�?5�?8�?13�?21�?34、�?��?�）保存在地�?�?0�?4�?8�?12�?...的数据存储器�?

main:
                addi $s2,$zero,10               		# $s2=n=10        

	addi $s1,$zero,0			# $s1=0 		
	sw $s1,3072($zero)                                       # 0 -> (0)

                addi $s1,$zero,1			# $s1=1 	
	sw $s1,3076($zero)			# 1 -> (4)

	sw $s1,3080($zero)			# 1 -> (8)

                addi $s3,$zero,2			# $s3 = 2
                addi $s4,$zero,1			# $s4 = 1
                addi $s5,$zero,1			# $s5 = 1
loop:
                add $s6,$s4,$s5			# $s4+$s5 -> $s6	
                addi $s4,$s5,0			# $s5 -> $s4	
                addi $s5,$s6,0			# $s6 -> $s5	
                addi $s3,$s3,1			# $s3+1 -> $s3	
                add $s7,$s3,$s3			# $s3+$s3 -> $s7
                add $s0,$s7,$s7			# $s7+$s7 -> $s0	
                sw $s6,3072($s0)                		# $s6 -> 0($s0)	
	beq $s2,$s3,end			# if $s2 = $s3 goto end	
                beq $zero,$zero,loop				# goto loop
end:
	beq $zero,$zero,end                			# 程序�?�?
IRQ0:                                                       #IRQ0�жϷ���������ڵ�ַ��1024   =   400H    		RAM��Ӧ100            
	addi $sp,$zero,3840    	#push registers  ��Ҫ�����жϳ����õ��ļĴ���                 $s0    $s1       
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3328              #RAM��Ӧ340
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret
IRQ1: 				#IRQ0�жϷ���������ڵ�ַ��1536     =   600H  		RAM��Ӧ180            
	addi $sp,$zero,3840    	#push registers  ��Ҫ�����жϳ����õ��ļĴ���                 $s0    $s1       
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3392              #RAM��Ӧ350
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret
IRQ2: 				#IRQ0�жϷ���������ڵ�ַ��2048       =  800H		RAM��Ӧ200            
	addi $sp,$zero,3840    	#push registers  ��Ҫ�����жϳ����õ��ļĴ���      	$s0    $s1        
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3456             #RAM��Ӧ360
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret

IRQ3: 				#IRQ0�жϷ���������ڵ�ַ��2560       =  A00H		RAM��Ӧ280            
	addi $sp,$zero,3840    	#push registers  ��Ҫ�����жϳ����õ��ļĴ���              $s0    $s1          
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3520               #RAM��Ӧ370
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret  	