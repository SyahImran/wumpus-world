/*
Ismat Syah Imran
Mr. Tully
Period 4
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WumpusApplication extends Application {

    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;

    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    private GraphicsContext gc;
    private boolean cheat;
    private boolean killedWumpus;

    Image arrow;
    Image black;
    Image breeze;
    Image deadWumpus;
    Image floor;
    Image gold;
    Image ladder;
    Image pit;
    Image playerDown;
    Image playerLeft;
    Image playerRight;
    Image playerUp;
    Image stench;
    Image wumpus;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        //load images
        arrow = new Image("File:Images/arrow.gif");
        black = new Image("File:Images/black.gif");
        breeze = new Image("File:Images/breeze.gif");
        deadWumpus = new Image("File:Images/deadWumpus.gif");
        floor = new Image("File:Images/Floor.gif");
        gold = new Image("File:Images/gold.gif");
        ladder = new Image("File:Images/ladder.gif");
        pit = new Image("File:Images/pit.gif");
        playerDown = new Image("File:Images/playerDown.png");
        playerLeft = new Image("File:Images/playerLeft.png");
        playerRight = new Image("File:Images/playerRight.png");
        playerUp = new Image("File:Images/playerUp.png");
        stench = new Image("File:Images/stench.gif");
        wumpus = new Image("File:Images/wumpus.gif");
        Group group = new Group();
        Canvas canvas = new Canvas(600, 700);
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        gc = canvas.getGraphicsContext2D();
        reset(); //creates new game
        canvas.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyTyped(event);
                paint(gc);
                System.out.println(event.getCharacter());
            }
        });
        paint(gc);
        canvas.requestFocus();
        stage.setScene(scene);
        stage.show();
        System.out.println(map.toString());
    }

    public void reset() {
        status = PLAYING;
        map = new WumpusMap();
        player = new WumpusPlayer();
        player.setColPosition(map.getLadderC());
        player.setRowPosition(map.getLadderR());
        cheat = false;
    }

    public void paint(GraphicsContext g) {
        map.getSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
        //border
        g.setFill(Color.GRAY);
        g.fillRect(0,0,600,700);
        //draws all images
        for(int r = 0; r < map.NUM_ROWS; r++) {
            for(int c = 0; c < map.NUM_COLUMNS; c++) {
                if(!cheat && !map.getSquare(r, c).getVisited())
                    g.drawImage(black, c * 50 + 50,r * 50 + 50,50,50);
                else {
                    g.drawImage(floor, c * 50 + 50, r * 50 + 50, 50, 50);
                    if (map.getSquare(r, c).getGold())
                        g.drawImage(gold, c * 50 + 50, r * 50 + 50, 50, 50);
                    else if (map.getSquare(r, c).getLadder())
                        g.drawImage(ladder, c * 50 + 50, r * 50 + 50, 50, 50);
                    else if (map.getSquare(r, c).getPit())
                        g.drawImage(pit, c * 50 + 50, r * 50 + 50, 50, 50);
                    if (map.getSquare(r, c).getWumpus())
                        g.drawImage(wumpus, c * 50 + 50, r * 50 + 50, 50, 50);
                    if (map.getSquare(r, c).getDeadWumpus())
                        g.drawImage(deadWumpus, c * 50 + 50, r * 50 + 50, 50, 50);
                    if (map.getSquare(r, c).getBreeze())
                        g.drawImage(breeze, c * 50 + 50, r * 50 + 50, 50, 50);
                    if (map.getSquare(r, c).getStench())
                        g.drawImage(stench, c * 50 + 50, r * 50 + 50, 50, 50);
                }
            }
        }
        //draws player
        switch (player.getDirection()) {
            case WumpusPlayer.NORTH:
                gc.drawImage(playerUp, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 50, 50, 50);
                break;
            case WumpusPlayer.EAST:
                gc.drawImage(playerRight, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 50, 50, 50);
                break;
            case WumpusPlayer.SOUTH:
                gc.drawImage(playerDown, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 50, 50, 50);
                break;
            case WumpusPlayer.WEST:
                gc.drawImage(playerLeft, player.getColPosition() * 50 + 50, player.getRowPosition() * 50 + 50, 50, 50);
                break;
        }
        //text and inventory
        g.setFill(Color.BLACK);
        g.fillRect(0,600,195,100);
        g.fillRect(200,600,400,100);
        g.setFill(Color.RED);
        g.setFont(new Font("Dialogue Input",25));
        g.fillText("Inventory:",5,625);
        g.fillText("Messages: ",205,625);
        //displays messages
        int pR = player.getRowPosition();
        int pC = player.getColPosition();
        int texts = 0;
        g.setFill(Color.CYAN);
        g.setFont(new Font("Dialogue Input",15));
        if(map.getSquare(pR,pC).getBreeze()) {
            texts++;
            g.fillText("You feel a breeze",205,texts*15 + 630);
        }
        if(map.getSquare(pR,pC).getStench() || map.getSquare(pR, pC).getDeadWumpus()) {
            texts++;
            g.fillText("You smell a stench", 205, texts*15 + 630);
        }
        if(map.getSquare(pR,pC).getGold()) {
            texts++;
            g.fillText("You see a glimmer",205,texts*15 + 630);
        }
        if(map.getSquare(pR,pC).getLadder()) {
            texts++;
            g.fillText("You bump into a ladder",205,texts*15 + 630);
        }
        if(map.getSquare(pR,pC).getPit()) {
            texts++;
            g.fillText("You fell down a pit to your death",205,texts*15 + 630);
        }
        if(map.getSquare(pR,pC).getWumpus()) {
            texts++;
            g.fillText("You are eaten by the Wumpus",205,texts*15 + 630);
        }
        if(killedWumpus) {
            texts++;
            g.fillText("You hear a scream",205,texts*15 + 630);
        }
        if(status == WON) {
            texts++;
            g.fillText("You Win. (N for new game)",205,texts*15 + 630);
        }
        if(status == DEAD) {
            texts++;
            g.fillText("Game Over. (N for new game)",205,texts*15 + 630);
        }
        if(player.getArrow())
            g.drawImage(arrow,5,635);
        if(player.getGold())
            g.drawImage(gold,55,635);
        killedWumpus = false;
    }
    public void keyTyped(KeyEvent e) {
        String s = e.getCharacter().toLowerCase();
        if(status == PLAYING) {
            //controls during the game
            switch (s) {
                case "w": //up
                    player.setDirection(WumpusPlayer.NORTH);
                    if (player.getRowPosition() > 0)
                        player.setRowPosition(player.getRowPosition() - 1);
                    break;
                case "a": //left
                    player.setDirection(WumpusPlayer.WEST);
                    if (player.getColPosition() > 0)
                        player.setColPosition(player.getColPosition() - 1);
                    break;
                case "s": //down
                    player.setDirection(WumpusPlayer.SOUTH);
                    if (player.getRowPosition() < map.NUM_ROWS-1)
                        player.setRowPosition(player.getRowPosition() + 1);
                    break;
                case "d": //right
                    player.setDirection(WumpusPlayer.EAST);
                    if (player.getColPosition() < map.NUM_COLUMNS-1)
                        player.setColPosition(player.getColPosition() + 1);
                    break;
                case "i": //shoot arrow upwards
                    player.setDirection(WumpusPlayer.NORTH);
                    if (player.getArrow() && player.getColPosition() == map.getWumpusC() && player.getRowPosition() > map.getWumpusR()) {
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setWumpus(false);
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setDeadWumpus(true);
                        killedWumpus = true;
                    }
                    player.setArrow(false);
                    break;
                case "j": //shoot arrow left
                    player.setDirection(WumpusPlayer.WEST);
                    if (player.getArrow() && player.getRowPosition() == map.getWumpusR() && player.getColPosition() > map.getWumpusC()) {
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setWumpus(false);
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setDeadWumpus(true);
                        killedWumpus = true;
                    }
                    player.setArrow(false);
                    break;
                case "k": //shoot arrow downwards
                    player.setDirection(WumpusPlayer.SOUTH);
                    if (player.getArrow() && player.getColPosition() == map.getWumpusC() && player.getRowPosition() < map.getWumpusR()) {
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setWumpus(false);
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setDeadWumpus(true);
                        killedWumpus = true;
                    }
                    player.setArrow(false);
                    break;
                case "l": //shoot arrow right
                    player.setDirection(WumpusPlayer.EAST);
                    if (player.getArrow() && player.getRowPosition() == map.getWumpusR() && player.getColPosition() < map.getWumpusC()) {
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setWumpus(false);
                        map.getSquare(map.getWumpusR(), map.getWumpusC()).setDeadWumpus(true);
                        killedWumpus = true;
                    }
                    player.setArrow(false);
                    break;
                case "c": //climbs ladder when the player has gold
                    if (player.getGold() && map.getSquare(player.getRowPosition(), player.getColPosition()).getLadder()) {
                        status = WON;
                    }
                case "p": //picks up gold
                    if (map.getSquare(player.getRowPosition(), player.getColPosition()).getGold()) {
                        player.setGold(true);
                        map.getSquare(player.getRowPosition(), player.getColPosition()).setGold(false);
                    }
                    break;
            }
        }
        else if(s.equals("n")) //creates a new game
            reset();
        if(s.equals("*")) //toggles on/off cheat mode
            cheat = !cheat;
        //kills player when on a pit or wumpus
        if(map.getSquare(player.getRowPosition(), player.getColPosition()).getPit() || map.getSquare(player.getRowPosition(), player.getColPosition()).getWumpus())
            status = DEAD;
    }
}
