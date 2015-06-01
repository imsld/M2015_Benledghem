package org.pfe.mapping.alignement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
import org.pfe.mapping.interfaces.views.tables.ViewAlignementResultOnglet;

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
	private List<List<String>> list;
	ViewAlignementResultOnglet ropertiesOnglet;
	double pond_ling, pond_hier;

	double s;

	public Alignement(String o1_path, String o2_path, String methodWord,
			String methodSentence, ViewAlignement viewLocal, Display display,
			ProgressBar progressBar, List<List<String>> list, String pond_ling,
			String pond_hier, ViewAlignementResultOnglet ropertiesOnglet) {

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

		this.list = list;
		this.ropertiesOnglet = ropertiesOnglet;
		this.pond_ling = Double.parseDouble(pond_ling);
		this.pond_hier = Double.parseDouble(pond_hier);
	}

	public void run() {
		cdm1 = new ConceptDataModel(onto_1.getConceptDataList());
		cdm2 = new ConceptDataModel(onto_2.getConceptDataList());
		String cpt1, cpt2;
		double score_ling, score_hier = 0, score_Sem = 0;

		for (ConceptInformation concept_1 : cdm1.getData()) {
			cpt1 = concept_1.getConcept();
			List<List<String>> Concept1_childList = onto_1
					.getChildList(concept_1.getIRI());

			for (ConceptInformation concept_2 : cdm2.getData()) {
				cpt2 = concept_2.getConcept();
				List<List<String>> Concept2_childList = onto_2
						.getChildList(concept_2.getIRI());

				score_ling = getLinguisticScore(cpt1, cpt2);
				score_hier = (score_ling + getHierachicScore(
						Concept1_childList, Concept2_childList)) / 2;
				score_Sem = ((score_ling*pond_ling) + (score_hier*pond_hier));

				s = score_ling;
				list.add(Arrays.asList(concept_1.getConcept(),
						concept_2.getConcept(), Double.toString(score_ling),
						Double.toString(score_hier), Double.toString(score_Sem)));

				display.syncExec(new Runnable() {
					public void run() {
						if (progressBar.isDisposed())
							return;
						progressBar.setSelection(progressBar.getSelection() + 1);
						viewLocal.text_1.setText(concept_1.getConcept());
						viewLocal.text_2.setText(concept_2.getConcept());
						viewLocal.text_3.setText(Double.toString(s));
						ropertiesOnglet.updateOngletResultatInterface();

					}
				});
			}

		}

	}

	private double getHierachicScore(List<List<String>> concept1_childList,
			List<List<String>> concept2_childList) {
		if (concept1_childList.size() == 0 || concept2_childList.size() == 0)
			return 0;
		double val = 0, max = 0, somme = 0;
		for (Iterator iterator = concept1_childList.iterator(); iterator
				.hasNext();) {
			List<String> list = (List<String>) iterator.next();
			String cpt1_Child = list.get(0);
			for (Iterator iterator2 = concept2_childList.iterator(); iterator2
					.hasNext();) {
				List<String> list2 = (List<String>) iterator2.next();
				String cpt2_Child = list2.get(0);
				val = getLinguisticScore(cpt1_Child, cpt2_Child);
				if (val > max)
					max = val;
			}
			somme += max;
			max = 0;
		}
		return somme / (concept1_childList.size());
	}

	private double getLinguisticScore(String cpt1, String cpt2) {
		if (cpt1.equals(cpt2))
			return 1;
		else {
			if (cpt1.equals("Scientific_Event"))
				System.out.println(cpt1 + " " + cpt2);
			cpt1 = cpt1.replace("_", " ");
			cpt2 = cpt2.replace("_", " ");

			if (cpt1.contains(" ") || cpt2.contains(" ")) {
				System.out.println(cpt1 + " " + cpt2);
				return SSS.getScoresentences(cpt1, cpt2, methodSentence);
			} else
				return SSW.getScoreWords(cpt1, cpt2, methodWord);
		}
	}
}
