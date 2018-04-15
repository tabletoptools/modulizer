/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 13.04.18 17:19
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
package io.tabletoptools.discord.modulizer.module.commands;

import io.tabletoptools.discord.modulizer.CommandClass;
import io.tabletoptools.discord.modulizer.annotation.Command;

@Command("alt_second")
public class AlternateTestCommandClass extends CommandClass {

    @Command("3_command")
    public void command() {
    }

}
