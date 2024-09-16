package com.example.usercrudapp.util;

import java.util.Optional;

public class TextUtil {

    public static boolean isAbsent(Optional<String> field){
        return field.isEmpty() || (field.isPresent() && field.get().trim().isEmpty());
    }
}
