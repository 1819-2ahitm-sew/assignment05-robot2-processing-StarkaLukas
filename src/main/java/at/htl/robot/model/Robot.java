package at.htl.robot.model;

public class Robot {

    private int x = 0;
    private int y = 0;
    private Direction direction = Direction.SOUTH;
    private Mode mode = Mode.TELEPORT;
    private Control control = Control.FORWARDLEFTRIGHT;

    //region Getter and Setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }
    //endregion

    public void stepForward(int[][] fieldBox){
        switch (direction) {
            case SOUTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
            case NORTH:
                y--;
                break;
        }
        if (mode == Mode.TELEPORT) {
            if (x < 0) {
                x = fieldBox[0].length - 1;
            } else if(x == fieldBox[0].length){
                x = 0;
            }
            else if (y < 0) {
                y = fieldBox.length - 1;
            } else if (y == fieldBox.length) {
                y = 0;
            }
        }
        else{
            if (x < 0) {
                x = 0;
            } else if (x == fieldBox[0].length) {
                x = fieldBox[0].length - 1;
            }
            if (y < 0) {
                y = 0;
            } else if (y == fieldBox[0].length) {
                y = fieldBox[0].length - 1;
            }
        }

    }

    public void rotateLeft() {
        switch (direction) {
            case SOUTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
            case NORTH:
                direction = Direction.WEST;
                break;
        }
    }

    public void rotateRight() {
        switch (direction) {
            case SOUTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
            case NORTH:
                direction = Direction.EAST;
                break;
        }
    }

    public void changeMode() {
        if (mode == Mode.TELEPORT) {
            mode = Mode.RESTRICT;
        }
        else{
            mode = Mode.TELEPORT;
        }
    }

    public void changeControl() {
        if (control == Control.FORWARDLEFTRIGHT) {
            control = Control.ARROW;
        }
        else{
            control = Control.FORWARDLEFTRIGHT;
        }
    }
}
