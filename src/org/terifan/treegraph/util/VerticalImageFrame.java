package org.terifan.treegraph.util;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class VerticalImageFrame
{
	private JFrame mFrame;
	private JPanel mContainer;
	private JScrollPane mScrollPane;


	public VerticalImageFrame()
	{
		mContainer = new JPanel(new VerticalFlowLayout());
		mContainer.setBackground(new Color(240,240,240));
		mContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));

		mScrollPane = new JScrollPane(mContainer);
		mScrollPane.getVerticalScrollBar().setUnitIncrement(100);
		mScrollPane.getHorizontalScrollBar().setUnitIncrement(100);

		mFrame = new JFrame();
		mFrame.setBackground(Color.GREEN);
		mFrame.add(mScrollPane);
		mFrame.setSize(1600, 1400);
		mFrame.setLocationRelativeTo(null);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setVisible(true);
	}


	public JFrame getFrame()
	{
		return mFrame;
	}


	public void add(JComponent aComponent)
	{
		mContainer.add(aComponent);
		mContainer.revalidate();
//		mContainer.invalidate();
//		mContainer.validate();
		mContainer.repaint();
//		mFrame.repaint();
//		mScrollPane.validate();
		mScrollPane.repaint();
	}


	public void remove(JComponent aComponent)
	{
		mContainer.remove(aComponent);
//		mContainer.revalidate();
//		mContainer.invalidate();
//		mContainer.validate();
	}
}