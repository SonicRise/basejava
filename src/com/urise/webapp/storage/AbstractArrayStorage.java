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
        if (isCheckPassed(resume, true)) {
            doSave(resume);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " does not exist");
        } else {
            doDelete(index);
        }
    }

    public void update(Resume resume) {
        if (isCheckPassed(resume, false)) {
            storage[getIndex(resume.getUuid())] = resume;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void doSave(Resume resume);

    protected abstract void doDelete(int index);

    private boolean isCheckPassed(Resume resume, boolean isExist) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full");
            return false;
        } else if (resume.getUuid() == null) {
            System.out.println("UUID in Resume is null");
            return false;
        } else if (isExist && getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume with uuid " + resume.getUuid() + " already exists");
            return false;
        } else if (!isExist && getIndex(resume.getUuid()) < 0) {
            System.out.println("Resume with uuid " + resume.getUuid() + " does not exist");
            return false;
        } else {
            return true;
        }
    }
}
