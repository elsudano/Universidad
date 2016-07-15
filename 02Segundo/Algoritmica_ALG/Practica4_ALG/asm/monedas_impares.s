	.file	"monedas_impares.c"
	.text
.Ltext0:
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"Monedero: ["
.LC1:
	.string	"%d"
.LC2:
	.string	", "
	.text
	.p2align 4,,15
	.globl	imprimeMonedero
	.type	imprimeMonedero, @function
imprimeMonedero:
.LFB32:
	.file 1 "src/monedas_impares.c"
	.loc 1 34 0
	.cfi_startproc
.LVL0:
	pushq	%r13
	.cfi_def_cfa_offset 16
	.cfi_offset 13, -16
	.loc 1 36 0
	xorl	%eax, %eax
	.loc 1 34 0
	pushq	%r12
	.cfi_def_cfa_offset 24
	.cfi_offset 12, -24
	movl	%esi, %r12d
	leal	-1(%r12), %r13d
	pushq	%rbp
	.cfi_def_cfa_offset 32
	.cfi_offset 6, -32
	movq	%rdi, %rbp
	.loc 1 36 0
	movl	$.LC0, %edi
.LVL1:
	.loc 1 34 0
	pushq	%rbx
	.cfi_def_cfa_offset 40
	.cfi_offset 3, -40
	.loc 1 35 0
	xorl	%ebx, %ebx
	.loc 1 34 0
	subq	$8, %rsp
	.cfi_def_cfa_offset 48
	.loc 1 36 0
	call	printf
.LVL2:
	.loc 1 37 0
	testl	%r12d, %r12d
	jle	.L5
.LVL3:
	.p2align 4,,10
	.p2align 3
.L6:
	.loc 1 38 0
	movl	0(%rbp), %esi
	xorl	%eax, %eax
	movl	$.LC1, %edi
	call	printf
.LVL4:
	.loc 1 39 0
	cmpl	%r13d, %ebx
	jge	.L3
	.loc 1 40 0
	movl	$.LC2, %edi
	xorl	%eax, %eax
	call	printf
.LVL5:
.L4:
	.loc 1 37 0
	addl	$1, %ebx
.LVL6:
	addq	$4, %rbp
	cmpl	%r12d, %ebx
	jne	.L6
.LVL7:
.L5:
	.loc 1 45 0
	addq	$8, %rsp
	.cfi_remember_state
	.cfi_def_cfa_offset 40
	.loc 1 44 0
	movl	$10, %edi
	.loc 1 45 0
	popq	%rbx
	.cfi_def_cfa_offset 32
	popq	%rbp
	.cfi_def_cfa_offset 24
	popq	%r12
	.cfi_def_cfa_offset 16
.LVL8:
	popq	%r13
	.cfi_def_cfa_offset 8
.LVL9:
	.loc 1 44 0
	jmp	putchar
.LVL10:
.L3:
	.cfi_restore_state
	.loc 1 42 0
	movl	$93, %edi
	call	putchar
.LVL11:
	jmp	.L4
	.cfi_endproc
.LFE32:
	.size	imprimeMonedero, .-imprimeMonedero
	.section	.rodata.str1.1
.LC3:
	.string	"\nA Devolver --> "
.LC4:
	.string	"\n________________"
.LC5:
	.string	"%2d "
.LC6:
	.string	"_"
.LC7:
	.string	"___"
.LC8:
	.string	"Mon. tipo %d -> "
	.text
	.p2align 4,,15
	.globl	imprimeTabla
	.type	imprimeTabla, @function
imprimeTabla:
.LFB33:
	.loc 1 56 0
	.cfi_startproc
.LVL12:
	pushq	%r15
	.cfi_def_cfa_offset 16
	.cfi_offset 15, -16
	.loc 1 58 0
	xorl	%eax, %eax
	.loc 1 56 0
	pushq	%r14
	.cfi_def_cfa_offset 24
	.cfi_offset 14, -24
	pushq	%r13
	.cfi_def_cfa_offset 32
	.cfi_offset 13, -32
	pushq	%r12
	.cfi_def_cfa_offset 40
	.cfi_offset 12, -40
	movq	%rsi, %r12
	pushq	%rbp
	.cfi_def_cfa_offset 48
	.cfi_offset 6, -48
	movq	%rdi, %rbp
	.loc 1 58 0
	movl	$.LC3, %edi
.LVL13:
	.loc 1 56 0
	pushq	%rbx
	.cfi_def_cfa_offset 56
	.cfi_offset 3, -56
	movl	%ecx, %ebx
	subq	$24, %rsp
	.cfi_def_cfa_offset 80
	.loc 1 56 0
	movl	%edx, 12(%rsp)
	.loc 1 58 0
	call	printf
.LVL14:
	.loc 1 59 0
	testl	%ebx, %ebx
	js	.L31
	movl	0(%rbp), %eax
	xorl	%r13d, %r13d
	jmp	.L17
.LVL15:
	.p2align 4,,10
	.p2align 3
.L33:
	.loc 1 61 0
	xorl	%eax, %eax
	movl	%ebx, %esi
	movl	$.LC5, %edi
	call	printf
.LVL16:
	.loc 1 59 0
	movl	0(%rbp), %eax
	addl	%eax, %r13d
.LVL17:
	cmpl	%r13d, %ebx
	jl	.L32
.LVL18:
.L17:
	.loc 1 60 0
	movl	%ebx, %edx
	subl	%r13d, %edx
	cmpl	%edx, %eax
	jg	.L33
	.loc 1 63 0
	movl	%r13d, %esi
	xorl	%eax, %eax
	movl	$.LC5, %edi
	call	printf
.LVL19:
	.loc 1 59 0
	movl	0(%rbp), %eax
	addl	%eax, %r13d
.LVL20:
	cmpl	%r13d, %ebx
	jge	.L17
.L32:
	.loc 1 65 0
	xorl	%eax, %eax
	movl	$.LC4, %edi
	call	printf
.LVL21:
	.loc 1 66 0
	testl	%ebx, %ebx
	jle	.L13
	xorl	%r13d, %r13d
.LVL22:
	.p2align 4,,10
	.p2align 3
.L20:
	.loc 1 67 0 discriminator 2
	xorl	%eax, %eax
	movl	$.LC7, %edi
	call	printf
.LVL23:
	.loc 1 66 0 discriminator 2
	addl	0(%rbp), %r13d
.LVL24:
	cmpl	%r13d, %ebx
	jg	.L20
.LVL25:
.L13:
	.loc 1 69 0
	movl	$.LC6, %edi
	.loc 1 70 0
	xorl	%r14d, %r14d
	movslq	%ebx, %r13
	.loc 1 69 0
	call	puts
.LVL26:
	.loc 1 70 0
	movl	12(%rsp), %eax
	testl	%eax, %eax
	jle	.L9
.LVL27:
	.p2align 4,,10
	.p2align 3
.L28:
	.loc 1 71 0
	addl	$1, %r14d
.LVL28:
	xorl	%eax, %eax
	movl	$.LC8, %edi
	movl	%r14d, %esi
	call	printf
.LVL29:
	.loc 1 72 0
	testl	%ebx, %ebx
	js	.L25
	movl	0(%rbp), %eax
	xorl	%r15d, %r15d
	jmp	.L26
.LVL30:
	.p2align 4,,10
	.p2align 3
.L34:
	.loc 1 74 0
	movl	(%r12,%r13,4), %esi
	xorl	%eax, %eax
	movl	$.LC5, %edi
	call	printf
.LVL31:
	.loc 1 72 0
	movl	0(%rbp), %eax
	addl	%eax, %r15d
.LVL32:
	cmpl	%r15d, %ebx
	jl	.L25
.LVL33:
.L26:
	.loc 1 73 0
	movl	%ebx, %ecx
	subl	%r15d, %ecx
	cmpl	%eax, %ecx
	jl	.L34
	.loc 1 76 0
	movslq	%r15d, %rax
	movl	$.LC5, %edi
	movl	(%r12,%rax,4), %esi
	xorl	%eax, %eax
	call	printf
.LVL34:
	.loc 1 72 0
	movl	0(%rbp), %eax
	addl	%eax, %r15d
.LVL35:
	cmpl	%r15d, %ebx
	jge	.L26
.LVL36:
.L25:
	.loc 1 78 0
	movl	$10, %edi
	addq	$39996, %r12
	call	putchar
.LVL37:
	.loc 1 70 0
	cmpl	12(%rsp), %r14d
	jne	.L28
.LVL38:
.L9:
	.loc 1 80 0
	addq	$24, %rsp
	.cfi_remember_state
	.cfi_def_cfa_offset 56
	popq	%rbx
	.cfi_def_cfa_offset 48
.LVL39:
	popq	%rbp
	.cfi_def_cfa_offset 40
.LVL40:
	popq	%r12
	.cfi_def_cfa_offset 32
	popq	%r13
	.cfi_def_cfa_offset 24
.LVL41:
	popq	%r14
	.cfi_def_cfa_offset 16
	popq	%r15
	.cfi_def_cfa_offset 8
	ret
.LVL42:
.L31:
	.cfi_restore_state
	.loc 1 65 0
	movl	$.LC4, %edi
	xorl	%eax, %eax
	call	printf
.LVL43:
	jmp	.L13
	.cfi_endproc
.LFE33:
	.size	imprimeTabla, .-imprimeTabla
	.section	.rodata.str1.8,"aMS",@progbits,1
	.align 8
.LC9:
	.string	" La cantidad de parametros es incorrecta "
	.align 8
.LC10:
	.string	" La cantidad valores de las monedas es diferente al numero de monedas "
	.align 8
.LC11:
	.string	" No hay una soluci\303\263n optima posible "
	.align 8
.LC12:
	.string	" Uso: %s [Cantidad Monedas] [Valor Monedas separadas por ,] [Euros a devolver]\n\n"
	.section	.rodata.str1.1
.LC13:
	.string	" Ejemplo: %s 3 1,2,5 13\n"
	.text
	.p2align 4,,15
	.globl	funError
	.type	funError, @function
funError:
.LFB34:
	.loc 1 85 0
	.cfi_startproc
.LVL44:
	.loc 1 86 0
	cmpl	$2, %edi
	.loc 1 85 0
	pushq	%rbx
	.cfi_def_cfa_offset 16
	.cfi_offset 3, -16
	.loc 1 85 0
	movl	%edi, %ebx
	.loc 1 86 0
	je	.L37
	cmpl	$3, %edi
	je	.L38
	cmpl	$1, %edi
	je	.L41
.LVL45:
.L36:
	.loc 1 98 0
	movq	nomPrograma(%rip), %rsi
	movl	$.LC12, %edi
	xorl	%eax, %eax
	.loc 1 100 0
	negl	%ebx
.LVL46:
	.loc 1 98 0
	call	printf
.LVL47:
	.loc 1 99 0
	movq	nomPrograma(%rip), %rsi
	movl	$.LC13, %edi
	xorl	%eax, %eax
	call	printf
.LVL48:
	.loc 1 100 0
	movl	%ebx, %edi
	call	exit
.LVL49:
.L41:
	.loc 1 88 0
	movl	$.LC9, %edi
	call	puts
.LVL50:
	.loc 1 89 0
	jmp	.L36
.L38:
	.loc 1 94 0
	movl	$.LC11, %edi
	call	puts
.LVL51:
	.loc 1 95 0
	.p2align 4,,3
	jmp	.L36
.L37:
	.loc 1 91 0
	movl	$.LC10, %edi
	call	puts
.LVL52:
	.loc 1 92 0
	.p2align 4,,3
	jmp	.L36
	.cfi_endproc
.LFE34:
	.size	funError, .-funError
	.p2align 4,,15
	.globl	min
	.type	min, @function
min:
.LFB35:
	.loc 1 108 0
	.cfi_startproc
.LVL53:
	.loc 1 109 0
	cmpl	%edi, %esi
	movl	%edi, %eax
	cmovle	%esi, %eax
	.loc 1 110 0
	ret
	.cfi_endproc
.LFE35:
	.size	min, .-min
	.p2align 4,,15
	.globl	Cambio
	.type	Cambio, @function
Cambio:
.LFB36:
	.loc 1 116 0
	.cfi_startproc
.LVL54:
	.loc 1 124 0
	movslq	%esi, %rax
.LVL55:
	.loc 1 116 0
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	.loc 1 124 0
	leaq	18(,%rax,4), %rax
.LVL56:
	.loc 1 116 0
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	.loc 1 124 0
	andq	$-16, %rax
	subq	%rax, %rsp
	.loc 1 134 0
	xorl	%eax, %eax
	testl	%esi, %esi
	.loc 1 124 0
	movq	%rsp, %rcx
.LVL57:
	.loc 1 134 0
	jle	.L47
.LVL58:
	.p2align 4,,10
	.p2align 3
.L60:
	.loc 1 135 0 discriminator 2
	movl	$0, (%rcx,%rax,4)
.LVL59:
	addq	$1, %rax
.LVL60:
	.loc 1 134 0 discriminator 2
	cmpl	%eax, %esi
	jg	.L60
.L47:
.LVL61:
	.loc 1 144 0
	xorl	%eax, %eax
	.loc 1 143 0
	testl	%edx, %edx
	je	.L45
	.loc 1 145 0
	testl	%esi, %esi
	jg	.L65
.L56:
	.loc 1 146 0
	movl	$999999, %eax
.L45:
.LVL62:
	.loc 1 163 0
	leave
	.cfi_remember_state
	.cfi_def_cfa 7, 8
	ret
.LVL63:
	.p2align 4,,10
	.p2align 3
.L65:
	.cfi_restore_state
	.loc 1 145 0
	movl	%edx, %eax
	shrl	$31, %eax
	testb	%al, %al
	jne	.L56
	movl	%esi, %eax
.LVL64:
	.p2align 4,,10
	.p2align 3
.L49:
	.loc 1 148 0 discriminator 1
	testl	%eax, %eax
	jle	.L57
.L66:
	testl	%edx, %edx
	jle	.L57
	.loc 1 149 0
	movslq	%eax, %r8
	movl	-4(%rdi,%r8,4), %r8d
	cmpl	%r8d, %edx
	jl	.L50
	.loc 1 150 0
	subl	%r8d, %edx
.LVL65:
	.loc 1 151 0
	leal	-1(%rax), %r8d
	movslq	%r8d, %r8
	addl	$1, (%rcx,%r8,4)
	.loc 1 148 0
	testl	%eax, %eax
	jg	.L66
.L57:
	xorl	%edx, %edx
.LVL66:
	xorl	%eax, %eax
.LVL67:
	.p2align 4,,10
	.p2align 3
.L54:
	.loc 1 159 0 discriminator 2
	addl	(%rcx,%rdx,4), %eax
.LVL68:
	addq	$1, %rdx
.LVL69:
	.loc 1 158 0 discriminator 2
	cmpl	%edx, %esi
	jg	.L54
	.loc 1 163 0
	leave
	.cfi_remember_state
	.cfi_def_cfa 7, 8
	ret
.LVL70:
	.p2align 4,,10
	.p2align 3
.L50:
	.cfi_restore_state
	.loc 1 153 0
	subl	$1, %eax
.LVL71:
	jmp	.L49
	.cfi_endproc
.LFE36:
	.size	Cambio, .-Cambio
	.section	.rodata.str1.1
.LC14:
	.string	","
.LC15:
	.string	"Cantidad a devolver: %d \342\202\254\n"
.LC16:
	.string	"\nSolucion: "
.LC17:
	.string	"%2d Monedas de %d \342\202\254\n"
	.section	.text.startup,"ax",@progbits
	.p2align 4,,15
	.globl	main
	.type	main, @function
main:
.LFB37:
	.loc 1 168 0
	.cfi_startproc
.LVL72:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
.LVL73:
.LBB11:
.LBB12:
	.file 2 "/usr/include/stdlib.h"
	.loc 2 280 0
	movl	$10, %edx
.LBE12:
.LBE11:
	.loc 1 168 0
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	pushq	%r15
	pushq	%r14
	.cfi_offset 15, -24
	.cfi_offset 14, -32
	movq	%rsi, %r14
	pushq	%r13
	.cfi_offset 13, -40
	movl	%edi, %r13d
	pushq	%r12
	pushq	%rbx
	subq	$600024, %rsp
	.cfi_offset 12, -48
	.cfi_offset 3, -56
.LBB14:
.LBB13:
	.loc 2 280 0
	movq	8(%rsi), %rdi
.LVL74:
	xorl	%esi, %esi
.LVL75:
	call	strtol
.LVL76:
.LBE13:
.LBE14:
	.loc 1 169 0
	movslq	%eax, %rbx
.LVL77:
	.loc 1 171 0
	movq	16(%r14), %rdi
	.loc 1 169 0
	salq	$2, %rbx
.LVL78:
	leaq	18(%rbx), %rax
.LVL79:
	andq	$-16, %rax
	subq	%rax, %rsp
	.loc 1 170 0
	movq	(%r14), %rax
	.loc 1 169 0
	leaq	3(%rsp), %r12
	shrq	$2, %r12
	.loc 1 177 0
	cmpl	$4, %r13d
	.loc 1 170 0
	movq	%rax, nomPrograma(%rip)
	.loc 1 169 0
	leaq	0(,%r12,4), %r15
.LVL80:
	.loc 1 177 0
	je	.L111
	.loc 1 187 0
	cmpl	$3, %r13d
	jle	.L74
	.loc 1 174 0
	xorl	%r11d, %r11d
.LVL81:
.L72:
	movl	0(,%r12,4), %r10d
	shrq	$2, %rbx
	movl	%r11d, %r9d
	leaq	1(%rbx), %r8
	movq	%rbx, -600040(%rbp)
	.loc 1 212 0 discriminator 1
	movq	$0, -600032(%rbp)
	xorl	%r13d, %r13d
	movl	%r11d, -600016(%rbp)
	movslq	%r10d, %rax
	movl	%r10d, %r14d
.LVL82:
	movq	%rax, -600056(%rbp)
	movl	%r10d, %eax
	negl	%eax
	movl	%eax, -600044(%rbp)
	leaq	-600000(%rbp), %rax
	movq	%rax, -600064(%rbp)
.LVL83:
	.p2align 4,,10
	.p2align 3
.L75:
	cmpq	$0, -600040(%rbp)
	je	.L79
	movq	-600064(%rbp), %r12
	.loc 1 212 0 is_stmt 0
	movl	$1, %ebx
	movl	%r14d, %ecx
	jmp	.L80
.LVL84:
	.p2align 4,,10
	.p2align 3
.L113:
	.loc 1 214 0 is_stmt 1
	movl	$0, (%r12)
.L77:
.LVL85:
	addq	$1, %rbx
	addq	$39996, %r12
	.loc 1 212 0
	cmpq	%r8, %rbx
	je	.L112
.L80:
	.loc 1 213 0
	testl	%r13d, %r13d
	leal	-1(%rbx), %r14d
.LVL86:
	je	.L113
	.loc 1 215 0
	cmpl	%r9d, %ecx
	jg	.L77
	.loc 1 216 0
	movl	%ebx, %esi
	movl	%r13d, %edx
	movq	%r15, %rdi
	movq	%r8, -600024(%rbp)
	movl	%r9d, -600012(%rbp)
	movl	%ecx, -600008(%rbp)
	call	Cambio
.LVL87:
	movl	%eax, -600004(%rbp)
.LVL88:
	.loc 1 217 0
	movslq	%r14d, %rax
.LVL89:
	movl	%r13d, %edx
	subl	(%r15,%rax,4), %edx
	movl	%r14d, %esi
	movq	%r15, %rdi
	call	Cambio
.LVL90:
.LBB15:
.LBB16:
	.loc 1 109 0
	movl	-600004(%rbp), %r10d
.LBE16:
.LBE15:
	.loc 1 217 0
	addl	$1, %eax
.LVL91:
	.loc 1 218 0
	movq	-600024(%rbp), %r8
	movl	-600012(%rbp), %r9d
	movl	-600008(%rbp), %ecx
.LVL92:
.LBB18:
.LBB17:
	.loc 1 109 0
	cmpl	%r10d, %eax
	cmovle	%eax, %r10d
.LBE17:
.LBE18:
	.loc 1 218 0
	movq	-600032(%rbp), %rax
.LVL93:
	addq	$1, %rbx
	movl	%r10d, (%r12,%rax,4)
	addq	$39996, %r12
	.loc 1 212 0
	cmpq	%r8, %rbx
	jne	.L80
.LVL94:
	.p2align 4,,10
	.p2align 3
.L112:
	movl	%ecx, %r14d
.L79:
	.loc 1 223 0
	addl	%r14d, %r13d
.LVL95:
	movq	-600056(%rbp), %rax
	addl	-600044(%rbp), %r9d
	addq	%rax, -600032(%rbp)
	.loc 1 211 0
	cmpl	-600016(%rbp), %r13d
	jle	.L75
	movl	-600016(%rbp), %r11d
.LVL96:
.L73:
	.loc 1 240 0
	movl	%r11d, %esi
	movl	$.LC15, %edi
	xorl	%eax, %eax
	movl	%r11d, -600004(%rbp)
	call	printf
.LVL97:
	.loc 1 246 0
	movq	-600040(%rbp), %r12
	movq	%r15, %rdi
	movl	%r12d, %esi
	call	imprimeMonedero
.LVL98:
	.loc 1 248 0
	movl	-600004(%rbp), %r11d
	movq	-600064(%rbp), %rsi
	movl	%r12d, %edx
	movq	%r15, %rdi
	movl	%r11d, %ecx
	call	imprimeTabla
.LVL99:
	.loc 1 257 0
	leal	-1(%r12), %edx
.LVL100:
	.loc 1 259 0
	salq	$2, %r12
	.loc 1 261 0
	movl	-600004(%rbp), %r11d
	.loc 1 259 0
	leaq	18(%r12), %rax
	.loc 1 261 0
	shrq	$2, %r12
	.loc 1 259 0
	andq	$-16, %rax
	subq	%rax, %rsp
	.loc 1 261 0
	testq	%r12, %r12
	.loc 1 259 0
	movq	%rsp, %r13
.LVL101:
	movq	%rsp, %rax
	leaq	(%rsp,%r12,4), %rcx
	.loc 1 261 0
	je	.L110
.LVL102:
	.p2align 4,,10
	.p2align 3
.L102:
	.loc 1 262 0 discriminator 2
	movl	$0, (%rax)
	addq	$4, %rax
	.loc 1 261 0 discriminator 2
	cmpq	%rcx, %rax
	jne	.L102
	jmp	.L110
.LVL103:
	.p2align 4,,10
	.p2align 3
.L106:
	.loc 1 265 0 discriminator 1
	leal	-1(%rdx), %edi
	movslq	%edx, %rax
	movslq	%r11d, %rcx
	imulq	$9999, %rax, %r8
	movslq	%edi, %r9
	imulq	$9999, %r9, %r9
	addq	%rcx, %r8
	addq	%r9, %rcx
	movl	-600000(%rbp,%rcx,4), %ecx
	cmpl	%ecx, -600000(%rbp,%r8,4)
	je	.L93
.L85:
	.loc 1 268 0
	addl	$1, 0(%r13,%rax,4)
	.loc 1 269 0
	subl	(%r15,%rax,4), %r11d
.LVL104:
.L110:
	movl	%r11d, %esi
	notl	%esi
	.loc 1 264 0
	movl	%esi, %eax
	shrl	$31, %eax
	testb	%al, %al
	je	.L94
.L114:
	.loc 1 264 0 is_stmt 0 discriminator 1
	testl	%edx, %edx
	js	.L94
	.loc 1 265 0 is_stmt 1
	testl	%edx, %edx
	jne	.L106
	xorl	%eax, %eax
	.p2align 4,,5
	jmp	.L85
	.p2align 4,,10
	.p2align 3
.L93:
	.loc 1 264 0
	movl	%esi, %eax
	.loc 1 266 0
	movl	%edi, %edx
.LVL105:
	.loc 1 264 0
	shrl	$31, %eax
	testb	%al, %al
	jne	.L114
	.p2align 4,,10
	.p2align 3
.L94:
	.loc 1 273 0
	movl	$.LC16, %edi
	.loc 1 274 0
	xorl	%ebx, %ebx
	.loc 1 273 0
	call	puts
.LVL106:
	.loc 1 274 0
	testq	%r12, %r12
	je	.L98
.LVL107:
	.p2align 4,,10
	.p2align 3
.L103:
	.loc 1 275 0 discriminator 2
	movslq	%ebx, %rax
	movl	$.LC17, %edi
	addq	$1, %rbx
.LVL108:
	movl	(%r15,%rax,4), %edx
	movl	0(%r13,%rax,4), %esi
	xorl	%eax, %eax
.LVL109:
	call	printf
.LVL110:
	.loc 1 274 0 discriminator 2
	cmpq	%r12, %rbx
	jne	.L103
.L98:
	.loc 1 279 0
	leaq	-40(%rbp), %rsp
.LVL111:
	xorl	%eax, %eax
	popq	%rbx
	popq	%r12
	popq	%r13
.LVL112:
	popq	%r14
	popq	%r15
.LVL113:
	popq	%rbp
	.cfi_remember_state
	.cfi_def_cfa 7, 8
.LVL114:
	ret
.LVL115:
.L111:
	.cfi_restore_state
	.loc 1 178 0
	movl	$.LC14, %esi
	call	strtok
.LVL116:
	.loc 1 179 0
	testq	%rax, %rax
	je	.L92
	.loc 1 175 0
	xorb	%r13b, %r13b
.LVL117:
	.p2align 4,,10
	.p2align 3
.L70:
.LBB19:
.LBB20:
	.loc 2 280 0
	xorl	%esi, %esi
	movl	$10, %edx
	movq	%rax, %rdi
	call	strtol
.LVL118:
.LBE20:
.LBE19:
	.loc 1 180 0
	movslq	%r13d, %rdx
	.loc 1 182 0
	xorl	%edi, %edi
	movl	$.LC14, %esi
.LBB22:
.LBB21:
	.loc 2 280 0
	movl	%eax, (%r15,%rdx,4)
.LBE21:
.LBE22:
	.loc 1 181 0
	addl	$1, %r13d
.LVL119:
	.loc 1 182 0
	call	strtok
.LVL120:
	.loc 1 179 0
	testq	%rax, %rax
	jne	.L70
.LVL121:
.L69:
	.loc 1 184 0
	movslq	%r13d, %rax
.LVL122:
	movq	%rax, %rdi
	movq	%rax, -600040(%rbp)
	movq	%rbx, %rax
	shrq	$2, %rax
	cmpq	%rax, %rdi
	jne	.L115
.LVL123:
.LBB23:
.LBB24:
	.loc 2 280 0
	movq	24(%r14), %rdi
	xorl	%esi, %esi
	movl	$10, %edx
	call	strtol
.LVL124:
.LBE24:
.LBE23:
	.loc 1 211 0
	testl	%eax, %eax
.LBB26:
.LBB25:
	.loc 2 280 0
	movl	%eax, %r11d
.LVL125:
.LBE25:
.LBE26:
	.loc 1 211 0
	jns	.L72
	leaq	-600000(%rbp), %rax
