B树,数据库
## 前言
了解B树和哈希数据结构有助于预测查询在这些使用不同索引数据结构的存储引擎上的执行情况，特别是对于MEMORY存储引擎,它是允许您选择B树或哈希作为索引的存储引擎。
### 1. B树指数特征
B树索引可以在使用表达式中使用的对列的比较 =， >， >=， <， <=，或BETWEEN关键字。如果使用LIKE 或to LIKE且是一个不以通配符开头的常量字符串，则索引也可用于比较 。
1. 例如，以下SELECT语句将使用索引：
````mysql
SELECT * FROM tbl_name WHERE key_col LIKE 'Patrick%';
SELECT * FROM tbl_name WHERE key_col LIKE 'Pat%_ck%';
````
在第一个语句中 'Patrick' <= key_col < 'Patricl',在第二个语句中'Pat' <= key_col < 'Pau'

2. 以下SELECT语句不使用索引：
````mysql
SELECT * FROM tbl_name WHERE key_col LIKE '%Patrick%';
SELECT * FROM tbl_name WHERE key_col LIKE other_col;
````
在第一个语句中，LIKE 值以通配符开头。在第二个语句中，该LIKE值不是常量。

如果使用了像'%string%'且长度超过三个字符的字符串查询,那么MySQL将使用Turbo Boyer-Moore算法初始化这个模型,用这个模型来匹配速度会更快.

不跨越子句中的所有AND级别的 任何索引 WHERE不用于优化查询。换句话说，为了能够使用索引，必须在每个AND组中使用索引的前缀 。

3. 以下WHERE子句使用索引：
````mysql
WHERE index_part1=1 AND index_part2=2 AND other_column=3

    /* index = 1 OR index = 2 */
WHERE index=1 OR A=10 AND index=2

    /* optimized like "index_part1='hello'" */
WHERE index_part1='hello' AND index_part3=5

    /* Can use index on index1 but not on index2 or index3 */
WHERE index1=1 AND index2=2 OR index1=3 AND index3=3;
````
4. 这些WHERE子句 不使用索引：

````mysql
    /* index_part1 is not used */
WHERE index_part2=1 AND index_part3=2

    /*  Index is not used in both parts of the WHERE clause  */
WHERE index=1 OR A=10

    /* No index spans all rows  */
WHERE index_part1=1 OR index_part2=10
````
有时MySQL不使用索引，即使有索引也是如此。发生这种情况的一种原因是，优化器估计使用索引将需要MySQL访问表中非常大比例的行。（在这种情况下，表扫描可能会快得多，因为它需要的搜索次数较少。）但是，如果这样的查询:例如LIMIT只用于检索某些行，那么MySQL无论如何都会使用索引，因为它可以更快地找到在结果中返回几行。

### 2. 哈希指数特征
散列索引与刚才讨论的特征有些不同：

1. 它们仅用于使用=或<=>(文章结尾有此符号说明) 运算符的相等比较 （但速度非常快）。它们不用于比较运算符，例如 <找到一系列值。依赖于这种类型的单值查找的系统被称为“ 键值存储 ” ; 要将MySQL用于键值查找类，请尽可能使用哈希索引。

2. 优化器无法使用哈希索引来加速 ORDER BY操作。（此类索引不能用于按顺序搜索下一个条目。）

3. MySQL无法确定两个值之间大约有多少行（范围优化器使用它来决定使用哪个索引）。如果将 MyISAM或 InnoDB表更改为哈希索引 MEMORY表，则可能会影响某些查询。

4. 只有整个键可用于搜索行。（使用B树索引，键的任何最左边的前缀都可用于查找行。）
### 附录
#### 解释=和<=>的区别:
相同点:
　　像常规的=运算符一样，两个值进行比较，结果是0（不等于）或1（相等）,换句话说：’A'<=>’B'得0和’a'<=>’a‘得1,都是值的比较。
不同点:
　　NULL的值是没有任何意义的。所以=号运算符不能把NULL作为有效的结果。所以：请使用<=>,'a' <=> NULL 得0   NULL<=> NULL 得出 1。和=运算符正相反，=号运算符规则是 'a'=NULL 结果是NULL 甚至NULL = NULL 结果也是NULL。顺便说一句，mysql上几乎所有的操作符和函数都是这样工作的，因为和NULL比较基本上都没有意义。
用处当两个操作数中可能含有NULL时，你需要一个一致的语句,此时就可以用<=>.
