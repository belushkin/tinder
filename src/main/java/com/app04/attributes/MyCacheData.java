package com.app04.attributes;

import java.util.Map;

public class MyCacheData {
    private static MyCacheData instance = new MyCacheData();
    private Map<String, Integer> cacheData;

    private MyCacheData(){

    }

    public static void setInstance(MyCacheData instance) {
        MyCacheData.instance = instance;
    }

    public Map<String, Integer> getCacheData() {
        return cacheData;
    }

    public void setCacheData(Map<String, Integer> cacheData) {
        this.cacheData = cacheData;
    }

    public static MyCacheData getInstance(){
        return instance;
    }



}
