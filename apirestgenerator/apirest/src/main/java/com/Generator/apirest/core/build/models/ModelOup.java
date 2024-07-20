package com.Generator.apirest.core.build.models;

public class ModelOup {

    private String nameOfClass;
    private String packageNane;
    private String classInString;
    private String directoryForJava;

    public ModelOup() {
    }


    public String getNameOfClass() {
        return nameOfClass;
    }

    public void setNameOfClass(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }

    public String getPackageNane() {
        return packageNane;
    }

    public void setPackageNane(String packageNane) {
        this.packageNane = packageNane;
    }

    public String getClassInString() {
        return classInString;
    }

    public void setClassInString(String classInString) {
        this.classInString = classInString;
    }

    public String getDirectoryForJava() {
        return directoryForJava;
    }

    public void setDirectoryForJava(String directoryForJava) {
        this.directoryForJava = directoryForJava;
    }

    public static Builder builder() {
        return new Builder();
    }

    public interface ModelOupBuilder {

        public Builder nameOfClass(String nameOfClass);

        public Builder packageNane(String packageNane);

        public Builder classInString(String classInString);

        public Builder directoryForJava(String directoryForJava);

        public ModelOup build();
    }

    public static class Builder implements ModelOupBuilder {
        private String nameOfClass;
        private String packageNane;
        private String classInString;
        private String directoryForJava;

        @Override
        public Builder nameOfClass(String nameOfClass) {
            if(nameOfClass !=null){
                this.nameOfClass = nameOfClass;
            }
            return this;
        }

        @Override
        public Builder packageNane(String packageNane) {
            if(packageNane !=null){
                this.packageNane = packageNane;
            }
            return this;
        }

        @Override
        public Builder classInString(String classInString) {
            if(classInString !=null){
                this.classInString = classInString;
            }
            return this;
        }

        @Override
        public Builder directoryForJava(String directoryForJava) {
            if(directoryForJava !=null){
                this.directoryForJava = directoryForJava;
            }
            return this;
        }

        @Override
        public ModelOup build() {

            ModelOup modelOup = new ModelOup();

            if(this.nameOfClass != null){
                modelOup.setNameOfClass(this.nameOfClass);
            }

            if(this.packageNane != null){
                modelOup.setPackageNane(this.packageNane);
            }

            if(this.classInString != null){
                modelOup.setClassInString(this.classInString);
            }

            if(this.directoryForJava != null){
                modelOup.setDirectoryForJava(this.directoryForJava);
            }
            return modelOup;
        }
    }


}
