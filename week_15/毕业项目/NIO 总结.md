## 模块二 NIO 总结
> 1、NIO和传统IO（一下简称IO）之间第一个最大的区别是，IO是面向流的，NIO是面向缓冲区的。

	Java IO面向流意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。此外，它不能前后移动流中的数据。如果需要前后移动从流中读取的数据，需要先将它缓存到一个缓冲区。NIO的缓冲导向方法略有不同。数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动。这就增加了处理过程中的灵活性。但是，还需要检查是否该缓冲区中包含所有您需要处理的数据。而且，需确保当更多的数据读入缓冲区时，不要覆盖缓冲区里尚未处理的数据。

> 2、IO的各种流是阻塞的。这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了。

	NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取。而不是保持线程阻塞，所以直至数据变的可以读取之前，该线程可以继续做其他的事情。 非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。 线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道（channel）

### NIO三种模型
	基本可以认为 “NIO = I/O多路复用 + 非阻塞式I/O”，大部分情况下是单线程，但也有超过一个线程实现NIO的情况
	上面所讲到的只需要一个线程就可以同时处理多个套接字，这只是其中的一种单线程模型，是一种较为极端的情况，NIO主要包含三种线程NIO三种模型

> 1、Reactor单线程模型

	单个线程完成所有事情包括接收客户端的TCP连接请求，读取和写入套接字数据等。
	对于一些小容量应用场景，可以使用单线程模型。但是对于高负载、大并发的应用却不合适,主要原因如下：
	[1]一个NIO线程同时处理成百上千的链路，性能上无法支撑，即便NIO线程的CPU负荷达到100%，也无法满足海量消息的编码、解码、读取和发送；
	[2]当NIO线程负载过重之后，处理速度将变慢，这会导致大量客户端连接超时，超时之后往往会进行重发，这更加重了NIO线程的负载，最终会导致大量消息积压和处理超时，NIO线程会成为系统的性能瓶颈；
	[3]可靠性问题：一旦NIO线程意外跑飞，或者进入死循环，会导致整个系统通信模块不可用，不能接收和处理外部消息，造成节点故障。

> 2、Reactor多线程模型

	Rector多线程模型与单线程模型最大的区别就是有一组NIO线程处理真实的IO操作。
	[1] 有专门一个NIO线程-Acceptor线程用于监听服务端，接收客户端的TCP连接请求；
	[2] 网络IO操作-读、写等由一个NIO线程池负责，线程池可以采用标准的JDK线程池实现，它包含一个任务队列和N个可用的线程，由这些NIO线程负责消息的读取、解码、编码和发送；
	[3] 1个NIO线程可以同时处理N条链路，但是1个链路只对应1个NIO线程，防止发生并发操作问题。

> 3、主从Reactor多线程模型

	在绝大多数场景下，Reactor多线程模型都可以满足性能需求；但是，在极特殊应用场景中，一个NIO线程负责监听和处理所有的客户端连接可能会存在性能问题。例如百万客户端并发连接，或者服务端需要对客户端的握手消息进行安全认证，认证本身非常损耗性能。在这类场景下，单独一个Acceptor线程可能会存在性能不足问题，为了解决性能问题，产生了第三种Reactor线程模型-主从Reactor多线程模型
	即从单线程中由一个线程即监听连接事件、读写事件、由完成数据读写，拆分为由一个线程专门监听各种事件，再由专门的线程池负责处理真正的IO数据读写。
	即从多线程模型中由一个线程来监听连接事件和数据读写事件，拆分为一个线程监听连接事件，线程池的多个线程监听已经建立连接的套接字的数据读写事件，另外和多线程模型一样有专门的线程池处理真正的IO操作。


[NIO 脑图](https://github.com/nj-068-lx1991/JavaCourseCodes/blob/master/week_15/%E6%AF%95%E4%B8%9A%E9%A1%B9%E7%9B%AE/nio.png)