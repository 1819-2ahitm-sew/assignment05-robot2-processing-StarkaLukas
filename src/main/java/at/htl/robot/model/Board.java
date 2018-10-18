package at.htl.robot.model;

public class Board {
    private int upperMargin = 100;
    private int leftMargin = 100;
    private int boxLength = 8;
    private int boardLength = 0;
    private int[][] fieldBox = new int[8][8];

    //region Getter and Setter
    public int getBoardLength() {
        return boardLength;
    }

    public void setBoardLength(int boardLength) {
        this.boardLength = boardLength;
    }

    public int getBoxLength() {
        return boxLength;
    }

    public void setBoxLength(int boxLength) {
        this.boxLength = boxLength;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public int getUpperMargin() {
        return upperMargin;
    }

    public void setUpperMargin(int upperMargin) {
        this.upperMargin = upperMargin;
    }

    public int[][] getFieldBox() {
        return fieldBox;
    }

    public void setFieldBox(int[][] fieldBox) {
        this.fieldBox = fieldBox;
    }
    //endregion
}
