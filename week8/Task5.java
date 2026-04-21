class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];

        // edge case: no need to fill
        if (original == color) return image;

        dfs(image, sr, sc, original, color);
        return image;
    }

    void dfs(int[][] image, int r, int c, int original, int color) {
        int m = image.length;
        int n = image[0].length;

        // boundary + color check
        if (r < 0 || c < 0 || r >= m || c >= n) return;
        if (image[r][c] != original) return;

        // fill color
        image[r][c] = color;

        // explore 4 directions
        dfs(image, r + 1, c, original, color);
        dfs(image, r - 1, c, original, color);
        dfs(image, r, c + 1, original, color);
        dfs(image, r, c - 1, original, color);
    }
}
