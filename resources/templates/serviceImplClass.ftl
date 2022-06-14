package ${class.typePackage};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import mbrs.tim9.repository.${class.name}Repository;
import org.springframework.stereotype.Service;
import mbrs.tim9.service.${class.name}Service;
import mbrs.tim9.model.${class.name};
import java.util.Collection;

@Service
public class ${class.name}ServiceImpl implements ${class.name}Service {

    @Autowired
    private ${class.name}Repository ${class.name?uncap_first}Repository;
    
<#list methods as method>
	<#if method.returnType.name == "Collection" || method.returnType.name == "Set" || method.returnType.name == "List" >
		<#if method.name == "getAll">
    @Override
    ${method.visibility} ${method.returnType.name}<${class.name}> ${method.name}(<#list method.parameters as parameter>${parameter.type.name} ${parameter.name}<#sep>, </#sep></#list>){
        return ${class.name?uncap_first}Repository.findAll();
    }
	    </#if>

	<#else>
		<#if method.name == "save">
 	@Override
    ${method.visibility} ${method.returnType.name} ${method.name}(<#list method.parameters as parameter>${parameter.type.name} ${parameter.name}<#sep>, </#sep></#list>){
        ${class.name?uncap_first}Repository.save(${class.name?uncap_first});
    }
		</#if>		
		<#if method.name == "delete">
 	@Override
    ${method.visibility} ${method.returnType.name} ${method.name}(<#list method.parameters as parameter>${parameter.type.name} ${parameter.name}<#sep>, </#sep></#list>){
        ${class.name?uncap_first}Repository.delete(${class.name?uncap_first});
    }
		</#if>		
		<#if method.name == "getById">
 	@Override
    ${method.visibility} ${method.returnType.name} ${method.name}(<#list method.parameters as parameter>${parameter.type.name?cap_first} ${parameter.name}<#sep>, </#sep></#list>){
        return ${class.name?uncap_first}Repository.findById(id).get();
    }
		</#if>
				
	</#if>
</#list>
}


