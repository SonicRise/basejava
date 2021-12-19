package com.urise.webapp.storage;

import org.junit.Assert;
import org.junit.Test;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void getIndex() {
        SortedArrayStorage arrayStorage = (SortedArrayStorage) storage;
        Assert.assertEquals(0, arrayStorage.getIndex(UUID_1));
    }
}
