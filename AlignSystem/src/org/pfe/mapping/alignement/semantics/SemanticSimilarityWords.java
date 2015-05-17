package org.pfe.mapping.alignement.semantics;

import edu.cmu.lti.lexical_db.ILexicalDatabase;

import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.HirstStOnge;
import edu.cmu.lti.ws4j.impl.JiangConrath;
import edu.cmu.lti.ws4j.impl.LeacockChodorow;
import edu.cmu.lti.ws4j.impl.Lesk;
import edu.cmu.lti.ws4j.impl.Lin;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.impl.Resnik;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;

public class SemanticSimilarityWords {

	public final static String HirstStOnge = "HirstStOnge";
	public final static String LeacockChodorow = "LeacockChodorow";
	public final static String Lesk = "Lesk";
	public final static String WuPalmer = "WuPalmer";
	public final static String Resnik = "Resnik";
	public final static String JiangConrath = "JiangConrath";
	public final static String Lin = "Lin";
	public final static String Path = "Path";
	
	private ILexicalDatabase db = new NictWordNet();

	private double run(String word1, String word2, String method) {
		
		RelatednessCalculator rcs1 = null;
		
		if (method.equals(HirstStOnge))
			rcs1 =  new HirstStOnge(db);
		
		if (method.equals(LeacockChodorow))
			rcs1 =  new LeacockChodorow(db);
		
		if (method.equals(Lesk))
			rcs1 =  new Lesk(db);
		
		if (method.equals(WuPalmer))
			rcs1 =  new WuPalmer(db);
		
		if (method.equals(Resnik))
			rcs1 =  new Resnik(db);
		
		if (method.equals(JiangConrath))
			rcs1 =  new JiangConrath(db);
		
		if (method.equals(Lin))
			rcs1 =  new Lin(db);
		
		if (method.equals(Path))
			rcs1 =  new Path(db);
		
		WS4JConfiguration.getInstance().setMFS(true);
		double s = 0;
			s = rcs1.calcRelatednessOfWords(word1, word2);

			return s;
	}
	
	
	public double getScoreWords(String concept1, String concept2, String method){		  
		return run(concept1,concept2, method); 
	}
}
