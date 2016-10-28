package com.forfan.bigbang.component.contentProvider;

import android.content.Context;
import android.content.SharedPreferences;

import com.forfan.bigbang.BigBangApp;

import java.util.Set;

import static com.forfan.bigbang.util.ConstantUtil.TYPE_BOOLEAN;
import static com.forfan.bigbang.util.ConstantUtil.TYPE_FLOAT;
import static com.forfan.bigbang.util.ConstantUtil.TYPE_INT;
import static com.forfan.bigbang.util.ConstantUtil.TYPE_LONG;
import static com.forfan.bigbang.util.ConstantUtil.TYPE_STRING;

class SPHelperImpl {

    private static final String MAINSPNAME="BigBang_sp_main";

    private static Context mContext= BigBangApp.getInstance();

    private static SharedPreferences getSP(String tagName){
        if (mContext==null){
            mContext= BigBangApp.getInstance();
        }
        if (mContext==null){
            return null;
        }
        return mContext.getSharedPreferences(MAINSPNAME, Context.MODE_PRIVATE );

    }
    public synchronized static <T> void save(String name, T t){
        SharedPreferences sp=getSP(name);
        if (sp==null)return;
        SharedPreferences.Editor editor=sp.edit();
        if (t instanceof Boolean){
            editor.putBoolean(name, (Boolean) t);
        }
        if (t instanceof String){
            editor.putString(name, (String) t);
        }
        if (t instanceof Integer){
            editor.putInt(name, (Integer) t);
        }
        if (t instanceof Long){
            editor.putLong(name, (Long) t);
        }
        if (t instanceof Float){
            editor.putFloat(name, (Float) t);
        }
        if (t instanceof Set){
            editor.putStringSet(name, (Set<String>) t);
        }
        editor.commit();
    }

    public static String get(String name, String type){
        if (!contains(name)){
            return null;
        }else{
            if (type.equalsIgnoreCase(TYPE_STRING)){
                return getString(name,null);
            }else if (type.equalsIgnoreCase(TYPE_BOOLEAN)){
                return getBoolean(name,false)+"";
            }else if (type.equalsIgnoreCase(TYPE_INT)){
                return getInt(name,0)+"";
            }else if (type.equalsIgnoreCase(TYPE_LONG)){
                return getLong(name,0L)+"";
            }else if (type.equalsIgnoreCase(TYPE_FLOAT)){
                return getFloat(name,0f)+"";
            }
            return null;
        }
    }

    public static String getString(String name, String defaultValue){
        SharedPreferences sp=getSP(name);
        if (sp==null)return defaultValue;
        return sp.getString(name,defaultValue);
    }
    public static int getInt(String name, int defaultValue){
        SharedPreferences sp=getSP(name);
        if (sp==null)return defaultValue;
        return sp.getInt(name, defaultValue);
    }
    public static float getFloat(String name, float defaultValue){
        SharedPreferences sp=getSP(name);
        if (sp==null)return defaultValue;
        return sp.getFloat(name, defaultValue);
    }
    public static boolean getBoolean(String name, boolean defaultValue){
        SharedPreferences sp=getSP(name);
        if (sp==null)return defaultValue;
        return sp.getBoolean(name, defaultValue);
    }
    public static long getLong(String name, long defaultValue){
        SharedPreferences sp=getSP(name);
        if (sp==null)return defaultValue;
        return sp.getLong(name, defaultValue);
    }
    public static Set<String> getStringSet(String name, Set<String> defaultValue){
        SharedPreferences sp=getSP(name);
        if (sp==null)return defaultValue;
        return sp.getStringSet(name, defaultValue);
    }
    public static boolean contains(String name){
        SharedPreferences sp=getSP(name);
        if (sp==null)return false;
        return sp.contains(name);
    }
    public static void remove(String name){
        SharedPreferences sp=getSP(name);
        if (sp==null)return;
        SharedPreferences.Editor editor=sp.edit();
        editor.remove(name);
        editor.commit();
    }
//    public static void clear(){
//        SharedPreferences sp=getSP();
//        SharedPreferences.Editor editor=sp.edit();
//        editor.clear();
//        editor.commit();
//    }
}