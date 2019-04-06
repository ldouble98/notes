# java 集合类
## Collection,collections
## HashMap,HashTable,Map
HashTable 不允许存放null值 线程安全
 
HashMap 多线程情况下要自己写同步方法 

- HashMap底层基于数组和链表实现
- 负载因子 a = 已装入数组数/数组长度
- 容量

容量的默认大小是16，负载因子是0.75

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


在 JDK1.8 中对 HashMap 进行了优化： 当 hash 碰撞之后写入链表的长度超过了阈值(默认为8)，链表将会转换为**红黑树**。

假设`hash`冲突非常严重，一个数组后面接了很长的链表，此时重新的时间复杂度就是`O(n)`。

如果是红黑树，时间复杂度就是` O(logn)` 。

大大提高了查询效率。


