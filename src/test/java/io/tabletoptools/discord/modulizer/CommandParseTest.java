/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 12.04.18 16:05
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

import io.tabletoptools.discord.modulizer.module.TestModule;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CommandParseTest extends Modulizer {

    @Test
    public void testGoodCommandList() {
        List<String> commands = this.getCommands("/help list command module list help");
        Assert.assertEquals(6, commands.size());
        commands = this.getCommands("/help list 'test something' and something else");
        Assert.assertEquals(6, commands.size());
        commands = this.getCommands("/help list 'test \"something' and something else");
        Assert.assertEquals(6, commands.size());
        commands = this.getCommands("/help list 'test something' and something else");
        Assert.assertEquals(6, commands.size());
        commands = this.getCommands("/help list 'test something' and something else");
        Assert.assertEquals(6, commands.size());
        commands = this.getCommands("/config add 'Some Value split into multiple thingies' -u userIdGoesHere -g guildIdGoesHere");
        Assert.assertEquals(7, commands.size());

    }

    @Test
    public void testPathFinding() {
        Modulizer.instance()
                .loadModule(new TestModule());
    }
}
