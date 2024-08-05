package com.generator.core.design.pom;

import com.generator.core.design.IMethodDesignDesign;
import com.generator.core.design.pom.interfaces.IDependencyDesign;

public class Dependency implements IDependencyDesign {

    private String groupId;
    private String artifactId;
    private String version;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Dependency() {
    }

    @Override
    public String toString() {
        return this.toStringDependency(this);
    }

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public static IMethodDesignDesign.Builder builder() {
        return new IMethodDesignDesign.Builder();
    }


    public interface DependencyBuilder {
        public Builder version(String version);
        public Builder artifactId(String artifactId);
        public Builder groupId(String groupId);
        public Dependency build();
    }


    public static class Builder implements DependencyBuilder {
        private String groupId;
        private String artifactId;
        private String version;


        @Override
        public Builder version(String version) {
            this.version = version;
            return this;
        }

        @Override
        public Builder artifactId(String artifactId) {
            this.artifactId = artifactId;
            return this;
        }

        @Override
        public Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        @Override
        public Dependency build() {
            Dependency dependency = new Dependency();
            if(this.groupId != null){
                dependency.setGroupId(this.groupId);
            }
            if(this.artifactId !=null){
                dependency.setArtifactId(this.artifactId);
            }

            if(this.version != null){
                dependency.setVersion(this.version);
            }
            return dependency;
        }
    }
}
