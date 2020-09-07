package com.taskmanager.NameListManager;

public class NameEntity
{

	private final String name;
	private final String surName;

	public NameEntity(String name, String surName) {
		this.name = name;
		this.surName = surName;
	}

	public String getName() {
		return name;
	}

	public String getSurName() {
		return surName;
	}
}
