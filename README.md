#### 完全跨域单点登录

 比如我要在两个系统中进行SSO 一个域名为  `www.niehziliang.com`   另一个域名为 `nzl.fangxinqian.cn`

 实现思路：

    在其中一个域名下登录成功，登录方法就会把jwt生成的token 保存到本域下，然后前端接收到登录成功的信息

  就跳转到首页，首页有个隐藏的ifream 在页面加载的时候，通过js拿到本域下面刚保存的cookie，动态改变ifream的src属性

  连接为另一个域名 nzl.fangxiqian.cn/后台为友方域添加cookie的方法?cookieName=xxx&cookieName=xxxxxx，

  这样做的目的，是两个域都保存了登录成功的cookie信息,所有在另一域名下 直接刷新就能拿到登录成功的信息，这样就达到了单点登录的效果

###### main-web

- 这个是后台，主要做的就是用户登录成功后，通过jwt生成token 通过cookie的形式 放到自己的域下面


- 还有一个验证用户是否登录的方法（如果cookie中存在登录token则直接返回登录成功）


- 还有一个就是为友方域添加cookie（如果我们在`www.niehziliang.com`登录成功后，会自动在其域下添加登录token，所以我们就要为`nzl.fangxinqian.cn`域下也添加登录成功的token到cookie）

这样就能达到单点登录的效果啦



###### ui-a




###### ui-b


这两个模块可以说基本一样，唯一不同的就是在首页(index.vue)，有一个ifream 他们访问的地址都是对方的域名加后台的地址，这样后台就会给对方域添加cookie的效果


在通过ifream跨域访问的时候可能在获取token的时候有点问题，主要就是vue使用不熟练造成的，但是功能却实现了
