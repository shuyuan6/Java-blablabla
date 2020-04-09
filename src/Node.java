
import java.util.List;

public class Node {
    private Point pt;
    private int distance;
    private List<Point> path;

    public void addPoint(Point p){
        path.add(p);
    }

    public Point getPoint(){
        return pt;
    }

    public List<Point> getPath(){
        return path;
    }

    public int getDistance(){
        return distance;
    }

    public void setPath(List<Point> path){
        this.path = path;
    }

    public Node(Point pt, int distance){
        this.pt = pt;
        this.distance = distance;
    }
}
