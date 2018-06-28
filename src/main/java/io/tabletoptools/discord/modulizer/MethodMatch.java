/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 12.04.18 12:50
 * 
 * Copyright (c) 2017 by bluesky IT-Solutions AG,
 * Kaspar-Pfeiffer-Strasse 4, 4142 Muenchenstein, Switzerland.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of bluesky IT-Solutions AG ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with bluesky IT-Solutions AG.
 */
package io.tabletoptools.discord.modulizer;

import org.apache.commons.cli.CommandLine;

import java.lang.reflect.Method;

public class MethodMatch {

    private Method method;
    private Integer depth;
    private Module module;
    private CommandLine commandLine;

    public MethodMatch(Module module, Method method, Integer depth, CommandLine commandLine) {
        this.module = module;
        this.method = method;
        this.depth = depth;
        this.commandLine = commandLine;
    }

    public Method getMethod() {
        return method;
    }

    public Module getModule() {
        return module;
    }

    public Integer getDepth() {
        return depth;
    }

    public CommandLine getCommandLine() {
        return commandLine;
    }
}