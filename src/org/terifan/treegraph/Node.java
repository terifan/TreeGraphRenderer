package org.terifan.treegraph;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;


public class Node<T>
{
	private LinkedHashMap<T, Object> mChildren;
//	String[] mText;
	NodeLayout mLayout;
//	Color[] mColors;
	String mLabel;
	boolean array;


	public Node(String... aText)
	{
//		mText = aText;
//		mColors = new Color[3];
		mChildren = new LinkedHashMap<>();
	}


	public boolean isArray()
	{
		return array;
	}


	public Node put(T aKey, Object aObject)
	{
		mChildren.put(aKey, aObject);
		return this;
	}


	int size()
	{
		return mChildren.size();
	}


	Object value(int aIndex)
	{
		return mChildren.values().toArray()[aIndex];
	}


	String key(int aIndex)
	{
		if (array)return value(aIndex).toString();
		return mChildren.keySet().toArray()[aIndex].toString();
	}


	@Override
	public String toString()
	{
		return mChildren.toString();
	}


	boolean isEmpty()
	{
		return mChildren.isEmpty();
	}


	Iterable<Object> values()
	{
		return mChildren.values();
	}


	Set<T> keys()
	{
		return mChildren.keySet();
	}


	Set<Entry<T, Object>> entrySet()
	{
		return mChildren.entrySet();
	}
}
