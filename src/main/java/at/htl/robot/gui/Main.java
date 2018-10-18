/*
    Von: Lukas Starka
 */

package at.htl.robot.gui;
import at.htl.robot.model.*;
import processing.core.PApplet;

public class Main extends PApplet {


    private int width = 800;
    private int height = 800;

    private Robot omicron = new Robot();
    private Robot2 theta = new Robot2();
    private static Board board = new Board();

    private char stepForward = 'F';
    private char stepForward2 = 'f';
    private char rotateLeft = 'L';
    private char rotateLeft2 = 'l';
    private char rotateRight = 'Ö';
    private char rotateRight2 = 'ö';
    private char mode = 'M';
    private char mode2 = 'm';
    private char control = 'C';
    private char control2 = 'c';
    private char stepForwardT = '4';
    private char rotateLeftT = '6';
    private char rotateRightT = '7';

    private int xText = 10;
    private int yText = 0;
    private float textSize = 0f;

    private int robotRadius = 0;

    public static void main(String[] args) {
        PApplet.main("at.htl.robot.gui.Main", args);
    }

    public void settings() {
        size(width, height);
    }

    public void setup() {
        background(0);

    }

    public void draw() {
        deleteAll();
        if (omicron.getControl() == Control.FORWARDLEFTRIGHT) {
            movesetForwardLeftRight();
            drawText1();
        } else if (omicron.getControl() == Control.ARROW) {
            movesetArrows();
            drawText2();
        }
        drawField();
        drawFieldBox();
        drawRobots();
    }

    private void deleteAll() {
        background(0);
    }

    private void drawText2() {
        yText = board.getUpperMargin() / 3;
        textSize = Math.min(board.getUpperMargin(), board.getLeftMargin()) / 8f;

        fill(255);
        textSize(textSize);
        text("Omicron Ο and Theta Θ - the little Robots", xText, yText);
        text("Omicron Ο: Steuern durch Pfeiltasten", xText, yText + textSize + 5);
        text("Theta Θ: Steuern durch WASD", xText, yText + (textSize * 2) + 10);
        text("<" + control + "> ... ändere Steuerung <" + mode + "> ... ändere Modus", xText, yText + (textSize * 3) + 15);
    }

    private void drawText1() {

        yText = board.getUpperMargin() / 3;
        textSize = Math.min(board.getUpperMargin(), board.getLeftMargin()) / 8f;

        fill(255);
        textSize(textSize);

        text("Omicron Ο and Theta Θ - the little Robots", xText, yText);
        text("Omicron Ο: <" + stepForward + "> ... 1 Schritt vorwärts, <" + rotateLeft + "> ... Drehe nach links, <" + rotateRight + "> ... Drehe nach rechts", xText, yText + textSize + 5);
        text("Theta Θ: <" + stepForwardT + "> ... 1 Schritt vorwärts <" + rotateLeftT + "> ... Drehe nach Links <" + rotateRightT + "> ... Drehe nach rechts", xText, yText + (textSize * 2) + 10);
        text("<" + control + "> ... ändere Steuerung <" + mode + "> ... ändere Modus", xText, yText + (textSize * 3) + 15);

    }

    private void drawFieldBox() {
        for (int i = 0; i < board.getFieldBox().length; i++) {
            for (int j = 0; j < board.getFieldBox()[i].length; j++) {

                rect(board.getLeftMargin() + (j * board.getBoxLength()), board.getUpperMargin() + (i * board.getBoxLength()), board.getBoxLength(), board.getBoxLength());
            }
        }
    }

    private void drawField() {
        board.setBoardLength( height - (2 * board.getUpperMargin()));

        fill(163, 102, 56);
        rect(board.getLeftMargin(), board.getUpperMargin(), board.getBoardLength(), board.getBoardLength());



        board.setBoxLength(board.getBoardLength() / board.getFieldBox()[0].length);
    }

