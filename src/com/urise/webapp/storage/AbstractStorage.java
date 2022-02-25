package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        searchKeyNotExistCheck(uuid);
        return getElement(getSearchKey(uuid));
    }

    @Override
    public void save(Resume resume) {
        searchKeyExistCheck(resume.getUuid());
        saveElement(resume);
    }

    @Override
    public void update(Resume resume) {
        searchKeyNotExistCheck(resume.getUuid());
        updateElement(getSearchKey(resume.getUuid()), resume);
    }

    @Override
    public void delete(String uuid) {
        searchKeyNotExistCheck(uuid);
        deleteElement(getSearchKey(uuid));
    }

    private void searchKeyNotExistCheck(String uuid) {
        if ((getSearchKey(uuid) instanceof Integer && (Integer) getSearchKey(uuid) < 0) ||
                (getSearchKey(uuid) instanceof String && getSearchKey(uuid).equals(""))) {
            throw new NotExistStorageException(uuid);
        }
    }

    private void searchKeyExistCheck(String uuid) {
        if ((getSearchKey(uuid) instanceof Integer && (Integer) getSearchKey(uuid) >= 0) ||
                (getSearchKey(uuid) instanceof String && !getSearchKey(uuid).equals(""))) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getElement(Object searchKey);

    protected abstract void saveElement(Resume resume);

    protected abstract void updateElement(Object searchKey, Resume resume);

    protected abstract void deleteElement(Object searchKey);
}
