package org.pfe.mapping.alignement;

import org.eclipse.swt.widgets.ProgressBar;
import org.pfe.ontologie.ConceptDataModel;
import org.pfe.ontologie.ConceptInformation;
import org.pfe.ontologie.Ontologie;
import org.pfe.mapping.alignement.semantics.SemanticSimilaritySentences;
import org.pfe.mapping.alignement.semantics.SemanticSimilarityWords;

public class Alignement extends Thread {
	private String o1_path;
	private String o2_path;
	private String methodWord;
	private String methodSentence;

	private Ontologie onto_1;
	private Ontologie onto_2;

	private ConceptDataModel cdm1;
	private ConceptDataModel cdm2;

	private SemanticSimilaritySentences SSS = new SemanticSimilaritySentences();
	private SemanticSimilarityWords SSW = new SemanticSimilarityWords();

	public Alignement(String o1_path, String o2_path, String methodWord,
			String methodSentence) {
		super();
		this.o1_path = o1_path;
		this.o2_path = o2_path;
		this.methodWord = methodWord;
		this.methodSentence = methodSentence;
		onto_1 = new Ontologie(o1_path);
		onto_2 = new Ontologie(o2_path);

	}
	static long totalTime = 0;
	static long t0 = System.currentTimeMillis();
	public void run() {
		startAlignement();
	}

	public void startAlignement() {
		cdm1 = new ConceptDataModel(onto_1.getConceptDataList());
		cdm2 = new ConceptDataModel(onto_2.getConceptDataList());

		String cpt1, cpt2;
		double score;
		double score1;
		int index_1, index_2 = -1;
		for (ConceptInformation concept_1 : cdm1.getData()) {
			score = 0;
			score1 = 0;
			index_1 = -1;
			cpt1 = concept_1.getLabel();
			for (ConceptInformation concept_2 : cdm2.getData()) {
				index_2 = 0;
				index_2++;
				cpt2 = concept_2.getLabel();
				if (cpt1.contains(" ") || cpt2.contains(" "))
					score = SSS.getScoresentences(cpt1, cpt2, methodSentence);
				else
					score = SSW.getScoreWords(cpt1, cpt2, methodWord);

				if (score > score1) {
					score1 = score;
					index_1 = index_2;
				}
			}

			if (index_1 != -1) {
				concept_1.setConceptEquivalent(cdm2.getData().get(index_1)
						.getConcept());
				concept_1.setIriEquivalent(cdm2.getData().get(index_1)
						.getLabel());
				concept_1
						.setIriEquivalent(cdm2.getData().get(index_1).getIRI());
				concept_1.setScore(score);

				System.out.println("concept :" + concept_1.getConcept()
						+ " concept equivalent :"
						+ concept_1.getConceptEquivalent() + " score "
						+ concept_1.getScore());
			}

		}
		long t1 = System.currentTimeMillis();
		System.out.println("Done in " + (t1 - t0));
		//System.out.println(" ******* Finish ****** ");
	}
}
