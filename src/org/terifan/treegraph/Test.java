package org.terifan.treegraph;


public class Test
{
	public static void main(String... args)
	{
		try
		{
			TreeFrame frame = new TreeFrame();
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
