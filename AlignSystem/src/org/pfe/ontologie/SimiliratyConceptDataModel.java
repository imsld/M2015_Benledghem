package org.pfe.ontologie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimiliratyConceptDataModel {

	List<List<String>> list;

	public SimiliratyConceptDataModel(List<List<String>> list) {
		this.list = list;
	}

	public List<SimiliratyConceptInformation> getData() {
		List<SimiliratyConceptInformation> similarityconcepts = new ArrayList<SimiliratyConceptInformation>();

		Iterator<List<String>> Itersimilarityconcept = list.iterator();
		while (Itersimilarityconcept.hasNext()) {
			Iterator<String> Iter = Itersimilarityconcept.next().iterator();
			if (Iter.hasNext()) {
				String c1 = Iter.next();
				String c2 = Iter.next();
				String sl = Iter.next();
				String sh = Iter.next();
				String ss = Iter.next();

				SimiliratyConceptInformation ci = new SimiliratyConceptInformation(
						c1, c2, sl, sh, ss);
				similarityconcepts.add(ci);
			}
		}
		return similarityconcepts;
	}
}
