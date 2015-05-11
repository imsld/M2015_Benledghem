package org.pfe.ontologie;

public class RelationInformation {
	private String relation;
	private String domain;
	private String range;

	public RelationInformation(String relation, String domain, String range) {
		super();
		this.relation = relation;
		this.domain = domain;
		this.range = range;
	}

	public String getRelation() {
		return relation;
	}

	public String getDomain() {
		return domain;
	}

	public String getRange() {
		return range;
	}

}