.LVL126:
	movq	%rax, -600064(%rbp)
	jmp	.L73
.LVL127:
.L92:
	.loc 1 175 0
	xorl	%r13d, %r13d
.LVL128:
	jmp	.L69
.LVL129:
.L74:
	.loc 1 188 0
	movl	$1, %edi
.LVL130:
	call	funError
.LVL131:
.L115:
	.loc 1 185 0
	movl	$2, %edi
	call	funError
.LVL132:
	.cfi_endproc
.LFE37:
	.size	main, .-main
	.comm	nomPrograma,8,8
	.text
.Letext0:
	.file 3 "/usr/lib/gcc/x86_64-redhat-linux/4.8.2/include/stddef.h"
	.file 4 "/usr/include/bits/types.h"
	.file 5 "/usr/include/libio.h"
	.file 6 "/usr/include/stdio.h"
	.file 7 "<interno>"
	.file 8 "/usr/include/string.h"
	.section	.debug_info,"",@progbits
.Ldebug_info0:
	.long	0xba4
	.value	0x4
	.long	.Ldebug_abbrev0
	.byte	0x8
	.uleb128 0x1
	.long	.LASF870
	.byte	0x1
	.long	.LASF871
	.long	.LASF872
	.long	.Ldebug_ranges0+0xc0
	.quad	0
	.long	.Ldebug_line0
	.long	.Ldebug_macro0
	.uleb128 0x2
	.long	.LASF791
	.byte	0x3
	.byte	0xd4
	.long	0x38
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF784
	.uleb128 0x3
	.byte	0x1
	.byte	0x8
	.long	.LASF785
	.uleb128 0x3
	.byte	0x2
	.byte	0x7
	.long	.LASF786
	.uleb128 0x3
	.byte	0x4
	.byte	0x7
	.long	.LASF787
	.uleb128 0x3
	.byte	0x1
	.byte	0x6
	.long	.LASF788
	.uleb128 0x3
	.byte	0x2
	.byte	0x5
	.long	.LASF789
	.uleb128 0x4
	.byte	0x4
	.byte	0x5
	.string	"int"
	.uleb128 0x3
	.byte	0x8
	.byte	0x5
	.long	.LASF790
	.uleb128 0x2
	.long	.LASF792
	.byte	0x4
	.byte	0x8c
	.long	0x69
	.uleb128 0x2
	.long	.LASF793
	.byte	0x4
	.byte	0x8d
	.long	0x69
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF794
	.uleb128 0x5
	.byte	0x8
	.uleb128 0x6
	.byte	0x8
	.long	0x95
	.uleb128 0x3
	.byte	0x1
	.byte	0x6
	.long	.LASF795
	.uleb128 0x7
	.long	.LASF825
	.byte	0xd8
	.byte	0x5
	.byte	0xf6
	.long	0x21d
	.uleb128 0x8
	.long	.LASF796
	.byte	0x5
	.byte	0xf7
	.long	0x62
	.byte	0
	.uleb128 0x8
	.long	.LASF797
	.byte	0x5
	.byte	0xfc
	.long	0x8f
	.byte	0x8
	.uleb128 0x8
	.long	.LASF798
	.byte	0x5
	.byte	0xfd
	.long	0x8f
	.byte	0x10
	.uleb128 0x8
	.long	.LASF799
	.byte	0x5
	.byte	0xfe
	.long	0x8f
	.byte	0x18
	.uleb128 0x8
	.long	.LASF800
	.byte	0x5
	.byte	0xff
	.long	0x8f
	.byte	0x20
	.uleb128 0x9
	.long	.LASF801
	.byte	0x5
	.value	0x100
	.long	0x8f
	.byte	0x28
	.uleb128 0x9
	.long	.LASF802
	.byte	0x5
	.value	0x101
	.long	0x8f
	.byte	0x30
	.uleb128 0x9
	.long	.LASF803
	.byte	0x5
	.value	0x102
	.long	0x8f
	.byte	0x38
	.uleb128 0x9
	.long	.LASF804
	.byte	0x5
	.value	0x103
	.long	0x8f
	.byte	0x40
	.uleb128 0x9
	.long	.LASF805
	.byte	0x5
	.value	0x105
	.long	0x8f
	.byte	0x48
	.uleb128 0x9
	.long	.LASF806
	.byte	0x5
	.value	0x106
	.long	0x8f
	.byte	0x50
	.uleb128 0x9
	.long	.LASF807
	.byte	0x5
	.value	0x107
	.long	0x8f
	.byte	0x58
	.uleb128 0x9
	.long	.LASF808
	.byte	0x5
	.value	0x109
	.long	0x255
	.byte	0x60
	.uleb128 0x9
	.long	.LASF809
	.byte	0x5
	.value	0x10b
	.long	0x25b
	.byte	0x68
	.uleb128 0x9
	.long	.LASF810
	.byte	0x5
	.value	0x10d
	.long	0x62
	.byte	0x70
	.uleb128 0x9
	.long	.LASF811
	.byte	0x5
	.value	0x111
	.long	0x62
	.byte	0x74
	.uleb128 0x9
	.long	.LASF812
	.byte	0x5
	.value	0x113
	.long	0x70
	.byte	0x78
	.uleb128 0x9
	.long	.LASF813
	.byte	0x5
	.value	0x117
	.long	0x46
	.byte	0x80
	.uleb128 0x9
	.long	.LASF814
	.byte	0x5
	.value	0x118
	.long	0x54
	.byte	0x82
	.uleb128 0x9
	.long	.LASF815
	.byte	0x5
	.value	0x119
	.long	0x261
	.byte	0x83
	.uleb128 0x9
	.long	.LASF816
	.byte	0x5
	.value	0x11d
	.long	0x271
	.byte	0x88
	.uleb128 0x9
	.long	.LASF817
	.byte	0x5
	.value	0x126
	.long	0x7b
	.byte	0x90
	.uleb128 0x9
	.long	.LASF818
	.byte	0x5
	.value	0x12f
	.long	0x8d
	.byte	0x98
	.uleb128 0x9
	.long	.LASF819
	.byte	0x5
	.value	0x130
	.long	0x8d
	.byte	0xa0
	.uleb128 0x9
	.long	.LASF820
	.byte	0x5
	.value	0x131
	.long	0x8d
	.byte	0xa8
	.uleb128 0x9
	.long	.LASF821
	.byte	0x5
	.value	0x132
	.long	0x8d
	.byte	0xb0
	.uleb128 0x9
	.long	.LASF822
	.byte	0x5
	.value	0x133
	.long	0x2d
	.byte	0xb8
	.uleb128 0x9
	.long	.LASF823
	.byte	0x5
	.value	0x135
	.long	0x62
	.byte	0xc0
	.uleb128 0x9
	.long	.LASF824
	.byte	0x5
	.value	0x137
	.long	0x277
	.byte	0xc4
	.byte	0
	.uleb128 0xa
	.long	.LASF873
	.byte	0x5
	.byte	0x9b
	.uleb128 0x7
	.long	.LASF826
	.byte	0x18
	.byte	0x5
	.byte	0xa1
	.long	0x255
	.uleb128 0x8
	.long	.LASF827
	.byte	0x5
	.byte	0xa2
	.long	0x255
	.byte	0
	.uleb128 0x8
	.long	.LASF828
	.byte	0x5
	.byte	0xa3
	.long	0x25b
	.byte	0x8
	.uleb128 0x8
	.long	.LASF829
	.byte	0x5
	.byte	0xa7
	.long	0x62
	.byte	0x10
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x224
	.uleb128 0x6
	.byte	0x8
	.long	0x9c
	.uleb128 0xb
	.long	0x95
	.long	0x271
	.uleb128 0xc
	.long	0x86
	.byte	0
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x21d
	.uleb128 0xb
	.long	0x95
	.long	0x287
	.uleb128 0xc
	.long	0x86
	.byte	0x13
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x28d
	.uleb128 0xd
	.long	0x95
	.uleb128 0x6
	.byte	0x8
	.long	0x62
	.uleb128 0x3
	.byte	0x8
	.byte	0x5
	.long	.LASF830
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF831
	.uleb128 0x2
	.long	.LASF832
	.byte	0x1
	.byte	0x1c
	.long	0x2b1
	.uleb128 0xb
	.long	0x4d
	.long	0x2c8
	.uleb128 0xc
	.long	0x86
	.byte	0xe
	.uleb128 0xe
	.long	0x86
	.value	0x270e
	.byte	0
	.uleb128 0xf
	.long	.LASF833
	.byte	0x2
	.value	0x116
	.long	0x62
	.byte	0x3
	.long	0x2e6
	.uleb128 0x10
	.long	.LASF834
	.byte	0x2
	.value	0x116
	.long	0x287
	.byte	0
	.uleb128 0x11
	.string	"min"
	.byte	0x1
	.byte	0x6c
	.long	0x62
	.byte	0x1
	.long	0x30d
	.uleb128 0x12
	.long	.LASF835
	.byte	0x1
	.byte	0x6c
	.long	0x62
	.uleb128 0x12
	.long	.LASF836
	.byte	0x1
	.byte	0x6c
	.long	0x62
	.byte	0
	.uleb128 0x13
	.long	.LASF839
	.byte	0x1
	.byte	0x22
	.quad	.LFB32
	.quad	.LFE32-.LFB32
	.uleb128 0x1
	.byte	0x9c
	.long	0x3de
	.uleb128 0x14
	.long	.LASF837
	.byte	0x1
	.byte	0x22
	.long	0x292
	.long	.LLST0
	.uleb128 0x14
	.long	.LASF838
	.byte	0x1
	.byte	0x22
	.long	0x62
	.long	.LLST1
	.uleb128 0x15
	.string	"i"
	.byte	0x1
	.byte	0x23
	.long	0x62
	.long	.LLST2
	.uleb128 0x16
	.quad	.LVL2
	.long	0xb10
	.long	0x374
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC0
	.byte	0
	.uleb128 0x16
	.quad	.LVL4
	.long	0xb10
	.long	0x393
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC1
	.byte	0
	.uleb128 0x16
	.quad	.LVL5
	.long	0xb10
	.long	0x3b2
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC2
	.byte	0
	.uleb128 0x18
	.quad	.LVL10
	.long	0xb27
	.long	0x3c9
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x3a
	.byte	0
	.uleb128 0x19
	.quad	.LVL11
	.long	0xb27
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x8
	.byte	0x5d
	.byte	0
	.byte	0
	.uleb128 0x13
	.long	.LASF840
	.byte	0x1
	.byte	0x38
	.quad	.LFB33
	.quad	.LFE33-.LFB33
	.uleb128 0x1
	.byte	0x9c
	.long	0x5b1
	.uleb128 0x14
	.long	.LASF837
	.byte	0x1
	.byte	0x38
	.long	0x292
	.long	.LLST3
	.uleb128 0x14
	.long	.LASF841
	.byte	0x1
	.byte	0x38
	.long	0x5c2
	.long	.LLST4
	.uleb128 0x1a
	.string	"FIL"
	.byte	0x1
	.byte	0x38
	.long	0x62
	.long	.LLST5
	.uleb128 0x1a
	.string	"COL"
	.byte	0x1
	.byte	0x38
	.long	0x62
	.long	.LLST6
	.uleb128 0x15
	.string	"fil"
	.byte	0x1
	.byte	0x39
	.long	0x62
	.long	.LLST7
	.uleb128 0x15
	.string	"col"
	.byte	0x1
	.byte	0x39
	.long	0x62
	.long	.LLST8
	.uleb128 0x16
	.quad	.LVL14
	.long	0xb10
	.long	0x474
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC3
	.byte	0
	.uleb128 0x16
	.quad	.LVL16
	.long	0xb10
	.long	0x499
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC5
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.uleb128 0x16
	.quad	.LVL19
	.long	0xb10
	.long	0x4be
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC5
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x7d
	.sleb128 0
	.byte	0
	.uleb128 0x16
	.quad	.LVL21
	.long	0xb10
	.long	0x4dd
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC4
	.byte	0
	.uleb128 0x16
	.quad	.LVL23
	.long	0xb10
	.long	0x4fc
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC7
	.byte	0
	.uleb128 0x16
	.quad	.LVL26
	.long	0xb40
	.long	0x51b
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC6
	.byte	0
	.uleb128 0x16
	.quad	.LVL29
	.long	0xb10
	.long	0x540
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC8
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x7e
	.sleb128 0
	.byte	0
	.uleb128 0x16
	.quad	.LVL31
	.long	0xb10
	.long	0x55f
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC5
	.byte	0
	.uleb128 0x16
	.quad	.LVL34
	.long	0xb10
	.long	0x57e
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC5
	.byte	0
	.uleb128 0x16
	.quad	.LVL37
	.long	0xb27
	.long	0x595
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x3a
	.byte	0
	.uleb128 0x19
	.quad	.LVL43
	.long	0xb10
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC4
	.byte	0
	.byte	0
	.uleb128 0xb
	.long	0x4d
	.long	0x5c2
	.uleb128 0xe
	.long	0x86
	.value	0x270e
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x5b1
	.uleb128 0x13
	.long	.LASF842
	.byte	0x1
	.byte	0x55
	.quad	.LFB34
	.quad	.LFE34-.LFB34
	.uleb128 0x1
	.byte	0x9c
	.long	0x6a4
	.uleb128 0x14
	.long	.LASF843
	.byte	0x1
	.byte	0x55
	.long	0x62
	.long	.LLST9
	.uleb128 0x16
	.quad	.LVL47
	.long	0xb10
	.long	0x613
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC12
	.byte	0
	.uleb128 0x16
	.quad	.LVL48
	.long	0xb10
	.long	0x632
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC13
	.byte	0
	.uleb128 0x16
	.quad	.LVL49
	.long	0xb59
	.long	0x64a
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.byte	0
	.uleb128 0x16
	.quad	.LVL50
	.long	0xb40
	.long	0x669
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC9
	.byte	0
	.uleb128 0x16
	.quad	.LVL51
	.long	0xb40
	.long	0x688
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC11
	.byte	0
	.uleb128 0x19
	.quad	.LVL52
	.long	0xb40
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC10
	.byte	0
	.byte	0
	.uleb128 0x1b
	.long	0x2e6
	.quad	.LFB35
	.quad	.LFE35-.LFB35
	.uleb128 0x1
	.byte	0x9c
	.long	0x6ce
	.uleb128 0x1c
	.long	0x2f6
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1c
	.long	0x301
	.uleb128 0x1
	.byte	0x54
	.byte	0
	.uleb128 0x1d
	.long	.LASF848
	.byte	0x1
	.byte	0x74
	.long	0x62
	.quad	.LFB36
	.quad	.LFE36-.LFB36
	.uleb128 0x1
	.byte	0x9c
	.long	0x75d
	.uleb128 0x1e
	.long	.LASF837
	.byte	0x1
	.byte	0x74
	.long	0x292
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1e
	.long	.LASF838
	.byte	0x1
	.byte	0x74
	.long	0x62
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x14
	.long	.LASF844
	.byte	0x1
	.byte	0x74
	.long	0x62
	.long	.LLST10
	.uleb128 0x15
	.string	"idx"
	.byte	0x1
	.byte	0x76
	.long	0x62
	.long	.LLST11
	.uleb128 0x1f
	.long	0x770
	.long	.LLST12
	.uleb128 0x20
	.long	.LASF845
	.byte	0x1
	.byte	0x7c
	.long	0x75d
	.uleb128 0x2
	.byte	0x72
	.sleb128 0
	.uleb128 0x21
	.long	.LASF846
	.byte	0x1
	.byte	0x7f
	.long	0x62
	.long	.LLST13
	.uleb128 0x21
	.long	.LASF847
	.byte	0x1
	.byte	0x8a
	.long	0x62
	.long	.LLST14
	.byte	0
	.uleb128 0xb
	.long	0x62
	.long	0x770
	.uleb128 0x22
	.long	0x86
	.long	0x727
	.byte	0
	.uleb128 0xd
	.long	0x86
	.uleb128 0x1d
	.long	.LASF849
	.byte	0x1
	.byte	0xa8
	.long	0x62
	.quad	.LFB37
	.quad	.LFE37-.LFB37
	.uleb128 0x1
	.byte	0x9c
	.long	0xab8
	.uleb128 0x14
	.long	.LASF850
	.byte	0x1
	.byte	0xa8
	.long	0x62
	.long	.LLST15
	.uleb128 0x14
	.long	.LASF851
	.byte	0x1
	.byte	0xa8
	.long	0xab8
	.long	.LLST16
	.uleb128 0x1f
	.long	0xad1
	.long	.LLST17
	.uleb128 0x21
	.long	.LASF852
	.byte	0x1
	.byte	0xa9
	.long	0xabe
	.long	.LLST18
	.uleb128 0x21
	.long	.LASF853
	.byte	0x1
	.byte	0xab
	.long	0x8f
	.long	.LLST19
	.uleb128 0x21
	.long	.LASF854
	.byte	0x1
	.byte	0xad
	.long	0x8f
	.long	.LLST20
	.uleb128 0x15
	.string	"CD"
	.byte	0x1
	.byte	0xae
	.long	0x62
	.long	.LLST21
	.uleb128 0x15
	.string	"M"
	.byte	0x1
	.byte	0xae
	.long	0x62
	.long	.LLST22
	.uleb128 0x15
	.string	"V"
	.byte	0x1
	.byte	0xae
	.long	0x62
	.long	.LLST23
	.uleb128 0x15
	.string	"idx"
	.byte	0x1
	.byte	0xaf
	.long	0x62
	.long	.LLST24
	.uleb128 0x20
	.long	.LASF855
	.byte	0x1
	.byte	0xbe
	.long	0x2a6
	.uleb128 0x4
	.byte	0x91
	.sleb128 -600016
	.uleb128 0x15
	.string	"C1"
	.byte	0x1
	.byte	0xd2
	.long	0x4d
	.long	.LLST25
	.uleb128 0x15
	.string	"C2"
	.byte	0x1
	.byte	0xd2
	.long	0x4d
	.long	.LLST26
	.uleb128 0x23
	.long	.LASF856
	.byte	0x1
	.value	0x101
	.long	0x62
	.long	.LLST27
	.uleb128 0x23
	.long	.LASF857
	.byte	0x1
	.value	0x102
	.long	0x62
	.long	.LLST28
	.uleb128 0x23
	.long	.LASF858
	.byte	0x1
	.value	0x103
	.long	0xad6
	.long	.LLST29
	.uleb128 0x24
	.long	0x2c8
	.quad	.LBB11
	.long	.Ldebug_ranges0+0
	.byte	0x1
	.byte	0xa9
	.long	0x8b6
	.uleb128 0x25
	.long	0x2d9
	.long	.LLST30
	.uleb128 0x19
	.quad	.LVL76
	.long	0xb6b
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x30
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x1
	.byte	0x3a
	.byte	0
	.byte	0
	.uleb128 0x24
	.long	0x2e6
	.quad	.LBB15
	.long	.Ldebug_ranges0+0x30
	.byte	0x1
	.byte	0xda
	.long	0x8e0
	.uleb128 0x25
	.long	0x301
	.long	.LLST26
	.uleb128 0x25
	.long	0x2f6
	.long	.LLST32
	.byte	0
	.uleb128 0x24
	.long	0x2c8
	.quad	.LBB19
	.long	.Ldebug_ranges0+0x60
	.byte	0x1
	.byte	0xb4
	.long	0x919
	.uleb128 0x25
	.long	0x2d9
	.long	.LLST33
	.uleb128 0x19
	.quad	.LVL118
	.long	0xb6b
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x30
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x1
	.byte	0x3a
	.byte	0
	.byte	0
	.uleb128 0x24
	.long	0x2c8
	.quad	.LBB23
	.long	.Ldebug_ranges0+0x90
	.byte	0x1
	.byte	0xba
	.long	0x952
	.uleb128 0x25
	.long	0x2d9
	.long	.LLST34
	.uleb128 0x19
	.quad	.LVL124
	.long	0xb6b
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x30
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x1
	.byte	0x3a
	.byte	0
	.byte	0
	.uleb128 0x16
	.quad	.LVL87
	.long	0x6ce
	.long	0x976
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7f
	.sleb128 0
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x73
	.sleb128 0
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x2
	.byte	0x7d
	.sleb128 0
	.byte	0
	.uleb128 0x16
	.quad	.LVL90
	.long	0x6ce
	.long	0x994
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7f
	.sleb128 0
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x7e
	.sleb128 0
	.byte	0
	.uleb128 0x16
	.quad	.LVL97
	.long	0xb10
	.long	0x9bd
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC15
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x6
	.byte	0x76
	.sleb128 -600004
	.byte	0x94
	.byte	0x4
	.byte	0
	.uleb128 0x16
	.quad	.LVL98
	.long	0x30d
	.long	0x9db
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7f
	.sleb128 0
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.byte	0
	.uleb128 0x16
	.quad	.LVL99
	.long	0x3de
	.long	0xa0c
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x2
	.byte	0x7f
	.sleb128 0
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x5
	.byte	0x76
	.sleb128 -600064
	.byte	0x6
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x2
	.byte	0x7c
	.sleb128 0
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x52
	.uleb128 0x6
	.byte	0x76
	.sleb128 -600004
	.byte	0x94
	.byte	0x4
	.byte	0
	.uleb128 0x16
	.quad	.LVL106
	.long	0xb40
	.long	0xa2b
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC16
	.byte	0
	.uleb128 0x16
	.quad	.LVL110
	.long	0xb10
	.long	0xa4a
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC17
	.byte	0
	.uleb128 0x16
	.quad	.LVL116
	.long	0xb90
	.long	0xa69
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC14
	.byte	0
	.uleb128 0x16
	.quad	.LVL120
	.long	0xb90
	.long	0xa8d
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x30
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC14
	.byte	0
	.uleb128 0x16
	.quad	.LVL131
	.long	0x5c8
	.long	0xaa4
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.byte	0
	.uleb128 0x19
	.quad	.LVL132
	.long	0x5c8
	.uleb128 0x17
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x32
	.byte	0
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x287
	.uleb128 0xb
	.long	0x62
	.long	0xad1
	.uleb128 0x22
	.long	0x86
	.long	0x7b4
	.byte	0
	.uleb128 0xd
	.long	0x86
	.uleb128 0xb
	.long	0x62
	.long	0xae5
	.uleb128 0x26
	.long	0x86
	.byte	0
	.uleb128 0x27
	.long	.LASF859
	.byte	0x6
	.byte	0xa8
	.long	0x25b
	.uleb128 0x27
	.long	.LASF860
	.byte	0x6
	.byte	0xa9
	.long	0x25b
	.uleb128 0x28
	.long	.LASF861
	.byte	0x1
	.byte	0x16
	.long	0x8f
	.uleb128 0x9
	.byte	0x3
	.quad	nomPrograma
	.uleb128 0x29
	.long	.LASF862
	.byte	0x6
	.value	0x16a
	.long	0x62
	.long	0xb27
	.uleb128 0x2a
	.long	0x287
	.uleb128 0x2b
	.byte	0
	.uleb128 0x2c
	.long	.LASF863
	.byte	0x7
	.byte	0
	.long	.LASF865
	.long	0x62
	.long	0xb40
	.uleb128 0x2a
	.long	0x62
	.byte	0
	.uleb128 0x2c
	.long	.LASF864
	.byte	0x7
	.byte	0
	.long	.LASF866
	.long	0x62
	.long	0xb59
	.uleb128 0x2a
	.long	0x287
	.byte	0
	.uleb128 0x2d
	.long	.LASF868
	.byte	0x2
	.value	0x21e
	.long	0xb6b
	.uleb128 0x2a
	.long	0x62
	.byte	0
	.uleb128 0x2e
	.long	.LASF867
	.byte	0x2
	.byte	0xb7
	.long	0x69
	.long	0xb8a
	.uleb128 0x2a
	.long	0x287
	.uleb128 0x2a
	.long	0xb8a
	.uleb128 0x2a
	.long	0x62
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x8f
	.uleb128 0x2f
	.long	.LASF869
	.byte	0x8
	.value	0x158
	.long	0x8f
	.uleb128 0x2a
	.long	0x8f
	.uleb128 0x2a
	.long	0x287
	.byte	0
	.byte	0
	.section	.debug_abbrev,"",@progbits
.Ldebug_abbrev0:
	.uleb128 0x1
	.uleb128 0x11
	.byte	0x1
	.uleb128 0x25
	.uleb128 0xe
	.uleb128 0x13
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x1b
	.uleb128 0xe
	.uleb128 0x55
	.uleb128 0x17
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x10
	.uleb128 0x17
	.uleb128 0x2119
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x2
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x3
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.byte	0
	.byte	0
	.uleb128 0x4
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0x8
	.byte	0
	.byte	0
	.uleb128 0x5
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x6
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x7
	.uleb128 0x13
	.byte	0x1
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x8
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x38
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x9
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x38
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xa
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xb
	.uleb128 0x1
	.byte	0x1
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xc
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2f
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xd
	.uleb128 0x26
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xe
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2f
	.uleb128 0x5
	.byte	0
	.byte	0
	.uleb128 0xf
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x20
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x10
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x11
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x20
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x12
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x13
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x14
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x15
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x16
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x17
	.uleb128 0x410a
	.byte	0
	.uleb128 0x2
	.uleb128 0x18
	.uleb128 0x2111
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x18
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x2115
	.uleb128 0x19
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x19
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x31
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1a
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x1b
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1c
	.uleb128 0x5
	.byte	0
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x1d
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1e
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x1f
	.uleb128 0x34
	.byte	0
	.uleb128 0x34
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x20
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x21
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x22
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2f
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x23
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x24
	.uleb128 0x1d
	.byte	0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x52
	.uleb128 0x1
	.uleb128 0x55
	.uleb128 0x17
	.uleb128 0x58
	.uleb128 0xb
	.uleb128 0x59
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x25
	.uleb128 0x5
	.byte	0
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x26
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x27
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x28
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x29
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x2a
	.uleb128 0x5
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x2b
	.uleb128 0x18
	.byte	0
	.byte	0
	.byte	0
	.uleb128 0x2c
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x2d
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x2e
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x2f
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x27
	.uleb128 0x19
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.byte	0
	.section	.debug_loc,"",@progbits
.Ldebug_loc0:
.LLST0:
	.quad	.LVL0
	.quad	.LVL1
	.value	0x1
	.byte	0x55
	.quad	.LVL1
	.quad	.LVL3
	.value	0x1
	.byte	0x56
	.quad	.LVL3
	.quad	.LFE32
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST1:
	.quad	.LVL0
	.quad	.LVL2-1
	.value	0x1
	.byte	0x54
	.quad	.LVL2-1
	.quad	.LVL8
	.value	0x1
	.byte	0x5c
	.quad	.LVL8
	.quad	.LVL9
	.value	0x3
	.byte	0x7d
	.sleb128 1
	.byte	0x9f
	.quad	.LVL9
	.quad	.LVL10
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	.LVL10
	.quad	.LFE32
	.value	0x1
	.byte	0x5c
	.quad	0
	.quad	0
