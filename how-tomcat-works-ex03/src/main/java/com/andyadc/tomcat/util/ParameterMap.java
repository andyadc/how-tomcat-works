package com.andyadc.tomcat.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of <strong>java.util.Map</strong> that includes a
 * <code>locked</code> property.  This class can be used to safely expose
 * Catalina internal parameter map objects to user classes without having
 * to clone them in order to avoid modifications.  When first created, a
 * <code>ParmaeterMap</code> instance is not locked.
 *
 * @param <K> The type of Key
 * @param <V> The type of Value
 * @author Craig R. McClanahan
 * @author andy.an
 * @since 2018/2/11
 */
public final class ParameterMap<K, V> implements Map<K, V>, Serializable {

    private static final long serialVersionUID = 2L;
    private static final StringManager sm = StringManager.getManager("com.andyadc.tomcat.util");
    private final Map<K, V> delegatedMap;
    private final Map<K, V> unmodifiableDelegatedMap;
    /**
     * The current lock state of this parameter map.
     */
    private boolean locked = false;

    /**
     * Construct a new, empty map with the default initial capacity and
     * load factor.
     */
    public ParameterMap() {
        this.delegatedMap = new LinkedHashMap<>();
        this.unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    /**
     * Construct a new, empty map with the specified initial capacity and
     * default load factor.
     *
     * @param initialCapacity The initial capacity of this map
     */
    public ParameterMap(int initialCapacity) {
        delegatedMap = new LinkedHashMap<>(initialCapacity);
        unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    /**
     * Construct a new, empty map with the specified initial capacity and
     * load factor.
     *
     * @param initialCapacity The initial capacity of this map
     * @param loadFactor      The load factor of this map
     */
    public ParameterMap(int initialCapacity, float loadFactor) {
        delegatedMap = new LinkedHashMap<>(initialCapacity, loadFactor);
        unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    /**
     * Construct a new map with the same mappings as the given map.
     *
     * @param map Map whose contents are duplicated in the new map
     */
    public ParameterMap(Map<K, V> map) {
        delegatedMap = new LinkedHashMap<>(map);
        unmodifiableDelegatedMap = Collections.unmodifiableMap(delegatedMap);
    }

    /**
     * @return the locked state of this parameter map.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Set the locked state of this parameter map.
     *
     * @param locked The new locked state
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    private void checkLocked() {
        if (locked) {
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
    }

    @Override
    public int size() {
        return delegatedMap.size();
    }

    @Override
    public boolean isEmpty() {
        return delegatedMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegatedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegatedMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return delegatedMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        checkLocked();
        return delegatedMap.put(key, value);
    }

    @Override
    public V remove(Object key) {
        checkLocked();
        return delegatedMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        checkLocked();
        delegatedMap.putAll(m);
    }

    @Override
    public void clear() {
        checkLocked();
        delegatedMap.clear();
    }

    @Override
    public Set<K> keySet() {
        if (locked) {
            return unmodifiableDelegatedMap.keySet();
        }
        return delegatedMap.keySet();
    }

    @Override
    public Collection<V> values() {
        if (locked) {
            return unmodifiableDelegatedMap.values();
        }
        return delegatedMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        if (locked) {
            return unmodifiableDelegatedMap.entrySet();
        }
        return delegatedMap.entrySet();
    }
}
