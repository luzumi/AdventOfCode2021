package Java;

enum Direction {
    up(-1, 0),
    upLeft( -1, -1),
    down(1, 0),
    left(0, -1),
    right(0, 1),
    rightDown(1, 1),
    downRight(1, 1),
    downLeft(1, 1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}