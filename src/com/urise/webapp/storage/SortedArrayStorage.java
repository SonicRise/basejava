package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else if (resume.getUuid() == null) {
            System.out.println("UUID in Resume is null");
        } else if (index >= 0) {
            System.out.println("Resume with uuid " + resume.getUuid() + " already exists");
        } else {
            int newIndex = Math.abs(index) - 1;
            if (size - newIndex >= 0) System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
            storage[newIndex] = resume;
            size++;
        }
    }

    @Override
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

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid " + uuid + " does not exist");
        } else {
            if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
