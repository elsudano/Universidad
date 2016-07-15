	.file	"SumaVectoresC.c"
	.comm	v1,268435456,32
	.comm	v2,268435456,32
	.comm	v3,268435456,32
	.section	.rodata
	.align 8
.LC0:
	.string	"Faltan n\302\272 componentes del vector"
.LC3:
	.string	"%11.9f\n"
	.align 8
.LC4:
	.string	"Tiempo(seg.):%11.9f\nTama\303\261o Vectores:%u\nV1[0]+V2[0]=V3[0](%8.6f+%8.6f=%8.6f)\nV1[%d]+V2[%d]=V3[%d](%8.6f+%8.6f=%8.6f)\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB2:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	pushq	%r12
	pushq	%rbx
	subq	$96, %rsp
	.cfi_offset 12, -24
	.cfi_offset 3, -32
	movl	%edi, -84(%rbp)
	movq	%rsi, -96(%rbp)
	cmpl	$1, -84(%rbp)
	jg	.L2
	movl	$.LC0, %edi
	call	puts
	movl	$-1, %edi
	call	exit
.L2:
	movq	-96(%rbp), %rax
	addq	$8, %rax
	movq	(%rax), %rax
	movq	%rax, %rdi
	call	atoi
	movl	%eax, -24(%rbp)
	movq	-96(%rbp), %rax
	addq	$16, %rax
	movq	(%rax), %rax
	movq	%rax, %rdi
	call	atoi
	movl	%eax, -28(%rbp)
	cmpl	$33554432, -24(%rbp)
	jbe	.L3
	movl	$33554432, -24(%rbp)
.L3:
	movl	$0, -20(%rbp)
	jmp	.L4
.L9:
	movl	-24(%rbp), %eax
	testq	%rax, %rax
	js	.L5
	cvtsi2sdq	%rax, %xmm0
	jmp	.L6
.L5:
	movq	%rax, %rdx
	shrq	%rdx
	andl	$1, %eax
	orq	%rax, %rdx
	cvtsi2sdq	%rdx, %xmm0
	addsd	%xmm0, %xmm0
.L6:
	movsd	.LC1(%rip), %xmm1
	mulsd	%xmm0, %xmm1
	cvtsi2sd	-20(%rbp), %xmm0
	movsd	.LC1(%rip), %xmm2
	mulsd	%xmm2, %xmm0
	addsd	%xmm1, %xmm0
	movl	-20(%rbp), %eax
	cltq
	movsd	%xmm0, v1(,%rax,8)
	movl	-24(%rbp), %eax
	testq	%rax, %rax
	js	.L7
	cvtsi2sdq	%rax, %xmm0
	jmp	.L8
.L7:
	movq	%rax, %rdx
	shrq	%rdx
	andl	$1, %eax
	orq	%rax, %rdx
	cvtsi2sdq	%rdx, %xmm0
	addsd	%xmm0, %xmm0
.L8:
	movsd	.LC1(%rip), %xmm1
	mulsd	%xmm1, %xmm0
	cvtsi2sd	-20(%rbp), %xmm1
	movsd	.LC1(%rip), %xmm2
	mulsd	%xmm2, %xmm1
	subsd	%xmm1, %xmm0
	movl	-20(%rbp), %eax
	cltq
	movsd	%xmm0, v2(,%rax,8)
	addl	$1, -20(%rbp)
.L4:
	movl	-20(%rbp), %eax
	cmpl	-24(%rbp), %eax
	jb	.L9
	leaq	-64(%rbp), %rax
	movq	%rax, %rsi
	movl	$0, %edi
	call	clock_gettime
	movl	$0, -20(%rbp)
	jmp	.L10
.L11:
	movl	-20(%rbp), %eax
	cltq
	movsd	v1(,%rax,8), %xmm1
	movl	-20(%rbp), %eax
	cltq
	movsd	v2(,%rax,8), %xmm0
	addsd	%xmm1, %xmm0
	movl	-20(%rbp), %eax
	cltq
	movsd	%xmm0, v3(,%rax,8)
	addl	$1, -20(%rbp)
.L10:
	movl	-20(%rbp), %eax
	cmpl	-24(%rbp), %eax
	jb	.L11
	leaq	-80(%rbp), %rax
	movq	%rax, %rsi
	movl	$0, %edi
	call	clock_gettime
	movq	-80(%rbp), %rdx
	movq	-64(%rbp), %rax
	subq	%rax, %rdx
	movq	%rdx, %rax
	cvtsi2sdq	%rax, %xmm1
	movq	-72(%rbp), %rdx
	movq	-56(%rbp), %rax
	subq	%rax, %rdx
	movq	%rdx, %rax
	cvtsi2sdq	%rax, %xmm0
	movsd	.LC2(%rip), %xmm2
	divsd	%xmm2, %xmm0
	addsd	%xmm1, %xmm0
	movsd	%xmm0, -40(%rbp)
	cmpl	$1, -28(%rbp)
	jne	.L12
	movq	-40(%rbp), %rax
	movq	%rax, -104(%rbp)
	movsd	-104(%rbp), %xmm0
	movl	$.LC3, %edi
	movl	$1, %eax
	call	printf
	jmp	.L13
.L12:
	movl	-24(%rbp), %eax
	subl	$1, %eax
	movl	%eax, %eax
	movq	v3(,%rax,8), %r8
	movl	-24(%rbp), %eax
	subl	$1, %eax
	movl	%eax, %eax
	movq	v2(,%rax,8), %rcx
	movl	-24(%rbp), %eax
	subl	$1, %eax
	movl	%eax, %eax
	movq	v1(,%rax,8), %rdx
	movl	-24(%rbp), %eax
	leal	-1(%rax), %r12d
	movl	-24(%rbp), %eax
	leal	-1(%rax), %ebx
	movl	-24(%rbp), %eax
	leal	-1(%rax), %r11d
	movq	v3(%rip), %r9
	movq	v2(%rip), %rdi
	movq	v1(%rip), %rsi
	movl	-24(%rbp), %r10d
	movq	-40(%rbp), %rax
	movq	%r8, -104(%rbp)
	movsd	-104(%rbp), %xmm6
	movq	%rcx, -104(%rbp)
	movsd	-104(%rbp), %xmm5
	movq	%rdx, -104(%rbp)
	movsd	-104(%rbp), %xmm4
	movl	%r12d, %r8d
	movl	%ebx, %ecx
	movl	%r11d, %edx
	movq	%r9, -104(%rbp)
	movsd	-104(%rbp), %xmm3
	movq	%rdi, -104(%rbp)
	movsd	-104(%rbp), %xmm2
	movq	%rsi, -104(%rbp)
	movsd	-104(%rbp), %xmm1
	movl	%r10d, %esi
	movq	%rax, -104(%rbp)
	movsd	-104(%rbp), %xmm0
	movl	$.LC4, %edi
	movl	$7, %eax
	call	printf
.L13:
	movl	$0, %eax
	addq	$96, %rsp
	popq	%rbx
	popq	%r12
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	main, .-main
	.section	.rodata
	.align 8
.LC1:
	.long	2576980378
	.long	1069128089
	.align 8
.LC2:
	.long	0
	.long	1104006501
	.ident	"GCC: (GNU) 4.8.2 20131212 (Red Hat 4.8.2-7)"
	.section	.note.GNU-stack,"",@progbits
