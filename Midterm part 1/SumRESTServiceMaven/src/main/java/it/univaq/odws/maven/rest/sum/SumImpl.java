package it.univaq.odws.maven.rest.sum;

public class SumImpl implements Sum{
	
	@Override
	public int sum(int a, int b) {
		return a+b;
	}
}
