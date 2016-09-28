package net.sotree.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.reflect.Field;

/**
 * Created by lims on 2016. 3. 29..
 * Preference 유틸리티
 */
public class Pref {

    public static String getPrefValue(Context context, String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

    public static int getPrefValue(Context context, String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue);
    }

    public static long getPrefValue(Context context, String key, long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, defaultValue);
    }

    public static float getPrefValue(Context context, String key, float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static double getPrefValue(Context context, String key, double defaultValue) {
        return Double.longBitsToDouble(getPrefValue(context, key, Double.doubleToRawLongBits(defaultValue)));
    }

    public static boolean getPrefValue(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }


    public static void setPrefValue(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static void setPrefValue(Context context, String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply();
    }

    public static void setPrefValue(Context context, String key, long value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key, value).apply();
    }

    public static void setPrefValue(Context context, String key, float value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(key, value).apply();
    }

    public static void setPrefValue(Context context, String key, double value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key, Double.doubleToRawLongBits(value)).apply();
    }

    public static void setPrefValue(Context context, String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    public static void removePrefValue(Context context, String key) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(key).apply();
    }

    public static void clearPref(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }

    /**
     * param으로 전달 받는 object의 필드에 defaultValue를 설정하여 호출 (TEST필요 아직 사용 못해봄 ㅋ)
     *
     * @param context
     * @param params
     */
    public static void getPrefValue(Context context, Object params) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Field[] fields = params.getClass().getFields();

        String key;
        Object value;
        for (Field field : fields) {
            key = field.getName();
            if (key == null) {
                continue;
            }

            try {
                value = field.get(params);

                if (field.getType() == Integer.TYPE || field.getType() == Integer.class) {
                    field.setInt(params, pref.getInt(key, (int) value));
                } else if (field.getType() == Long.TYPE || field.getType() == Long.class) {
                    field.setLong(params, pref.getLong(key, (long) value));
                } else if (field.getType() == Float.TYPE || field.getType() == Float.class) {
                    field.setFloat(params, pref.getFloat(key, (float) value));
                } else if (field.getType() == Double.TYPE || field.getType() == Double.class) {
                    field.setDouble(params, Double.longBitsToDouble(pref.getLong(key, Double.doubleToRawLongBits((double) value))));
                } else if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
                    field.setBoolean(params, pref.getBoolean(key, (boolean) value));
                } else {
                    // null값이 "null" 스트링으로 되는것 방지
                    field.set(params, pref.getString(key, value == null ? null : String.valueOf(value)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setPrefValue(Context context, Object params) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        Field[] fields = params.getClass().getFields();

        String key;
        Object value;
        for (Field field : fields) {
            key = field.getName();
            if (key == null) {
                continue;
            }

            try {
                value = field.get(params);
            } catch (IllegalAccessException e) {
                value = null;
                e.printStackTrace();
            }

            if (value == null) {
                editor.remove(key);
                continue;
            }

            if (field.getType() == Integer.TYPE || field.getType() == Integer.class) {
                editor.putInt(key, (int) value);
            } else if (field.getType() == Long.TYPE || field.getType() == Long.class) {
                editor.putLong(key, (long) value);
            } else if (field.getType() == Float.TYPE || field.getType() == Float.class) {
                editor.putFloat(key, (float) value);
            } else if (field.getType() == Double.TYPE || field.getType() == Double.class) {
                editor.putLong(key, Double.doubleToRawLongBits((double) value));
            } else if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
                editor.putBoolean(key, (boolean) value);
            } else {
                editor.putString(key, String.valueOf(value));
            }
        }

        editor.apply();
    }
}
