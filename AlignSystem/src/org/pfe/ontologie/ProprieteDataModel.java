package org.pfe.ontologie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProprieteDataModel {
	List<List<String>> list;

	public ProprieteDataModel(List<List<String>> list) {
		this.list = list;
	}

	public List<ProprieteInformation> getData() {
		List<ProprieteInformation> proprietes = new ArrayList<ProprieteInformation>();

		Iterator<List<String>> IterPropriete = list.iterator();
		while (IterPropriete.hasNext()) {
			Iterator<String> Iter = IterPropriete.next().iterator();
			if (Iter.hasNext()) {
				String m = "";
				String s = Iter.next();
				if (Iter.hasNext()) {
					m = Iter.next();
				}
				ProprieteInformation ci = new ProprieteInformation(s, m);
				proprietes.add(ci);
			}

		}
		return proprietes;
	}
}