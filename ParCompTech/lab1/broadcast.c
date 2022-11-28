#include <stdio.h>
#include <mpi.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    MPI_Init(&argc, &argv);
    int rank, commsize;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    double t;
    int count = 1024 * 1024;

    // Номер процесса (0, 1, ..., p - 1) в глобальном коммуникаторе MPI_COMM_WORLD
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    // Число процессов
    MPI_Comm_size(MPI_COMM_WORLD, &commsize);
    int root = 0;
    int size = sizeof(char) * count;
    
    char* buf = malloc(size);

    if (rank == root) {
        for (int i = 0; i < size; i++)
        {
            buf[i] = 'Z';
        }
    }
    t = MPI_Wtime();

    if (rank == root) {
        for (int i = 0; i < commsize; ++i) {
            if (i != root) {
                MPI_Send(buf, count, MPI_CHAR, i, 0, MPI_COMM_WORLD);
            }
        }
    } else {
        MPI_Recv(buf, count, MPI_CHAR, root, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
    }
    
    t = MPI_Wtime() - t;
    
    /*if (rank == 3) {
        for (int i = 0; i < count; i++) {
            if (i % 100 == 0)
                putchar('\n');
            printf("%c", buf[i]);
        }
    }*/


    free(buf);
    printf("Process #%d - %0.6f time - %d elem\n", rank, t, count);
    MPI_Finalize();
    return 0;
}