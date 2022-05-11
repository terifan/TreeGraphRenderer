package demo;

import org.terifan.treegraph.util.VerticalImageFrame;
import org.terifan.treegraph.HorizontalLayout;
import org.terifan.treegraph.TreeRenderer;
import org.terifan.treegraph.VerticalLayout;


public class Test
{
	public static void main(String... args)
	{
		try
		{
			VerticalImageFrame frame = new VerticalImageFrame();
			frame.add(new TreeRenderer("'aa:bb:cc'#f00#a00#fff['dd:ee'###0f0,'ff:gg'#00f,'hh:ii'#0ff['jj:kk'#ff0,'ll:mm'#f0f]]").render(new VerticalLayout()));
			frame.add(new TreeRenderer("'Jalapeno:Quality:>'['Dove:>'['Apple:Banana:Circus','Dove:Ear:Female'],'Japanese:>'['Gloves:Head:Internal','Japanese:Knife:Leap'],'Quality:>'['Mango:Nose:Open','Quality:Rupee']]").render(new HorizontalLayout()));
			frame.add(new TreeRenderer("'Jalapeno:Quality:>'['Dove:>'['Apple:Banana:Circus','Dove:Ear:Female'],'Japanese:>'['Gloves:Head:Internal','Japanese:Knife:Leap'],'Open:Quality:>'['Mango:Nose','Open:Pen','Quality:Rupee']]").render(new VerticalLayout()));
			frame.add(new TreeRenderer("['a','b','c','d']").render(new HorizontalLayout()));
			frame.add(new TreeRenderer("'x'['a','b','c','d']").render(new HorizontalLayout()));
			frame.add(new TreeRenderer("'x:y'[['a','b','c','d'],['a','b','c','d']]").render(new HorizontalLayout()));
		}
		catch (Throwable e)
		{
			e.printStackTrace(System.out);
		}
	}
}
