package org.pfe.ontologie;

public class SimiliratyConceptInformationAlignement {
	private String concept_o1;
	private String concept_o2;
	private String socre_linguistic;
	private String socre_hierarchic;
	private String socre_semantic;
	
	public SimiliratyConceptInformationAlignement(String concept_o1, String concept_o2,
			String socre_linguistic, String socre_hierarchic,
			String socre_semantic) {
		super();
		this.concept_o1 = concept_o1;
		this.concept_o2 = concept_o2;
		this.socre_linguistic = socre_linguistic;
		this.socre_hierarchic = socre_hierarchic;
		this.socre_semantic = socre_semantic;
	}

	public String getConcept_o1() {
		return concept_o1;
	}

	public void setConcept_o1(String concept_o1) {
		this.concept_o1 = concept_o1;
	}

	public String getConcept_o2() {
		return concept_o2;
	}

	public void setConcept_o2(String concept_o2) {
		this.concept_o2 = concept_o2;
	}

	public String getSocre_linguistic() {
		return socre_linguistic;
	}

	public void setSocre_linguistic(String socre_linguistic) {
		this.socre_linguistic = socre_linguistic;
	}

	public String getSocre_hierarchic() {
		return socre_hierarchic;
	}

	public void setSocre_hierarchic(String socre_hierarchic) {
		this.socre_hierarchic = socre_hierarchic;
	}

	public String getSocre_semantic() {
		return socre_semantic;
	}

	public void setSocre_semantic(String socre_semantic) {
		this.socre_semantic = socre_semantic;
	}

}
