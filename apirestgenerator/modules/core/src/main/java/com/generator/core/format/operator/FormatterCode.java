package com.generator.core.format.operator;



import com.generator.core.format.interfaces.IFormatter;
import com.generator.core.format.utils.DataReferece;

import java.util.List;

//version 1.0.1
public class FormatterCode extends RemoveFormatte implements IFormatter {

    private List<String> anotationClass = List.of("@Component","@Service","@Repository", "interface","class");

    private List<String> anotationMethod =
            List.of("@Override","@GetMapping","@PostMapping", "@PatchMapping","@PutMapping","@Autowired");

    private List<String> accessModifiers = List.of("private","public","protected");

    @Override
    public String removeFormatte(String multiLineText) {
        return this.start(multiLineText);
    }

    @Override
    public String codeFormatter(String codeNoFornate) {
        return formatCode(codeNoFornate);
    }

    private String formatCode(String codeNoFornate) {
        StringBuffer codigoFormateado = new StringBuffer();
        String beforeLine = null;
        int indentationLevel = 0;
        List<String> codeTreeStructure = IBaseString.createList(codeNoFornate);

        for (int i = 0; i < codeTreeStructure.size(); i++) {

            String line = codeTreeStructure.get(i);
            if (i == 0 && beforeLine == null) {
                beforeLine = line;
            }
            if(!line.equals(";")) {

                if (anotationClass.stream().anyMatch(line::contains) && !line.contains(".class")) {
                    codigoFormateado.append(line).append(IBaseString.createNewLine);
                }


                if (anotationMethod.stream().anyMatch(line::contains) && !line.contains(".class")) {
                    indentationLevel = 2;
                    codigoFormateado.append(IBaseString.indentation(indentationLevel)).append(line).append(IBaseString.createNewLine);
                }

                if (beforeLine.contains(DataReferece.OVERRIDE.getNombre()) && line.contains("{") && !line.contains("interface") && !line.contains("class") && ((line.contains("private") || line.contains("public") || line.contains("protected")))) {
                    indentationLevel = 2;
                    codigoFormateado.append(IBaseString.indentation(indentationLevel)).append(line).append(IBaseString.createNewLine);
                }

                if (line.contains("{") && !line.contains("else") && !line.contains("catch") && !line.contains("}") && !line.contains("interface") && !line.contains("class") && !beforeLine.contains(DataReferece.OVERRIDE.getNombre())) {
                    codigoFormateado.append(IBaseString.indentation(indentationLevel)).append(line).append(IBaseString.createNewLine);
                    indentationLevel++;
                }

                if (line.contains("}") && (line.contains("else") || line.contains("catch") || line.contains("finally") || line.contains("final")) && line.contains("{")) {
                    indentationLevel--;
                    codigoFormateado.append(IBaseString.indentation(indentationLevel)).append(line).append(IBaseString.createNewLine);
                }


                if (line.contains("}") && !line.contains("else") && !line.contains("catch") && !line.contains("{")) {
                    indentationLevel--;
                    codigoFormateado.append(IBaseString.indentation(indentationLevel)).append(line);

                    if (i < codeTreeStructure.size() && i + 1 < codeTreeStructure.size() && codeTreeStructure.get(i + 1).contains(DataReferece.OVERRIDE.getNombre())) {
                        codigoFormateado.append(IBaseString.createNewLine).append(IBaseString.createNewLine);
                    }
                    if (i >= codeTreeStructure.size()) {
                        codigoFormateado.append(IBaseString.createNewLine).append(IBaseString.createNewLine);
                    } else {
                        codigoFormateado.append(IBaseString.createNewLine).append(IBaseString.createNewLine);
                    }
                }


                if (!line.contains("@Component") && !line.contains("@Service") && !line.contains("@Repository") &&
                        !line.contains(DataReferece.OVERRIDE.getNombre()) && !line.contains("{") && !line.contains("}") && beforeLine.contains("{")) {
                    indentationLevel++;
                    codigoFormateado.append(IBaseString.indentation(indentationLevel)).append(line).append(IBaseString.createNewLine);

                } else if (!anotationClass.stream().anyMatch(line::contains) && !line.contains(".class")
                        && !anotationMethod.stream().anyMatch(line::contains) && !line.contains("{") && !line.contains("}") && !beforeLine.contains("{")) {
                    codigoFormateado.append(IBaseString.indentation(indentationLevel)).append(line).append(IBaseString.createNewLine);
                }

                if (!anotationClass.stream().anyMatch(line::contains) && !line.contains(".class")
                        && !anotationMethod.stream().anyMatch(line::contains) && !line.contains("{") && !line.contains("}")) {
                  if(i + 1 < codeTreeStructure.size()){
                      String lineWill = codeTreeStructure.get(i + 1);
                      if (i + 1 < codeTreeStructure.size() && anotationMethod.stream().anyMatch(lineWill::contains)){
                          codigoFormateado.append(IBaseString.createNewLine);
                      }
                  }
                }


                if (line.contains("import") && beforeLine.contains("import") && i + 1 < codeTreeStructure.size() && !codeTreeStructure.get((i + 1)).contains("import")) {
                    codigoFormateado.append(IBaseString.createNewLine).append(IBaseString.createNewLine);
                }

                if (accessModifiers.stream().anyMatch(line::contains) && line.contains("class") && i + 1 < codeTreeStructure.size() && !codeTreeStructure.get((i + 1)).contains("class")) {
                    codigoFormateado.append(IBaseString.createNewLine);
                }

                beforeLine = line;
            }
        }
        return codigoFormateado.toString();
    }

}


// 1. Lexical analysis (omitted in this simplified example)
// 2. Syntactic analysis (omitted in this simplified example)