.LLST2:
	.quad	.LVL0
	.quad	.LVL3
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL3
	.quad	.LVL7
	.value	0x1
	.byte	0x53
	.quad	.LVL10
	.quad	.LFE32
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST3:
	.quad	.LVL12
	.quad	.LVL13
	.value	0x1
	.byte	0x55
	.quad	.LVL13
	.quad	.LVL40
	.value	0x1
	.byte	0x56
	.quad	.LVL40
	.quad	.LVL42
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL42
	.quad	.LFE33
	.value	0x1
	.byte	0x56
	.quad	0
	.quad	0
.LLST4:
	.quad	.LVL12
	.quad	.LVL14-1
	.value	0x1
	.byte	0x54
	.quad	.LVL14-1
	.quad	.LVL27
	.value	0x1
	.byte	0x5c
	.quad	.LVL27
	.quad	.LVL42
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	.LVL42
	.quad	.LFE33
	.value	0x1
	.byte	0x5c
	.quad	0
	.quad	0
.LLST5:
	.quad	.LVL12
	.quad	.LVL14-1
	.value	0x1
	.byte	0x51
	.quad	.LVL14-1
	.quad	.LFE33
	.value	0x3
	.byte	0x91
	.sleb128 -68
	.quad	0
	.quad	0
.LLST6:
	.quad	.LVL12
	.quad	.LVL14-1
	.value	0x1
	.byte	0x52
	.quad	.LVL14-1
	.quad	.LVL39
	.value	0x1
	.byte	0x53
	.quad	.LVL39
	.quad	.LVL41
	.value	0x1
	.byte	0x5d
	.quad	.LVL41
	.quad	.LVL42
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x52
	.byte	0x9f
	.quad	.LVL42
	.quad	.LFE33
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST7:
	.quad	.LVL12
	.quad	.LVL27
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL27
	.quad	.LVL28
	.value	0x1
	.byte	0x5e
	.quad	.LVL28
	.quad	.LVL37
	.value	0x3
	.byte	0x7e
	.sleb128 -1
	.byte	0x9f
	.quad	.LVL37
	.quad	.LVL38
	.value	0x1
	.byte	0x5e
	.quad	.LVL42
	.quad	.LFE33
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST8:
	.quad	.LVL12
	.quad	.LVL15
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL15
	.quad	.LVL21
	.value	0x1
	.byte	0x5d
	.quad	.LVL21
	.quad	.LVL22
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL22
	.quad	.LVL25
	.value	0x1
	.byte	0x5d
	.quad	.LVL29
	.quad	.LVL30
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL30
	.quad	.LVL36
	.value	0x1
	.byte	0x5f
	.quad	.LVL42
	.quad	.LFE33
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST9:
	.quad	.LVL44
	.quad	.LVL45
	.value	0x1
	.byte	0x55
	.quad	.LVL45
	.quad	.LVL46
	.value	0x1
	.byte	0x53
	.quad	.LVL46
	.quad	.LVL49
	.value	0x4
	.byte	0x73
	.sleb128 0
	.byte	0x1f
	.byte	0x9f
	.quad	.LVL49
	.quad	.LFE34
	.value	0x1
	.byte	0x53
	.quad	0
	.quad	0
.LLST10:
	.quad	.LVL54
	.quad	.LVL65
	.value	0x1
	.byte	0x51
	.quad	.LVL65
	.quad	.LVL66
	.value	0x1
	.byte	0x51
	.quad	.LVL70
	.quad	.LFE36
	.value	0x1
	.byte	0x51
	.quad	0
	.quad	0
.LLST11:
	.quad	.LVL54
	.quad	.LVL58
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL58
	.quad	.LVL59
	.value	0x1
	.byte	0x50
	.quad	.LVL59
	.quad	.LVL60
	.value	0x3
	.byte	0x70
	.sleb128 1
	.byte	0x9f
	.quad	.LVL67
	.quad	.LVL68
	.value	0x1
	.byte	0x51
	.quad	.LVL68
	.quad	.LVL69
	.value	0x3
	.byte	0x71
	.sleb128 1
	.byte	0x9f
	.quad	0
	.quad	0
.LLST12:
	.quad	.LVL54
	.quad	.LVL55
	.value	0xb
	.byte	0x74
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x31
	.byte	0x1c
	.byte	0x9f
	.quad	.LVL55
	.quad	.LVL56
	.value	0x3
	.byte	0x70
	.sleb128 -1
	.byte	0x9f
	.quad	.LVL56
	.quad	.LFE36
	.value	0xb
	.byte	0x74
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x31
	.byte	0x1c
	.byte	0x9f
	.quad	0
	.quad	0
.LLST13:
	.quad	.LVL57
	.quad	.LVL64
	.value	0x1
	.byte	0x54
	.quad	.LVL64
	.quad	.LVL67
	.value	0x1
	.byte	0x50
	.quad	.LVL70
	.quad	.LFE36
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST14:
	.quad	.LVL61
	.quad	.LVL62
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL62
	.quad	.LVL63
	.value	0x1
	.byte	0x50
	.quad	.LVL63
	.quad	.LVL67
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL67
	.quad	.LVL70
	.value	0x1
	.byte	0x50
	.quad	.LVL70
	.quad	.LFE36
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST15:
	.quad	.LVL72
	.quad	.LVL74
	.value	0x1
	.byte	0x55
	.quad	.LVL74
	.quad	.LVL81
	.value	0x1
	.byte	0x5d
	.quad	.LVL81
	.quad	.LVL115
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL115
	.quad	.LVL117
	.value	0x1
	.byte	0x5d
	.quad	.LVL117
	.quad	.LVL127
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL127
	.quad	.LVL128
	.value	0x1
	.byte	0x5d
	.quad	.LVL128
	.quad	.LVL129
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	.LVL129
	.quad	.LVL131
	.value	0x1
	.byte	0x5d
	.quad	.LVL131
	.quad	.LFE37
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LLST16:
	.quad	.LVL72
	.quad	.LVL75
	.value	0x1
	.byte	0x54
	.quad	.LVL75
	.quad	.LVL82
	.value	0x1
	.byte	0x5e
	.quad	.LVL82
	.quad	.LVL115
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	.LVL115
	.quad	.LFE37
	.value	0x1
	.byte	0x5e
	.quad	0
	.quad	0
.LLST17:
	.quad	.LVL76
	.quad	.LVL77
	.value	0xb
	.byte	0x70
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x31
	.byte	0x1c
	.byte	0x9f
	.quad	.LVL77
	.quad	.LVL78
	.value	0x3
	.byte	0x73
	.sleb128 -1
	.byte	0x9f
	.quad	.LVL78
	.quad	.LVL79
	.value	0xb
	.byte	0x70
	.sleb128 0
	.byte	0x8
	.byte	0x20
	.byte	0x24
	.byte	0x8
	.byte	0x20
	.byte	0x26
	.byte	0x31
	.byte	0x1c
	.byte	0x9f
	.quad	0
	.quad	0
.LLST18:
	.quad	.LVL80
	.quad	.LVL113
	.value	0x2
	.byte	0x7f
	.sleb128 0
	.quad	.LVL115
	.quad	.LFE37
	.value	0x2
	.byte	0x7f
	.sleb128 0
	.quad	0
	.quad	0
.LLST19:
	.quad	.LVL80
	.quad	.LVL81
	.value	0x1
	.byte	0x55
	.quad	.LVL115
	.quad	.LVL116-1
	.value	0x1
	.byte	0x55
	.quad	.LVL129
	.quad	.LVL130
	.value	0x1
	.byte	0x55
	.quad	.LVL130
	.quad	.LVL131-1
	.value	0x2
	.byte	0x7e
	.sleb128 16
	.quad	0
	.quad	0
.LLST20:
	.quad	.LVL116
	.quad	.LVL118-1
	.value	0x1
	.byte	0x50
	.quad	.LVL120
	.quad	.LVL122
	.value	0x1
	.byte	0x50
	.quad	.LVL127
	.quad	.LVL129
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST21:
	.quad	.LVL80
	.quad	.LVL81
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL83
	.quad	.LVL96
	.value	0x4
	.byte	0x76
	.sleb128 -600016
	.quad	.LVL96
	.quad	.LVL97-1
	.value	0x1
	.byte	0x5b
	.quad	.LVL97-1
	.quad	.LVL114
	.value	0x4
	.byte	0x76
	.sleb128 -600004
	.quad	.LVL114
	.quad	.LVL115
	.value	0x4
	.byte	0x91
	.sleb128 -600020
	.quad	.LVL115
	.quad	.LVL125
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL125
	.quad	.LVL126
	.value	0x1
	.byte	0x50
	.quad	.LVL126
	.quad	.LVL127
	.value	0x1
	.byte	0x5b
	.quad	.LVL127
	.quad	.LFE37
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST22:
	.quad	.LVL80
	.quad	.LVL84
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL84
	.quad	.LVL85
	.value	0x1
	.byte	0x5e
	.quad	.LVL86
	.quad	.LVL92
	.value	0x1
	.byte	0x5e
	.quad	.LVL115
	.quad	.LFE37
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST23:
	.quad	.LVL80
	.quad	.LVL83
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL83
	.quad	.LVL96
	.value	0x1
	.byte	0x5d
	.quad	.LVL115
	.quad	.LFE37
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST24:
	.quad	.LVL80
	.quad	.LVL81
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL101
	.quad	.LVL102
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL115
	.quad	.LVL117
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL117
	.quad	.LVL121
	.value	0x1
	.byte	0x5d
	.quad	.LVL127
	.quad	.LVL131
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	0
	.quad	0
.LLST25:
	.quad	.LVL88
	.quad	.LVL89
	.value	0x1
	.byte	0x50
	.quad	.LVL89
	.quad	.LVL94
	.value	0x4
	.byte	0x76
	.sleb128 -600004
	.quad	0
	.quad	0
.LLST26:
	.quad	.LVL90
	.quad	.LVL91
	.value	0x3
	.byte	0x70
	.sleb128 1
	.byte	0x9f
	.quad	.LVL91
	.quad	.LVL93
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST27:
	.quad	.LVL100
	.quad	.LVL106-1
	.value	0x1
	.byte	0x51
	.quad	.LVL106
	.quad	.LVL107
	.value	0x2
	.byte	0x30
	.byte	0x9f
	.quad	.LVL107
	.quad	.LVL108
	.value	0x1
	.byte	0x53
	.quad	.LVL108
	.quad	.LVL109
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST28:
	.quad	.LVL100
	.quad	.LVL103
	.value	0x4
	.byte	0x76
	.sleb128 -600004
	.quad	.LVL103
	.quad	.LVL106-1
	.value	0x1
	.byte	0x5b
	.quad	0
	.quad	0
.LLST29:
	.quad	.LVL101
	.quad	.LVL111
	.value	0x2
	.byte	0x77
	.sleb128 0
	.quad	.LVL111
	.quad	.LVL112
	.value	0x2
	.byte	0x7d
	.sleb128 0
	.quad	0
	.quad	0
.LLST30:
	.quad	.LVL72
	.quad	.LVL73
	.value	0x2
	.byte	0x74
	.sleb128 8
	.quad	0
	.quad	0
.LLST32:
	.quad	.LVL90
	.quad	.LVL94
	.value	0x4
	.byte	0x76
	.sleb128 -600004
	.quad	0
	.quad	0
.LLST33:
	.quad	.LVL117
	.quad	.LVL118-1
	.value	0x1
	.byte	0x50
	.quad	0
	.quad	0
.LLST34:
	.quad	.LVL123
	.quad	.LVL124-1
	.value	0x2
	.byte	0x7e
	.sleb128 24
	.quad	0
	.quad	0
	.section	.debug_aranges,"",@progbits
	.long	0x3c
	.value	0x2
	.long	.Ldebug_info0
	.byte	0x8
	.byte	0
	.value	0
	.value	0
	.quad	.Ltext0
	.quad	.Letext0-.Ltext0
	.quad	.LFB37
	.quad	.LFE37-.LFB37
	.quad	0
	.quad	0
	.section	.debug_ranges,"",@progbits
.Ldebug_ranges0:
	.quad	.LBB11
	.quad	.LBE11
	.quad	.LBB14
	.quad	.LBE14
	.quad	0
	.quad	0
	.quad	.LBB15
	.quad	.LBE15
	.quad	.LBB18
	.quad	.LBE18
	.quad	0
	.quad	0
	.quad	.LBB19
	.quad	.LBE19
	.quad	.LBB22
	.quad	.LBE22
	.quad	0
	.quad	0
	.quad	.LBB23
	.quad	.LBE23
	.quad	.LBB26
	.quad	.LBE26
	.quad	0
	.quad	0
	.quad	.Ltext0
	.quad	.Letext0
	.quad	.LFB37
	.quad	.LFE37
	.quad	0
	.quad	0
	.section	.debug_macro,"",@progbits
