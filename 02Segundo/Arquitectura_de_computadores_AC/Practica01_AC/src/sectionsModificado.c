#include <stdio.h>
#include <stdlib.h>
#ifdef _OPENMP
  #include <omp.h>
#else
  #define omp_get_thread_num() 0
  #define omp_get_num_threads() 1
#endif

void funcA() {
  printf("En funcA: esta ejecuta el thread %d\n",omp_get_thread_num());
}

void funcB() {
  printf("En funcB: esta sección la ejecuta el thread %d\n",omp_get_thread_num());
}

int main(int argc, char ** argv)
{
  #ifdef _OPENMP
    #pragma omp parallel sections
  #endif
  {
    #ifdef _OPENMP
      #pragma omp section
    #endif
    (void) funcA();
    #ifdef _OPENMP
      #pragma omp section
    #endif
    (void) funcB();
  }
  return 0;
}