package kingery.game.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import kingery.dev.EditingWindow;
import kingery.game.entities.Player;
import kingery.game.gfx.SpriteSheet;
import kingery.game.islands.Island;
import kingery.game.islands.Island_Start;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.InGameMenu;
import kingery.game.menu.Menu;
import kingery.game.menu.Settings;
import kingery.ui.InGameUI;
import kingery.ui.RenderOrder;
import kingery.ui.component.GameButton;
import kingery.ui.component.GameWindow;

public class Engine extends Canvas implements Runnable {

	private static final long serialVersionUID = -5198863677834462653L;

	public int tickCount;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public ArrayList<GameButton> buttons = new ArrayList<>();
	public static ArrayList<GameWindow> subwindows = new ArrayList<>();

	public static InputHandler input;

	public static Engine engine;

	public Menu menu;
	EditingWindow ewindow;
	public static InGameMenu inMenu;

	public static Player p;
	public int cameraX = 0;
	public int cameraY = 0;

	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

	public static int WIDTH = 256;
	public static int HEIGHT = 192;
	private static final String NAME = "Tree Town alpha";

	BufferedImage backGround;
	public SpriteSheet spriteSheet;
	public boolean running = false;
	public static JFrame frame = new JFrame();
	static final Dimension gameDimension = new Dimension(WIDTH * Tile.scale, HEIGHT * Tile.scale);
	public static final Dimension reduced = new Dimension(WIDTH / Tile.scale, HEIGHT / Tile.scale);

	public Engine() {

		initIslands();
		input = new InputHandler(this);
		GameState.init();
		menu = new Menu(this);

		frame.setTitle(NAME);
		frame.setSize(gameDimension);
		frame.setPreferredSize(gameDimension);
		frame.setMinimumSize(gameDimension);
		frame.setMaximumSize(gameDimension);
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println(WIDTH + ", " + HEIGHT);
		inMenu = new InGameMenu(this);

		p = GameState.p;
		ewindow = new EditingWindow();
		engine = this;

		Settings.init();

		new Sound().start();

		System.out.println("reduced resolution: " + WIDTH / Tile.scale + ", " + HEIGHT / Tile.scale);

	}

	public void initIslands() {

		Island.Utopia = new Island("res/Islands/Utopia.png");
		Island.Start = new Island_Start();

	}

	boolean canEnterMenu = false;

	private void update() {

		if (menu.canStartGame() && !InGameMenu.inMenu) {

			if (!input.esc.isPressed()) {
				canEnterMenu = true;
			}
			if (canEnterMenu) {
				if (input.esc.isPressed()) {
					InGameMenu.inMenu = true;
					canEnterMenu = false;
				}
			}

			GameState.currentLevel.update();
			GameState.camera.tick();

		} else if (!menu.canStartGame() && !InGameMenu.inMenu) {

			menu.update();

		} else if (menu.canStartGame() && InGameMenu.inMenu) {

			inMenu.update();

		}

	}

	float frames = 0;

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60;
		int ticks = 0;
		float frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double delta2 = 0;

		while (running) {
			long now = System.nanoTime();
			double renderTime = 1000000000D / Settings.frameCap;
			delta += (now - lastTime) / nsPerTick;
			delta2 += (now - lastTime) / renderTime;
			lastTime = now;
			boolean shouldRender = false;
			if (Settings.frameCap == 0) {
				shouldRender = true;
			}
			while (delta >= 1) {
				ticks++;
				tick();
				update();
				delta -= 1;
			}

			while (delta2 >= 1) {
				delta2 -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				render();
				frames++;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				this.frames = frames;
				ticks = 0;
				frames = 0;
			}

		}
	}

	BufferStrategy bs;
	public static Graphics2D g;

	public void render() {

		// Creating graphics object
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}

		g = (Graphics2D) bs.getDrawGraphics();
		g.scale(Tile.scale, Tile.scale);
		// General Rendering
		g.clearRect(0, 0, (WIDTH), (HEIGHT));
		if (menu.canStartGame()) {
			GameState.currentLevel.render(g);
			if (p.inventory.active) {
				p.inventory.render(g);
			}

			//InGameUI.render(g);

		} else if (!menu.canStartGame()) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, (WIDTH), (HEIGHT));
			menu.renderTitle(g);
		}

		if (InGameMenu.inMenu) {
			inMenu.renderMenu(g);
		}
		
		for (GameWindow w : subwindows) {
			w.update(g);
		}

		RenderOrder.render();

		bs.show();
		g.dispose();

	}

	void tick() {

		tickCount++;

	}

	public void start() {

		running = true;
		new Thread(this).start();

	}

	public static void main(String[] args) {
		new Engine().start();
	}

	public static int height() {
		return HEIGHT * Tile.scale;
	}

	public static int width() {
		return WIDTH * Tile.scale;
	}

}