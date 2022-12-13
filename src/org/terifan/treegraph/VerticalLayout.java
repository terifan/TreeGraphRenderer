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
		if (aNode.mChildren == null)
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
			for (Node n : aNode.mChildren)
			{
				n.mLayout = new VerticalLayout();
				n.mLayout.layout(n);
				w = Math.max(n.mLayout.mBounds.width, w);
				w = Math.max(measure(n.mLabel).width, w);
				h += n.mLayout.mBounds.height + CHILD_SPACING;
			}
//				w = Math.max(measure(aNode.mLabel).x, w);

			mBounds = new Dimension(w + SIBLING_SPACING + mWidth, Math.max(h, mHeight));
		}
	}


	@Override
	public void render(Node aNode, Graphics2D aGraphics, int aX, int aY)
	{
		int y = aY + (mBounds.height - mHeight) / 2;

		if (aNode.mChildren != null)
		{
			renderVerticalBox(aGraphics, aX, y, aNode);

			Stroke oldStroke = aGraphics.getStroke();

			boolean b = aNode.mText.length == aNode.mChildren.size();
			int t = b ? y + (mHeight - mTextHeight) / 2 : y;
			int s = mHeight / aNode.mChildren.size();
			int h = s;
			int ch = -CHILD_SPACING;

			if (aNode.mLabel != null && !aNode.mLabel.isEmpty())
			{
				t += LABEL_HEIGHT / 2;
			}

			for (int i = 0; i < aNode.mChildren.size(); i++)
			{
				ch += aNode.mChildren.get(i).mLayout.mBounds.height + CHILD_SPACING;
			}
			if (ch < mHeight)
			{
				aY += (mHeight - ch) / 2;
			}
			for (int i = 0; i < aNode.mChildren.size(); i++)
			{
				if (b)
				{
					h = (int)FONT.getStringBounds(aNode.mText[i], FRC).getHeight();
					s = h + TEXT_PADDING_Y;
				}

				Node n = aNode.mChildren.get(i);
				n.mLayout.render(n, aGraphics, aX + mWidth + SIBLING_SPACING, aY);

				aGraphics.setColor(Color.LIGHT_GRAY);
				aGraphics.setStroke(LINE_STROKE);
				aGraphics.drawLine(aX + mWidth + 5, t + h / 2, aX + mWidth + SIBLING_SPACING - 5, aY + n.mLayout.mBounds.height / 2);
				aGraphics.setStroke(oldStroke);

				aY += n.mLayout.mBounds.height + CHILD_SPACING;
				t += s;
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
