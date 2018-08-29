package kingery.game.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

	public int MouseX = 0;
	public int MouseY = 0;
	private boolean clicking = false;

	public InputHandler(Engine e) {

		e.addKeyListener(this);
		e.addMouseListener(this);
		e.addMouseMotionListener(this);

	}

	public class Key {

		private boolean isPressed = false;

		public boolean isPressed() {

			return isPressed;

		}

		public void toggle(boolean b) {

			isPressed = b;

		}

	}

	public Key up = new Key();
	public Key left = new Key();
	public Key down = new Key();
	public Key right = new Key();
	public Key shift = new Key();
	public Key r = new Key();
	public Key E = new Key();
	public Key w = new Key();
	public Key a = new Key();
	public Key s = new Key();
	public Key d = new Key();
	public Key esc = new Key();
	public Key space = new Key();
	public Key k1 = new Key();
	public Key k2 = new Key();
	public Key k3 = new Key();

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {

			shift.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_R) {

			r.toggle(true);

		}
		
		if (e.getKeyCode() == KeyEvent.VK_E) {

			E.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			esc.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_1) {

			k1.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_2) {

			k2.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_3) {

			k3.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			space.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_W) {

			w.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_A) {

			a.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_S) {

			s.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_D) {

			d.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {

			up.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			left.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			right.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			down.toggle(true);

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_E) {

			E.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {

			shift.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			esc.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_R) {

			r.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_1) {

			k1.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_2) {

			k2.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_3) {

			k3.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			space.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_W) {

			w.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_A) {

			a.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_S) {

			s.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_D) {

			d.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {

			up.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			left.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			right.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			down.toggle(false);

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		MouseX = e.getX();
		MouseY = e.getY();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		clicking = true;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		clicking = false;

	}
	
	public boolean clicking() {
		return clicking;
	}

}
