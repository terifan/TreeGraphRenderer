package org.terifan.treegraph;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;


public class VerticalFlowLayout implements LayoutManager
{
	public final static int CENTER = 0;
	public final static int RIGHT = 1;
	public final static int LEFT = 2;
	public final static int BOTH = 3;
	public final static int TOP = 1;
	public final static int BOTTOM = 2;

	int vgap;
	int alignment;
	int anchor;


	public VerticalFlowLayout()
	{
		this.vgap = 5;
		this.alignment = BOTH;
		this.anchor = TOP;
	}


	Dimension layoutSize(Container parent, boolean minimum)
	{
		Dimension dim = new Dimension(0, 0);
		synchronized (parent.getTreeLock())
		{
			int n = parent.getComponentCount();
			for (int i = 0; i < n; i++)
			{
				Component c = parent.getComponent(i);
				if (c.isVisible())
				{
					Dimension d = minimum ? c.getMinimumSize() : c.getPreferredSize();
					dim.width = Math.max(dim.width, d.width);
					dim.height += d.height;
					if (i > 0)
					{
						dim.height += vgap;
					}
				}
			}
		}
		Insets insets = parent.getInsets();
		dim.width += insets.left + insets.right;
		dim.height += insets.top + insets.bottom;
		return dim;
	}


	public void layoutContainer(Container parent)
	{
		Insets insets = parent.getInsets();
		synchronized (parent.getTreeLock())
		{
			int n = parent.getComponentCount();
			Dimension pd = parent.getSize();
			int y = 0;
			for (int i = 0; i < n; i++)
			{
				Dimension d = parent.getComponent(i).getPreferredSize();
				y += d.height + vgap;
			}
			y -= vgap;
			if (anchor == TOP)
			{
				y = insets.top;
			}
			else
			{
				if (anchor == CENTER)
				{
					y = (pd.height - y) / 2;
				}
				else
				{
					y = pd.height - y - insets.bottom;
				}
			}
			for (int i = 0; i < n; i++)
			{
				Component c = parent.getComponent(i);
				Dimension d = c.getPreferredSize();
				int x = insets.left;
				int wid = d.width;
				if (alignment == CENTER)
				{
					x = (pd.width - d.width) / 2;
				}
				else if (alignment == RIGHT)
				{
					x = pd.width - d.width - insets.right;
				}
				else if (alignment == BOTH)
				{
					wid = pd.width - insets.left - insets.right;
				}
				c.setBounds(x, y, wid, d.height);
				y += d.height + vgap;
			}
		}
	}


	public Dimension minimumLayoutSize(Container parent)
	{
		return layoutSize(parent, false);
	}


	public Dimension preferredLayoutSize(Container parent)
	{
		return layoutSize(parent, false);
	}


	public void addLayoutComponent(String name, Component comp)
	{
	}


	public void removeLayoutComponent(Component comp)
	{
	}
}
