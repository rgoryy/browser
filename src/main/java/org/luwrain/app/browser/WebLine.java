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

package org.luwrain.app.browser;

import java.util.*;

import org.luwrain.controls.block.*;
import org.luwrain.web.WebKitBlockBase.*;

import static org.luwrain.core.NullCheck.*;

final class WebLine implements BlockLine
{
    final String text;
    final Line srcLine;

    WebLine(String text)
    {
	notNull(text, "text");
	this.text = text;
	this.srcLine = null;
    }

    WebLine(Line srcLine)
    {
	notNull(srcLine, "srcLine");
	this.text = srcLine.text;
	this.srcLine = srcLine;
    }
}
