package org.terifan.treegraph.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class TextSlice extends BufferedImage
{
	private final static FontRenderContext FRC = new FontRenderContext(null, true, false);
	private final static Font FONT = new Font("arial", Font.PLAIN, 20);


	public TextSlice(String aText)
	{
		super(measure(aText), 20, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = createGraphics();
//		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawString(aText, 0, getHeight() / 2 + FONT.getLineMetrics(aText, FRC).getDescent());
		g.dispose();
	}


	private static int measure(String aText)
	{
		Rectangle2D bounds = FONT.getStringBounds(aText, FRC);
		return (int)bounds.getWidth();
	}
}
