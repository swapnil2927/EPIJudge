package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsCircuitWirable {

  public static class GraphVertex {
    public int d = -1;
    public List<GraphVertex> edges = new ArrayList<>();
  }

  public static boolean isAnyPlacementFeasible(List<GraphVertex> graph) {
    Map<GraphVertex, Integer> color = new HashMap<>();
    for (GraphVertex curVer : graph) {
      if (color.get(curVer) == null) {
        color.put(curVer, 1);
        boolean conflictFound = bfs(curVer, color, graph);
        if (conflictFound) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean bfs(GraphVertex vertex, Map<GraphVertex, Integer> color, List<GraphVertex> graph) {
    Queue<GraphVertex> nodes = new ArrayDeque<>();
    if (vertex.edges.size() == 0) {
      return false;
    }
    nodes.add(vertex);
    while (nodes.size() != 0) {
      GraphVertex cur = nodes.poll();
      if (color.get(cur) == 1) {
        for (GraphVertex adjV : cur.edges) {
          if (color.get(adjV) == null) {
            color.put(adjV, 2);
            nodes.add(adjV);
          }
          if (color.get(adjV) == 1) {
            return true;
          }
        }
      } else if (color.get(cur) == 2) {
        for (GraphVertex adjV : cur.edges) {
          if (color.get(adjV) == null) {
            color.put(adjV, 1);
            nodes.add(adjV);
          }
          if (color.get(adjV) == 2) {
            return true;
          }
        }
      }
    }
    return false;
  }
  @EpiUserType(ctorParams = {int.class, int.class})
  public static class Edge {
    public int from;
    public int to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  @EpiTest(testDataFile = "is_circuit_wirable.tsv")
  public static boolean isAnyPlacementFeasibleWrapper(TimedExecutor executor,
                                                      int k, List<Edge> edges)
      throws Exception {
    if (k <= 0)
      throw new RuntimeException("Invalid k value");
    List<GraphVertex> graph = new ArrayList<>();
    for (int i = 0; i < k; i++)
      graph.add(new GraphVertex());
    for (Edge e : edges) {
      if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k)
        throw new RuntimeException("Invalid vertex index");
      graph.get(e.from).edges.add(graph.get(e.to));
    }

    return executor.run(() -> isAnyPlacementFeasible(graph));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsCircuitWirable.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
