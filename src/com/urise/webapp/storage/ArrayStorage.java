package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r.getUuid() == null || getIndex(r.getUuid()) != -1 || size > 10_000) {
            System.out.println("Resume with uuid " + r.getUuid() + " already exists or storage is full");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        if (r.getUuid() == null || getIndex(r.getUuid()) == -1) {
            System.out.println("Resume with uuid " + r.getUuid() + " does not exist");
        } else {
            storage[getIndex(r.getUuid())] = r;
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) == -1) {
            System.out.println("Resume with uuid " + uuid + " does not exist");
            return null;
        } else {
            return storage[getIndex(uuid)];
        }
    }

    public void delete(String uuid) {
        if (getIndex(uuid) == -1) {
            System.out.println("Resume with uuid " + uuid + " does not exist");
        } else {
            int index = getIndex(uuid);
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    private int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
