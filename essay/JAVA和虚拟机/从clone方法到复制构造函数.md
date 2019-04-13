java,clone
## 前言
在Java API中,可以通过实现Cloneable接口并重写clone方法实现克隆,但Java设计者否定了使用clone创建新对象的方法.
### 1. clone方法实现对象的复制
在Java API中,如果被克隆的对象成员变量有对象变量,则对象变量也需要实现Cloneable接口,并重新给新的父类赋值,例如:
    1.创建一个对象,其存在对象类型的成员变量childClone
````java
class ParentsClone implements Cloneable {

    public int id;
    public ChildClone childClone;

    public ParentsClone(int id) {
        this.id = id;
    }

    public ParentsClone(int id, ChildClone childClone) {
        this.id = id;
        this.childClone = childClone;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
````
    2.因此ChildClone也需要实现Cloneable:
````java
class ChildClone implements Cloneable {
    public String name;

    public ChildClone(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
````
    3.所以要做到真正的clone需要按照如下调用顺序操作:
````java
public class ParentClone implements Cloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        ParentsClone p1 = new ParentsClone(1, new ChildClone("HAHA"));
        ParentsClone p2 = (ParentsClone) p1.clone();
        p2.childClone = (ChildClone) p1.childClone.clone();

        System.out.println("p1 HashCode: " + p1.hashCode() + "  p1.child HashCode: " + p1.childClone.hashCode());
        System.out.println("p2 HashCode: " + p2.hashCode() + "  p2.child HashCode: " + p2.childClone.hashCode());
    }
}
//output:
/**
* p1 HashCode: 1163157884  p1.child HashCode: 1956725890
* p2 HashCode: 356573597  p2.child HashCode: 1735600054
*/

````
    4.如此便完成了java对象的真正clone.但是java开发者并不建议这样做.
### 2. 和JAVA开发者对话
Bill Venners： 在你的书中，你建议使用复制构造函数而不是实现Cloneable和编写clone。你能详细说明吗？

Josh Bloch：如果你已经阅读了我的书中关于克隆的章节，特别是如果你看得仔细的话，你就会知道我认为克隆已经完全坏掉的东西。有一些设计缺陷，其中最大的一个是 Cloneable 接口没有 clone方法。这意味着它根本不起作用：实现了 Cloneable 接口并不说明你可以用它做什么。相反，它说明了内部可能做些什么。它说如果通过super.clone 反复调用它最终调用 Object 的 clone 方法，这个方法将返回原始的属性副本。

但它没有说明你可以用一个实现 Cloneable 接口的对象做什么，这意味着你不能做多态 clone 操作。如果我有一个 Cloneable 数组，你会认为我可以运行该数组并克隆每个元素以制作数组的深层副本，但不能。你不能强制转换对象为 Cloneable 接口并调用 clone 方法，因为 Cloneable 没有public clone 方法，Object 类也没有。如果您尝试强制转换 Cloneable 并调用该 clone 方法，编译器会说您正在尝试在对象上调用受保护的clone方法。

事实的真相是，您不通过实施 Cloneable 和提供 clone 除复制能力之外的公共方法为您的客户提供任何能力。如果您提供具有不同名称的copy操作, 怎么也不次于去实现 Cloneable 接口。这基本上就是你用复制构造函数做的事情。复制构造方法有几个优点，我在本书中有讨论。一个很大的优点是可以使副本具有与原始副本不同的实现。例如，您可以将一个 LinkedList 复制到 ArrayList。

Object 的 clone 方法是非常棘手的。它基于属性复制，而且是“超语言”。它创建一个对象而不调用构造函数。无法保证它保留构造函数建立的不变量。多年来，在Sun内外存在许多错误，这源于这样一个事实，即如果你只是super.clone 反复调用链直到你克隆了一个对象，那么你就拥有了一个浅层的对象副本。克隆通常与正在克隆的对象共享状态。如果该状态是可变的，则您没有两个独立的对象。如果您修改一个，另一个也会更改。突然之间，你会得到随机行为。

我使用的东西很少实现 Cloneable。我经常提供实现类的 clone 公共方法，仅是因为人们期望有。我没有抽象类实现 Cloneable，也没有接口扩展它，因为我不会将实现的负担 Cloneable 放在扩展（或实现）抽象类（或接口）的所有类上。这是一个真正的负担，几乎没有什么好处。

Doug Lea 走得更远。他告诉我 clone 除了复制数组之外他不再使用了。您应该使用 clone 复制数组，因为这通常是最快的方法。但 Doug 的类根本就不再实施 Cloneable了。他放弃了。而且我认为这并非不合理。

这是一个耻辱, Cloneable 接口坏掉了，但它发生了。最初的 Java API在紧迫的期限内完成，以满足市场窗口收紧的需求。最初的 Java 团队做了不可思议的工作，但并非所有的 API 都是完美的。 Cloneable 是一个弱点，我认为人们应该意识到它的局限性。
传送门:[<复制构造函数与克隆>英文原版](https://www.artima.com/intv/bloch13.html)

### 3. 总结Java开发者的话
1. 设计缺陷, Cloneable 接口没有 clone方法.
2. 调用的是Object的clone方法,返回原始的属性副本.
3. clone方法返回浅层的对象副本.
4. 应该使用 clone 复制数,因为通常速度最快..{具体使用: int[] newArrays=(int[])oldArrays.clone() }
5. 复制构造方法的优点:副本具有与原始副本不同的实现.
### 4. 复制构造函数
java开发者不建议我们使用clone方法,从而转向灵活的构造函数实现方法.
    1.新写两个类,分别两个构造函数,关注第二个构造函数,参数为当前类的对象实例.
````java
class Parents {

    public int id;
    public Child child;

    public Parents(int id, Child child) {
        this.id = id;
        this.child = child;
    }
    //实现对象的复制
    public Parents(Parents parents) {
        id = parents.id;
        child = new Child(parents.child);
    }
}


class Child {
    public String name;

    public Child(String name) {
        this.name = name;
    }
    //实现对象的复制
    public Child(Child child) {
        name = child.name;
    }
}
````
    2.测试:
 ````java
public static void main(String[] args) throws CloneNotSupportedException {

        Parents p1=new Parents(1,new Child("HAHA"));
        Parents p2=new Parents(p1);

        System.out.println("p1 HashCode: " + p1.hashCode() + "  p1.child HashCode: " + p1.child.hashCode());
        System.out.println("p2 HashCode: " + p2.hashCode() + "  p2.child HashCode: " + p2.child.hashCode());

        //output
        /**
         * p1 HashCode: 1163157884  p1.child HashCode: 1956725890
         * p2 HashCode: 356573597  p2.child HashCode: 1735600054
         */

    }
 ````
## 结语
以上便是笔者对放弃clone,使用构造方法创建新对象的整理.若有不足,敬请指正.