# ArrayList 
# 构造函数
```java
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            if (initialCapacity != 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
            }

            this.elementData = EMPTY_ELEMENTDATA;
        }

    }

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
```
指定大小初始化或者默认以10初始化数组
# add 方法
```java
    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = this.grow();
        }

        elementData[s] = e;
        this.size = s + 1;
    }

    public boolean add(E e) {
        ++this.modCount;
        this.add(e, this.elementData, this.size);
        return true;
    }
```
在`add`操作前判断是否进行扩容 size+1 
`modCount`成員變量記錄着集合的修改次数，也就每次add或者remove它的值都会加1
保证在Iterator遍历集合时无法进行remove，add等操作。
**最终调用的扩容代码**：
        
        private Object[] grow(int minCapacity) {
            return this.elementData = Arrays.copyOf(this.elementData, this.newCapacity(minCapacity));
        }
    
        private Object[] grow() {
            return this.grow(this.size + 1);
        }
    
        private int newCapacity(int minCapacity) {
            int oldCapacity = this.elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);//1.5倍扩容
            if (newCapacity - minCapacity <= 0) {
                if (this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                    return Math.max(10, minCapacity);
                } else if (minCapacity < 0) {
                    throw new OutOfMemoryError();
                } else {
                    return minCapacity;
                }
            } else {
                return newCapacity - 2147483639 <= 0 ? newCapacity : hugeCapacity(minCapacity);
            }
        }
  #序列化
  由于 ArrayList 是基于动态数组实现的，所以并不是所有的空间都被使用。
  因此使用了 transient 修饰，可以防止被自动序列化。
  
     transient Object[] elementData;