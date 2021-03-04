package com.metanit;

import com.google.common.base.Joiner;
import org.codehaus.plexus.util.StringUtils;

public class SimpleOutput {

    public void show() {
        Joiner joiner = Joiner.on(" ").skipNulls();
        System.out.println(joiner.join("Build", "and", null, "compile"));
        System.out.println(StringUtils.capitaliseAllWords("class simple output"));
    }
}