package org.pfe.ontologie;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.impl.OntologyImpl;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class Ontologie {
	private Model model;
	private OntModel ontologie;
	List<List<String>> concepts;
	List<List<String>> relations;
	List<List<String>> proprietes;
	List<List<String>> individuals;

	public Ontologie(String path) {
		ontologie = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		ontologie.read(path);

		model = ontologie.getBaseModel();
		setConceptDataList();
		setRelationDataList();
		setProprieteDataList();
		setIndividualsDataList();
	}

	public int getIndividualCout() {
		return individuals.size();
	}

	public int getProprieteCout() {
		return proprietes.size();
	}

	public int getConceptCout() {
		return concepts.size();
	}

	public int getRelationCout() {
		return relations.size();
	}

	public List<List<String>> getIndividualsDataList() {
		return individuals;
	}

	private void setIndividualsDataList() {
		individuals = new ArrayList<List<String>>();
		ExtendedIterator<Individual> instances = ontologie.listIndividuals();
		while (instances.hasNext()) {
			Individual ind = (Individual) instances.next();
			List<String> list = new ArrayList<String>();
			String indName = ind.getLocalName().toString();
			list.add(indName);
			StmtIterator iter = model.listStatements(ind, RDF.type,
					(RDFNode) null);
			Statement stmt = iter.next();
			Resource classe = (Resource) stmt.getObject();
			list.add(classe.getLocalName());
			individuals.add(list);
		}
	}

	public List<List<String>> getProprieteDataList() {
		return proprietes;
	}

	private void setProprieteDataList() {
		proprietes = new ArrayList<List<String>>();

		StmtIterator iter = model.listStatements(null, RDF.type,
				OWL.DatatypeProperty);
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			Resource property = stmt.getSubject();

			if (property != null) {
				if (property.getLocalName() != null) {
					List<String> list = new ArrayList<String>();
					list.add(property.getLocalName());

					StmtIterator iter1 = model.listStatements(property,
							RDFS.domain, (RDFNode) null);
					while (iter1.hasNext()) {
						Statement stmt1 = iter1.nextStatement();
						String domain = stmt1.getLiteral().getString();
						list.add(domain);
						list.add(stmt1.getLiteral().getDatatypeURI());

					}
					iter1 = model.listStatements(property, RDFS.range,
							(RDFNode) null);
					while (iter1.hasNext()) {
						Statement stmt1 = iter1.nextStatement();
						String range = (String) stmt1.getResource()
								.getLocalName();
						list.add(range);

					}
					proprietes.add(list);
				}
			}
		}
	}

	public List<List<String>> getRelationDataList() {
		return relations;
	}

	private void setRelationDataList() {
		relations = new ArrayList<List<String>>();

		StmtIterator iter = model.listStatements(null, RDF.type,
				OWL.ObjectProperty);
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			Resource property = stmt.getSubject();

			if (property != null) {
				if (property.getLocalName() != null) {
					List<String> list = new ArrayList<String>();
					list.add(property.getLocalName());

					StmtIterator iter1 = model.listStatements(property,
							RDFS.domain, (RDFNode) null);
					while (iter1.hasNext()) {
						Statement stmt1 = iter1.nextStatement();
						String domain = (String) stmt1.getResource()
								.getLocalName();
						list.add(domain);

					}
					iter1 = model.listStatements(property, RDFS.range,
							(RDFNode) null);
					while (iter1.hasNext()) {
						Statement stmt1 = iter1.nextStatement();
						String range = (String) stmt1.getResource()
								.getLocalName();
						list.add(range);

					}
					relations.add(list);
				}
			}
		}
	}

	public List<List<String>> getConceptDataList() {
		return concepts;
	}

	private void setConceptDataList() {
		concepts = new ArrayList<List<String>>();

		StmtIterator iter = model.listStatements(null, RDF.type, OWL.Class);
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			Resource classe = stmt.getSubject();

			if (classe != null) {
				if (classe.getLocalName() != null) {
					List<String> list = new ArrayList<String>();
					list.add(classe.getLocalName());

					StmtIterator iter1 = model.listStatements(classe,
							RDFS.label, (RDFNode) null);
					if (iter1.hasNext()) {
						Statement stmt1 = iter1.nextStatement();
						String lab = (String) stmt1.getLiteral().getValue();
						list.add(lab);
					} else
						list.add("");
					list.add(classe.getURI());
					concepts.add(list);
				}
			}
		}
	}

	public String getURI() {
		ExtendedIterator<Ontology> iter = ontologie.listOntologies();
		OntologyImpl onto = (OntologyImpl) iter.next();
		return onto.getURI();
	}

}
