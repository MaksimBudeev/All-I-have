#!/bin/bash

./bin/dgemmexe --size 1000
./bin/dgemmOMPexe --size 1000 --count-threads 2
./bin/dgemmOMPexe --size 1000 --count-threads 3
./bin/dgemmOMPexe --size 1000 --count-threads 4
./bin/dgemmPTexe --size 1000 --count-threads 2
./bin/dgemmPTexe --size 1000 --count-threads 3
./bin/dgemmPTexe --size 1000 --count-threads 4
mpiexec -n 2 ./bin/dgemmMPIexe --size 1000 
mpiexec -n 3 ./bin/dgemmMPIexe --size 1000 
mpiexec -n 4 ./bin/dgemmMPIexe --size 1000 

echo "End of program"