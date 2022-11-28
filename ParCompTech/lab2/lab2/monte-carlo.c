#include <stdio.h>
#include <math.h>
#include <mpi.h>
#include <stdlib.h>
#include <sys/time.h>

const double PI = 3.14159265358979323846;
const int n = 10000000;

double getrand()
{
	return (double)rand() / RAND_MAX;
}

double func(double x, double y)
{
	return powf(exp(x + y), 2);
}

double wtime()
{
	struct timeval tv;
	gettimeofday(&tv, NULL);
	return tv.tv_sec + tv.tv_usec * 1E-6;
}

double stream_getrand()
{
	return (double)rand() / RAND_MAX;
}

double lineal(int n0)
{
	double t = wtime();

	int in = 0;
	double sum = 0;

	for (int i = 0; i < n0; i++) {
		double x = stream_getrand();
		double y = stream_getrand();

		if (y <= 1 - x) {
			in++;
			sum += func(x, y);
		}
	}

	t = wtime() - t;

	double v = in / n0;
	double res = v * sum / in;

	return t;
}

int main(int argc, char *argv[])
{
	MPI_Init(&argc, &argv);
	
	int rank, commsize;

	MPI_Comm_size(MPI_COMM_WORLD, &commsize);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);

	srand(rank);
	int in = 0, n0 = n;
	double sum = 0;

	double t = MPI_Wtime();

	for (int i = rank; i < n; i += commsize ) {
		double x = getrand();
		double y = getrand();

		if (y <= 1 - x) {
			in++;
			sum += func(x, y);
		}
	}

	int gin = 0.0;
	MPI_Reduce(&in, &gin, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

	double gsum = 0.0;
	MPI_Reduce(&sum, &gsum, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD); 
	t = MPI_Wtime() - t;
	double t_max = 0.0;
	MPI_Reduce(&t, &t_max, 1, MPI_DOUBLE, MPI_MAX, 0, MPI_COMM_WORLD); 
	if (rank == 0) {
		double v = gin / n;
		double res = v * gsum / gin;
		double t_lineal = lineal(n0);

		printf("%.12f\n", t_lineal / t_max);
	}

	MPI_Finalize();

	return 0;
}
