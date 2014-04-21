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

import org.exist.xquery.value.Item;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author <a href="mailto:gazdovsky@gmail.com">Gazdovsky Evgeny</a>
 *
 */
public class eXid {


    private static final char[] abc =  {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    };

    private static BigInteger l = BigInteger.valueOf(abc.length);

    private static String translate(BigInteger n, String s) {
        if (n.compareTo(l) < 0)
            return abc[n.byteValue()] + s;
        byte i =  n.mod(l).byteValue();
        return translate(n.divide(l), abc[((int) i)] + s);
    }

    private static String uuid(UUID id) {
        ByteBuffer b = ByteBuffer.allocate(16);
        b.putLong(id.getMostSignificantBits());
        b.putLong(id.getLeastSignificantBits());
        BigInteger a = new BigInteger(1, b.array());
        return 'x' + translate(a, "");
    }

    public static String get() {
        return uuid(UUID.randomUUID());
    }

    public static String get(String s) {
        return uuid(UUID.nameUUIDFromBytes(s.getBytes()));
    }

}
