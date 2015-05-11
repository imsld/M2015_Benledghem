package org.pfe.mapping.alignement;

import org.pfe.ontologie.ConceptDataModel;
import org.pfe.ontologie.ConceptInformation;
import org.pfe.ontologie.Ontologie;
import org.pfe.mapping.alignement.semantics.SemanticSimilaritySentences;
import org.pfe.mapping.alignement.semantics.SemanticSimilarityWords;

public class Alignement {
	private String o1_path;
	private String o2_path;
	private String method;

	private Ontologie onto_1;
	private Ontologie onto_2;

	private ConceptDataModel cdm1;
	private ConceptDataModel cdm2;

	private SemanticSimilaritySentences SSS = new SemanticSimilaritySentences();
	private SemanticSimilarityWords SSW = new SemanticSimilarityWords();

	public Alignement(String o1_path, String o2_path, String method) {
		super();
		this.o1_path = o1_path;
		this.o2_path = o2_path;
		this.method = method;
		onto_1 = new Ontologie(o1_path);
		onto_2 = new Ontologie(o2_path);
	}

	public void startAlignement() {
		cdm1 = new ConceptDataModel(onto_1.getConceptDataList());
		cdm2 = new ConceptDataModel(onto_2.getConceptDataList());

		String cpt1, cpt2;
		double score;
		double score1;
		int index_1 ,index_2=-1;
		for (ConceptInformation concept_1 : cdm1.getData()) {
			score = 0;
			score1 = 0;
			index_1= -1;
			cpt1 = concept_1.getLabel();
			for (ConceptInformation concept_2 : cdm2.getData()) {
				index_2=0;
				index_2 ++;
				cpt2 = concept_2.getLabel();
				if (cpt1.contains(" ") || cpt2.contains(" "))
					score = SSS.getScoresentences_2(cpt1, cpt2);
				else
					score = SSW.getScoreWords(cpt1, cpt2, method);

				if (score > score1) {
					score1 = score;
					index_1 = index_2;
				}
			}
			
			if(index_1!=-1){
				concept_1.setConceptEquivalent(cdm2.getData().get(index_1).getConcept());
				concept_1.setIriEquivalent(cdm2.getData().get(index_1).getLabel());
				concept_1.setIriEquivalent(cdm2.getData().get(index_1).getIRI());
				concept_1.setScore(score);
				
				System.out.println ("concept"+ concept_1.getConcept() +"concept equivalent" + concept_1.getConceptEquivalent() + "score" + concept_1.getScore()) ;
			}
			
		}
		
	}
}
