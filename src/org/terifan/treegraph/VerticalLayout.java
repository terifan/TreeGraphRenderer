package org.terifan.treegraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Stroke;
import static org.terifan.treegraph.TreeGraph.CHILD_SPACING;
import static org.terifan.treegraph.TreeGraph.COMPACT_LEAFS;
import static org.terifan.treegraph.TreeGraph.FONT;
import static org.terifan.treegraph.TreeGraph.FRC;
import static org.terifan.treegraph.TreeGraph.LINE_STROKE;
import static org.terifan.treegraph.TreeGraph.SIBLING_SPACING;
import static org.terifan.treegraph.TreeGraph.TEXT_PADDING_Y;
import static org.terifan.treegraph.TreeGraph.LABEL_HEIGHT;


public class VerticalLayout extends NodeLayout
{
	@Override
	public void layout(Node aNode)
	{
		if (!aNode.isEmpty())
		{
			if (COMPACT_LEAFS)
			{
				layoutHorizontalBox(aNode);
			}
			else
			{
				layoutVerticalBox(aNode);
			}

			mBounds = new Dimension(mWidth, mHeight);
		}
		else
		{
			layoutVerticalBox(aNode);

			int w = 0;
			int h = -CHILD_SPACING;
			for (Object n : aNode.values())
			{
				if (n instanceof Node)
				{
					Node _n = (Node)n;
					_n.mLayout = new VerticalLayout();
					_n.mLayout.layout(_n);
					w = Math.max(_n.mLayout.mBounds.width, w);
					w = Math.max(measure(_n.mLabel).width, w);
					h += _n.mLayout.mBounds.height + CHILD_SPACING;
				}
			}
//				w = Math.max(measure(aNode.mLabel).x, w);

			mBounds = new Dimension(w + SIBLING_SPACING + mWidth, Math.max(h, mHeight));
		}
	}


	@Override
	public void render(Node aNode, Graphics2D aGraphics, int aX, int aY)
	{
		int y = aY + (mBounds.height - mHeight) / 2;

		if (!aNode.isEmpty())
		{
			renderVerticalBox(aGraphics, aX, y, aNode);

			Stroke oldStroke = aGraphics.getStroke();

			boolean b = aNode.size() == aNode.size();
			int t = b ? y + (mHeight - mTextHeight) / 2 : y;
			int s = mHeight / aNode.size();
			int h = s;
			int ch = -CHILD_SPACING;

			if (aNode.mLabel != null && !aNode.mLabel.isEmpty())
			{
				t += LABEL_HEIGHT / 2;
			}

			for (int i = 0; i < aNode.size(); i++)
			{
				Object n = aNode.value(i);
				if (n instanceof Node)
				{
					Node _n = (Node)n;
					ch += _n.mLayout.mBounds.height + CHILD_SPACING;
				}
			}
			if (ch < mHeight)
			{
				aY += (mHeight - ch) / 2;
			}
			for (int i = 0; i < aNode.size(); i++)
			{
				if (b)
				{
					h = (int)FONT.getStringBounds(aNode.key(i), FRC).getHeight();
					s = h + TEXT_PADDING_Y;
				}

				Object n = aNode.value(i);
				if (n instanceof Node)
				{
					Node _n = (Node)n;
					_n.mLayout.render(_n, aGraphics, aX + mWidth + SIBLING_SPACING, aY);

					aGraphics.setColor(Color.LIGHT_GRAY);
					aGraphics.setStroke(LINE_STROKE);
					aGraphics.drawLine(aX + mWidth + 5, t + h / 2, aX + mWidth + SIBLING_SPACING - 5, aY + _n.mLayout.mBounds.height / 2);
					aGraphics.setStroke(oldStroke);

					aY += _n.mLayout.mBounds.height + CHILD_SPACING;
					t += s;
				}
			}
		}
		else if (COMPACT_LEAFS)
		{
			renderHorizontalBox(aGraphics, aX, aY, aNode);
		}
		else
		{
			renderVerticalBox(aGraphics, aX, aY, aNode);
		}
	}
}
