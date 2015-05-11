package org.pfe.ontologie;

public class ConceptInformation {
	private String concept;
	private String iri;
	private String label;

	private String conceptEquivalent;
	private String iriEquivalent;
	private String labelEquivalent;

	double score;

	public ConceptInformation(String concept, String label, String iri) {
		super();
		this.concept = concept;
		this.iri = iri;
		this.label = label;
	}

	public String getConcept() {
		return concept;
	}

	public String getIRI() {
		return iri;
	}

	public String getLabel() {
		return label;
	}

	public String getConceptEquivalent() {
		return conceptEquivalent;
	}

	public void setConceptEquivalent(String conceptEquivalent) {
		this.conceptEquivalent = conceptEquivalent;
	}

	public String getIriEquivalent() {
		return iriEquivalent;
	}

	public void setIriEquivalent(String iriEquivalent) {
		this.iriEquivalent = iriEquivalent;
	}

	public String getLabelEquivalent() {
		return labelEquivalent;
	}

	public void setLabelEquivalent(String labelEquivalent) {
		this.labelEquivalent = labelEquivalent;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
