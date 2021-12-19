package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void get() throws Exception {
        Resume r = new Resume(UUID_1);
        Assert.assertEquals(r, storage.get(UUID_1));
    }

    @Test
    public void save() throws Exception {
        Resume r = new Resume(UUID_4);
        storage.save(r);
        Assert.assertEquals(r, storage.get(UUID_4));
    }

    @Test
    public void delete() throws Exception {
        int initSize = storage.size();
        storage.delete(UUID_1);
        Assert.assertEquals(initSize - 1, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        Assert.assertEquals(r, storage.get(UUID_1));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() throws Exception {
        Resume r = new Resume(UUID_1);
        storage.save(r);
    }

    @Test
    public void getOverflow() throws Exception {
        //Reflection
        /*Field storageLimit = storage.getClass().getSuperclass().getDeclaredField("STORAGE_LIMIT");
        Field size = storage.getClass().getSuperclass().getDeclaredField("size");
        size.set(storage, storageLimit.get(storage));
        Resume r = new Resume(UUID_1);
        try {
            storage.save(r);
        } catch (StorageException e) {

        }*/

        //Loop
        try {
            for (int i = storage.size(); i < 10_000; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Overflow happened too early");
        }

        System.out.println(storage.size());

        try {
            storage.save(new Resume());
        } catch (StorageException e) {

        }
    }
}