package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100_000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " does not exist");
            return null;
        }
        return storage[index];
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else if (resume.getUuid() == null) {
            System.out.println("UUID in Resume is null");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume with uuid " + resume.getUuid() + " already exists");
        } else {
            insertElement(resume);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " does not exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (resume.getUuid() == null) {
            System.out.println("UUID in Resume is null");
        } else if (index < 0) {
            System.out.println("Resume with uuid " + resume.getUuid() + " does not exist");
        } else {
            storage[index] = resume;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
 
    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume);

    protected abstract void fillDeletedElement(int index);
}
