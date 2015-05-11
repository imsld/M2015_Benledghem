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
	
	public double getScoresentences_1(String concept1, String concept2){
		
			float a = m.getBlockDistance(concept1, concept2);  
			float b = m.getChapmanMatchingSoundex(concept1, concept2);  
			float f = m.getQGramsDistance(concept1, concept2);  
			float g = m.getVectorSpaceAnalysis(concept1, concept2);  
			float h = (float) (Math.pow(b,2)*Math.atan2(b, Math.atan2(Math.cosh(a), f + g)));
			if (h >1){return 1;}
			if (h <0){return 0;}
			return h;
		}

	public double getScoresentences_2(String concept1, String concept2){
		//public float calculateRBFSimilarityFast(String aa, String bb){
			float a = m.getBlockDistance(concept1,concept2);  
			float b = m.getChapmanMatchingSoundex(concept1,concept2);  
			float f = m.getQGramsDistance(concept1,concept2);  
			float g = m.getVectorSpaceAnalysis(concept1,concept2);  
			float h = (float) (Math.pow(b,2)*Math.atan2(b, Math.atan2(Math.cosh(a), f + g)));
			if (h >1){return 1;}
			if (h <0){return 0;}
			return h;		
		
	}
	
	private Instance instanceMaker(String in){
		
		String[]s=in.split(",");
		double[] r = new double[s.length];
		for (int t=0;t<r.length;t++){r[t]=Double.parseDouble(s[t]);}
		
		int sz = r.length-1;

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
	
	
	private float getRBFSemantics(String in){
		in = in+",0";
		try{
			if ((rss == null)){ this.rss = (RBFRegressor) weka.core.SerializationHelper.read("C:/Users/Rafif/workspace/AlignSystem/MODEL/Semantic_RBFR.model");}
		Instance first = instanceMaker(in);

		float classified = (float)rss.classifyInstance(first); 
		if (classified >1){return 1;}
		if (classified <0){return 0;}
		return classified;
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public double getScoresentences_3(String concept1, String concept2){
	//public float calculateRBFSimilarity(String a, String b){
		
		float block = m.getBlockDistance(concept1, concept2); 
		float CMSoundex = m.getChapmanMatchingSoundex(concept1, concept2);  
		float cosine = m.getCosineSimilarity(concept1, concept2); 
		float dice  = m.getDiceSimilarity(concept1, concept2); 
		float jaccard = m.getJaccardSimilarity(concept1, concept2); 
		float QGrams = m.getQGramsDistance(concept1, concept2); 
		float vectorSpace = m.getVectorSpaceAnalysis(concept1, concept2); 
		
		String predict = block+","+CMSoundex+","+cosine+","+dice+","+jaccard+","+QGrams+","+vectorSpace;
		float category1 = getRBFSemantics(predict); //System.out.println(category1);
		 
		return category1;
	}
	
			

}
