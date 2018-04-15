/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 13.04.18 17:18
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
package io.tabletoptools.discord.modulizer.module;

import io.tabletoptools.discord.modulizer.Module;
import io.tabletoptools.discord.modulizer.module.commands.AlternateTestCommandClass;
import io.tabletoptools.discord.modulizer.module.commands.TestCommandClass;

public class TestModule extends Module {

    @Override
    public void onLoad() {
        this.addCommandClass(AlternateTestCommandClass.class);
        this.addCommandClass(TestCommandClass.class);
    }

    @Override
    public void onUnload() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