.Ldebug_macro0:
	.value	0x4
	.byte	0x2
	.long	.Ldebug_line0
	.byte	0x7
	.long	.Ldebug_macro1
	.byte	0x3
	.uleb128 0
	.uleb128 0x1
	.file 9 "/usr/include/stdc-predef.h"
	.byte	0x3
	.uleb128 0
	.uleb128 0x9
	.byte	0x7
	.long	.Ldebug_macro2
	.byte	0x4
	.byte	0x3
	.uleb128 0x8
	.uleb128 0x6
	.byte	0x5
	.uleb128 0x1a
	.long	.LASF239
	.file 10 "/usr/include/features.h"
	.byte	0x3
	.uleb128 0x1b
	.uleb128 0xa
	.byte	0x7
	.long	.Ldebug_macro3
	.file 11 "/usr/include/sys/cdefs.h"
	.byte	0x3
	.uleb128 0x177
	.uleb128 0xb
	.byte	0x7
	.long	.Ldebug_macro4
	.file 12 "/usr/include/bits/wordsize.h"
	.byte	0x3
	.uleb128 0x181
	.uleb128 0xc
	.byte	0x7
	.long	.Ldebug_macro5
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro6
	.byte	0x4
	.byte	0x5
	.uleb128 0x187
	.long	.LASF362
	.file 13 "/usr/include/gnu/stubs.h"
	.byte	0x3
	.uleb128 0x18f
	.uleb128 0xd
	.file 14 "/usr/include/gnu/stubs-64.h"
	.byte	0x3
	.uleb128 0xa
	.uleb128 0xe
	.byte	0x7
	.long	.Ldebug_macro7
	.byte	0x4
	.byte	0x4
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro8
	.byte	0x3
	.uleb128 0x21
	.uleb128 0x3
	.byte	0x7
	.long	.Ldebug_macro9
	.byte	0x4
	.byte	0x3
	.uleb128 0x23
	.uleb128 0x4
	.byte	0x5
	.uleb128 0x18
	.long	.LASF400
	.byte	0x3
	.uleb128 0x1b
	.uleb128 0xc
	.byte	0x7
	.long	.Ldebug_macro5
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro10
	.file 15 "/usr/include/bits/typesizes.h"
	.byte	0x3
	.uleb128 0x82
	.uleb128 0xf
	.byte	0x7
	.long	.Ldebug_macro11
	.byte	0x4
	.byte	0x6
	.uleb128 0xc9
	.long	.LASF453
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro12
	.byte	0x3
	.uleb128 0x4a
	.uleb128 0x5
	.byte	0x5
	.uleb128 0x1e
	.long	.LASF461
	.file 16 "/usr/include/_G_config.h"
	.byte	0x3
	.uleb128 0x20
	.uleb128 0x10
	.byte	0x7
	.long	.Ldebug_macro13
	.byte	0x3
	.uleb128 0xf
	.uleb128 0x3
	.byte	0x7
	.long	.Ldebug_macro14
	.byte	0x4
	.byte	0x5
	.uleb128 0x10
	.long	.LASF463
	.file 17 "/usr/include/wchar.h"
	.byte	0x3
	.uleb128 0x14
	.uleb128 0x11
	.byte	0x7
	.long	.Ldebug_macro15
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro16
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro17
	.file 18 "/usr/lib/gcc/x86_64-redhat-linux/4.8.2/include/stdarg.h"
	.byte	0x3
	.uleb128 0x32
	.uleb128 0x12
	.byte	0x7
	.long	.Ldebug_macro18
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro19
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro20
	.file 19 "/usr/include/bits/stdio_lim.h"
	.byte	0x3
	.uleb128 0xa4
	.uleb128 0x13
	.byte	0x7
	.long	.Ldebug_macro21
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro22
	.file 20 "/usr/include/bits/sys_errlist.h"
	.byte	0x3
	.uleb128 0x355
	.uleb128 0x14
	.byte	0x4
	.file 21 "/usr/include/bits/stdio.h"
	.byte	0x3
	.uleb128 0x3a6
	.uleb128 0x15
	.byte	0x7
	.long	.Ldebug_macro23
	.byte	0x4
	.byte	0x4
	.byte	0x3
	.uleb128 0x9
	.uleb128 0x2
	.byte	0x7
	.long	.Ldebug_macro24
	.byte	0x3
	.uleb128 0x20
	.uleb128 0x3
	.byte	0x7
	.long	.Ldebug_macro25
	.byte	0x4
	.byte	0x5
	.uleb128 0x25
	.long	.LASF602
	.file 22 "/usr/include/bits/waitflags.h"
	.byte	0x3
	.uleb128 0x29
	.uleb128 0x16
	.byte	0x7
	.long	.Ldebug_macro26
	.byte	0x4
	.file 23 "/usr/include/bits/waitstatus.h"
	.byte	0x3
	.uleb128 0x2a
	.uleb128 0x17
	.byte	0x7
	.long	.Ldebug_macro27
	.file 24 "/usr/include/endian.h"
	.byte	0x3
	.uleb128 0x40
	.uleb128 0x18
	.byte	0x7
	.long	.Ldebug_macro28
	.file 25 "/usr/include/bits/endian.h"
	.byte	0x3
	.uleb128 0x24
	.uleb128 0x19
	.byte	0x5
	.uleb128 0x7
	.long	.LASF628
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro29
	.file 26 "/usr/include/bits/byteswap.h"
	.byte	0x3
	.uleb128 0x3c
	.uleb128 0x1a
	.byte	0x5
	.uleb128 0x18
	.long	.LASF635
	.byte	0x3
	.uleb128 0x1c
	.uleb128 0xc
	.byte	0x7
	.long	.Ldebug_macro5
	.byte	0x4
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF636
	.file 27 "/usr/include/bits/byteswap-16.h"
	.byte	0x3
	.uleb128 0x23
	.uleb128 0x1b
	.byte	0x5
	.uleb128 0x19
	.long	.LASF637
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro30
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro31
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro32
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro33
	.file 28 "/usr/include/sys/types.h"
	.byte	0x3
	.uleb128 0x13a
	.uleb128 0x1c
	.byte	0x7
	.long	.Ldebug_macro34
	.file 29 "/usr/include/time.h"
	.byte	0x3
	.uleb128 0x84
	.uleb128 0x1d
	.byte	0x7
	.long	.Ldebug_macro35
	.byte	0x4
	.byte	0x5
	.uleb128 0x91
	.long	.LASF377
	.byte	0x3
	.uleb128 0x92
	.uleb128 0x3
	.byte	0x7
	.long	.Ldebug_macro36
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro37
	.file 30 "/usr/include/sys/select.h"
	.byte	0x3
	.uleb128 0xdb
	.uleb128 0x1e
	.byte	0x5
	.uleb128 0x16
	.long	.LASF701
	.file 31 "/usr/include/bits/select.h"
	.byte	0x3
	.uleb128 0x1e
	.uleb128 0x1f
	.byte	0x3
	.uleb128 0x16
	.uleb128 0xc
	.byte	0x7
	.long	.Ldebug_macro5
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro38
	.byte	0x4
	.file 32 "/usr/include/bits/sigset.h"
	.byte	0x3
	.uleb128 0x21
	.uleb128 0x20
	.byte	0x7
	.long	.Ldebug_macro39
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro40
	.byte	0x3
	.uleb128 0x2b
	.uleb128 0x1d
	.byte	0x7
	.long	.Ldebug_macro41
	.byte	0x4
	.byte	0x5
	.uleb128 0x2c
	.long	.LASF712
	.file 33 "/usr/include/bits/time.h"
	.byte	0x3
	.uleb128 0x2d
	.uleb128 0x21
	.byte	0x7
	.long	.Ldebug_macro42
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro43
	.byte	0x4
	.file 34 "/usr/include/sys/sysmacros.h"
	.byte	0x3
	.uleb128 0xde
	.uleb128 0x22
	.byte	0x7
	.long	.Ldebug_macro44
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro45
	.file 35 "/usr/include/bits/pthreadtypes.h"
	.byte	0x3
	.uleb128 0x10e
	.uleb128 0x23
	.byte	0x5
	.uleb128 0x13
	.long	.LASF735
	.byte	0x3
	.uleb128 0x15
	.uleb128 0xc
	.byte	0x7
	.long	.Ldebug_macro5
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro46
	.byte	0x4
	.byte	0x4
	.byte	0x5
	.uleb128 0x1ce
	.long	.LASF748
	.file 36 "/usr/include/alloca.h"
	.byte	0x3
	.uleb128 0x1eb
	.uleb128 0x24
	.byte	0x7
	.long	.Ldebug_macro47
	.byte	0x3
	.uleb128 0x18
	.uleb128 0x3
	.byte	0x7
	.long	.Ldebug_macro36
	.byte	0x4
	.byte	0x7
	.long	.Ldebug_macro48
	.byte	0x4
	.byte	0x5
	.uleb128 0x2e4
	.long	.LASF752
	.file 37 "/usr/include/bits/stdlib-float.h"
	.byte	0x3
	.uleb128 0x3b7
	.uleb128 0x25
	.byte	0x4
	.byte	0x6
	.uleb128 0x3c2
	.long	.LASF753
	.byte	0x4
	.byte	0x3
	.uleb128 0xa
	.uleb128 0x8
	.byte	0x7
	.long	.Ldebug_macro49
	.byte	0x3
	.uleb128 0x20
	.uleb128 0x3
	.byte	0x7
	.long	.Ldebug_macro14
	.byte	0x4
	.file 38 "/usr/include/xlocale.h"
	.byte	0x3
	.uleb128 0x9f
	.uleb128 0x26
	.byte	0x5
	.uleb128 0x15
	.long	.LASF755
	.byte	0x4
	.file 39 "/usr/include/bits/string.h"
	.byte	0x3
	.uleb128 0x276
	.uleb128 0x27
	.byte	0x5
	.uleb128 0x18
	.long	.LASF756
	.byte	0x4
	.file 40 "/usr/include/bits/string2.h"
	.byte	0x3
	.uleb128 0x279
	.uleb128 0x28
	.byte	0x7
	.long	.Ldebug_macro50
	.byte	0x4
	.byte	0x4
	.byte	0x5
	.uleb128 0xc
	.long	.LASF782
	.byte	0x5
	.uleb128 0x11
	.long	.LASF783
	.byte	0x4
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.1.0b367abf500fc30e2b5642d5aaf15de7,comdat
.Ldebug_macro1:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x1
	.long	.LASF0
	.byte	0x5
	.uleb128 0x1
	.long	.LASF1
	.byte	0x5
	.uleb128 0x1
	.long	.LASF2
	.byte	0x5
	.uleb128 0x1
	.long	.LASF3
	.byte	0x5
	.uleb128 0x1
	.long	.LASF4
	.byte	0x5
	.uleb128 0x1
	.long	.LASF5
	.byte	0x5
	.uleb128 0x1
	.long	.LASF6
	.byte	0x5
	.uleb128 0x1
	.long	.LASF7
	.byte	0x5
	.uleb128 0x1
	.long	.LASF8
	.byte	0x5
	.uleb128 0x1
	.long	.LASF9
	.byte	0x5
	.uleb128 0x1
	.long	.LASF10
	.byte	0x5
	.uleb128 0x1
	.long	.LASF11
	.byte	0x5
	.uleb128 0x1
	.long	.LASF12
	.byte	0x5
	.uleb128 0x1
	.long	.LASF13
	.byte	0x5
	.uleb128 0x1
	.long	.LASF14
	.byte	0x5
	.uleb128 0x1
	.long	.LASF15
	.byte	0x5
	.uleb128 0x1
	.long	.LASF16
	.byte	0x5
	.uleb128 0x1
	.long	.LASF17
	.byte	0x5
	.uleb128 0x1
	.long	.LASF18
	.byte	0x5
	.uleb128 0x1
	.long	.LASF19
	.byte	0x5
	.uleb128 0x1
	.long	.LASF20
	.byte	0x5
	.uleb128 0x1
	.long	.LASF21
	.byte	0x5
	.uleb128 0x1
	.long	.LASF22
	.byte	0x5
	.uleb128 0x1
	.long	.LASF23
	.byte	0x5
	.uleb128 0x1
	.long	.LASF24
	.byte	0x5
	.uleb128 0x1
	.long	.LASF25
	.byte	0x5
	.uleb128 0x1
	.long	.LASF26
	.byte	0x5
	.uleb128 0x1
	.long	.LASF27
	.byte	0x5
	.uleb128 0x1
	.long	.LASF28
	.byte	0x5
	.uleb128 0x1
	.long	.LASF29
	.byte	0x5
	.uleb128 0x1
	.long	.LASF30
	.byte	0x5
	.uleb128 0x1
	.long	.LASF31
	.byte	0x5
	.uleb128 0x1
	.long	.LASF32
	.byte	0x5
	.uleb128 0x1
	.long	.LASF33
	.byte	0x5
	.uleb128 0x1
	.long	.LASF34
	.byte	0x5
	.uleb128 0x1
	.long	.LASF35
	.byte	0x5
	.uleb128 0x1
	.long	.LASF36
	.byte	0x5
	.uleb128 0x1
	.long	.LASF37
	.byte	0x5
	.uleb128 0x1
	.long	.LASF38
	.byte	0x5
	.uleb128 0x1
	.long	.LASF39
	.byte	0x5
	.uleb128 0x1
	.long	.LASF40
	.byte	0x5
	.uleb128 0x1
	.long	.LASF41
	.byte	0x5
	.uleb128 0x1
	.long	.LASF42
	.byte	0x5
	.uleb128 0x1
	.long	.LASF43
	.byte	0x5
	.uleb128 0x1
	.long	.LASF44
	.byte	0x5
	.uleb128 0x1
	.long	.LASF45
	.byte	0x5
	.uleb128 0x1
	.long	.LASF46
	.byte	0x5
	.uleb128 0x1
	.long	.LASF47
	.byte	0x5
	.uleb128 0x1
	.long	.LASF48
	.byte	0x5
	.uleb128 0x1
	.long	.LASF49
	.byte	0x5
	.uleb128 0x1
	.long	.LASF50
	.byte	0x5
	.uleb128 0x1
	.long	.LASF51
	.byte	0x5
	.uleb128 0x1
	.long	.LASF52
	.byte	0x5
	.uleb128 0x1
	.long	.LASF53
	.byte	0x5
	.uleb128 0x1
	.long	.LASF54
	.byte	0x5
	.uleb128 0x1
	.long	.LASF55
	.byte	0x5
	.uleb128 0x1
	.long	.LASF56
	.byte	0x5
	.uleb128 0x1
	.long	.LASF57
	.byte	0x5
	.uleb128 0x1
	.long	.LASF58
	.byte	0x5
	.uleb128 0x1
	.long	.LASF59
	.byte	0x5
	.uleb128 0x1
	.long	.LASF60
	.byte	0x5
	.uleb128 0x1
	.long	.LASF61
	.byte	0x5
	.uleb128 0x1
	.long	.LASF62
	.byte	0x5
	.uleb128 0x1
	.long	.LASF63
	.byte	0x5
	.uleb128 0x1
	.long	.LASF64
	.byte	0x5
	.uleb128 0x1
	.long	.LASF65
	.byte	0x5
	.uleb128 0x1
	.long	.LASF66
	.byte	0x5
	.uleb128 0x1
	.long	.LASF67
	.byte	0x5
	.uleb128 0x1
	.long	.LASF68
	.byte	0x5
	.uleb128 0x1
	.long	.LASF69
	.byte	0x5
	.uleb128 0x1
	.long	.LASF70
	.byte	0x5
	.uleb128 0x1
	.long	.LASF71
	.byte	0x5
	.uleb128 0x1
	.long	.LASF72
	.byte	0x5
	.uleb128 0x1
	.long	.LASF73
	.byte	0x5
	.uleb128 0x1
	.long	.LASF74
	.byte	0x5
	.uleb128 0x1
	.long	.LASF75
	.byte	0x5
	.uleb128 0x1
	.long	.LASF76
	.byte	0x5
	.uleb128 0x1
	.long	.LASF77
	.byte	0x5
	.uleb128 0x1
	.long	.LASF78
	.byte	0x5
	.uleb128 0x1
	.long	.LASF79
	.byte	0x5
	.uleb128 0x1
	.long	.LASF80
	.byte	0x5
	.uleb128 0x1
	.long	.LASF81
	.byte	0x5
	.uleb128 0x1
	.long	.LASF82
	.byte	0x5
	.uleb128 0x1
	.long	.LASF83
	.byte	0x5
	.uleb128 0x1
	.long	.LASF84
	.byte	0x5
	.uleb128 0x1
	.long	.LASF85
	.byte	0x5
	.uleb128 0x1
	.long	.LASF86
	.byte	0x5
	.uleb128 0x1
	.long	.LASF87
	.byte	0x5
	.uleb128 0x1
	.long	.LASF88
	.byte	0x5
	.uleb128 0x1
	.long	.LASF89
	.byte	0x5
	.uleb128 0x1
	.long	.LASF90
	.byte	0x5
	.uleb128 0x1
	.long	.LASF91
	.byte	0x5
	.uleb128 0x1
	.long	.LASF92
	.byte	0x5
	.uleb128 0x1
	.long	.LASF93
	.byte	0x5
	.uleb128 0x1
	.long	.LASF94
	.byte	0x5
	.uleb128 0x1
	.long	.LASF95
	.byte	0x5
	.uleb128 0x1
	.long	.LASF96
	.byte	0x5
	.uleb128 0x1
	.long	.LASF97
	.byte	0x5
	.uleb128 0x1
	.long	.LASF98
	.byte	0x5
	.uleb128 0x1
	.long	.LASF99
	.byte	0x5
	.uleb128 0x1
	.long	.LASF100
	.byte	0x5
	.uleb128 0x1
	.long	.LASF101
	.byte	0x5
	.uleb128 0x1
	.long	.LASF102
	.byte	0x5
	.uleb128 0x1
	.long	.LASF103
	.byte	0x5
	.uleb128 0x1
	.long	.LASF104
	.byte	0x5
	.uleb128 0x1
	.long	.LASF105
	.byte	0x5
	.uleb128 0x1
	.long	.LASF106
	.byte	0x5
	.uleb128 0x1
	.long	.LASF107
	.byte	0x5
	.uleb128 0x1
	.long	.LASF108
	.byte	0x5
	.uleb128 0x1
	.long	.LASF109
	.byte	0x5
	.uleb128 0x1
	.long	.LASF110
	.byte	0x5
	.uleb128 0x1
	.long	.LASF111
	.byte	0x5
	.uleb128 0x1
	.long	.LASF112
	.byte	0x5
	.uleb128 0x1
	.long	.LASF113
	.byte	0x5
	.uleb128 0x1
	.long	.LASF114
	.byte	0x5
	.uleb128 0x1
	.long	.LASF115
	.byte	0x5
	.uleb128 0x1
	.long	.LASF116
	.byte	0x5
	.uleb128 0x1
	.long	.LASF117
	.byte	0x5
	.uleb128 0x1
	.long	.LASF118
	.byte	0x5
	.uleb128 0x1
	.long	.LASF119
	.byte	0x5
	.uleb128 0x1
	.long	.LASF120
	.byte	0x5
	.uleb128 0x1
	.long	.LASF121
	.byte	0x5
	.uleb128 0x1
	.long	.LASF122
	.byte	0x5
	.uleb128 0x1
	.long	.LASF123
	.byte	0x5
	.uleb128 0x1
	.long	.LASF124
	.byte	0x5
	.uleb128 0x1
	.long	.LASF125
	.byte	0x5
	.uleb128 0x1
	.long	.LASF126
	.byte	0x5
	.uleb128 0x1
	.long	.LASF127
	.byte	0x5
	.uleb128 0x1
	.long	.LASF128
	.byte	0x5
	.uleb128 0x1
	.long	.LASF129
	.byte	0x5
	.uleb128 0x1
	.long	.LASF130
	.byte	0x5
	.uleb128 0x1
	.long	.LASF131
	.byte	0x5
	.uleb128 0x1
	.long	.LASF132
	.byte	0x5
	.uleb128 0x1
	.long	.LASF133
	.byte	0x5
	.uleb128 0x1
	.long	.LASF134
	.byte	0x5
	.uleb128 0x1
	.long	.LASF135
	.byte	0x5
	.uleb128 0x1
	.long	.LASF136
	.byte	0x5
	.uleb128 0x1
	.long	.LASF137
	.byte	0x5
	.uleb128 0x1
	.long	.LASF138
	.byte	0x5
	.uleb128 0x1
	.long	.LASF139
	.byte	0x5
	.uleb128 0x1
	.long	.LASF140
	.byte	0x5
	.uleb128 0x1
	.long	.LASF141
	.byte	0x5
	.uleb128 0x1
	.long	.LASF142
	.byte	0x5
	.uleb128 0x1
	.long	.LASF143
	.byte	0x5
	.uleb128 0x1
	.long	.LASF144
	.byte	0x5
	.uleb128 0x1
	.long	.LASF145
	.byte	0x5
	.uleb128 0x1
	.long	.LASF146
	.byte	0x5
	.uleb128 0x1
	.long	.LASF147
	.byte	0x5
	.uleb128 0x1
	.long	.LASF148
	.byte	0x5
	.uleb128 0x1
	.long	.LASF149
	.byte	0x5
	.uleb128 0x1
	.long	.LASF150
	.byte	0x5
	.uleb128 0x1
	.long	.LASF151
	.byte	0x5
	.uleb128 0x1
	.long	.LASF152
	.byte	0x5
	.uleb128 0x1
	.long	.LASF153
	.byte	0x5
	.uleb128 0x1
	.long	.LASF154
	.byte	0x5
	.uleb128 0x1
	.long	.LASF155
	.byte	0x5
	.uleb128 0x1
	.long	.LASF156
	.byte	0x5
	.uleb128 0x1
	.long	.LASF157
	.byte	0x5
	.uleb128 0x1
	.long	.LASF158
	.byte	0x5
	.uleb128 0x1
	.long	.LASF159
	.byte	0x5
	.uleb128 0x1
	.long	.LASF160
	.byte	0x5
	.uleb128 0x1
	.long	.LASF161
	.byte	0x5
	.uleb128 0x1
	.long	.LASF162
	.byte	0x5
	.uleb128 0x1
	.long	.LASF163
	.byte	0x5
	.uleb128 0x1
	.long	.LASF164
	.byte	0x5
	.uleb128 0x1
	.long	.LASF165
	.byte	0x5
	.uleb128 0x1
	.long	.LASF166
	.byte	0x5
	.uleb128 0x1
	.long	.LASF167
	.byte	0x5
	.uleb128 0x1
	.long	.LASF168
	.byte	0x5
	.uleb128 0x1
	.long	.LASF169
	.byte	0x5
	.uleb128 0x1
	.long	.LASF170
	.byte	0x5
	.uleb128 0x1
	.long	.LASF171
	.byte	0x5
	.uleb128 0x1
	.long	.LASF172
	.byte	0x5
	.uleb128 0x1
	.long	.LASF173
	.byte	0x5
	.uleb128 0x1
	.long	.LASF174
	.byte	0x5
	.uleb128 0x1
	.long	.LASF175
	.byte	0x5
	.uleb128 0x1
	.long	.LASF176
	.byte	0x5
	.uleb128 0x1
	.long	.LASF177
	.byte	0x5
	.uleb128 0x1
	.long	.LASF178
	.byte	0x5
	.uleb128 0x1
	.long	.LASF179
	.byte	0x5
	.uleb128 0x1
	.long	.LASF180
	.byte	0x5
	.uleb128 0x1
	.long	.LASF181
	.byte	0x5
	.uleb128 0x1
	.long	.LASF182
	.byte	0x5
	.uleb128 0x1
	.long	.LASF183
	.byte	0x5
	.uleb128 0x1
	.long	.LASF184
	.byte	0x5
	.uleb128 0x1
	.long	.LASF185
	.byte	0x5
	.uleb128 0x1
	.long	.LASF186
	.byte	0x5
	.uleb128 0x1
	.long	.LASF187
	.byte	0x5
	.uleb128 0x1
	.long	.LASF188
	.byte	0x5
	.uleb128 0x1
	.long	.LASF189
	.byte	0x5
	.uleb128 0x1
	.long	.LASF190
	.byte	0x5
	.uleb128 0x1
	.long	.LASF191
	.byte	0x5
	.uleb128 0x1
	.long	.LASF192
	.byte	0x5
	.uleb128 0x1
	.long	.LASF193
	.byte	0x5
	.uleb128 0x1
	.long	.LASF194
	.byte	0x5
	.uleb128 0x1
	.long	.LASF195
	.byte	0x5
	.uleb128 0x1
	.long	.LASF196
	.byte	0x5
	.uleb128 0x1
	.long	.LASF197
	.byte	0x5
	.uleb128 0x1
	.long	.LASF198
	.byte	0x5
	.uleb128 0x1
	.long	.LASF199
	.byte	0x5
	.uleb128 0x1
	.long	.LASF200
	.byte	0x5
	.uleb128 0x1
	.long	.LASF201
	.byte	0x5
	.uleb128 0x1
	.long	.LASF202
	.byte	0x5
	.uleb128 0x1
	.long	.LASF203
	.byte	0x5
	.uleb128 0x1
	.long	.LASF204
	.byte	0x5
	.uleb128 0x1
	.long	.LASF205
	.byte	0x5
	.uleb128 0x1
	.long	.LASF206
	.byte	0x5
	.uleb128 0x1
	.long	.LASF207
	.byte	0x5
	.uleb128 0x1
	.long	.LASF208
	.byte	0x5
	.uleb128 0x1
	.long	.LASF209
	.byte	0x5
	.uleb128 0x1
	.long	.LASF210
	.byte	0x5
	.uleb128 0x1
	.long	.LASF211
	.byte	0x5
	.uleb128 0x1
	.long	.LASF212
	.byte	0x5
	.uleb128 0x1
	.long	.LASF213
	.byte	0x5
	.uleb128 0x1
	.long	.LASF214
	.byte	0x5
	.uleb128 0x1
	.long	.LASF215
	.byte	0x5
	.uleb128 0x1
	.long	.LASF216
	.byte	0x5
	.uleb128 0x1
	.long	.LASF217
	.byte	0x5
	.uleb128 0x1
	.long	.LASF218
	.byte	0x5
	.uleb128 0x1
	.long	.LASF219
	.byte	0x5
	.uleb128 0x1
	.long	.LASF220
	.byte	0x5
	.uleb128 0x1
	.long	.LASF221
	.byte	0x5
	.uleb128 0x1
	.long	.LASF222
	.byte	0x5
	.uleb128 0x1
	.long	.LASF223
	.byte	0x5
	.uleb128 0x1
	.long	.LASF224
	.byte	0x5
	.uleb128 0x1
	.long	.LASF225
	.byte	0x5
	.uleb128 0x1
	.long	.LASF226
	.byte	0x5
	.uleb128 0x1
	.long	.LASF227
	.byte	0x5
	.uleb128 0x1
	.long	.LASF228
	.byte	0x5
	.uleb128 0x1
	.long	.LASF229
	.byte	0x5
	.uleb128 0x1
	.long	.LASF230
	.byte	0x5
	.uleb128 0x1
	.long	.LASF231
	.byte	0x5
	.uleb128 0x1
	.long	.LASF232
	.byte	0x5
	.uleb128 0x1
	.long	.LASF233
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdcpredef.h.19.785b9754a8399dbf7fe5c981ac822b48,comdat
.Ldebug_macro2:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x13
	.long	.LASF234
	.byte	0x5
	.uleb128 0x1e
	.long	.LASF235
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF236
	.byte	0x5
	.uleb128 0x23
	.long	.LASF237
	.byte	0x5
	.uleb128 0x26
	.long	.LASF238
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.features.h.19.9ee3c42bde04aef8d26d8625c4f42754,comdat
.Ldebug_macro3:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x13
	.long	.LASF240
	.byte	0x6
	.uleb128 0x62
	.long	.LASF241
	.byte	0x6
	.uleb128 0x63
	.long	.LASF242
	.byte	0x6
	.uleb128 0x64
	.long	.LASF243
	.byte	0x6
	.uleb128 0x65
	.long	.LASF244
	.byte	0x6
	.uleb128 0x66
	.long	.LASF245
	.byte	0x6
	.uleb128 0x67
	.long	.LASF246
	.byte	0x6
	.uleb128 0x68
	.long	.LASF247
	.byte	0x6
	.uleb128 0x69
	.long	.LASF248
	.byte	0x6
	.uleb128 0x6a
	.long	.LASF249
	.byte	0x6
	.uleb128 0x6b
	.long	.LASF250
	.byte	0x6
	.uleb128 0x6c
	.long	.LASF251
	.byte	0x6
	.uleb128 0x6d
	.long	.LASF252
	.byte	0x6
	.uleb128 0x6e
	.long	.LASF253
	.byte	0x6
	.uleb128 0x6f
	.long	.LASF254
	.byte	0x6
	.uleb128 0x70
	.long	.LASF255
	.byte	0x6
	.uleb128 0x71
	.long	.LASF256
	.byte	0x6
	.uleb128 0x72
	.long	.LASF257
	.byte	0x6
	.uleb128 0x73
	.long	.LASF258
	.byte	0x6
	.uleb128 0x74
	.long	.LASF259
	.byte	0x6
	.uleb128 0x75
	.long	.LASF260
	.byte	0x6
	.uleb128 0x76
	.long	.LASF261
	.byte	0x6
	.uleb128 0x77
	.long	.LASF262
	.byte	0x6
	.uleb128 0x78
	.long	.LASF263
	.byte	0x6
	.uleb128 0x79
	.long	.LASF264
	.byte	0x6
	.uleb128 0x7a
	.long	.LASF265
	.byte	0x6
	.uleb128 0x7b
	.long	.LASF266
	.byte	0x6
	.uleb128 0x7c
	.long	.LASF267
	.byte	0x5
	.uleb128 0x81
	.long	.LASF268
	.byte	0x5
	.uleb128 0x85
	.long	.LASF269
	.byte	0x5
	.uleb128 0x8f
	.long	.LASF270
	.byte	0x5
	.uleb128 0xbc
	.long	.LASF271
	.byte	0x5
	.uleb128 0xbd
	.long	.LASF272
	.byte	0x5
	.uleb128 0xdf
	.long	.LASF273
	.byte	0x5
	.uleb128 0xe7
	.long	.LASF274
	.byte	0x5
	.uleb128 0xe9
	.long	.LASF275
	.byte	0x5
	.uleb128 0xed
	.long	.LASF276
	.byte	0x5
	.uleb128 0xf1
	.long	.LASF277
	.byte	0x5
	.uleb128 0xf5
	.long	.LASF278
	.byte	0x5
	.uleb128 0xf9
	.long	.LASF279
	.byte	0x5
	.uleb128 0xfd
	.long	.LASF280
	.byte	0x6
	.uleb128 0xfe
	.long	.LASF243
	.byte	0x5
	.uleb128 0xff
	.long	.LASF281
	.byte	0x6
	.uleb128 0x100
	.long	.LASF242
	.byte	0x5
	.uleb128 0x101
	.long	.LASF282
	.byte	0x5
	.uleb128 0x105
	.long	.LASF283
	.byte	0x6
	.uleb128 0x106
	.long	.LASF284
	.byte	0x5
	.uleb128 0x107
	.long	.LASF285
	.byte	0x5
	.uleb128 0x131
	.long	.LASF286
	.byte	0x5
	.uleb128 0x135
	.long	.LASF287
	.byte	0x5
	.uleb128 0x139
	.long	.LASF288
	.byte	0x5
	.uleb128 0x13d
	.long	.LASF289
	.byte	0x5
	.uleb128 0x154
	.long	.LASF290
	.byte	0x6
	.uleb128 0x161
	.long	.LASF291
	.byte	0x5
	.uleb128 0x162
	.long	.LASF292
	.byte	0x5
	.uleb128 0x166
	.long	.LASF293
	.byte	0x5
	.uleb128 0x167
	.long	.LASF294
	.byte	0x5
	.uleb128 0x169
	.long	.LASF295
	.byte	0x5
	.uleb128 0x171
	.long	.LASF296
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.cdefs.h.20.b3afe02c7fce4d545688e15e2e1c6579,comdat
.Ldebug_macro4:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x14
	.long	.LASF297
	.byte	0x2
	.uleb128 0x23
	.string	"__P"
	.byte	0x6
	.uleb128 0x24
	.long	.LASF298
	.byte	0x5
	.uleb128 0x2b
	.long	.LASF299
	.byte	0x5
	.uleb128 0x2c
	.long	.LASF300
	.byte	0x5
	.uleb128 0x38
	.long	.LASF301
	.byte	0x5
	.uleb128 0x39
	.long	.LASF302
	.byte	0x5
	.uleb128 0x3a
	.long	.LASF303
	.byte	0x5
	.uleb128 0x53
	.long	.LASF304
	.byte	0x5
	.uleb128 0x54
	.long	.LASF305
	.byte	0x5
	.uleb128 0x59
	.long	.LASF306
	.byte	0x5
	.uleb128 0x5a
	.long	.LASF307
	.byte	0x5
	.uleb128 0x5d
	.long	.LASF308
	.byte	0x5
	.uleb128 0x5e
	.long	.LASF309
	.byte	0x5
	.uleb128 0x66
	.long	.LASF310
	.byte	0x5
	.uleb128 0x67
	.long	.LASF311
	.byte	0x5
	.uleb128 0x7b
	.long	.LASF312
	.byte	0x5
	.uleb128 0x7c
	.long	.LASF313
	.byte	0x5
	.uleb128 0x7d
	.long	.LASF314
	.byte	0x5
	.uleb128 0x7e
	.long	.LASF315
	.byte	0x5
	.uleb128 0x7f
	.long	.LASF316
	.byte	0x5
	.uleb128 0x80
	.long	.LASF317
	.byte	0x5
	.uleb128 0x86
	.long	.LASF318
	.byte	0x5
	.uleb128 0x87
	.long	.LASF319
	.byte	0x5
	.uleb128 0x88
	.long	.LASF320
	.byte	0x5
	.uleb128 0x8d
	.long	.LASF321
	.byte	0x5
	.uleb128 0x8e
	.long	.LASF322
	.byte	0x5
	.uleb128 0x8f
	.long	.LASF323
	.byte	0x5
	.uleb128 0x92
	.long	.LASF324
	.byte	0x5
	.uleb128 0x94
	.long	.LASF325
	.byte	0x5
	.uleb128 0x95
	.long	.LASF326
	.byte	0x5
	.uleb128 0xa0
	.long	.LASF327
	.byte	0x5
	.uleb128 0xbb
	.long	.LASF328
	.byte	0x5
	.uleb128 0xc2
	.long	.LASF329
	.byte	0x5
	.uleb128 0xc4
	.long	.LASF330
	.byte	0x5
	.uleb128 0xc7
	.long	.LASF331
	.byte	0x5
	.uleb128 0xc8
	.long	.LASF332
	.byte	0x5
	.uleb128 0xdd
	.long	.LASF333
	.byte	0x5
	.uleb128 0xe6
	.long	.LASF334
	.byte	0x5
	.uleb128 0xed
	.long	.LASF335
	.byte	0x5
	.uleb128 0xf6
	.long	.LASF336
	.byte	0x5
	.uleb128 0xf7
	.long	.LASF337
	.byte	0x5
	.uleb128 0xff
	.long	.LASF338
	.byte	0x5
	.uleb128 0x10b
	.long	.LASF339
	.byte	0x5
	.uleb128 0x115
	.long	.LASF340
	.byte	0x5
	.uleb128 0x11e
	.long	.LASF341
	.byte	0x5
	.uleb128 0x126
	.long	.LASF342
	.byte	0x5
	.uleb128 0x12f
	.long	.LASF343
	.byte	0x5
	.uleb128 0x134
	.long	.LASF344
	.byte	0x5
	.uleb128 0x13c
	.long	.LASF345
	.byte	0x5
	.uleb128 0x149
	.long	.LASF346
	.byte	0x5
	.uleb128 0x14a
	.long	.LASF347
	.byte	0x5
	.uleb128 0x158
	.long	.LASF348
	.byte	0x5
	.uleb128 0x159
	.long	.LASF349
	.byte	0x5
	.uleb128 0x16d
	.long	.LASF350
	.byte	0x5
	.uleb128 0x17c
	.long	.LASF351
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.wordsize.h.4.256e8fdbd37801980286acdbc40d0280,comdat
.Ldebug_macro5:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x4
	.long	.LASF352
	.byte	0x5
	.uleb128 0xa
	.long	.LASF353
	.byte	0x5
	.uleb128 0xc
	.long	.LASF354
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.cdefs.h.407.c122ddb2157a4c1fbd957332b50319ec,comdat
.Ldebug_macro6:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x197
	.long	.LASF355
	.byte	0x5
	.uleb128 0x198
	.long	.LASF356
	.byte	0x5
	.uleb128 0x199
	.long	.LASF357
	.byte	0x5
	.uleb128 0x19a
	.long	.LASF358
	.byte	0x5
	.uleb128 0x19b
	.long	.LASF359
	.byte	0x5
	.uleb128 0x19d
	.long	.LASF360
	.byte	0x5
	.uleb128 0x19e
	.long	.LASF361
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stubs64.h.10.6fb4b470a4f113ab27ac07383b62200b,comdat
.Ldebug_macro7:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0xa
	.long	.LASF363
	.byte	0x5
	.uleb128 0xb
	.long	.LASF364
	.byte	0x5
	.uleb128 0xc
	.long	.LASF365
	.byte	0x5
	.uleb128 0xd
	.long	.LASF366
	.byte	0x5
	.uleb128 0xe
	.long	.LASF367
	.byte	0x5
	.uleb128 0xf
	.long	.LASF368
	.byte	0x5
	.uleb128 0x10
	.long	.LASF369
	.byte	0x5
	.uleb128 0x11
	.long	.LASF370
	.byte	0x5
	.uleb128 0x12
	.long	.LASF371
	.byte	0x5
	.uleb128 0x13
	.long	.LASF372
	.byte	0x5
	.uleb128 0x14
	.long	.LASF373
	.byte	0x5
	.uleb128 0x15
	.long	.LASF374
	.byte	0x5
	.uleb128 0x16
	.long	.LASF375
	.byte	0x5
	.uleb128 0x17
	.long	.LASF376
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdio.h.31.e39a94e203ad4e1d978c0fc68ce016ee,comdat
.Ldebug_macro8:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF377
	.byte	0x5
	.uleb128 0x20
	.long	.LASF378
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stddef.h.184.159df79b4ca79c76561572a55985524c,comdat
.Ldebug_macro9:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0xb8
	.long	.LASF379
	.byte	0x5
	.uleb128 0xb9
	.long	.LASF380
	.byte	0x5
	.uleb128 0xba
	.long	.LASF381
	.byte	0x5
	.uleb128 0xbb
	.long	.LASF382
	.byte	0x5
	.uleb128 0xbc
	.long	.LASF383
	.byte	0x5
	.uleb128 0xbd
	.long	.LASF384
	.byte	0x5
	.uleb128 0xbe
	.long	.LASF385
	.byte	0x5
	.uleb128 0xbf
	.long	.LASF386
	.byte	0x5
	.uleb128 0xc0
	.long	.LASF387
	.byte	0x5
	.uleb128 0xc1
	.long	.LASF388
	.byte	0x5
	.uleb128 0xc2
	.long	.LASF389
	.byte	0x5
	.uleb128 0xc3
	.long	.LASF390
	.byte	0x5
	.uleb128 0xc4
	.long	.LASF391
	.byte	0x5
	.uleb128 0xc5
	.long	.LASF392
	.byte	0x5
	.uleb128 0xc6
	.long	.LASF393
	.byte	0x5
	.uleb128 0xc7
	.long	.LASF394
	.byte	0x5
	.uleb128 0xce
	.long	.LASF395
	.byte	0x6
	.uleb128 0xea
	.long	.LASF396
	.byte	0x6
	.uleb128 0x18d
	.long	.LASF397
	.byte	0x5
	.uleb128 0x192
	.long	.LASF398
	.byte	0x6
	.uleb128 0x198
	.long	.LASF399
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.types.h.98.2414c985b07b6bc05c8aeed70b12c683,comdat
.Ldebug_macro10:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x62
	.long	.LASF401
	.byte	0x5
	.uleb128 0x63
	.long	.LASF402
	.byte	0x5
	.uleb128 0x64
	.long	.LASF403
	.byte	0x5
	.uleb128 0x65
	.long	.LASF404
	.byte	0x5
	.uleb128 0x66
	.long	.LASF405
	.byte	0x5
	.uleb128 0x67
	.long	.LASF406
	.byte	0x5
	.uleb128 0x75
	.long	.LASF407
	.byte	0x5
	.uleb128 0x76
	.long	.LASF408
	.byte	0x5
	.uleb128 0x77
	.long	.LASF409
	.byte	0x5
	.uleb128 0x78
	.long	.LASF410
	.byte	0x5
	.uleb128 0x79
	.long	.LASF411
	.byte	0x5
	.uleb128 0x7a
	.long	.LASF412
	.byte	0x5
	.uleb128 0x7b
	.long	.LASF413
	.byte	0x5
	.uleb128 0x7c
	.long	.LASF414
	.byte	0x5
	.uleb128 0x7e
	.long	.LASF415
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.typesizes.h.24.c4a72432ea65bcf9f35838c785ffdcc8,comdat
.Ldebug_macro11:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x18
	.long	.LASF416
	.byte	0x5
	.uleb128 0x22
	.long	.LASF417
	.byte	0x5
	.uleb128 0x23
	.long	.LASF418
	.byte	0x5
	.uleb128 0x26
	.long	.LASF419
	.byte	0x5
	.uleb128 0x27
	.long	.LASF420
	.byte	0x5
	.uleb128 0x28
	.long	.LASF421
	.byte	0x5
	.uleb128 0x29
	.long	.LASF422
	.byte	0x5
	.uleb128 0x2a
	.long	.LASF423
	.byte	0x5
	.uleb128 0x2b
	.long	.LASF424
	.byte	0x5
	.uleb128 0x2d
	.long	.LASF425
	.byte	0x5
	.uleb128 0x2e
	.long	.LASF426
	.byte	0x5
	.uleb128 0x33
	.long	.LASF427
	.byte	0x5
	.uleb128 0x34
	.long	.LASF428
	.byte	0x5
	.uleb128 0x35
	.long	.LASF429
	.byte	0x5
	.uleb128 0x36
	.long	.LASF430
	.byte	0x5
	.uleb128 0x37
	.long	.LASF431
	.byte	0x5
	.uleb128 0x38
	.long	.LASF432
	.byte	0x5
	.uleb128 0x39
	.long	.LASF433
	.byte	0x5
	.uleb128 0x3a
	.long	.LASF434
	.byte	0x5
	.uleb128 0x3b
	.long	.LASF435
	.byte	0x5
	.uleb128 0x3c
	.long	.LASF436
	.byte	0x5
	.uleb128 0x3d
	.long	.LASF437
	.byte	0x5
	.uleb128 0x3e
	.long	.LASF438
	.byte	0x5
	.uleb128 0x3f
	.long	.LASF439
	.byte	0x5
	.uleb128 0x40
	.long	.LASF440
	.byte	0x5
	.uleb128 0x41
	.long	.LASF441
	.byte	0x5
	.uleb128 0x42
	.long	.LASF442
	.byte	0x5
	.uleb128 0x43
	.long	.LASF443
	.byte	0x5
	.uleb128 0x44
	.long	.LASF444
	.byte	0x5
	.uleb128 0x45
	.long	.LASF445
	.byte	0x5
	.uleb128 0x46
	.long	.LASF446
	.byte	0x5
	.uleb128 0x47
	.long	.LASF447
	.byte	0x5
	.uleb128 0x48
	.long	.LASF448
	.byte	0x5
	.uleb128 0x49
	.long	.LASF449
	.byte	0x5
	.uleb128 0x4f
	.long	.LASF450
	.byte	0x5
	.uleb128 0x52
	.long	.LASF451
	.byte	0x5
	.uleb128 0x56
	.long	.LASF452
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdio.h.36.2dd12c1fd035242ad5cfd0152a01be5a,comdat
.Ldebug_macro12:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x24
	.long	.LASF454
	.byte	0x5
	.uleb128 0x25
	.long	.LASF455
	.byte	0x5
	.uleb128 0x38
	.long	.LASF456
	.byte	0x6
	.uleb128 0x3a
	.long	.LASF457
	.byte	0x5
	.uleb128 0x42
	.long	.LASF458
	.byte	0x6
	.uleb128 0x44
	.long	.LASF459
	.byte	0x5
	.uleb128 0x48
	.long	.LASF460
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4._G_config.h.5.b0f37d9e474454cf6e459063458db32f,comdat
.Ldebug_macro13:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x5
	.long	.LASF462
	.byte	0x5
	.uleb128 0xa
	.long	.LASF377
	.byte	0x5
	.uleb128 0xe
	.long	.LASF378
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stddef.h.234.5fa44232df77937e0d1fcac1468785aa,comdat
.Ldebug_macro14:
	.value	0x4
	.byte	0
	.byte	0x6
	.uleb128 0xea
	.long	.LASF396
	.byte	0x6
	.uleb128 0x18d
	.long	.LASF397
	.byte	0x5
	.uleb128 0x192
	.long	.LASF398
	.byte	0x6
	.uleb128 0x198
	.long	.LASF399
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.wchar.h.80.628010d306c9ecbd260f9d4475ab1db1,comdat
.Ldebug_macro15:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x50
	.long	.LASF464
	.byte	0x6
	.uleb128 0x60
	.long	.LASF465
	.byte	0x6
	.uleb128 0x383
	.long	.LASF465
	.byte	0x6
	.uleb128 0x384
	.long	.LASF466
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4._G_config.h.46.5187c97b14fd664662cb32e6b94fc49e,comdat
.Ldebug_macro16:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x2e
	.long	.LASF467
	.byte	0x5
	.uleb128 0x30
	.long	.LASF468
	.byte	0x5
	.uleb128 0x31
	.long	.LASF469
	.byte	0x5
	.uleb128 0x33
	.long	.LASF470
	.byte	0x5
	.uleb128 0x36
	.long	.LASF471
	.byte	0x5
	.uleb128 0x38
	.long	.LASF472
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.libio.h.34.5329345d19de76111aed1a6ff6d11a75,comdat
.Ldebug_macro17:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x22
	.long	.LASF473
	.byte	0x5
	.uleb128 0x23
	.long	.LASF474
	.byte	0x5
	.uleb128 0x24
	.long	.LASF475
	.byte	0x5
	.uleb128 0x25
	.long	.LASF476
	.byte	0x5
	.uleb128 0x26
	.long	.LASF477
	.byte	0x5
	.uleb128 0x27
	.long	.LASF478
	.byte	0x5
	.uleb128 0x28
	.long	.LASF479
	.byte	0x5
	.uleb128 0x29
	.long	.LASF480
	.byte	0x5
	.uleb128 0x2a
	.long	.LASF481
	.byte	0x5
	.uleb128 0x2b
	.long	.LASF482
	.byte	0x5
	.uleb128 0x2c
	.long	.LASF483
	.byte	0x5
	.uleb128 0x2d
	.long	.LASF484
	.byte	0x5
	.uleb128 0x2e
	.long	.LASF485
	.byte	0x5
	.uleb128 0x31
	.long	.LASF486
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdarg.h.34.3a23a216c0c293b3d2ea2e89281481e6,comdat
.Ldebug_macro18:
	.value	0x4
	.byte	0
	.byte	0x6
	.uleb128 0x22
	.long	.LASF487
	.byte	0x5
	.uleb128 0x27
	.long	.LASF488
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.libio.h.52.b59b07fe8b0bf3842d57d5058d497d93,comdat
.Ldebug_macro19:
	.value	0x4
	.byte	0
	.byte	0x6
	.uleb128 0x34
	.long	.LASF489
	.byte	0x5
	.uleb128 0x35
	.long	.LASF490
	.byte	0x5
	.uleb128 0x3c
	.long	.LASF491
	.byte	0x5
	.uleb128 0x3f
	.long	.LASF492
	.byte	0x5
	.uleb128 0x4e
	.long	.LASF493
	.byte	0x5
	.uleb128 0x4f
	.long	.LASF494
	.byte	0x5
	.uleb128 0x50
	.long	.LASF495
	.byte	0x5
	.uleb128 0x51
	.long	.LASF496
	.byte	0x5
	.uleb128 0x52
	.long	.LASF497
	.byte	0x5
	.uleb128 0x53
	.long	.LASF498
	.byte	0x5
	.uleb128 0x54
	.long	.LASF499
	.byte	0x5
	.uleb128 0x55
	.long	.LASF500
	.byte	0x5
	.uleb128 0x5d
	.long	.LASF501
	.byte	0x5
	.uleb128 0x5e
	.long	.LASF502
	.byte	0x5
	.uleb128 0x5f
	.long	.LASF503
	.byte	0x5
	.uleb128 0x60
	.long	.LASF504
	.byte	0x5
	.uleb128 0x61
	.long	.LASF505
	.byte	0x5
	.uleb128 0x62
	.long	.LASF506
	.byte	0x5
	.uleb128 0x63
	.long	.LASF507
	.byte	0x5
	.uleb128 0x64
	.long	.LASF508
	.byte	0x5
	.uleb128 0x65
	.long	.LASF509
	.byte	0x5
	.uleb128 0x66
	.long	.LASF510
	.byte	0x5
	.uleb128 0x67
	.long	.LASF511
	.byte	0x5
	.uleb128 0x68
	.long	.LASF512
	.byte	0x5
	.uleb128 0x69
	.long	.LASF513
	.byte	0x5
	.uleb128 0x6a
	.long	.LASF514
	.byte	0x5
	.uleb128 0x6b
	.long	.LASF515
	.byte	0x5
	.uleb128 0x6c
	.long	.LASF516
	.byte	0x5
	.uleb128 0x6d
	.long	.LASF517
	.byte	0x5
	.uleb128 0x6e
	.long	.LASF518
	.byte	0x5
	.uleb128 0x6f
	.long	.LASF519
	.byte	0x5
	.uleb128 0x71
	.long	.LASF520
	.byte	0x5
	.uleb128 0x72
	.long	.LASF521
	.byte	0x5
	.uleb128 0x76
	.long	.LASF522
	.byte	0x5
	.uleb128 0x7e
	.long	.LASF523
	.byte	0x5
	.uleb128 0x7f
	.long	.LASF524
	.byte	0x5
	.uleb128 0x80
	.long	.LASF525
	.byte	0x5
	.uleb128 0x81
	.long	.LASF526
	.byte	0x5
	.uleb128 0x82
	.long	.LASF527
	.byte	0x5
	.uleb128 0x83
	.long	.LASF528
	.byte	0x5
	.uleb128 0x84
	.long	.LASF529
	.byte	0x5
	.uleb128 0x85
	.long	.LASF530
	.byte	0x5
	.uleb128 0x86
	.long	.LASF531
	.byte	0x5
	.uleb128 0x87
	.long	.LASF532
	.byte	0x5
	.uleb128 0x88
	.long	.LASF533
	.byte	0x5
	.uleb128 0x89
	.long	.LASF534
	.byte	0x5
	.uleb128 0x8a
	.long	.LASF535
	.byte	0x5
	.uleb128 0x8b
	.long	.LASF536
	.byte	0x5
	.uleb128 0x8c
	.long	.LASF537
	.byte	0x5
	.uleb128 0x8d
	.long	.LASF538
	.byte	0x5
	.uleb128 0x8e
	.long	.LASF539
	.byte	0x5
	.uleb128 0xf8
	.long	.LASF540
	.byte	0x5
	.uleb128 0x115
	.long	.LASF541
	.byte	0x5
	.uleb128 0x145
	.long	.LASF542
	.byte	0x5
	.uleb128 0x146
	.long	.LASF543
	.byte	0x5
	.uleb128 0x147
	.long	.LASF544
	.byte	0x5
	.uleb128 0x191
	.long	.LASF545
	.byte	0x5
	.uleb128 0x196
	.long	.LASF546
	.byte	0x5
	.uleb128 0x199
	.long	.LASF547
	.byte	0x5
	.uleb128 0x19d
	.long	.LASF548
	.byte	0x5
	.uleb128 0x1b0
	.long	.LASF549
	.byte	0x5
	.uleb128 0x1b1
	.long	.LASF550
	.byte	0x5
	.uleb128 0x1bb
	.long	.LASF551
	.byte	0x5
	.uleb128 0x1c9
	.long	.LASF552
	.byte	0x5
	.uleb128 0x1ca
	.long	.LASF553
	.byte	0x5
	.uleb128 0x1cb
	.long	.LASF554
	.byte	0x5
	.uleb128 0x1cc
	.long	.LASF555
	.byte	0x5
	.uleb128 0x1cd
	.long	.LASF556
	.byte	0x5
	.uleb128 0x1ce
	.long	.LASF557
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdio.h.80.2c0b67e94eb93fd8dd1cc22c150ce71c,comdat
.Ldebug_macro20:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x50
	.long	.LASF558
	.byte	0x5
	.uleb128 0x5e
	.long	.LASF559
	.byte	0x5
	.uleb128 0x67
	.long	.LASF560
	.byte	0x5
	.uleb128 0x78
	.long	.LASF561
	.byte	0x5
	.uleb128 0x79
	.long	.LASF562
	.byte	0x5
	.uleb128 0x7a
	.long	.LASF563
	.byte	0x5
	.uleb128 0x7f
	.long	.LASF564
	.byte	0x5
	.uleb128 0x8c
	.long	.LASF565
	.byte	0x5
	.uleb128 0x8d
	.long	.LASF566
	.byte	0x5
	.uleb128 0x8e
	.long	.LASF567
	.byte	0x5
	.uleb128 0x97
	.long	.LASF568
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdio_lim.h.23.557290a6cddeba0587f574f29e3a5fb9,comdat
.Ldebug_macro21:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x17
	.long	.LASF569
	.byte	0x5
	.uleb128 0x18
	.long	.LASF570
	.byte	0x5
	.uleb128 0x19
	.long	.LASF571
	.byte	0x5
	.uleb128 0x1c
	.long	.LASF572
	.byte	0x6
	.uleb128 0x24
	.long	.LASF573
	.byte	0x5
	.uleb128 0x25
	.long	.LASF574
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdio.h.172.df21df34a7396d7da2e08f9b617d582f,comdat
.Ldebug_macro22:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0xac
	.long	.LASF575
	.byte	0x5
	.uleb128 0xad
	.long	.LASF576
	.byte	0x5
	.uleb128 0xae
	.long	.LASF577
	.byte	0x5
	.uleb128 0x21f
	.long	.LASF578
	.byte	0x5
	.uleb128 0x249
	.long	.LASF579
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdio.h.26.cae64f7ada9bb8327a89dba6a5e93655,comdat
.Ldebug_macro23:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x1a
	.long	.LASF580
	.byte	0x5
	.uleb128 0x90
	.long	.LASF581
	.byte	0x5
	.uleb128 0xa8
	.long	.LASF582
	.byte	0x6
	.uleb128 0xbe
	.long	.LASF583
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdlib.h.27.59e2586c75bdbcb991b248ad7257b993,comdat
.Ldebug_macro24:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x1b
	.long	.LASF377
	.byte	0x5
	.uleb128 0x1d
	.long	.LASF584
	.byte	0x5
	.uleb128 0x1e
	.long	.LASF378
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stddef.h.234.6f23eed7302c690ea7fa44a242ca26d6,comdat
.Ldebug_macro25:
	.value	0x4
	.byte	0
	.byte	0x6
	.uleb128 0xea
	.long	.LASF396
	.byte	0x5
	.uleb128 0x107
	.long	.LASF585
	.byte	0x5
	.uleb128 0x108
	.long	.LASF586
	.byte	0x5
	.uleb128 0x109
	.long	.LASF587
	.byte	0x5
	.uleb128 0x10a
	.long	.LASF588
	.byte	0x5
	.uleb128 0x10b
	.long	.LASF589
	.byte	0x5
	.uleb128 0x10c
	.long	.LASF590
	.byte	0x5
	.uleb128 0x10d
	.long	.LASF591
	.byte	0x5
	.uleb128 0x10e
	.long	.LASF592
	.byte	0x5
	.uleb128 0x10f
	.long	.LASF593
	.byte	0x5
	.uleb128 0x110
	.long	.LASF594
	.byte	0x5
	.uleb128 0x111
	.long	.LASF595
	.byte	0x5
	.uleb128 0x112
	.long	.LASF596
	.byte	0x5
	.uleb128 0x113
	.long	.LASF597
	.byte	0x5
	.uleb128 0x114
	.long	.LASF598
	.byte	0x5
	.uleb128 0x115
	.long	.LASF599
	.byte	0x6
	.uleb128 0x122
	.long	.LASF600
	.byte	0x6
	.uleb128 0x157
	.long	.LASF601
	.byte	0x6
	.uleb128 0x18d
	.long	.LASF397
	.byte	0x5
	.uleb128 0x192
	.long	.LASF398
	.byte	0x6
	.uleb128 0x198
	.long	.LASF399
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.waitflags.h.25.f56331828b5cc76f944a22c96459a9b6,comdat
.Ldebug_macro26:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x19
	.long	.LASF603
	.byte	0x5
	.uleb128 0x1a
	.long	.LASF604
	.byte	0x5
	.uleb128 0x1d
	.long	.LASF605
	.byte	0x5
	.uleb128 0x1e
	.long	.LASF606
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF607
	.byte	0x5
	.uleb128 0x20
	.long	.LASF608
	.byte	0x5
	.uleb128 0x22
	.long	.LASF609
	.byte	0x5
	.uleb128 0x24
	.long	.LASF610
	.byte	0x5
	.uleb128 0x25
	.long	.LASF611
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.waitstatus.h.28.93f167f49d64e2b9b99f98d1162a93bf,comdat
.Ldebug_macro27:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x1c
	.long	.LASF612
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF613
	.byte	0x5
	.uleb128 0x22
	.long	.LASF614
	.byte	0x5
	.uleb128 0x25
	.long	.LASF615
	.byte	0x5
	.uleb128 0x28
	.long	.LASF616
	.byte	0x5
	.uleb128 0x2c
	.long	.LASF617
	.byte	0x5
	.uleb128 0x31
	.long	.LASF618
	.byte	0x5
	.uleb128 0x35
	.long	.LASF619
	.byte	0x5
	.uleb128 0x38
	.long	.LASF620
	.byte	0x5
	.uleb128 0x39
	.long	.LASF621
	.byte	0x5
	.uleb128 0x3a
	.long	.LASF622
	.byte	0x5
	.uleb128 0x3b
	.long	.LASF623
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.endian.h.19.ff00c9c0f5e9f9a9719c5de76ace57b4,comdat
.Ldebug_macro28:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x13
	.long	.LASF624
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF625
	.byte	0x5
	.uleb128 0x20
	.long	.LASF626
	.byte	0x5
	.uleb128 0x21
	.long	.LASF627
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.endian.h.41.24cced64aef71195a51d4daa8e4f4a95,comdat
.Ldebug_macro29:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x29
	.long	.LASF629
	.byte	0x5
	.uleb128 0x2d
	.long	.LASF630
	.byte	0x5
	.uleb128 0x2e
	.long	.LASF631
	.byte	0x5
	.uleb128 0x2f
	.long	.LASF632
	.byte	0x5
	.uleb128 0x30
	.long	.LASF633
	.byte	0x5
	.uleb128 0x34
	.long	.LASF634
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.byteswap.h.38.11ee5fdc0f6cc53a16c505b9233cecef,comdat
.Ldebug_macro30:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x26
	.long	.LASF638
	.byte	0x5
	.uleb128 0x61
	.long	.LASF639
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.endian.h.63.99653ab2386785ec6f4e6e95e3b547d9,comdat
.Ldebug_macro31:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x3f
	.long	.LASF640
	.byte	0x5
	.uleb128 0x40
	.long	.LASF641
	.byte	0x5
	.uleb128 0x41
	.long	.LASF642
	.byte	0x5
	.uleb128 0x42
	.long	.LASF643
	.byte	0x5
	.uleb128 0x44
	.long	.LASF644
	.byte	0x5
	.uleb128 0x45
	.long	.LASF645
	.byte	0x5
	.uleb128 0x46
	.long	.LASF646
	.byte	0x5
	.uleb128 0x47
	.long	.LASF647
	.byte	0x5
	.uleb128 0x4a
	.long	.LASF648
	.byte	0x5
	.uleb128 0x4b
	.long	.LASF649
	.byte	0x5
	.uleb128 0x4c
	.long	.LASF650
	.byte	0x5
	.uleb128 0x4d
	.long	.LASF651
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.waitstatus.h.99.408b6270fa6eb71377201a241ef15f79,comdat
.Ldebug_macro32:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x63
	.long	.LASF652
	.byte	0x5
	.uleb128 0x64
	.long	.LASF653
	.byte	0x5
	.uleb128 0x65
	.long	.LASF654
	.byte	0x5
	.uleb128 0x66
	.long	.LASF655
	.byte	0x5
	.uleb128 0x67
	.long	.LASF656
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stdlib.h.50.84aeca2ac6f37d40e1e9b3cef757ba2d,comdat
.Ldebug_macro33:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x32
	.long	.LASF657
	.byte	0x5
	.uleb128 0x48
	.long	.LASF658
	.byte	0x5
	.uleb128 0x54
	.long	.LASF659
	.byte	0x5
	.uleb128 0x55
	.long	.LASF660
	.byte	0x5
	.uleb128 0x56
	.long	.LASF661
	.byte	0x5
	.uleb128 0x57
	.long	.LASF662
	.byte	0x5
	.uleb128 0x58
	.long	.LASF663
	.byte	0x5
	.uleb128 0x59
	.long	.LASF664
	.byte	0x5
	.uleb128 0x5b
	.long	.LASF665
	.byte	0x5
	.uleb128 0x6e
	.long	.LASF666
	.byte	0x5
	.uleb128 0x7a
	.long	.LASF667
	.byte	0x5
	.uleb128 0x80
	.long	.LASF668
	.byte	0x5
	.uleb128 0x85
	.long	.LASF669
	.byte	0x5
	.uleb128 0x86
	.long	.LASF670
	.byte	0x5
	.uleb128 0x8a
	.long	.LASF671
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.types.h.23.9ed8c6dacea1f5e7143399c60517a0cc,comdat
.Ldebug_macro34:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x17
	.long	.LASF672
	.byte	0x5
	.uleb128 0x28
	.long	.LASF673
	.byte	0x5
	.uleb128 0x34
	.long	.LASF674
	.byte	0x5
	.uleb128 0x3d
	.long	.LASF675
	.byte	0x5
	.uleb128 0x42
	.long	.LASF676
	.byte	0x5
	.uleb128 0x47
	.long	.LASF677
	.byte	0x5
	.uleb128 0x4c
	.long	.LASF678
	.byte	0x5
	.uleb128 0x51
	.long	.LASF679
	.byte	0x5
	.uleb128 0x63
	.long	.LASF680
	.byte	0x5
	.uleb128 0x69
	.long	.LASF681
	.byte	0x5
	.uleb128 0x75
	.long	.LASF682
	.byte	0x5
	.uleb128 0x7b
	.long	.LASF683
	.byte	0x5
	.uleb128 0x7f
	.long	.LASF684
	.byte	0x5
	.uleb128 0x81
	.long	.LASF685
	.byte	0x5
	.uleb128 0x82
	.long	.LASF686
	.byte	0x5
	.uleb128 0x83
	.long	.LASF687
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.time.h.53.beb46e650cd406cb917b6b96b45e640a,comdat
.Ldebug_macro35:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x35
	.long	.LASF688
	.byte	0x6
	.uleb128 0x42
	.long	.LASF689
	.byte	0x5
	.uleb128 0x45
	.long	.LASF690
	.byte	0x6
	.uleb128 0x52
	.long	.LASF691
	.byte	0x5
	.uleb128 0x56
	.long	.LASF692
	.byte	0x6
	.uleb128 0x5e
	.long	.LASF693
	.byte	0x5
	.uleb128 0x62
	.long	.LASF694
	.byte	0x6
	.uleb128 0x6a
	.long	.LASF695
	.byte	0x6
	.uleb128 0x7f
	.long	.LASF696
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.stddef.h.234.7b49b621169e2007451b8fadbcc644ca,comdat
.Ldebug_macro36:
	.value	0x4
	.byte	0
	.byte	0x6
	.uleb128 0xea
	.long	.LASF396
	.byte	0x6
	.uleb128 0x198
	.long	.LASF399
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.types.h.187.bd5a05039b505b3620e6973f1b2ffeb1,comdat
.Ldebug_macro37:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0xbb
	.long	.LASF697
	.byte	0x5
	.uleb128 0xbd
	.long	.LASF698
	.byte	0x5
	.uleb128 0xc1
	.long	.LASF699
	.byte	0x5
	.uleb128 0xd3
	.long	.LASF700
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.select.h.28.eb2f3debdbcffd1442ebddaebc4fb6ff,comdat
.Ldebug_macro38:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x1c
	.long	.LASF702
	.byte	0x5
	.uleb128 0x21
	.long	.LASF703
	.byte	0x5
	.uleb128 0x3a
	.long	.LASF704
	.byte	0x5
	.uleb128 0x3c
	.long	.LASF705
	.byte	0x5
	.uleb128 0x3e
	.long	.LASF706
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.sigset.h.21.0fa4fa7356d0a375809a5a65c08b5399,comdat
.Ldebug_macro39:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x15
	.long	.LASF707
	.byte	0x5
	.uleb128 0x1b
	.long	.LASF708
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.select.h.36.f76c3b9e55c871743863013cc4cc14c9,comdat
.Ldebug_macro40:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x24
	.long	.LASF709
	.byte	0x5
	.uleb128 0x29
	.long	.LASF685
	.byte	0x5
	.uleb128 0x2a
	.long	.LASF710
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.time.h.66.e70ce69790c975f0efb369340c432e0b,comdat
.Ldebug_macro41:
	.value	0x4
	.byte	0
	.byte	0x6
	.uleb128 0x42
	.long	.LASF689
	.byte	0x6
	.uleb128 0x52
	.long	.LASF691
	.byte	0x6
	.uleb128 0x5e
	.long	.LASF693
	.byte	0x6
	.uleb128 0x6a
	.long	.LASF695
	.byte	0x5
	.uleb128 0x72
	.long	.LASF711
	.byte	0x6
	.uleb128 0x7f
	.long	.LASF696
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.time.h.25.72fdfa5826e4e33a45f01b6b43c97e79,comdat
.Ldebug_macro42:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x19
	.long	.LASF713
	.byte	0x6
	.uleb128 0x63
	.long	.LASF714
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.select.h.49.5062e7117766526526e41607c5714bde,comdat
.Ldebug_macro43:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x31
	.long	.LASF715
	.byte	0x6
	.uleb128 0x39
	.long	.LASF716
	.byte	0x5
	.uleb128 0x3b
	.long	.LASF717
	.byte	0x5
	.uleb128 0x3c
	.long	.LASF718
	.byte	0x5
	.uleb128 0x3d
	.long	.LASF719
	.byte	0x5
	.uleb128 0x49
	.long	.LASF720
	.byte	0x5
	.uleb128 0x4e
	.long	.LASF721
	.byte	0x5
	.uleb128 0x55
	.long	.LASF722
	.byte	0x5
	.uleb128 0x5a
	.long	.LASF723
	.byte	0x5
	.uleb128 0x5b
	.long	.LASF724
	.byte	0x5
	.uleb128 0x5c
	.long	.LASF725
	.byte	0x5
	.uleb128 0x5d
	.long	.LASF726
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.sysmacros.h.21.a9f5c7b78d72ee534a0aa637d6fe1260,comdat
.Ldebug_macro44:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x15
	.long	.LASF727
	.byte	0x5
	.uleb128 0x42
	.long	.LASF728
	.byte	0x5
	.uleb128 0x43
	.long	.LASF729
	.byte	0x5
	.uleb128 0x44
	.long	.LASF730
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.types.h.229.67b3f66bd74b06b451caec392a72a945,comdat
.Ldebug_macro45:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0xe5
	.long	.LASF731
	.byte	0x5
	.uleb128 0xec
	.long	.LASF732
	.byte	0x5
	.uleb128 0xf0
	.long	.LASF733
	.byte	0x5
	.uleb128 0xf4
	.long	.LASF734
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.pthreadtypes.h.25.0d0064558db2c0ce3003a91dd4ca3c45,comdat
.Ldebug_macro46:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x19
	.long	.LASF736
	.byte	0x5
	.uleb128 0x1a
	.long	.LASF737
	.byte	0x5
	.uleb128 0x1b
	.long	.LASF738
	.byte	0x5
	.uleb128 0x1c
	.long	.LASF739
	.byte	0x5
	.uleb128 0x1d
	.long	.LASF740
	.byte	0x5
	.uleb128 0x1e
	.long	.LASF741
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF742
	.byte	0x5
	.uleb128 0x20
	.long	.LASF743
	.byte	0x5
	.uleb128 0x21
	.long	.LASF744
	.byte	0x5
	.uleb128 0x46
	.long	.LASF745
	.byte	0x5
	.uleb128 0x6a
	.long	.LASF746
	.byte	0x5
	.uleb128 0xb6
	.long	.LASF747
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.alloca.h.19.edefa922a76c1cbaaf1e416903ba2d1c,comdat
.Ldebug_macro47:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x13
	.long	.LASF749
	.byte	0x5
	.uleb128 0x17
	.long	.LASF377
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.alloca.h.29.156e12058824cc23d961c4d3b13031f6,comdat
.Ldebug_macro48:
	.value	0x4
	.byte	0
	.byte	0x6
	.uleb128 0x1d
	.long	.LASF750
	.byte	0x5
	.uleb128 0x23
	.long	.LASF751
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.string.h.23.8394011d5995a16f15d67d04e84a1d69,comdat
.Ldebug_macro49:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x17
	.long	.LASF754
	.byte	0x5
	.uleb128 0x1e
	.long	.LASF377
	.byte	0x5
	.uleb128 0x1f
	.long	.LASF378
	.byte	0
	.section	.debug_macro,"G",@progbits,wm4.string2.h.45.f93e7283b8988255f403883ee3b99b26,comdat
