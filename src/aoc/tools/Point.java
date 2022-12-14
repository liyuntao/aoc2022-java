package aoc.tools;


public class Point {
    public int x;
    public int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point clone() {
        return new Point(this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point(" + x + "," + y + ')';
    }
}