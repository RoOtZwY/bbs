为了快速开发，可能会存在许多未抽离和隔离的代码，并且省略了很多 DTO 的定义转换，目前代码中的 DO 可以理解为在系统中流转的贫血对象，CO 可以理解为展现给外界的数据对象，Entity 则是单表映射对象。

目前的论坛帖子设计：

攻略区：帖子中所有用户的权重都相等，由帖主发布帖子，其它用户只能回复主贴而不能互相回复，随着时间流排序，保证帖子有一定的次序，并规定帖子有一定的字数要求