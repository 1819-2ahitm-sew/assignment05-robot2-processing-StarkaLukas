/*
    Von: Lukas Starka
 */

package at.htl.robot.gui;

import at.htl.robot.model.Control;
import at.htl.robot.model.Direction;
import at.htl.robot.model.Mode;
import at.htl.robot.model.Robot;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {


    private int width = 800;
    private int height = 800;
    private int upperMargin = 100;
    private int leftMargin = 100;
    private int fieldHeight = 0;
    private int fieldWidth = 0;
    private float fieldBoxWidth = 0f;
    private float fieldBoxHeight = 0f;
    public static int[][] fieldBox = new int[8][8];
    private Robot robot = new Robot();
    private char stepForward = 'F';
    private char stepForward2 = 'f';
    private char rotateLeft = 'L';
    private char rotateLeft2 = 'l';
    private int xText = 10;
    private int yText = 0;
    private float textSize = 0f;
    private int robotRadius = 0;
    private int x = 0;
    private int y = 0;
    private char rotateRight = 'Ö';
    private char rotateRight2 = 'ö';


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
        if (robot.getControl() == Control.FORWARDLEFTRIGHT) {
            movesetForwardLeftRight();
            drawText1();
        } else if (robot.getControl() == Control.ARROW) {
            movesetArrows();
            drawText2();
        }
        drawField();
        drawFieldBox();


        drawRobot();
    }

    private void deleteAll() {
        background(0);
    }

    private void drawText2() {
        yText = upperMargin / 3;
        textSize = Math.min(upperMargin, leftMargin) / 6f;

        fill(255);
        textSize(textSize);
        text("Omicron - the little Robot", xText, yText);
        text("Steuern durch Pfeiltasten", xText, yText + textSize + 5);
        text("<C> ... Ändern der Steuerung <M> ... ändere Modus", xText, yText + (textSize * 2) + 10);
    }

    private void drawText1() {

        yText = upperMargin / 3;
        textSize = Math.min(upperMargin, leftMargin) / 6f;

        fill(255);
        textSize(textSize);

        text("Omicron - the little Robot", xText, yText);
        text("<" + stepForward + "> ... 1 Schritt vorwärts, <" + rotateLeft + "> ... Drehe nach links, <" + rotateRight + "> ... Drehe nach rechts", xText, yText + textSize + 5);
        text("<C> ... Ändern der Steuerung <M> ... ändere Modus", xText, yText + (textSize * 2) + 10);

    }

    private void drawFieldBox() {
        for (int i = 0; i < fieldBox.length; i++) {
            for (int j = 0; j < fieldBox[i].length; j++) {

                rect(leftMargin + (j * fieldBoxWidth), upperMargin + (i * fieldBoxHeight), fieldBoxWidth, fieldBoxHeight);
            }
        }
    }

    private void drawField() {
        fieldHeight = height - (2 * upperMargin);
        fieldWidth = width - (2 * leftMargin);

        fill(163, 102, 56);
        rect(leftMargin, upperMargin, fieldWidth, fieldHeight);

        fieldBoxHeight = (float) fieldHeight / fieldBox[0].length;
        fieldBoxWidth = (float) fieldWidth / fieldBox.length;
    }

    public void drawRobot() {

        robotRadius = (int) (Math.min(fieldBoxWidth, fieldBoxHeight) * 0.9);

        fill(191, 186, 171);
        ellipse(leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 2), upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight / 2), robotRadius, robotRadius);

        if (robot.getControl() == Control.FORWARDLEFTRIGHT) {
            textSize(robotRadius * 0.3f);
            fill(0);

            if (robot.getMode() == Mode.TELEPORT) {

                switch (robot.getDirection()) {
                    case WEST:
                        text("T", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth * 0.1f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight / 1.8f));
                        break;
                    case SOUTH:
                        text("T", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 2.2f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight * 0.9f));
                        break;
                    case EAST:
                        text("T", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 1.4f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight / 1.8f));
                        break;
                    case NORTH:
                        text("T", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 2.2f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight * 0.3f));
                        break;
                }
            }
            else{
                switch (robot.getDirection()) {
                    case WEST:
                        text("R", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth * 0.1f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight / 1.8f));
                        break;
                    case SOUTH:
                        text("R", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth  / 2.2f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight  * 0.9f));
                        break;
                    case EAST:
                        text("R", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 1.4f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight / 1.8f));
                        break;
                    case NORTH:
                        text("R", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 2.2f),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight * 0.3f));
                        break;
                }
            }
        } else {
            if (robot.getMode() == Mode.TELEPORT) {
                textSize(robotRadius * 0.7f);
                fill(0);
                text("T", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 3),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight / 1.4f));
            }
            else{
                textSize(robotRadius * 0.7f);
                fill(0);
                text("R", leftMargin + (robot.getX() * fieldBoxWidth) + (fieldBoxWidth / 3),upperMargin + (robot.getY() * fieldBoxHeight) + (fieldBoxHeight / 1.4f));
            }
        }
    }

    public void movesetForwardLeftRight() {
        println("pressed " + key + " " + keyCode);

        if (keyPressed) {
            if ((key == stepForward || key == stepForward2)) {
                robot.stepForward(fieldBox);
            } else if ((key == rotateLeft || key == rotateLeft2)) {
                robot.rotateLeft();
            } else if ((key == rotateRight || key == rotateRight2)) {
                robot.rotateRight();
            } else if ((key == 'm') || (key == 'M')) {
                robot.changeMode();
            } else if ((key == 'c') || (key == 'C')) {
                robot.changeControl();
            }
        }
        key = '%';
    }

    public void movesetArrows() {
        println("pressed " + key + " " + keyCode);

        if (keyPressed) {
            switch (keyCode) {
                case UP:
                    robot.setDirection(Direction.NORTH);
                    robot.stepForward(fieldBox);
                    break;
                case RIGHT:
                    robot.setDirection(Direction.EAST);
                    robot.stepForward(fieldBox);
                    break;
                case DOWN:
                    robot.setDirection(Direction.SOUTH);
                    robot.stepForward(fieldBox);
                    break;
                case LEFT:
                    robot.setDirection(Direction.WEST);
                    robot.stepForward(fieldBox);
                    break;
            }
            if ((key == 'm') || (key == 'M')) {
                robot.changeMode();
            } else if ((key == 'c') || (key == 'C')) {
                robot.changeControl();
            }
        }
        key = '%';
        keyCode = 13;
    }
}
