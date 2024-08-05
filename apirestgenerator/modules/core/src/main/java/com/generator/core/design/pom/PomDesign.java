package com.generator.core.design.pom;

import java.util.ArrayList;
import java.util.List;

public class PomDesign {

   private String packaging;
   private List<Profile> profiles = new ArrayList<>();
   private List<Dependency>dependencies = new ArrayList<>();

   public PomDesign() {
   }

   public String getPackaging() {
      return packaging;
   }

   public void setPackaging(String packaging) {
      this.packaging = packaging;
   }

   public List<Profile> getProfiles() {
      return profiles;
   }

   public void setProfiles(List<Profile> profiles) {
      this.profiles = profiles;
   }

   public List<Dependency> getDependencies() {
      return dependencies;
   }

   public void setDependencies(List<Dependency> dependencies) {
      this.dependencies = dependencies;
   }
}
