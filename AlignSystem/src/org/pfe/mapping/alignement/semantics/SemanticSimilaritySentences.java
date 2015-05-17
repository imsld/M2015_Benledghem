package org.pfe.mapping.alignement.semantics;

import java.util.ArrayList;

import org.pfe.mapping.alignement.semantics.Metrics;

import weka.classifiers.functions.MultilayerPerceptronCS;
import weka.classifiers.functions.RBFRegressor;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class SemanticSimilaritySentences {

	private Metrics m = new Metrics();
	private RBFRegressor rss = null;

	public final static String FastMethod = "FastMethod";
	// public static String NeuroneNetWork = "NeuroneNetWork";
	public final static String calculateRBFSimilarity = "calculateRBFSimilarity";

	private Instance instanceMaker(String in) {

		String[] s = in.split(",");
		double[] r = new double[s.length];
		for (int t = 0; t < r.length; t++) {
			r[t] = Double.parseDouble(s[t]);
		}

		int sz = r.length - 1;

		ArrayList<Attribute> atts = new ArrayList<Attribute>(sz);

		for (int t = 0; t < sz + 1; t++) {
			atts.add(new Attribute("number" + t, t));
		}

		Instances dataRaw = new Instances("TestInstances", atts, sz);
		dataRaw.add(new DenseInstance(1.0, r));
		Instance first = dataRaw.firstInstance(); //
		int cIdx = dataRaw.numAttributes() - 1;
		dataRaw.setClassIndex(cIdx);

		return first;

	}

	private float getRBFSemantics(String in) {
		in = in + ",0";
		try {
			if ((rss == null)) {
				this.rss = (RBFRegressor) weka.core.SerializationHelper
						.read("C:/Users/Rafif/workspace/AlignSystem/MODEL/Semantic_RBFR.model");
			}
			Instance first = instanceMaker(in);

			float classified = (float) rss.classifyInstance(first);
			if (classified > 1) {
				return 1;
			}
			if (classified < 0) {
				return 0;
			}
			return classified;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public double getScoresentences(String concept1, String concept2,
			String method) {
		float a, b, f, g, h = 0;
		float block, CMSoundex, cosine, dice, jaccard, QGrams, vectorSpace, category1 = 0;
		if (method == FastMethod) {
			a = m.getBlockDistance(concept1, concept2);
			b = m.getChapmanMatchingSoundex(concept1, concept2);
			f = m.getQGramsDistance(concept1, concept2);
			g = m.getVectorSpaceAnalysis(concept1, concept2);
			h = (float) (Math.pow(b, 2) * Math.atan2(b,
					Math.atan2(Math.cosh(a), f + g)));
			if (h > 1) {
				return 1;
			}
			if (h < 0) {
				return 0;
			}

			return h;
		} else if (method == calculateRBFSimilarity) {

			block = m.getBlockDistance(concept1, concept2);
			CMSoundex = m.getChapmanMatchingSoundex(concept1, concept2);
			cosine = m.getCosineSimilarity(concept1, concept2);
			dice = m.getDiceSimilarity(concept1, concept2);
			jaccard = m.getJaccardSimilarity(concept1, concept2);
			QGrams = m.getQGramsDistance(concept1, concept2);
			vectorSpace = m.getVectorSpaceAnalysis(concept1, concept2);

			String predict = block + "," + CMSoundex + "," + cosine + ","
					+ dice + "," + jaccard + "," + QGrams + "," + vectorSpace;
			category1 = getRBFSemantics(predict); // System.out.println(category1);
		}

		return category1;
	}

	/*
	 * public double getScoresentences_2(String concept1, String concept2,String
	 * method){ //public float calculateRBFSimilarityFast(String aa, String bb){
	 * float a = m.getBlockDistance(concept1,concept2); float b =
	 * m.getChapmanMatchingSoundex(concept1,concept2); float f =
	 * m.getQGramsDistance(concept1,concept2); float g =
	 * m.getVectorSpaceAnalysis(concept1,concept2); float h = (float)
	 * (Math.pow(b,2)*Math.atan2(b, Math.atan2(Math.cosh(a), f + g))); if (h
	 * >1){return 1;} if (h <0){return 0;} return h;
	 * 
	 * }
	 */

}
