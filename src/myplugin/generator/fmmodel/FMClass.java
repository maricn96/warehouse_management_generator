package myplugin.generator.fmmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FMClass extends FMType {

	private String visibility;

	private String tableName;

	private List<FMProperty> properties = new ArrayList<FMProperty>();

	private List<String> importedPackages = new ArrayList<String>();

	private List<FMMethod> methods = new ArrayList<FMMethod>();

	private List<FMReferencedProperty> referencedProperties = new ArrayList<>();
	
	
	public FMClass(String name, String classPackage, String visibility) {
		super(name, classPackage);
		this.visibility = visibility;
	}

	public List<FMProperty> getProperties() {
		return properties;
	}

	public List<FMReferencedProperty> getReferencedProperties() {
		return referencedProperties;
	}
	
	public Iterator<FMProperty> getPropertyIterator() {
		return properties.iterator();
	}

	public Iterator<FMReferencedProperty> getReferencedPropertyIterator() {
		return referencedProperties.iterator();
	}
	
	public void addProperty(FMProperty property) {
		properties.add(property);
	}
	
	public void addReferencedProperty(FMReferencedProperty property) {
		referencedProperties.add(property);
	}

	public int getPropertyCount() {
		return properties.size();
	}
	
	public int getReferencedPropertyCount() {
		return referencedProperties.size();
	}
	
	
	public List<FMMethod> getMethods() {
		return methods;
	}

	public Iterator<FMMethod> getMethodIterator() {
		return methods.iterator();
	}

	public void addMethod(FMMethod method) {
		methods.add(method);
	}

	public int getMethodCount() {
		return methods.size();
	}

	public List<String> getImportedPackages() {
		List<String> importedPackages = new ArrayList<String>();
		
		this.properties.stream().forEach(p -> importedPackages.add(p.getType()));

		this.methods.stream().forEach(m -> {
			m.getParameters().stream().forEach(p -> importedPackages.add(p.getType().getTypePackage()));

			importedPackages.add(m.getReturnType().getTypePackage());
		});

		return importedPackages;
	}

	public Iterator<String> getImportedIterator() {
		return importedPackages.iterator();
	}

	public void addImportedPackage(String importedPackage) {
		importedPackages.add(importedPackage);
	}

	public int getImportedCount() {
		return properties.size();
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