.Ldebug_macro50:
	.value	0x4
	.byte	0
	.byte	0x5
	.uleb128 0x2d
	.long	.LASF757
	.byte	0x5
	.uleb128 0x37
	.long	.LASF758
	.byte	0x5
	.uleb128 0x3a
	.long	.LASF759
	.byte	0x5
	.uleb128 0x5c
	.long	.LASF760
	.byte	0x5
	.uleb128 0xc2
	.long	.LASF761
	.byte	0x5
	.uleb128 0x18b
	.long	.LASF762
	.byte	0x5
	.uleb128 0x2f8
	.long	.LASF763
	.byte	0x5
	.uleb128 0x310
	.long	.LASF764
	.byte	0x5
	.uleb128 0x31f
	.long	.LASF765
	.byte	0x5
	.uleb128 0x349
	.long	.LASF766
	.byte	0x5
	.uleb128 0x364
	.long	.LASF767
	.byte	0x5
	.uleb128 0x37a
	.long	.LASF768
	.byte	0x5
	.uleb128 0x397
	.long	.LASF769
	.byte	0x5
	.uleb128 0x3a6
	.long	.LASF770
	.byte	0x5
	.uleb128 0x3f2
	.long	.LASF771
	.byte	0x5
	.uleb128 0x43e
	.long	.LASF772
	.byte	0x5
	.uleb128 0x48a
	.long	.LASF773
	.byte	0x5
	.uleb128 0x4aa
	.long	.LASF774
	.byte	0x5
	.uleb128 0x4b3
	.long	.LASF775
	.byte	0x5
	.uleb128 0x505
	.long	.LASF776
	.byte	0x5
	.uleb128 0x50f
	.long	.LASF777
	.byte	0x5
	.uleb128 0x516
	.long	.LASF778
	.byte	0x5
	.uleb128 0x522
	.long	.LASF779
	.byte	0x5
	.uleb128 0x52a
	.long	.LASF780
	.byte	0x6
	.uleb128 0x545
	.long	.LASF781
	.byte	0
	.section	.debug_line,"",@progbits
