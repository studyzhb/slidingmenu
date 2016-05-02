package com.coolshow.slidingmenu;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
	private int age;
	private String name;
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public static final Parcelable.Creator<Person> CREATOR=new Creator<Person>() {
		
		@Override
		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
		
		@Override
		public Person createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			Person person =new Person();
			person.name=source.readString();
			person.age=source.readInt();
			return null;
		}
	};
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public Person() {
		super();
	}
	
}
