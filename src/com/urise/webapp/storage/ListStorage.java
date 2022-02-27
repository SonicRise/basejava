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
    protected Resume getResume(Object searchKey) {
        return resumeList.get((int) searchKey);
    }

    @Override
    protected void saveResume(Resume resume) {
        resumeList.add(resume);
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        System.out.println("Updated");
        resumeList.set((int) searchKey, resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        resumeList.remove((int) searchKey);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    protected Integer getSearchKey(String uuid) {
        return resumeList.indexOf(new Resume(uuid));
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }
}
