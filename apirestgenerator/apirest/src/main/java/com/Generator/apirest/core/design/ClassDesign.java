package com.Generator.apirest.core.design;

import com.Generator.apirest.core.build.ClassType;
import com.Generator.apirest.core.build.ParameterClassMethod;
import com.Generator.apirest.core.build.TypeInject;

import java.util.ArrayList;
import java.util.List;

public class ClassDesign {

    private String packagePaht;
    private String packageName;
    private List<String> imports = new ArrayList<>();
    private List<String> annotation = new ArrayList<>();
    private String className;
    private List<MethodDesign> methodDesignList = new ArrayList<>();
    private ClassType classType;
    private Boolean isClasInheritance;
    private Boolean isClasImplement;
    private String clasInheritance;
    private String clasImplement;
    private Boolean isInject;
    private TypeInject typeInject;
    private List<ParameterClassMethod> classParameterClassMethods = new ArrayList<>();

}