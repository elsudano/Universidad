Ejercicios del Tema 1
=======================

<a name="ejercicio1"></a>1- Ejercicio.
*  Consultar en el catálogo de alguna tienda de informática el precio de un ordenador tipo servidor y calcular su coste de amortización a cuatro y siete años.
    -  Respuesta:
    Ordenador: [RAX XT16-2260V3-10G](http://www.thinkmate.com/system/rax-xt16-2260v3-10g)
    Precio: 2.934 dolares que es igual a 2.934 * 0,892219843 = 2.617 euros
    Base imponible: 2.934 (precio) - 10% (tax) = 2.667,27 * 0,892219843 (cambio) =  2379,79 (euros)

    [Según el articulo](http://infoautonomos.eleconomista.es/fiscalidad/gastos-deducibles-autonomos-irpf-estimacion-directa/) se puede realizar una estimación directa con las tablas simplificadas, buscando hemos encontrado [en la agencia tributaria](http://www.agenciatributaria.es/AEAT.internet/Inicio/_Segmentos_/Empresas_y_profesionales/Empresarios_individuales_y_profesionales/Rendimientos_de_actividades_economicas_en_el_IRPF/Regimenes_para_determinar_el_rendimiento_de_las_actividades_economicas/Estimacion_Directa_Simplificada.shtml) dichas tablas.

    Según dicen las tablas el valor maximo anual que se puede amortizar es del 26% por lo tanto lo que hacemos es cálcular la cantidad maxima que podemos amortizar en lo que queda de año *menos 4 meses* he intentamos amortizar el maximo en el tiempo que queda.

    | Año | Porcentaje |                  Valor                  |                    Explicación                                 |
    |:---:|:----------:|:---------------------------------------:|:--------------------------------------------------------------:|
    | 1   |    26%     |        2.102,06*26% = 546,53 €          | Lo que queda de año podemos amortizar (546,53/12)x4=136,63 €   |
    | 2   |    26%     |  2.102,06 - 136,63 -546,53 = 1965,43 €  | Restamos el primer año y el total del año actual               |
    | 3   |    26%     | 2.102,06 - 136,63 - 546,53 =  1418,9 €  | todavia queda suficiente para amortizar 511,06 más             |
    | 4   |    26%     |      1418,9 - 546,53 = 872,37 €         | Lo que queda de año podemos amortizar (546,53/12)x4=136,63 €   |

    por lo tanto este servidor no es amortizable a 4 años, pero sin enbargo a 7 si.

    | Año | Porcentaje |                  Valor                  |                    Explicación                                 |
    |:---:|:----------:|:---------------------------------------:|:--------------------------------------------------------------:|
    | 1   |    26%     |        2.102,06*26% = 546,53 €          | Lo que queda de año podemos amortizar (546,53/12)x4=136,63 €   |
    | 2   |    26%     |  2.102,06 - 136,63 -546,53 = 1965,43 €  | Restamos el primer año y el total del año actual               |
    | 3   |    26%     |      1965,43 - 546,53 =  1418,9 €       | todavia queda suficiente para amortizar 546,53 más             |
    | 4   |    26%     |      1418,9 - 546,53 = 872,37 €         | todavia queda suficiente para amortizar 546,53 más             |
    | 5   |    26%     |      872,37 - 546,53 = 325,84 €         | todavia queda suficiente para amortizar 546,53 más             |
    | 6   |    26%     |               325,84                    | se amortiza el resto del equipo                                |
    | 7   |    26%     |                                         |                                                                |

<a name="ejercicio2"></a>2- Ejercicio
* Usando las tablas de precios de servicios de alojamiento en Internet y de proveedores de servicios en la nube, Comparar el coste durante un año de un ordenador con un procesador estándar (escogerlo de forma que sea el mismo tipo de procesador en los dos vendedores) y con el resto de las características similares (tamaño de disco duro equivalente a transferencia de disco duro) en el caso de que la infraestructura comprada se usa sólo el 1% o el 10% del tiempo.
    -  Respuesta:

<a name="ejercicio3"></a>3- Ejercicio
* ¿Qué tipo de virtualización usarías en cada caso?
    -  Respuesta:

        * Cada tipo de virtualización tiene su cometido a la hora de montar cualquier infraestructura virtual hay que tener en cuenta cuales son las caracteristicas que necesitamos en cada caso, por ejemplo un primer nivel de vitualización podría ser la Virtualización de sistema operativo, ya que desde los equipos físicos instalariamos un hipervisor como por ejemplo ESXi de vmware que proporciona comunicación desde los equipos invitados hacia el anfitrión.
        * Después esta la virtualización plena que se encarga de virtualizar todos los aspectos de un equipo, se puede hacer con VmWare Workstation.
        * Después tenemos una virtualización de aplicaciones como puede ser Wine para virtualizar aplicaciones windows en Linux.


* Crear un programa simple en cualquier lenguaje interpretado para Linux, empaquetarlo con CDE y probarlo en diferentes distribuciones.
    -  Respuesta:

<a name="ejercicio4"></a>4- Ejercicio

* [x]  Comprobar si el procesador o procesadores instalados tienen estos flags.
* ¿Qué modelo de procesador es?
    -  Respuesta:

    >model name      : Intel(R) Core(TM) i7-6700HQ CPU @ 2.60GHz
* ¿Qué aparece como salida de esa orden?
    -  Respuesta:
    
    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

    >flags           : fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu pni pclmulqdq dtes64 monitor ds_cpl vmx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb intel_pt tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp

<a name="ejercicio5"></a>5- Ejercicio

* Comprobar si el núcleo instalado en tu ordenador contiene este módulo del kernel usando la orden kvm-ok.
    -  Respuesta:

    Mi equipo tiene una bios UEFI y parece que no tiene integrado el modulo en el kernel.

* Instalar un hipervisor para gestionar máquinas virtuales, que más adelante se podrá usar en pruebas y ejercicios.
    -  Respuesta:

    ![VmWare Workstation](captura3.png)
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
