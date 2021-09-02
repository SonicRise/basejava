/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume tempResume;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (i == size() - 1) {
                    storage[i] = null;
                } else {
                    tempResume = storage[i + 1];
                    storage[i + 1] = storage[i];
                    storage[i] = tempResume;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[size()];
        if (allResumes.length >= 0) System.arraycopy(storage, 0, allResumes, 0, allResumes.length);
        return allResumes;
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }
            count++;
        }
        return count;
    }
}
