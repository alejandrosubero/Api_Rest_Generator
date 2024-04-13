package com.api.wiki.businessrules;

import com.api.wiki.utility.VersionComparatorState;

public interface VersionControlDTOBusinessRuler {

    default public String versionComparator(String version1,  String version2 ) {
        int comparisonResult = version1.compareTo(version2);

        if (comparisonResult < 0) {
           return VersionComparatorState.OLDER.toString();
        } else if (comparisonResult > 0) {
            return VersionComparatorState.NEWER.toString();
        } else {
            return VersionComparatorState.EQUAL.toString();
        }
    }
}
