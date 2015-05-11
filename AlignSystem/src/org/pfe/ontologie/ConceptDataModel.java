package org.pfe.ontologie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConceptDataModel {
	List<List<String>> list;

	public ConceptDataModel(List<List<String>> list) {
		this.list = list;
	}

	public List<ConceptInformation> getData() {
		List<ConceptInformation> concepts = new ArrayList<ConceptInformation>();

		Iterator<List<String>> IterConcept = list.iterator();
		while (IterConcept.hasNext()) {
			Iterator<String> Iter = IterConcept.next().iterator();
			if (Iter.hasNext()) {
				String m = "";
				String n = "";
				String s = Iter.next();
				if (Iter.hasNext()) {
					m = Iter.next();
					if (Iter.hasNext()) {
						n = Iter.next();
					}
				}
				ConceptInformation ci = new ConceptInformation(s, m, n);
				concepts.add(ci);
			}

		}
		return concepts;
	}
}
