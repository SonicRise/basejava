package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        return getResume(getSearchkeyIfExist(uuid));
    }

    @Override
    public void save(Resume resume) {
        getSearchkeyIfNotExist(resume.getUuid());
        saveResume(resume);
    }

    @Override
    public void update(Resume resume) {
        updateResume(getSearchkeyIfExist(resume.getUuid()), resume);
    }

    @Override
    public void delete(String uuid) {
        deleteResume(getSearchkeyIfExist(uuid));
    }

    private Object getSearchkeyIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchkeyIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void saveResume(Resume resume);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void deleteResume(Object searchKey);
}
