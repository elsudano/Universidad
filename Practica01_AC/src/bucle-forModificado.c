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
  int i, n = 9;

  if(argc < 2) {
    fprintf(stderr,"\n[ERROR] - Falta nº iteraciones \n");
    exit(-1);
  }
  n = atoi(argv[1]);
#ifdef _OPENMP
  #pragma omp parallel for
#endif
  for (i=0; i<n; i++)
    printf("thread %d ejecuta la iteración %d del bucle\n",omp_get_thread_num(),i);
  
  return(0);
}