#include <stdio.h>
#include <stdlib.h>
#ifdef _OPENMP
  #include <omp.h>
#else
  #define omp_get_thread_num() 0
  #define omp_get_num_threads() 1
#endif

int main(int argc, char ** argv)
{
  int n = 9, i, a, b[n];
  for (i=0; i<n; i++) b[i] = -1;
  #ifdef _OPENMP
    #pragma omp parallel
  #endif
  {
    #ifdef _OPENMP
      #pragma omp single
    #endif
    {
      printf("Introduce valor de inicialización a: ");
      scanf("%d", &a );
      printf("Single ejecutada por el thread %d\n",omp_get_thread_num());
    }
    #ifdef _OPENMP
      #pragma omp for
    #endif
      for (i=0; i<n; i++)
	b[i] = a;
    
    #ifdef _OPENMP
      #pragma omp single
    #endif
    {
      printf("Dentro del parallel en la thread %d:\n",omp_get_thread_num());
      for (i=0; i<n; i++)
    	  printf("b[%d] = %d\t",i,b[i]);
      printf("\n");
    }
  }
  return 0;
}
