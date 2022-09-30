package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;


public class SquareObstacle implements Obstacle {

    private final int size;
    private int x, y;
    private Position cur;

    public SquareObstacle(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 5;
    }

    @Override
    public int getBottomLeftX() {
        return this.x;
    }

    @Override
    public int getBottomLeftY() {
        return this.y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean blocksPosition(Position position) {
        if((position.getX() >= x && position.getX() <= x+4) &&
                (position.getY() >= y && position.getY() <= y+4)){
            return true;
        }
        return false;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        if(a.getX() == b.getX() && (a.getX() >= x && a.getX() <= x+size)){
            if((a.getY() < y && b.getY() > y)  || (a.getY() > y && b.getY() < y)){
                return true;
            }
        } else if (a.getY() == b.getY() && (a.getY() >= y && a.getY() <= y+size)){
            if((a.getX() < x && b.getX() > x) || (a.getX() > x && b.getX() < x)){
                return true;
            }
        }
        return false;
    }

}
