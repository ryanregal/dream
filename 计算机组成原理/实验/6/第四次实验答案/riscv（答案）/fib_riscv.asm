#RISC-V计算费波那契数列                           
#                                                 n存放在地址为0的数据存储器中（可以改变）
#                                                 计算好的n+1个数（0、1、1、2、3、5、8、13、21、34、……）       存放在地址为4、8、12、...4*(n+1)的数据存储器中

main:
	ori a1, zero, 0 			# a1 = 0
	sw a1, 4(zero)                             	# 0 -> (4)

	ori a1, zero, 1 			# a1 = 1
	sw a1, 8(zero)                             	# 1 -> (8)

	sw a1, 12(zero)                          	# 1 -> (12)

                lw a2,0(zero)			# (0) -> a2                

	ori a3, zero, 2			# a3 = 2
 	ori a4, zero, 1 			# a4 = 1
	ori a5, zero, 1 			# a5 = 1
loop:
	add a6, a4, a5 			# a4+a5 -> a6	
	ori a4, a5, 0 			# a5 -> a4
	ori a5, a6, 0 			# a6 -> a5
                add a3,a3,a1                           		# a3+1 -> a3
                add a7,a3,a3                          		# a3*2 -> a7
                add a0,a7,a7                          		# a7*2 -> a0
	sw a6, 4(a0)                            		# a6 -> 4(a0)                      
                beq a2, a3, end 			# if a2=a3 goto end
	jal zero, loop             		# goto loop
end:
	jal zero, end                                            # goto end
