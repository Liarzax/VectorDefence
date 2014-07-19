package com.TAFE.comp;

public class Weapon {
	
	public int fireRate = 20;
	public int fireCooldown = getFireRate();
	public int dammage = 1;
	
	public String name = "Standard Gun";
	
	public void weapon() {
		
	}
	
	
	
	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public int getFireCooldown() {
		return fireCooldown;
	}

	public void setFireCooldown(int fireCooldown) {
		this.fireCooldown = fireCooldown;
	}

	public int getDammage() {
		return dammage;
	}

	public void setDammage(int dammage) {
		this.dammage = dammage;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
