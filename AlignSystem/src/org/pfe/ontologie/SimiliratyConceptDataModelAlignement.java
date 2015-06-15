package org.pfe.ontologie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimiliratyConceptDataModelAlignement {
	List<List<String>> list;
	boolean linguistique;
	boolean hierarchique;
	boolean semantique;

	public SimiliratyConceptDataModelAlignement(List<List<String>> list,
			boolean linguistique, boolean hierarchique, boolean semantique) {

		this.list = list;
		this.linguistique = linguistique;
		this.hierarchique = hierarchique;
		this.semantique = semantique;
	}

	public List<SimiliratyConceptInformationAlignement> getData() {
		List<SimiliratyConceptInformationAlignement> similarityconcepts = new ArrayList<SimiliratyConceptInformationAlignement>();

		for (int i = list.size() - 1; i > -1; i--) {
			Iterator<String> Iter = list.get(i).iterator();
			if (Iter.hasNext()) {
				String c1 = Iter.next();
				String c2 = Iter.next();
				String sl = Iter.next();
				String sh = Iter.next();
				String ss = Iter.next();
				String isLinguistiqueMax = Iter.next();
				String isHierarchiqueMax = Iter.next();
				String isSemantiqueMax = Iter.next();

				if ((linguistique)
						&& ((Boolean.parseBoolean(isLinguistiqueMax)))) {
					SimiliratyConceptInformationAlignement ci = new SimiliratyConceptInformationAlignement(
							c1, c2, sl, sh, ss);
					similarityconcepts.add(ci);
				}
				
				if ((hierarchique)
						&& ((Boolean.parseBoolean(isHierarchiqueMax)))) {
					SimiliratyConceptInformationAlignement ci = new SimiliratyConceptInformationAlignement(
							c1, c2, sl, sh, ss);
					similarityconcepts.add(ci);
				}
				
				if ((semantique)
						&& ((Boolean.parseBoolean(isSemantiqueMax)))) {
					SimiliratyConceptInformationAlignement ci = new SimiliratyConceptInformationAlignement(
							c1, c2, sl, sh, ss);
					similarityconcepts.add(ci);
				}
			}
		}

		return similarityconcepts;
	}

}
