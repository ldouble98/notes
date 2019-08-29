# java 集合类
## Collection,collections
## HashMap,HashTable,Map
> fail-fast

    fail-fast机制在遍历一个集合时，当集合结构被修改，会抛出Concurrent Modification Exception。
    
    fail-fast会在以下两种情况下抛出ConcurrentModificationException
    
    （1）单线程环境
    
    集合被创建后，在遍历它的过程中修改了结构。
    
    注意 Iteratore 的remove()方法会让expectModcount和modcount 相等，所以是不会抛出这个异常。
    
    （2）多线程环境
    
    当一个线程在遍历这个集合，而另一个线程对这个集合的结构进行了修改
    
> fail-safe
    
    fail-safe任何对集合结构的修改都会在一个复制的集合上进行修改，因此不会抛出ConcurrentModificationException
    
    fail-safe机制有两个问题
    
    （1）需要复制集合，产生大量的无效对象，开销大
    
    （2）无法保证读取的数据是目前原始数据结构中的数据。
    


HashTable 不允许存放null值 线程安全
    HashTable 是 Enmeration迭代，只能读取
HashMap 多线程情况下要自己写同步方法 
   HashMap 是 Iterator机制，采用fail-fast方法
```java
    public HashMap(int initialCapacity) {
        this(initialCapacity, 0.75F);
    }

    public HashMap() {
        this.loadFactor = 0.75F;
    }
```
- HashMap底层基于数组和链表实现
- 负载因子 a = 已装入数组数/数组长度
- 容量

容量的默认大小是16，负载因子是0.75,当容量大于阀值16*0.75时会发生扩容

Hash处理冲突 ： 

## get方法
get 和 put 类似，也是将传入的 Key 计算出 index ，如果该位置上是一个链表就需要遍历整个链表，通过 key.equals(k) 来找到对应的元素。
## put方法
首先会将传入的 Key 做`hash`运算计算出 hashcode,然后根据数组长度取模计算出在数组中的 index 下标。

由于在计算中位运算比取模运算效率高的多，所以 HashMap 规定数组的长度为
 2^n 。这样用 2^n - 1 做位运算与取模效果一致，并且效率还要高出许多。

由于数组的长度有限，所以难免会出现不同的 Key 通过运算得到的
 `index`相同，这种情况可以利用链表来解决，HashMap 会在`table[index]`处
 形成链表，采用头插法将数据插入到链表中
## 遍历方式
```java
     Iterator iterator=  map.keySet().iterator();
            while (iterator.hasNext()){
                int key = (int) iterator.next();
                int valuer = map.get(key);
                System.out.println();
            }
```
```java
    Iterator<Map.Entry<Integer,Integer>> iterator1 = map.entrySet().iterator();
            while (iterator1.hasNext()){
                Map.Entry<Integer,Integer> entry = iterator1.next();
                System.out.println(entry.getKey()+"value:"+entry.getValue());
            }
```
```java
    map.forEach((key,value)->{
            System.out.println("key=" + key + " value=" + value);
        });
```

方法二`entrySet`会同时输出key和value值，效率高。
第三种需要`JDK1.8`以上，通过外层遍历 table，内层遍历链表或红黑树。


JDK1.8 中对 HashMap 进行了优化： 当 hash 碰撞之后写入链表的长度超过了阈值(默认为8)，链表将会转换为**红黑树**。

假设`hash`冲突非常严重，一个数组后面接了很长的链表，此时重新的时间复杂度就是`O(n)`。

如果是红黑树，时间复杂度就是` O(logn)` 。

大大提高了查询效率。

> jdk1.7 扩容时 死循环问题
    
> jdk 1.8 尾插法 扩容时不再调用rehash重新计算hash,
而是判断原始hash的高一位是0还是1，直接将低四位的hash+原来数组的长度OldCap
    

## h & (length-1) 与操作   不用求 % 自 定义 2 的 n 的方数
    效率快
    16 0001 0000     17 0001 0001
    
    15 0000 1111     16 0001 0000
    h :0101 0101     h :0101 0101
    &                &  只会有 0001 0000和0000 0000 两种结果，浪费空间
       0000 0101  前四位证号正好都是o，取余的结果就是后四位，而且范围正好是16 
    所以初始化时采用2的n次方
    寻求散列性更好 

































