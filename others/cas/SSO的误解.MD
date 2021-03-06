# 关于APP如何只能在一个地方登陆
相信很多人在移动开发中都会遇到这样的需求，当手机端的一个账号已经登录的情况下，
限制这个账号在另一个设备上同时登录；账号自动登录。其实要做到这个的方法有很多，
在这里我说下目前使用最多的一种方法：
在开发的时候，和后台协商定义一个token字段，在每次通过输入账号密码登录的情况下，
后台生成一个token（一般为字符串）保存在数据库或其他方式，并返回给客户端，
客户端接收后保存在本地（可以是数据库，也可以是SharedPreferences），
在每次和后台的网络请求中都带上这个token字段，后台每次接收到请求都先对这个字段进行校验，
如果与现有的字段值不相同，则说明是违法请求，限制操作或者强制下线。
在这里可以分析下这样做的原理：
当设备第一次登陆时是必输账号密码的（可以通过校验本地token值是否为空来确定是否自动登陆，
不为空说明之前已经登陆过，后台返回了token值），后台收到登陆请求，
通过请求并返回token值给客户端，客户端保存并在随后的网络请求中都带上这个字段，
这样如果在一个账号已经登陆的情况下，使用别的设备再登陆就会出现，后台收到另一设备的登陆请求，
重新生成了新的token值，这样当旧设备在请求后台时会被后台校验token不正确而视为非法请求并强制其下线。
顺带一提，记住账号密码可以用sp存在本地哦，虽然不安全，但不失为可行性方法。