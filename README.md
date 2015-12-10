# compact-data

JVM is inefficient in heap usage for objects (see also https://vimeo.com/138956045 ) 
This library store all data of an object and all fields in a byte array. There multiple optimized Schema for types of data, 
so for String there is a common Schema but also one for fixed size Strings, NumberStrings, 
StringEnums (fix list of possible Strings) & ASCII Strings
It provides Schema for all native data types and Lists.

There are 2 implementations, a byte based one (each object uses a fix size of bytes) and a bit based solution, 
which also support datatypes with any bit count (a boolean is one bit, integers with n bits are supported, optimized doubles).
The bit variant needs less memory but is also less performance.

All compact data are represented with a byte array, so there is also an efficient byte serialization without any overhead.

Currently, only readonly data are supported.

For a bit-based example see
https://github.com/ShengFui/compact-data/blob/master/src/test/java/net/sprd/example/Path.java
a byte based example
https://github.com/ShengFui/compact-data/blob/master/src/test/java/net/sprd/example/MyCompactData.java
