package bearmaps.proj2c;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private List<Vertex> solution = new ArrayList<>();
    private Map<Vertex, Double> distTo = new HashMap<>();
    private Map<Vertex, Vertex> edgeTo = new HashMap<>();
    private DoubleMapPQ<Vertex> fringe = new DoubleMapPQ<>();
    private Set<Vertex> visited = new HashSet<Vertex>();

    private double solutionWeight = 0.0;
    private int numStatesExplored = 0;
    private SolverOutcome outcome;
    private double timeSpent;
    AStarGraph<Vertex> graph;
    private Vertex goal;
    private Vertex currVertex;
    private Vertex p;
    private Vertex q;
    private double w;

    // Helper (relax) method
    private void relax(WeightedEdge<Vertex> e) {
        p = e.from();
        q = e.to();
        w = e.weight();
        double infinity = Double.POSITIVE_INFINITY;
        // If the vertex is not in the distTo set, set distTo to inf
        if (!distTo.containsKey(q)) {
            distTo.put(q, infinity);
        }

        if (distTo.get(p) + w < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);
            if (fringe.contains(q)) {
                fringe.changePriority(q, distTo.get(q) + graph.estimatedDistanceToGoal(q, goal));
            } else {
                fringe.add(q, distTo.get(q) + graph.estimatedDistanceToGoal(q, goal));
            }
            edgeTo.put(q, p);
        }
    }

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        this.graph = input;
        this.goal = end;

        // Add source to queue, distTo and edgeTo
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        edgeTo.put(start, null);

        // Repeat until the PQ is empty,
        // PQ.getSmallest() is the goal, or
        // timeout is exceeded
        while (fringe.size() != 0) {

            // if PQ.getSmallest == goal:
            if (fringe.getSmallest().equals(end)) {
                Vertex vertex = fringe.removeSmallest();
                while (vertex != null) {
                    this.solution.add(vertex);
                    vertex = edgeTo.get(vertex);
                }
                this.solutionWeight = distTo.get(end);
                this.outcome = SolverOutcome.SOLVED;
                this.timeSpent = sw.elapsedTime();
                Collections.reverse(solution);
                return;
                // if PQ is empty:
               // if timeout is exceeded
            } else if (sw.elapsedTime() > timeout) {
                this.outcome = SolverOutcome.TIMEOUT;
                this.timeSpent = sw.elapsedTime();
                return;
            }

            currVertex = fringe.removeSmallest();

            for (WeightedEdge<Vertex> neighbor : graph.neighbors(currVertex)) {
                if (!visited.contains(neighbor)) {
                    relax(neighbor);
                }
            }
            numStatesExplored++;
            visited.add(currVertex);
        }
        this.outcome = SolverOutcome.UNSOLVABLE;
        this.timeSpent = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
            return this.solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
