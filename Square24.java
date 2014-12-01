import java.util.*;

public class Square24 {
    
    final int COLORS = 3;
    final int SIDES = 4;

    int[][] rots;
    
    void init() {
        List<int[]> res = new ArrayList<>();
        int vars = (int) Math.floor(Math.pow(COLORS, SIDES) + 0.5);
        outer:
        for (int i = 0; i < vars; i++) {
            int v = 0;
            int n = i;
            for (int j = 0; j < SIDES; j++) {
                int c = n % COLORS;
                n /= COLORS;
                v <<= COLORS;
                v |= (1 << c);
            }
            int[] cur = enumerateRotations(v);
            for (int[] prev : res) {
                if (prev[0] == cur[0]) {
                    continue outer;
                }
            }
            res.add(cur);
            for (int x : cur) {
                System.out.print(Integer.toString(x, 8) + " ");
            }
            System.out.println();
        }
    }

    int[] enumerateRotations(int v) {
        Set<Integer> set = new TreeSet<>();
        set.add(v);
        for (int i = 1; i < SIDES; i++) {
            v = rotate(v);
            set.add(v);
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (Integer x : set) {
            res[i++] = x;
        }
        return res;
    }

    int rotate(int v) {
        return ((v << COLORS) | (v >> COLORS * (SIDES - 1))) & ((1 << (COLORS * SIDES)) - 1);
    }

    void run() {
        init();
    }

    public static void main(String... args) {
        new Square24().run();
    }

}
