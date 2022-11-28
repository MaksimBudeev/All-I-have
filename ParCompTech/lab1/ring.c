#include <stdio.h>
#include <mpi.h>
#include <stdlib.h>
 
int main(int argc, char *argv[]) {
    MPI_Init(&argc, &argv);
    int rank, commsize;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &commsize);
    int prev = (rank - 1 + commsize) % commsize;
    int next = (rank + 1) % commsize;
    int count = 1024;
    char *buf = malloc(sizeof(*buf) * commsize * count);
    char *p = buf + rank * count * sizeof(char);
    for (int i = 0; i < count; i++) {
        p[i] = rank;
    }
    int sblock = rank;
    int rblock = prev;
    
    double t = MPI_Wtime();

    for (int i = 0; i < commsize - 1; i++) {
        MPI_Sendrecv(buf + sblock * count * sizeof(char), count, MPI_CHAR, next, 0,
        buf + rblock * count * sizeof(char), count, MPI_CHAR, prev, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        sblock = (sblock - 1 + commsize) % commsize;
        rblock = (rblock - 1 + commsize) % commsize;
    }

    t = MPI_Wtime() - t;
    
    if (rank == 0) {
       /* for (int i = 0; i < commsize * count; i++) {
             printf("%d ", buf[i]);
        }
        printf("\n");*/

        printf("Process #%d - %0.6f", rank, t);
    } 


    free(buf);
    MPI_Finalize();
}