.Ldebug_line0:
	.section	.debug_str,"MS",@progbits,1
.LASF561:
	.string	"_IOFBF 0"
.LASF68:
	.string	"__GXX_ABI_VERSION 1002"
.LASF415:
	.string	"__STD_TYPE typedef"
.LASF734:
	.string	"__fsfilcnt_t_defined "
.LASF499:
	.string	"_IOS_NOREPLACE 64"
.LASF184:
	.string	"__DEC128_EPSILON__ 1E-33DL"
.LASF497:
	.string	"_IOS_TRUNC 16"
.LASF472:
	.string	"_G_BUFSIZ 8192"
.LASF48:
	.string	"__UINT32_TYPE__ unsigned int"
.LASF197:
	.string	"__GCC_ATOMIC_WCHAR_T_LOCK_FREE 2"
.LASF791:
	.string	"size_t"
.LASF674:
	.string	"__ino_t_defined "
.LASF794:
	.string	"sizetype"
.LASF73:
	.string	"__LONG_LONG_MAX__ 9223372036854775807LL"
.LASF569:
	.string	"L_tmpnam 20"
.LASF753:
	.string	"__need_malloc_and_calloc"
.LASF183:
	.string	"__DEC128_MAX__ 9.999999999999999999999999999999999E6144DL"
.LASF730:
	.string	"makedev(maj,min) gnu_dev_makedev (maj, min)"
.LASF847:
	.string	"cantidad_monedas"
.LASF16:
	.string	"__LP64__ 1"
.LASF556:
	.string	"_IO_cleanup_region_start(_fct,_fp) "
.LASF374:
	.string	"__stub_sigreturn "
.LASF255:
	.string	"__USE_XOPEN2K8XSI"
.LASF565:
	.string	"SEEK_SET 0"
.LASF697:
	.string	"__intN_t(N,MODE) typedef int int ##N ##_t __attribute__ ((__mode__ (MODE)))"
.LASF12:
	.string	"__ATOMIC_CONSUME 1"
.LASF355:
	.string	"__LDBL_REDIR1(name,proto,alias) name proto"
.LASF692:
	.string	"__clockid_t_defined 1"
.LASF327:
	.string	"__flexarr []"
.LASF157:
	.string	"__DECIMAL_DIG__ 21"
.LASF548:
	.string	"_IO_putc_unlocked(_ch,_fp) (_IO_BE ((_fp)->_IO_write_ptr >= (_fp)->_IO_write_end, 0) ? __overflow (_fp, (unsigned char) (_ch)) : (unsigned char) (*(_fp)->_IO_write_ptr++ = (_ch)))"
.LASF641:
	.string	"htole16(x) (x)"
.LASF291:
	.string	"__GNU_LIBRARY__"
.LASF142:
	.string	"__DBL_MAX_10_EXP__ 308"
.LASF709:
	.string	"__sigset_t_defined "
.LASF490:
	.string	"_IO_va_list __gnuc_va_list"
.LASF477:
	.string	"_IO_off_t __off_t"
.LASF529:
	.string	"_IO_HEX 0100"
.LASF807:
	.string	"_IO_save_end"
.LASF757:
	.string	"__STRING_INLINE __extern_inline"
.LASF857:
	.string	"aux2"
.LASF792:
	.string	"__off_t"
.LASF29:
	.string	"__ORDER_PDP_ENDIAN__ 3412"
.LASF174:
	.string	"__DEC64_MAX_EXP__ 385"
.LASF555:
	.string	"_IO_ftrylockfile(_fp) "
.LASF42:
	.string	"__INT8_TYPE__ signed char"
.LASF605:
	.string	"WSTOPPED 2"
.LASF872:
	.string	"/home/usuario/Dropbox/Facultad/2do_Curso_Grado/09_Algoritmica_ALG/Practicas/Practica4_ALG"
.LASF33:
	.string	"__SIZE_TYPE__ long unsigned int"
.LASF103:
	.string	"__UINT8_C(c) c"
.LASF43:
	.string	"__INT16_TYPE__ short int"
.LASF228:
	.string	"linux 1"
.LASF53:
	.string	"__INT_LEAST64_TYPE__ long int"
.LASF158:
	.string	"__LDBL_MAX__ 1.18973149535723176502e+4932L"
.LASF614:
	.string	"__WSTOPSIG(status) __WEXITSTATUS(status)"
.LASF319:
	.string	"__unbounded "
.LASF266:
	.string	"__FAVOR_BSD"
.LASF800:
	.string	"_IO_write_base"
.LASF337:
	.string	"__attribute_noinline__ __attribute__ ((__noinline__))"
.LASF189:
	.string	"__GCC_HAVE_SYNC_COMPARE_AND_SWAP_1 1"
.LASF227:
	.string	"__linux__ 1"
.LASF799:
	.string	"_IO_read_base"
.LASF1:
	.string	"__STDC_HOSTED__ 1"
.LASF453:
	.string	"__STD_TYPE"
.LASF715:
	.string	"__suseconds_t_defined "
.LASF131:
	.string	"__FLT_MIN__ 1.17549435082228750797e-38F"
.LASF304:
	.string	"__P(args) args"
.LASF700:
	.string	"__BIT_TYPES_DEFINED__ 1"
.LASF858:
	.string	"solucion"
.LASF380:
	.string	"__SIZE_T__ "
.LASF79:
	.string	"__SIZE_MAX__ 18446744073709551615UL"
.LASF825:
	.string	"_IO_FILE"
.LASF486:
	.string	"__need___va_list "
.LASF866:
	.string	"puts"
.LASF188:
	.string	"__GNUC_GNU_INLINE__ 1"
.LASF428:
	.string	"__OFF64_T_TYPE __SQUAD_TYPE"
.LASF682:
	.string	"__daddr_t_defined "
.LASF554:
	.string	"_IO_funlockfile(_fp) "
.LASF601:
	.string	"__need_wchar_t"
.LASF151:
	.string	"__LDBL_MANT_DIG__ 64"
.LASF755:
	.string	"_XLOCALE_H 1"
.LASF721:
	.string	"FD_SETSIZE __FD_SETSIZE"
.LASF550:
	.string	"_IO_ferror_unlocked(__fp) (((__fp)->_flags & _IO_ERR_SEEN) != 0)"
.LASF767:
	.ascii	"__strcmp_cg(s1,s2,l1) (__extension__ ({ const unsigned char "
	.ascii	"*__s2 = (const unsigned char *) (const char *) (s2); registe"
	.ascii	"r int __result = (((const unsigned char *) (const char *) (s"
	.ascii	"1))[0] - __s2[0]); if (l1 > 0 && __result == 0) { __result ="
	.ascii	" (((const unsigned char *) (co"
	.string	"nst char *) (s1))[1] - __s2[1]); if (l1 > 1 && __result == 0) { __result = (((const unsigned char *) (const char *) (s1))[2] - __s2[2]); if (l1 > 2 && __result == 0) __result = (((const unsigned char *) (const char *) (s1))[3] - __s2[3]); } } __result; }))"
.LASF805:
	.string	"_IO_save_base"
.LASF664:
	.string	"WIFSTOPPED(status) __WIFSTOPPED (__WAIT_INT (status))"
.LASF496:
	.string	"_IOS_APPEND 8"
.LASF405:
	.string	"__SLONGWORD_TYPE long int"
.LASF395:
	.string	"__size_t "
.LASF318:
	.string	"__bounded "
.LASF706:
	.string	"__FD_ISSET(d,set) ((__FDS_BITS (set)[__FD_ELT (d)] & __FD_MASK (d)) != 0)"
.LASF214:
	.string	"__ATOMIC_HLE_ACQUIRE 65536"
.LASF412:
	.string	"__ULONG32_TYPE unsigned int"
.LASF295:
	.string	"__GLIBC_PREREQ(maj,min) ((__GLIBC__ << 16) + __GLIBC_MINOR__ >= ((maj) << 16) + (min))"
.LASF625:
	.string	"__LITTLE_ENDIAN 1234"
.LASF328:
	.string	"__REDIRECT(name,proto,alias) name proto __asm__ (__ASMNAME (#alias))"
.LASF384:
	.string	"_T_SIZE "
.LASF140:
	.string	"__DBL_MIN_10_EXP__ (-307)"
.LASF28:
	.string	"__ORDER_BIG_ENDIAN__ 4321"
.LASF861:
	.string	"nomPrograma"
.LASF553:
	.string	"_IO_flockfile(_fp) "
.LASF631:
	.string	"BIG_ENDIAN __BIG_ENDIAN"
.LASF809:
	.string	"_chain"
.LASF116:
	.string	"__UINT_FAST32_MAX__ 18446744073709551615UL"
.LASF258:
	.string	"__USE_FILE_OFFSET64"
.LASF813:
	.string	"_cur_column"
.LASF245:
	.string	"__USE_POSIX"
.LASF69:
	.string	"__SCHAR_MAX__ 127"
.LASF170:
	.string	"__DEC32_EPSILON__ 1E-6DF"
.LASF397:
	.string	"NULL"
.LASF239:
	.string	"_STDIO_H 1"
.LASF841:
	.string	"TABLA"
.LASF704:
	.string	"__FD_SET(d,set) ((void) (__FDS_BITS (set)[__FD_ELT (d)] |= __FD_MASK (d)))"
.LASF517:
	.string	"_IO_IS_FILEBUF 0x2000"
.LASF87:
	.string	"__INT16_MAX__ 32767"
.LASF544:
	.string	"_IO_stderr ((_IO_FILE*)(&_IO_2_1_stderr_))"
.LASF110:
	.string	"__INT_FAST8_MAX__ 127"
.LASF366:
	.string	"__stub_fchflags "
.LASF203:
	.string	"__GCC_ATOMIC_POINTER_LOCK_FREE 2"
.LASF732:
	.string	"__blkcnt_t_defined "
.LASF833:
	.string	"atoi"
.LASF437:
	.string	"__FSFILCNT64_T_TYPE __UQUAD_TYPE"
.LASF132:
	.string	"__FLT_EPSILON__ 1.19209289550781250000e-7F"
.LASF839:
	.string	"imprimeMonedero"
.LASF270:
	.string	"__GNUC_PREREQ(maj,min) ((__GNUC__ << 16) + __GNUC_MINOR__ >= ((maj) << 16) + (min))"
.LASF589:
	.string	"_T_WCHAR "
.LASF164:
	.string	"__LDBL_HAS_QUIET_NAN__ 1"
.LASF254:
	.string	"__USE_XOPEN2K8"
.LASF382:
	.string	"_SYS_SIZE_T_H "
.LASF770:
	.ascii	"strcspn(s,reject) __extension__ ({ char __r0, __r1, __r2; (_"
	.ascii	"_builtin_constant_p (reject) && __string2_1bptr_p (reject) ?"
	.ascii	" ((__builtin_constant_p (s) && __string2_1bptr_p (s)) ? __bu"
	.ascii	"iltin_strcspn (s, reject) : ((__r0 = ((const char *) (reject"
	.ascii	"))[0], __r0 == '\\0') ? strlen (s) : ((__r1 = ((const char *"
	.ascii	") (reject))[1], __r1 == '\\0') ? __s"
	.string	"trcspn_c1 (s, __r0) : ((__r2 = ((const char *) (reject))[2], __r2 == '\\0') ? __strcspn_c2 (s, __r0, __r1) : (((const char *) (reject))[3] == '\\0' ? __strcspn_c3 (s, __r0, __r1, __r2) : __builtin_strcspn (s, reject)))))) : __builtin_strcspn (s, reject)); })"
.LASF54:
	.string	"__UINT_LEAST8_TYPE__ unsigned char"
.LASF736:
	.string	"__SIZEOF_PTHREAD_ATTR_T 56"
.LASF442:
	.string	"__SUSECONDS_T_TYPE __SYSCALL_SLONG_TYPE"
.LASF293:
	.string	"__GLIBC__ 2"
.LASF834:
	.string	"__nptr"
.LASF790:
	.string	"long int"
.LASF643:
	.string	"le16toh(x) (x)"
.LASF108:
	.string	"__UINT_LEAST64_MAX__ 18446744073709551615UL"
.LASF640:
	.string	"htobe16(x) __bswap_16 (x)"
.LASF283:
	.string	"__USE_XOPEN2K8 1"
.LASF445:
	.string	"__CLOCKID_T_TYPE __S32_TYPE"
.LASF504:
	.string	"_IO_USER_BUF 1"
.LASF180:
	.string	"__DEC128_MIN_EXP__ (-6142)"
.LASF527:
	.string	"_IO_DEC 020"
.LASF379:
	.string	"__size_t__ "
.LASF65:
	.string	"__UINT_FAST64_TYPE__ long unsigned int"
.LASF229:
	.string	"__unix 1"
.LASF50:
	.string	"__INT_LEAST8_TYPE__ signed char"
.LASF32:
	.string	"__SIZEOF_POINTER__ 8"
.LASF193:
	.string	"__GCC_ATOMIC_BOOL_LOCK_FREE 2"
.LASF452:
	.string	"__FD_SETSIZE 1024"
.LASF826:
	.string	"_IO_marker"
.LASF627:
	.string	"__PDP_ENDIAN 3412"
.LASF650:
	.string	"be64toh(x) __bswap_64 (x)"
.LASF849:
	.string	"main"
.LASF699:
	.string	"__int8_t_defined "
.LASF574:
	.string	"FOPEN_MAX 16"
.LASF542:
	.string	"_IO_stdin ((_IO_FILE*)(&_IO_2_1_stdin_))"
.LASF64:
	.string	"__UINT_FAST32_TYPE__ long unsigned int"
.LASF86:
	.string	"__INT8_MAX__ 127"
.LASF347:
	.string	"__extern_always_inline extern __always_inline"
.LASF435:
	.string	"__FSBLKCNT64_T_TYPE __UQUAD_TYPE"
.LASF391:
	.string	"_SIZE_T_DECLARED "
.LASF306:
	.string	"__CONCAT(x,y) x ## y"
.LASF617:
	.string	"__WIFSTOPPED(status) (((status) & 0xff) == 0x7f)"
.LASF222:
	.string	"__FXSR__ 1"
.LASF829:
	.string	"_pos"
.LASF274:
	.string	"_POSIX_C_SOURCE 200809L"
.LASF127:
	.string	"__FLT_MAX_EXP__ 128"
.LASF237:
	.string	"__STDC_ISO_10646__ 201103L"
.LASF18:
	.string	"__SIZEOF_LONG__ 8"
.LASF566:
	.string	"SEEK_CUR 1"
.LASF357:
	.string	"__LDBL_REDIR1_NTH(name,proto,alias) name proto __THROW"
.LASF22:
	.string	"__SIZEOF_DOUBLE__ 8"
.LASF549:
	.string	"_IO_feof_unlocked(__fp) (((__fp)->_flags & _IO_EOF_SEEN) != 0)"
.LASF234:
	.string	"_STDC_PREDEF_H 1"
.LASF758:
	.string	"__STRING2_SMALL_GET16(src,idx) (((const unsigned char *) (const char *) (src))[idx + 1] << 8 | ((const unsigned char *) (const char *) (src))[idx])"
.LASF145:
	.string	"__DBL_MIN__ ((double)2.22507385850720138309e-308L)"
.LASF176:
	.string	"__DEC64_MAX__ 9.999999999999999E384DD"
.LASF463:
	.string	"__need_mbstate_t "
.LASF670:
	.string	"EXIT_SUCCESS 0"
.LASF422:
	.string	"__INO_T_TYPE __SYSCALL_ULONG_TYPE"
.LASF126:
	.string	"__FLT_MIN_10_EXP__ (-37)"
.LASF718:
	.string	"__FD_ELT(d) ((d) / __NFDBITS)"
.LASF129:
	.string	"__FLT_DECIMAL_DIG__ 9"
.LASF582:
	.ascii	"fwrite_unlocked(ptr,size,n,stream) (__extension__ ((__builti"
	.ascii	"n_constant_p (size) && __builtin_constant_p (n) && (size_t) "
	.ascii	"(size) * (size_t) (n) <= 8 && (size_t) (size) != 0) ? ({ con"
	.ascii	"st char *__ptr = (const char *) (ptr); FILE *__stream = (str"
	.ascii	"eam); size_t __cnt; for (__cnt = (size_t) (size) * (size_t) "
	.ascii	"(n); __cnt > 0; --__cnt) if (_IO_putc_unlocked (*__ptr++, __"
	.ascii	"stream) == EOF) break; ((size_t) (size) * (size_t) (n) -"
	.string	" __cnt) / (size_t) (size); }) : (((__builtin_constant_p (size) && (size_t) (size) == 0) || (__builtin_constant_p (n) && (size_t) (n) == 0)) ? ((void) (ptr), (void) (stream), (void) (size), (void) (n), (size_t) 0) : fwrite_unlocked (ptr, size, n, stream))))"
.LASF788:
	.string	"signed char"
.LASF509:
	.string	"_IO_ERR_SEEN 0x20"
.LASF837:
	.string	"MONEDERO"
.LASF485:
	.string	"_IO_wint_t wint_t"
.LASF635:
	.string	"_BITS_BYTESWAP_H 1"
.LASF402:
	.string	"__U16_TYPE unsigned short int"
.LASF308:
	.string	"__ptr_t void *"
.LASF707:
	.string	"_SIGSET_H_types 1"
.LASF570:
	.string	"TMP_MAX 238328"
.LASF136:
	.string	"__FLT_HAS_QUIET_NAN__ 1"
.LASF417:
	.string	"__SYSCALL_SLONG_TYPE __SLONGWORD_TYPE"
.LASF372:
	.string	"__stub_revoke "
.LASF74:
	.string	"__WCHAR_MAX__ 2147483647"
.LASF433:
	.string	"__BLKCNT64_T_TYPE __SQUAD_TYPE"
.LASF489:
	.string	"_IO_va_list"
.LASF538:
	.string	"_IO_DONT_CLOSE 0100000"
.LASF673:
	.string	"__u_char_defined "
.LASF390:
	.string	"_BSD_SIZE_T_DEFINED_ "
.LASF392:
	.string	"___int_size_t_h "
.LASF528:
	.string	"_IO_OCT 040"
.LASF282:
	.string	"__USE_ISOC99 1"
.LASF128:
	.string	"__FLT_MAX_10_EXP__ 38"
.LASF305:
	.string	"__PMT(args) args"
.LASF181:
	.string	"__DEC128_MAX_EXP__ 6145"
.LASF512:
	.string	"_IO_IN_BACKUP 0x100"
.LASF37:
	.string	"__INTMAX_TYPE__ long int"
.LASF8:
	.string	"__ATOMIC_SEQ_CST 5"
.LASF466:
	.string	"__need_wint_t"
.LASF855:
	.string	"tablaDinamica"
.LASF141:
	.string	"__DBL_MAX_EXP__ 1024"
.LASF779:
	.string	"strdup(s) __strdup (s)"
.LASF648:
	.string	"htobe64(x) __bswap_64 (x)"
.LASF107:
	.string	"__UINT32_C(c) c ## U"
.LASF793:
	.string	"__off64_t"
.LASF224:
	.string	"__SSE2_MATH__ 1"
.LASF81:
	.string	"__INTMAX_C(c) c ## L"
.LASF155:
	.string	"__LDBL_MAX_EXP__ 16384"
.LASF20:
	.string	"__SIZEOF_SHORT__ 2"
.LASF725:
	.string	"FD_ISSET(fd,fdsetp) __FD_ISSET (fd, fdsetp)"
.LASF693:
	.string	"__clockid_time_t"
.LASF98:
	.string	"__INT_LEAST32_MAX__ 2147483647"
.LASF101:
	.string	"__INT64_C(c) c ## L"
.LASF350:
	.string	"__restrict_arr __restrict"
.LASF294:
	.string	"__GLIBC_MINOR__ 17"
.LASF388:
	.string	"_SIZE_T_DEFINED_ "
.LASF83:
	.string	"__UINTMAX_C(c) c ## UL"
.LASF610:
	.string	"__WALL 0x40000000"
.LASF838:
	.string	"CANTI_MONEDAS"
.LASF263:
	.string	"__USE_GNU"
.LASF205:
	.string	"__PRAGMA_REDEFINE_EXTNAME 1"
.LASF795:
	.string	"char"
.LASF190:
	.string	"__GCC_HAVE_SYNC_COMPARE_AND_SWAP_2 1"
.LASF584:
	.string	"__need_wchar_t "
.LASF61:
	.string	"__INT_FAST64_TYPE__ long int"
.LASF377:
	.string	"__need_size_t "
.LASF427:
	.string	"__OFF_T_TYPE __SYSCALL_SLONG_TYPE"
.LASF267:
	.string	"__KERNEL_STRICT_NAMES"
.LASF26:
	.string	"__BIGGEST_ALIGNMENT__ 16"
.LASF871:
	.string	"src/monedas_impares.c"
.LASF117:
	.string	"__UINT_FAST64_MAX__ 18446744073709551615UL"
.LASF323:
	.string	"__fortify_function __extern_always_inline __attribute_artificial__"
.LASF508:
	.string	"_IO_EOF_SEEN 0x10"
.LASF652:
	.string	"w_termsig __wait_terminated.__w_termsig"
.LASF873:
	.string	"_IO_lock_t"
.LASF146:
	.string	"__DBL_EPSILON__ ((double)2.22044604925031308085e-16L)"
.LASF252:
	.string	"__USE_XOPEN2K"
.LASF782:
	.string	"DEBUGMODE 1"
.LASF609:
	.string	"__WNOTHREAD 0x20000000"
.LASF370:
	.string	"__stub_lchmod "
.LASF523:
	.string	"_IO_SKIPWS 01"
.LASF835:
	.string	"VALOR_A"
.LASF854:
	.string	"charMoneda"
.LASF748:
	.string	"__malloc_and_calloc_defined "
.LASF148:
	.string	"__DBL_HAS_DENORM__ 1"
.LASF564:
	.string	"BUFSIZ _IO_BUFSIZ"
.LASF63:
	.string	"__UINT_FAST16_TYPE__ long unsigned int"
.LASF278:
	.string	"__USE_POSIX199309 1"
.LASF521:
	.string	"_IO_FLAGS2_NOTCANCEL 2"
.LASF346:
	.string	"__extern_inline extern __inline"
.LASF797:
	.string	"_IO_read_ptr"
.LASF96:
	.string	"__INT_LEAST16_MAX__ 32767"
.LASF247:
	.string	"__USE_POSIX199309"
.LASF772:
	.ascii	"strpbrk(s,accept) __extension__ ({ char __a0, __a1, __a2; (_"
	.ascii	"_builtin_constant_p (accept) && __string2_1bptr_p (accept) ?"
	.ascii	" ((__builtin_constant_p (s) && __string2_1bptr_p (s)) ? __bu"
	.ascii	"iltin_strpbrk (s, accept) : ((__a0 = ((const char *) (accept"
	.ascii	"))[0], __a0 == '\\0') ? ((void) (s), (char *) NULL) : ((__a1"
	.ascii	" = ((const char *) (accept))[1], __a1 == '\\0') ? __built"
	.string	"in_strchr (s, __a0) : ((__a2 = ((const char *) (accept))[2], __a2 == '\\0') ? __strpbrk_c2 (s, __a0, __a1) : (((const char *) (accept))[3] == '\\0' ? __strpbrk_c3 (s, __a0, __a1, __a2) : __builtin_strpbrk (s, accept)))))) : __builtin_strpbrk (s, accept)); })"
