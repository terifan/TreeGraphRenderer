package org.terifan.treegraph.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class TextSlice extends BufferedImage
{
	private final static FontRenderContext FRC = new FontRenderContext(null, true, false);
	private final static Font FONT = new Font("arial", Font.PLAIN, 20);
	private final Color mBackground;


	public TextSlice(String aText)
	{
		this(aText, new Color(240,240,240), Color.BLACK, 0);
	}


	public TextSlice(String aText, Color aBackground, Color aForeground, int aMargin)
	{
		this(measure(aText, aMargin), aText, aBackground, aForeground);
	}

	public TextSlice(Dimension aDimension, String aText, Color aBackground, Color aForeground)
	{
		super(aDimension.width, aDimension.height, BufferedImage.TYPE_INT_ARGB);

		mBackground = aBackground;

		Graphics2D g = createGraphics();
		g.setFont(FONT);
		g.setColor(aForeground);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		LineMetrics m = FONT.getLineMetrics(aText, FRC);
		g.drawString(aText, 0, (getHeight() + m.getHeight()) / 2 - m.getDescent());
		g.dispose();
	}


	public Color getBackground()
	{
		return mBackground;
	}


	private static Dimension measure(String aText, int aMargin)
	{
		Rectangle2D bounds = FONT.getStringBounds(aText, FRC);
		return new Dimension((int)bounds.getWidth(), (int)bounds.getHeight() + aMargin);
	}
}
