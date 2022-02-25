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
    protected Resume getElement(Object searchKey) {
        return resumeMap.get((String) searchKey);
    }

    @Override
    protected void saveElement(Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Object searchKey, Resume resume) {
        resumeMap.put((String) searchKey, resume);
    }

    @Override
    protected void deleteElement(Object searchKey) {
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
        return r != null ? r.getUuid() : "";
    }
}