.LASF703:
	.string	"__FD_ZERO(fdsp) do { int __d0, __d1; __asm__ __volatile__ (\"cld; rep; \" __FD_ZERO_STOS : \"=c\" (__d0), \"=D\" (__d1) : \"a\" (0), \"0\" (sizeof (fd_set) / sizeof (__fd_mask)), \"1\" (&__FDS_BITS (fdsp)[0]) : \"memory\"); } while (0)"
.LASF622:
	.string	"__W_CONTINUED 0xffff"
.LASF456:
	.string	"__FILE_defined 1"
.LASF368:
	.string	"__stub_getmsg "
.LASF859:
	.string	"stdin"
.LASF251:
	.string	"__USE_UNIX98"
.LASF192:
	.string	"__GCC_HAVE_SYNC_COMPARE_AND_SWAP_8 1"
.LASF58:
	.string	"__INT_FAST8_TYPE__ signed char"
.LASF268:
	.string	"__KERNEL_STRICT_NAMES "
.LASF720:
	.string	"__FDS_BITS(set) ((set)->__fds_bits)"
.LASF764:
	.string	"strncat(dest,src,n) __builtin_strncat (dest, src, n)"
.LASF421:
	.string	"__GID_T_TYPE __U32_TYPE"
.LASF519:
	.string	"_IO_USER_LOCK 0x8000"
.LASF198:
	.string	"__GCC_ATOMIC_SHORT_LOCK_FREE 2"
.LASF763:
	.string	"strncpy(dest,src,n) __builtin_strncpy (dest, src, n)"
.LASF45:
	.string	"__INT64_TYPE__ long int"
.LASF406:
	.string	"__ULONGWORD_TYPE unsigned long int"
.LASF710:
	.string	"__need_timespec "
.LASF202:
	.string	"__GCC_ATOMIC_TEST_AND_SET_TRUEVAL 1"
.LASF808:
	.string	"_markers"
.LASF824:
	.string	"_unused2"
.LASF232:
	.string	"__ELF__ 1"
.LASF558:
	.string	"_VA_LIST_DEFINED "
.LASF75:
	.string	"__WCHAR_MIN__ (-__WCHAR_MAX__ - 1)"
.LASF583:
	.string	"__STDIO_INLINE"
.LASF685:
	.string	"__need_time_t "
.LASF571:
	.string	"FILENAME_MAX 4096"
.LASF740:
	.string	"__SIZEOF_PTHREAD_CONDATTR_T 4"
.LASF163:
	.string	"__LDBL_HAS_INFINITY__ 1"
.LASF762:
	.string	"strchr(s,c) (__extension__ (__builtin_constant_p (c) && !__builtin_constant_p (s) && (c) == '\\0' ? (char *) __rawmemchr (s, c) : __builtin_strchr (s, c)))"
.LASF373:
	.string	"__stub_setlogin "
.LASF530:
	.string	"_IO_SHOWBASE 0200"
.LASF451:
	.string	"__INO_T_MATCHES_INO64_T 1"
.LASF688:
	.string	"__clock_t_defined 1"
.LASF470:
	.string	"_G_IO_IO_FILE_VERSION 0x20001"
.LASF488:
	.string	"__GNUC_VA_LIST "
.LASF654:
	.string	"w_retcode __wait_terminated.__w_retcode"
.LASF862:
	.string	"printf"
.LASF863:
	.string	"__builtin_putchar"
.LASF85:
	.string	"__SIG_ATOMIC_MIN__ (-__SIG_ATOMIC_MAX__ - 1)"
.LASF592:
	.string	"_BSD_WCHAR_T_ "
.LASF478:
	.string	"_IO_off64_t __off64_t"
.LASF668:
	.string	"RAND_MAX 2147483647"
.LASF460:
	.string	"_STDIO_USES_IOSTREAM "
.LASF638:
	.string	"__bswap_constant_32(x) ((((x) & 0xff000000) >> 24) | (((x) & 0x00ff0000) >> 8) | (((x) & 0x0000ff00) << 8) | (((x) & 0x000000ff) << 24))"
.LASF353:
	.string	"__WORDSIZE_TIME64_COMPAT32 1"
.LASF455:
	.string	"__need___FILE "
.LASF483:
	.string	"_IO_BUFSIZ _G_BUFSIZ"
.LASF464:
	.string	"____mbstate_t_defined 1"
.LASF329:
	.string	"__REDIRECT_NTH(name,proto,alias) name proto __asm__ (__ASMNAME (#alias)) __THROW"
.LASF410:
	.string	"__UWORD_TYPE unsigned long int"
.LASF780:
	.ascii	"__strndup(s,n) (__extension__ (__builtin_constant_p (s) && _"
	.ascii	"_string2_1bptr_p (s) ? (((const char *) (s))[0] == '\\0' ? ("
	.ascii	"char *) calloc ((size_t) 1, (size_t) 1) : ({ size_t __len = "
	.ascii	"strle"
	.string	"n (s) + 1; size_t __n = (n); char *__retval; if (__n < __len) __len = __n + 1; __retval = (char *) malloc (__len); if (__retval != NULL) { __retval[__len - 1] = '\\0'; __retval = (char *) memcpy (__retval, s, __len - 1); } __retval; })) : __strndup (s, n)))"
.LASF121:
	.string	"__DEC_EVAL_METHOD__ 2"
.LASF114:
	.string	"__UINT_FAST8_MAX__ 255"
.LASF644:
	.string	"htobe32(x) __bswap_32 (x)"
.LASF639:
	.ascii	"__bswap_constant_64(x) (__extension__ ((((x) & 0xff000000000"
	.ascii	"00000ull) >> 56) | (((x) & 0x00ff0000000"
	.string	"00000ull) >> 40) | (((x) & 0x0000ff0000000000ull) >> 24) | (((x) & 0x000000ff00000000ull) >> 8) | (((x) & 0x00000000ff000000ull) << 8) | (((x) & 0x0000000000ff0000ull) << 24) | (((x) & 0x000000000000ff00ull) << 40) | (((x) & 0x00000000000000ffull) << 56)))"
.LASF178:
	.string	"__DEC64_SUBNORMAL_MIN__ 0.000000000000001E-383DD"
.LASF398:
	.string	"NULL ((void *)0)"
.LASF817:
	.string	"_offset"
.LASF120:
	.string	"__FLT_EVAL_METHOD__ 0"
.LASF279:
	.string	"__USE_POSIX199506 1"
.LASF88:
	.string	"__INT32_MAX__ 2147483647"
.LASF454:
	.string	"__need_FILE "
.LASF284:
	.string	"_ATFILE_SOURCE"
.LASF271:
	.string	"_BSD_SOURCE 1"
.LASF411:
	.string	"__SLONG32_TYPE int"
.LASF480:
	.string	"_IO_uid_t __uid_t"
.LASF524:
	.string	"_IO_LEFT 02"
.LASF432:
	.string	"__BLKCNT_T_TYPE __SYSCALL_SLONG_TYPE"
.LASF9:
	.string	"__ATOMIC_ACQUIRE 2"
.LASF785:
	.string	"unsigned char"
.LASF507:
	.string	"_IO_NO_WRITES 8"
.LASF354:
	.string	"__SYSCALL_WORDSIZE 64"
.LASF106:
	.string	"__UINT_LEAST32_MAX__ 4294967295U"
.LASF716:
	.string	"__NFDBITS"
.LASF775:
	.ascii	"__strsep(s,reject) __extension__ ({ char __r0, __r1, __r2; ("
	.ascii	"__builtin_constant_p (reject) && __string2_1bptr_p (reject) "
	.ascii	"&& (__r0 = ((const char *) (reject))[0], ((const char *) (re"
	.ascii	"ject))[0] != '\\0') ? ((__r1 = ((const char *) (reject))[1],"
	.ascii	" ((const char *) (reject)"
	.string	")[1] == '\\0') ? __strsep_1c (s, __r0) : ((__r2 = ((const char *) (reject))[2], __r2 == '\\0') ? __strsep_2c (s, __r0, __r1) : (((const char *) (reject))[3] == '\\0' ? __strsep_3c (s, __r0, __r1, __r2) : __strsep_g (s, reject)))) : __strsep_g (s, reject)); })"
.LASF667:
	.string	"__lldiv_t_defined 1"
.LASF722:
	.string	"NFDBITS __NFDBITS"
.LASF462:
	.string	"_G_config_h 1"
.LASF525:
	.string	"_IO_RIGHT 04"
.LASF135:
	.string	"__FLT_HAS_INFINITY__ 1"
.LASF236:
	.string	"__STDC_IEC_559_COMPLEX__ 1"
.LASF495:
	.string	"_IOS_ATEND 4"
.LASF606:
	.string	"WEXITED 4"
.LASF434:
	.string	"__FSBLKCNT_T_TYPE __SYSCALL_ULONG_TYPE"
.LASF259:
	.string	"__USE_BSD"
.LASF204:
	.string	"__GCC_HAVE_DWARF2_CFI_ASM 1"
.LASF182:
	.string	"__DEC128_MIN__ 1E-6143DL"
.LASF400:
	.string	"_BITS_TYPES_H 1"
.LASF738:
	.string	"__SIZEOF_PTHREAD_MUTEXATTR_T 4"
.LASF2:
	.string	"__GNUC__ 4"
.LASF660:
	.string	"WTERMSIG(status) __WTERMSIG (__WAIT_INT (status))"
.LASF522:
	.string	"_IO_FLAGS2_USER_WBUF 8"
.LASF34:
	.string	"__PTRDIFF_TYPE__ long int"
.LASF47:
	.string	"__UINT16_TYPE__ short unsigned int"
.LASF547:
	.string	"_IO_peekc_unlocked(_fp) (_IO_BE ((_fp)->_IO_read_ptr >= (_fp)->_IO_read_end, 0) && __underflow (_fp) == EOF ? EOF : *(unsigned char *) (_fp)->_IO_read_ptr)"
.LASF281:
	.string	"__USE_ISOC95 1"
.LASF167:
	.string	"__DEC32_MAX_EXP__ 97"
.LASF383:
	.string	"_T_SIZE_ "
.LASF575:
	.string	"stdin stdin"
.LASF735:
	.string	"_BITS_PTHREADTYPES_H 1"
.LASF479:
	.string	"_IO_pid_t __pid_t"
.LASF467:
	.string	"_G_va_list __gnuc_va_list"
.LASF314:
	.string	"__USING_NAMESPACE_STD(name) "
.LASF776:
	.string	"strsep(s,reject) __strsep (s, reject)"
.LASF217:
	.string	"__k8__ 1"
.LASF15:
	.string	"_LP64 1"
.LASF41:
	.string	"__SIG_ATOMIC_TYPE__ int"
.LASF301:
	.string	"__THROW __attribute__ ((__nothrow__ __LEAF))"
.LASF378:
	.string	"__need_NULL "
.LASF240:
	.string	"_FEATURES_H 1"
.LASF661:
	.string	"WSTOPSIG(status) __WSTOPSIG (__WAIT_INT (status))"
.LASF675:
	.string	"__dev_t_defined "
.LASF686:
	.string	"__need_timer_t "
.LASF71:
	.string	"__INT_MAX__ 2147483647"
.LASF299:
	.string	"__LEAF , __leaf__"
.LASF737:
	.string	"__SIZEOF_PTHREAD_MUTEX_T 40"
.LASF336:
	.string	"__attribute_used__ __attribute__ ((__used__))"
.LASF647:
	.string	"le32toh(x) (x)"
.LASF438:
	.string	"__ID_T_TYPE __U32_TYPE"
.LASF842:
	.string	"funError"
.LASF195:
	.string	"__GCC_ATOMIC_CHAR16_T_LOCK_FREE 2"
.LASF416:
	.string	"_BITS_TYPESIZES_H 1"
.LASF10:
	.string	"__ATOMIC_RELEASE 3"
.LASF123:
	.string	"__FLT_MANT_DIG__ 24"
.LASF273:
	.string	"_POSIX_SOURCE 1"
.LASF843:
	.string	"ERROR"
.LASF684:
	.string	"__need_clock_t "
.LASF113:
	.string	"__INT_FAST64_MAX__ 9223372036854775807L"
.LASF533:
	.string	"_IO_SHOWPOS 02000"
.LASF13:
	.string	"__OPTIMIZE__ 1"
.LASF540:
	.string	"_IO_file_flags _flags"
.LASF850:
	.string	"argc"
.LASF367:
	.string	"__stub_fdetach "
.LASF275:
	.string	"__USE_POSIX_IMPLICITLY 1"
.LASF578:
	.string	"getc(_fp) _IO_getc (_fp)"
.LASF812:
	.string	"_old_offset"
.LASF851:
	.string	"argv"
.LASF220:
	.string	"__SSE__ 1"
.LASF475:
	.string	"_IO_size_t size_t"
.LASF537:
	.string	"_IO_STDIO 040000"
.LASF93:
	.string	"__UINT64_MAX__ 18446744073709551615UL"
.LASF450:
	.string	"__OFF_T_MATCHES_OFF64_T 1"
.LASF705:
	.string	"__FD_CLR(d,set) ((void) (__FDS_BITS (set)[__FD_ELT (d)] &= ~__FD_MASK (d)))"
.LASF701:
	.string	"_SYS_SELECT_H 1"
.LASF845:
	.string	"monedas_usadas"
.LASF487:
	.string	"__need___va_list"
.LASF852:
	.string	"misMonedas"
.LASF246:
	.string	"__USE_POSIX2"
.LASF134:
	.string	"__FLT_HAS_DENORM__ 1"
.LASF175:
	.string	"__DEC64_MIN__ 1E-383DD"
.LASF200:
	.string	"__GCC_ATOMIC_LONG_LOCK_FREE 2"
.LASF396:
	.string	"__need_size_t"
.LASF655:
	.string	"w_stopsig __wait_stopped.__w_stopsig"
.LASF112:
	.string	"__INT_FAST32_MAX__ 9223372036854775807L"
.LASF386:
	.string	"_SIZE_T_ "
.LASF513:
	.string	"_IO_LINE_BUF 0x200"
.LASF683:
	.string	"__key_t_defined "
.LASF830:
	.string	"long long int"
.LASF40:
	.string	"__CHAR32_TYPE__ unsigned int"
.LASF76:
	.string	"__WINT_MAX__ 4294967295U"
.LASF399:
	.string	"__need_NULL"
.LASF568:
	.string	"P_tmpdir \"/tmp\""
.LASF665:
	.string	"WIFCONTINUED(status) __WIFCONTINUED (__WAIT_INT (status))"
.LASF603:
	.string	"WNOHANG 1"
.LASF5:
	.string	"__VERSION__ \"4.8.2 20131212 (Red Hat 4.8.2-7)\""
.LASF201:
	.string	"__GCC_ATOMIC_LLONG_LOCK_FREE 2"
.LASF778:
	.ascii	"__strdup(s) (__extension__ (__builtin_constant_p (s) && __st"
	.ascii	"ring2_1bptr_p (s) ?"
	.string	" (((const char *) (s))[0] == '\\0' ? (char *) calloc ((size_t) 1, (size_t) 1) : ({ size_t __len = strlen (s) + 1; char *__retval = (char *) malloc (__len); if (__retval != NULL) __retval = (char *) memcpy (__retval, s, __len); __retval; })) : __strdup (s)))"
.LASF341:
	.string	"__nonnull(params) __attribute__ ((__nonnull__ params))"
.LASF711:
	.string	"__timespec_defined 1"
.LASF213:
	.string	"__x86_64__ 1"
.LASF590:
	.string	"__WCHAR_T "
.LASF567:
	.string	"SEEK_END 2"
.LASF713:
	.string	"_STRUCT_TIMEVAL 1"
.LASF38:
	.string	"__UINTMAX_TYPE__ long unsigned int"
.LASF802:
	.string	"_IO_write_end"
.LASF25:
	.string	"__CHAR_BIT__ 8"
.LASF339:
	.string	"__attribute_format_arg__(x) __attribute__ ((__format_arg__ (x)))"
.LASF207:
	.string	"__SIZEOF_WCHAR_T__ 4"
.LASF630:
	.string	"LITTLE_ENDIAN __LITTLE_ENDIAN"
.LASF298:
	.string	"__PMT"
.LASF122:
	.string	"__FLT_RADIX__ 2"
.LASF853:
	.string	"parametro2"
.LASF516:
	.string	"_IO_IS_APPENDING 0x1000"
.LASF656:
	.string	"w_stopval __wait_stopped.__w_stopval"
.LASF211:
	.string	"__amd64__ 1"
.LASF144:
	.string	"__DBL_MAX__ ((double)1.79769313486231570815e+308L)"
.LASF864:
	.string	"__builtin_puts"
.LASF642:
	.string	"be16toh(x) __bswap_16 (x)"
.LASF616:
	.string	"__WIFSIGNALED(status) (((signed char) (((status) & 0x7f) + 1) >> 1) > 0)"
.LASF461:
	.string	"_IO_STDIO_H "
.LASF407:
	.string	"__SQUAD_TYPE long int"
.LASF572:
	.string	"L_ctermid 9"
.LASF30:
	.string	"__BYTE_ORDER__ __ORDER_LITTLE_ENDIAN__"
.LASF99:
	.string	"__INT32_C(c) c"
.LASF498:
	.string	"_IOS_NOCREATE 32"
.LASF35:
	.string	"__WCHAR_TYPE__ int"
.LASF253:
	.string	"__USE_XOPEN2KXSI"
.LASF67:
	.string	"__UINTPTR_TYPE__ long unsigned int"
.LASF265:
	.string	"__USE_FORTIFY_LEVEL"
.LASF492:
	.string	"EOF (-1)"
.LASF187:
	.string	"__USER_LABEL_PREFIX__ "
.LASF115:
	.string	"__UINT_FAST16_MAX__ 18446744073709551615UL"
.LASF36:
	.string	"__WINT_TYPE__ unsigned int"
.LASF216:
	.string	"__k8 1"
.LASF580:
	.string	"__STDIO_INLINE __extern_inline"
.LASF712:
	.string	"__need_timeval "
.LASF443:
	.string	"__DADDR_T_TYPE __S32_TYPE"
.LASF803:
	.string	"_IO_buf_base"
.LASF169:
	.string	"__DEC32_MAX__ 9.999999E96DF"
.LASF811:
	.string	"_flags2"
.LASF501:
	.string	"_IO_MAGIC 0xFBAD0000"
.LASF619:
	.string	"__WCOREDUMP(status) ((status) & __WCOREFLAG)"
.LASF56:
	.string	"__UINT_LEAST32_TYPE__ unsigned int"
.LASF787:
	.string	"unsigned int"
.LASF745:
	.string	"__have_pthread_attr_t 1"
.LASF60:
	.string	"__INT_FAST32_TYPE__ long int"
.LASF371:
	.string	"__stub_putmsg "
.LASF125:
	.string	"__FLT_MIN_EXP__ (-125)"
.LASF441:
	.string	"__USECONDS_T_TYPE __U32_TYPE"
.LASF783:
	.string	"NELEMENTOS(x) (sizeof(x)/sizeof(x[0]))"
.LASF261:
	.string	"__USE_MISC"
.LASF414:
	.string	"__U64_TYPE unsigned long int"
.LASF256:
	.string	"__USE_LARGEFILE"
.LASF124:
	.string	"__FLT_DIG__ 6"
.LASF599:
	.string	"_WCHAR_T_DECLARED "
.LASF280:
	.string	"__USE_XOPEN2K 1"
.LASF118:
	.string	"__INTPTR_MAX__ 9223372036854775807L"
.LASF579:
	.string	"putc(_ch,_fp) _IO_putc (_ch, _fp)"
.LASF444:
	.string	"__KEY_T_TYPE __S32_TYPE"
.LASF82:
	.string	"__UINTMAX_MAX__ 18446744073709551615UL"
.LASF818:
	.string	"__pad1"
.LASF819:
	.string	"__pad2"
.LASF820:
	.string	"__pad3"
.LASF821:
	.string	"__pad4"
.LASF822:
	.string	"__pad5"
.LASF404:
	.string	"__U32_TYPE unsigned int"
.LASF836:
	.string	"VALOR_B"
.LASF828:
	.string	"_sbuf"
.LASF173:
	.string	"__DEC64_MIN_EXP__ (-382)"
.LASF545:
	.string	"_IO_BE(expr,res) __builtin_expect ((expr), res)"
.LASF750:
	.string	"alloca"
.LASF324:
	.string	"__warndecl(name,msg) extern void name (void) __attribute__((__warning__ (msg)))"
.LASF369:
	.string	"__stub_gtty "
.LASF586:
	.string	"__WCHAR_T__ "
.LASF209:
	.string	"__SIZEOF_PTRDIFF_T__ 8"
.LASF31:
	.string	"__FLOAT_WORD_ORDER__ __ORDER_LITTLE_ENDIAN__"
.LASF598:
	.string	"_GCC_WCHAR_T "
.LASF418:
	.string	"__SYSCALL_ULONG_TYPE __ULONGWORD_TYPE"
.LASF235:
	.string	"__STDC_IEC_559__ 1"
.LASF343:
	.string	"__wur "
.LASF19:
	.string	"__SIZEOF_LONG_LONG__ 8"
.LASF532:
	.string	"_IO_UPPERCASE 01000"
.LASF681:
	.string	"__id_t_defined "
.LASF714:
	.string	"__need_timeval"
.LASF856:
	.string	"aux1"
.LASF179:
	.string	"__DEC128_MANT_DIG__ 34"
.LASF796:
	.string	"_flags"
.LASF678:
	.string	"__nlink_t_defined "
.LASF577:
	.string	"stderr stderr"
.LASF292:
	.string	"__GNU_LIBRARY__ 6"
.LASF420:
	.string	"__UID_T_TYPE __U32_TYPE"
.LASF194:
	.string	"__GCC_ATOMIC_CHAR_LOCK_FREE 2"
.LASF823:
	.string	"_mode"
.LASF429:
	.string	"__PID_T_TYPE __S32_TYPE"
.LASF613:
	.string	"__WTERMSIG(status) ((status) & 0x7f)"
.LASF302:
	.string	"__THROWNL __attribute__ ((__nothrow__))"
.LASF356:
	.string	"__LDBL_REDIR(name,proto) name proto"
.LASF612:
	.string	"__WEXITSTATUS(status) (((status) & 0xff00) >> 8)"
.LASF296:
	.string	"__GLIBC_HAVE_LONG_LONG 1"
.LASF94:
	.string	"__INT_LEAST8_MAX__ 127"
.LASF109:
	.string	"__UINT64_C(c) c ## UL"
.LASF307:
	.string	"__STRING(x) #x"
.LASF587:
	.string	"_WCHAR_T "
.LASF165:
	.string	"__DEC32_MANT_DIG__ 7"
.LASF651:
	.string	"le64toh(x) (x)"
.LASF637:
	.string	"__bswap_16(x) (__extension__ ({ register unsigned short int __v, __x = (unsigned short int) (x); if (__builtin_constant_p (__x)) __v = __bswap_constant_16 (__x); else __asm__ (\"rorw $8, %w0\" : \"=r\" (__v) : \"0\" (__x) : \"cc\"); __v; }))"
.LASF312:
	.string	"__BEGIN_NAMESPACE_STD "
.LASF774:
	.string	"strtok_r(s,sep,nextp) __strtok_r (s, sep, nextp)"
.LASF359:
	.string	"__LDBL_REDIR_DECL(name) "
.LASF743:
	.string	"__SIZEOF_PTHREAD_BARRIER_T 32"
.LASF303:
	.string	"__NTH(fct) __attribute__ ((__nothrow__ __LEAF)) fct"
.LASF361:
	.string	"__REDIRECT_NTH_LDBL(name,proto,alias) __REDIRECT_NTH (name, proto, alias)"
.LASF381:
	.string	"_SIZE_T "
.LASF595:
	.string	"_WCHAR_T_H "
.LASF506:
	.string	"_IO_NO_READS 4"
.LASF658:
	.string	"__WAIT_STATUS_DEFN int *"
.LASF747:
	.string	"__PTHREAD_RWLOCK_INT_FLAGS_SHARED 1"
.LASF458:
	.string	"____FILE_defined 1"
.LASF331:
	.string	"__ASMNAME(cname) __ASMNAME2 (__USER_LABEL_PREFIX__, cname)"
.LASF649:
	.string	"htole64(x) (x)"
.LASF505:
	.string	"_IO_UNBUFFERED 2"
.LASF481:
	.string	"_IO_iconv_t _G_iconv_t"
.LASF482:
	.string	"_IO_HAVE_ST_BLKSIZE _G_HAVE_ST_BLKSIZE"
.LASF680:
	.string	"__pid_t_defined "
.LASF156:
	.string	"__LDBL_MAX_10_EXP__ 4932"
.LASF95:
	.string	"__INT8_C(c) c"
.LASF491:
	.string	"_IO_UNIFIED_JUMPTABLES 1"
.LASF139:
	.string	"__DBL_MIN_EXP__ (-1021)"
.LASF385:
	.string	"__SIZE_T "
.LASF624:
	.string	"_ENDIAN_H 1"
.LASF44:
	.string	"__INT32_TYPE__ int"
.LASF172:
	.string	"__DEC64_MANT_DIG__ 16"
.LASF196:
	.string	"__GCC_ATOMIC_CHAR32_T_LOCK_FREE 2"
.LASF448:
	.string	"__FSID_T_TYPE struct { int __val[2]; }"
.LASF243:
	.string	"__USE_ISOC95"
.LASF526:
	.string	"_IO_INTERNAL 010"
.LASF133:
	.string	"__FLT_DENORM_MIN__ 1.40129846432481707092e-45F"
.LASF242:
	.string	"__USE_ISOC99"
.LASF769:
	.string	"strncmp(s1,s2,n) (__extension__ (__builtin_constant_p (n) && ((__builtin_constant_p (s1) && strlen (s1) < ((size_t) (n))) || (__builtin_constant_p (s2) && strlen (s2) < ((size_t) (n)))) ? strcmp (s1, s2) : strncmp (s1, s2, n)))"
.LASF317:
	.string	"__USING_NAMESPACE_C99(name) "
.LASF870:
	.string	"GNU C 4.8.2 20131212 (Red Hat 4.8.2-7) -mtune=generic -march=x86-64 -g3 -O2"
.LASF250:
	.string	"__USE_XOPEN_EXTENDED"
.LASF162:
	.string	"__LDBL_HAS_DENORM__ 1"
.LASF344:
	.string	"__always_inline __inline __attribute__ ((__always_inline__))"
.LASF191:
	.string	"__GCC_HAVE_SYNC_COMPARE_AND_SWAP_4 1"
.LASF761:
	.string	"__bzero(s,n) __builtin_memset (s, '\\0', n)"
.LASF288:
	.string	"__USE_SVID 1"
.LASF310:
	.string	"__BEGIN_DECLS "
.LASF691:
	.string	"__need_time_t"
