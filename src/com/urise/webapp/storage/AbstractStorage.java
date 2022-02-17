package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(index);
    }

    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }

        saveElement(resume);
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateElement(index, resume);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteElement(index);
    }

    protected abstract int getIndex(String uuid);

    protected abstract Resume getElement(int index);

    protected abstract void saveElement(Resume resume);

    protected abstract void updateElement(int index, Resume resume);

    protected abstract void deleteElement(int index);
}
