import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class Maine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m = ModelFactory.createDefaultModel();
		String ns = "http://Exemple.com/test " ;
		Resource r= m.createResource(ns + "r" );
		Property p= m.createProperty(ns + "p");
		r.addProperty(p , "hello world" ,XSDDatatype.XSDstring);
		m.write(System.out ,"turtle");
		
		
		
		
		

	}

}
