package org.terifan.treegraph.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class TextSlice extends JLabel
{
//	private final static FontRenderContext FRC = new FontRenderContext(null, true, false);
	private final static Font FONT = new Font("arial", Font.PLAIN, 20);


	public TextSlice(String aText)
	{
		this(aText, new Color(240,240,240), Color.BLACK, 0);
	}


	public TextSlice(String aText, Color aBackground, Color aForeground, int aMargin)
	{
		setOpaque(true);
		setForeground(aForeground);
		setBackground(aBackground);
		setBorder(BorderFactory.createMatteBorder(aMargin, 100, aMargin, 100, aBackground));
		setHorizontalAlignment(CENTER);
		setFont(FONT);
		setText(aText);
	}


//	@Override
//	protected void paintComponent(Graphics aGraphics)
//	{
//		Graphics2D g = (Graphics2D)aGraphics;
//
//		g.setFont(FONT);
//		g.setColor(getForeground());
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		LineMetrics m = FONT.getLineMetrics(mText, FRC);
//		g.drawString(mText, 0, (getHeight() + m.getHeight()) / 2 - m.getDescent());
//		g.dispose();
//	}
//
//
//	private static Dimension measure(String aText, int aMargin)
//	{
//		Rectangle2D bounds = FONT.getStringBounds(aText, FRC);
//		return new Dimension((int)bounds.getWidth(), (int)bounds.getHeight() + aMargin);
//	}
}
