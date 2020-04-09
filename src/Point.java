public class Point {
    private int r;
    private int c;

    public int getR(){
        return r;
    }

    public int getC(){
        return c;
    }

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public boolean equals(Point other) {
        return r == other.r && c == other.c;
    }

    public String toString(){
        return "("+ r + ", " + c +")";
    }
}
