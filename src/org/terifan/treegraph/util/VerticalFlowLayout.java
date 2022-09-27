package org.terifan.treegraph.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JComponent;


public class VerticalFlowLayout implements LayoutManager
{
	int vgap;


	public VerticalFlowLayout()
	{
		vgap = 5;
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


	@Override
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
				JComponent c = (JComponent)parent.getComponent(i);
				Dimension d = c.getPreferredSize();
				y += d.height + vgap;
			}
			y = insets.top;
			for (int i = 0; i < n; i++)
			{
				JComponent c = (JComponent)parent.getComponent(i);
				Dimension d = c.getPreferredSize();
				int wid = pd.width - insets.left - insets.right;
				int x = c instanceof TextSlice ? 0 : insets.left+pd.width/2-d.width/2;
				c.setBounds(x, y, wid, d.height);
				y += d.height + vgap;
			}
		}
	}


	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return layoutSize(parent, false);
	}


	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return layoutSize(parent, false);
	}


	@Override
	public void addLayoutComponent(String name, Component comp)
	{
	}


	@Override
	public void removeLayoutComponent(Component comp)
	{
	}
}
