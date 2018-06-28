/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 12.04.18 10:38
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
package io.tabletoptools.discord.modulizer.modules.config;

import io.tabletoptools.discord.modulizer.Module;
import io.tabletoptools.discord.modulizer.annotation.Command;
import io.tabletoptools.discord.modulizer.annotation.Locked;

@Command("config")
@Locked()
public class ConfigModule extends Module {

    @Override
    public void onLoad() {
        this.addCommandClass(ConfigurationCommands.class);
    }

    @Override
    public void onUnload() {
        //This module should never be unloaded.
    }

    @Override
    public void onEnable() {
        //TODO: Enabling this module
    }

    @Override
    public void onDisable() {
        //This module should never be disabled.
    }
}
