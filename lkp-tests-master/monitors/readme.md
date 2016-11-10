Function and Usage of All Monitors

 1. [ifstat](ifstat):检测网络接口的状态信息，参数如下：
    - -t 在每一行的开头加一个时间戳（能告诉我们具体的时间）
    - -b 用kbits/s显示带宽而不是kbytes/s 
 2. [memory](memory):检测内存使用问题，参数如下：
    - total：表示物理内存总量(total = used + free)
    - used：表示总计分配给缓存（包含buffers 与cache ）使用的数量，但其中可能部分缓存并未实际使用
    - free：未被分配的内存
    - shared：共享内存，一般系统不会用到，这里也不讨论
    - buffers：系统分配但未被使用的buffers 数量
    - cached：系统分配但未被使用的cache 数量

