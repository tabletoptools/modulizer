package io.tabletoptools.discord.modulizer.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Option {
    String value();
    boolean defaultValue();
}
