/*
   Copyright 2012-2017 Michael Pozhidaev <michael.pozhidaev@gmail.com>
   Copyright 2015-2016 Roman Volovodov <gr.rPman@gmail.com>

   This file is part of LUWRAIN.

   LUWRAIN is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public
   License as published by the Free Software Foundation; either
   version 3 of the License, or (at your option) any later version.

   LUWRAIN is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   General Public License for more details.
*/

package org.luwrain.controls.browser;

import org.luwrain.core.*;

public interface Callback
{
    public enum MessageType {PROGRESS, ALERT, ERROR};

    void onBrowserRunning();
    void onBrowserSuccess(String title);
    void onBrowserFailed();
    void onBrowserContentChanged(long lastTimeChanged);
    String askFormTextValue(String currentValue);
    String askFormListValue(String[] items, boolean fromListOnly);
    int getAreaVisibleWidth(Area area);
    boolean confirm(String text);
    String prompt(String message, String text);
    void message(String text, MessageType type);
}