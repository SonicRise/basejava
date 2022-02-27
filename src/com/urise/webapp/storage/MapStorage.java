package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return resumeMap.get((String) searchKey);
    }

    @Override
    protected void saveResume(Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        resumeMap.put((String) searchKey, resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        resumeMap.remove((String) searchKey);
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = resumeMap.values().toArray(new Resume[0]);
        Arrays.sort(result);
        return result;
    }

    @Override
    protected String getSearchKey(String uuid) {
        Resume r = resumeMap.get(uuid);
        return r != null ? r.getUuid() : null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
