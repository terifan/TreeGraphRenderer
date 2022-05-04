package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


class DemoFrame
{
	private JFrame mFrame;
	private JPanel mContainer;


	public DemoFrame()
	{
		mContainer = new JPanel(new VerticalFlowLayout());
		JScrollPane scrollPane = new JScrollPane(mContainer);
		scrollPane.getVerticalScrollBar().setUnitIncrement(100);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(100);
		mFrame = new JFrame();
		mFrame.add(scrollPane);
		mFrame.setSize(1600, 1200);
		mFrame.setLocationRelativeTo(null);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setVisible(true);
	}


	public void add(BufferedImage aImage)
	{
		mContainer.add(new JPanel()
		{
			{
				setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			}


			@Override
			protected void paintComponent(Graphics aGraphics)
			{
				aGraphics.setColor(Color.WHITE);
				aGraphics.fillRect(0, 0, getWidth(), getHeight());
				aGraphics.drawImage(aImage, (getWidth() - aImage.getWidth()) / 2, (getHeight() - aImage.getHeight()) / 2, null);
			}


			@Override
			public Dimension getPreferredSize()
			{
				return new Dimension(aImage.getWidth(), aImage.getHeight() + 10);
			}
		});

		mContainer.revalidate();
		mContainer.invalidate();
		mContainer.validate();
	}
}