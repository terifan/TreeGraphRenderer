package org.terifan.treegraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import static org.terifan.treegraph.TreeRenderer.FONT;
import static org.terifan.treegraph.TreeRenderer.FRC;
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
		aGraphics.setColor(Color.BLACK);
		aGraphics.drawRect(aX, aY, aNode.mLayout.mWidth - 1, aNode.mLayout.mHeight - 1);
		for (int i = 0, tx = aX + (aNode.mLayout.mWidth - aNode.mLayout.mTextWidth) / 2; i < aNode.mText.length; i++)
		{
			aGraphics.setColor(Color.BLACK);
			if (aNode.mText[i].equals("*"))
			{
				aGraphics.fillOval(tx, aY + (int)(aNode.mLayout.mHeight + LM.getHeight()) / 2, 5, 5);
			}
			else
			{
				aGraphics.drawString(aNode.mText[i], tx, aY + (aNode.mLayout.mHeight + LM.getHeight()) / 2 - aGraphics.getFontMetrics().getDescent());
			}
			if (i > 0)
			{
				aGraphics.setColor(Color.LIGHT_GRAY);
				aGraphics.drawLine(tx - TEXT_PADDING_X / 2, aY + 1, tx - TEXT_PADDING_X / 2, aY + aNode.mLayout.mHeight - 2);
			}
			tx += FONT.getStringBounds(aNode.mText[i], FRC).getWidth() + TEXT_PADDING_X;
		}
	}


	void renderVerticalBox(Graphics2D aGraphics, int aX, int aY, Node aNode)
	{
		aGraphics.setColor(Color.BLACK);
		aGraphics.drawRect(aX, aY, aNode.mLayout.mWidth - 1, aNode.mLayout.mHeight - 1);
		for (int i = 0, ty = aY + (aNode.mLayout.mHeight - aNode.mLayout.mTextHeight) / 2; i < aNode.mText.length; i++)
		{
			aGraphics.setColor(Color.BLACK);
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
			Rectangle2D b = FONT.getStringBounds(s, FRC);
			aNode.mLayout.mTextWidth += b.getWidth() + TEXT_PADDING_X;
			aNode.mLayout.mTextHeight = Math.max((int)b.getHeight(), aNode.mLayout.mTextHeight);
		}
		aNode.mLayout.mWidth = TEXT_PADDING_X + aNode.mLayout.mTextWidth + 2;
		aNode.mLayout.mHeight = TEXT_PADDING_Y + aNode.mLayout.mTextHeight;
	}


	void layoutVerticalBox(Node aNode)
	{
		aNode.mLayout.mTextWidth = 0;
		aNode.mLayout.mTextHeight = -TEXT_PADDING_Y;
		for (String s : aNode.mText)
		{
			Rectangle2D b = FONT.getStringBounds(s, FRC);
			aNode.mLayout.mTextWidth = Math.max((int)b.getWidth(), aNode.mLayout.mTextWidth);
			aNode.mLayout.mTextHeight += b.getHeight() + TEXT_PADDING_Y;
		}
		aNode.mLayout.mWidth = TEXT_PADDING_X + aNode.mLayout.mTextWidth;
		aNode.mLayout.mHeight = TEXT_PADDING_Y + aNode.mLayout.mTextHeight + 2;
	}
}


