package GraphTask1;

import java.util.LinkedList;

class Task {


    boolean bfs(int[][] rGraph, int s, int t, int[] parent) {

        boolean[] visited = new boolean[rGraph.length];
        for (int i = 0; i < rGraph.length; ++i)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;


        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < rGraph.length; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[t]);
    }

    int fordFulkersonA(int[][] graph, int s, int t) {
        int u;
        int v;
        int[][] rGraph = new int[graph.length][graph.length];
        for (u = 0; u < graph.length; u++)
            for (v = 0; v < graph.length; v++)
                rGraph[u][v] = graph[u][v];

        int[] parent = new int[graph.length];

        int maxFlow = 0;

        while (bfs(rGraph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
//                rGraph[u][v] -= pathFlow;
//                rGraph[v][u] += pathFlow;
                rGraph[u][v] = 0;
                rGraph[v][u] = 0;
//                System.out.println(u + "-" + v);
            }
//            System.out.println("----");
            maxFlow += pathFlow;
        }


        return maxFlow;
    }

    int fordFulkersonB(int[][] graph, int s, int t) {
        int u;
        int v;
        int[][] rGraph = new int[graph.length][graph.length];
        for (u = 0; u < graph.length; u++)
            for (v = 0; v < graph.length; v++)
                rGraph[u][v] = graph[u][v];

        int[] parent = new int[graph.length];

        int maxFlow = 0;

        while (bfs(rGraph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                deleteRow(rGraph, u);
//                graph = deleteRow(graph, v);
//                System.out.println(u + "-" + v);
            }
//            System.out.println("----");
            maxFlow += pathFlow;
        }


        return maxFlow;
    }


    private static int[][] transform(int[][] graph) {
        int[][] rez = new int[graph.length * 2][graph.length * 2];
        for (int i = 0; i < graph.length; i++) {
            rez[i * 2][i * 2 + 1] = rowSum(graph, i);
            for (int j = 0; j < graph.length; j++) {
                rez[i * 2 + 1][j * 2 + 1] = graph[i][j];
            }
        }
        return rez;
    }

    private void deleteRow(int[][] graph, int row) {
        for (int i = 0; i < graph.length; i++) {
            graph[row][i] = 0;
        }
    }

    private static int rowSum(int[][] graph, int row) {
        int cnt = 0;
        for (int i = 0; i < graph.length; i++) {
            cnt += graph[row][i];
        }
        return cnt;
    }

    public static void main(String[] args) {
//        int[][] graph = new int[][]{
//                {0, 1, 1, 0, 0, 0},
//                {0, 0, 1, 1, 0, 0},
//                {0, 0, 0, 0, 1, 0},
//                {0, 0, 1, 0, 0, 1},
//                {0, 0, 0, 1, 0, 1},
//                {0, 0, 0, 0, 0, 0}
//        };
        int[][] graph = new int[][]{
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 0, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        Task m = new Task();
        System.out.println(m.fordFulkersonA(graph, 0, 5));
        System.out.println("-------------------");
        System.out.println(m.fordFulkersonB(graph, 0, 5));

    }
}
