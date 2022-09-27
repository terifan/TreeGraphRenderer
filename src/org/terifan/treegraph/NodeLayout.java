package org.terifan.treegraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import static org.terifan.treegraph.TreeRenderer.FONT;
import static org.terifan.treegraph.TreeRenderer.FRC;
import static org.terifan.treegraph.TreeRenderer.LABEL_HEIGHT;
import static org.terifan.treegraph.TreeRenderer.LM;
import static org.terifan.treegraph.TreeRenderer.TEXT_PADDING_X;
import static org.terifan.treegraph.TreeRenderer.TEXT_PADDING_Y;


public abstract class NodeLayout
{
	Dimension mBounds;
	int mWidth;
	int mHeight;
	int mTextWidth;
	int mTextHeight;


	abstract void layout(Node aNode);


	abstract void render(Node aNode, Graphics2D aGraphics, int aX, int aY);


	void renderHorizontalBox(Graphics2D aGraphics, int aX, int aY, Node aNode)
	{
		int h = aNode.mLayout.mHeight;

		if (aNode.mLabel != null && !aNode.mLabel.isEmpty())
		{
			aY += LABEL_HEIGHT;
			h -= LABEL_HEIGHT;

			aGraphics.setColor(Color.BLACK);
			aGraphics.drawString(aNode.mLabel, aX + (aNode.mLayout.mWidth - measure(aNode.mLabel).width) / 2, aY - 5);
		}

		aGraphics.setColor(aNode.mColors[1] != null ? aNode.mColors[1] : Color.WHITE);
		aGraphics.fillRect(aX, aY, aNode.mLayout.mWidth - 1, h - 1);

		aGraphics.setColor(aNode.mColors[0] != null ? aNode.mColors[0] : Color.BLACK);
		aGraphics.drawRect(aX, aY, aNode.mLayout.mWidth - 1, h - 1);

		for (int i = 0, tx = aX + (aNode.mLayout.mWidth - aNode.mLayout.mTextWidth) / 2; i < aNode.mText.length; i++)
		{
			aGraphics.setColor(aNode.mColors[2] != null ? aNode.mColors[2] : Color.BLACK);
			if (aNode.mText[i].equals("*"))
			{
				aGraphics.fillOval(tx, aY + h / 2 - 2, 5, 5);
			}
			else
			{
				aGraphics.drawString(aNode.mText[i], tx, aY + (h + LM.getHeight()) / 2 - aGraphics.getFontMetrics().getDescent());
			}
			if (i > 0)
			{
				aGraphics.setColor(Color.LIGHT_GRAY);
				aGraphics.drawLine(tx - TEXT_PADDING_X / 2, aY + 1, tx - TEXT_PADDING_X / 2, aY + h - 2);
			}
			tx += measure(aNode.mText[i]).width + TEXT_PADDING_X;
		}
	}


	void renderVerticalBox(Graphics2D aGraphics, int aX, int aY, Node aNode)
	{
		int h = aNode.mLayout.mHeight;

		if (aNode.mLabel != null && !aNode.mLabel.isEmpty())
		{
			aY += LABEL_HEIGHT;
			h -= LABEL_HEIGHT;

			aGraphics.setColor(Color.BLACK);
			aGraphics.drawString(aNode.mLabel, aX + (aNode.mLayout.mWidth - measure(aNode.mLabel).width) / 2, aY - 5);
		}

		aGraphics.setColor(aNode.mColors[1] != null ? aNode.mColors[1] : Color.WHITE);
		aGraphics.fillRect(aX, aY, aNode.mLayout.mWidth - 1, h - 1);

		aGraphics.setColor(aNode.mColors[0] != null ? aNode.mColors[0] : Color.BLACK);
		aGraphics.drawRect(aX, aY, aNode.mLayout.mWidth - 1, h - 1);

		for (int i = 0, ty = aY + (h - aNode.mLayout.mTextHeight) / 2; i < aNode.mText.length; i++)
		{
			aGraphics.setColor(aNode.mColors[2] != null ? aNode.mColors[2] : Color.BLACK);
			if (aNode.mText[i].equals("*"))
			{
				aGraphics.fillOval(aX + aNode.mLayout.mWidth / 2 - 2, ty + (int)LM.getHeight() / 2 - 2, 5, 5);
			}
			else
			{
				aGraphics.drawString(aNode.mText[i], aX + (aNode.mLayout.mWidth - aGraphics.getFontMetrics().stringWidth(aNode.mText[i])) / 2, ty + LM.getHeight() - aGraphics.getFontMetrics().getDescent());
			}
			if (i > 0)
			{
				aGraphics.setColor(Color.LIGHT_GRAY);
				aGraphics.drawLine(aX + 1, ty - TEXT_PADDING_Y / 2, aX + aNode.mLayout.mWidth - 2, ty - TEXT_PADDING_Y / 2);
			}
			ty += LM.getHeight() + TEXT_PADDING_Y;
		}
	}


	void layoutHorizontalBox(Node aNode)
	{
		aNode.mLayout.mTextWidth = -TEXT_PADDING_X;
		aNode.mLayout.mTextHeight = 0;
		for (String s : aNode.mText)
		{
			Dimension b = measure(s);
			aNode.mLayout.mTextWidth += b.width + TEXT_PADDING_X;
			aNode.mLayout.mTextHeight = Math.max(b.height, aNode.mLayout.mTextHeight);
		}
		aNode.mLayout.mWidth = TEXT_PADDING_X + aNode.mLayout.mTextWidth + 2;
		aNode.mLayout.mHeight = TEXT_PADDING_Y + aNode.mLayout.mTextHeight;

		aNode.mLayout.mWidth = Math.max(aNode.mLayout.mWidth, measure(aNode.mLabel).width);

		if (aNode.mLabel != null && !aNode.mLabel.isEmpty())
		{
			aNode.mLayout.mHeight += LABEL_HEIGHT;
		}
	}


	void layoutVerticalBox(Node aNode)
	{
		aNode.mLayout.mTextWidth = 0;
		aNode.mLayout.mTextHeight = -TEXT_PADDING_Y;
		for (String s : aNode.mText)
		{
			Dimension b = measure(s);
			aNode.mLayout.mTextWidth = Math.max(b.width, aNode.mLayout.mTextWidth);
			aNode.mLayout.mTextHeight += b.height + TEXT_PADDING_Y;
		}
		aNode.mLayout.mWidth = TEXT_PADDING_X + aNode.mLayout.mTextWidth;
		aNode.mLayout.mHeight = TEXT_PADDING_Y + aNode.mLayout.mTextHeight + 2;

		aNode.mLayout.mWidth = Math.max(aNode.mLayout.mWidth, measure(aNode.mLabel).width);

		if (aNode.mLabel != null && !aNode.mLabel.isEmpty())
		{
			aNode.mLayout.mHeight += LABEL_HEIGHT;
		}
	}


	static Dimension measure(String s)
	{
		if (s == null || s.isEmpty())
		{
			return new Dimension();
		}
		Rectangle2D r = FONT.getStringBounds(s, FRC);
		return new Dimension((int)r.getWidth(), (int)r.getHeight());
	}
}