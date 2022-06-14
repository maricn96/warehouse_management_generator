package myplugin.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMEnumeration;
import myplugin.generator.fmmodel.FMMethod;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMParameter;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.FMReferencedProperty;
import myplugin.generator.fmmodel.FMType;


/** Model Analyzer takes necessary metadata from the MagicDraw model and puts it in 
 * the intermediate data structure (@see myplugin.generator.fmmodel.FMModel) optimized
 * for code generation using freemarker. Model Analyzer now takes metadata only for ejb code 
 * generation

 * @ToDo: Enhance (or completely rewrite) myplugin.generator.fmmodel classes and  
 * Model Analyzer methods in order to support GUI generation. */ 


public class ModelAnalyzer {	
	//root model package
	private Package root;
	
	//java root package for generated code
	private String filePackage;
	
	public ModelAnalyzer(Package root, String filePackage) {
		super();
		this.root = root;
		this.filePackage = filePackage;
	}

	public Package getRoot() {
		return root;
	}
	
	public void prepareModel() throws AnalyzeException {
		FMModel.getInstance().getClasses().clear();
		FMModel.getInstance().getEnumerations().clear();
		processPackage(root, filePackage);
	}
	
	private void processPackage(Package pack, String packageOwner) throws AnalyzeException {
		//Recursive procedure that extracts data from package elements and stores it in the 
		// intermediate data structure
		
		if (pack.getName() == null) throw  
			new AnalyzeException("Packages must have names!");
		
		String packageName = packageOwner;
		if (pack != root) {
			packageName += "." + pack.getName();
		}
		
		if (pack.hasOwnedElement()) {
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Class) {
					Class cl = (Class)ownedElement;
					FMClass fmClass = getClassData(cl, packageName);
					FMModel.getInstance().getClasses().add(fmClass);
				}
				
				if (ownedElement instanceof Enumeration) {
					Enumeration en = (Enumeration)ownedElement;
					FMEnumeration fmEnumeration = getEnumerationData(en, packageName);
					FMModel.getInstance().getEnumerations().add(fmEnumeration);
				}								
			}
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Package) {					
					Package ownedPackage = (Package)ownedElement;
					if (StereotypesHelper.getAppliedStereotypeByString(ownedPackage, "BusinessApp") != null)
						processPackage(ownedPackage, packageName);
				}
			}
		}
	}
	
	private FMClass getClassData(Class cl, String packageName) throws AnalyzeException {
		if (cl.getName() == null) 
			throw new AnalyzeException("Classes must have names!");
		
		String tableName = "";
				
		Stereotype entityStereotype = StereotypesHelper.getAppliedStereotypeByString(cl, "Entity");
		if (entityStereotype != null) {
			tableName = getTagValue(cl, entityStereotype, "tableName");
		}
		
		FMClass fmClass = new FMClass(cl.getName(), packageName, cl.getVisibility().toString() );
		fmClass.setTableName(tableName);
		
		Iterator<Property> it = ModelHelper.attributes(cl);
		while (it.hasNext()) {
			Property p = it.next();
			
			if (p.getOpposite() != null) {
				FMReferencedProperty referencedProperty = getReferencedPropertyData(p, cl);
				fmClass.addReferencedProperty(referencedProperty);
			} else {
				FMProperty prop = getPropertyData(p, cl);
				fmClass.addProperty(prop);
			}
		}
		
		Iterator<Operation> op = ModelHelper.operations(cl);
		while (op.hasNext()) {
			Operation o = op.next();
			FMMethod met = getMethodData(o, cl);
			
			fmClass.addMethod(met);
		}
			
		return fmClass;
	}

	private FMProperty getPropertyData(Property p, Class cl) throws AnalyzeException {
		String attName = p.getName();
		if (attName == null) 
			throw new AnalyzeException("Properties of the class: " + cl.getName() +
					" must have names!");
		Type attType = p.getType();
		if (attType == null)
			throw new AnalyzeException("Property " + cl.getName() + "." +
			p.getName() + " must have type!");
		
		String typeName = attType.getName();
		if (typeName == null)
			throw new AnalyzeException("Type ot the property " + cl.getName() + "." +
			p.getName() + " must have name!");		
			
		int lower = p.getLower();
		int upper = p.getUpper();
		
		FMProperty prop = new FMProperty(attName, typeName, p.getVisibility().toString(), 
				lower, upper);
		return prop;		
	}
	
	private FMReferencedProperty getReferencedPropertyData(Property p, Class cl) throws AnalyzeException {
		Stereotype referencedPropertyStereotype = null;
		
		String cascade = null;
		String fetchType = null;
		String mappedBy = null;
		String joinTable = null;
		String columnName = null;
		int upper = p.getUpper();
		Integer oppositeEnd = p.getOpposite().getUpper();
		if(upper == -1 && oppositeEnd == -1) {
			referencedPropertyStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "ManyToMany");
		} else if(upper == -1 && oppositeEnd == 1) {
			referencedPropertyStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "OneToMany");
		} else if(upper == 1 && oppositeEnd == -1) {
			referencedPropertyStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "ManyToOne");
		} else {
			referencedPropertyStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "OneToOne");
		}
		
		if(referencedPropertyStereotype != null) {
			List<Property> tags = referencedPropertyStereotype.getOwnedAttribute();
			for (int j = 0; j < tags.size(); ++j) {
				Property tagDef = tags.get(j);
				String tagName = tagDef.getName();
				String value = getTagValue(p, referencedPropertyStereotype, tagName);
			
				switch (tagName) {
					case "cascade":
						cascade = value;
						break;
					case "fetch":
						fetchType = value;
						break;
					case "joinTable":
						joinTable = value;
						break;
					case "mappedBy":
						mappedBy = value;
						break;
					case "columnName":
						columnName = value;
						break;
				}
			}
		} 
		
		String attName = p.getName();
		if (attName == null) 
			throw new AnalyzeException("Properties of the class: " + cl.getName() +
					" must have names!");
		Type attType = p.getType();
		if (attType == null)
			throw new AnalyzeException("Property " + cl.getName() + "." +
			p.getName() + " must have type!");
		String typeName = attType.getName();
		if (typeName == null)
			throw new AnalyzeException("Type ot the property " + cl.getName() + "." +
			p.getName() + " must have name!");		
		FMReferencedProperty prop = new FMReferencedProperty(attName, typeName, p.getVisibility().toString(),
				p.getLower(), p.getUpper(), cascade, fetchType, mappedBy, joinTable, columnName, oppositeEnd);
		return prop;


	}
	
	private FMMethod getMethodData(Operation o, Class cl) throws AnalyzeException {
		String methodName = o.getName();
		if (methodName == null) 
			throw new AnalyzeException("Operations of the class: " + cl.getName() +
					" must have names!");
		
		String methodVisibility = o.getVisibility().toString();
		if (methodVisibility == null) 
			throw new AnalyzeException("Operations of the class: " + cl.getName() +
					" must have visibility!");
		
		Type methodType = o.getType();
		if (methodType == null) 
			throw new AnalyzeException("Operations of the class: " + cl.getName() +
					" must have a return type!");
		
		String typeName = methodType.getName();
		if (typeName == null) 
			throw new AnalyzeException("Operations of the class: " + cl.getName() +
					" must have a return type name!");
		
		FMType retType = new FMType(typeName, "");
		
		FMMethod met = new FMMethod(methodVisibility, retType, methodName);
		
		List<FMParameter> parameters = new ArrayList<>();
		
		Iterator<Parameter> it = o.getOwnedParameter().iterator();	
		while (it.hasNext()) {
			Parameter p = it.next();
			if (p.getDirection().toString().equals("in")) {
				FMParameter par = getParameterData(p, met);
				parameters.add(par);
			}
		}
		
		met.setParameters(parameters);
		
		return met;
	}
	

	private FMParameter getParameterData(Parameter p, FMMethod met) throws AnalyzeException {
		String parName = p.getName();
		if (parName == null) 
			throw new AnalyzeException("Parameters of the operation: " + met.getName() +
					" must have names!");
		
		String parType = p.getType().getName();
		if (parType == null) 
			throw new AnalyzeException("Parameters of the operation: " + met.getName() +
					" must have types!");
		
		FMType parameterType = new FMType(parType, "");
		
		FMParameter par = new FMParameter(parameterType, parName);
		
		return par;
	}

	private FMEnumeration getEnumerationData(Enumeration enumeration, String packageName) throws AnalyzeException {
		FMEnumeration fmEnum = new FMEnumeration(enumeration.getName(), packageName);
		List<EnumerationLiteral> list = enumeration.getOwnedLiteral();
		for (int i = 0; i < list.size() - 1; i++) {
			EnumerationLiteral literal = list.get(i);
			if (literal.getName() == null)  
				throw new AnalyzeException("Items of the enumeration " + enumeration.getName() +
				" must have names!");
			fmEnum.addValue(literal.getName());
		}
		return fmEnum;
	}	
	
	private String getTagValue(Element el, Stereotype s, String tagName) {
		List<String> value = StereotypesHelper.getStereotypePropertyValueAsString(el, s, tagName);
		if(value == null)
			return null;
		if(value.size() == 0)
			return null;
		return value.get(0);
		
	}
	
}
