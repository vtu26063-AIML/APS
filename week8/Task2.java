import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<List<Integer>> red = new ArrayList<>();
        List<List<Integer>> blue = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            red.add(new ArrayList<>());
            blue.add(new ArrayList<>());
        }

        for (int[] e : redEdges) {
            red.get(e[0]).add(e[1]);
        }

        for (int[] e : blueEdges) {
            blue.get(e[0]).add(e[1]);
        }

        int[][] dist = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<int[]> q = new LinkedList<>();

        // start from node 0 with both possibilities
        q.offer(new int[]{0, 0}); // last red
        q.offer(new int[]{0, 1}); // last blue

        dist[0][0] = 0;
        dist[0][1] = 0;

        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int node = curr[0];
                int color = curr[1];

                if (color == 0) {
                    // last was red → go blue
                    for (int nei : blue.get(node)) {
                        if (dist[nei][1] == Integer.MAX_VALUE) {
                            dist[nei][1] = level + 1;
                            q.offer(new int[]{nei, 1});
                        }
                    }
                } else {
                    // last was blue → go red
                    for (int nei : red.get(node)) {
                        if (dist[nei][0] == Integer.MAX_VALUE) {
                            dist[nei][0] = level + 1;
                            q.offer(new int[]{nei, 0});
                        }
                    }
                }
            }

            level++;
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int res = Math.min(dist[i][0], dist[i][1]);
            ans[i] = (res == Integer.MAX_VALUE) ? -1 : res;
        }

        ans[0] = 0;
        return ans;
    }
}
