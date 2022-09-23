package org.terifan.treegraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Stroke;
import static org.terifan.treegraph.TreeRenderer.CHILD_SPACING;
import static org.terifan.treegraph.TreeRenderer.COMPACT_LEAFS;
import static org.terifan.treegraph.TreeRenderer.FONT;
import static org.terifan.treegraph.TreeRenderer.FRC;
import static org.terifan.treegraph.TreeRenderer.LINE_STROKE;
import static org.terifan.treegraph.TreeRenderer.SIBLING_SPACING;
import static org.terifan.treegraph.TreeRenderer.TEXT_PADDING_X;


public class HorizontalLayout extends NodeLayout
{
	@Override
	public void layout(Node aNode)
	{
		if (aNode.mChildren == null)
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
			for (Node n : aNode.mChildren)
			{
				n.mLayout = new HorizontalLayout();
				n.mLayout.layout(n);
				w += Math.max(measure(n.mLabel).width, n.mLayout.mBounds.width) + CHILD_SPACING;
				h = Math.max(n.mLayout.mBounds.height, h);
			}

			mBounds = new Dimension(Math.max(w, mWidth), h + SIBLING_SPACING + mHeight);
		}
	}


	@Override
	public void render(Node aNode, Graphics2D aGraphics, int aX, int aY)
	{
		int x = aX + (mBounds.width - mWidth) / 2;

		if (aNode.mChildren != null)
		{
			renderHorizontalBox(aGraphics, x, aY, aNode);

			Stroke oldStroke = aGraphics.getStroke();

			boolean b = aNode.mText.length == aNode.mChildren.size();
			int t = b ? x + (mWidth - mTextWidth) / 2 : x;
			int s = mWidth / aNode.mChildren.size();
			int w = s;
			int ch = -CHILD_SPACING;
			for (int i = 0; i < aNode.mChildren.size(); i++)
			{
				ch += aNode.mChildren.get(i).mLayout.mBounds.width + CHILD_SPACING;
			}
			if (ch < mWidth)
			{
				aX += (mWidth - ch) / 2;
			}
			for (int i = 0; i < aNode.mChildren.size(); i++)
			{
				if (b)
				{
					w = (int)FONT.getStringBounds(aNode.mText[i], FRC).getWidth();
					s = w + TEXT_PADDING_X;
				}

				Node n = aNode.mChildren.get(i);
				n.mLayout.render(n, aGraphics, aX, aY + mHeight + SIBLING_SPACING);

				aGraphics.setColor(Color.LIGHT_GRAY);
				aGraphics.setStroke(LINE_STROKE);
				aGraphics.drawLine(t + w / 2, aY + mHeight + 5, aX + n.mLayout.mBounds.width / 2, aY + mHeight + SIBLING_SPACING - 5);
				aGraphics.setStroke(oldStroke);
				aGraphics.setColor(Color.RED);

				aX += n.mLayout.mBounds.width + CHILD_SPACING;
				t += s;
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




