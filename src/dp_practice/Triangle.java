package dp_practice;

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> curRow = triangle.get(i), nextRow = triangle.get(i + 1);
            for (int j = 0; j < curRow.size(); j++) {
                curRow.set(j, curRow.get(j) + Math.min(nextRow.get(j), nextRow.get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
