/**
 * Literary Jeopardy Applet application
 *
 * @Edwin Young
 * @version 1.00 2008/5/31
 */

import java.applet.Applet;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TextField;

import javax.swing.Timer;


public class LiteraryJeopardy extends Applet implements ActionListener
{
	private Button[] pointCategories;
	private Button[] next;
	private Button[] prev;
	
	private TextField text;
	
	private Timer t;
	
	private String message;
	private String test;
	
	public LiteraryJeopardy()
	{
		final Color Purple = new Color(127, 0, 255);
		
		//Button Assignments
		pointCategories = new Button[30];
		int x = 0;//loop tracker
		int points = 100;//points for the q that button leads to
		for (Button b : pointCategories)
		{
			b = new Button(Integer.toString(points));
			b.setBounds(10 + x * 100, 10 + x * 100, 1000, 1500);
			b.addActionListener(this);
			b.setVisible(true);
			add(b);
			x++;
			if (x > 5)
			{
				x = 0;
				points += 100;
			}
		}
		
		//TextField Assignments
		/*TextField text = new TextField(30);
		text.addActionListener(this);
		text.setBounds(290, 75, 80, 20);
		text.setBackground(Purple);
		add(text);*/
		
		test = "";
		
		class CountDown implements ActionListener
		{
			private int count;
			
			public CountDown(int initialCount)
			{
				count = initialCount;
			}
			public void actionPerformed(ActionEvent event)
			{
				if (count >= 0)
					message = Integer.toString(count);
				if (count < 0)
					message = "TIME'S UP!!!!!";
				repaint();
				count--;
			}
		}
		//CountDown Timer
		CountDown listener = new CountDown(10);
		
		final int DELAY = 1000;
		t = new Timer(DELAY, listener);
		//t.start();
	}
	public void init()
	{
	}

	public void paint(Graphics g)
	{
		//Creation of fonts
		final Font medium = new Font("Serif", Font.PLAIN, 18);
		final Font large = new Font("Serif", Font.BOLD, 36);
		final Font Header = new Font("Serif", Font.BOLD, 50);
		final Font Gigantic = new Font("Serif", Font.BOLD, 80);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//sets font to header
		g2.setFont(Header);
		
		//routine to center text for Literary Jeopardy
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = Header.getStringBounds("Literary Jeopardy!", context);
		
		double xLeft = (getWidth() - bounds.getWidth()) / 2;
		
		g2.drawString("Literary Jeopardy!", (float) xLeft, 50);
		g2.drawString(test, 400, 300);
		
		//routine to center countdown timer
		g2.setFont(Gigantic);
		bounds = Gigantic.getStringBounds(message, context);
		xLeft = (getWidth() - bounds.getWidth()) / 2;
		g2.drawString(message, (float) xLeft, 500);	
		
	}
	public void actionPerformed (ActionEvent e)
	{
		
	}
}