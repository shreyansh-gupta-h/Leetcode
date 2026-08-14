class LFUCache {

    private final int capacity;
    private int minFreq;

    private Map<Integer, Integer> keyToVal;
    private Map<Integer, Integer> keyToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;

        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }

        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        // Key already exists
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            increaseFreq(key);
            return;
        }

        // Cache full
        if (keyToVal.size() == capacity) {
            LinkedHashSet<Integer> keys = freqToKeys.get(minFreq);
            int evict = keys.iterator().next(); // LRU within minFreq
            keys.remove(evict);

            keyToVal.remove(evict);
            keyToFreq.remove(evict);
        }

        // Insert new key
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);

        freqToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);

        minFreq = 1;
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);

        keyToFreq.put(key, freq + 1);

        LinkedHashSet<Integer> oldSet = freqToKeys.get(freq);
        oldSet.remove(key);

        if (freq == minFreq && oldSet.isEmpty()) {
            minFreq++;
        }

        freqToKeys
            .computeIfAbsent(freq + 1, k -> new LinkedHashSet<>())
            .add(key);
    }
}