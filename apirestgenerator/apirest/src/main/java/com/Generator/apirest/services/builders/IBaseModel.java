package com.Generator.apirest.services.builders;

import java.util.List;

public interface IBaseModel extends IConstantModel {


	
	default public String path(List<String> paths) {
		String newPath="";
		String  barra = java.nio.file.FileSystems.getDefault().getSeparator();
		for (int i = 0; paths.size() > i; i++) {
				if(i != 0 && i+1 != paths.size() && paths.get(i) != " ") {
					newPath += barra + paths.get(i);
				}else if (paths.get(i) != " " && i+1 == paths.size()) {
					newPath += barra + paths.get(i);
				}else if (paths.get(i) != " ") {
					newPath += paths.get(i);
				}
			if (paths.get(i) == " ") {
				newPath += barra;
			}	
		}
		return newPath;
	}


	default public String stringEnsamble(List<String> stringPaths) {
		StringBuilder newString = new StringBuilder();
		for (String part : stringPaths) {
			newString.append(part);
			}
		return newString.toString();
	}


	
}
