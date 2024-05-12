package com.Generator.apirest.core.build;

import com.Generator.apirest.core.design.MethodDesign;

public class BuildFileClass {

    private String nameFile;
    private String singleString;
    private String path;

    public BuildFileClass() {
    }

    public BuildFileClass(String nameFile, String singleString, String direction) {
        this.nameFile = nameFile;
        this.singleString = singleString;
        this.path = direction;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getSingleString() {
        return singleString;
    }

    public void setSingleString(String singleString) {
        this.singleString = singleString;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String direction) {
        this.path = direction;
    }

    public static BuildFileClass.Builder builder() {
        return new BuildFileClass.Builder();
    }

    public interface BuildFileClassBuilder {
        public Builder singleString(String singleString);
        public Builder nameFile(String nameFile);
        public Builder path(String path);
        public BuildFileClass build();
    }


    public static class Builder implements BuildFileClassBuilder {
        private String nameFile;
        private String singleString;
        private String path;

        @Override
        public Builder singleString(String singleString) {
            this.singleString = singleString;
            return this;
        }

        @Override
        public Builder nameFile(String nameFile) {
            this.nameFile = nameFile;
            return this;
        }

        @Override
        public Builder path(String path) {
            this.path = path;
            return this;
        }

        @Override
        public BuildFileClass build() {
            BuildFileClass buildFileClass = new BuildFileClass();

            if (this.nameFile != null)
                buildFileClass.setNameFile(this.nameFile);

            if (this.singleString != null)
                buildFileClass.setSingleString(this.singleString);

            if (this.path != null)
                buildFileClass.setPath(this.path);

            return buildFileClass;
        }

    }
}