    public void drawRobots() {

        robotRadius = (int) (board.getBoxLength() * 0.9);

        fill(255, 180, 11);
        ellipse(board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 2), board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() / 2), robotRadius, robotRadius);

        fill(135, 31, 13);
        ellipse(board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 2), board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() / 2), robotRadius, robotRadius);

        if (omicron.getControl() == Control.FORWARDLEFTRIGHT) {
            textSize(robotRadius * 0.3f);
            fill(0);

            if (omicron.getMode() == Mode.TELEPORT) {

                switch (omicron.getDirection()) {
                    case WEST:
                        text("T", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() * 0.1f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case SOUTH:
                        text("T", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 2.2f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() * 0.9f));
                        break;
                    case EAST:
                        text("T", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 1.4f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case NORTH:
                        text("T", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 2.2f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() * 0.3f));
                        break;
                }
                switch (theta.getDirection()) {
                    case WEST:
                        text("T", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() * 0.1f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case SOUTH:
                        text("T", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 2.2f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() * 0.9f));
                        break;
                    case EAST:
                        text("T", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 1.4f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case NORTH:
                        text("T", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 2.2f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() * 0.3f));
                        break;
                }
            }
            else{
                switch (omicron.getDirection()) {
                    case WEST:
                        text("R", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() * 0.1f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case SOUTH:
                        text("R", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength()  / 2.2f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength()  * 0.9f));
                        break;
                    case EAST:
                        text("R", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 1.4f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case NORTH:
                        text("R", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 2.2f),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() * 0.3f));
                        break;
                }
                switch (theta.getDirection()) {
                    case WEST:
                        text("R", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() * 0.1f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case SOUTH:
                        text("R", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength()  / 2.2f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength()  * 0.9f));
                        break;
                    case EAST:
                        text("R", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 1.4f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.8f));
                        break;
                    case NORTH:
                        text("R", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 2.2f),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() * 0.3f));
                        break;
                }
            }
        } else {
            if (omicron.getMode() == Mode.TELEPORT) {
                textSize(robotRadius * 0.7f);
                fill(0);
                text("T", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 3),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.4f));
                text("T", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 3),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.4f));
            }
            else{
                textSize(robotRadius * 0.7f);
                fill(0);
                text("R", board.getLeftMargin() + (omicron.getX() * board.getBoxLength()) + (board.getBoxLength() / 3),board.getUpperMargin() + (omicron.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.4f));
                text("R", board.getLeftMargin() + (theta.getX() * board.getBoxLength()) + (board.getBoxLength() / 3),board.getUpperMargin() + (theta.getY() * board.getBoxLength()) + (board.getBoxLength() / 1.4f));
            }
        }
    }
    public void movesetForwardLeftRight() {
        println("pressed " + key + " " + keyCode);

        if (keyPressed) {
            if ((key == stepForward || key == stepForward2)) {
                omicron.stepForward();
            } else if ((key == rotateLeft || key == rotateLeft2)) {
                omicron.rotateLeft();
            } else if ((key == rotateRight || key == rotateRight2)) {
                omicron.rotateRight();
            } else if ((key == mode) || (key == mode2)) {
                omicron.changeMode();
                theta.changeMode();
            } else if ((key == control) || (key == control2)) {
                omicron.changeControl();
                theta.changeControl();
            }
            else if (key == stepForwardT) {
                theta.stepForward();
            } else if (key == rotateLeftT) {
                theta.rotateLeft();
            } else if (key == rotateRightT) {
                theta.rotateRight();
            }
        }
        key = '%';
    }

    public void movesetArrows() {
        println("pressed " + key + " " + keyCode);

        if (keyPressed) {
            switch (keyCode) {
                case UP:
                    omicron.setDirection(Direction.NORTH);
                    omicron.stepForward();
                    break;
                case RIGHT:
                    omicron.setDirection(Direction.EAST);
                    omicron.stepForward();
                    break;
                case DOWN:
                    omicron.setDirection(Direction.SOUTH);
                    omicron.stepForward();
                    break;
                case LEFT:
                    omicron.setDirection(Direction.WEST);
                    omicron.stepForward();
                    break;
            }
            switch (key) {
                case 'w':
                    theta.setDirection(Direction.NORTH);
                    theta.stepForward();
                    break;
                case 'W':
                    theta.setDirection(Direction.NORTH);
                    theta.stepForward();
                    break;
                case 'd':
                    theta.setDirection(Direction.EAST);
                    theta.stepForward();
                    break;
                case 'D':
                    theta.setDirection(Direction.EAST);
                    theta.stepForward();
                    break;
                case 's':
                    theta.setDirection(Direction.SOUTH);
                    theta.stepForward();
                    break;
                case 'S':
                    theta.setDirection(Direction.SOUTH);
                    theta.stepForward();
                    break;
                case 'a':
                    theta.setDirection(Direction.WEST);
                    theta.stepForward();
                    break;
                case 'A':
                    theta.setDirection(Direction.WEST);
                    theta.stepForward();
                    break;
            }
            if ((key == mode) || (key == mode2)) {
                omicron.changeMode();
                theta.changeMode();
            } else if ((key == control) || (key == control2)) {
                omicron.changeControl();
                theta.changeControl();
            }
        }
        key = '%';
        keyCode = 13;
    }
}
