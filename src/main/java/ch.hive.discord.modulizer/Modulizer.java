/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 20.03.18 17:07
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
package ch.hive.discord.modulizer;

import java.util.HashMap;

public class Modulizer {

    /*
    *
    * Modules can be loaded or unloaded at runtime
    * Modules can be enabled and disabled on a per-user and per-server basis
    * Modules contain a set of functionality and already have implied access to the following:
    *  - Server- (Guild-)Events
    *  - Persistence
    *  - Logging
    *
    *  Alternatively:
    *  - The Module defines what it needs and the implementor is responsible for making the corresponding resources available
    *  - For this, Modulizer would provide a way to share something with the module at load time
    *
    *  Per-user and per-server accessibility is stored in persistence, not in memory
    *  Module functionality can be restricted to 'roles' on two levels:
    *  - Source-Code level (Static)
    *  - Dynamically, using commands
    *
    *  Modules are loaded automatically depending on existing configuration.
    *
    *  Help
    *  - A module provides help
    *  - Help gives a description of the module, and lists sub-modules and commands
    *
    *  Sub-Modules:
    *  - Modules can have Sub-Modules
    *  - Sub-Modules extend the command level by one
    *
    *  Configuration
    *  - A Module can store configuration in the following scope:
    *  - Global
    *  - Module-Specific
    *  - User-Specific
    *  - Server-Specific
    *  - User-Server-Specific
    *  - Module-User-Specific
    *  - Module-Server-Specific
    *  - Module-Server-User-Specific
    *
    *  Persistence is provided by the implementation of the module
    *
    */

    //loaded modules
    private HashMap<String, Module> modules;

    public Modulizer() {

    }

}
