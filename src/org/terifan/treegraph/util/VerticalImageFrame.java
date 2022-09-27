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


	public VerticalImageFrame()
	{
		mContainer = new JPanel(new VerticalFlowLayout());
		mContainer.setBackground(new Color(240,240,240));
		mContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));

		JScrollPane scrollPane = new JScrollPane(mContainer);
		scrollPane.getVerticalScrollBar().setUnitIncrement(100);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(100);

		mFrame = new JFrame();
		mFrame.setBackground(Color.GREEN);
		mFrame.add(scrollPane);
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
		mContainer.invalidate();
		mContainer.validate();
	}
}