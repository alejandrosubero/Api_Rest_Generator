package com.generator.core.design.interfaces;



import com.generator.core.design.ParameterClassMethod;
import com.generator.core.design.reference.RetunsType;
import com.generator.core.interfaces.IImportModel;

import java.util.List;

public interface DesingCommunInterface extends IImportModel {


    default public String annotationBuild(List<String> annotation) {
        StringBuilder methodTx = new StringBuilder();
        if (annotation != null && annotation.size() > 0) {
            annotation.stream().forEach(annotationOne -> {
                methodTx.append(stringEnsamble(List.of(annotationOne, BREAK_LINE)));
            });
        } else {
            methodTx.append(SPACE);
        }
        return methodTx.toString();
    }

    default public String parameterBuildStructure(List<ParameterClassMethod> parameters) {
        StringBuilder contexParameter = new StringBuilder();

        if (parameters.size() > 0) {
            contexParameter.append(PARENTHESES_OPEN);
            for (int i = 0; i < parameters.size(); i++) {
                String annotation = "";

                if (parameters.get(i).getAnnotation().size() > 0) {
                    annotation = this.annotationBuild(parameters.get(i).getAnnotation());
                }

                String parameter = parameters.get(i).toString().replaceAll("\\s{1,2}", " ");
                contexParameter.append(annotation);

                if (i < parameters.size() - 1) {
                    contexParameter.append(stringEnsamble(List.of(parameter, COMMA)));
                } else {
                    contexParameter.append(parameter);
                }
            }
            contexParameter.append(PARENTHESES_CLOSE);
        } else {
            contexParameter.append(PARENTHESES_OPEN_CLOSE);
        }
        return contexParameter.toString();
    }

    default public String structureStringListinColumm(List<String> parameters) {
        StringBuilder stringColumm = new StringBuilder(BREAK_LINE);
        if (parameters.size() > 0) {
            for (String item : parameters) {
                stringColumm.append(stringEnsamble(List.of(item, BREAK_LINE)));
            }
            stringColumm.append(DOUBLEBREAK_LINE);
        }
        return stringColumm.toString();
    }

    default public String structureStringListinColummAnotation(List<String> parameters) {
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

    default public String buildLoggerInfo(String text) {
        return stringEnsamble(List.of(DOUBLETAB, "logger.info(\"", text, "\");"));
    }

    default public String buildLoggerError(String text, String error) {
        if (text == null){
            text = "";
        }
        if (error == null) {
            error = "";
        } else {
            error = stringEnsamble(List.of("\" ERROR : \"", error));
        }
        return stringEnsamble(List.of(DOUBLETAB, "logger.error( ", error, SPACE, text, "\");"));
    }

    default public String metodTrycath(String tryOperation, String catchOperation, String exceptionObject) {
        StringBuilder sb2 = new StringBuilder(BREAK_LINE);
        sb2.append(stringEnsamble(List.of(TRY,SPACE,BRACKET_OPEN,BREAK_LINE)));
        sb2.append(stringEnsamble(List.of(tryOperation,BREAK_LINE)));
        sb2.append(stringEnsamble(List.of(BRACKET_CLOSE,CATCH,PARENTHESES_OPEN, exceptionObject, SPACE, "e", PARENTHESES_CLOSE,BRACKET_OPEN,BREAK_LINE)));
        if(catchOperation!=null){
            sb2.append(stringEnsamble(List.of(catchOperation, BREAK_LINE)));
        }
        sb2.append(stringEnsamble(List.of(BRACKET_CLOSE, BREAK_LINE)));
        return sb2.toString();
    }

    default public String createNewObject(String className){
       return stringEnsamble(List.of(className, TAB,className.toLowerCase(),"Entity = new ",className,PARENTHESES_OPEN_CLOSE,SEMICOLON,BREAK_LINE));
    }

    default public String createOptionalEqual(String className, String optionalArgumentName, String variator){
        return stringEnsamble(List.of(RetunsType.Optional.toString(), openAngleBrackets,className,closeAngleBrackets,SPACE,optionalArgumentName,variator, SPACE, EQUAL_, SPACE));
    }

    default public String callRepository(String repositorieNameOjecte, String repoOperator, String attributeName ){
        return stringEnsamble(List.of(repositorieNameOjecte, DOT, repoOperator, this.capitalizeOrUncapitalisedFirstLetter(attributeName, 'u'),
                PARENTHESES_OPEN, attributeName, PARENTHESES_CLOSE,SEMICOLON));
    }

    default public String createIfBlock(String argument, String condition, String ifStamentBody ){
        return stringEnsamble(List.of(
                IF_STAMENT, PARENTHESES_OPEN,argument,condition, PARENTHESES_CLOSE,BRACKET_OPEN,
                BREAK_LINE,ifStamentBody,BREAK_LINE,BRACKET_CLOSE));
    }


    private String metodo(StringBuilder sb, String nameOfClass, String numeral) {
        sb.append("		if (fileOptional" + numeral + ".isPresent()) {" + "\r\n");
        sb.append("\r\n");
        sb.append("		try {" + "\r\n");
        sb.append("\r\n");
        sb.append("	logger.info(\"the proyect be updated\");" + "\r\n");
        sb.append("\r\n");
        sb.append("		" + nameOfClass + " proyectoBDA" + numeral + " = fileOptional.get();" + "\r\n");
        sb.append("\r\n");
        sb.append("		return proyectoBDA" + numeral + "; " + "\r\n");
        sb.append("		} catch (DataAccessException e) {  " + "\r\n");
        sb.append("		logger.error(\" ERROR : \" + e); " + "\r\n");
        sb.append("		}" + "\r\n");
        sb.append("  	}else { " + "\r\n");
        sb.append("		return new " + nameOfClass + "(); " + "\r\n");
        sb.append("		}" + "\r\n");
        sb.append("\r\n");
        return sb.toString();
    }

    private String metodoGeneric(StringBuilder sb, String nameOfClass, String numeral, String operacion,
                                 String operacionElse) {

        sb.append("		if (fileOptional" + numeral + ".isPresent()) {" + "\r\n");
        sb.append("\r\n");
        sb.append("		try {" + "\r\n");
        sb.append("\r\n");
        sb.append("	logger.info(\"the proyect be updated\");" + "\r\n");
        sb.append("\r\n");
        sb.append("		" + nameOfClass + " proyectoBDA" + numeral + " = fileOptional" + numeral + ".get();" + "\r\n");
        sb.append("\r\n");
        sb.append(operacion);
        sb.append("		} catch (DataAccessException e) {  " + "\r\n");
        sb.append("		logger.error(\" ERROR : \" + e); " + "\r\n");
        sb.append("		}" + "\r\n");
        sb.append("  	}else { " + "\r\n");
        sb.append(operacionElse);
        sb.append("		}" + "\r\n");
        sb.append("\r\n");
        return sb.toString();
    }

}
