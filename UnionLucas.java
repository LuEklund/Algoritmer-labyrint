import java.util.ArrayList;
import java.util.Random;

public class UnionLucas {
    private int cellsCount;
    private ArrayList<Integer> union;

    UnionLucas(int cellsCount) {
        this.cellsCount = cellsCount;
        union = new ArrayList<Integer>();
        for (int i = 0; i < cellsCount*cellsCount; i++) {
            union.add(-1);
        }
    }

    public Integer getCell(int row, int col) {
        if (col < 0 || col > cellsCount-1 || row < 0 || row > cellsCount-1) {
            return null;
        }
        int val = row*cellsCount + col;
        return val;
    }

    public NeighbourInfo getRandomValidNeighbour(int row, int col) {
        Random random = new Random();
        int x = random.nextInt(4);
        int tries = 0;
        Integer nextCell = null;
        while (nextCell == null && tries < 4) {
            switch (x) {
                case 0:
                    nextCell = getCell(row, col - 1);
                    break;
                case 1:
                    nextCell = getCell(row - 1, col);
                    break;
                case 2:
                    nextCell = getCell(row, col + 1);
                    break;
                case 3:
                    nextCell = getCell(row + 1, col);
                    break;
            }
            if (nextCell != null) return new NeighbourInfo(nextCell, x);
            tries++;
            x++;
            if (x == 4) x = 0;
        }
        return null;
    }


    public boolean joinUnion(Integer current, Integer next) {
        if (current == null || next == null) return false;
        int cur = current;
        int n = next;
        while (union.get(cur) != -1) {cur = union.get(cur);}
        while (union.get(n) != -1) {n = union.get(n);}
        if (cur == n) return false;
        union.set(n, cur);
        return true;
    }
}


