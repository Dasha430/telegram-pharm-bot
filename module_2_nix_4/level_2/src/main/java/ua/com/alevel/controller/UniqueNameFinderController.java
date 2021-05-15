package ua.com.alevel.controller;

import ua.com.alevel.util.UniqueNameFinderUtil;

import java.util.List;

public class UniqueNameFinderController {

    public String run(List<String> allNames) {
        return UniqueNameFinderUtil.findFirstUnique(allNames);
    }
}
