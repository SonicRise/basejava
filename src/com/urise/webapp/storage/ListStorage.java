package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    protected Resume getElement(Object searchKey) {
        return resumeList.get((int) searchKey);
    }

    @Override
    protected void saveElement(Resume resume) {
        resumeList.add(resume);
    }

    @Override
    protected void updateElement(Object searchKey, Resume resume) {
        System.out.println("Updated");
        resumeList.set((int) searchKey, resume);
    }

    @Override
    protected void deleteElement(Object searchKey) {
        resumeList.remove((int) searchKey);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    protected Integer getSearchKey(String uuid) {
        return resumeList.indexOf(new Resume(uuid));
    }
}
