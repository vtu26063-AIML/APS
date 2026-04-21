import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // Step 1: build graph
        for (List<String> acc : accounts) {
            String name = acc.get(0);

            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);

                emailToName.put(email, name);

                graph.putIfAbsent(email, new ArrayList<>());

                if (i == 1) continue;

                String prev = acc.get(i - 1);

                graph.get(email).add(prev);
                graph.get(prev).add(email);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        // Step 2: DFS for connected components
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                List<String> list = new ArrayList<>();
                dfs(email, graph, visited, list);

                Collections.sort(list);
                list.add(0, emailToName.get(email));

                result.add(list);
            }
        }

        return result;
    }

    void dfs(String email, Map<String, List<String>> graph,
             Set<String> visited, List<String> list) {

        visited.add(email);
        list.add(email);

        for (String nei : graph.get(email)) {
            if (!visited.contains(nei)) {
                dfs(nei, graph, visited, list);
            }
        }
    }
}
