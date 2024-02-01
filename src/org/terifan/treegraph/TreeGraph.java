package org.terifan.treegraph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JComponent;
import static org.terifan.treegraph.TreeGraph.FONT;
import static org.terifan.treegraph.TreeGraph.FRC;


public class TreeGraph extends JComponent
{
	final static BasicStroke LINE_STROKE = new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1f, new float[]
	{
		2f
	}, 0);
	final static Font FONT = new Font("arial", Font.PLAIN, 10);
	final static FontRenderContext FRC = new FontRenderContext(null, true, false);
	final static LineMetrics LM = FONT.getLineMetrics("Adgjy", FRC);
	final static int SIBLING_SPACING = 50;
	final static int CHILD_SPACING = 10;
	final static int TEXT_PADDING_X = 4+0*15;
	final static int TEXT_PADDING_Y = 4+0*11;
	final static int FRAME_PADDING = 20;
	final static boolean COMPACT_LEAFS = true;
	final static int LABEL_HEIGHT = 15;


	private transient Node mRoot;


	public TreeGraph(NodeLayout aNodeLayout, String aInput)
	{
		try
		{
			mRoot = parse(new PushbackReader(new StringReader(aInput)));
			mRoot.mLayout = aNodeLayout;
			mRoot.mLayout.layout(mRoot);
		}
		catch (Exception e)
		{
			throw new IllegalStateException(e);
		}
	}


	private Node parse(PushbackReader aInput) throws IOException
	{
		Node node;
		int c = aInput.read();
		String label = "";
		if (c == '{')
		{
			for (;;)
			{
				c = aInput.read();
				if (c == '}') break;
				label += (char)c;
			}
			c = aInput.read();
		}
		if (c == '\'')
		{
			node = new Node(readWord(aInput).split(":"));
			node.mLabel = label;

			readColors(aInput, node);

			c = aInput.read();
			if (c == '[')
			{
				do
				{
					node.add(parse(aInput));
				}
				while (aInput.read() == ',');
			}
			else
			{
				aInput.unread(c);
			}
		}
		else
		{
			node = new Node();
			node.mLabel = label;

			ArrayList<String> keys = new ArrayList<>();
			do
			{
				if (aInput.read() != '\'')
				{
					break;
				}
				keys.add(readWord(aInput));
			}
			while (aInput.read() == ',');

			readColors(aInput, node);

			node.mText = keys.toArray(String[]::new);
		}

		return node;
	}


	private void readColors(PushbackReader aInput, Node aNode) throws IOException, NumberFormatException
	{
		for (int i = 0; i < 3; i++)
		{
			int c = aInput.read();
			if (c != '#')
			{
				aInput.unread(c);
				break;
			}

			c = aInput.read();
			if (c >= '0' && c <= '9' || c >= 'A' && c <= 'F' || c >= 'a' && c <= 'f')
			{
				int r = 17 * Integer.parseInt("" + (char)c, 16);
				int g = 17 * Integer.parseInt("" + (char)aInput.read(), 16);
				int b = 17 * Integer.parseInt("" + (char)aInput.read(), 16);
				aNode.mColors[i] = new Color(r, g, b);
			}
			else if (c != '#')
			{
				aInput.unread(c);
				break;
			}
		}
	}


	private String readWord(PushbackReader aInput) throws IOException
	{
		StringBuilder s = new StringBuilder();
		for (int c; (c = aInput.read()) != '\'';)
		{
			s.append((char)c);
		}
		return s.toString();
	}


	@Override
	protected void paintComponent(Graphics aGraphics)
	{
		Dimension d = mRoot.mLayout.mBounds;

		Graphics2D g = (Graphics2D)aGraphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g.setFont(FONT);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, d.width + 10, d.height + 10);

		mRoot.mLayout.render(mRoot, g, 5, 5);

		g.dispose();
	}


	@Override
	public Dimension getPreferredSize()
	{
		Dimension d = mRoot.mLayout.mBounds.getSize();
		d.width += 10;
		d.height += 10;
		return d;
	}
}