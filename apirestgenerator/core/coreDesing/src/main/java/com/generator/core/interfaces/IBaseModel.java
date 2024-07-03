package com.generator.core.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IBaseModel extends IConstantModel {



	default public String path(List<String> paths) {
		StringBuilder newPathBuilder = new StringBuilder();
		for (int i = 0; paths.size() > i; i++) {
				if(i != 0 && i+1 != paths.size() && paths.get(i) != " ") {
					newPathBuilder.append(stringEnsamble(List.of(pathSeparator, paths.get(i))));
				}else if (paths.get(i) != " " && i+1 == paths.size()) {
					newPathBuilder.append(stringEnsamble(List.of(pathSeparator, paths.get(i))));
				}else if (paths.get(i) != " ") {
					newPathBuilder.append(paths.get(i));
				}
			if (paths.get(i) == " ") {
				newPathBuilder.append(pathSeparator);
			}	
		}
		return newPathBuilder.toString();
	}


	default public String stringEnsamble(List<String> stringPaths) {
		StringBuffer newString = new StringBuffer();
		stringPaths.stream().forEach(path -> newString.append(path));
		return newString.toString();
	}

	default public String stringEnsamble(String ...stringPaths) {
		StringBuffer newString = new StringBuffer();
		for (String path : stringPaths) {
			newString.append(path);
		}
		return newString.toString();
	}

	default public List<String> toList(String ...StringPaths){
		List<String> stringList = new ArrayList<>(Arrays.asList(StringPaths));
		return stringList;
	}


	default public String listStringStructureToColummString(List<String> parameters) {
		StringBuilder stringColumm = new StringBuilder(BREAK_LINE);
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++ ){
				stringColumm.append(stringEnsamble(List.of(parameters.get(i))));
				if(i < parameters.size()){
					stringColumm.append(BREAK_LINE);
				}
			}
		}
		return stringColumm.toString();
	}


	default public String capitalizeOrUncapitalisedFirstLetter(String str, Character action) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		String remainingCharsInString = str.substring(1);
		return action.equals('u')?stringEnsamble(List.of(str.substring(0, 1).toUpperCase(),remainingCharsInString)):
				stringEnsamble(List.of(str.substring(0, 1).toLowerCase(),remainingCharsInString));
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
		String firstCharInString = str.substring(0, 1).toUpperCase();
		String remainingCharsInString = str.substring(1);

		return stringEnsamble(List.of(firstCharInString,remainingCharsInString));
	}




}
