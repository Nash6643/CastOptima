# CastOptima Engine

CastOptima is an enterprise-grade combinatorial optimization engine for movie cast scheduling and holding-cost minimization.

## Algorithm Specs
- **GreedyCSPSolver**: Uses incremental constraint checking to evaluate actor holding costs across scene perturbations.
- **SimulatedAnnealingSolver**: Probabilistic metaheuristic applying the Metropolis criterion to escape local minima in complex schedules.
**Genetic Algorithm Solver**: Evolutionary strategy utilizing crossover and mutation mechanisms to search for optimal schedule ordering.

## Running the Solvers

Run `Main.java` to evaluate all three optimization algorithms on the sample problem:

```bash
java -cp target/classes com.castoptima.Main