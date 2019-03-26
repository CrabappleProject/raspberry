# 前言
HTTPS是以安全为目标的HTTP通道，简单讲是HTTP的安全版，即HTTP下加入SSL层，HTTPS的安全基础是SSL，因此加密的详细内容就需要SSL。

## 1. 主要作用
1.确认网站的真实性。
2.建立一个信息安全通道，来保证数据传输的安全。

## 2.什么是SSL
SSL(Secure Sockets Layer 安全套接层),及其继任者传输层安全（Transport Layer Security，TLS）是为网络通信提供安全及数据完整性的一种安全协议。TLS与SSL在传输层对网络连接进行加密。

## 3.HTTPS与HTTP的区别
1.HTTPS的服务器需要到CA申请证书，以证明自己服务器的用途；
2.HTTP信息是明文传输，HTTPS信息是密文传输；
3.HTTP与HTTPS的端口不同，一个是80端口，一个是443端口；
4.可以说HTTP与HTTPS是完全不同的连接方式，HTTPS集合了加密传输，身份认证，更加的安全。

## 4.通信过程
![HTTPS通信验证](https://raw.githubusercontent.com/CrabappleProject/raspberry/master/extra/img/HTTPS通信验证.jpg)
HTTPS就是一种HTTP的升级版.