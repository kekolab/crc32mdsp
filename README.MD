# CRC32 Message Digest and Security Provider

The library is made of a Java Message Digest and a Java Security  Provider implementation based on the checksum algorithm CRC32. Particularly, it is based on the default Java implementation of CRC32

## Integration

You can integrate this library in your build using jitpack. To understand how, click on the following badge:

[![](https://jitpack.io/v/kekolab/crc32mdsp.svg)](https://jitpack.io/#kekolab/crc32mdsp)

## Usage

If you want to use directly the message digest, these are the steps:

```java
byte[] bytes; // The array of bytes whose CRC32 checksum is to be calculated
MessageDigestSpi digest = new CRC32MessageDigestSpi();
digest.engineUpdate(bytes, 0, bytes.length);
byte[] result = digest.engineDigest();
```

Anyway, the message digest has very little added value (the CRC32 checksum of a byte array can be easily calculated with the class `java.util.zip.CRC32`, which is what, actually, `CRC32MessageDigestSpi` does under the hood); the added value is that you can add a `CRC32Provider` to your security provider:

```java
static {
    Security.addProvider(new CRC32Provider()); // Just once
}

public void foo(byte[] bar) {
    MessageDigest digest = MessageDigest.getInstance("CRC32");
    digest.update(bar);
    byte[] result = digest.digest();
}
```
