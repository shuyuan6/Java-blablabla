import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

    static int rSize = 10;
    static int cSize = 10;
    static boolean [][]visited = new boolean[rSize][cSize];

    static boolean isValid(Node n, int[][]mat){
        return (n.getPoint().getR() >= 0) && (n.getPoint().getR() < rSize) && (n.getPoint().getC() >= 0) && (n.getPoint().getC() < cSize)
                && !visited[n.getPoint().getR()][n.getPoint().getC()] && mat[n.getPoint().getR()][n.getPoint().getC()] == 0;
    }

    public static int findNumberOfIslands(int[][]mat){

        if(mat.length == 0){
            return 0;
        }

        int count = 0;

        for (int i = 0; i <= mat.length-1; i++){
            for (int j = 0; j <= mat[i].length-1; j++){
                if (mat[i][j] == 1){
                    count ++;
                    System.out.println("This is the " + count + " island");
                }
                //search
                visitedLand(mat, i, j);
            }
        }
        return count;
    }

    public static void visitedLand(int[][]mat, int i, int j){
        Queue <Integer> q = new LinkedList<>();
        q.add(i);
        q.add(j);

        while (!q.isEmpty()){
            //获得p开头的值并删掉
            i = q.remove();
            j = q.remove();

            if (mat[i][j] != 1){
                continue;
            }

            //mark visited point as -1
            mat[i][j] = -1;

            if (i - 1 > 0){
                q.add(i - 1);
                q.add(j);
            }

            if (i + 1 < mat.length){
                q.add(i + 1);
                q.add(j);
            }

            if (j - 1 > 0){
                q.add(i);
                q.add(j - 1);
            }

            if (j + 1 < mat[i].length){
                q.add(i);
                q.add(j + 1);
            }
        }


    }

    static Node findPath(int[][] mat, Point source, Point destination){

        if(mat[source.getR()][source.getC()] != 0 || mat[destination.getR()][destination.getC()] != 0){
            //Point p = new Point(0, 0);
            return new Node(null, -1);
        }

        Queue <Node> q = new LinkedList<>();
        Point p = new Point(source.getR(), source.getC());
        int distance = 0;
        Node n = new Node(p, distance);
        n.setPath(new LinkedList<>());

        q.add(n);
        visited[n.getPoint().getR()][n.getPoint().getC()] = true;

        while(!q.isEmpty()){
            Node current = q.remove();
            System.out.println("current = " + current.getPoint().getR() + ", " + current.getPoint().getC());

            if ((current.getPoint().getC() == destination.getC()) && (current.getPoint().getR() == destination.getR())){
                return current;
            }

            //visited[current.pt.r][current.pt.c] = true;

            Point up = new Point(current.getPoint().getR() - 1 , current.getPoint().getC());
            Node u = new Node(up, current.getDistance() + 1);
            u.setPath(new LinkedList<>(current.getPath()));
            u.getPath().add(current.getPoint());

            Point down = new Point(current.getPoint().getR() + 1, current.getPoint().getC());
            Node d = new Node(down, current.getDistance() + 1);
            d.setPath(new LinkedList<>(current.getPath()));
            d.getPath().add(current.getPoint());

            Point left = new Point(current.getPoint().getR(), current.getPoint().getC() - 1);
            Node l = new Node(left, current.getDistance() + 1);
            l.setPath(new LinkedList<>(current.getPath()));
            l.getPath().add(current.getPoint());

            Point right = new Point(current.getPoint().getR(), current.getPoint().getC() + 1);
            Node r = new Node(right, current.getDistance() + 1);
            r.setPath(new LinkedList<>(current.getPath()));
            r.getPath().add(current.getPoint());

            if(isValid(u, mat)){
                q.add(u);
                visited[u.getPoint().getR()][u.getPoint().getC()] = true;
            }
            if(isValid(d, mat)){
                q.add(d);
                visited[d.getPoint().getR()][d.getPoint().getC()] = true;
            }
            if(isValid(l, mat)){
                q.add(l);
                visited[l.getPoint().getR()][l.getPoint().getC()] = true;
            }
            if(isValid(r, mat)){
                q.add(r);
                visited[r.getPoint().getR()][r.getPoint().getC()] = true;
            }

        }
        return  new Node(null, -1);
    }



    public static void main(String[] arg) {
        int[][] mat =  {{0, 1, 1, 0, 0, 0, 1, 1, 0, 0},
                        {0, 0, 0, 0, 1, 0, 1, 1, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 1, 0, 0},
                        {0, 1, 1, 1, 0, 1, 0, 1, 0, 0},
                        {0, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                        {0, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                        {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 1, 1, 1, 0, 1, 0, 0, 0, 0},
                        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] mat1 = {{0, 0, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 0}
                        };
        //Point source = new Point(0, 0);
        //Point destination = new Point(9, 9);
        // int distance = 18;
        int count = findNumberOfIslands(mat1);
        //Node actualDistance = findPath(mat, source, destination);
        //System.out.println("Actual Distance = " + actualDistance.getDistance());
        //System.out.println("The path is " + actualDistance.getPath());
        System.out.println("There are " + count + " islands");
        /*
        if(distance == actualDistance.getDistance()){
            System.out.println("Correct, the path is " + actualDistance.getPath());
        }else{
            System.out.println("Wrong");
        }*/
    }

}
