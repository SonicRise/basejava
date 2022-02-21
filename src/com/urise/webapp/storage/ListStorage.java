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
    protected Resume getElement(int index) {
        return resumeList.get(index);
    }

    @Override
    protected void saveElement(Resume resume) {
        resumeList.add(resume);
    }

    @Override
    protected void updateElement(int index, Resume resume) {
        System.out.println("Updated");
        resumeList.set(index, resume);
    }

    @Override
    protected void deleteElement(int index) {
        resumeList.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    protected int getIndex(String uuid) {
        for (Resume r : resumeList) {
            if (uuid.equals(r.getUuid())) {
                return resumeList.indexOf(r);
            }
        }
        return -1;
    }
}
