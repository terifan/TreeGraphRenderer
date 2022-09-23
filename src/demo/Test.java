package demo;

import java.awt.Color;
import org.terifan.treegraph.util.VerticalImageFrame;
import org.terifan.treegraph.HorizontalLayout;
import org.terifan.treegraph.TreeRenderer;
import org.terifan.treegraph.VerticalLayout;
import org.terifan.treegraph.util.TextSlice;


public class Test
{
	public static void main(String... args)
	{
		try
		{
			VerticalImageFrame frame = new VerticalImageFrame().setMargin(10);
			frame.add(new TreeRenderer("{root}'A:B:C'#f00#a00#fff['D:E'###0f0,'F:G'#00f,{internal}'H:I'#0ff[{leaf}'J:K'#ff0,'L:M'#f0f]]").render(new VerticalLayout()));
//			frame.add(new TextSlice("test", Color.RED, Color.WHITE, 20));
			frame.add(new TextSlice("testTESTÅÄÖ"));
			frame.add(new TreeRenderer("{root}'*:Jalapeno:Mango'[{first}'*:Dove'['Apple:Banana:Circus','Dove:Ear:Female'],'*:Japanese'['Gloves:Head:Internal','Japanese:Knife:Leap'],'*:Quality'['Mango:Nose:Open','Quality:Rupee']]").render(new HorizontalLayout()));
			frame.add(new TreeRenderer("'*:Jalapeno:Mango'['*:Dove'['Apple:Banana:Circus','Dove:Ear:Female'],'*:Japanese'['Gloves:Head:Internal','Japanese:Knife:Leap'],'*:Open:Quality'['Mango:Nose','Open:Pen','Quality:Rupee']]").render(new VerticalLayout()));
			frame.add(new TreeRenderer("['a','b','c','d']#f00").render(new HorizontalLayout()));
			frame.add(new TreeRenderer("'x'['a','b','c','d']").render(new HorizontalLayout()));
			frame.add(new TreeRenderer("'x:y'[['a','b','c','d'],['a','b','c','d']]").render(new HorizontalLayout()));
		}
		catch (Throwable e)
		{
			e.printStackTrace(System.out);
		}
	}
}
