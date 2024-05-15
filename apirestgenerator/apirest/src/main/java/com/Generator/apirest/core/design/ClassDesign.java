package com.Generator.apirest.core.design;

import com.Generator.apirest.core.build.ClassType;
import com.Generator.apirest.core.build.ParameterClassMethod;
import com.Generator.apirest.core.build.TypeInject;

import java.util.ArrayList;
import java.util.List;

public class ClassDesign implements ClassDesignInterface {

    private String packagePaht;
    private String packageName;
    private String className;
    private ClassType classType;
    private Boolean isClasInheritance;
    private Boolean isClasImplement;
    private String clasInheritance;
    private String clasImplement;
    private Boolean isInject;
    private TypeInject typeInject;
    private List<String> imports = new ArrayList<>();
    private List<String> annotation = new ArrayList<>();
    private List<ParameterClassMethod> classParameterClassMethods = new ArrayList<>();
    private String content;


    public ClassDesign() {
    }

    public ClassDesign(String packagePaht, String packageName, String className, ClassType classType, Boolean isClasInheritance, Boolean isClasImplement, String clasInheritance, String clasImplement, Boolean isInject, TypeInject typeInject, List<String> imports, List<String> annotation, List<ParameterClassMethod> classParameterClassMethods, String content) {
        this.packagePaht = packagePaht;
        this.packageName = packageName;
        this.className = className;
        this.classType = classType;
        this.isClasInheritance = isClasInheritance;
        this.isClasImplement = isClasImplement;
        this.clasInheritance = clasInheritance;
        this.clasImplement = clasImplement;
        this.isInject = isInject;
        this.typeInject = typeInject;
        this.imports = imports;
        this.annotation = annotation;
        this.classParameterClassMethods = classParameterClassMethods;
        this.content = content;
    }

    public String getPackagePaht() {
        return packagePaht;
    }

    public void setPackagePaht(String packagePaht) {
        this.packagePaht = packagePaht;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Boolean getClasInheritance() {
        return isClasInheritance;
    }

    public void setClasInheritance(String clasInheritance) {
        this.clasInheritance = clasInheritance;
    }

    public void setClasInheritance(Boolean clasInheritance) {
        isClasInheritance = clasInheritance;
    }

    public Boolean getClasImplement() {
        return isClasImplement;
    }

    public void setClasImplement(String clasImplement) {
        this.clasImplement = clasImplement;
    }

    public Boolean getInject() {
        return isInject;
    }

    public void setInject(Boolean inject) {
        isInject = inject;
    }

    public TypeInject getTypeInject() {
        return typeInject;
    }

    public void setTypeInject(TypeInject typeInject) {
        this.typeInject = typeInject;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public List<String> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(List<String> annotation) {
        this.annotation = annotation;
    }

    public List<ParameterClassMethod> getClassParameterClassMethods() {
        return classParameterClassMethods;
    }

    public void setClassParameterClassMethods(List<ParameterClassMethod> classParameterClassMethods) {
        this.classParameterClassMethods = classParameterClassMethods;
    }

    public void setClasImplement(Boolean clasImplement) {
        isClasImplement = clasImplement;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ClassDesign.Builder builder() {
        return new  ClassDesign.Builder();
    }

    @Override
    public String toString() {
        StringBuilder buildClassDesign = new StringBuilder();
        this.packagePahtBuild(this.packagePaht, this.packageName);


        return "ClassDesign{}";
    }

    public interface ClassDesingBuilder {

        public Builder packagePaht(String packagePaht);

        public Builder packageName(String packageName);

        public Builder className(String className);

        public Builder classType(ClassType classType);

        public Builder clasInheritance(String clasInheritance);

        public Builder isClasInheritance(Boolean clasInheritance);

        public Builder isClasImplement(String clasImplement);

        public Builder isClasImplement(Boolean clasImplement);

        public Builder inject(Boolean inject);

        public Builder typeInject(TypeInject typeInject);

        public Builder imports(List<String> imports);

        public Builder annotation(List<String> annotation);

        public Builder classParameterClassMethods(List<ParameterClassMethod> classParameterClassMethods);

        public Builder content(String content);
        public ClassDesign build();
    }


    public static class Builder implements ClassDesingBuilder {

        private String packagePaht;
        private String packageName;
        private String className;
        private ClassType classType;
        private Boolean isClasInheritance;
        private Boolean isClasImplement;
        private String clasInheritance;
        private String clasImplement;
        private Boolean isInject;
        private TypeInject typeInject;
        private List<String> imports = new ArrayList<>();
        private List<String> annotation = new ArrayList<>();
        private List<ParameterClassMethod> classParameterClassMethods = new ArrayList<>();
        private String content;

        @Override
        public Builder packagePaht(String packagePaht) {
            this.packagePaht = packagePaht;
            return this;
        }

        @Override
        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        @Override
        public Builder className(String className) {
            this.className = className;
            return this;
        }

        @Override
        public Builder classType(ClassType classType) {
            this.classType = classType;
            return this;
        }

        @Override
        public Builder clasInheritance(String clasInheritance) {
            this.clasInheritance = clasInheritance;
            return this;
        }

        @Override
        public Builder isClasInheritance(Boolean clasInheritance) {
            this.isClasInheritance = clasInheritance;
            return this;
        }


        @Override
        public Builder isClasImplement(String clasImplement) {
            this.clasImplement = clasImplement;
            return this;
        }

        @Override
        public Builder inject(Boolean isInject) {
            this.isInject = isInject;
            return this;
        }

        @Override
        public Builder typeInject(TypeInject typeInject) {
            this.typeInject = typeInject;
            return this;
        }

        @Override
        public Builder imports(List<String> imports) {
            this.imports = imports;
            return this;
        }

        @Override
        public Builder annotation(List<String> annotation) {
            this.annotation = annotation;
            return this;
        }

        @Override
        public Builder classParameterClassMethods(List<ParameterClassMethod> classParameterClassMethods) {
            this.classParameterClassMethods = classParameterClassMethods;
            return this;
        }

        @Override
        public Builder content(String content) {
            this.content = content;
            return this;
        }


        @Override
        public Builder isClasImplement(Boolean isclasImplement) {
            this.isClasImplement = isclasImplement;
            return this;
        }

        @Override
        public ClassDesign build() {
            ClassDesign classDesign = new ClassDesign();

            if (null != this.packagePaht)
                classDesign.setPackagePaht(this.packagePaht);

            if (null != this.packageName)
                classDesign.setPackageName(this.packageName);

            if (null != this.className)
                classDesign.setClassName(this.className);

            if (null != this.classType)
                classDesign.setClassType(this.classType);

            if (null != this.isClasInheritance)
                classDesign.setClasInheritance(this.isClasInheritance);

            if (null != this.isClasImplement)
                classDesign.setClasImplement(this.isClasImplement);

            if (null != this.clasInheritance)
                classDesign.setClasInheritance(this.clasInheritance);

            if (null != this.clasImplement)
                classDesign.setClasImplement(this.clasImplement);

            if (null != this.isInject)
                classDesign.setInject(this.isInject);

            if (null != this.typeInject)
                classDesign.setTypeInject(this.typeInject);

            if (null != this.imports)
                classDesign.setImports(this.imports);

            if (null != this.annotation)
                classDesign.setAnnotation(this.annotation);

            if (null != this.classParameterClassMethods)
                classDesign.setClassParameterClassMethods(this.classParameterClassMethods);

            if(this.content != null)
                classDesign.setContent(this.content);

            return classDesign;
        }


    }
}
