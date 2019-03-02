# STABLE MATCHING

## 题目要求

SA（医学院）是求婚者，student是被求者。
输入的样本格式是：

```
num
saNames
studentNames
sa1 preferred list
sa2 preferred list
...
student1 preferred list
student2 preferred list
...
```

例如：

```
3
adam bale campbell
alice bob calvin
bob calvin alice
calvin bob alice
bob alice calvin
adam bale campbell
adam campbell bale
bale adam campbell
```

## HashMap<Integer, String>

由于这是的输入的**字符串**
在很多时候，用数字进行映射处理的会更方便。
index想映射成字符串可以用数组，很简单
但字符串映射到index需要用HashMap才比较高效。

## ~~Iterator~~

SA是降序进行找student的，不可能每次都从头找。
而是应该从上一个被挖墙角的位置之后找下一个。
为了达到这个目标，可以用一个存储单元来存储这一个SA找到的位置，
对所有的SA来说，开一个数组记录即可
不要用Iterator，性能不行

## reverse list

在学生进行比较择优的时候，需要拿序号查SA优先级。
但是我们有的偏好列表是根据优先级获取SA，和我们要用的逻辑关系相反。
所以还是一个数组，只不过是反过来的数组，被称为reverse list。
把SA的序号去映射到优先级。
