package org.pfe.mapping.alignement;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.ui.IViewPart;
import org.pfe.ontologie.ConceptDataModel;
import org.pfe.ontologie.ConceptInformation;
import org.pfe.ontologie.Ontologie;
import org.pfe.mapping.alignement.semantics.SemanticSimilaritySentences;
import org.pfe.mapping.alignement.semantics.SemanticSimilarityWords;
import org.pfe.mapping.interfaces.views.ViewAlignement;

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

	private ViewAlignement viewLocal;
	private Display display;
	private ProgressBar progressBar;
	private int total;

	double s;
	
	
	public Alignement(String o1_path, String o2_path, String methodWord,
			String methodSentence, ViewAlignement viewLocal, Display display,
			ProgressBar progressBar) {
		super();
		this.o1_path = o1_path;
		this.o2_path = o2_path;
		this.methodWord = methodWord;
		this.methodSentence = methodSentence;
		this.viewLocal = viewLocal;
		onto_1 = new Ontologie(o1_path);
		onto_2 = new Ontologie(o2_path);

		this.display = display;
		this.progressBar = progressBar;
		this.progressBar.setMinimum(0);
		total = onto_1.getConceptCout() * onto_2.getConceptCout();
		this.progressBar.setMaximum(total);
	}

	public void run() {
		cdm1 = new ConceptDataModel(onto_1.getConceptDataList());
		cdm2 = new ConceptDataModel(onto_2.getConceptDataList());
		String cpt1, cpt2;
		double score, score1;
		
		int index_1 = -1, index_2 = -1;

		for (ConceptInformation concept_1 : cdm1.getData()) {
			cpt1 = concept_1.getConcept();

			index_2 = -1;
			score = 0;
			score1 = 0;

			for (ConceptInformation concept_2 : cdm2.getData()) {
				cpt2 = concept_2.getConcept();
				index_2++;

				if (cpt1.contains(" ") || cpt2.contains(" "))
					score1 = SSS.getScoresentences(cpt1, cpt2, methodSentence);
				else
					score1 = SSW.getScoreWords(cpt1, cpt2, methodWord);

				if (score1 > score) {
					score = score1;
					index_1 = index_2;
				}
				s = score1;
				/*try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}*/
				display.syncExec(new Runnable() {
					public void run() {
						if (progressBar.isDisposed())
							return;
						progressBar.setSelection(progressBar.getSelection() + 1);
						viewLocal.text_1.setText(concept_1.getConcept());
						viewLocal.text_2.setText(concept_2.getConcept());
						viewLocal.text_3.setText(Double.toString(s));
					}
				});

			}
			if (index_1 != -1) {
				concept_1.setConceptEquivalent(cdm2.getData().get(index_1)
						.getConcept());
				concept_1.setIriEquivalent(cdm2.getData().get(index_1)
						.getConcept());
				concept_1
						.setIriEquivalent(cdm2.getData().get(index_1).getIRI());
				concept_1.setScore(score);

				System.out.println("concept :" + concept_1.getConcept()
						+ " concept equivalent :"
						+ concept_1.getConceptEquivalent() + " score "
						+ concept_1.getScore());
			}

		}

	}
}
