package yjc.wdb.bbs.bean;

public class Member {
	private String name;
	private int age;
	
	public Member(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public Member(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString(){
		return "name : " + name + ", age : " + age ;
		
	}
}
