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
		WS4JConfiguration.getInstance().setMFS(true);
		double s = 0;

		if (method.equals(HirstStOnge))
			s = new HirstStOnge(db).calcRelatednessOfWords(word1, word2);

		else if (method.equals(LeacockChodorow))
			s = new LeacockChodorow(db).calcRelatednessOfWords(word1, word2);

		/*else if (method.equals(Lesk))
			s = new Lesk(db).calcRelatednessOfWords(word1, word2);*/

		else if (method.equals(WuPalmer))
			s = new WuPalmer(db).calcRelatednessOfWords(word1, word2);

		else if (method.equals(Resnik))
			s = new Resnik(db).calcRelatednessOfWords(word1, word2);

		else if (method.equals(JiangConrath))
			s = new JiangConrath(db).calcRelatednessOfWords(word1, word2);

		else if (method.equals(Lin))
			s = new Lin(db).calcRelatednessOfWords(word1, word2);

		else if (method.equals(Path))
			s = new Path(db).calcRelatednessOfWords(word1, word2);

		return s;
	}

	public double getScoreWords(String concept1, String concept2, String method) {
		return run(concept1, concept2, method);
	}
}
