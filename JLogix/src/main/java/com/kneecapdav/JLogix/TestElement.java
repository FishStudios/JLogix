package com.kneecapdav.JLogix;

import java.util.Random;

import com.kneecapdav.JLogix.API.element.Element;
import com.kneecapdav.JLogix.API.element.ElementInfo;
import com.kneecapdav.JLogix.API.meta.MetaValue;
import com.kneecapdav.JLogix.API.meta.MetaValue.MetaType;

@ElementInfo(elementID = "TestElement", moduleID = "JLogix")
public class TestElement extends Element {

	public static Random rand = new Random();
	
	public MetaValue<String> name;
	public MetaValue<Boolean> bool1;
	public MetaValue<Byte> byte1;
	public MetaValue<Short> short1;
	public MetaValue<Integer> int1;
	public MetaValue<Long> long1;
	public MetaValue<Float> float1;
	public MetaValue<Double> double1;
	
	public TestElement(String name, int id) {
		super(id);
		
		this.name.setValue(name);
		this.bool1.setValue(rand.nextBoolean());
		
		byte[] bytes = new byte[1];
		rand.nextBytes(bytes);
		this.byte1.setValue(bytes[0]);
		
		this.short1.setValue((short) rand.nextInt(100));
		
		this.int1.setValue(rand.nextInt(100));
		
		this.long1.setValue(rand.nextLong());
		
		this.float1.setValue(rand.nextFloat());
		
		this.double1.setValue(rand.nextDouble());
	}
	
	public TestElement() {super();}

	@Override
	public void onCreate() {
		this.name = meta.<String>addDefault(MetaType.STRING, "name");
		this.bool1 = meta.<Boolean>addDefault(MetaType.BOOLEAN, "bool1");
		this.byte1 = meta.<Byte>addDefault(MetaType.BYTE, "byte1");
		this.short1 = meta.<Short>addDefault(MetaType.SHORT, "short1");
		this.int1 = meta.<Integer>addDefault(MetaType.INTEGER, "int1");
		this.long1 = meta.<Long>addDefault(MetaType.LONG, "long1");
		this.float1 = meta.<Float>addDefault(MetaType.FLOAT, "float1");
		this.double1 = meta.<Double>addDefault(MetaType.DOUBLE, "double1");
	}
	
	@Override
	public void onPlace() {
	}
	
}
