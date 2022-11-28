#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) 
{
    int rank, commsize;
    int root = 0;

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &commsize);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    int count = 1024;
    char *sbuf = malloc(sizeof(*sbuf) * count);

    for (int i = 0; i < count; i++) {
        sbuf[i] = rank;
    }
    
    char *rbuf = malloc(sizeof(*rbuf) * count * commsize);

    double t = MPI_Wtime();
    if (rank == root) 
	{
        memcpy(rbuf + count * root * sizeof(char), sbuf, count * sizeof(*sbuf)); 
        for (int i = 0; i < commsize; i++) 
	    {
            if (i == root) 
	        {
               continue;
            }
            MPI_Recv(rbuf + count * i * sizeof(*rbuf), count, MPI_CHAR, i, 0, MPI_COMM_WORLD,  MPI_STATUS_IGNORE);
        }
    }
	else
	{
        MPI_Send(sbuf, count, MPI_CHAR, root, 0, MPI_COMM_WORLD);
    }
    t = MPI_Wtime() - t;
    /*
    if (rank == 0) 
	{
        printf("\n%d elem - time %0.6f\n", count, t);

        for (int i = 0; i < count * commsize; i++) {
            printf("%d ", rbuf[i]);
        }

	printf("\n");
    }
    */
    printf("Process #%d - %0.6f time - %d elem\n", rank, t, count);
    free(sbuf);
    free(rbuf);
    MPI_Finalize();
    return 0;
}
