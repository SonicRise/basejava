package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getElement(int index) {
        return storage[index];
    }

    @Override
    protected void saveElement(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        }
        insertElement(resume);
        size++;
    }

    @Override
    protected void updateElement(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void insertElement(Resume resume);

    protected abstract void fillDeletedElement(int index);
}
