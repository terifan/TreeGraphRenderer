package org.terifan.treegraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Map.Entry;
import java.util.Set;
import static org.terifan.treegraph.TreeGraph.CHILD_SPACING;
import static org.terifan.treegraph.TreeGraph.COMPACT_LEAFS;
import static org.terifan.treegraph.TreeGraph.FONT;
import static org.terifan.treegraph.TreeGraph.FRC;
import static org.terifan.treegraph.TreeGraph.LINE_STROKE;
import static org.terifan.treegraph.TreeGraph.SIBLING_SPACING;
import static org.terifan.treegraph.TreeGraph.TEXT_PADDING_X;


public class HorizontalLayout extends NodeLayout
{
	@Override
	public void layout(Node aNode)
	{
		if (aNode.isEmpty())
		{
			if (COMPACT_LEAFS)
			{
				layoutVerticalBox(aNode);
			}
			else
			{
				layoutHorizontalBox(aNode);
			}

			mBounds = new Dimension(mWidth, mHeight);
		}
		else
		{
			layoutHorizontalBox(aNode);

			int w = -CHILD_SPACING;
			int h = 0;
			for (int i = 0; i < aNode.size(); i++)
			{
				Object n = aNode.value(i);
				if (n instanceof Node)
				{
					Node _n = (Node)n;
					_n.mLayout = new HorizontalLayout();
					_n.mLayout.layout(_n);
					w += Math.max(measure(_n.mLabel).width, _n.mLayout.mBounds.width) + CHILD_SPACING;
					h = Math.max(_n.mLayout.mBounds.height, h);
				}
			}

			mBounds = new Dimension(Math.max(w, mWidth), h + SIBLING_SPACING + mHeight);
		}
	}


	@Override
	public void render(Node aNode, Graphics2D aGraphics, int aX, int aY)
	{
		int x = aX + (mBounds.width - mWidth) / 2;

		if (!aNode.isEmpty())
		{
			renderHorizontalBox(aGraphics, x, aY, aNode);

			Stroke oldStroke = aGraphics.getStroke();

			boolean b = aNode.size() == aNode.size();
			int t = b ? x + (mWidth - mTextWidth) / 2 : x;
			int s = mWidth / aNode.size();
			int w = s;
			int ch = -CHILD_SPACING;
			for (int i = 0; i < aNode.size(); i++)
			{
				Object n = aNode.value(i);
				if (n instanceof Node)
				{
					Node _n = (Node)n;
					ch += _n.mLayout.mBounds.width + CHILD_SPACING;
				}
			}
			if (ch < mWidth)
			{
				aX += (mWidth - ch) / 2;
			}
			for (int i = 0; i < aNode.size(); i++)
			{
				if (b)
				{
					w = (int)FONT.getStringBounds(aNode.key(i), FRC).getWidth();
					s = w + TEXT_PADDING_X;
				}

				Object n = aNode.value(i);

				if (n instanceof Node)
				{
					Node _n = (Node)n;

					_n.mLayout.render(_n, aGraphics, aX, aY + mHeight + SIBLING_SPACING);

					aGraphics.setColor(Color.LIGHT_GRAY);
					aGraphics.setStroke(LINE_STROKE);
					aGraphics.drawLine(t + w / 2, aY + mHeight + 5, aX + _n.mLayout.mBounds.width / 2, aY + mHeight + SIBLING_SPACING - 5);
					aGraphics.setStroke(oldStroke);
					aGraphics.setColor(Color.RED);

					aX += _n.mLayout.mBounds.width + CHILD_SPACING;
					t += s;
				}
			}
		}
		else if (COMPACT_LEAFS)
		{
			renderVerticalBox(aGraphics, aX, aY, aNode);
		}
		else
		{
			renderHorizontalBox(aGraphics, aX, aY, aNode);
		}
	}
}




