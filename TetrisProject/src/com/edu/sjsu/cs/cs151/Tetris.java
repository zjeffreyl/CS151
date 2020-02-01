package com.edu.sjsu.cs.cs151;

/**
 * @author Jeffrey Zhang, Zach Tom, Connie Huynh
 */

import com.edu.sjsu.cs.cs151.Controller.Controller;
import com.edu.sjsu.cs.cs151.Models.Model;
import com.edu.sjsu.cs.cs151.Views.Message;
import com.edu.sjsu.cs.cs151.Views.View;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main class that contains main method and starts game
 */
public class Tetris
{
    public static Queue<Message> queue = new LinkedList<Message>(){};
    private static View view;
    private static Model model;
    private static Controller game;

    /**
     * Timer thread that drops tetromino object one unit every second
     */
    public static class DropTimer extends TimerTask {
        @Override
        public void run() {

            if(game.tetrominoDead)
            {
                game.newRound();
            }
            else{
                game.translateTetromino(0, 1, true);
            }
        }
    }

    /**
     * Main method starts game
     * @param args command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        view = new View();
        model = new Model();
        game = new Controller(view, model);
        game.gameOverCleanUp();
        game.mainLoop();
        queue.clear();
    }
}