.LASF768:
	.ascii	"__strcmp_gc(s1,s2,l2) (__extension__ ({ const unsigned char "
	.ascii	"*__s1 = (const unsigned char *) (const char *) (s1); registe"
	.ascii	"r int __result = __s1[0] - ((const unsigned char *) (const c"
	.ascii	"har *) (s2))[0]; if (l2 > 0 && __result == 0) { __result = ("
	.ascii	"__s1[1] - ((const unsigned c"
	.string	"har *) (const char *) (s2))[1]); if (l2 > 1 && __result == 0) { __result = (__s1[2] - ((const unsigned char *) (const char *) (s2))[2]); if (l2 > 2 && __result == 0) __result = (__s1[3] - ((const unsigned char *) (const char *) (s2))[3]); } } __result; }))"
.LASF607:
	.string	"WCONTINUED 8"
.LASF708:
	.string	"_SIGSET_NWORDS (1024 / (8 * sizeof (unsigned long int)))"
.LASF604:
	.string	"WUNTRACED 2"
.LASF426:
	.string	"__FSWORD_T_TYPE __SYSCALL_SLONG_TYPE"
.LASF27:
	.string	"__ORDER_LITTLE_ENDIAN__ 1234"
.LASF447:
	.string	"__BLKSIZE_T_TYPE __SYSCALL_SLONG_TYPE"
.LASF831:
	.string	"long long unsigned int"
.LASF476:
	.string	"_IO_ssize_t __ssize_t"
.LASF623:
	.string	"__WCOREFLAG 0x80"
.LASF244:
	.string	"__USE_ISOCXX11"
.LASF389:
	.string	"_SIZE_T_DEFINED "
.LASF560:
	.string	"__ssize_t_defined "
.LASF727:
	.string	"_SYS_SYSMACROS_H 1"
.LASF185:
	.string	"__DEC128_SUBNORMAL_MIN__ 0.000000000000000000000000000000001E-6143DL"
.LASF611:
	.string	"__WCLONE 0x80000000"
.LASF66:
	.string	"__INTPTR_TYPE__ long int"
.LASF784:
	.string	"long unsigned int"
.LASF694:
	.string	"__timer_t_defined 1"
.LASF465:
	.string	"__need_mbstate_t"
.LASF55:
	.string	"__UINT_LEAST16_TYPE__ short unsigned int"
.LASF618:
	.string	"__WIFCONTINUED(status) ((status) == __W_CONTINUED)"
.LASF39:
	.string	"__CHAR16_TYPE__ short unsigned int"
.LASF552:
	.string	"_IO_peekc(_fp) _IO_peekc_unlocked (_fp)"
.LASF645:
	.string	"htole32(x) (x)"
.LASF104:
	.string	"__UINT_LEAST16_MAX__ 65535"
.LASF248:
	.string	"__USE_POSIX199506"
.LASF626:
	.string	"__BIG_ENDIAN 4321"
.LASF14:
	.string	"__FINITE_MATH_ONLY__ 0"
.LASF602:
	.string	"_STDLIB_H 1"
.LASF844:
	.string	"DEVOLVER"
.LASF474:
	.string	"_IO_fpos64_t _G_fpos64_t"
.LASF510:
	.string	"_IO_DELETE_DONT_CLOSE 0x40"
.LASF352:
	.string	"__WORDSIZE 64"
.LASF325:
	.string	"__warnattr(msg) __attribute__((__warning__ (msg)))"
.LASF557:
	.string	"_IO_cleanup_region_end(_Doit) "
.LASF594:
	.string	"_WCHAR_T_DEFINED "
.LASF629:
	.string	"__FLOAT_WORD_ORDER __BYTE_ORDER"
.LASF206:
	.string	"__SIZEOF_INT128__ 16"
.LASF376:
	.string	"__stub_stty "
.LASF218:
	.string	"__code_model_small__ 1"
.LASF756:
	.string	"_STRING_ARCH_unaligned 1"
.LASF171:
	.string	"__DEC32_SUBNORMAL_MIN__ 0.000001E-95DF"
.LASF225:
	.string	"__gnu_linux__ 1"
.LASF272:
	.string	"_SVID_SOURCE 1"
.LASF744:
	.string	"__SIZEOF_PTHREAD_BARRIERATTR_T 4"
.LASF413:
	.string	"__S64_TYPE long int"
.LASF536:
	.string	"_IO_UNITBUF 020000"
.LASF387:
	.string	"_BSD_SIZE_T_ "
.LASF322:
	.string	"__bos0(ptr) __builtin_object_size (ptr, 0)"
.LASF249:
	.string	"__USE_XOPEN"
.LASF591:
	.string	"_WCHAR_T_ "
.LASF520:
	.string	"_IO_FLAGS2_MMAP 1"
.LASF539:
	.string	"_IO_BOOLALPHA 0200000"
.LASF315:
	.string	"__BEGIN_NAMESPACE_C99 "
.LASF100:
	.string	"__INT_LEAST64_MAX__ 9223372036854775807L"
.LASF403:
	.string	"__S32_TYPE int"
.LASF663:
	.string	"WIFSIGNALED(status) __WIFSIGNALED (__WAIT_INT (status))"
.LASF816:
	.string	"_lock"
.LASF806:
	.string	"_IO_backup_base"
.LASF815:
	.string	"_shortbuf"
.LASF535:
	.string	"_IO_FIXED 010000"
.LASF597:
	.string	"__INT_WCHAR_T_H "
.LASF754:
	.string	"_STRING_H 1"
.LASF726:
	.string	"FD_ZERO(fdsetp) __FD_ZERO (fdsetp)"
.LASF57:
	.string	"__UINT_LEAST64_TYPE__ long unsigned int"
.LASF724:
	.string	"FD_CLR(fd,fdsetp) __FD_CLR (fd, fdsetp)"
.LASF241:
	.string	"__USE_ISOC11"
.LASF515:
	.string	"_IO_CURRENTLY_PUTTING 0x800"
.LASF702:
	.string	"__FD_ZERO_STOS \"stosq\""
.LASF563:
	.string	"_IONBF 2"
.LASF559:
	.string	"__off_t_defined "
.LASF518:
	.string	"_IO_BAD_SEEN 0x4000"
.LASF502:
	.string	"_OLD_STDIO_MAGIC 0xFABC0000"
.LASF827:
	.string	"_next"
.LASF159:
	.string	"__LDBL_MIN__ 3.36210314311209350626e-4932L"
.LASF52:
	.string	"__INT_LEAST32_TYPE__ int"
.LASF147:
	.string	"__DBL_DENORM_MIN__ ((double)4.94065645841246544177e-324L)"
.LASF154:
	.string	"__LDBL_MIN_10_EXP__ (-4931)"
.LASF3:
	.string	"__GNUC_MINOR__ 8"
.LASF199:
	.string	"__GCC_ATOMIC_INT_LOCK_FREE 2"
.LASF161:
	.string	"__LDBL_DENORM_MIN__ 3.64519953188247460253e-4951L"
.LASF89:
	.string	"__INT64_MAX__ 9223372036854775807L"
.LASF364:
	.string	"__stub_chflags "
.LASF717:
	.string	"__NFDBITS (8 * (int) sizeof (__fd_mask))"
.LASF363:
	.string	"__stub_bdflush "
.LASF394:
	.string	"_SIZET_ "
.LASF729:
	.string	"minor(dev) gnu_dev_minor (dev)"
.LASF446:
	.string	"__TIMER_T_TYPE void *"
.LASF150:
	.string	"__DBL_HAS_QUIET_NAN__ 1"
.LASF320:
	.string	"__ptrvalue "
.LASF679:
	.string	"__uid_t_defined "
.LASF728:
	.string	"major(dev) gnu_dev_major (dev)"
.LASF771:
	.ascii	"strspn(s,accept) __extension__ ({ char __a0, __a1, __a2; (__"
	.ascii	"builtin_constant_p (accept) && __string2_1bptr_p (accept) ? "
	.ascii	"((__builtin_constant_p (s) && __string2_1bptr_p (s)) ? __bui"
	.ascii	"ltin_strspn (s, accept) : ((__a0 = ((const char *) (accept))"
	.ascii	"[0], __a0 == '\\0') ? ((void) (s), (size_t) 0) : ((__a1 = (("
	.ascii	"const char *) (accept))[1], __a1 == '\\0') "
	.string	"? __strspn_c1 (s, __a0) : ((__a2 = ((const char *) (accept))[2], __a2 == '\\0') ? __strspn_c2 (s, __a0, __a1) : (((const char *) (accept))[3] == '\\0' ? __strspn_c3 (s, __a0, __a1, __a2) : __builtin_strspn (s, accept)))))) : __builtin_strspn (s, accept)); })"
.LASF221:
	.string	"__SSE2__ 1"
.LASF689:
	.string	"__need_clock_t"
.LASF804:
	.string	"_IO_buf_end"
.LASF440:
	.string	"__TIME_T_TYPE __SYSCALL_SLONG_TYPE"
.LASF360:
	.string	"__REDIRECT_LDBL(name,proto,alias) __REDIRECT (name, proto, alias)"
.LASF186:
	.string	"__REGISTER_PREFIX__ "
.LASF511:
	.string	"_IO_LINKED 0x80"
.LASF138:
	.string	"__DBL_DIG__ 15"
.LASF340:
	.string	"__attribute_format_strfmon__(a,b) __attribute__ ((__format__ (__strfmon__, a, b)))"
.LASF621:
	.string	"__W_STOPCODE(sig) ((sig) << 8 | 0x7f)"
.LASF24:
	.string	"__SIZEOF_SIZE_T__ 8"
.LASF469:
	.string	"_G_HAVE_MREMAP 1"
.LASF766:
	.ascii	"__strcmp_cc(s1,s2,l) (__extension__ ({ register int __result"
	.ascii	" = (((const unsigned char *) (const char *) (s1))[0] - ((con"
	.ascii	"st unsigned char *) (const char *)(s2))[0]); if (l > 0 && __"
	.ascii	"result == 0) { __result = (((const unsigned char *) (const c"
	.ascii	"har *) (s1))[1] - ((const unsigned char *) (const char *) (s"
	.ascii	"2))[1]); if (l > 1 && __result == 0) { __result = (((con"
	.string	"st unsigned char *) (const char *) (s1))[2] - ((const unsigned char *) (const char *) (s2))[2]); if (l > 2 && __result == 0) __result = (((const unsigned char *) (const char *) (s1))[3] - ((const unsigned char *) (const char *) (s2))[3]); } } __result; }))"
.LASF311:
	.string	"__END_DECLS "
.LASF846:
	.string	"auxCantiMonedas"
.LASF608:
	.string	"WNOWAIT 0x01000000"
.LASF233:
	.string	"__DECIMAL_BID_FORMAT__ 1"
.LASF573:
	.string	"FOPEN_MAX"
.LASF869:
	.string	"strtok"
.LASF867:
	.string	"strtol"
.LASF215:
	.string	"__ATOMIC_HLE_RELEASE 131072"
.LASF62:
	.string	"__UINT_FAST8_TYPE__ unsigned char"
.LASF91:
	.string	"__UINT16_MAX__ 65535"
.LASF657:
	.string	"__WAIT_INT(status) (__extension__ (((union { __typeof(status) __in; int __i; }) { .__in = (status) }).__i))"
.LASF92:
	.string	"__UINT32_MAX__ 4294967295U"
.LASF316:
	.string	"__END_NAMESPACE_C99 "
.LASF223:
	.string	"__SSE_MATH__ 1"
.LASF789:
	.string	"short int"
.LASF289:
	.string	"__USE_ATFILE 1"
.LASF659:
	.string	"WEXITSTATUS(status) __WEXITSTATUS (__WAIT_INT (status))"
.LASF105:
	.string	"__UINT16_C(c) c"
.LASF349:
	.string	"__va_arg_pack_len() __builtin_va_arg_pack_len ()"
.LASF297:
	.string	"_SYS_CDEFS_H 1"
.LASF687:
	.string	"__need_clockid_t "
.LASF7:
	.string	"__ATOMIC_RELAXED 0"
.LASF332:
	.string	"__ASMNAME2(prefix,cname) __STRING (prefix) cname"
.LASF731:
	.string	"__blksize_t_defined "
.LASF119:
	.string	"__UINTPTR_MAX__ 18446744073709551615UL"
.LASF832:
	.string	"matriz"
.LASF72:
	.string	"__LONG_MAX__ 9223372036854775807L"
.LASF633:
	.string	"BYTE_ORDER __BYTE_ORDER"
.LASF59:
	.string	"__INT_FAST16_TYPE__ long int"
.LASF739:
	.string	"__SIZEOF_PTHREAD_COND_T 48"
.LASF4:
	.string	"__GNUC_PATCHLEVEL__ 2"
.LASF264:
	.string	"__USE_REENTRANT"
.LASF408:
	.string	"__UQUAD_TYPE unsigned long int"
.LASF21:
	.string	"__SIZEOF_FLOAT__ 4"
.LASF773:
	.string	"__strtok_r(s,sep,nextp) (__extension__ (__builtin_constant_p (sep) && __string2_1bptr_p (sep) && ((const char *) (sep))[0] != '\\0' && ((const char *) (sep))[1] == '\\0' ? __strtok_r_1c (s, ((const char *) (sep))[0], nextp) : __strtok_r (s, sep, nextp)))"
.LASF70:
	.string	"__SHRT_MAX__ 32767"
.LASF814:
	.string	"_vtable_offset"
.LASF143:
	.string	"__DBL_DECIMAL_DIG__ 17"
.LASF672:
	.string	"_SYS_TYPES_H 1"
.LASF330:
	.string	"__REDIRECT_NTHNL(name,proto,alias) name proto __asm__ (__ASMNAME (#alias)) __THROWNL"
.LASF493:
	.string	"_IOS_INPUT 1"
.LASF425:
	.string	"__NLINK_T_TYPE __SYSCALL_ULONG_TYPE"
.LASF365:
	.string	"__stub_fattach "
.LASF500:
	.string	"_IOS_BIN 128"
.LASF333:
	.string	"__attribute_malloc__ __attribute__ ((__malloc__))"
.LASF409:
	.string	"__SWORD_TYPE long int"
.LASF335:
	.string	"__attribute_const__ __attribute__ ((__const__))"
.LASF576:
	.string	"stdout stdout"
.LASF338:
	.string	"__attribute_deprecated__ __attribute__ ((__deprecated__))"
.LASF695:
	.string	"__need_timer_t"
.LASF562:
	.string	"_IOLBF 1"
.LASF276:
	.string	"__USE_POSIX 1"
.LASF777:
	.string	"__need_malloc_and_calloc "
.LASF581:
	.ascii	"fread_unlocked(ptr,size,n,stream) (__extension__ ((__builtin"
	.ascii	"_constant_p (size) && __builtin_constant_p (n) && (size_t) ("
	.ascii	"size) * (size_t) (n) <= 8 && (size_t) (size) != 0) ? ({ char"
	.ascii	" *__ptr = (char *) (ptr); FILE *__stream = (stream); size_t "
	.ascii	"__cnt; for (__cnt = (size_t) (size) * (size_t) (n); __cnt > "
	.ascii	"0; --__cnt) { int __c = _IO_getc_unlocked (__stream); if (__"
	.ascii	"c == EOF) break; *__ptr++ = __c; } ((size_t) (size) * (size_"
	.ascii	"t) (n) "
	.string	"- __cnt) / (size_t) (size); }) : (((__builtin_constant_p (size) && (size_t) (size) == 0) || (__builtin_constant_p (n) && (size_t) (n) == 0)) ? ((void) (ptr), (void) (stream), (void) (size), (void) (n), (size_t) 0) : fread_unlocked (ptr, size, n, stream))))"
.LASF865:
	.string	"putchar"
.LASF671:
	.string	"MB_CUR_MAX (__ctype_get_mb_cur_max ())"
.LASF168:
	.string	"__DEC32_MIN__ 1E-95DF"
.LASF471:
	.string	"_G_HAVE_ST_BLKSIZE defined (_STATBUF_ST_BLKSIZE)"
.LASF401:
	.string	"__S16_TYPE short int"
.LASF208:
	.string	"__SIZEOF_WINT_T__ 4"
.LASF690:
	.string	"__time_t_defined 1"
.LASF0:
	.string	"__STDC__ 1"
.LASF212:
	.string	"__x86_64 1"
.LASF742:
	.string	"__SIZEOF_PTHREAD_RWLOCKATTR_T 8"
.LASF342:
	.string	"__attribute_warn_unused_result__ __attribute__ ((__warn_unused_result__))"
.LASF534:
	.string	"_IO_SCIENTIFIC 04000"
.LASF345:
	.string	"__attribute_artificial__ __attribute__ ((__artificial__))"
.LASF503:
	.string	"_IO_MAGIC_MASK 0xFFFF0000"
.LASF759:
	.ascii	"__STRING2_SMALL_GET32"
	.string	"(src,idx) (((((const unsigned char *) (const char *) (src))[idx + 3] << 8 | ((const unsigned char *) (const char *) (src))[idx + 2]) << 8 | ((const unsigned char *) (const char *) (src))[idx + 1]) << 8 | ((const unsigned char *) (const char *) (src))[idx])"
.LASF166:
	.string	"__DEC32_MIN_EXP__ (-94)"
.LASF49:
	.string	"__UINT64_TYPE__ long unsigned int"
.LASF546:
	.string	"_IO_getc_unlocked(_fp) (_IO_BE ((_fp)->_IO_read_ptr >= (_fp)->_IO_read_end, 0) ? __uflow (_fp) : *(unsigned char *) (_fp)->_IO_read_ptr++)"
.LASF78:
	.string	"__PTRDIFF_MAX__ 9223372036854775807L"
.LASF596:
	.string	"___int_wchar_t_h "
.LASF632:
	.string	"PDP_ENDIAN __PDP_ENDIAN"
.LASF23:
	.string	"__SIZEOF_LONG_DOUBLE__ 16"
.LASF543:
	.string	"_IO_stdout ((_IO_FILE*)(&_IO_2_1_stdout_))"
.LASF358:
	.string	"__LDBL_REDIR_NTH(name,proto) name proto __THROW"
.LASF439:
	.string	"__CLOCK_T_TYPE __SYSCALL_SLONG_TYPE"
.LASF348:
	.string	"__va_arg_pack() __builtin_va_arg_pack ()"
.LASF430:
	.string	"__RLIM_T_TYPE __SYSCALL_ULONG_TYPE"
.LASF219:
	.string	"__MMX__ 1"
.LASF798:
	.string	"_IO_read_end"
.LASF468:
	.string	"_G_HAVE_MMAP 1"
.LASF666:
	.string	"__ldiv_t_defined 1"
.LASF260:
	.string	"__USE_SVID"
.LASF531:
	.string	"_IO_SHOWPOINT 0400"
.LASF393:
	.string	"_GCC_SIZE_T "
.LASF588:
	.string	"_T_WCHAR_ "
.LASF334:
	.string	"__attribute_pure__ __attribute__ ((__pure__))"
.LASF698:
	.string	"__u_intN_t(N,MODE) typedef unsigned int u_int ##N ##_t __attribute__ ((__mode__ (MODE)))"
.LASF17:
	.string	"__SIZEOF_INT__ 4"
.LASF600:
	.string	"_BSD_WCHAR_T_"
.LASF719:
	.string	"__FD_MASK(d) ((__fd_mask) 1 << ((d) % __NFDBITS))"
.LASF628:
	.string	"__BYTE_ORDER __LITTLE_ENDIAN"
.LASF459:
	.string	"__need___FILE"
.LASF419:
	.string	"__DEV_T_TYPE __UQUAD_TYPE"
.LASF653:
	.string	"w_coredump __wait_terminated.__w_coredump"
.LASF765:
	.ascii	"strcmp(s1,s2) __extension__ ({ size_t __s1_len, __s2_len; (_"
	.ascii	"_builtin_constant_p (s1) && __builtin_constant_p (s2) && (__"
	.ascii	"s1_len = strlen (s1), __s2_len = strlen (s2), (!__string2_1b"
	.ascii	"ptr_p (s1) || __s1_len >= 4) && (!__string2_1bptr_p (s2) || "
	.ascii	"__s2_len >= 4)) ? __builtin_strcmp (s1, s2) : (__builtin_con"
	.ascii	"stant_p (s1) && __string2_1bptr_p (s1) && (__s1_len = strlen"
	.ascii	" (s1), __s1_len < 4) ? (__builtin_constant_p (s2) && __strin"
	.ascii	"g2_1bptr_p (s2) ? __builtin_strcmp (s1, s2) : __strcmp_cg (s"
	.ascii	"1, s2, __s"
	.string	"1_len)) : (__builtin_constant_p (s2) && __string2_1bptr_p (s2) && (__s2_len = strlen (s2), __s2_len < 4) ? (__builtin_constant_p (s1) && __string2_1bptr_p (s1) ? __builtin_strcmp (s1, s2) : __strcmp_gc (s1, s2, __s2_len)) : __builtin_strcmp (s1, s2)))); })"
.LASF262:
	.string	"__USE_ATFILE"
.LASF810:
	.string	"_fileno"
.LASF321:
	.string	"__bos(ptr) __builtin_object_size (ptr, __USE_FORTIFY_LEVEL > 1)"
.LASF634:
	.string	"__LONG_LONG_PAIR(HI,LO) LO, HI"
.LASF210:
	.string	"__amd64 1"
.LASF646:
	.string	"be32toh(x) __bswap_32 (x)"
.LASF84:
	.string	"__SIG_ATOMIC_MAX__ 2147483647"
.LASF277:
	.string	"__USE_POSIX2 1"
.LASF230:
	.string	"__unix__ 1"
.LASF431:
	.string	"__RLIM64_T_TYPE __UQUAD_TYPE"
.LASF620:
	.string	"__W_EXITCODE(ret,sig) ((ret) << 8 | (sig))"
.LASF733:
	.string	"__fsblkcnt_t_defined "
.LASF696:
	.string	"__need_timespec"
.LASF152:
	.string	"__LDBL_DIG__ 18"
.LASF287:
	.string	"__USE_BSD 1"
.LASF423:
	.string	"__INO64_T_TYPE __UQUAD_TYPE"
.LASF457:
	.string	"__need_FILE"
.LASF677:
	.string	"__mode_t_defined "
.LASF6:
	.string	"__GNUC_RH_RELEASE__ 7"
.LASF77:
	.string	"__WINT_MIN__ 0U"
.LASF424:
	.string	"__MODE_T_TYPE __U32_TYPE"
.LASF514:
	.string	"_IO_TIED_PUT_GET 0x400"
.LASF760:
	.string	"__string2_1bptr_p(__x) ((size_t)(const void *)((__x) + 1) - (size_t)(const void *)(__x) == 1)"
.LASF177:
	.string	"__DEC64_EPSILON__ 1E-15DD"
.LASF473:
	.string	"_IO_fpos_t _G_fpos_t"
.LASF309:
	.string	"__long_double_t long double"
.LASF51:
	.string	"__INT_LEAST16_TYPE__ short int"
.LASF160:
	.string	"__LDBL_EPSILON__ 1.08420217248550443401e-19L"
.LASF752:
	.string	"__COMPAR_FN_T "
.LASF436:
	.string	"__FSFILCNT_T_TYPE __SYSCALL_ULONG_TYPE"
.LASF786:
	.string	"short unsigned int"
.LASF860:
	.string	"stdout"
.LASF585:
	.string	"__wchar_t__ "
.LASF749:
	.string	"_ALLOCA_H 1"
.LASF551:
	.string	"_IO_PENDING_OUTPUT_COUNT(_fp) ((_fp)->_IO_write_ptr - (_fp)->_IO_write_base)"
.LASF290:
	.string	"__USE_FORTIFY_LEVEL 0"
.LASF153:
	.string	"__LDBL_MIN_EXP__ (-16381)"
.LASF286:
	.string	"__USE_MISC 1"
.LASF111:
	.string	"__INT_FAST16_MAX__ 9223372036854775807L"
.LASF449:
	.string	"__SSIZE_T_TYPE __SWORD_TYPE"
.LASF313:
	.string	"__END_NAMESPACE_STD "
.LASF801:
	.string	"_IO_write_ptr"
.LASF615:
	.string	"__WIFEXITED(status) (__WTERMSIG(status) == 0)"
.LASF375:
	.string	"__stub_sstk "
.LASF840:
	.string	"imprimeTabla"
.LASF231:
	.string	"unix 1"
.LASF269:
	.string	"__USE_ANSI 1"
.LASF676:
	.string	"__gid_t_defined "
.LASF149:
	.string	"__DBL_HAS_INFINITY__ 1"
.LASF326:
	.string	"__errordecl(name,msg) extern void name (void) __attribute__((__error__ (msg)))"
.LASF362:
	.string	"__USE_EXTERN_INLINES 1"
.LASF669:
	.string	"EXIT_FAILURE 1"
.LASF300:
	.string	"__LEAF_ATTR __attribute__ ((__leaf__))"
.LASF130:
	.string	"__FLT_MAX__ 3.40282346638528859812e+38F"
.LASF868:
	.string	"exit"
.LASF351:
	.string	"__glibc_unlikely(cond) __builtin_expect((cond), 0)"
.LASF285:
	.string	"_ATFILE_SOURCE 1"
.LASF46:
	.string	"__UINT8_TYPE__ unsigned char"
.LASF257:
	.string	"__USE_LARGEFILE64"
.LASF97:
	.string	"__INT16_C(c) c"
.LASF238:
	.string	"__STDC_NO_THREADS__ 1"
.LASF662:
	.string	"WIFEXITED(status) __WIFEXITED (__WAIT_INT (status))"
.LASF11:
	.string	"__ATOMIC_ACQ_REL 4"
.LASF781:
	.string	"__STRING_INLINE"
.LASF636:
	.string	"__bswap_constant_16(x) ((unsigned short int) ((((x) >> 8) & 0xff) | (((x) & 0xff) << 8)))"
.LASF226:
	.string	"__linux 1"
.LASF593:
	.string	"_WCHAR_T_DEFINED_ "
.LASF723:
	.string	"FD_SET(fd,fdsetp) __FD_SET (fd, fdsetp)"
.LASF90:
	.string	"__UINT8_MAX__ 255"
.LASF751:
	.string	"alloca(size) __builtin_alloca (size)"
.LASF102:
	.string	"__UINT_LEAST8_MAX__ 255"
.LASF741:
	.string	"__SIZEOF_PTHREAD_RWLOCK_T 56"
.LASF494:
	.string	"_IOS_OUTPUT 2"
.LASF137:
	.string	"__DBL_MANT_DIG__ 53"
.LASF746:
	.string	"__PTHREAD_MUTEX_HAVE_PREV 1"
.LASF848:
	.string	"Cambio"
.LASF80:
	.string	"__INTMAX_MAX__ 9223372036854775807L"
.LASF541:
	.string	"__HAVE_COLUMN "
.LASF484:
	.string	"_IO_va_list _G_va_list"
	.ident	"GCC: (GNU) 4.8.2 20131212 (Red Hat 4.8.2-7)"
	.section	.note.GNU-stack,"",@progbits
