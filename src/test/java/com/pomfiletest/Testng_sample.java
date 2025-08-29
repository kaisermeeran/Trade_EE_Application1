package com.pomfiletest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testng_sample {
	
	@BeforeMethod
	public void Beforemethod() {
		System.out.println("Before method");
	}
	
	@Test
	public void Testng_sample1() {
		System.out.println("Constructor");
	}
	
	@Test
	public void Testng_sample2() {
		System.out.println("Testng_sample2");
	}
	
	@Test
	public void Testng_sample3() {
		System.out.println("Testng_sample3");
	}

}
