# Set集合
无序且不能重复的集合,继承Collection 接口, 实现类: HashSet(是一个只有Key的HashMap）
# 构造函数
```java
    public HashSet() {
        this.map = new HashMap();
    }

    public HashSet(Collection<? extends E> c) {
        this.map = new HashMap(Math.max((int)((float)c.size() / 0.75F) + 1, 16));
        this.addAll(c);
    }

    public HashSet(int initialCapacity, float loadFactor) {
        this.map = new HashMap(initialCapacity, loadFactor);
    }

    public HashSet(int initialCapacity) {
        this.map = new HashMap(initialCapacity);
    }

    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        this.map = new LinkedHashMap(initialCapacity, loadFactor);
    }
```
创建HashSet的过程实际在构造方法是调用HashMap的初始化
过程，由于HashMap不允许有重复的key值，所以Set没有重复元素
# add方法

    public boolean add(E e) {
            return this.map.put(e, PRESENT) == null;
        }
由于`HashMap`的`key`是不能重复的，所以每当有重复的值写入到`HashSet`时，
`value`会被覆盖，但`key`不会受到影响，这样就保证了`HashSet`中只能存放不重复的元素。












