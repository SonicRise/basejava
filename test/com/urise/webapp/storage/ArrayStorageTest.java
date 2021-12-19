package com.urise.webapp.storage;

import org.junit.Assert;
import org.junit.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    public void getIndex() {
        ArrayStorage arrayStorage = (ArrayStorage) storage;
        Assert.assertEquals(0, arrayStorage.getIndex(UUID_1));
    }
}