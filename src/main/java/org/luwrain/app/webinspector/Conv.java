/*
   Copyright 2012-2024 Michael Pozhidaev <msp@luwrain.org>
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

package org.luwrain.app.webinspector;

import java.util.*;

import org.luwrain.core.*;
import org.luwrain.popups.*;

final class Conv
{
    private final Luwrain luwrain;
    private final Set<String> openUrlHistory = new HashSet<String>();

    Conv(App app)
    {
	NullCheck.notNull(app, "app");
	this.luwrain = app.getLuwrain();
    }

        String formText(String prevValue)
    {
	NullCheck.notNull(prevValue, "prevValue");
	return Popups.text(luwrain, "Редактирования формы", "Текст в поле:", prevValue);
    }
    }
