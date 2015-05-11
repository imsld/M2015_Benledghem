package org.pfe.ontologie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IndividualDataModel {
	List<List<String>> list;

	public IndividualDataModel(List<List<String>> list) {
		this.list = list;
	}

	public List<IndividualInformation> getData() {
		List<IndividualInformation> individuals = new ArrayList<IndividualInformation>();

		Iterator<List<String>> IterIndividual = list.iterator();
		while (IterIndividual.hasNext()) {
			Iterator<String> Iter = IterIndividual.next().iterator();
			if (Iter.hasNext()) {
				String m = "";
				String s = Iter.next();
				if (Iter.hasNext()) {
					m = Iter.next();
				}
				IndividualInformation ci = new IndividualInformation(s, m);
				individuals.add(ci);
			}
		}
		return individuals;
	}
}
