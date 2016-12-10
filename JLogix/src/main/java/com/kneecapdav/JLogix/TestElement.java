package com.kneecapdav.JLogix;

import java.util.Random;

import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.element.ElementInfo;
import com.kneecapdav.JLogix.API.meta.Meta;
import com.kneecapdav.JLogix.API.meta.MetaValue;

@ElementInfo(elementID = "TestElement", moduleID = "JLogix")
public class TestElement extends Element {

	public static Random rand = new Random();
	
	public MetaValue<String> name;
	
	public MetaValue<Meta> testMeta1;
	public MetaValue<Meta> testMeta2;
	
	public TestElement(String name, int id) {
		super(id);
		
		this.name.setValue(name);
		
		Meta m1 = new Meta();
		
		m1.add(new MetaValue<Integer>("int1", rand.nextInt(1000)));
		m1.add(new MetaValue<Integer>("int2", rand.nextInt(1000)));
		m1.add(new MetaValue<Integer>("int3", rand.nextInt(1000)));
		
		testMeta1.setValue(m1);
		
		Meta m2 = new Meta();
		
		m2.add(new MetaValue<Integer>("int1", rand.nextInt(1000)));
		m2.add(new MetaValue<Integer>("int2", rand.nextInt(1000)));
		m2.add(new MetaValue<Integer>("int3", rand.nextInt(1000)));
		
		testMeta2.setValue(m2);
	}
	
	public TestElement() {super();}

	@Override
	public void onCreate() {
		this.name = meta.newString("name");
		this.testMeta1 = meta.newMeta("testMeta1");
		this.testMeta2 = meta.newMeta("testMeta2");
	}
	
	@Override
	public void onPlace() {
		this.meta.print();
	}
	
}
