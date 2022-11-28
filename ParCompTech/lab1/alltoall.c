#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
    int rank, commsize;

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &commsize);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Request reqs[commsize * 2];

    int count = 1024;
    int n = 0;
    char *sendbuf = malloc(sizeof(*sendbuf) * count * commsize);
    char *recvbuf = malloc(sizeof(*recvbuf) * count * commsize);

    for (int i = 0; i < commsize * count; i++) {
        sendbuf[i] = rank;
    }
    double t = MPI_Wtime();

    for (int i = 0; i < commsize; i++) 
    {
	    MPI_Isend(sendbuf + i * count * sizeof(*sendbuf), count, MPI_CHAR, i, 0, MPI_COMM_WORLD, &reqs[n++]);
        MPI_Irecv(recvbuf + i * count * sizeof(*recvbuf), count, MPI_CHAR, i, 0, MPI_COMM_WORLD, &reqs[n++]);
    }

    MPI_Waitall(n, reqs, MPI_STATUS_IGNORE);

    t = MPI_Wtime() - t;
    /*if (rank == 0) 
    {
        for (int i = 0; i < count * commsize; i++)
        {
            printf("%d ", recvbuf[i]);
        }
        printf("\n%d elem - time %0.6f\n", count, t);
    }*/

    printf("Process #%d - %0.6f time - %d elem\n", rank, t, count);
    free(sendbuf);
    free(recvbuf);
    MPI_Finalize();
    return 0;
}
