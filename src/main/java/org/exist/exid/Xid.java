/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-2014 The eXist Project
 *  http://exist-db.org
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.exist.exid;

import org.apache.log4j.Logger;
import org.exist.dom.QName;
import org.exist.xquery.*;
import org.exist.xquery.value.*;

import static org.exist.exid.Module.*;
import static org.exist.xquery.Cardinality.EXACTLY_ONE;
import static org.exist.xquery.value.Type.*;

/**
 * @author <a href="mailto:gazdovsky@gmail.com">Evgeny Gazdovsky</a>
 *
 */
public class Xid extends BasicFunction {

    private final static Logger logger = Logger.getLogger(Xid.class);

    public final static FunctionSignature[] signature = {
            new FunctionSignature(
                    new QName("xid", NAMESPACE_URI, PREFIX),
                    "generate random id based on the uuid.",
                    new SequenceType[] {},
                    new FunctionReturnSequenceType(STRING, EXACTLY_ONE, "")
            ),
            new FunctionSignature(
                    new QName("xid", NAMESPACE_URI, PREFIX),
                    "generate random id based on the uuid.",
                    new SequenceType[] {
                            new FunctionParameterSequenceType("id", Type.STRING, Cardinality.EXACTLY_ONE, ""),

                    },
                    new FunctionReturnSequenceType(STRING, EXACTLY_ONE, "")
            ),
    };

    public Xid(XQueryContext context, FunctionSignature signature) {
        super(context, signature);
    }
    
    @Override
    public Sequence eval(Sequence[] args, Sequence contextSequence) throws XPathException {
        return new StringValue(
                args.length == 0 ? eXid.get() : eXid.get(args[0].itemAt(0).getStringValue())
        );
    }

}
