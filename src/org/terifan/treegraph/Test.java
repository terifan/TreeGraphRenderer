package org.terifan.treegraph;

import java.io.StringReader;
import org.terifan.treegraph.util.VerticalImageFrame;


public class Test
{
	public static void main(String... args)
	{
		try
		{
			VerticalImageFrame frame = new VerticalImageFrame();
//			frame.add(new TreeGraph(new VerticalLayout(), "{root}'A:B:C'#f00#a00#fff['D:E'###0f0,'F:G'#00f,{internal}'H:I'#0ff[{leaf}'J:K'#ff0,'L:M'#f0f]]"));
//			frame.add(new TextSlice("Mango juice", Color.RED, Color.WHITE, 20));
//			frame.add(new TreeGraph(new HorizontalLayout(), "{root}'*:Jalapeno:Mango'[{first}'*:Dove'['Apple:Banana:Circus','Dove:Ear:Female'],'*:Japanese'['Gloves:Head:Internal','Japanese:Knife:Leap'],'*:Quality'['Mango:Nose:Open','Quality:Rupee']]"));
//			frame.add(new TreeGraph(new VerticalLayout(), "'*:Jalapeno:Mango'['*:Dove'['Apple:Banana:Circus','Dove:Ear:Female'],'*:Japanese'['Gloves:Head:Internal','Japanese:Knife:Leap'],'*:Open:Quality'['Mango:Nose','Open:Pen','Quality:Rupee']]"));
//			frame.add(new TreeGraph(new HorizontalLayout(), "['a','b','c','d']#f00"));
//			frame.add(new TreeGraph(new HorizontalLayout(), "'x'['a','b','c','d']"));
//			frame.add(new TreeGraph(new HorizontalLayout(), "'x:y'[['a','b','c','d'],['a','b','c','d']]"));
			frame.add(new TreeGraph(new HorizontalLayout(), "{'x':['a','b','c','d'],'y':['a','b','c','d']}"));

			Object x = new JSONDecoder().unmarshal(new StringReader("{'x':['a','b','c','d'],'y':['a','b','c','d']}"));
			System.out.println(x);
		}
		catch (Throwable e)
		{
			e.printStackTrace(System.out);
		}
	}
}
