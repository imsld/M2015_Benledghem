package org.pfe.ontologie;

public class ProprieteInformation {
	private String propriete;
	private String type;

	public ProprieteInformation(String propriete, String type) {
		super();
		this.propriete = propriete;
		this.type = type;
	}

	public String getPropriete() {
		return propriete;
	}

	public String getType() {
		return type;
	}
}
