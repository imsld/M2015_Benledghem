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

		for(int i=list.size()-1;i>-1;i--){
		//Iterator<List<String>> Itersimilarityconcept = list.iterator();
		//while (Itersimilarityconcept.hasNext()) {
			Iterator<String> Iter = list.get(i).iterator();//Itersimilarityconcept.next().iterator();
			if (Iter.hasNext()) {
				String c1 = Iter.next();
				String c2 = Iter.next();
				String sl = Iter.next();
				String sh = Iter.next();
				String ss = Iter.next();
				String isLinguistiqueMax = Iter.next();
				String isHierarchiqueMax = Iter.next();
				String isSemantiqueMax = Iter.next();

				SimiliratyConceptInformation ci = new SimiliratyConceptInformation(
						c1, c2, sl, sh, ss, isLinguistiqueMax, isHierarchiqueMax, isSemantiqueMax);
				similarityconcepts.add(ci);
			}
		}
		
		return similarityconcepts;
	}
}
