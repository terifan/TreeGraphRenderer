package org.terifan.treegraph;

import java.awt.Color;
import java.util.ArrayList;


public class Node
{
	ArrayList<Node> mChildren;
	String[] mText;
	NodeLayout mLayout;
	Color[] mColors;


	public Node(String... aText)
	{
		mText = aText;
		mColors = new Color[3];
	}


	public Node add(Node aNode)
	{
		if (mChildren == null)
		{
			mChildren = new ArrayList<>();
		}
		mChildren.add(aNode);
		return this;
	}
}
