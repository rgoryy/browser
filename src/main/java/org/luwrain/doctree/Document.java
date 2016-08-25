/*
   Copyright 2012-2016 Michael Pozhidaev <michael.pozhidaev@gmail.com>
   Copyright 2015-2016 Roman Volovodov <gr.rPman@gmail.com>

   This file is part of the LUWRAIN.

   LUWRAIN is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public
   License as published by the Free Software Foundation; either
   version 3 of the License, or (at your option) any later version.

   LUWRAIN is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   General Public License for more details.
*/

package org.luwrain.doctree;

import java.net.*;
import java.util.*;

import org.luwrain.core.NullCheck;
import org.luwrain.core.Log;

public class Document 
{
    private final Properties props = new Properties();
    private String title;
    private String[] hrefs;


    private NodeImpl root;
    private Layout layout = new Layout(this);
    private Paragraph[] paragraphs; //Only paragraphs which appear in document, no paragraphs without row parts
    public RowPart[] rowParts;
    private Row[] rows;

    public HashMap<String,NodeImpl> idx=new HashMap<String,NodeImpl>();

    public Document(NodeImpl root)
    {
	this.root = root;
	NullCheck.notNull(root, "root");
	title = "";
    }

    public Document(String title, NodeImpl root)
    {
	this.root = root;
	this.title = title;
	NullCheck.notNull(root, "root");
	NullCheck.notNull(title, "title");
    }

    public void buildView(int width)
    {
	root.commit();
	root.setEmptyMark();
	root.removeEmpty();
	Layout.calcWidth(root, width);
	final RowPartsBuilder rowPartsBuilder = new RowPartsBuilder();
	rowPartsBuilder.onNode(root);
	rowParts = rowPartsBuilder.parts();
	NullCheck.notNullItems(rowParts, "rowParts");
	Log.debug("doctree", "" + rowParts.length + " row parts prepared");
	if (rowParts.length <= 0)
	    return;
	paragraphs = rowPartsBuilder.paragraphs();
	//	    Log.debug("doctree", "" + paragraphs.length + " paragraphs prepared");
	Layout.calcHeight(root);
	Layout.calcAbsRowNums(rowParts);
	Layout.calcPosition(root);
	rows = Layout.buildRows(rowParts);
	Log.debug("doctree", "" + rows.length + " rows prepared");
	layout.calc();
    }

    public Iterator getIterator()
    {
	return new Iterator(this);
    }

    public int getLineCount()
    {
	return layout.getLineCount();
    }

    public String getLine(int index)
    {
	return layout.getLine(index);
    }

    public void setProperty(String propName, String value)
    {
	NullCheck.notEmpty(propName, "propName");
	NullCheck.notNull(value, "value");
	props.setProperty(propName, value);
    }

    public String getProperty(String propName)
    {
	NullCheck.notEmpty(propName, "propName");
	final String res = props.getProperty(propName);
	return res != null?res:"";
    }

    public void setHrefs(String[] hrefs)
    {
	NullCheck.notNullItems(hrefs, "hrefs");
	this.hrefs = hrefs;
    }

    public URL getUrl()
    {
	final String value = getProperty("url");
	if (value.isEmpty())
	    return null;
	try {
	    return new URL(value);
	}
	catch(MalformedURLException e)
	{
	    return null;
	}
    }

    public String getTitle() { return title != null?title:""; }
    public NodeImpl getRoot() { return root; }
    public Paragraph[] getParagraphs() { return paragraphs; }
    public Row[] getRows() { return rows; }
    public RowPart[] getRowParts() { return rowParts; }
    public String[] getHrefs(){return hrefs;}
}
