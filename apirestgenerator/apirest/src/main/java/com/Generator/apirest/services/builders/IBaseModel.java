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
//		stringPaths.stream().forEach(path -> newString.append(path));
		return newString.toString();
	}


	default  String toCamelCase(String text) {
		String[] words = text.split("[\\W_]+");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			builder.append(i > 0 ? word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() : word.toLowerCase());
		}
		return builder.toString();
	}

	default String capitalizeFirstLetter(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		char firstChar = str.charAt(0);
		String remainingChars = str.substring(1);
		StringBuilder builder = new StringBuilder(Character.toUpperCase(firstChar));
		builder.append(remainingChars);
		return Character.isUpperCase(firstChar) ? str : builder.toString();
	}

	default public String primeraLetraMayuscula(String cadena) {
		String cadenaN = "";
		char[] caracteres = cadena.toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);
		for (char c : caracteres) { cadenaN = cadenaN + c; }
		return cadenaN;
	}
}
