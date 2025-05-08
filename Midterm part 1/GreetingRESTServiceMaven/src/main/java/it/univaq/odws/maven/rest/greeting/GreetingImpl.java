package it.univaq.odws.maven.rest.greeting;

public class GreetingImpl implements Greeting {
	
	@Override
	public String greetingTextPlain(String name) {
		return "<h1>hello "+ name + "</h1>";
	}
	
	@Override
	public String greetingTextHtml(String name) {
		return "<h1>hello "+ name + "</h1>";
	}
	
	@Override
	public String greetingTextXml(String name) {
		String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		return response += "<greeting>hello "+ name + "</greeting>";
	}

}
