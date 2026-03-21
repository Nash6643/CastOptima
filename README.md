# CastOptima Engine

CastOptima is an enterprise combinatorial optimization engine engineered for film production cast scheduling and actor holding-cost minimization.

## Architecture & Features

- **Domain Model**: Strongly typed `Actor` and `Scene` entities representing daily rates, required cast sets, and budget allocations.
- **Evaluation Engine**: `CostCalculator` tracking actor contract spans to compute total holding expenses.
- **Optimization Algorithms**:
  - `GreedyCSPSolver`: Fast heuristic baseline using local search.
  - `SimulatedAnnealingSolver`: Probabilistic metaheuristic utilizing the Metropolis criterion.
  - `GeneticAlgorithmSolver`: Population-based evolutionary solver featuring Order Crossover (OX) and elitism.
- **I/O & Benchmarking**: Export capabilities alongside runtime benchmarking tools (`BenchmarkRunner`).

## Build & Execution

To compile and execute the benchmark suite:

```powershell
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp bin com.castoptima.Main