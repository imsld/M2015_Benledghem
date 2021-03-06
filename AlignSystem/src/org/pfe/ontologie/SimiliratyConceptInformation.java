package org.pfe.ontologie;

public class SimiliratyConceptInformation {
	private String concept_o1;
	private String concept_o2;
	private String socre_linguistic;
	private String socre_hierarchic;
	private String socre_semantic;
	private Boolean isMaxSemantiqueScore;
	private Boolean isMaxLinguistiqueScore;
	private Boolean isMaxHierarchiqueScore;

	public SimiliratyConceptInformation(String concept_o1, String concept_o2,
			String socre_linguistic, String socre_hierarchic,
			String socre_semantic, String isMaxLinguistiqueScore, String isMaxHierarchiqueScore, String isMaxSementiqueScore) {
		super();
		this.concept_o1 = concept_o1;
		this.concept_o2 = concept_o2;
		this.socre_linguistic = socre_linguistic;
		this.socre_hierarchic = socre_hierarchic;
		this.socre_semantic = socre_semantic;
		this.isMaxLinguistiqueScore = (Boolean.parseBoolean(isMaxLinguistiqueScore));
		this.isMaxHierarchiqueScore = (Boolean.parseBoolean(isMaxHierarchiqueScore));
		this.isMaxSemantiqueScore = (Boolean.parseBoolean(isMaxSementiqueScore));
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
	
	public Boolean IsMaxSemantiqueScore() {
		return isMaxSemantiqueScore;
	}

	public void setMaxSemantiqueScore(Boolean isMaxScore) {
		this.isMaxSemantiqueScore = isMaxScore;
	}
	
	public Boolean IsMaxLinguistiqueScore() {
		return isMaxLinguistiqueScore;
	}

	public void setMaxLinguistiqueScore(Boolean isMaxScore) {
		this.isMaxLinguistiqueScore = isMaxScore;
	}
	
	public Boolean IsMaxHierarchiqueScore() {
		return isMaxHierarchiqueScore;
	}

	public void setMaxHierarchiqueScore(Boolean isMaxScore) {
		this.isMaxHierarchiqueScore = isMaxScore;
	}
}

