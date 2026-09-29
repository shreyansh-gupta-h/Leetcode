class Solution {
    public int makeConnected(int n, int[][] connections) {
        
        if(connections.length < n-1){
            return -1;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] conn : connections){
            adj.get(conn[0]).add(conn[1]);
            adj.get(conn[1]).add(conn[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for(int i=0; i<n; i++){
            if(!visited[i]){
                components++;
                dfs(i, adj, visited);
            }
        }
        return components - 1;
    }
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited){
        visited[node] = true;
        for(int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }
}