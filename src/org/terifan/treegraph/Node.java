package org.terifan.treegraph;

import java.util.ArrayList;


public class Node
{
	ArrayList<Node> mChildren;
	String[] mText;
	NodeLayout mLayout;


	public Node(String... aText)
	{
		mText = aText;
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
