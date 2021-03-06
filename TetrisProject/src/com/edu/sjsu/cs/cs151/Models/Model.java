package com.edu.sjsu.cs.cs151.Models;

import java.awt.*;
import java.util.Random;

/**
 * Model of the game stores all information about the current state of the game
 *
 */
public class Model
{

	/**
	 * A class that stores the coordinates of the grid
	 */
	public class Coordinate
    {
        private int x;
        private int y;

		/**
		 * Constructs the coordinate
		 * @param x  x coordinate (col)
		 * @param y  y coordinate (row)
		 */
		public Coordinate(int x, int y)
        {
            this.x = x;
            this.y = y;

        }

		/**
		 * Get X coordinate
		 * @return x  column
		 */
		public int getX() {
            return x;
        }

		/**
		 * Get the Y coordinate
		 * @return y  row
		 */
		public int getY() { return y; }

		/**
		 * Set the X coordinate
		 * @param x column
		 */
        public void setX(int x) { this.x = x; }

		/**
		 * Set the Y coordinate
		 * @param y  row
		 */
		public void setY(int y) { this.y = y; }

	}

	/**
	 * A class that stores the Tetromino (block)
	 */
	public class Tetromino
	{
	    Coordinate a;
	    Coordinate b;
	    Coordinate c;
	    Coordinate d;
	    Coordinate[] coords;
	    Color color;

		/**
		 * Constructs Tetromino object with four specified coordinates
		 * @param a  first square coordinate
		 * @param b  second square coordinate
		 * @param c  third square coordinate
		 * @param d  fourth square coordinate
		 * @param color  the tetromino color
		 */
		public Tetromino(Coordinate a, Coordinate b, Coordinate c, Coordinate d, Color color)
		{
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
			coords = new Coordinate[]{this.a, this.b, this.c, this.d};
            this.color = color;
		}

		/**
		 * Checks if a specific coordinate is contained in the tetromino
		 * @param x  coordinate x
		 * @param y  coordinate y
		 * @return  boolean  whether it is contained or not
		 */
		public boolean contains(int x, int y) {
			for(Coordinate coord: coords) {
				if(coord.getX() == x && coord.getY() == y) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Rotates specified block 90 degrees clockwise
		 */
		public int[] rotate()
		{
			boolean inBounds = true;
			int[] xChange = new int[4];
			int[] yChange = new int[4];
			int[] result = new int[8];
			int resultCounter = 0;

			for (int m = 0; m < 4; m++)
			{
				int xResult = coords[m].x - coords[1].x;
				int yResult = coords[m].y - coords[1].y;

				int yNew = 0 * yResult + (-1) * xResult;
				int xNew = 1 * yResult + 0 * xResult;

				xChange[m] = xNew - xResult;
				yChange[m] = yNew - yResult;


				if (coords[m].x + xChange[m] >= 0 && coords[m].x + xChange[m] <= 9)
				{
					if (coords[m].y + yChange[m] >= 0 && coords[m].y + yChange[m] <= 19)
					{
						inBounds = true;
					} else
						{
							inBounds = false;
							break;
						}
				} else
					{
						inBounds = false;
						break;
					}

//				coords[m].setX(coords[m].getX() + xChange);
//				coords[m].setY(coords[m].getY() + yChange);

				//int xResult = inputBlock.getCoordinates()[x].getX() - inputBlock.getCoordinates()[1].getX();
			}

			if (inBounds)
			{
				for (int m = 0; m < 4; m++)
				{
					coords[m].x += xChange[m];
					coords[m].y += yChange[m];
					result[resultCounter] = coords[m].x;
					result[resultCounter+1] = coords[m].y;
					resultCounter += 2;
				}
			}

			return result;
		}

		/**
		 * An array of coordinates
		 * @return coords  the array of coordinates
		 */
		public Coordinate[] getCoordinates()
        {
            return coords;
        }

        public void moveTetromino(int addX, int addY) {
			for(Coordinate coord: coords) {

				if (coord.x + addX >= 0 && coord.x + addX <= 9) {
					if (coord.y + addY >= 0 && coord.y + addY <= 19) {
						coord.x += addX;
						coord.y += addY;
					} else
						break;

				}
			}
		}

		/**
		 * Change the color to a string
		 * @return the string
		 */
		@Override
		public String toString() {
			return this.getColor().toString();
		}

		/**
		 * Get the color
		 * @return color
		 */
		public Color getColor() {
			return color;
		}
	}

	/**
	 * A class that generates the next tetromino
	 */
	public class NextTetrominoGenerator
    {
		/**
		 * Generate the tetromino
		 * @param letter  the tetromino to be generated
		 * @return  Tetromino
		 */
        private Tetromino generate(String letter) {
			if (letter.equals("o")) {
				return new Tetromino(new Coordinate(0, 0),
						new Coordinate(1, 0), new Coordinate(0, 1), new Coordinate(1, 1), Color.yellow);
			} else if (letter.equals("i")) {
				return new Tetromino(new Coordinate(0, 0), new Coordinate(1, 0),
						new Coordinate(2, 0), new Coordinate(3, 0), Color.cyan);
			} else if (letter.equals("j")) {
				return new Tetromino(new Coordinate(0, 0), new Coordinate(1, 0),
						new Coordinate(1, 1), new Coordinate(1, 2), Color.blue);
			} else if (letter.equals("l")) {
				return new Tetromino(new Coordinate(0, 1), new Coordinate(1, 1),
						new Coordinate(2, 1), new Coordinate(2, 0), Color.orange);
			} else if (letter.equals("s")) {
				return new Tetromino(new Coordinate(0, 1), new Coordinate(1, 1),
						new Coordinate(1, 0), new Coordinate(2, 0), Color.green);
			} else if (letter.equals("z")) {
				return new Tetromino(new Coordinate(0, 0), new Coordinate(1, 0),
						new Coordinate(1, 1), new Coordinate(2, 1), Color.red);
			} else if (letter.equals("k")) {
				return new Tetromino(new Coordinate(0, 1), new Coordinate(1, 0),
						new Coordinate(1, 1), new Coordinate(2, 1), Color.magenta);
			} else
				return null;
		}

        String[] nextTetromino = {"o", "i", "j", "l", "s", "z", "k"};

		/**
		 * Spawn a random tetromino
		 * @return Tetromino
		 */
		public Tetromino generateRandom()
        {
            Random Dice = new Random();
            int n = Dice.nextInt(7);
            return generate(nextTetromino[n]);
        }
    }

	/**
	 * Describes a window that can hold the next Tetris object until user releases
	 *
	 */
	public class HoldBlock
	{
		
	}
	
	/**
	 * Describes a window that displays and tracks the score
	 *
	 */
	public class Score
	{
		
	}
	
	/**
	 * Returns true if round has started, false otherwise
	 * @return true or false
	 */
	public boolean roundStarted()
	{
		return false;
	}
	
	/**
	 * Returns true if round is over, false otherwise
	 * @return true or false
	 */
	public boolean roundOver()
	{
		return false;
	}
	
	/**
	 * Returns true if game has started, false otherwise
	 * @return true or false
	 */
	public boolean gameStarted()
	{
		return false;
	}
	
	/**
	 * Returns true if game is over, false otherwise
	 * @return true or false
	 */
	public boolean gameOver()
	{
		return false;
	}
}
