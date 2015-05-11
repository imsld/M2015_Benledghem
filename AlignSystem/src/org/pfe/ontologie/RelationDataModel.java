package org.pfe.ontologie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RelationDataModel {
	List<List<String>> list;

	public RelationDataModel(List<List<String>> list) {
		this.list = list;
	}

	public List<RelationInformation> getData() {
		List<RelationInformation> relations = new ArrayList<RelationInformation>();

		Iterator<List<String>> IterRelation = list.iterator();
		while (IterRelation.hasNext()) {
			Iterator<String> Iter = IterRelation.next().iterator();
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
				RelationInformation ci = new RelationInformation(s, m, n);
				relations.add(ci);
			}
		}
		return relations;
	}
}
